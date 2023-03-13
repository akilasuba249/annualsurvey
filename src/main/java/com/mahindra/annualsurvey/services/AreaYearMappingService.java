/**
 * 
 */
package com.mahindra.annualsurvey.services;

import com.mahindra.annualsurvey.dto.AreaYearMappingDTO;

/**
 * @author Yash Mahale
 *
 */

public interface AreaYearMappingService {

	
	public String create(AreaYearMappingDTO areaMap);

	public void delete(AreaYearMappingDTO areaMap);
}
