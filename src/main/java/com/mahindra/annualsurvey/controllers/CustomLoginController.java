package com.mahindra.annualsurvey.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.activation.URLDataSource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mahindra.annualsurvey.dao.MTEmployeeDAO;
import com.mahindra.annualsurvey.dao.MTResponseDAO;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTResponse;
import com.mahindra.annualsurvey.model.MTSurvey;
import com.mahindra.annualsurvey.services.MTEmployeeService;
import com.mahindra.annualsurvey.services.StatusService;
import com.mahindra.annualsurvey.springsecurityconfig.CustomSuccessHandler;
import com.mahindra.annualsurvey.springsecurityconfig.CustomUser;
import com.mahindra.annualsurvey.springsecurityconfig.CustomeAuthProvider;
import com.mahindra.annualsurvey.springsecurityconfig.Role;
import com.mahindra.annualsurvey.utils.AES;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

@Controller
public class CustomLoginController {
	
	@Autowired
	CustomeAuthProvider customAuthProvider;
	@Autowired
	StatusService statusService;
	@Autowired
	MTEmployeeService mtemployeeservice;
	@Autowired
	MTEmployeeDAO employeeDao;
	@Autowired
	AES encryption;
	@Autowired
	MTResponseDAO mtResponseDao;
	@Autowired
	CustomSuccessHandler loginHandler;
	

//	@Autowired
////	@Qualifier("authenticationManager")
//	protected AuthenticationManager authManager;


	private static final String SPRING_SECURITY_CONTEXT_KEY=HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
	
	@RequestMapping(value = { "/urlLogin"})
	public String prelogin(Map m,HttpServletRequest req,HttpServletResponse res,@RequestParam(value="username",required=false) String user,Map<String, Object> model)throws IOException
	{
		UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
	    Browser browser = userAgent.getBrowser();
        
	    String browserName = browser.getName();
	    int vno;
	    //or 
	    // String browserName = browser.getGroup().getName();
	    Version browserVersion = userAgent.getBrowserVersion();
	    String version=browserVersion.toString() ;  
	    if(version.contains("."))
	    {
	    String[] s=version.split("\\.");
	    vno= Integer.parseInt(s[0]);
	    }
	    else
	    	vno=Integer.parseInt(version.toString());
	    
	    //chrome compatibility
	    if(browserName.contains("Chrome") && vno < 45)
	    {
	    	   return "checkCompatibility";		
	    }
	    
	   /*// Internet Explorer compatibility
	    else if(browserName.contains("Internet Explorer") && vno < 10)
	    {
	    	   return "checkCompatibility";		
	    }*/
	    
	  //Firefox compatibility
	    else if(browserName.contains("Firefox") && vno < 57)
	    {
	    	   return "checkCompatibility";		
	    }
	    
	    //Safari compatibility
	    else if(browserName.contains("Safari") && vno < 10)
	    {
	    	   return "checkCompatibility";		
	    }
	    
	    //opera compatibility
	    else if(browserName.contains("Opera") && vno < 10)
	    {
	    	   return "checkCompatibility";		
	    }
	    else {
	    	try {
			    String username1= URLDecoder.decode(user, "UTF-8");
			   // username1=username1.replace("\\s", "+");
			    System.out.println("decoded: "+username1);
			//	String username=encryption.decrypt(user,"");
				String username=AES.decrypt(user,"");
				/*username=username.contains("+") ? username.replaceAll("+", "%2B") : username;*/
				System.out.println("decrypted: "+username);
				MTEmployee e=employeeDao.findOne("emailId='"+username+"' OR empId='"+username+"'");
 				String userName=e.getEmailId();
				MTSurvey survey= statusService.getSurveyIDUser(e.getYear());
				Date endDate=survey.getEndDate();
				Date date=new Date();
				System.out.println(date);
				
				if (endDate.compareTo(date)<0) {
					model.put("surveyEnd", "yes");
					return "login";
				}
					MTResponse mtResponse=mtResponseDao.findOne("mtEmployee.pkEmp='"+e.getPkEmp()+"'");
					if(mtResponse!=null)
					{
						 if(mtResponse.getStatus().equals("completed"))
						 {
							model.put("completed", "yes");
							return "login";
								
						 }
					}
					String password=e.getPassword();
					/*UsernamePasswordAuthenticationToken authReq
				      = new UsernamePasswordAuthenticationToken(username, password);*/
				    Authentication auth = customAuthProvider.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
				     
				     /*if(auth.isAuthenticated())
				     {*/
				    	 SecurityContext sc = SecurityContextHolder.getContext();
				 	    sc.setAuthentication(auth);
				 	    HttpSession session = req.getSession(true);
				 	    session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
				 	  
						loginHandler.handle(req, res, auth);
				
				
				 
				}
				catch(Exception e)
				{
					model.put("invalidLink", "Please use the link provided in your mail to fill the survey!");
					e.printStackTrace();
					return "login";
				}
			 	  
		    
	    	
		     return null;
	    }
	   
	    
		
		
	}
	/*
	@RequestMapping(value = { "/urlLogin"}) 
	public  String checkCompatibility(HttpServletRequest request, @RequestParam("username") String user,Map<String, Object> m)  { 
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
	    Browser browser = userAgent.getBrowser();

	    String browserName = browser.getName();
	    //or 
	    // String browserName = browser.getGroup().getName();
	    Version browserVersion = userAgent.getBrowserVersion();
	    System.out.println("The user is using browser " + browserName + " - version " + browserVersion);
   
		m.put("user", user);
		return "checkCompatibility";
	   
	    
	}*/
	
	
	
}
