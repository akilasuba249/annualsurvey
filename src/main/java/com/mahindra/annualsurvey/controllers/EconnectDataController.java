package com.mahindra.annualsurvey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mahindra.annualsurvey.dto.EconnectDataDTO;
import com.mahindra.annualsurvey.services.EconnectDataService;

@RestController
public class EconnectDataController {
	
	@Autowired EconnectDataService service;
	
	@RequestMapping(value="getAnnualsurveyData",method=RequestMethod.GET)
	public EconnectDataDTO getAllActiveEmployee() {
		String year = "2020";
		EconnectDataDTO dto = service.getActiveSurveyAndEmployee(year);
		return dto;
		
	}

}
