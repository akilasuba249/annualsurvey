package com.mahindra.annualsurvey.filter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

	public static final Logger logger = Logger.getLogger(SessionInterceptor.class);

 
	  @Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
	       
	    	
	    	
	        logger.info("in prehandle");
	 
	        if(request.getSession(false)==null ||request.getSession(false).getAttribute("username") == null ){
	        	logger.info("in if condition");
	        	if (isAJAXRequest(request)) {
	                StringBuilder sb = new StringBuilder();
	      	  sb.append("");
	      	 
	      	  PrintWriter pw = response.getWriter();
	      	  pw.println(sb.toString());
	      	  pw.flush();
	      	  return false;
	      	}
	        	request.getRequestDispatcher("/").forward(request, response);
	        	return false;
	        }
	 
	        //if returned false, we need to make sure 'response' is sent
	        return true;
	    }
	 
	    @Override
	    public void postHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	        
	    	
	        //we can add attributes in the modelAndView and use that in the view page
	    }
	 
	    @Override
	    public void afterCompletion(HttpServletRequest request,
	            HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
	    	
	    }
	    
	    
	    private boolean isAJAXRequest(HttpServletRequest request) {
	  	  boolean check = false;
	  	 
	  	 String isAjax=request.getHeader("x-requested-with");
	  	  if(isAjax != null && isAjax.trim().equalsIgnoreCase("XMLHttpRequest")){
	  	    check = true;
	  	  }
	  	  return check;
	  	}
 
}