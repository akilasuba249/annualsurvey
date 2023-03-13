package com.mahindra.annualsurvey.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.model.MTResponse;

@Repository
public class MTResponseDAOImpl  extends AbstractJpaDAO<MTResponse> implements MTResponseDAO{

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void postConstruct() {
		// TODO Auto-generated method stub
		setClazz(MTResponse.class);
	}

}
