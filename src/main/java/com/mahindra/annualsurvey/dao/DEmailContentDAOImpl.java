package com.mahindra.annualsurvey.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.mahindra.annualsurvey.controllers.AreaController;
import com.mahindra.annualsurvey.model.DEmailContent;
/**
 * UserDao Implementation
 * @author admin
 *
 */
@Repository
public class DEmailContentDAOImpl extends AbstractJpaDAO<DEmailContent> implements DEmailContentDAO {
	public static final Logger logger = Logger.getLogger(DEmailContentDAOImpl.class);
	
	@Override
	public void postConstruct() {
		// TODO Auto-generated method stub
		setClazz(DEmailContent.class);
	}

	public String getEmailContent(String emailCode){
		logger.info("email code="+emailCode);
		String emailString = "";
		try{
			List<DEmailContent> emailContent=findAll(" emailCode='"+emailCode+"'");
			logger.info("email content"+emailContent.get(0).getEmailContent());
			emailString = emailContent.get(0).getEmailContent(); 
		}
		catch(Exception e){
			
		}
		
		return emailString;
	}

	

}
