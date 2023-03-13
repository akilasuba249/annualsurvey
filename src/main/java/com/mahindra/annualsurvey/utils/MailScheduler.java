/**
 * 
 */
package com.mahindra.annualsurvey.utils;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.MailClient.Client.MailClient;//
import com.mahindra.MailClient.DTO.Authentication;// imported jar from ashish project
import com.mahindra.annualsurvey.constants.MailConstants;
import com.mahindra.annualsurvey.dao.StatusDAO;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.dto.MailDTO;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTMail;
import com.mahindra.annualsurvey.model.MTSurvey;
import com.mahindra.annualsurvey.services.MTEmployeeService;
import com.mahindra.annualsurvey.services.StatusService;

/**
 * @author Yash Mahale
 */
/*
 * @Configuration
 * 
 * @EnableAsync
 * 
 * @EnableScheduling
 */
//Mail schedular class
@Component
public class MailScheduler {
	public static final Logger logger = Logger.getLogger(MailScheduler.class);

	@Autowired
	private StatusDAO statusDAO;

	@Autowired
	private AES encryption;
	@Autowired
	private StatusService statusService;

	@PersistenceContext
	private EntityManager entitymanager;

	@Autowired
	MTEmployeeService empservice;

	@Autowired
	private PasswordGenSSOdomainCheck passwordGenSSOdomainCheck;

	//@Scheduled(cron = "0 27 16 1/1 * ?") // second, minute, hour, day of month, month, day(s) of week
	public void sapdataintegration() { //sap services for data integration 
		logger.info("EXECUTING CRON for SAP Employee Data Integration schedular");
		String query = "select survey from MTSurvey survey where startDate > CURRENT_DATE() and year=YEAR(CURRENT_DATE())";
		@SuppressWarnings("unchecked")
		List<MTSurvey> survey = entitymanager.createQuery(query)
				.getResultList();
		if (!survey.isEmpty()) {
			logger.info("Survey found for sap service employee data updation");
			Boolean isSAPDataIntegrated = empservice.sapEmployeeInt();
			if (isSAPDataIntegrated) {

				logger.info("SAP Employee Data Integration SUCCESS");
			} else {
				logger.info("SAP Employee Data Integration FAILED"); //
			}

		}
		logger.info("Survey NOT found for sap service employee data updation"); //
	}

	@Transactional(propagation = Propagation.REQUIRED)
	//@Scheduled(cron = "0 23 12 1/1 * ?") // second, minute, hour, day of month, month, day(s) of week // reminder schedualar
	public void surveyReminder() throws ParseException {

		logger.info("EXECUTING CRON for reminder schedular");

		int year = Calendar.getInstance().get(Calendar.YEAR);

		String querySql = "select emp.email_id,emp.fname,emp.sapFlag,resp.status"
				+ "from "
				+ "mt_employee emp left outer join mt_response resp "
				+ "on emp.pk_emp=resp.pk_emp where"
				+ "emp.year='"
				+ year
				+ "' and"
				+ "(resp.status<>'completed' or resp.status is null) "
				+ "and emp.usertype='USER'";

		logger.info("SQL QUERY FOR GETTING EMPLOYEE LIST IS " + querySql);

		String getSurvey = "select survey from MTSurvey survey where survey.nextDate = curdate() and survey.year="
				+ year;

		List<MTSurvey> mtSurveyList = statusDAO
				.getSurveyForReminderMail(getSurvey);
		logger.info("MT SURVEY LIST " + mtSurveyList.get(0).getSurveyId());
		logger.info("IS EMPTY" + mtSurveyList.isEmpty());
		if (!mtSurveyList.isEmpty()) {
			logger.info("SURVEY FOUND");
			logger.info("SURVEY ID IS " + mtSurveyList.get(0).getSurveyId());

			// TODO Auto-generated method stub
			String query = "Select  mtemp from MTEmployee mtemp where mtemp.usertype='USER'  and year=YEAR(CURRENT_DATE()) AND mtemp.isActive='Y' ";
			// latest update here above
			// insert above pk_emp that are genrated for newly uploaded GCO HR
			// career conversations

			ArrayList<MTEmployee> empListStatus = statusDAO.getEmployeesForReminderMail(query);

			logger.info("SIZE OF THE LIST OF EMPLOYEES IS "+ empListStatus.size()); // should get the number of
												// reminder mails to be
												// triggered

			ArrayList<EmployeeDTO> employeeDTOList = convertToDTO(empListStatus);
			for (EmployeeDTO empDTO : employeeDTOList) {
				// logger.info("-------------------------------------------------------------");
				logger.info("Employee ID is " + empDTO.getEmpId() + "'");
				System.out.println("email ids====>"+empDTO.getEmailId());
				/*
				 * logger.info("Employee Name is " + empDTO.getfName());
				 * logger.info("Employee Status is	" + empDTO.getStatus());
				 * logger.info("Employee Belongs to SAP?"+empDTO.getSapFlag());
				 * logger.info(
				 * "-------------------------------------------------------------"
				 * ); logger.info("");
				 */
			}
			System.out.println("count: "+employeeDTOList.size());
			String yearString = year + "";
			logger.info(employeeDTOList.size()); // should get the number of
													// reminder mails to be
													// triggered
			
			statusService
					.sendReminderToRespondents(employeeDTOList, yearString);

			String getSurveyForChangingNextDate = "select survey from MTSurvey survey where survey.year="
					+ year;
			List<MTSurvey> mtSurveyList1 = statusDAO
					.getSurveyForReminderMail(getSurveyForChangingNextDate);

			Date endDate = mtSurveyList1.get(0).getEndDate();
			Date nextDate = mtSurveyList1.get(0).getNextDate();
			int frequency = mtSurveyList.get(0).getFrequency();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nextDate);
			calendar.add(Calendar.DATE, frequency);
			// java.util.Date nextDate1 = calendar.getTime();
			String NextDateString = new SimpleDateFormat("dd/MM/yyyy")
					.format(calendar.getTime());
			logger.info("Next Date is :" + NextDateString);

			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date convertedDate = df.parse(NextDateString);
			java.sql.Date sqlNextDate = new java.sql.Date(
					convertedDate.getTime());
			logger.info("CONVERTED SQL DATE IS " + sqlNextDate);

			int comparison = sqlNextDate.compareTo(endDate);
			if (comparison == -1) {
				logger.info("END DATE IS " + endDate);
				logger.info("NEXT DATE IS " + nextDate);
				logger.info("Next date is lesser than endDate");
				String updateDateQuery = "Update MTSurvey survey set survey.nextDate= '"
						+ sqlNextDate + "' where survey.year=" + yearString;
				statusDAO.updateNextDate(updateDateQuery);

			} else {
				logger.info("END DATE IS " + endDate);
				logger.info("NEXT DATE IS " + sqlNextDate);
				logger.info("NEXT DATE IS EXCEEDING THE END DATE FOR THE SURVEY!! HENCE NOT UPDATING NEXT DATE");

			}

		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
   //@Scheduled(cron = "0 52 10 1/1 * ?")// second, minute, hour, day of month, month, day(s) of week // basic firsttime mail schedular
	public void surveyCreate() throws ParseException {

		logger.info("EXECUTING CRON for basic first time mails");

		int year = Calendar.getInstance().get(Calendar.YEAR);



		String getSurvey = "select survey from MTSurvey survey where survey.startDate = curdate() and survey.year="
				+ year;

		List<MTSurvey> mtSurveyList = statusDAO
				.getSurveyForReminderMail(getSurvey);
		logger.info("IS EMPTY" + mtSurveyList.isEmpty());
		if (!mtSurveyList.isEmpty()) {
			logger.info("MT SURVEY LIST " + mtSurveyList.get(0).getSurveyId());
			logger.info("SURVEY FOUND");
			logger.info("SURVEY ID IS " + mtSurveyList.get(0).getSurveyId());

			String query = "select emp from MTEmployee emp where emp.year="
					+ year + "and emp.usertype='USER' and emp.isActive='Y'";

			List<MTEmployee> empListModel = entitymanager.createQuery(query)
					.getResultList();
			logger.info("<<<<<<<>>>>>>>>>>" + empListModel.size());
			List<EmployeeDTO> empListDTO = convertToDTO(empListModel);

			for (EmployeeDTO empDTO : empListDTO) {
				logger.info("-------------------------------------------------------------");
				logger.info("Employee ID is " + empDTO.getEmpId());
				logger.info("Employee Name is " + empDTO.getfName());
				logger.info("Employee Status is	" + empDTO.getStatus());
				logger.info("Employee Belongs to SAP?" + empDTO.getSapFlag());
				logger.info("-------------------------------------------------------------");
				logger.info("");
			}
			String yearSend = year + "";
			try {
				sendMailOnCreateToRespondents1(empListDTO, yearSend);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// convert model to DTO
	private ArrayList<EmployeeDTO> convertToDTO(List<MTEmployee> mtemployees) {
		ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<EmployeeDTO>();
		if (mtemployees != null) {
			for (MTEmployee mtEmployee : mtemployees) {
				EmployeeDTO employeeDTO = new EmployeeDTO();
				employeeDTO.setEmpId(mtEmployee.getEmpId());
				employeeDTO.setfName(mtEmployee.getfName());
				employeeDTO.setlName(mtEmployee.getlName());
				employeeDTO.setFullName(mtEmployee.getfName() + " "
						+ mtEmployee.getlName());
				employeeDTO.setPkEmp(mtEmployee.getPkEmp());
				employeeDTO.setEmailId(mtEmployee.getEmailId());
				employeeDTO.setSapFlag(mtEmployee.getSapFlag());
				employeeDTO.setPassword(mtEmployee.getPassword());
				if (mtEmployee.getMtResponse().size() != 0) {
					if (MailConstants.year.equalsIgnoreCase(mtEmployee
							.getMtResponse()
							.get(mtEmployee.getMtResponse().size() - 1)
							.getYear())) {
						employeeDTO.setStatus(mtEmployee.getMtResponse()
								.get(mtEmployee.getMtResponse().size() - 1)
								.getStatus());
						logger.info("....." + employeeDTO.getStatus());
						if (!employeeDTO.getStatus().equalsIgnoreCase(
								"completed")) {
							logger.info("<<<<<completed>>>>>>>>");
							employeeDTOS.add(employeeDTO);
						}
					} else {
						logger.info("<<<<<not started>>>>>>>>");
						employeeDTO.setStatus("notStarted");
						employeeDTOS.add(employeeDTO);
					}

				} else {
					employeeDTO.setStatus("notStarted");
					employeeDTOS.add(employeeDTO);
				}

			}
		}

		return employeeDTOS;
	}

	/*
	 * public void sendMailOnCreateToRespondents( List<EmployeeDTO>
	 * employeeListForMail, String year) { // TODO Auto-generated method stub
	 * 
	 * 
	 * 
	 * String query =
	 * "from MTMail where year ="+year+" and mailType='create_survey'"; MTMail
	 * mailDetails = statusDAO.sendEmail(query); String mailBody =
	 * mailDetails.getMailBody(); String Salutation =
	 * mailDetails.getSalutation();
	 * 
	 * // mailSalutation=mailSalutation.replace("<param1>","Yash");
	 * mailBody=mailBody.replace("<param1>", MailConstants.CREATE_PARAM_1);
	 * mailBody=mailBody.replace("<param2>", MailConstants.CREATE_PARAM_2);
	 * 
	 * logger.info(""); logger.info(
	 * "***************************CREATE SURVEY MAIL STARTS***************************"
	 * ); logger.info(mailDetails.getMailSubject()); logger.info("");
	 * logger.info(Salutation); logger.info(""); logger.info(mailBody);
	 * logger.info(""); logger.info(mailDetails.getClosing());
	 * logger.info(mailDetails.getSignature()); logger.info("");
	 * logger.info(mailDetails.getNonMahindra()); logger.info(
	 * "****************************CREATE SURVEY MAIL ENDS****************************"
	 * ); logger.info("");
	 * 
	 * //---------------------------------------Converting Model to
	 * DTO---------------------------------------
	 * 
	 * MailDTO mailDTO = new MailDTO();
	 * mailDTO.setMailSubject(mailDetails.getMailSubject());
	 * mailDTO.setSalutation(mailDetails.getSalutation());
	 * mailDTO.setMailBody(mailBody);
	 * mailDTO.setClosing(mailDetails.getClosing());
	 * mailDTO.setSignature(mailDetails.getSignature());
	 * mailDTO.setNonMahindra(mailDetails.getNonMahindra());
	 * 
	 * 
	 * //-----------------------------------------Mail to Employees
	 * Start-----------------------------------------
	 * 
	 * Iterator<EmployeeDTO> itr = employeeListForMail.iterator(); EmployeeDTO
	 * employee = null; while (itr.hasNext()) { employee =(EmployeeDTO)
	 * itr.next();
	 * 
	 * 
	 * HashMap<String, String> mailDetailsMap = new HashMap<String, String>();
	 * 
	 * String fullName =employee.getFullName(); String[] array =
	 * fullName.split("\\s+"); String firstName = employee.getfName(); firstName
	 * = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
	 * 
	 * logger.info("FIRST NAME FOR MAIL IS "+firstName); String subject =
	 * mailDTO.getMailSubject(); String
	 * mailSalutation=mailDTO.getSalutation().replace("<param1>",firstName);
	 * String msgBody = mailDTO.getMailBody(); String closing =
	 * mailDTO.getClosing(); String signature = mailDTO.getSignature(); String
	 * nonMahindra = mailDTO.getNonMahindra();
	 * 
	 * String space = "<br>";
	 * 
	 * 
	 * if(employee.getSapFlag().equals("Y")){
	 * logger.info("Is a Mahindra Employee"); msgBody = mailSalutation +
	 * space+space+ msgBody + space+space+closing+ space+signature;
	 * 
	 * logger.info(
	 * "++++++++++++++++++++++++++Final Mail Body is++++++++++++++++++++++++++"
	 * ); logger.info(msgBody);
	 * 
	 * mailDetailsMap.put("msgSub", subject); mailDetailsMap.put("salutation",
	 * mailSalutation); mailDetailsMap.put("msgBody", msgBody);
	 * mailDetailsMap.put("closing", closing); mailDetailsMap.put("signature",
	 * signature);
	 * 
	 * try { setSystemPropertiesforMahindraSMTP(employee, mailDetailsMap); //
	 * setSystemPropertiesforFalconideSMTP(employee, mailDetailsMap); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * } else{ logger.info("Not a Mahindra Employee");
	 * 
	 * msgBody = mailSalutation + space+space+ msgBody+space+ nonMahindra+
	 * space+space+closing+ space+signature;
	 * 
	 * logger.info(
	 * "++++++++++++++++++++++++++Final Mail Body is++++++++++++++++++++++++++"
	 * ); logger.info(msgBody);
	 * 
	 * mailDetailsMap.put("msgSub", subject); mailDetailsMap.put("salutation",
	 * mailSalutation); mailDetailsMap.put("msgBody", msgBody);
	 * mailDetailsMap.put("closing", closing); mailDetailsMap.put("signature",
	 * signature); // mailDetailsMap.put("nonMahindra", nonMahindra);
	 * 
	 * try { setSystemPropertiesforMahindraSMTP(employee, mailDetailsMap); //
	 * setSystemPropertiesforFalconideSMTP(employee, mailDetailsMap); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * }
	 * 
	 * 
	 * }
	 * 
	 * }
	 */
	public void sendMailOnCreateToRespondents1(
			List<EmployeeDTO> employeeListForMail, String year) throws UnsupportedEncodingException {

		Iterator<EmployeeDTO> itr1 = employeeListForMail.iterator();
		EmployeeDTO employee = null;
		while (itr1.hasNext()) {
			employee = (EmployeeDTO) itr1.next();
			
			String sapFlag = "";
			sapFlag = employee.getSapFlag();
			//if (passwordGenSSOdomainCheck.ssodomain(employee.getEmailId())) {
     
				if (sapFlag.equals("Y")) {
					logger.info("SAP EMPLOYEE, SENDING MAHINDRA MAIL");
					String query = "from MTMail mtmail where mtmail.year ="
							+ year + "and mtmail.mailType='create_survey_mnm'";
					MTMail mailDetails = statusDAO.sendEmail(query); //
					String mailBody = mailDetails.getMailBody();
					String Salutation = mailDetails.getSalutation();

					String encryptedContent=encryption.encrypt(employee.getEmailId(),"key123key123key1");
					
					System.out.println("encrypt :"+encryptedContent);
					String encodedUsername=URLEncoder.encode(encryptedContent, "UTF-8");
					
					System.out.println("encoded: "+encodedUsername);
					// mailSalutation=mailSalutation.replace("<param1>","Yash");
					mailBody = mailBody.replace("<param1>",
							MailConstants.REMINDER_PARAM_2);
					mailBody = mailBody.replace("<param2>",
							MailConstants.LINK_FOR_MNM);
					mailBody=mailBody.replace("<param6>",encodedUsername);
					// mailBody=mailBody.replace("<param3>",
					// MailConstants.REMINDER_PARAM_3);

					logger.info("");
					logger.info("***************************CREATE SCHEDULER MAIL STARTS***************************");
					logger.info(mailDetails.getMailSubject());
					logger.info("");
					logger.info(Salutation);
					logger.info("");
					logger.info(mailBody);
					logger.info("");
					logger.info(mailDetails.getClosing());
					logger.info(mailDetails.getSignature());
					logger.info("****************************CREATE SCHEDULER MAIL ENDS****************************");
					logger.info("");

					// ---------------------------------------Converting Model
					// to DTO---------------------------------------

					MailDTO mailDTO = new MailDTO();
					mailDTO.setMailSubject(mailDetails.getMailSubject());
					mailDTO.setSalutation(mailDetails.getSalutation());
					mailDTO.setMailBody(mailBody);
					mailDTO.setClosing(mailDetails.getClosing());
					mailDTO.setSignature(mailDetails.getSignature());

					// -----------------------------------------Mail to
					// Employees Start-----------------------------------------

					HashMap<String, String> mailDetailsMap = new HashMap<String, String>();

					String fullName = employee.getFullName();
					String[] array = fullName.split("\\s+");
					String firstName = array[0];
					/*if(!firstName.substring(0, 1).equals(" "))*/
					firstName = firstName.substring(0, 1).toUpperCase()
							+ firstName.substring(1);
					/*else
						firstName=firstName.substring(1, 2).toUpperCase()
								+ firstName.substring(2);*/

					logger.info("FIRST NAME FOR MAIL IS " + firstName);
					String subject = mailDTO.getMailSubject();
					String mailSalutation = mailDTO.getSalutation().replace(
							"<param1>", firstName);
					String msgBody = mailDTO.getMailBody();
					String closing = mailDTO.getClosing();
					String signature = mailDTO.getSignature();

					String space = "<br>";
					msgBody = mailSalutation + space + space + msgBody + space
							+ space + closing + space + signature;

					logger.info("++++++++++++++++++++++++++Final Mail Body is++++++++++++++++++++++++++");
					logger.info(msgBody);

					mailDetailsMap.put("msgSub", subject);
					mailDetailsMap.put("salutation", mailSalutation);
					mailDetailsMap.put("msgBody", msgBody);
					mailDetailsMap.put("closing", closing);
					mailDetailsMap.put("signature", signature);

					try {
						setSystemPropertiesforMahindraSMTP(employee,mailDetailsMap);
						//setSystemPropertiesforFalconideSMTP(employee,mailDetailsMap);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				//} // if condition of sap flag y ends here
			} else {
				logger.info("NON SAP EMPLOYEE, SENDING NON MAHINDRA MAIL");
				String query = "from MTMail mtmail where mtmail.year =" + year
						+ "and mtmail.mailType='create_survey_nonmnm'";
				MTMail mailDetails = statusDAO.sendEmail(query);
				String mailBody = mailDetails.getMailBody();
				String Salutation = mailDetails.getSalutation();

				// mailSalutation=mailSalutation.replace("<param1>","Yash");
				mailBody = mailBody.replace("<param1>",
						MailConstants.REMINDER_PARAM_2);
				mailBody = mailBody.replace("<param2>",
						MailConstants.REMINDER_PARAM_3);
				// mailBody=mailBody.replace("<param3>",
				// MailConstants.REMINDER_PARAM_3);
				
				/*if(employee.getPassword()!=null)
				mailBody = mailBody.replace("<param4>",employee.getPassword());
				
				mailBody=mailBody.replace("<param5>",
						employee.getEmailId());*/
				String encryptedContent=encryption.encrypt(employee.getEmailId(),"key123key123key1");
				String encodedUsername=URLEncoder.encode(encryptedContent);
				mailBody = mailBody.replace("<param6>",
						encodedUsername);
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

				// ---------------------------------------Converting Model to
				// DTO---------------------------------------

				MailDTO mailDTO = new MailDTO();
				mailDTO.setMailSubject(mailDetails.getMailSubject());
				mailDTO.setSalutation(mailDetails.getSalutation());
				mailDTO.setMailBody(mailBody);
				mailDTO.setClosing(mailDetails.getClosing());
				mailDTO.setSignature(mailDetails.getSignature());

				// -----------------------------------------Mail to Employees
				// Start-----------------------------------------

				HashMap<String, String> mailDetailsMap = new HashMap<String, String>();

				String fullName = employee.getFullName();
				String[] array = fullName.split("\\s+");
				String firstName = array[0];
				firstName = firstName.substring(0, 1).toUpperCase()
						+ firstName.substring(1);

				logger.info("FIRST NAME FOR MAIL IS " + firstName);
				String subject = mailDTO.getMailSubject();
				String mailSalutation = mailDTO.getSalutation().replace(
						"<param1>", firstName);
				String msgBody = mailDTO.getMailBody();
				String closing = mailDTO.getClosing();
				String signature = mailDTO.getSignature();

				String space = "<br>";
				msgBody = mailSalutation + space + space + msgBody + space
						+ space + closing + space + signature;

				logger.info("++++++++++++++++++++++++++Final Mail Body is++++++++++++++++++++++++++");
				logger.info(msgBody);

				mailDetailsMap.put("msgSub", subject);
				mailDetailsMap.put("salutation", mailSalutation);
				mailDetailsMap.put("msgBody", msgBody);
				mailDetailsMap.put("closing", closing);
				mailDetailsMap.put("signature", signature);

				try {
					// setSystemPropertiesforMahindraSMTP(employee,
					// mailDetailsMap);
					setSystemPropertiesforFalconideSMTP(employee,
							mailDetailsMap);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public void setSystemPropertiesforMahindraSMTP(EmployeeDTO employee,
			Map<String, String> mailDetails) throws Exception {

		logger.info("SimpleEmail Start");
		String emailId = employee.getEmailId();
		String smtpHostServer = "10.2.202.42";
		/*
		 * System.getProperties().put("http.proxyHost", "10.2.152.4");
		 * System.getProperties().put("http.proxyPort", "80");
		 * System.getProperties().put("http.proxyUser", "mobcomm");
		 * System.getProperties().put("http.proxyPassword", "welcome@1");
		 */

		Session session = null;
		Properties props = System.getProperties();
		props.put("http.proxyHost", "10.2.152.4");
		props.put("http.proxyPort", "80");
		props.put("http.proxyUser", "mobcomm");
		props.put("http.proxyPassword", "welcome@1");
		props.put("mail.smtp.host", "10.2.202.42");

		/*
		 * Properties props = System.getProperties();
		 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.host",
		 * smtpHostServer); props.put("mail.smtp.port", "25"); Session session =
		 * Session.getInstance(props, null);
		 */
		/*
		 * session = Session.getInstance(props, new Authenticator() { protected
		 * PasswordAuthentication getPasswordAuthentication() { return new
		 * PasswordAuthentication("mahaya-cont", "mahindra@333"); } });
		 */

	
		sendEmail(session, employee, mailDetails);

	}

	public void setSystemPropertiesforFalconideSMTP(EmployeeDTO employee,
			Map<String, String> mailDetails) throws Exception {
		logger.info("SimpleEmail Start");
		String emailId = employee.getEmailId();
		/*
		 * String smtpHostServer = "10.2.202.42";
		 * System.getProperties().put("http.proxyHost", "10.2.152.4");
		 * System.getProperties().put("http.proxyPort", "80");
		 * System.getProperties().put("http.proxyUser", "mobcomm");
		 * System.getProperties().put("http.proxyPassword", "welcome@1");
		 */

		String smtpHostServer = "smtp.falconide.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpHostServer);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, null);
		session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("mahindrasamvaad","Mahindra@123");
			}
		});

		sendEmail(session, employee, mailDetails); // check here
	}

	private void sendEmail(Session session, EmployeeDTO employee,
			Map<String, String> mailDetails) {
		// TODO Auto-generated method stub     //ashish mail

		try {

			MimeMessage msg = new MimeMessage(session);
			// set message headers

			msg.setText(mailDetails.get("msgBody"), "UTF-8");
			msg.setSubject(mailDetails.get("msgSub"), "text/HTML");
			
			//revert changes afterward
			 /* List<String> ccList=new ArrayList();
	            ccList.add("ranjan.devashish@mahindra.com");
	            ccList.add("mutkure.sachin@mahindra.com");*/
	            //
			MailClient mailClient = new MailClient(); // new
			com.mahindra.MailClient.DTO.MailDTO mailDTOLatest = new com.mahindra.MailClient.DTO.MailDTO(
					"awaras-cont@ecommunication-mahindra.com",
					employee.getEmailId(), mailDetails.get("msgSub"),
					mailDetails.get("msgBody"), "SM", "Dr. Prince Augustin",
					"text/HTML", null, false, null, null);

			// msg.setFrom(new
			// InternetAddress("CorporateLearning@mahindra.com"));
			// msg.setFrom(new
			// InternetAddress("prince.augustin@grouphrannualsurvey.com","Dr. Prince Augustin"));
			/*msg.setFrom(new InternetAddress(
					"prince.augustin@grouphrannualsurvey.com",
					"Dr. Prince Augustin"));*/
			 msg.setFrom(new InternetAddress("SAMVAAD@MahindraSamvaad.com", "360FEEDBACK"));
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			// msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			// Address myAddress = new InternetAddress(employee.getEmailId());

			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					employee.getEmailId()));

			msg.setReplyTo(InternetAddress
					.parse("no_reply@mahindra.com", false));
			msg.setSentDate(new java.util.Date());

			// msg.setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse(toEmail, false));
			logger.info("Mail Subject " + mailDetails.get("msgSub"));
			logger.info("Mail Body " + mailDetails.get("msgBody"));
			logger.info("Message is ready");
			// Transport.send(msg); sending mail using old method
			// sending mail using mail  client ashish
			
			//remove comments 
			String status = mailClient.SendMail(new Authentication("ashish","pass,123"), mailDTOLatest);
			if ("OK".equalsIgnoreCase(status)) {
				System.out.println("Success "+mailDTOLatest.toString());
			} else { // new
				System.out.println("Error");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * public static void main(String[] args , EmployeeDTO employee, //main will
	 * not work because of spring Map<String, String> mailDetails) {
	 * 
	 * MailClient mailClient=new MailClient(); //Invite Mail MailDTO mailDTO=new
	 * MailDTO("awaras-cont@mahindrasamvaad.com", "ashish.awarkar@bcone.com",
	 * "Test Invite1", "Test Content", "CI", "Invite Test", "text", null, new
	 * Date(), new Date(), "Worli", "M001", false);
	 * 
	 * //Simple Mail
	 * 
	 * com.mahindra.MailClient.DTO.MailDTO mailDTOLatest=new
	 * com.mahindra.MailClient
	 * .DTO.MailDTO("awaras-cont@ecommunication-mahindra.com",
	 * employee.getEmailId(), mailDetails.get("msgSub"),
	 * mailDetails.get("msgBody"), "SM", "Annual Survey", "text/HTML", null,
	 * false,null,null);
	 * 
	 * 
	 * try { String status=mailClient.SendMail(new Authentication("ashish",
	 * "pass,123"), mailDTOLatest); if("OK".equalsIgnoreCase(status)){
	 * System.out.println("Success"); }else{ System.out.println("Error"); } }
	 * catch (IOException e) { e.printStackTrace(); } catch (CustomException e)
	 * { e.printStackTrace(); } }
	 */

}
