package com.mahindra.annualsurvey.dao;

import com.mahindra.annualsurvey.model.DEmailContent;

/**
 * UserDAO implementation
 * @author admin
 * 
 */
public interface DEmailContentDAO  extends CommonDao<DEmailContent>{
	
	public String getEmailContent(String emailCode);
}
