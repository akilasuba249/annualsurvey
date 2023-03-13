package com.mahindra.annualsurvey.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.model.MTQuestion;
import com.mahindra.annualsurvey.model.MTQuestionType;

@Repository
public class MTQuestionTypeDAOImpl extends AbstractJpaDAO<MTQuestionType>  implements MTQuestionTypeDAO {
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void postConstruct() {
		// TODO Auto-generated method stub
		setClazz(MTQuestionType.class);
	}

}
