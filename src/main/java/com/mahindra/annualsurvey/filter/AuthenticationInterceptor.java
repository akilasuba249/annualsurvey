package com.mahindra.annualsurvey.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter 
{
	public static final Logger logger = Logger.getLogger(AuthenticationInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        
        if(request.getSession(true).getAttribute("username") == null ){
//        	response.sendRedirect("/msquare/");
        	request.getRequestDispatcher("/").forward(request, response);
        	logger.info("... Pre Handle ..... Interceptor ");
        	return false;
        }
        //if returned false, we need to make sure 'response' is sent
        //response.sendRedirect("admin");
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

}