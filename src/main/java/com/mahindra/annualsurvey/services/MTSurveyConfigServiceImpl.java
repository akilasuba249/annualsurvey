package com.mahindra.annualsurvey.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.dao.MTEmployeeDAO;
import com.mahindra.annualsurvey.dao.MTSurveyConfigDao;
import com.mahindra.annualsurvey.dto.AreaDTO;
import com.mahindra.annualsurvey.dto.SurveyDTO;
import com.mahindra.annualsurvey.model.MTArea;
import com.mahindra.annualsurvey.model.MTSurvey;
import com.mahindra.annualsurvey.model.MTSurveyConfig;
@Service
public class MTSurveyConfigServiceImpl implements MTSurveyConfigService
{

	public static final Logger logger = Logger.getLogger(MTSurveyConfigServiceImpl.class);
@PersistenceContext
private EntityManager entityManager;

@Autowired
MTSurveyConfigDao mtsconfigDao;

@Override
@Transactional(propagation = Propagation.REQUIRED)
public String create(MTSurvey msconfig) 
{
	logger.info("Into MTSurveyConfigServiceImpl for Create Survey");
	mtsconfigDao.create(msconfig);
	return null;
}
	
@Override
public boolean doesSurveyExist(String year) {
	// TODO Auto-generated method stub
	logger.info("CHECKING IF THE SURVEY ALREADY EXISTS FOR THE YEAR "+year);
	logger.info("");
	String query = "Select survey from MTSurvey survey where year = "+year;
	logger.info("QUERY TO BE EXECUTED IS"+query);
	boolean doesExist=mtsconfigDao.doesSurveyExist(query);
	return doesExist;
}

@Override
public List<SurveyDTO> getAllSurveys() {
	// TODO Auto-generated method stub
	List<MTSurvey> allSurveys=mtsconfigDao.findAll();
	List<SurveyDTO> surveyDTO= new ArrayList<SurveyDTO>();;
	if(!allSurveys.isEmpty()){
		
		 surveyDTO = convertToDTO(allSurveys);
	}
	return surveyDTO;
	
}

private List<SurveyDTO> convertToDTO(List<MTSurvey> mtsurveys){
	List<SurveyDTO> surveyDTOList =  new ArrayList<SurveyDTO>();
	if(mtsurveys !=null){
		for(MTSurvey mtSurvey : mtsurveys){
			SurveyDTO surveyDTO= new SurveyDTO();
			surveyDTO.setStartDate(mtSurvey.getStartDate());
			surveyDTO.setEndDate(mtSurvey.getEndDate());
			surveyDTO.setYear(mtSurvey.getYear());
			
			surveyDTOList.add(surveyDTO);
		}
	}
	
	return surveyDTOList;
}
}
