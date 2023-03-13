package com.mahindra.annualsurvey.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTSurvey;
import com.mahindra.annualsurvey.model.MTSurveyConfig;

@Repository
public class MTSurveyConfigDaoImpl extends AbstractJpaDAO<MTSurvey> implements MTSurveyConfigDao
{
	public static final Logger logger = Logger.getLogger(MTSurveyConfigDaoImpl.class);
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void postConstruct() 
	{
		// TODO Auto-generated method stub
		setClazz(MTSurvey.class);
	}

	@Override
	public boolean doesSurveyExist(String query) {
		// TODO Auto-generated method stub
		logger.info("CHECKING WHETHER THE SURVEY EXISTS IN MTSurveyConfigDaoImpl");
		boolean flag=false;
		List<MTSurvey> surveyList=  getEntityManager().createQuery(query).getResultList();
		if(surveyList.size()>=1){
			logger.info("");
			logger.info("SURVEY EXISTS");
			logger.info("");
			flag=true;
		}
		return flag;
	}



	
}
