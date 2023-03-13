/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.controllers.AreaController;
import com.mahindra.annualsurvey.dao.AreaYearMapDAO;
import com.mahindra.annualsurvey.dao.MTAreaDao;
import com.mahindra.annualsurvey.dto.AreaYearMappingDTO;
import com.mahindra.annualsurvey.model.AreaYearMapping;
import com.mahindra.annualsurvey.model.MTArea;

/**
 * @author Yash Mahale
 *
 */
@Service
public class AreaYearMappingServiceImpl implements AreaYearMappingService {
	
	public static final Logger logger = Logger.getLogger(AreaYearMappingServiceImpl.class);
	
	@Autowired
	private AreaYearMapDAO areaYearMapDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String create(AreaYearMappingDTO areaMap){
		
		logger.info("Into Area Year Mapping Service IMPL");
		
		MTArea mtarea = new MTArea();
		mtarea.setAreaCode(areaMap.getAreaCode());
		
		AreaYearMapping areaMapModel = new AreaYearMapping();
		
		areaMapModel.setCreatedBy("User");
		areaMapModel.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		areaMapModel.setYear(areaMap.getYear());
		areaMapModel.setMtAreas(mtarea);
		areaMapModel.setIsActive("Y");
		
		areaYearMapDao.create(areaMapModel);
		
		
		return null;
		
	}

	@Override
	public void delete(AreaYearMappingDTO areaMap) {
		// TODO Auto-generated method stub
		int areaCode=areaMap.getAreaCode();
		String year=areaMap.getYear();
		logger.info("");
		logger.info("Into AreaYearMappingServiceImpl for deleting the year "+year+"and area code "+ areaCode);
		logger.info("");
		String query = "delete from AreaYearMapping areamap where areamap.year="+year+"and areamap.mtAreas.areaCode="+areaCode;
		areaYearMapDao.deleteArea(query);
		
	}

}
