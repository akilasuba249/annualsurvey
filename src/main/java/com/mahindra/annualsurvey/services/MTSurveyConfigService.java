package com.mahindra.annualsurvey.services;

import java.util.List;

import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.dto.SurveyDTO;
import com.mahindra.annualsurvey.model.MTSurvey;
import com.mahindra.annualsurvey.model.MTSurveyConfig;
import com.mahindra.annualsurvey.model.MTUser;

public interface MTSurveyConfigService
{
	public String create(MTSurvey mtconfig);
//	public List<MTSurvey> getCreateSurvey();
	public boolean doesSurveyExist(String year);
	public List<SurveyDTO> getAllSurveys();
}
