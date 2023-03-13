/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahindra.annualsurvey.dao.MTEmployeeDAO;
import com.mahindra.annualsurvey.dao.MTYearDAO;
import com.mahindra.annualsurvey.dto.YearDTO;
import com.mahindra.annualsurvey.model.MTYear;

/**
 * @author Yash Mahale
 *
 */
@Service
public class MTYearServiceImpl implements MTYearService {

	
	@Autowired
	private MTYearDAO yeardao;

	@Override
	public List<MTYear> getYears() {
		// TODO Auto-generated method stub
		List<MTYear> yearList = yeardao.findAll();
		return yearList;
	}

	

	
	
	

}
