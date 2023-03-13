package com.mahindra.annualsurvey.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.dto.SurveyDTO;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTSurvey;
import com.mahindra.annualsurvey.model.MTSurveyConfig;
import com.mahindra.annualsurvey.services.MTEmployeeService;
import com.mahindra.annualsurvey.services.MTResponseService;
import com.mahindra.annualsurvey.services.MTSurveyConfigService;
import com.mahindra.annualsurvey.services.StatusService;
import com.mahindra.annualsurvey.utils.FileBean;


//This controller is called from config,js and is used to create and populate surveys
@Controller
public class MTSurveyConfigController
{
	
	public static final Logger logger = Logger.getLogger(MTSurveyConfigController.class);
	@Autowired
	MTSurveyConfigService mtscConfig;
	
	@Autowired
	StatusService statusService;
	
    @Autowired
    MTEmployeeService mtemployeeservice;
    
    @Autowired
	MTResponseService mtresponseservice;
	//Creating a Syrvey Starts
    
	@RequestMapping(value = "/configInsert", method = RequestMethod.POST)
	public void insert(HttpServletRequest request,
	HttpServletResponse response) throws IOException, ParseException
	{
	   
	  logger.info("INSERT SURVEY CONFIG STARTS");
	  String startDate = request.getParameter("startdate");
	  String endDate = request.getParameter("enddate");
	  String nextDate = request.getParameter("nextdate");
	  String year = request.getParameter("year");
	  java.sql.Date utilstartDate = getUtilDate(startDate);
	  java.sql.Date utilendDate = getUtilDate(endDate);
	  java.sql.Date utilnextDate = getUtilDate(nextDate);
	  int frequency = Integer.parseInt(request.getParameter("frequency"));
	  
	  MTSurvey mtSurveyConfig =new MTSurvey();
	  
	  mtSurveyConfig.setCreatedBy("admin");
	  mtSurveyConfig.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
	  mtSurveyConfig.setFrequency(frequency);
	  mtSurveyConfig.setStartDate(utilstartDate);
	  mtSurveyConfig.setEndDate(utilendDate);
	  mtSurveyConfig.setNextDate(utilnextDate);
	  mtSurveyConfig.setYear(year);
	  System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2"); 
	  boolean alreadyExists = mtscConfig.doesSurveyExist(year);
	  if(alreadyExists==true){
		  response.getWriter().write("exists");
	  }
	  else{
		  logger.info("Into MTsurveyController;Creating a new survey!!");
		  mtscConfig.create(mtSurveyConfig);
		  // mail to all employees here
		  // Boolean isSAPDataIntegrated = empservice.sapEmployeeInt();
	      // statusService.sendMailOnCreate(year); //commented by Rakesh kumar
		  response.getWriter().write("success");
	  }
	  
	   logger.info("INSERT SURVEY CONFIG ENDS");
	}
	
	public java.sql.Date getUtilDate(String date) throws ParseException{
		
		 SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		 Date convertedDate = df.parse(date);
		 java.sql.Date sqlDate = new java.sql.Date(convertedDate.getTime());
		 logger.info("CONVERTED DATE IS "+sqlDate);
		return sqlDate;
		
	}
	//Creating a Syrvey Ends
	
	//Get all surveys start
	/*
	 * @RequestMapping(value="/getSurveys",method=RequestMethod.POST) public void
	 * getAllSurveys(HttpServletRequest request,HttpServletResponse response) throws
	 * IOException{ logger.info("GET ALL SURVEYS START");
	 * 
	 * List<SurveyDTO> surveyDTO=mtscConfig.getAllSurveys();
	 * if(!surveyDTO.isEmpty()){
	 * 
	 * Gson gson = new Gson(); String surveyJSON =gson.toJson(surveyDTO);
	 * logger.info("JSON SURVEY LIST IS "+surveyJSON);
	 * logger.info("SurveyJson is "+surveyJSON); logger.info("GET ALL SURVEYS END");
	 * response.getWriter().write(surveyJSON); } else{
	 * response.getWriter().write("no_survey"); }
	 * 
	 * }
	 */
	@RequestMapping(value="/getSurveys",method=RequestMethod.GET)
	public String getAllSurveys(HttpServletRequest request,HttpServletResponse response,Map<String, Object> model) throws IOException{
		logger.info("GET ALL SURVEYS START");
		List<SurveyDTO> surveyDTO=mtscConfig.getAllSurveys();
		if(!surveyDTO.isEmpty()){
			Gson gson = new Gson();
			String surveyJSON =gson.toJson(surveyDTO);
			logger.info("JSON SURVEY LIST IS "+surveyJSON);
			logger.info("SurveyJson is "+surveyJSON);
			logger.info("GET ALL SURVEYS END");
			response.getWriter().write(surveyJSON);
			return "survey";
		}
		else{
			response.getWriter().write("no_survey");
			return "userScreen";
		}
		
	}
	
	@RequestMapping(value="/getSurveyDtls",method=RequestMethod.GET)
	public String getSurveyDetailsForEmp(HttpServletRequest req, HttpServletResponse res,
			Map<String, Object> model) throws IOException{
		try {
		MTEmployee mtemp = (MTEmployee) req.getSession(false).getAttribute("loginDetails");
		MTSurvey survey= statusService.getSurveyIDUser(mtemp.getYear());
		java.sql.Date d= new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		if(survey.getEndDate().before(d) && !(removeTime(survey.getEndDate()).equals(removeTime(d))))
		{
			return "redirect:/login";		
		}
		model.put("areaquestion",mtemployeeservice.getEmployeeYear(mtemp.getYear(),mtemp.getEmpId(), "Y"));
		model.put("areas",mtemployeeservice.getEmployeeYear(mtemp.getYear(),mtemp.getEmpId(), "N"));
		model.put("empId", mtemp.getEmpId());
		model.put("surveyId", survey.getSurveyId());
		model.put("pkEmp", mtemp.getPkEmp());
        model.put("sector", mtresponseservice.getAllSector());
        model.put("business",mtresponseservice.getAllBusiness());
		model.put("employee", mtemp);
		model.put("sapFlag", mtemp.getSapFlag());
		
		}catch (Exception e) {
			e.printStackTrace();
			return "redirect:/login";
		}
				return "survey";
		}
	
	public Date removeTime(Date date) {    
	    Calendar cal = Calendar.getInstance();  
	    cal.setTime(date);  
	    cal.set(Calendar.HOUR_OF_DAY, 0);  
	    cal.set(Calendar.MINUTE, 0);  
	    cal.set(Calendar.SECOND, 0);  
	    cal.set(Calendar.MILLISECOND, 0);  
	    return cal.getTime(); 
	}
	
	//Get all surveys start
	
}

