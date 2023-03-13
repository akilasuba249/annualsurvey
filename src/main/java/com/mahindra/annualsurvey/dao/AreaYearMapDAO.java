/**
 * 
 */
package com.mahindra.annualsurvey.dao;

import com.mahindra.annualsurvey.model.AreaYearMapping;
import com.mahindra.annualsurvey.model.MTArea;

/**
 * @author Yash Mahale
 *
 */
public interface AreaYearMapDAO extends CommonDao<AreaYearMapping>{

	void deleteArea(String query);

}
