package com.mahindra.annualsurvey.services;

import java.util.List;

import com.mahindra.annualsurvey.model.MTBusiness;
import com.mahindra.annualsurvey.model.MTResponse;
import com.mahindra.annualsurvey.model.MTSector;

public interface MTResponseService {
	public String create(MTResponse mtresp);
	public String update(MTResponse mtresp);
	public List<MTSector> getAllSector();
	public List<MTBusiness> getBusinessSector(String sectorId);
	public List<MTBusiness> getAllBusiness();
}
