package com.mahindra.annualsurvey.services;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahindra.annualsurvey.controllers.AreaController;
import com.mahindra.annualsurvey.dao.DEmailContentDAO;

@Service
public class DEmailContentServiceImpl implements DEmailContentService{
	public static final Logger logger = Logger.getLogger(DEmailContentServiceImpl.class);

	@Autowired
	private DEmailContentDAO dEmailContentDao;
	
	public String getEmailContent(String emailCode) {
		// TODO Auto-generated method stub
		return dEmailContentDao.getEmailContent(emailCode);
	}

	
	
	
	
	
	
	
	
	

	
}
