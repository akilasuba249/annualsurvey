package com.mahindra.annualsurvey.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

/**
 * A <code>RequestDataValueProcessor</code> that pushes a hidden field with a CSRF token into forms.
 * This process implements the {@link #getExtraHiddenFields(HttpServletRequest)} method to push the
 * CSRF token obtained from {@link CSRFTokenManager}. To register this processor to automatically process all
 * Spring based forms register it as a Spring bean named 'requestDataValueProcessor' as shown below:
 * <pre>
 *  &lt;bean name="requestDataValueProcessor" class="com.eyallupu.blog.springmvc.controller.csrf.CSRFRequestDataValueProcessor"/&gt;
 * </pre>
 * @author Eyal Lupu
 *
 */
public class CSRFRequestDataValueProcessor implements RequestDataValueProcessor {

	public static final Logger LOG= Logger.getLogger(CSRFRequestDataValueProcessor.class);
	/*@Override
	public String processAction(HttpServletRequest request, String action) {
		return action;
	}*/

	public String processFormFieldValue(HttpServletRequest request, String name, String value, String type) {
		System.out.println("inside processFormFieldValue method:");
		LOG.info("inside processFormFieldValue method:");
		System.out.println("Field value:"+value);
		LOG.info("Field value:"+value);
		return value;
	}

	public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
		System.out.println("inside getExtraHiddenFields method:");
		LOG.info("inside getExtraHiddenFields method:");
		Map<String, String> hiddenFields = new HashMap<String, String>();
		hiddenFields.put(CSRFTokenManager.CSRF_PARAM_NAME, CSRFTokenManager.getTokenForSession(request.getSession()));
		return hiddenFields;
	}

	public String processUrl(HttpServletRequest request, String url) {
		System.out.println("inside processUrl method:");
		LOG.info("inside processUrl method:");
		return url;
	}

	public String processAction(HttpServletRequest request, String action, String arg2) {
		System.out.println("inside processAction method:");
		// TODO Auto-generated method stub
		return action;
	}
	
}
