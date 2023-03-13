/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mahindra.annualsurvey.controllers.AreaController;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.dto.EmployeeMasterDTO;
import com.mahindra.annualsurvey.dto.ResponseMasterDTO;
import com.mahindra.annualsurvey.model.MTEmployee;



/**
 * @author Yash Mahale
 *
 */
@Service
public class WebServiceImpl implements WebService{

	public static final Logger logger = Logger.getLogger(WebServiceImpl.class);
	@PersistenceContext
	private EntityManager entityManager;
	
	
//	String masterDataQuery = "select emp.email_id,emp.emp_id from emptbl emp";

	@Override
	public List<EmployeeMasterDTO> getEmployees() {
		// TODO Auto-generated method stub
		
		//Query for getting Master Data
		
/*		
		Query masterDataQuery = entityManager.createNativeQuery("select area_emp_id,emp_id,emp.is_Active,emp.year, "
				+ "concat(emp.fname,' ',emp.lname) as fullName,floor(emp.age) as age, aem.area_code, aem.areadesc, "
				+ "emp.sector, emp.sectorOthers ,sec.SectorName, emp.business,emp.businessOthers, "
				+ "country,gender,date_of_birth ,bus.BusinessName,surv.start_date,surv.end_date "
				+ "from area_emp_mapping aem left outer join mt_employee emp on aem.area_emp_id=emp.pk_emp "
				+ "left outer join mt_sector sec on sec.sectorId=emp.sector left outer join mt_business bus on bus.businessId=emp.business "
				+ "left outer join mt_survey surv on surv.year = emp.year order by aem.area_emp_id ");*/   //changed to below by ajay sir for conflict in year
		
		
		
		Query masterDataQuery = entityManager.createNativeQuery ("select * from (select area_emp_id,emp_id,emp.is_Active,aem.year, "
				  +"concat(emp.fname,' ',emp.lname) as fullName,floor(emp.age) as age, aem.area_code, aem.areadesc, "
		          +"emp.sector, emp.sectorOthers ,sec.SectorName, emp.business,emp.businessOthers, "
				  +"country,gender,date_of_birth ,bus.BusinessName,surv.start_date,surv.end_date "
		          +"from area_emp_mapping aem  left outer join mt_employee emp on aem.area_emp_id=emp.pk_emp "
		          +" left outer join mt_sector sec on sec.sectorId=emp.sector "
		          +"left outer join mt_business bus on bus.businessId=emp.business "
		          +"left outer join mt_survey surv on surv.year = emp.year ) as a where  a.year =YEAR(CURDATE())   order by 2 desc");
		
		
		
		
		
		List<Object[]> employees = masterDataQuery.getResultList();
		List<EmployeeMasterDTO> empMasterDTO = new ArrayList<>();
		for (Object[] a : employees) {
			EmployeeMasterDTO empDTO = new EmployeeMasterDTO();
		    logger.info("Employee ID "
		            + a[1]
		            + "Employee Age "
		            + a[5]);
		    
		   /* if(a[0]!=null){
		    	
		    	empDTO.setAreaEmployeeID(a[0].toString());
		    }
		    else{
		    	empDTO.setAreaEmployeeID("");
		    }*/
		    //-------------------------------------------------------------------------------------------
		    if(a[1]!=null){
		    	empDTO.setEmployeeID(a[1].toString());
		    }
		    else{
		    	empDTO.setEmployeeID("");

		    }
		  //-------------------------------------------------------------------------------------------
		    
		    if(a[2]!=null){
		    	empDTO.setParticipation(a[2].toString());
		    }
		    else{
		    	empDTO.setParticipation("");
		    }
		  //-------------------------------------------------------------------------------------------
		  
		    if(a[3]!=null){
		    	empDTO.setFiscalYear(a[3].toString());

		    }
		    else{

		    	empDTO.setFiscalYear("");
		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[4]!=null){
		    	empDTO.setFullName(a[4].toString());

		    }
		    else{
		    	empDTO.setFullName("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[5]!=null){
		    	/*if(String.valueOf((a[5])).contains(".")){
		    		
		    		String age[]=  a[5].toString().split(".");
		    		logger.info("AGE SPLIT is "+age[0]);
		    		empDTO.setEmployeeAge(Integer.parseInt(age[0].toString()));
		    	}
		    	else{
		    		empDTO.setEmployeeAge(Integer.parseInt(a[5].toString()));
		    	}*/
		    	empDTO.setEmployeeAge(a[5].toString());

		    }
		    else{
		    	empDTO.setEmployeeAge("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[6]!=null){
		    	empDTO.setAreaCode(a[6].toString());

		    }
		    else{
		    	empDTO.setAreaCode("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[7]!=null){
		    	empDTO.setAreaDescription(a[7].toString());

		    }
		    else{
		    	empDTO.setAreaDescription("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[8]!=null){
		    	empDTO.setSectorID(a[8].toString());

		    }
		    else{
		    	empDTO.setSectorID("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[9]!=null){
		    	empDTO.setSectorOthers(a[9].toString());

		    }
		    else{
		    	empDTO.setSectorOthers("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[10]!=null){
		    	empDTO.setSector(a[10].toString());

		    }
		    else{
		    	empDTO.setSector("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[11]!=null){
		    	empDTO.setBusiness(a[11].toString());

		    }
		    else{
		    	empDTO.setBusiness("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[12]!=null){
		    	empDTO.setBusinessOthers(a[12].toString());

		    }
		    else{
		    	empDTO.setBusinessOthers("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[13]!=null){
		    	empDTO.setCountry(a[13].toString());

		    }
		    else{
		    	empDTO.setCountry("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[14]!=null){
		    	empDTO.setGender(a[14].toString());

		    }
		    else{
		    	empDTO.setGender("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[15]!=null){
		    	empDTO.setDateOfBirth(a[15].toString());
		    }
		    else{
		    	empDTO.setDateOfBirth("");
		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[16]!=null){
		    	empDTO.setBusinessName(a[16].toString());
		    }
		    else{
		    	empDTO.setBusinessName("");
		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[17]!=null){
		    	empDTO.setStartDate(a[17].toString());
		    }
		    else{
		    	empDTO.setStartDate("");
		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[18]!=null){
		    	empDTO.setEndDate(a[18].toString());
		    }
		    else{
		    	empDTO.setEndDate("");

		    }
		  //-------------------------------------------------------------------------------------------
		    
		    empMasterDTO.add(empDTO);
		}

		
		return empMasterDTO;
	}


	@Override
	public List<ResponseMasterDTO> getResponse() {
		// TODO Auto-generated method stub
		
		//Query for getting Response of the users
		
		/*Query responseDataQuery = entityManager.createNativeQuery("select emp.emp_id,qb.area_code,qb.question_id,qt.pk_question_type,rsd.subquestionId as subquestId, "
				+ "case when qt.pk_question_type=4 then "
				+ "rsd.answer_no else rdt.answer_no end as answer,res.lastupdated_datetime as respondeddate,'2016' as year,res.status "
				+ "from mt_response_dtls rdt "
				+ "inner join mt_response res  on rdt.response_map_id=res.response_id "
				+ "inner join mt_employee emp  on res.pk_emp=emp.pk_emp "
				+ "inner join mt_question qb  on rdt.questionbank_id=qb.question_id "
				+ "inner join mt_question_type qt  on qt.pk_question_type=qb.pk_question_type "
				+ "inner join mt_area ar   on ar.area_code=qb.area_code "
				+ "left outer join mt_sector sec on emp.sector=sec.sectorid "      
				+ "left outer join mt_resp_subquest_dtls rsd on rsd.subquestrespmapid=rdt.resposedetail_id and rsd.questionbank_id=rdt.questionbank_id");*/
		        //changed to below query by Ajay  for conflict in year data
		
		
		  Query responseDataQuery = entityManager.createNativeQuery ( 	" select * from ( select emp.emp_id,qb.area_code,qb.question_id,qt.pk_question_type,rsd.subquestionId as subquestId,   "    
			 +"case when qt.pk_question_type=4 then  rsd.answer_no else rdt.answer_no end as answer,res.lastupdated_datetime as respondeddate,res.year as year,res.status       "
			 +"from mt_response_dtls rdt       "
			 +"inner join mt_response res  on rdt.response_map_id=res.response_id       "
			 +"inner join mt_employee emp  on res.pk_emp=emp.pk_emp       and res.year =YEAR(CURDATE())"
			 +"inner join mt_question qb  on rdt.questionbank_id=qb.question_id "       
			 +"inner join mt_question_type qt  on qt.pk_question_type=qb.pk_question_type       "
			 +"inner join mt_area ar   on ar.area_code=qb.area_code       "
			 +"left outer join mt_sector sec on emp.sector=sec.sectorid    "         
			 +"left outer join mt_resp_subquest_dtls rsd on rsd.subquestrespmapid=rdt.resposedetail_id and rsd.questionbank_id=rdt.questionbank_id) as a where a.year=YEAR(CURDATE())  order by 1 desc") ;

		
		
		
		
		
		
		List<Object[]> responseList = responseDataQuery.getResultList();
		List<ResponseMasterDTO> responseMasterList = new ArrayList<>();
		for (Object[] a : responseList) {
			ResponseMasterDTO responseDTO = new ResponseMasterDTO();
		    logger.info("Employee ID "
		            + a[0]
		            + "Question ID "
		            + a[2]);	
		    
		    
		    if(a[0]!=null){
		    	responseDTO.setEmployeeID(a[0].toString());
		    }
		    else{
		    	responseDTO.setEmployeeID("");
		    }
		    //-------------------------------------------------------------------------------------------
		    if(a[1]!=null){
		    	responseDTO.setAreaCode(a[1].toString());
		    }
		    else{
		    	responseDTO.setAreaCode("");

		    }
		  //-------------------------------------------------------------------------------------------
		    
		    if(a[2]!=null){
		    	responseDTO.setQuestionID(a[2].toString());
		    }
		    else{
		    	responseDTO.setQuestionID("");
		    }
		  //-------------------------------------------------------------------------------------------
		  
		    if(a[3]!=null){
		    	responseDTO.setQuestionType(a[3].toString());
		    }
		    else{
		    	responseDTO.setQuestionType("");;
		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[4]!=null){
		    	responseDTO.setSubQuestionID(a[4].toString());
		    }
		    else{
		    	responseDTO.setSubQuestionID("");
		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[5]!=null){
		    	responseDTO.setAnswer(a[5].toString());
		    }
		    else{
		    	responseDTO.setAnswer("");;

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[6]!=null){
		    	responseDTO.setRespondedDate(a[6].toString());
		    }
		    else{
		    	responseDTO.setRespondedDate("");

		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[7]!=null){
		    	responseDTO.setYear(a[7].toString());
		    }
		    else{
		    	responseDTO.setYear("");
		    }
		  //-------------------------------------------------------------------------------------------
		    if(a[8]!=null){
		    	responseDTO.setStatus(a[8].toString());
		    }
		    else{
		    	responseDTO.setStatus("");

		    }
		  //-------------------------------------------------------------------------------------------  
		    responseMasterList.add(responseDTO);
	}
		return responseMasterList;
	}

}
