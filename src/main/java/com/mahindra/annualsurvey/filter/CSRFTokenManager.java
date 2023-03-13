package com.mahindra.annualsurvey.filter;


import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * A manager for the CSRF token for a given session. The {@link #getTokenForSession(HttpSession)} should used to
 * obtain the token value for the current session (and this should be the only way to obtain the token value).
 * 
 * @author Eyal Lupu
 */
final class CSRFTokenManager {

	/**
	 * The token parameter name
	 */
	static final String CSRF_PARAM_NAME = "CSRFToken";

	/**
	 * The location on the session which stores the token
	 */
	private final static String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenManager.class.getName() + ".tokenval";
	public static final Logger LOG = Logger.getLogger(CSRFTokenManager.class);
	static String getTokenForSession (HttpSession session) {
		LOG.info("IN getTokenForSession...");
		System.out.println("CSRFTokenManager IN getTokenForSession...");
		String token = null;
		// I cannot allow more than one token on a session - in the case of two requests trying to
		// init the token concurrently
		synchronized (session) {
			token = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
			LOG.info("Tokan Value: " +token);
			System.out.println("Tokan Value: "+token);
			if (null==token) {
				token=UUID.randomUUID().toString();
				System.out.println("Token UUID: "+token);
				session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
				
			}
		}
		return token;
	}

	/**
	 * Extracts the token value from the session
	 * @param request
	 * @return
	 */
	static String getTokenFromRequest(HttpServletRequest request) {
		LOG.info("In getTokenFromRequest...");
		return request.getParameter(CSRF_PARAM_NAME);
	}

	private CSRFTokenManager() {};
}
