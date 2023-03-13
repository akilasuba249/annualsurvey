package com.mahindra.annualsurvey.springsecurityconfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTLoginHistory;
import com.mahindra.annualsurvey.services.LoginUserService;
import com.mahindra.annualsurvey.services.MTEmployeeService;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Autowired
	MTEmployeeService loginService;
	@Autowired
	LoginUserService loginUserService;
	

	
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void handle(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException{
		HttpSession session = request.getSession();
		String targetUrl = determineTargetUrl(authentication);
		String username =authentication.getName();
		String pass=request.getParameter("j_password");
		String tokenid = request.getParameter("j_tokenid");
		
		 
		//System.out.println("+++++++++++HARSHAD++++++ Token ID is  " + tokenid );
		
		MTEmployee user = loginService.getuserdetails(username,tokenid);
		
		
		session.setAttribute("loginDetails",user);
		session.setAttribute("username", user.getfName());
		session.setAttribute("emailId", user.getEmailId());
		
		 String ipAddress = request.getHeader("X-FORWARDED-FOR");
	        if(ipAddress==null)
	        	ipAddress = request.getRemoteAddr(); 
	        
	        MTLoginHistory loginHistory=new MTLoginHistory();
	        loginHistory.setIpAddress(ipAddress);
	        loginHistory.setLoginUser(user.getEmailId());
	        
	        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		    Browser browser = userAgent.getBrowser();
		    String browserName = browser.getName();
		    Version browserVersion = userAgent.getBrowserVersion();
		    
		    loginHistory.setBrowserName(browser.getName());
		    loginHistory.setBrowserVersion(browserVersion.toString());
		    
		    /*MTLoginHistory mtLoginHistory=new MTLoginHistory();*/
			/*mtLoginHistory=(MTLoginHistory)req.getSession(false).getAttribute("loginHistory");*/
			loginUserService.createLoginHistory(loginHistory);
	        session.setAttribute("loginHistory",loginHistory);
	       
	        
				
		if(response.isCommitted()){
		    System.out.println("Can't redirect");
		    return;
		}
		if(user.getUserAttempts() == null || user.getUserAttempts().isEmpty()) {
			if(user.getUsertype().contains("USER")) {
			user.setUserAttempts("1");
			loginService.updateemp(user);
			}
		} else {
			if(user.getUsertype().contains("USER")) {
				targetUrl = "/getSurveyDtls";
			}
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	
	public String determineTargetUrl(Authentication authentication) {
		// TODO Auto-generated method stub
		String url="";
		
		Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
        
        List<String> roles = new ArrayList<String>();
 
        for (GrantedAuthority a : authorities) {
        	System.out.println(a.getAuthority());
            roles.add(a.getAuthority());
        }
        
        if (isDba(roles)) {
            url = "/db";
        } else if (isAdmin(roles)) {
            url = "/admin";
        } else if (isUser(roles)) {
            url = "/user";
        }else if(isSPOC(roles)){
        	url = "/index";
        }else {
            url="/accessDenied";
        }
		return url;
	}
	
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
     
    private boolean isUser(List<String> roles) {
        if (roles.toString().contains("USER")) {
            return true;
        }
        return false;
    }
 
    private boolean isAdmin(List<String> roles) {
        if (roles.toString().contains("ADMIN")) {
            return true;
        }
        return false;
    }
 
    private boolean isDba(List<String> roles) {
        if (roles.toString().contains("DBA")) {
            return true;
        }
        return false;
    }
    
    private boolean isSPOC(List<String> roles){
    	if (roles.toString().contains("SPOC")) {
            return true;
        }
        return false;
    }
}
