package com.mahindra.annualsurvey.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.model.MTEmployee;

@Repository
public class MTEmployeeDAOImpl extends AbstractJpaDAO<MTEmployee>  implements MTEmployeeDAO {
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void postConstruct() {
		// TODO Auto-generated method stub
		setClazz(MTEmployee.class);
	}

	@Override
	@Transactional
	public MTEmployee addnewemployee(MTEmployee emp) {
		create(emp);
		return emp;
	}
	
	
}
