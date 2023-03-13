package com.mahindra.annualsurvey.services;

import com.mahindra.annualsurvey.dto.EconnectDataDTO;

public interface EconnectDataService {
	
	public EconnectDataDTO getActiveSurveyAndEmployee(String year);

}
