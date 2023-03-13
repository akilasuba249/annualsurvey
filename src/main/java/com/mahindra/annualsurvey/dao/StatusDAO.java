/**
 * 
 */
package com.mahindra.annualsurvey.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTMail;
import com.mahindra.annualsurvey.model.MTSurvey;

/**
 * @author Yash Mahale
 * DAO to get the Employee Survey statuses for a particular year
 *
 */
public interface StatusDAO extends CommonDao<MTEmployee> {

	int getSurveyID(String query);

	List<MTEmployee> getEmployeesForTracking(String query);
	ArrayList<MTEmployee> getEmployeesForReminderMail(String query);

	MTMail sendEmail(String query);

	List<MTSurvey> getSurveyForReminderMail(String getSurvey);

	public MTSurvey getSurveyIDUser(String query);
	
	void updateNextDate(String updateDateQuery);

}
