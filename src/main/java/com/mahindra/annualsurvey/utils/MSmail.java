package com.mahindra.annualsurvey.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.log4j.Logger;

import javax.activation.*;

public class MSmail
{
	public static final Logger logger = Logger.getLogger(MSmail.class);
	public MSmail(){
		
	}
	
   public static boolean sendEmail(String toUser, String subject, String emailContent)
   {
      
	   try{
	      // Recipient's email ID needs to be mentioned.
	      String to = toUser;	
	      // Sender's email ID needs to be mentioned
	      String from = MConstants.EMAIL_USER_FROM_EMAIL;
	
	      // Assuming you are sending email from localhost
	      String host = MConstants.smtpHost;
	
	      // Get system properties
	      Properties properties = System.getProperties();
	
	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	//      properties.setProperty("mail.user", MConstants.emailUser);
	//      properties.setProperty("mail.password", MConstants.emailPassword);
//	      
//	      properties.put("http.proxyHost", "10.2.152.4");
//	      properties.put("http.proxyPort", "80");
			// props.put("socksProxyHost", "10.2.152.4");
			// props.put("socksProxyPort", "80");
	
//	      properties.put("proxyUser", "mahindra/210613");
//	      properties.put("proxyPassword", "mahindra,123");
	
	      // Get the default Session object.
//	      Session session = Session.getDefaultInstance(properties);
	      
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(MConstants.EMAIL_USER_FROM, MConstants.emailPassword);
				}
			});
	 
	logger.info("Email ...... " + MConstants.EMAIL_USER_FROM + "   " + MConstants.emailPassword + "  FROM .... " 
			+ from + "   TO.... " + toUser);
	      
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	
	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));
	
	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(toUser));  //BIRDI.VIVEK@mahindra.com // dange.micky@mahindra.com
	
	         // Set Subject: header field
	         message.setSubject(subject);
	
	         // Send the actual HTML message, as big as you like
	         message.setContent(emailContent,
	                            "text/html" );
	         logger.info("Sending ....... ");
	         // Send message
//	         Transport.send(message);
	         logger.info("Sent message successfully....");
	         
      }catch (Exception mex) {
         mex.printStackTrace();
         return false;
      }
	   return true;
   }
   
/*   public static void main(String[] args) {
	
	   sendEmail("vivek.birdi@bcone.com", "Notification", MConstants.sampleContent);
   }*/
}