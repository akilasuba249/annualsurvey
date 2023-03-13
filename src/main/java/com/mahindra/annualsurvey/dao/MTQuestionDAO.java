package com.mahindra.annualsurvey.dao;

import java.util.List;

import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTQuestion;

public interface MTQuestionDAO extends CommonDao<MTQuestion> {

	List<MTQuestion> selectQuests(String query);
	
}
