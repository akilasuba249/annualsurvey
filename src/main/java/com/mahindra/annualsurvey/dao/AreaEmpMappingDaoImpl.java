package com.mahindra.annualsurvey.dao;

import org.springframework.stereotype.Repository;

import com.mahindra.annualsurvey.model.AreaEmpMapping;

@Repository
public class AreaEmpMappingDaoImpl extends AbstractJpaDAO<AreaEmpMapping> implements AreaEmpMappingDao{
	
	@Override
	public void postConstruct(){
		setClazz(AreaEmpMapping.class);
	}
	

}
