package com.mahindra.annualsurvey.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahindra.annualsurvey.dao.MTSurveyConfigDao;
import com.mahindra.annualsurvey.dto.EconnectDataDTO;
import com.mahindra.annualsurvey.model.MTSurvey;
import com.mahindra.annualsurvey.services.EconnectDataService;

@Service
public class EconnectDataServiceImpl  implements EconnectDataService{

	@PersistenceContext
	EntityManager entityManger;
	
	@Autowired
	MTSurveyConfigDao surveyDao;
	
	@Override
	@Transactional
	public EconnectDataDTO getActiveSurveyAndEmployee(String year) {
		String QUERY="select emp.email_id,emp.emp_id,emp.fname,emp.sapFlag,resp.status" + 
				"	from mt_employee emp left outer join mt_response resp" + 
				"	on emp.pk_emp=resp.pk_emp where emp.year=:year and" + 
				"	(resp.status<>'completed' or resp.status is null)" + 
				"	and emp.usertype='user' and emp.is_active='Y'";
		
	//	String year = "2020";
		Query query = entityManger.createNativeQuery(QUERY);
		query.setParameter("year", year);
		List<Object[]> resultList = query.getResultList();
		
		MTSurvey survey = surveyDao.findOne("year="+year);
		
		EconnectDataDTO dto = new EconnectDataDTO();
		dto.setStart_date(String.valueOf(survey.getStartDate()));
		dto.setEnd_date(String.valueOf(survey.getEndDate()));
		
		List<Object> list = new ArrayList<Object>();
		
		for(Object[] obj : resultList) {
			if(null != obj[1])
				list.add(obj[1]);
		}
		dto.setTokenId(list);
		
		return dto;
	}

}
