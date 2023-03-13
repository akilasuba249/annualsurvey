/**
 * 
 */
package com.mahindra.annualsurvey.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.controllers.AreaController;
import com.mahindra.annualsurvey.model.AreaYearMapping;

/**
 * @author Yash Mahale
 *
 */
@Repository
public class AreaYearMapDAOImpl  extends AbstractJpaDAO<AreaYearMapping>  implements AreaYearMapDAO {
	public static final Logger logger = Logger.getLogger(AreaYearMapDAOImpl.class);
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void postConstruct() {
		// TODO Auto-generated method stub
		setClazz(AreaYearMapping.class);
	}

	

	@Override
	public void delete(AreaYearMapping entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AreaYearMapping update(AreaYearMapping entity) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteArea(String query) {
		// TODO Auto-generated method stub
		logger.info("");
		logger.info("Into AreaYearmappingDAOImpl FOR EXECUTING THE QUERY");
		logger.info("");
	int rowsAffected=getEntityManager().createQuery(query).executeUpdate();
	
	logger.info("Number of rows affected "+rowsAffected);
		
	}

	
}
