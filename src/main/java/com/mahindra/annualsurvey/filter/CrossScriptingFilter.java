package com.mahindra.annualsurvey.filter;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CrossScriptingFilter implements Filter {

	private String mode = "DENY";

	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		String configMode = filterConfig.getInitParameter("mode");
		if (configMode != null) {
			mode = configMode;
		}

	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest httpReq = (HttpServletRequest) request;
		// Check the user session for the salt cache, if none is present we
		// create one
		
		Map m = httpReq.getParameterMap();
		Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) httpReq.getSession().getAttribute("csrfPreventionSaltCache");

		if (csrfPreventionSaltCache == null) {
			csrfPreventionSaltCache = CacheBuilder.newBuilder().maximumSize(5000).expireAfterWrite(20, TimeUnit.MINUTES).build();

			httpReq.getSession().setAttribute("csrfPreventionSaltCache", csrfPreventionSaltCache);
		}

		// Generate the salt and store it in the users cache
		String salt = RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
		csrfPreventionSaltCache.put(salt, Boolean.TRUE);

		// Add the salt to the current request so it can be used
		// by the page rendered in this request
		httpReq.setAttribute("csrfPreventionSalt", salt);
		String path = ((HttpServletRequest) request).getRequestURI();
		// res.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT,
		// UPDATE, OPTIONS");
		res.addHeader("Server", "xyz");
	
		res.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
		res.addHeader("Pragma", "no-cache"); 
		res.addDateHeader ("Expires", 0);
		
		
	/*	 if(path.contains("/j_spring_security_check"))
		{
			HttpSession session1 = httpReq.getSession(true);
			String tokenId=(String)session1.getAttribute("CAPTCHA");
			session1.setAttribute("CAPTCHA", "");
			if(tokenId.equals(request.getParameter("j_captcha")))
			{
			chain.doFilter(httpReq, response);
			res.addHeader("X-Powered-By","");
			}
			else
			{
				session1.invalidate();
				res.sendRedirect("login?captchaerror");
			}	
		}	else if (!path.startsWith("/saml")) {
			HttpSession session = httpReq.getSession();

			// System.out.println(session.getAttribute(CommonConstants.SESSION_EMP_OBJ));
			RequestWrapper requestwrap = new RequestWrapper((HttpServletRequest) request);
			chain.doFilter(requestwrap, response);
			res.addHeader("X-Powered-By","");
			}
		else{
			
			chain.doFilter(httpReq, response);

			res.addHeader("X-Powered-By","");
			
		}*/
		 
		 String pass = request.getParameter("j_password");
		/*if (path.contains("/j_spring_security_check")) {
			
			if (!("sso".equalsIgnoreCase(pass))) {

				try {
					HttpSession session = httpReq.getSession();
					String tokenId = (String) session.getAttribute("CAPTCHA");
					System.out.println("Tokan Id Captcha: "+tokenId);
					System.out.println("Session request Captcha:"+request.getParameter("j_captcha"));
					session.setAttribute("CAPTCHA", "");
					if (tokenId.equals(request.getParameter("j_captcha"))) {
						chain.doFilter(httpReq, response);
						res.addHeader("X-Powered-By", "");
					} else {
						session.invalidate();
						res.sendRedirect("login?captchaerror");
					}
				} catch (Exception e) {
					e.printStackTrace();
					res.sendRedirect("login?captchaerror");
				}
			}else{
				chain.doFilter(request, response);
				res.addHeader("X-Powered-By", "");
			}

		} else {*/
			chain.doFilter(request, response);
			res.addHeader("X-Powered-By", "");
		}
	}
/*}*/