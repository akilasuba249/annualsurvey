package com.mahindra.annualsurvey.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.model.MTArea;

@Repository
public class MTAreaDaoImpl  extends AbstractJpaDAO<MTArea>  implements MTAreaDao{
	public static final Logger logger = Logger.getLogger(MTAreaDaoImpl.class);
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void postConstruct() {
		// TODO Auto-generated method stub
		setClazz(MTArea.class);
	}

	@Override
	public void save(MTArea area) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int areaCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MTArea get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MTArea> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MTArea> selectAreas(String query) {
		// TODO Auto-generated method stub
		List<MTArea> areasActive = getEntityManager().createQuery(query).getResultList();
		return areasActive;
	
	}

	


}
