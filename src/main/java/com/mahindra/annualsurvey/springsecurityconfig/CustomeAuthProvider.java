package com.mahindra.annualsurvey.springsecurityconfig;

import java.net.PasswordAuthentication;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.mahindra.annualsurvey.filter.PasswordPolicyFilter;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTLoginHistory;
import com.mahindra.annualsurvey.services.MTEmployeeService;

@Component
public class CustomeAuthProvider implements AuthenticationProvider{
	
	@Autowired
	MTEmployeeService loginService;
	
	@Autowired
	PasswordPolicyFilter passfilter;
	
    

	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		
	
		System.out.println("Authentication handler==>"+username+"------------"+password);
		MTEmployee mtLogin = loginService.checkLoginExternal(username,password);
		if((mtLogin.getUserAttempts() == null) && (mtLogin.getUsertype().contains("USER"))) {
			loginService.firstTimeLogin(mtLogin);	
		}
		
		/** Password Authentication filter **/
		/*if(!passfilter.passwordValidation(password)){
			System.out.println("Password not strong/ Password Wrong");
		}*/
		
		
		if(mtLogin!=null){
			CustomUser loginDto = new CustomUser();
			loginDto.setAccountNonExpired(false);
			loginDto.setAccountNonLocked(false);
			loginDto.setCredentialsNonExpired(false);
			loginDto.setEnabled(true);
			loginDto.setUsername(username);
			loginDto.setPassword(password);
			String[] s =mtLogin.getUsertype().split(",");
			List<Role> roleList = new LinkedList<Role>();
			for (int i = 0; i < s.length; i++) {
				Role r = new Role();
	            r.setName("ROLE_"+s[i]);
	            roleList.add(r);
			}
			loginDto.setAuthorities(roleList);
			Collection<? extends GrantedAuthority> authorities = roleList;
			return new UsernamePasswordAuthenticationToken(loginDto, password, authorities);
		}else{
			throw new BadCredentialsException("Invalid username or password");	
		}
		
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
