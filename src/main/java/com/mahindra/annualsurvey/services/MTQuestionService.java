/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.util.List;

import com.mahindra.annualsurvey.dao.MTQuestionDAO;
import com.mahindra.annualsurvey.dto.QuestionDTO;
import com.mahindra.annualsurvey.model.MTQuestion;
import com.mahindra.annualsurvey.model.MTSubQuestion;

/**
 * @author pooja.pawar
 * 
 */
public interface MTQuestionService {
	public String create(QuestionDTO quest);

	public String update(QuestionDTO quest);

	public void delete(QuestionDTO quest);

	public List<MTQuestion> getQuestions();

	void deletebyId(long l);

	List<MTQuestion> getQuestionByYear(int pkYear);

	public List<MTQuestion> getQuestionByAreaYear(int pkYear, long areaCode);

	public boolean updateQuestStatus(long qId);
	
	public List<MTSubQuestion> getSubQuestions(long questId);

}
