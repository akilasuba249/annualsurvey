/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahindra.MailClient.Client.MailClient;
import com.mahindra.MailClient.CustomException.CustomException;
import com.mahindra.MailClient.DTO.Authentication;
import com.mahindra.annualsurvey.constants.MailConstants;
import com.mahindra.annualsurvey.controllers.AreaController;
import com.mahindra.annualsurvey.dao.StatusDAO;
import com.mahindra.annualsurvey.dto.AreaDTO;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.dto.MailDTO;
import com.mahindra.annualsurvey.model.MTArea;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTResponse;
import com.mahindra.annualsurvey.model.MTResponseDetails;
import com.mahindra.annualsurvey.model.MTMail;
import com.mahindra.annualsurvey.model.MTSurvey;

import com.mahindra.annualsurvey.utils.GenrateRandomPassword;
import com.mahindra.annualsurvey.utils.PasswordGenSSOdomainCheck;
import com.mahindra.annualsurvey.utils.AES;
/**
 * @author Yash Mahale;Service Implementation to get Employee Survey statues
 * 
 */
@Service
public class StatusServiceImpl implements StatusService {
	public static final Logger logger = Logger.getLogger(StatusServiceImpl.class);

	@Autowired
	private StatusDAO statusDAO;

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Autowired
	private AES encryption;
	
	@Autowired
	private PasswordGenSSOdomainCheck passwordGenSSOdomainCheck;

	private static SecureRandom random = new SecureRandom();

	@Override
	public List<EmployeeDTO> getEmployeeStatus(String year) {
		// TODO Auto-generated method stub
		String query = "Select distinct mtemp,resp from MTEmployee mtemp left outer JOIN FETCH  mtemp.mtResponse resp"
                + " where mtemp.year= "+year+" and ( resp.status  is null or (resp.status in('progress','completed') and resp.year<="+year +")) and mtemp.isActive='Y' and mtemp.usertype='USER' order by resp.responseId ";
		
		logger.info("QUERY FOR GETTING EMPLOYEES FOR TRACKING IS "+query);
		List<MTEmployee> empListStatus = statusDAO.getEmployeesForTracking(query);
		logger.info("SIZE OF THE LIST OF EMPLOYEES IS "+empListStatus.size());
		
		List<EmployeeDTO> employeeDTOList =convertToDTO(empListStatus);
		for(EmployeeDTO empDTO:employeeDTOList){
			logger.info("-------------------------------------------------------------");
			logger.info("Employee ID is "+empDTO.getEmpId());
			logger.info("Employee Name is "+empDTO.getfName());
			logger.info("Employee Status is	"+empDTO.getStatus());
			logger.info("-------------------------------------------------------------");
		}
		return employeeDTOList;
	}

	@Override
	public int getSurveyID(String year) {
		logger.info("Into Status Service Impl for getting survey ID for year "+year);
		String query = "select mt.surveyId from MTSurvey mt where year ="+year;
		int surveyID = statusDAO.getSurveyID(query);
		return surveyID;
	}
	
	
	@Override
	public MTSurvey getSurveyIDUser(String year) {
		logger.info("Into Status Service Impl for getting survey ID for year "+year);
		String query = " from MTSurvey mt where year ="+year;
		MTSurvey surveyID =statusDAO.getSurveyIDUser(query);
		
		return surveyID;
	}

	private List<EmployeeDTO> convertToDTO(List<MTEmployee> mtemployees) {
		List<EmployeeDTO> employeeDTOS = new ArrayList<EmployeeDTO>();
		if (mtemployees != null) {
			for (MTEmployee mtEmployee : mtemployees) {
				EmployeeDTO employeeDTO = new EmployeeDTO();
				employeeDTO.setEmpId(mtEmployee.getEmpId());
				employeeDTO.setfName(mtEmployee.getfName());
				employeeDTO.setlName(mtEmployee.getlName());
				employeeDTO.setPkEmp(mtEmployee.getPkEmp());
				employeeDTO.setEmailId(mtEmployee.getEmailId());
				employeeDTO.setSapFlag(mtEmployee.getSapFlag());
				employeeDTO.setPassword(mtEmployee.getPassword());
				
				if(mtEmployee.getMtResponse().size()!=0)
				{
					if(MailConstants.year.equalsIgnoreCase(mtEmployee.getMtResponse().get(mtEmployee.getMtResponse().size()-1).getYear()))
					{
						employeeDTO.setStatus(mtEmployee.getMtResponse().get(mtEmployee.getMtResponse().size()-1)
								.getStatus());
						logger.info("....."+employeeDTO.getStatus());
						employeeDTOS.add(employeeDTO);
					}
					else
					{
						employeeDTO.setStatus("notStarted");
						employeeDTOS.add(employeeDTO);
					}	
					
				}
				else{
					employeeDTO.setStatus("notStarted");
					employeeDTOS.add(employeeDTO);
				}
			}
		}

		return employeeDTOS;
	}

	
	//reminder mails start below
	@Override
	public void sendReminderToRespondents(ArrayList<EmployeeDTO> employeeListForMail, String year) {
		
		Iterator<EmployeeDTO> itr1 = employeeListForMail.iterator();
		EmployeeDTO employee = null;
		while (itr1.hasNext()) {
			employee =(EmployeeDTO) itr1.next();
			String sapFlag="";
			sapFlag = employee.getSapFlag();
			//if(passwordGenSSOdomainCheck.ssodomain(employee.getEmailId())){
				if(sapFlag.equals("Y")){
					logger.info("SAP EMPLOYEE, SENDING MAHINDRA MAIL");
					String query = "from MTMail mtmail where mtmail.year ="+year+"and mtmail.mailType='reminder_mnm'";
					MTMail mailDetails = statusDAO.sendEmail(query);
					String mailBody = mailDetails.getMailBody();
					String Salutation = mailDetails.getSalutation();
					//String encryptedContent=encryption.encrypt("username="+employee.getEmailId()+"password="+employee.getPassword());
					// commenting below code and putting the entire mail body in the DB
                 String encryptedContent=encryption.encrypt(employee.getEmailId(),"key123key123key1");
					
					System.out.println("encrypt :"+encryptedContent);
					try {
						String encodedUsername=URLEncoder.encode(encryptedContent, "UTF-8");
						mailBody=mailBody.replace("<param6>",encodedUsername);
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				//	mailSalutation=mailSalutation.replace("<param1>","Yash");
					//mailBody=mailBody.replace("<param1>", MailConstants.REMINDER_PARAM_1);
					//mailBody=mailBody.replace("<param2>", MailConstants.REMINDER_PARAM_2);
					//mailBody=mailBody.replace("<param3>", MailConstants.LINK_FOR_MNM);
					
					//mailBody=mailBody.replace("<param6>", encryptedContent);
					/*mailBody=mailBody.replace("<param1>", MailConstants.REMINDER_PARAM_2);
					mailBody=mailBody.replace("<param2>", MailConstants.REMINDER_PARAM_3);*/
					
					logger.info("");
					logger.info("***************************REMINDER MAIL STARTS***************************");
					logger.info(mailDetails.getMailSubject());
					logger.info("");
					logger.info(Salutation);
					logger.info("");
					logger.info(mailBody);
					logger.info("");
					logger.info(mailDetails.getClosing());
					logger.info(mailDetails.getSignature());
					logger.info("****************************REMINDER MAIL ENDS****************************");
					logger.info("");
					
					//---------------------------------------Converting Model to DTO---------------------------------------
					
					MailDTO mailDTO = new MailDTO();
					mailDTO.setMailSubject(mailDetails.getMailSubject());
					mailDTO.setSalutation(mailDetails.getSalutation());
					mailDTO.setMailBody(mailBody);
					mailDTO.setClosing(mailDetails.getClosing());
					mailDTO.setSignature(mailDetails.getSignature());
					
					//-----------------------------------------Mail to Employees Start-----------------------------------------
					
						HashMap<String, String> mailDetailsMap = new HashMap<String, String>();
						
						String fullName =employee.getFullName();
						String[] array = fullName.split("\\s+");
						String firstName = array[0];
						firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
						
						logger.info("FIRST NAME FOR MAIL IS "+firstName);
						String subject = mailDTO.getMailSubject();
						String mailSalutation=mailDTO.getSalutation().replace("<param1>",firstName);//random
						
						String msgBody = mailDTO.getMailBody().replace("<param3>", MailConstants.LINK_FOR_MNM).replace("<param2>", MailConstants.REMINDER_PARAM_2).replace("<param1>", MailConstants.REMINDER_PARAM_1);
						String closing = mailDTO.getClosing();
						String signature = mailDTO.getSignature();
						
						String space = "<br>";
						msgBody = mailSalutation + space+space+msgBody+space+space+closing+space+signature;
						
						logger.info("++++++++++++++++++++++++++Final Mail Body is++++++++++++++++++++++++++");
						logger.info(msgBody);
						
						mailDetailsMap.put("msgSub", subject);
						mailDetailsMap.put("salutation", mailSalutation);
						mailDetailsMap.put("msgBody", msgBody);
						mailDetailsMap.put("closing", closing);
						mailDetailsMap.put("signature", signature);
						
						try {
							//setSystemPropertiesforMahindraSMTP(employee, mailDetailsMap);
							setSystemPropertiesforFalconideSMTP(employee, mailDetailsMap);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					
				//}
			}
			else{
				logger.info("Non-Mahindra Employee, sending Non Mahindra Mail");
				String query = "from MTMail mtmail where mtmail.year ="+year+"and mtmail.mailType='reminder_nonmnm'";
				MTMail mailDetails = statusDAO.sendEmail(query);
				String mailBody = mailDetails.getMailBody();
				String Salutation = mailDetails.getSalutation();
				
				String encryptedContent=encryption.encrypt(employee.getEmailId(),"key123key123key1");
				
				System.out.println("encrypt :"+encryptedContent);
				try {
					String encodedUsername=URLEncoder.encode(encryptedContent, "UTF-8");
					mailBody=mailBody.replace("<param6>",encodedUsername);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			//	mailSalutation=mailSalutation.replace("<param1>","Yash");
				//mailBody=mailBody.replace("<param1>", MailConstants.REMINDER_PARAM_1);
				//mailBody=mailBody.replace("<param2>", MailConstants.REMINDER_PARAM_2);
				//mailBody=mailBody.replace("<param3>", MailConstants.REMINDER_PARAM_3);
				
				logger.info("");
				logger.info("***************************REMINDER MAIL STARTS***************************");
				logger.info(mailDetails.getMailSubject());
				logger.info("");
				logger.info(Salutation);
				logger.info("");
				logger.info(mailBody);
				logger.info("");
				logger.info(mailDetails.getClosing());
				logger.info(mailDetails.getSignature());
				logger.info("****************************REMINDER MAIL ENDS****************************");
				logger.info("");
				
				//---------------------------------------Converting Model to DTO---------------------------------------
				
				MailDTO mailDTO = new MailDTO();
				mailDTO.setMailSubject(mailDetails.getMailSubject());
				mailDTO.setSalutation(mailDetails.getSalutation());
				mailDTO.setMailBody(mailBody);
				mailDTO.setClosing(mailDetails.getClosing());
				mailDTO.setSignature(mailDetails.getSignature());
				
				//-----------------------------------------Mail to Employees Start-----------------------------------------
				
				
					HashMap<String, String> mailDetailsMap = new HashMap<String, String>();
					
					String fullName =employee.getFullName();
					String[] array = fullName.split("\\s+");
					String firstName = array[0];
					firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
					
					logger.info("FIRST NAME FOR MAIL IS "+firstName);
					String subject = mailDTO.getMailSubject();
					String mailSalutation=mailDTO.getSalutation().replace("<param1>",firstName);
					String msgBody = mailDTO.getMailBody().replace("<param1>",MailConstants.REMINDER_PARAM_1).replace("<param2>", MailConstants.REMINDER_PARAM_2).replace("<param3>", MailConstants.REMINDER_PARAM_3).replace("<param5>",employee.getPassword() ).replaceAll("#EmailID#", employee.getEmailId());
					String closing = mailDTO.getClosing();
					String signature = mailDTO.getSignature();
					
					String space = "<br>";
					msgBody = mailSalutation + space+space+ msgBody + space+closing+space+signature;
					
					logger.info("++++++++++++++++++++++++++Final Mail Body is++++++++++++++++++++++++++");
					logger.info(msgBody);
					
					mailDetailsMap.put("msgSub", subject);
					mailDetailsMap.put("salutation", mailSalutation);
					mailDetailsMap.put("msgBody", msgBody);
					mailDetailsMap.put("closing", closing);
					mailDetailsMap.put("signature", signature);
					
					try {
						//setSystemPropertiesforMahindraSMTP(employee, mailDetailsMap);
						setSystemPropertiesforFalconideSMTP(employee, mailDetailsMap);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		}
		
		
		
	}     // reminder mail sending ends here 
	
	public void setSystemPropertiesforMahindraSMTP(EmployeeDTO employee, Map<String, String> mailDetails) throws Exception {
		logger.info("SimpleEmail Start");
		/*String emailId = employee.getEmailId();
		String smtpHostServer = "10.2.202.42";
		System.getProperties().put("http.proxyHost", "10.2.152.4");
		System.getProperties().put("http.proxyPort", "80");
		System.getProperties().put("http.proxyUser", "mobcomm");
		System.getProperties().put("http.proxyPassword", "welcome@1");
		
		Session session=null;
        Properties props = System.getProperties();
        props.put("http.proxyHost", "10.2.152.4");
        props.put("http.proxyPort", "80");
        props.put("http.proxyUser", "mobcomm");
        props.put("http.proxyPassword", "welcome@1");
        props.put("mail.smtp.host", "10.2.202.42");
*/
		
		/*Properties props = System.getProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHostServer);
		props.put("mail.smtp.port", "25");
		Session session = Session.getInstance(props, null);*/
		/*session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("mahaya-cont", "mahindra@333");
				}
			});*/
		
		
		//sendEmail(session, employee, mailDetails);
		
		MailClient mailClient = new MailClient(); // new
		com.mahindra.MailClient.DTO.MailDTO mailDTOLatest = new com.mahindra.MailClient.DTO.MailDTO(
				"awaras-cont@ecommunication-mahindra.com",
				employee.getEmailId(), mailDetails.get("msgSub"),
				//mailDetails.get("msgBody"), "SM", "Annual Survey","text/HTML", null, false, null, null);
				mailDetails.get("msgBody"), "SM", "Dr. Prince Augustin","text/HTML", null, false, null, null);
		

		try {
			String status=mailClient.SendMail(new Authentication("ashish", "pass,123"), mailDTOLatest);
			if("OK".equalsIgnoreCase(status)){
				System.out.println("Success");
			}else{
				System.out.println("Error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void setSystemPropertiesforFalconideSMTP(EmployeeDTO employee, Map<String, String> mailDetails) throws Exception {
		logger.info("SimpleEmail Start");
		String emailId = employee.getEmailId();
		/*String smtpHostServer = "10.2.202.42";
		System.getProperties().put("http.proxyHost", "10.2.152.4");
		System.getProperties().put("http.proxyPort", "80");
		System.getProperties().put("http.proxyUser", "mobcomm");
		System.getProperties().put("http.proxyPassword", "welcome@1");*/
		
		/*String smtpHostServer = "smtp.falconide.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpHostServer);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, null);
		session = Session.getInstance(props, new Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("mahindrasamvaad", "Mahindra@123");
			}
		});*/
		
		
		
		MailClient mailClient = new MailClient(); // new
		com.mahindra.MailClient.DTO.MailDTO mailDTOLatest = new com.mahindra.MailClient.DTO.MailDTO(
				"awaras-cont@ecommunication-mahindra.com",
				employee.getEmailId(), mailDetails.get("msgSub"),
				//mailDetails.get("msgBody"), "SM", "Annual Survey","text/HTML", null, false, null, null);
				mailDetails.get("msgBody"), "SM", "Dr. Prince Augustin","text/HTML", null, false, null, null);
		
		

		try {
			String status=mailClient.SendMail(new Authentication("ashish", "pass,123"), mailDTOLatest);
			if("OK".equalsIgnoreCase(status)){
				System.out.println("Success");
			}else{
				System.out.println("Error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			e.printStackTrace();
		} 
		
		
		
		//sendEmail(session, employee, mailDetails);

	}

	private void sendEmail(Session session, EmployeeDTO employee,
			Map<String, String> mailDetails) {
		// TODO Auto-generated method stub
		
		try {

			MimeMessage msg = new MimeMessage(session);
			// set message headers

			msg.setText(mailDetails.get("msgBody"), "UTF-8");
			msg.setSubject(mailDetails.get("msgSub"), "text/HTML");
//			msg.setFrom(new InternetAddress("CorporateLearning@mahindra.com"));
//			msg.setFrom(new InternetAddress("prince.augustin@grouphrannualsurvey.com","Dr. Prince Augustin"));
			msg.setFrom(new InternetAddress("kharde.neha@grouphrannualsurvey.com","Neha Kharde"));
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			// msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			// Address myAddress = new InternetAddress(employee.getEmailId());

			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(employee.getEmailId()));

			msg.setReplyTo(InternetAddress.parse("no_reply@mahindra.com", false));
			msg.setSentDate(new java.util.Date());

			// msg.setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse(toEmail, false));
			logger.info("Mail Subject " + mailDetails.get("msgSub"));
			logger.info("Mail Body " + mailDetails.get("msgBody"));
			logger.info("Message is ready");
    	   //Transport.send(msg);

			logger.info("EMail Sent Successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<MTResponseDetails> getEmpResponse(String surveyId, String pkEmp) {

		String query = "select mt.tblresponsedtls from MTResponse mt where mt.surveyId ="
				+ surveyId + " and mt.mtEmployee.pkEmp =" + pkEmp;

		return  entitymanager.createQuery(query).getResultList();
	}

	@Override
	public void sendMailOnCreate(String year) {
		// TODO Auto-generated method stub
		String query ="select emp from MTEmployee emp where emp.year="+year +"and emp.usertype='USER' ";
		List<MTEmployee> empListModel= entitymanager.createQuery(query).getResultList();
		List<EmployeeDTO> empListDTO = convertToDTO(empListModel);
		sendMailOnCreateToRespondents(empListDTO,year);
		
	}
	
	//@Override
	public void sendMailOnCreateToRespondents(
			List<EmployeeDTO> employeeListForMail, String year) {
		// TODO Auto-generated method stub
		Iterator<EmployeeDTO> itr1 = employeeListForMail.iterator();
		EmployeeDTO employee = null;
		while (itr1.hasNext()) {
			employee =(EmployeeDTO) itr1.next();
			String sapFlag="";
			sapFlag = employee.getSapFlag();
			if(sapFlag.equals("Y")){
				String query = "from MTMail mtmail where mtmail.year ="+year+"and mtmail.mailType='create_survey_mnm'";
				MTMail mailDetails = statusDAO.sendEmail(query);
				String mailBody = mailDetails.getMailBody();
				String Salutation = mailDetails.getSalutation();
				
			//	mailSalutation=mailSalutation.replace("<param1>","Yash");
				/*mailBody=mailBody.replace("<param1>", MailConstants.REMINDER_PARAM_2);
				mailBody=mailBody.replace("<param2>", MailConstants.REMINDER_PARAM_3);*/
				//mailBody=mailBody.replace("<param3>", MailConstants.REMINDER_PARAM_3);
				
				logger.info("");
				logger.info("***************************REMINDER MAIL STARTS***************************");
				logger.info(mailDetails.getMailSubject());
				logger.info("");
				logger.info(Salutation);
				logger.info("");
				logger.info(mailBody);
				logger.info("");
				logger.info(mailDetails.getClosing());
				logger.info(mailDetails.getSignature());
				logger.info("****************************REMINDER MAIL ENDS****************************");
				logger.info("");
				
				//---------------------------------------Converting Model to DTO---------------------------------------
				
				MailDTO mailDTO = new MailDTO();
				mailDTO.setMailSubject(mailDetails.getMailSubject());
				mailDTO.setSalutation(mailDetails.getSalutation());
				mailDTO.setMailBody(mailBody);
				mailDTO.setClosing(mailDetails.getClosing());
				mailDTO.setSignature(mailDetails.getSignature());
				
				//-----------------------------------------Mail to Employees Start-----------------------------------------
				
					HashMap<String, String> mailDetailsMap = new HashMap<String, String>();
					
					//String fullName =employee.getFullName();   //comment
					//String[] array = fullName.split("\\s+");
					//String firstName = array[0];
					//firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
					
					logger.info("FIRST NAME FOR MAIL IS "+employee.getfName());
					String subject = mailDTO.getMailSubject();
					String mailSalutation=mailDTO.getSalutation().replace("<param1>",employee.getfName());
					String msgBody = mailDTO.getMailBody();
					String closing = mailDTO.getClosing();
					String signature = mailDTO.getSignature();
					
					String space = "<br>";
					msgBody = mailSalutation + space+space+ msgBody + space+space+closing+space+signature;
					
					logger.info("++++++++++++++++++++++++++Final Mail Body is++++++++++++++++++++++++++");
					logger.info(msgBody);
					
					mailDetailsMap.put("msgSub", subject);
					mailDetailsMap.put("salutation", mailSalutation);
					mailDetailsMap.put("msgBody", msgBody);
					mailDetailsMap.put("closing", closing);
					mailDetailsMap.put("signature", signature);
					
					try {
						setSystemPropertiesforMahindraSMTP(employee, mailDetailsMap);
					//	setSystemPropertiesforFalconideSMTP(employee, mailDetailsMap);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				
			}
			else{
				logger.info("NON SAP EMPLOYEE, SENDING NON MAHINDRA MAIL");
				String query = "from MTMail mtmail where mtmail.year ="+year+"and mtmail.mailType='create_survey_nonmnm'";
				MTMail mailDetails = statusDAO.sendEmail(query);
				String mailBody = mailDetails.getMailBody();
				String Salutation = mailDetails.getSalutation();
				
				
				mailBody = mailBody.replace("<param4>",employee.getPassword());
				mailBody=mailBody.replace("<param5>",employee.getEmailId());
				
				
			//	mailSalutation=mailSalutation.replace("<param1>","Yash");
				/*mailBody=mailBody.replace("<param1>", MailConstants.REMINDER_PARAM_2);
				mailBody=mailBody.replace("<param2>", MailConstants.REMINDER_PARAM_3);*/
			//	mailBody=mailBody.replace("<param3>", MailConstants.REMINDER_PARAM_3);
				
				logger.info("");
				logger.info("***************************REMINDER MAIL STARTS***************************");
				logger.info(mailDetails.getMailSubject());
				logger.info("");
				logger.info(Salutation);
				logger.info("");
				logger.info(mailBody);
				logger.info("");
				logger.info(mailDetails.getClosing());
				logger.info(mailDetails.getSignature());
				logger.info("****************************REMINDER MAIL ENDS****************************");
				logger.info("");
				
				//---------------------------------------Converting Model to DTO---------------------------------------
				
				MailDTO mailDTO = new MailDTO();
				mailDTO.setMailSubject(mailDetails.getMailSubject());
				mailDTO.setSalutation(mailDetails.getSalutation());
				mailDTO.setMailBody(mailBody);
				mailDTO.setClosing(mailDetails.getClosing());
				mailDTO.setSignature(mailDetails.getSignature());
				
				//-----------------------------------------Mail to Employees Start-----------------------------------------
				
				
					HashMap<String, String> mailDetailsMap = new HashMap<String, String>();
					
					//String fullName =employee.getFullName();
					//String[] array = fullName.split("\\s+");
					//String firstName = array[0];
					//firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
					
					logger.info("FIRST NAME FOR MAIL IS "+employee.getfName());
					String subject = mailDTO.getMailSubject();
					String mailSalutation=mailDTO.getSalutation().replace("<param1>",employee.getfName());
					String msgBody = mailDTO.getMailBody();
					String closing = mailDTO.getClosing();
					String signature = mailDTO.getSignature();
					
				
					String space = "<br>";
					msgBody = mailSalutation + space+space+ msgBody + space+space+closing+space+signature;
					
					logger.info("++++++++++++++++++++++++++Final Mail Body is++++++++++++++++++++++++++");
					logger.info(msgBody);
					
					mailDetailsMap.put("msgSub", subject);
					mailDetailsMap.put("salutation", mailSalutation);
					mailDetailsMap.put("msgBody", msgBody);
					mailDetailsMap.put("closing", closing);
					mailDetailsMap.put("signature", signature);
					
					try {
						setSystemPropertiesforMahindraSMTP(employee, mailDetailsMap);
					//	setSystemPropertiesforFalconideSMTP(employee, mailDetailsMap);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		}
		
		
		
	
}
}
//String output = input.substring(0, 1).toUpperCase() + input.substring(1);