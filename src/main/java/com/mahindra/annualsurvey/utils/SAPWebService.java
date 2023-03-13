package com.mahindra.annualsurvey.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.mahindra.annualsurvey.dto.TempEmployeeDTO;





public class SAPWebService {
	private final String USER_AGENT = "Mozilla/5.0";

	static final Logger logger = Logger.getLogger(SAPWebService.class);

	public String sendGet(String url) throws Exception {

		logger.info("Inside SAP Call Class");

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		/*// optional default is GET
		System.getProperties().put("http.proxyHost", "10.2.152.4");
		System.getProperties().put("http.proxyPort", "80");
		//
*/		/*Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mahindra\\mobcomm", "welcome@1".toCharArray());
			}
		});
		*/
		con.setRequestMethod("GET");
		con.setConnectTimeout(600 * 1000);
		con.setReadTimeout(600 * 1000);
		// add request header
		
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		logger.info("\nSending 'GET' request to URL : " + url);
		logger.info("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
logger.info("Annual Survey :----------->" + response.toString());
		return response.toString();
	}


	
	
	public HashMap<String, String> getEmpDetails(SAPWebService sap, String empId) throws Exception {
		HashMap<String, String> empDetails = new HashMap<String, String>();

		try {

			// http://10.142.0.105:8000/sap/bc/zsi_mldl_hod?sap-client=220&IV_PERNR=" + name + "&TYPE=REP
//			String str1 = sap.sendGet("http://mkhardev.corp.mahindra.com:8000/sap/bc/zsi_emp_detail?sap-client=220&IV_PERNR="+empId+"&TYPE=DTA");
	//		String str1 = sap.sendGet("http://mkhartst.corp.mahindra.com:8000/sap/bc/zsi_emp_detail?sao-client=220&IV_PERNR="+empId+"&TYPE=DTA");
//			String str1 = sap.sendGet("http://ecc.mahindra.com:8020/sap/bc/zsi_emp_detail?sap-client=500&IV_PERNR="+empId+"&TYPE=DTA");
			//Commented by Rakesh kumar 28-03-2018 Start
			//String str1 = sap.sendGet("http://emss.mahindra.com:8020/sap/bc/zsi_emp_detail?sap-client=500&IV_PERNR="+empId+"&TYPE=DTA&saml2=disabled");
			JSONParser parser = new JSONParser();
			//String str1 = sap.sendGet("http://mivlad1rd2.corp.mahindra.com:8001/sap/bc/zsi_emp_detail1?sap-client=220&IV_PERNR="+empId+"&TYPE=DTA&saml2=disabled");
			String str1 = sap.sendGet("https://emss.mahindra.com/sap/bc/zsi_emp_detail1?sap-client=500&IV_PERNR="+empId+"&TYPE=DTA&saml2=disabled");
			logger.info("result is--------->>" + str1);
			if (str1.startsWith("@IV_PERNR")) {
				empDetails.put("empId", "false");
				logger.info(" =======================Employee Not found at SAP========================== ");
			}
			else {
				
				//String[] reporteeAtr = str1.split("/");
				String[] reporteeAtr=null;
				Gson gson=new Gson();
				TempEmployeeDTO tempemp=gson.fromJson(str1, TempEmployeeDTO.class);
				
				/*empDetails.put("empId", reporteeAtr[0]);
				empDetails.put("firstName", reporteeAtr[1]);
				empDetails.put("lastName", reporteeAtr[2]);
				empDetails.put("emailId", reporteeAtr[3]);
				empDetails.put("companyName", reporteeAtr[4]);
				empDetails.put("level", reporteeAtr[5]);
				empDetails.put("band", reporteeAtr[6]);
				empDetails.put("Something", reporteeAtr[7]);
				empDetails.put("manager", reporteeAtr[8]);
				empDetails.put("managerEmail", reporteeAtr[9]);
				empDetails.put("location", reporteeAtr[10]);
				empDetails.put("age", reporteeAtr[11]);
				empDetails.put("dob", reporteeAtr[12]);
				empDetails.put("gender", reporteeAtr[13]);
				empDetails.put("country", reporteeAtr[14]);
				empDetails.put("hometown", reporteeAtr[15]);
				empDetails.put("sector", reporteeAtr[16]);*/
				
				empDetails.put("empId", tempemp.getEmpid());
				empDetails.put("firstName", tempemp.getFirstname());
				empDetails.put("lastName", tempemp.getLastname());
				empDetails.put("emailId", tempemp.getEmailid());
				empDetails.put("companyName", tempemp.getCompanyname());
				empDetails.put("manager",tempemp.getManagername());
				empDetails.put("managerEmail", tempemp.getManageremail());
				empDetails.put("location", tempemp.getLocationTxt());
				empDetails.put("age", tempemp.getAge());
				empDetails.put("dob", tempemp.getDateOfBirth());
				empDetails.put("gender", tempemp.getGender());
				empDetails.put("country", tempemp.getCountryTxt());
				empDetails.put("hometown", tempemp.getHometown());
				empDetails.put("sector", tempemp.getPersonnelAreaTxt());
			}
			
		} catch (Exception e) {
			logger.error(" =======================Exception at get Employee Details from SAP========================== ");
			e.printStackTrace();
			// return null;
		}
		return empDetails;
	}
}
