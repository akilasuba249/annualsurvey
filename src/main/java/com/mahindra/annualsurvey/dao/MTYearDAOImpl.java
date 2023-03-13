/**
 * 
 */
package com.mahindra.annualsurvey.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.model.MTArea;
import com.mahindra.annualsurvey.model.MTYear;

/**
 * @author admin
 *
 */
@Repository
public class MTYearDAOImpl extends AbstractJpaDAO<MTYear>  implements MTYearDAO {
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void postConstruct() {
		// TODO Auto-generated method stub
		setClazz(MTYear.class);
	}

	@Override
	public MTArea get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MTYear> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
