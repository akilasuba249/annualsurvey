package com.mahindra.annualsurvey.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLStreamException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onelogin.saml.AuthRequest;
import com.onelogin.saml.Response;

import one.login.AccountSettings;
import one.login.AppSettings;

@Controller
public class SSOController {

	@RequestMapping(value = "/saml", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, XMLStreamException, IOException {
		// System.out.println("Home url........");
		System.out.println("+++++++++++++++++++++++Into SAML+++++++++++++++++++++++");
		AppSettings appSettings = new AppSettings();

		// set the URL of the consume.jsp (or similar) file for this app. The
		// SAML Response will be posted to this URL
		appSettings.setAssertionConsumerServiceUrl("https://annualsurvey.mahindra.com/annualsurvey/adfslogin");

		// set the issuer of the authentication request. This would usually be
		// the URL of the issuing web application
		appSettings.setIssuer("https://annualsurvey.mahindra.com/annualsurvey/");

		// the accSettings object contains settings specific to the users
		// account.
		// At this point, your application must have identified the users origin
		AccountSettings accSettings = new AccountSettings();

		// The URL at the Identity Provider where to the authentication request
		// should be sent
		accSettings.setIdpSsoTargetUrl("https://sts.mahindra.com/adfs/ls/");

		// Generate an AuthRequest and send it to the identity provider
		AuthRequest authReq = new AuthRequest(appSettings, accSettings);

		// Get RelayState
		Map<String, String[]> parameters = request.getParameterMap();
		String relayState = null;
		for (String parameter : parameters.keySet()) {
			if (parameter.equalsIgnoreCase("relaystate")) {
				String[] values = parameters.get(parameter);
				relayState = values[0];
			}
		}
		String reqString = authReq.getSSOurl(relayState, "p");
		response.sendRedirect(reqString);

		return null;
	}

	@RequestMapping(value = "/adfslogin")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model, Model m, HttpSession session) throws Exception {
		System.out.println("login ADFS SSO ------------------");

		/*
		 * String certificateS =
		 * "MIIC3DCCAcSgAwIBAgIQF0JFnj4M1pNGR+uUM0mbmjANBgkqhkiG9w0BAQsFADAq" +
		 * "MSgwJgYDVQQDEx9BREZTIFNpZ25pbmcgLSBzdHMubWFoaW5kcmEuY29tMB4XDTE2" +
		 * "MDYwMjEwMTI1N1oXDTE3MDYwMjEwMTI1N1owKjEoMCYGA1UEAxMfQURGUyBTaWdu" +
		 * "aW5nIC0gc3RzLm1haGluZHJhLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCC" +
		 * "AQoCggEBAJg2Qs+n54qLTQzLE9q/JwzOuweHcGhEnEFnvNxmM1OXAsDni9FlMGyj" +
		 * "tTzVylfheZivVPCm8kXk8+cfSEgFLTEnKUDUsJfAfTY3xmqbh0G08QLXLkg1AiDc" +
		 * "TWJHwFWwnk9NNhwZyK4gYrvNcIVSl20SHTtMqHr0Isgevm9emjpJfu32Dn4um16o" +
		 * "tMdSyBto8l6mg3top2ARiw7nOwZ7nsF/gk6IneKuXzcHCD/sFl7AP/k9frtvdzCH" +
		 * "w9zOk25HsCIb7Rf3XxI7Wvqhml6sPEvGmbbxHsixYRW0zBkHFosRQ8jpsI2cDkws" +
		 * "O9xsOC1f7lTzko3Z++BehXhj2dfQc/0CAwEAATANBgkqhkiG9w0BAQsFAAOCAQEA" +
		 * "IYBr6GWGmtuyLnwFM+e8BeMHOTz7CiWyakOa6KoECvbQaJxtw+2rMGy5sy6oa0BB" +
		 * "XNNdMYNzPgRR5/LrcJmoF+XNLz7TkoxmkTsFggMgzJaDvrW4Pd6ER3dcUIDi+5Cu" +
		 * "Vj7Emq9KcIa8yIAogmY9x92B+ucJNg383IwquliWWhuiVlAjNc6yEiARkMitIz8M" +
		 * "HbD3J4x77QH1vhr5jKGxGNwLSgw1vnrz28lhPRPvfQolq7XupxEM9yx11YMVtjCB" +
		 * "+qPkbBuQOwzOE8924k1AdeeABWfihRfGfBNDbKftbHzTgwIajIpuJJ1D+ozWZ4wu" +
		 * "JgS1W1xXQRN5MLMOZftBAg==";
		 */

		String certificateS = "MIIC3DCCAcSgAwIBAgIQEnpuZKTJGYNPpuno1Rl3ZTANBgkqhkiG9w0BAQsFADAq"
				+ "MSgwJgYDVQQDEx9BREZTIFNpZ25pbmcgLSBzdHMubWFoaW5kcmEuY29tMB4XDTE3"
				+ "MDUxMzExMzQxNFoXDTE4MDUxMzExMzQxNFowKjEoMCYGA1UEAxMfQURGUyBTaWdu"
				+ "aW5nIC0gc3RzLm1haGluZHJhLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCC"
				+ "AQoCggEBAOkOZxGPfJ4Sk8K+QP1yZ+1AtmA+7PRw/NC3eo3mn336Bw+t94J0iJ1u"
				+ "kBJ0nOnCpGT3OFxqI2HNsQEjaM24963r7Naw6ZSLsDg5OglY6PglbbGawgs0tLvm"
				+ "hr0wl1FsXbk719ILEcaWUfYx2Ne2AKCwA0tzYatDuL2Oq+TJbqybNPHM3aWRD/b9"
				+ "rKxm3YFr6moVfPt779gt62UxEav+SnXqdLG5DsliHQpLUclwdelNEGv8XNwuH1V1"
				+ "Kko/YQLIvv8fXRCPlaomp7sHakJunsSELMVtaJhsGwkocD9HWvQKzeYkuinp6q4x"
				+ "tJZOFuQ6g+RPRBOr1ObtKSCbQ31660ECAwEAATANBgkqhkiG9w0BAQsFAAOCAQEA"
				+ "gO/xT9pkbqU1CmyaFyqe7Co0Swp8wlbe3zjf6nkdjOMUmn1iP49WOtJ9cJFX//eU"
				+ "jRCASLJmr5LnLoacSdXYIe23hWJXTprD0daNXMxKMOlGnnGGsuHIkSX/0sBx/J5a"
				+ "a+s7bIMsRZ88l0RflqmdLX95WDFGKywwCGnqRiebeQKlw/MTKJGWJp3EoaCWy7Oh"
				+ "gazZ+uho0eulSf5CYRK9WD23EK0UStc4Qp8ovIYhU0+ZV2eUtFS49NAjPEvV17wj"
				+ "L2IXj1/z4dRmmtGE3nzHeEb01pJepQe6HBTyBhGLRwwQ0Hr9DAPEwnC2BAv6A4AE" + "YxNp13vOvZzdYkarhqBXgA==";

		// user account specific settings. Import the certificate here
		AccountSettings accountSettings = new AccountSettings();
		accountSettings.setCertificate(certificateS);

		if (request.getParameter("SAMLResponse") == null) // yash
		{
			return "redirect:login?type=nonsso";
		}

		Response samlResponse = new Response(accountSettings, request.getParameter("SAMLResponse"),
				request.getRequestURL().toString());

		// user account specific settings. Import the certificate here
		String nameId = samlResponse.getNameId();
		
		String attribute = samlResponse.getNameId1();//witten by vaibhav
		
		System.out.println(nameId + "|||||||||||||||||||||||||||||||>>>>>>>>>>>>>>>>>>>>>>>>");
		// String nameId = "test";
		
		System.out.println(attribute+" ---UPN------attribute");

		if (attribute == null || attribute.equalsIgnoreCase("test")) {
			AppSettings appSettings = new AppSettings();

			// set the URL of the consume.jsp (or similar) file for this app.
			// The SAML Response will be posted to this URL
			appSettings.setAssertionConsumerServiceUrl("https://annualsurvey.mahindra.com/annualsurvey/adfslogin");

			// set the issuer of the authentication request. This would usually
			// be the URL of the issuing web application
			appSettings.setIssuer("https://annualsurvey.mahindra.com/annualsurvey/");

			// the accSettings object contains settings specific to the users
			// account.
			// At this point, your application must have identified the users
			// origin
			AccountSettings accSettings = new AccountSettings();

			// The URL at the Identity Provider where to the authentication
			// request should be sent
			accSettings.setIdpSsoTargetUrl("https://sts.mahindra.com/adfs/ls/");

			// Generate an AuthRequest and send it to the identity provider
			AuthRequest authReq = new AuthRequest(appSettings, accSettings);

			// Get RelayState
			Map<String, String[]> parameters = request.getParameterMap();
			String relayState = null;
			for (String parameter : parameters.keySet()) {
				if (parameter.equalsIgnoreCase("relaystate")) {
					String[] values = parameters.get(parameter);
					relayState = values[0];
				}
			}
			String reqString = authReq.getSSOurl(relayState, "t");
			response.sendRedirect(reqString);
		}
		/*System.out.println(nameId);
		model.put("tokenId", nameId);
		if (nameId == null) {
			return "redirect:login?type=nonsso";
		}*/
		//by vaibhav start
		
		System.out.println(attribute+"++++++++++UPN++++++++++++");
		if (attribute == null) {
			return "redirect:login?type=nonsso";
		}
		//by vaibhav end
		m.addAttribute("tokenId", nameId);  // sso changes
		
		m.addAttribute("attribute", attribute);
		

		return "ssoLogin";

	}

}
