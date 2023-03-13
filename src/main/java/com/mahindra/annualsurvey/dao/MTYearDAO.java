/**
 * 
 */
package com.mahindra.annualsurvey.dao;

import java.util.List;

import com.mahindra.annualsurvey.model.MTArea;
import com.mahindra.annualsurvey.model.MTYear;

/**
 * @author Yash Mahale
 *
 */
public interface MTYearDAO extends CommonDao<MTYear> {
	
	     
	    public MTArea get();
	     
	    public List<MTYear> list();

}
