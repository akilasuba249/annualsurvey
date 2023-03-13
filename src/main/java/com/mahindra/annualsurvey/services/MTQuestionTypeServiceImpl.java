package com.mahindra.annualsurvey.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahindra.annualsurvey.controllers.QuestionsController;
import com.mahindra.annualsurvey.dao.MTQuestionDAO;
import com.mahindra.annualsurvey.dao.MTQuestionTypeDAO;
import com.mahindra.annualsurvey.dto.QuestionTypeDTO;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTQuestionType;

@Service
public class MTQuestionTypeServiceImpl implements MTQuestionTypeService {

	static final Logger logger = Logger.getLogger(QuestionsController.class);

	@Autowired
	private MTQuestionTypeDAO questiondao;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public String create(QuestionTypeDTO questType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(QuestionTypeDTO questType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(QuestionTypeDTO questType) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MTQuestionType> getQuestionType() {
		List<MTQuestionType> questTypeList = questiondao.findAll();
		return questTypeList;

	}

	@Override
	public void deletebyId(long l) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getQuestionTypeID(String QuestTypeDesc) {
		int questID =0;
		StringBuffer query = new StringBuffer(
				" select distinct do.pkQuestType from MTQuestionType do");
		StringBuffer whereClause = new StringBuffer(" WHERE do.questTypeDesc='"
				+ QuestTypeDesc + "'");

		query = query.append(whereClause.toString());
        try {
        	questID= (int) entityManager.createQuery(query.toString())
    				.getSingleResult();
	
		} catch (Exception e) {
		
			return 0;
		}
		
		return questID;
	}

}
