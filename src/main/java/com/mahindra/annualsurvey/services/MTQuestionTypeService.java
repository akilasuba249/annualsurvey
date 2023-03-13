package com.mahindra.annualsurvey.services;

import java.util.List;

import com.mahindra.annualsurvey.dto.QuestionTypeDTO;
import com.mahindra.annualsurvey.model.MTQuestionType;

public interface MTQuestionTypeService {

	public String create(QuestionTypeDTO questType);

	public String update(QuestionTypeDTO questType);

	public void delete(QuestionTypeDTO questType);

	public List<MTQuestionType> getQuestionType();

	void deletebyId(long l);

	public int getQuestionTypeID(String QuestTypeDesc);
}
