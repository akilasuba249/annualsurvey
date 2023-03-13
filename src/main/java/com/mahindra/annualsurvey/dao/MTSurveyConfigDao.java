package com.mahindra.annualsurvey.dao;

import com.mahindra.annualsurvey.model.MTSurvey;
import com.mahindra.annualsurvey.model.MTSurveyConfig;

public interface MTSurveyConfigDao extends CommonDao<MTSurvey>
{

	boolean doesSurveyExist(String query);

}
