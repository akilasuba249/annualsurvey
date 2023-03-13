/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mahindra.annualsurvey.dto.YearDTO;
import com.mahindra.annualsurvey.model.MTYear;

/**
 * @author Yash Mahale
 *
 */
@Service
public interface MTYearService {

	
	public List<MTYear>getYears();
}
