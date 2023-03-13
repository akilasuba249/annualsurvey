package com.mahindra.annualsurvey.utils;

import java.util.List;
import java.util.UUID;

public class MConstants {

	/* Production params .. */
	public static final String smtpHost = "smtp.falconide.com";
	public static final String emailUser = "msquarealliance@mahindra.com";
	public static final String EMAIL_USER_FROM = "mahindrasamvaad";
	// public static final String
	// EMAIL_USER_FROM_EMAIL="SAMVAAD@MahindraSamvaad.com";
	public static final String EMAIL_USER_FROM_EMAIL = "msquarealliance@mahindramsquarealliance.com";
	//
	public static final String emailPassword = "Mahindra@123"; // msquarealliance@mahindra.com

	
	public static final String smtpPort = "25";
	public static final String sampleContent = "<h>Request Processed successfully<h>";

	public static final String regRequestMailToAdmin = "RRMTA";
	public static final String regApprovalMailToUser = "RAMTU";
	public static final String advisoryRequestMailToAdmin = "ARMTA";
	public static final String opportunityRequestMailToAdmin = "ORMTA";
	public static final String opportunityInterestedMailToAdmin = "OIMTA";
	public static final String registrationRejectedMailToAdmin = "RRMTU";
	public static final String OPPORTUNITY_REQUEST_TO_USER = "ORMTU";

	public static final String FORGET_PASSWORD_EMAIL = "FGTPW";

	
	public static String getRandomPassword() {

		String password = UUID.randomUUID().toString();
		return password.replaceAll("-", "").substring(0, 8);

	}

}
