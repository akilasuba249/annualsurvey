/**
 * 
 */
package com.mahindra.annualsurvey.controllers;

import java.util.List;

import javax.persistence.QueryHint;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.dto.EmployeeMasterDTO;
import com.mahindra.annualsurvey.dto.EmployeeMasterDTOList;
import com.mahindra.annualsurvey.dto.ResponseMasterDTO;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.services.WebService;

/**
 * @author Yash Mahale
 *
 */
@RestController
public class WebServiceController {
	public static final Logger logger = Logger.getLogger(WebServiceController.class);
	@Autowired
	WebService webService;  //Service which will do all data retrieval/manipulation work
	
	

	
	//-------------------Retrieve All Users--------------------------------------------------------
	
	@RequestMapping(value = "/empmaster", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<EmployeeMasterDTO>> employeeMaster() {
		List<EmployeeMasterDTO> users = webService.getEmployees();
		if(users.isEmpty()){
			return new ResponseEntity<List<EmployeeMasterDTO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
	// EmployeeMasterDTOList empt=new EmployeeMasterDTOList(users);
	 
	 
		return new ResponseEntity<List<EmployeeMasterDTO>>(users, HttpStatus.OK);
	}
	
	
	//-------------------Get Response from Users--------------------------------------------------------
	
	@RequestMapping(value = "/response", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ResponseMasterDTO>> responseData() {
		List<ResponseMasterDTO> responseList = webService.getResponse();
		if(responseList.isEmpty()){
			return new ResponseEntity<List<ResponseMasterDTO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<ResponseMasterDTO>>(responseList, HttpStatus.OK);
	}
	
}
