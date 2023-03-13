package com.mahindra.annualsurvey.services;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.mahindra.annualsurvey.controllers.AreaController;
import com.mahindra.annualsurvey.dao.MTUserDao;
import com.mahindra.annualsurvey.dao.MTUserDaoImpl;
import com.mahindra.annualsurvey.model.MTUser;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MTUserServiceImpl implements MTUserService
{
	public static final Logger logger = Logger.getLogger(MTUserServiceImpl.class);
	@Autowired
	private MTUserDao mtUserDao;
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public List<MTUser> checkLogin(MTUser user) {
		// TODO Auto-generated method stub
		List<MTUser> users=new ArrayList<MTUser>();
		try{
	
			logger.info("in mt_user check::"+user.getUsername()+"&&"+user+"&&"+user.getPassword());
			
			logger.info(user.getUsername());
			logger.info(mtUserDao);
			users=mtUserDao.findAll(" username='"+user.getUsername()+"' and password='"+user.getPassword()+"'");
			//}
			logger.info("users"+users);
	
		}catch(Exception e){
			e.printStackTrace();
			logger.info("Exception in Login Query");
			return null;
		}
	
	return users;
	}


}
