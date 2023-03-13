package com.mahindra.annualsurvey.dao;

import com.mahindra.annualsurvey.model.MTEmployee;

public interface MTEmployeeDAO extends CommonDao<MTEmployee> {
	
	public MTEmployee addnewemployee(MTEmployee emp);
	
}
