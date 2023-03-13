package com.mahindra.annualsurvey.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.controllers.QuestionsController;
import com.mahindra.annualsurvey.dao.MTQuestionDAO;
import com.mahindra.annualsurvey.dto.AreaDTO;
import com.mahindra.annualsurvey.dto.QuestionDTO;
import com.mahindra.annualsurvey.model.MTArea;
import com.mahindra.annualsurvey.model.MTQuestion;
import com.mahindra.annualsurvey.model.MTQuestionType;
import com.mahindra.annualsurvey.model.MTSubQuestion;
import com.mahindra.annualsurvey.model.MTYear;

@Service
public class MTQuestionServiceImpl implements MTQuestionService {

	@Autowired
	private MTQuestionDAO questiondao;
	static final Logger logger = Logger.getLogger(MTQuestionServiceImpl.class);

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String create(QuestionDTO quest) {
		MTQuestion question = new MTQuestion();
		MTArea mt = new MTArea();
		mt.setAreaCode(quest.getAreaCode());
		MTQuestionType qt = new MTQuestionType();
		qt.setPkQuestType(quest.getQuestionType());
       MTYear yt = new MTYear();
		yt.setPkYear(quest.getYear());
		question.setIsActive("Y");
		question.setQuestionDesc(quest.getQuestionDesc());
		question.setCreatedBy(quest.getCreatedBy());
		question.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		question.setQuestionArea(mt);
		question.setQuestType(qt);
		question.setModifiedBy(quest.getCreatedBy());
		question.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
		question.setYearMap(yt);
		if (quest.getQuestionType() == 4)
		{	
			
			List<MTSubQuestion> subquest=new ArrayList<MTSubQuestion>();
			Iterator<MTSubQuestion> iter=quest.getSubquest().iterator();
			while (iter.hasNext()) {
				MTSubQuestion mtSubQuestion = (MTSubQuestion) iter.next();
				if(!(mtSubQuestion==null ||mtSubQuestion.getSubQuestDesc()==null))
				if(mtSubQuestion.getSubQuestDesc().trim().length()>0)
				{
					subquest.add(mtSubQuestion);
				}
				
			}
			question.setMtsubquestion(subquest);
		}	
		questiondao.create(question);
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String update(QuestionDTO quest) {
		MTQuestion question = new MTQuestion();
		MTArea mt = new MTArea();
		mt.setAreaCode(quest.getAreaCode());
		MTQuestionType qt = new MTQuestionType();
		qt.setPkQuestType(quest.getQuestionType());
		logger.info(quest.getYear());
		MTYear yt = new MTYear();
		yt.setPkYear(quest.getYear());
        
		question.setQuestionId(quest.getQuestionId());
		question.setIsActive("Y");
		question.setQuestionDesc(quest.getQuestionDesc());
		question.setCreatedBy(quest.getCreatedBy());
		question.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		question.setQuestionArea(mt);
		question.setQuestType(qt);
		question.setYearMap(yt);
		// logger.info(question.getYearMap().getYear() );
		question.setModifiedBy(quest.getCreatedBy());
		question.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
		if (quest.getQuestionType() == 4)
		{	
			
			List<MTSubQuestion> subquest=new ArrayList<MTSubQuestion>();
			Iterator<MTSubQuestion> iter=quest.getSubquest().iterator();
			while (iter.hasNext()) {
				MTSubQuestion mtSubQuestion = (MTSubQuestion) iter.next();
				if(!(mtSubQuestion.getSubQuestDesc()==null))
				if(mtSubQuestion.getSubQuestDesc().trim().length()>0)
				{
					subquest.add(mtSubQuestion);
				}
				
			}
			question.setMtsubquestion(subquest);
		}	
		questiondao.update(question);
		return null;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deletebyId(long entityId) {

		questiondao.deleteById(entityId);

	}

	@Override
	public void delete(QuestionDTO quest) {

		questiondao.deleteById(quest.getQuestionId());
	}

	@Override
	public List<MTQuestion> getQuestions() {
		List<MTQuestion> questList = questiondao.findAll();
		return questList;
	}

	@Override
	public List<MTQuestion> getQuestionByYear(int pkYear) {
		String query = "from MTQuestion quest JOIN FETCH quest.yearMap yearmap where yearmap.pkYear="
				+ pkYear
				+ " and quest.isActive='Y' order by quest.questType.pkQuestType";
		List<MTQuestion> questList = questiondao.selectQuests(query);
		Iterator<MTQuestion> iterator = questList.iterator();
		MTQuestion questObj = null;
		ArrayList<MTQuestion> getQuestList = null;
		getQuestList = new ArrayList<MTQuestion>();
		while (iterator.hasNext()) {
			questObj = iterator.next();
			logger.info(questObj.getQuestionId());
			getQuestList.add(questObj);
		}
		return getQuestList;
	}

	@Override
	public List<MTQuestion> getQuestionByAreaYear(int pkYear, long areaCode) {
		String query = "from MTQuestion quest JOIN FETCH quest.yearMap yearmap JOIN FETCH quest.questionArea areaMap where yearmap.pkYear="
				+ pkYear
				+ " and areaMap.areaCode="
				+ areaCode
				+ " and quest.isActive='Y' order by quest.questType.pkQuestType";
		List<MTQuestion> questList = questiondao.selectQuests(query);
		Iterator<MTQuestion> iterator = questList.iterator();
		MTQuestion questObj = null;
		ArrayList<MTQuestion> getQuestList = null;
		getQuestList = new ArrayList<MTQuestion>();
		while (iterator.hasNext()) {

			questObj = iterator.next();
			logger.info(questObj.getQuestionId());
			getQuestList.add(questObj);
		}
		return getQuestList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateQuestStatus(long questId) {
		boolean isQuestUpdated = false;
		String query = "update MTQuestion set isActive='N' where questionId='"
				+ questId + "'";

		int execute = entityManager.createQuery(query).executeUpdate();
		if (execute > 1) {
			isQuestUpdated = true;
		}
		return isQuestUpdated;

	}
     
	public List<MTSubQuestion> getSubQuestions(long questId)
	{		
		return entityManager.createQuery("from MTSubQuestion msq where msq.mtQuestion.questionId="+questId).getResultList();
	}
}
