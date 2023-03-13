package com.mahindra.annualsurvey.filter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * A Spring MVC <code>HandlerInterceptor</code> which is responsible to enforce CSRF token validity on incoming posts requests. The interceptor
 * should be registered with Spring MVC servlet using the following syntax:
 * <pre>
 *   &lt;mvc:interceptors&gt;
 *        &lt;bean class="com.eyallupu.blog.springmvc.controller.csrf.CSRFHandlerInterceptor"/&gt;
 *   &lt;/mvc:interceptors&gt;
 *   </pre>
 * @author Eyal Lupu
 * @see CSRFRequestDataValueProcessor
 *
 */
public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter{
	private static final Logger LOG = Logger.getLogger(CSRFHandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("inside the handler interceptor");
		LOG.info("inside the handler interceptor");
		
		if (!request.getMethod().equalsIgnoreCase("POST")) {        
			 
			// Not a POST - allow the request
			System.out.println("inside the if incoming request is GET request:");
			LOG.info("inside the if incoming request is GET request:");
		
			return true;
		} else {
		
			System.out.println("inside the else incoming request is POST request:");
			LOG.info("inside the else incoming request is POST request:");
			
			// This is a POST request - need to check the CSRF token
			String sessionToken = CSRFTokenManager.getTokenForSession(request.getSession());
			System.out.println("Session Token Value :"+sessionToken);
			LOG.info("Session Token Value :"+sessionToken);
			
			String requestToken = CSRFTokenManager.getTokenFromRequest(request);
			System.out.println("Request Token Value :"+requestToken);
			LOG.info("Request Token Value :"+requestToken);
			
			if (sessionToken.equals(requestToken)) {
			
				System.out.println("session token value:"+requestToken);
				LOG.info("session token value:"+requestToken);
				
				return true;
			} else {
				
				System.out.println("inside error response:");
				LOG.info("inside error response:");  
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
				
				return false;
			}
		}
	}
}
