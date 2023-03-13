/**
 * 
 */
package com.mahindra.annualsurvey.controllers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mahindra.annualsurvey.constants.CommonConstants;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.services.StatusService;

/**
 * @author Yash Mahale
 * 
 *Controller to get the Employee Survey statuses for a particular year
 */
@Controller
public class StatusController {
	public static final Logger logger = Logger.getLogger(StatusController.class);
	@Autowired
	private StatusService statusService;
	
	@RequestMapping(value={"/getStatuses"},method=RequestMethod.POST)        // change to post for deployement
	public void getStatuses(HttpServletRequest req, HttpServletResponse res ) throws IOException {
		logger.info("Into getStatuses in Status Controller for getting Employee Survey Statuses");
		String year = req.getParameter("year");
		logger.info("Year selected is "+year);
	//	int surveyID = statusService.getSurveyID(year);
	//	logger.info("Survey ID is "+surveyID);
		List<EmployeeDTO> employeeStatusList = statusService.getEmployeeStatus(year);
		Gson gson = new Gson();
		String employeeList = gson.toJson(employeeStatusList);
		res.getWriter().write(employeeList);
 		
	}
	@RequestMapping(value={"/mailToEmployees"},method=RequestMethod.POST)
	public void sendMailToEmployees(HttpServletRequest req, HttpServletResponse res ) throws IOException {
		logger.info("Into sendMailToEmployees in Status Controller for sending Reminder Mail");
		String mailType = req.getParameter("mailType");
		String mailTracking = req.getParameter("dataForTracking");
		String year = req.getParameter("year");
		String mailTrackingJson = convertJsonFormat(mailTracking);
		logger.info("JSON FOR SENDING MAIL IS"+mailTrackingJson);
		
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<EmployeeDTO>>() {
		}.getType();
		ArrayList<EmployeeDTO> employeeListForMail = gson.fromJson(mailTrackingJson, listType);
		
		//Printing the Details of Employees to whom the mail has to be triggered
		for (Iterator iterator = employeeListForMail.iterator(); iterator.hasNext();) {
			EmployeeDTO empDTO = (EmployeeDTO) iterator.next();
			logger.info("-----------------------------------------------------");
			logger.info("EMPLOYEE ID 	" + empDTO.getEmpId());
			logger.info("EMPNAME	" + empDTO.getfName());
			logger.info("EMP EMAIL ID	" + empDTO.getEmailId());
			logger.info("-----------------------------------------------------");
		}
		
		//if(CommonConstants.TRACKING_REMINDER.equalsIgnoreCase(mailType)){
			
			/*MailThread mailThread=new MailThread(employeeListForMail, year);
			Thread t= new Thread(mailThread);
			t.start();*/
			statusService.sendReminderToRespondents(employeeListForMail,year);
			
	//	}
		
	}

	private String convertJsonFormat(String jdata) {
		jdata = jdata.substring(1, jdata.length() - 1);
		jdata = jdata.replace("[", "{");
		jdata = jdata.replace("]", "}");
		// jdata=jdata.replace("\"", "");
		jdata = jdata.replace("':',", "':'");
		jdata = jdata.replace("'", "\"");
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(jdata);
		sb.append("]");
		return sb.toString();

	}
	
	
}
