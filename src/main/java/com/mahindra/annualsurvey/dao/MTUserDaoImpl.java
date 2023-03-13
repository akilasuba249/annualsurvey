package com.mahindra.annualsurvey.dao;



import org.springframework.stereotype.Repository;

import com.mahindra.annualsurvey.model.MTUser;

@Repository
public class MTUserDaoImpl extends AbstractJpaDAO<MTUser> implements MTUserDao
{
	
	@Override
	public void postConstruct() {
		// TODO Auto-generated method stub
		setClazz(MTUser.class);
	}
	

	
	}
