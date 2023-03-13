package com.mahindra.annualsurvey.services;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.dao.MTResponseDAO;
import com.mahindra.annualsurvey.model.MTBusiness;
import com.mahindra.annualsurvey.model.MTResponse;
import com.mahindra.annualsurvey.model.MTResponseDetails;
import com.mahindra.annualsurvey.model.MTSector;

@Service
public class MTResponseServiceImpl implements MTResponseService {

	@PersistenceContext
	EntityManager entitymanager;
	
	@Autowired
	MTResponseDAO mtresponsedao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String create(MTResponse mtresp) {

		mtresponsedao.create(mtresp);
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String update(MTResponse mtresp) {
		List<MTResponseDetails> responsedetls=entitymanager.createQuery(" from MTResponseDetails  mrd where mrd.tblresponse.responseId = "+mtresp.getResponseId()).getResultList();
		//
		Iterator<MTResponseDetails> i=responsedetls.iterator();
		while (i.hasNext()) {
			MTResponseDetails mtResponseDetails = (MTResponseDetails) i.next();
			entitymanager.createQuery(" Delete from MTRespSubQuestDtls where respdtls.responseDetailId ="+mtResponseDetails.getResponseDetailId()).executeUpdate();
		}
		entitymanager.createQuery("delete from MTResponseDetails  mrd where mrd.tblresponse.responseId = "+mtresp.getResponseId()).executeUpdate();
		mtresponsedao.update(mtresp);
		return null;
	}

	public List<MTSector> getAllSector()
	{
		return entitymanager.createQuery("from MTSector").getResultList();
	}
	
	public List<MTBusiness> getBusinessSector(String sectorId)
	{
		return entitymanager.createQuery("from MTBusiness mb where mb.mtsector.sectorId ="+sectorId).getResultList();
	}

	public List<MTBusiness> getAllBusiness()
	{
		return entitymanager.createQuery("from MTBusiness").getResultList();
	}

	
}
