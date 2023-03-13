/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.util.ArrayList;
import java.util.List;

import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTResponseDetails;
import com.mahindra.annualsurvey.model.MTSurvey;



/**
 * @author Yash Mahale
 * StatusService to get Employee Survey statuses for a particular year
 *
 */
public interface StatusService {

	List<EmployeeDTO> getEmployeeStatus(String year);

	int getSurveyID(String year);

	public List<MTResponseDetails> getEmpResponse(String surveyId,String pkEmp);
		
	public void sendReminderToRespondents(
			ArrayList<EmployeeDTO> employeeListForMail, String year);

	public void sendMailOnCreate(String year); 
   
	public MTSurvey getSurveyIDUser(String year);
}
