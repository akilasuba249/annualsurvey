package com.mahindra.annualsurvey.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.model.MTArea;
import com.mahindra.annualsurvey.model.MTQuestion;

@Repository
public class MTQuestionDAOImpl extends AbstractJpaDAO<MTQuestion>  implements MTQuestionDAO {
	
	
	public static final Logger logger = Logger.getLogger(MTQuestionDAOImpl.class);
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void postConstruct() {
		// TODO Auto-generated method stub
		setClazz(MTQuestion.class);
	}


	@Override
	public List<MTQuestion> selectQuests(String query) {
		// TODO Auto-generated method stub
		List<MTQuestion> quests = getEntityManager().createQuery(query).getResultList();
		return quests;
	
	}
	
	

}
