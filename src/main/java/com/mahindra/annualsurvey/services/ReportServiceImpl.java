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
import org.springframework.stereotype.Service;

import com.mahindra.annualsurvey.controllers.AreaController;
import com.mahindra.annualsurvey.dto.CommentsDTO;
import com.mahindra.annualsurvey.dto.EmployeeMasterDTO;
import com.mahindra.annualsurvey.dto.OpenEndedDTO;
import com.mahindra.annualsurvey.dto.YesNoDTO;

/**
 * @author Yash Mahale	
 *
 */
@Service
public class ReportServiceImpl implements ReportService{
	
	public static final Logger logger = Logger.getLogger(ReportServiceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<YesNoDTO> getyesNoReport(String year) {
		// TODO Auto-generated method stub
		
		logger.info("Into getyesNoReport in ReportServiceImpl");
		
		Query yesNoQuery = entityManager.createNativeQuery("select * from ( select emp.emp_id,concat(emp.fname,' ',emp.lname) as"
				+ " fullname,qb.area_code as areaCode,ar.area_desc as areaDescription,qb.question_desc as question,qt.question_type_desc as questionType,"
				+ "case when qt.pk_question_type=4 then rsd.answer_no else rdt.answer_no end as answer,surv.year ,res.year as resyear "
				+ "from mt_response_dtls rdt inner join mt_response res  on rdt.response_map_id=res.response_id "
				+ "inner join mt_employee emp  on res.pk_emp=emp.pk_emp "
				+ "inner join mt_question qb  on rdt.questionbank_id=qb.question_id "
				+ "inner join mt_question_type qt  on qt.pk_question_type=qb.pk_question_type "
				+ "inner join mt_area ar   on ar.area_code=qb.area_code "
				+ "left outer join mt_sector sec on emp.sector=sec.sectorid "
				+ "left outer join mt_resp_subquest_dtls rsd on rsd.subquestrespmapid=rdt.resposedetail_id and rsd.questionbank_id=rdt.questionbank_id "
				+ "left outer join mt_survey surv on surv.year="+year+" "
				+ "where qt.pk_question_type =5 ) as a where a.year="+year+" and a.resyear="+year+"  order by 1 desc;");
		
		List<Object[]> yesNoList = yesNoQuery.getResultList();
		List<YesNoDTO> yesNoMasterList = new ArrayList<>();
		for (Object[] yesNoObject : yesNoList) {
			YesNoDTO yesNoDTO = new YesNoDTO();
		    logger.info("Employee ID " + yesNoObject[0]+ "Employee Name "+ yesNoObject[1]);
		    
		   
		    //-------------------------------------------------------------------------------------------
		    if(yesNoObject[0]!=null){
		    	yesNoDTO.setEmplolyeeID(yesNoObject[0].toString());
		    }
		    else{
		    	yesNoDTO.setEmplolyeeID("");

		    }

		    //-------------------------------------------------------------------------------------------
		    if(yesNoObject[1]!=null){
		    	yesNoDTO.setEmployeeName(yesNoObject[1].toString());
		    }
		    else{
		    	yesNoDTO.setEmployeeName("");

		    }
		    
		    //-------------------------------------------------------------------------------------------
		    if(yesNoObject[3]!=null){
		    	yesNoDTO.setAreaDescription(yesNoObject[3].toString());
		    }
		    else{
		    	yesNoDTO.setAreaDescription("");

		    }
		    
		    //-------------------------------------------------------------------------------------------
		    if(yesNoObject[4]!=null){
		    	yesNoDTO.setQuestion(yesNoObject[4].toString());
		    }
		    else{
		    	yesNoDTO.setQuestion("");

		    }
		    
		    //-------------------------------------------------------------------------------------------
		    if(yesNoObject[6]!=null){
		    	yesNoDTO.setAnswer(yesNoObject[6].toString());
		    	
		    }
		    else{
		    	yesNoDTO.setAnswer("");

		    }
		    
		    if(yesNoObject[7]!=null){
		    	yesNoDTO.setYear(yesNoObject[7].toString());
		    	
		    }
		    else{
		    	yesNoDTO.setYear("");

		    }
		    
		    yesNoMasterList.add(yesNoDTO);
		}
		
		return yesNoMasterList;
	}

	@Override
	public List<CommentsDTO> getCommentReport(String year) {
		// TODO Auto-generated method stub
		logger.info("Into ReportServiceImpl for get Comment Report");
		
		Query commentsQuery = entityManager.createNativeQuery("select distinct emp.emp_id,concat(emp.fname,' ',emp.lname) "
				+ "as fullname,ar.area_desc as areaDescription,comments,emp.year ,res.year as resyear from mt_response_dtls rdt "
				+ "inner join mt_response res  on rdt.response_map_id=res.response_id "
				+ "inner join mt_employee emp  on res.pk_emp=emp.pk_emp "
				+ "inner join mt_area ar   on ar.area_code=rdt.area_code "
				+ "left outer join mt_sector sec on emp.sector=sec.sectorid "
				+ "left outer join mt_survey surv on surv.year="+year+" where res.year="+year+" and comments <> '' order by emp.emp_id desc");
		
		List<Object[]> commentsList = commentsQuery.getResultList();
		List<CommentsDTO> commentsMasterList = new ArrayList<>();
		
		for(Object [] commentObject: commentsList){
			CommentsDTO commentsDTO = new CommentsDTO();
			
			 logger.info("Employee ID " + commentObject[0]+ "Employee Name "+ commentObject[1]);
			    
			   
			    //-------------------------------------------------------------------------------------------
			    if(commentObject[0]!=null){
			    	commentsDTO.setEmployeeID(commentObject[0].toString());
			    }
			    else{
			    	commentsDTO.setEmployeeID("");

			    }

			    //-------------------------------------------------------------------------------------------
			    if(commentObject[1]!=null){
			    	commentsDTO.setEmployeeName(commentObject[1].toString());
			    }
			    else{
			    	commentsDTO.setEmployeeName("");

			    }


			    //-------------------------------------------------------------------------------------------
			    if(commentObject[2]!=null){
			    	commentsDTO.setAreaDescription(commentObject[2].toString());
			    }
			    else{
			    	commentsDTO.setAreaDescription("");

			    }


			    //-------------------------------------------------------------------------------------------
			    if(commentObject[3]!=null){
			    	commentsDTO.setComments(commentObject[3].toString());
			    }
			    else{
			    	commentsDTO.setComments("");

			    }
			    
			    if(commentObject[4]!=null){
			    	commentsDTO.setYear(commentObject[4].toString());
			    }
			    else{
			    	commentsDTO.setYear("");

			    }
			    commentsMasterList.add(commentsDTO);
		}
		
		return commentsMasterList;
	}

	@Override
	public List<OpenEndedDTO> getOpenEndedReport(String year) {
		// TODO Auto-generated method stub
		
		Query openEndedQuery = entityManager.createNativeQuery("select emp.emp_id,concat(emp.fname,' ',emp.lname) "
				+ "as fullname,qb.area_code as areacode,ar.area_desc as areadesc,qb.question_desc as questionDescription,qt.question_type_desc as questionType, "
				+ "case when qt.pk_question_type=4 then rsd.answer_no else rdt.answer_text end as answer,surv.year from mt_response_dtls rdt "
				+ "inner join mt_response res  on rdt.response_map_id=res.response_id "
				+ "inner join mt_employee emp  on res.pk_emp=emp.pk_emp inner join mt_question qb  on rdt.questionbank_id=qb.question_id inner join mt_question_type qt "
				+ " on qt.pk_question_type=qb.pk_question_type "
				+ "inner join mt_area ar   on ar.area_code=qb.area_code "
				+ "left outer join mt_sector sec on emp.sector=sec.sectorid "
				+ "left outer join mt_resp_subquest_dtls rsd on rsd.subquestrespmapid=rdt.resposedetail_id and rsd.questionbank_id=rdt.questionbank_id "
				+ "left outer join mt_survey surv on surv.year="+year+" "
				+ "where qt.pk_question_type =2 and res.year="+year+" order by emp.emp_id desc;");
		
		List<Object[]> openEndedList = openEndedQuery.getResultList();
		List<OpenEndedDTO> openEndedMasterList = new ArrayList<>();
		
		for(Object[] openEndedObject:openEndedList){
			OpenEndedDTO openEndedDTO = new OpenEndedDTO();
			 logger.info("Employee ID " + openEndedObject[0]+ "Employee Name "+ openEndedObject[1]);
			    
			   
			    //-------------------------------------------------------------------------------------------
			    if(openEndedObject[0]!=null){
			    	openEndedDTO.setEmployeeID(openEndedObject[0].toString());
			    }
			    else{
			    	openEndedDTO.setEmployeeID("");

			    }
			    
			    //-------------------------------------------------------------------------------------------
			    if(openEndedObject[1]!=null){
			    	openEndedDTO.setEmployee_Name(openEndedObject[1].toString());
			    }
			    else{
			    	openEndedDTO.setEmployee_Name("");

			    }
			    
			    //-------------------------------------------------------------------------------------------
			    if(openEndedObject[3]!=null){
			    	openEndedDTO.setArea(openEndedObject[3].toString());
			    }
			    else{
			    	openEndedDTO.setArea("");

			    }
			    
			    //-------------------------------------------------------------------------------------------
			    if(openEndedObject[4]!=null){
			    	openEndedDTO.setQuestion(openEndedObject[4].toString());
			    }
			    else{
			    	openEndedDTO.setQuestion("");

			    }
			
			    
			    //-------------------------------------------------------------------------------------------
			    if(openEndedObject[6]!=null){
			    	openEndedDTO.setAnswer(openEndedObject[6].toString());
			    }
			    else{
			    	openEndedDTO.setAnswer("");

			    }
			
			    //-------------------------------------------------------------------------------------------
			    if(openEndedObject[7]!=null){
			    	openEndedDTO.setYear(openEndedObject[7].toString());
			    }
			    else{
			    	openEndedDTO.setYear("");

			    }
			    openEndedMasterList.add(openEndedDTO);
		}
		
		return openEndedMasterList;
	}

	@Override
	public void downloadReports(String area_code, String year) {
		
		final String REPORT_QUERY="SELECT respdtl.resposedetail_id,respdtl.answer_no,respdtl.answer_text,respdtl.comments,respdtl.questionbank_id," + 
				"que.question_desc,respdtl.questionType,respdtl.area_code,response_map_id" + 
				" FROM annual_survey.mt_response_dtls respdtl" + 
				" inner join annual_survey.mt_question que on que.question_id= respdtl.questionbank_id" + 
				" where response_map_id in(Select resp.response_id from annual_survey.mt_response resp where resp.pk_emp" + 
				" in(SELECT aem.area_emp_id FROM annual_survey.area_emp_mapping aem where aem.area_code=25 and year=2020)) and respdtl.area_code=25";
		
		final String SUB_QUES_RESPONSE_QRY="SELECT resd.subquestrespdtl_id,resd.answer_no,subque.sub_question_desc,resd.subquestrespmapid,subque.sub_question_id FROM annual_survey.mt_resp_subquest_dtls resd" + 
				"inner join annual_survey.mt_sub_question subque on subque.sub_question_id = resd.subquestionId\r\n" + 
				"where subquestrespmapid=13324";
		
		Query report = entityManager.createNativeQuery(REPORT_QUERY);
		List<Object[]> reportResult = report.getResultList();
		
	}

}
