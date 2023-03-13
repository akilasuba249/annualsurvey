package com.mahindra.annualsurvey.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mahindra.annualsurvey.controllers.AreaController;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.model.MTArea;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTMail;
import com.mahindra.annualsurvey.model.MTSurvey;

@Repository
public class StatusDAOImpl extends AbstractJpaDAO<MTEmployee> implements StatusDAO {
	
	public static final Logger logger = Logger.getLogger(StatusDAOImpl.class);

/*	@Override
	public MTEmployee update(MTEmployee entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(MTEmployee entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(MTEmployee entity) {
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public int getSurveyID(String query) {
		// TODO Auto-generated method stub
		logger.info("------------------------------------------------------------------");
		logger.info("Into StatusDaoImpl for getting Survey ID");
		logger.info("");
		logger.info("Query to be executed is "+query);
		logger.info("------------------------------------------------------------------");
		int surveyID = (int) getEntityManager().createQuery(query).getSingleResult();
		logger.info("SURVEY ID FOR ABOVE YEAR IS "+surveyID);
		return surveyID;
	}


	@Override
	public MTSurvey getSurveyIDUser(String query) {
		// TODO Auto-generated method stub
		logger.info("------------------------------------------------------------------");
		logger.info("Into StatusDaoImpl for getting Survey ID");
		logger.info("");
		logger.info("Query to be executed is "+query);
		logger.info("------------------------------------------------------------------");
		MTSurvey surveyID = (MTSurvey) getEntityManager().createQuery(query).getSingleResult();
		logger.info("SURVEY ID FOR ABOVE YEAR IS "+surveyID.getSurveyId());
		return surveyID;
	}
	
	@Override
	public List<MTEmployee> getEmployeesForTracking(String query) {
		// TODO Auto-generated method stub
		List<MTEmployee> empList = getEntityManager().createQuery(query).getResultList();
				return empList;
	}
	
	@Override
	public ArrayList<MTEmployee> getEmployeesForReminderMail(String query) {
		// TODO Auto-generated method stub
		ArrayList<MTEmployee> empList = (ArrayList<MTEmployee>) getEntityManager().createQuery(query).getResultList();
				return empList;
	}

	@Override
	public MTMail sendEmail(String query) {
		// TODO Auto-generated method stub
		
		MTMail mailDetails = (MTMail) getEntityManager().createQuery(query).getSingleResult();
		return mailDetails;
	}

	@Override
	public List<MTSurvey> getSurveyForReminderMail(String getSurvey) {
		// TODO Auto-generated method stub
		List<MTSurvey> mtSurvey = getEntityManager().createQuery(getSurvey).getResultList();
		return mtSurvey;
	}

	@Override
	public void updateNextDate(String updateDateQuery) {
		// TODO Auto-generated method stub
	int rowsAffected=getEntityManager().createQuery(updateDateQuery).executeUpdate();
		
	}
}
