/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javassist.compiler.ast.NewExpr;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.controllers.AreaController;
import com.mahindra.annualsurvey.dao.MTAreaDao;
import com.mahindra.annualsurvey.dto.AreaDTO;
import com.mahindra.annualsurvey.model.MTArea;

/**
 * @author Yash Mahale
 *
 */
@Service
public class MTAreaServiceImpl implements MTAreaService{
	public static final Logger logger = Logger.getLogger(MTAreaServiceImpl.class);
	@Autowired
	private MTAreaDao areaDao; 

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String create(AreaDTO area) {
		// TODO Auto-generated method stub
		logger.info("Into MT Area Service");
		MTArea areaModel = new MTArea();
		areaModel.setAreaDesc(area.getAreaDesc());
		areaModel.setIsActive("Y");
		areaModel.setCreatedBy("User");
		
		areaModel.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		areaDao.create(areaModel);
		return null;
		
	}

	@Override
	public long select(String areaDesc) {
		StringBuffer query = new StringBuffer(
	" select distinct do.areaCode from MTArea do");
	StringBuffer whereClause = new StringBuffer(" WHERE do.areaDesc='"
			+ areaDesc + "'");

	query = query.append(whereClause.toString());
	long questID =0;
try {
	questID= (long) entitymanager.createQuery(query.toString())
			.getSingleResult();
	
} catch (Exception e) {
	return 0;
}
		
		return questID;
	}

	@Override
	public List<AreaDTO> getArea() {
		
	      logger.info("In Get Area"); 	
		// TODO Auto-generated method stub
		List<MTArea> allAreas=areaDao.findAll();
		
		//converting to AreaDTO
		List<AreaDTO> areaDTO = convertToDTO(allAreas);
	
		return areaDTO;
		
		
	}

	@Override
	public List<AreaDTO> selectAreas(String year) {
		// TODO Auto-generated method stub
		
		//Query for getting the areas actove for a particular year
		String query = "from MTArea area JOIN FETCH area.areaYearMapping mapping where mapping.year="+year;
		List<MTArea> activeAreas =  areaDao.selectAreas(query);
		
		
		List<AreaDTO> areaYearDTO = convertToDTO(activeAreas);
		
		return areaYearDTO;
		
	}
	
	
	private List<AreaDTO> convertToDTO(List<MTArea> mtareas){
		List<AreaDTO> chatDtos =  new ArrayList<AreaDTO>();
		if(mtareas !=null){
			for(MTArea mtArea : mtareas){
				AreaDTO areaDto = new AreaDTO();
				areaDto.setAreaCode(mtArea.getAreaCode());
				areaDto.setAreaDesc(mtArea.getAreaDesc());
				
				chatDtos.add(areaDto);
			}
		}
		
		return chatDtos;
	}

	@Override
	@Transactional
	public String updateArea(AreaDTO area) {
		// TODO Auto-generated method stub
		try {
			
			MTArea areaModel = areaDao.findOne(area.getAreaCode());
			areaModel.setAreaDesc(area.getAreaDesc());
			areaModel.setIsActive("Y");
			areaModel.setModifiedBy("User");
			
			areaModel.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
			areaDao.update(areaModel);
			
			return null;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "failed";
		}
	}

	@Override
	@Transactional
	public String deleteArea(String areaCode) {
		// TODO Auto-generated method stub
		try {
			
			MTArea areaModel = areaDao.findOne(Long.valueOf(areaCode));
			areaDao.delete(areaModel);
			
			return null;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "failed";
		}
	}

	// @Override
/*	public String update(AreaDTO area) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
