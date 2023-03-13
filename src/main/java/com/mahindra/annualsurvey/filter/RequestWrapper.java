package com.mahindra.annualsurvey.filter;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.web.util.HtmlUtils;

public final class RequestWrapper extends HttpServletRequestWrapper {
	// private static Logger logger = Logger.getLogger(RequestWrapper.class);
	public RequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
		System.out.println("HTTP REQUEST WRAPPER IN ANNUAL SURVEY");
	/*   	HttpSession session = servletRequest.getSession(true);									Yash Comment
			String tokenId=(String)session.getAttribute("tokenId");
			if(tokenId!=null)
			servletRequest.setAttribute("hashtokenId", tokenId);*/
		
	}

	public String[] getParameterValues(String parameter) {
		// logger.info("InarameterValues .. parameter .......");
		System.out.println("InarameterValues .. parameter .......");
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	public String getParameter(String parameter) {
		// logger.info("Inarameter .. parameter .......");
		System.out.println("Inarameter .. parameter .......");
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
	//	System.out.println(value);
		// logger.info("Inarameter RequestWrapper ........ value .......");
		return cleanXSS(value);
	}

	public String getHeader(String name) {
		// logger.info("Ineader .. parameter .......");
		System.out.println("Ineader .. parameter .......");
		String value = super.getHeader(name);
		if (value == null)
			return null;
		// logger.info("Ineader RequestWrapper ........... value ....");
		return cleanXSS(value);
	}

	private String cleanXSS(String value) {
		System.out.println("In clean XSS....");
		if (value != null) {

			Policy policy = null;
			CleanResults scanned = null;
			AntiSamy sanitizer = null;

			try {
				policy = Policy.getInstance(RequestWrapper.class
						.getResourceAsStream("/antisamy-slashdot-1.4.4.xml"));
				sanitizer = new AntiSamy(policy);

			} catch (PolicyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				scanned = sanitizer.scan(value);
			} catch (ScanException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PolicyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int errors = scanned.getNumberOfErrors(); // Kural ihlali sayısı
			List<String> errorMsg = scanned.getErrorMessages(); // İhlal
																// nedenleri

			String sanitized = scanned.getCleanHTML();

			if (errors > 0) {
				System.out.println(errors);
				value = "";
			}

			// NOTE: It's highly recommended to use the ESAPI library and
			// uncomment the following line to
			// avoid encoded attacks.
			// value = ESAPI.encoder().canonicalize(value);
			// Avoid null characters
			value = value.replaceAll("", "");
			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid anything in a src='...' type of expression
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid expression(...) expressions
			scriptPattern = Pattern.compile("expression\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("alert[(]",
					Pattern.CASE_INSENSITIVE);

			if (scriptPattern.matcher(value).find()) {
				value = "";
			}

			scriptPattern = Pattern.compile("url[(]", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			if (scriptPattern.matcher(value).find()) {
				value = "";
			}

			// Avoid javascript:... expressions
			scriptPattern = Pattern.compile("javascript:",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid vbscript:... expressions
			scriptPattern = Pattern.compile("vbscript:",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
            value=HtmlUtils.htmlEscape(value);
            System.out.println("value :---"+value);
//            value =  StringEscapeUtils.escapeHtml(value);
//            System.out.println("value :--aa-"+value);
		}
		return value;
	}
}