/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.util.List;

import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.dto.EmployeeMasterDTO;
import com.mahindra.annualsurvey.dto.ResponseMasterDTO;
import com.mahindra.annualsurvey.model.MTEmployee;


/**
 * @author admin
 *
 */
public interface WebService {

	List<EmployeeMasterDTO> getEmployees();

	List<ResponseMasterDTO> getResponse();

}
