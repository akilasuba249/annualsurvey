package com.mahindra.annualsurvey.filter;

import java.util.List;
import java.util.regex.Pattern;

import org.owasp.esapi.ESAPI;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.stereotype.Component;

@Component
public class MultipartXSSFilter {

	public String cleanXSS(String value) {
		System.out.println("inside cleanXSS method:");
		if (value != null) {

			Policy policy = null;
			CleanResults scanned = null;
			AntiSamy sanitizer = null;

			try {
				policy = Policy.getInstance(RequestWrapper.class .getResourceAsStream("/antisamy-slashdot-1.4.4.xml"));
				sanitizer = new AntiSamy(policy);
				System.out.println("antisamy successfull");

			} catch (PolicyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				scanned = sanitizer.scan(value);
				System.out.println("scan successfull");
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
			//value = ESAPI.encoder().canonicalize(value);
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
				System.out.println("Script pattern find");
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
			 /*  value=HtmlUtils.htmlEscape(value);*/
			   System.out.println(value);
		}
		return value;
	}
}
