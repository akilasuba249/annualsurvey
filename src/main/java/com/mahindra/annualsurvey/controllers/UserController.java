package com.mahindra.annualsurvey.controllers;

import java.io.IOException;


import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mahindra.annualsurvey.dao.MTResponseDAO;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.model.MTBusiness;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTLoginHistory;
import com.mahindra.annualsurvey.model.MTResponse;
import com.mahindra.annualsurvey.model.MTResponseDetails;
import com.mahindra.annualsurvey.model.MTSurvey;
import com.mahindra.annualsurvey.services.LoginUserService;
import com.mahindra.annualsurvey.services.MTAreaService;
import com.mahindra.annualsurvey.services.MTEmployeeService;
import com.mahindra.annualsurvey.services.MTResponseService;
import com.mahindra.annualsurvey.services.MTSurveyConfigService;
import com.mahindra.annualsurvey.services.StatusService;
import com.mahindra.annualsurvey.springsecurityconfig.CustomSuccessHandler;

@Controller
public class UserController {
	public static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	MTAreaService mtareaservice;

	@Autowired
	MTEmployeeService mtemployeeservice;

	@Autowired
	MTResponseService mtresponseservice;

	@Autowired
	StatusService statusService;
	@Autowired
	MTResponseDAO mtResponseDao;
	
	@Autowired
	LoginUserService loginUserService;
	
	@Autowired
	MTSurveyConfigService mtscConfig;
	
	@RequestMapping(value = { "/user" })
	public String getArea(HttpServletRequest req, HttpServletResponse res,
			Map<String, Object> model) throws IOException {
		
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
			
		/*	MTLoginHistory mtLoginHistory=new MTLoginHistory();
			mtLoginHistory=(MTLoginHistory)req.getSession(false).getAttribute("loginHistory");
			loginUserService.createLoginHistory(mtLoginHistory);
		*/	
	         	
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/login";
		}
		finally
		{
			//return "redirect:/login";
		}
				return "userScreen";
	}

	@RequestMapping(value = { "/responseAdd" })
	public String saveResponse(HttpServletRequest req, HttpServletResponse res,
			Map<String, Object> model,
			@ModelAttribute("MTResponse") MTResponse mtresp) throws IOException {
		
		MTEmployee mtemp = (MTEmployee) req.getSession(false).getAttribute("loginDetails");
		MTSurvey survey= statusService.getSurveyIDUser(mtemp.getYear());
		
		mtemployeeservice.getEmployeeYear(mtemp.getYear(),mtemp.getEmpId(), "Y");
		mtemployeeservice.getEmployeeYear(mtemp.getYear(),mtemp.getEmpId(), "N");
        mtresponseservice.getAllSector();
        mtresponseservice.getAllBusiness();
		
		mtresp.setEntryDateTime(new Timestamp(System.currentTimeMillis()));
		mtresp.setLastUpdatedDateTime(new Timestamp(System.currentTimeMillis()));
		logger.info("tesing "+req.getParameter("pkEmp"));
		mtresp.setStatus("progress");
		mtresp.setIsActive("Y");
		mtresp.setYear(Year.now().toString());
		MTEmployee m = new MTEmployee();
		//m.setPkEmp(Long.parseLong(req.getParameter("pkEmp")));
		m.setPkEmp(mtemp.getPkEmp());
		mtresp.setMtEmployee(m);
		
		System.out.println("req.getAttribute(operation) >>"+req.getAttribute("operation"));
		System.out.println("req.getAttribute(responsemap) >>"+req.getAttribute("responsemap"));
		
		List<MTResponseDetails> mt= statusService.getEmpResponse(String.valueOf(survey.getSurveyId()),String.valueOf(mtemp.getPkEmp()));
		String operation =  "N";
		long responseid = 0;
		
		if(mt != null && mt.size()>0) {
		 responseid = mt.get(0).getTblresponse().getResponseId();
		operation = "Y";
		}
		
		
		//if("N".equalsIgnoreCase(req.getParameter("operation")))
		if("N".equalsIgnoreCase(operation))	
		{	
		mtresp.setOperation("Y"); //Added by nikita	
		mtresponseservice.create(mtresp);
		}
		else
		{
			mtresp.setIsActive("Y");			
			//mtresp.setResponseId(Long.parseLong(req.getParameter("responsemap")));
			mtresp.setResponseId(responseid);
			//mtresp.setResponseId();
		mtresponseservice.update(mtresp);
		}	
		return "userScreen";
	}
 
	@RequestMapping(value = { "/responseSubmit" })
	public String submitResponse(HttpServletRequest req, HttpServletResponse res,
			Map<String, Object> model,
			@ModelAttribute("MTResponse") MTResponse mtresp) throws IOException {
		
		MTEmployee mtemp = (MTEmployee) req.getSession(false).getAttribute("loginDetails");
		MTSurvey survey= statusService.getSurveyIDUser(mtemp.getYear());
		
		mtemployeeservice.getEmployeeYear(mtemp.getYear(),mtemp.getEmpId(), "Y");
		mtemployeeservice.getEmployeeYear(mtemp.getYear(),mtemp.getEmpId(), "N");
        mtresponseservice.getAllSector();
        mtresponseservice.getAllBusiness();
		
		mtresp.setEntryDateTime(new Timestamp(System.currentTimeMillis()));
		mtresp.setLastUpdatedDateTime(new Timestamp(System.currentTimeMillis()));
		mtresp.setStatus("completed");
		mtresp.setIsActive("Y");		
		MTEmployee m = new MTEmployee();
		
		//m.setPkEmp(Long.parseLong(req.getParameter("pkEmp")));
		m.setPkEmp(mtemp.getPkEmp());
		mtresp.setMtEmployee(m);
		 
		System.out.println("Response >>>>"+mtresp.getOperation());
		
		System.out.println("req.getAttribute(operation) >>"+req.getAttribute("operation"));
		System.out.println("req.getAttribute(responsemap) >>"+req.getAttribute("responsemap"));

		
		logger.info(req.getParameter("pkEmp"));
		
		//String operation = mtresponseservice.doesSurveyExists( survey.getSurveyId(),mtemp.getPkEmp()); //Added by nikita
		
		List<MTResponseDetails> mt= statusService.getEmpResponse(String.valueOf(survey.getSurveyId()),String.valueOf(mtemp.getPkEmp()));
		String operation =  "N";
		long responseid = 0;
		
		if(mt != null && mt.size()>0) {
		 responseid = mt.get(0).getTblresponse().getResponseId();
		operation = "Y";
		}
		
		if("N".equalsIgnoreCase(operation))
		{	
		mtresponseservice.create(mtresp);
		mtresp.setOperation("Y");
		}
		else
		{
		//mtresp.setResponseId(Long.parseLong(req.getParameter("responsemap")));
			mtresp.setResponseId(responseid);
			mtresponseservice.update(mtresp);
		}
		
		
		/*if("N".equalsIgnoreCase(req.getParameter("operation")))
		{	
		mtresponseservice.create(mtresp);
		}
		else
		{
		mtresp.setResponseId(Long.parseLong(req.getParameter("responsemap")));
		mtresponseservice.update(mtresp);
		}*/	
		return "userScreen";
	}

	
	@RequestMapping(value={"/getEmpresponse"},method = RequestMethod.POST)
	public MTResponse getResponse(HttpServletRequest req, HttpServletResponse res) throws IOException, JSONException
	{
		 MTEmployee mtemp = (MTEmployee)
		  req.getSession(false).getAttribute("loginDetails");
		  
		  MTSurvey survey= statusService.getSurveyIDUser(mtemp.getYear());
		  int surveyid = survey.getSurveyId();

			mtemployeeservice.getEmployeeYear(mtemp.getYear(),mtemp.getEmpId(), "Y");
			mtemployeeservice.getEmployeeYear(mtemp.getYear(),mtemp.getEmpId(), "N");
            mtresponseservice.getAllSector();
            mtresponseservice.getAllBusiness();
		  
		  String x = req.getParameter("surveyId1");
		 
		//System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"+x);
		
		//List<MTResponseDetails> mt= statusService.getEmpResponse(req.getParameter("surveyId1"),req.getParameter("pkEmp1"));
		
		List<MTResponseDetails> mt= statusService.getEmpResponse(String.valueOf(surveyid),String.valueOf(mtemp.getPkEmp()));
		JSONArray arr = new JSONArray();
		
		Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();   // html escape characters
		
		PrintWriter pw = res.getWriter();
		//res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		for (int i = 0; i < mt.size(); i++) {
			//logger.info(mt.get(i).toString());
			
			if(mt.get(i) != null || !("").equals(mt.get(i).toString()) )
			//if(mt.get(i) != null )
			{
				arr.put(new JSONObject(mt.get(i).toString().replace("\r\n", " "))); // html escape char
			}
				//arr.put(new JSONObject(mt.get(i))); // html escape char
		}
		pw.write(arr.toString());
		//pw.write(arr);

		pw.flush();
		pw.close();

		return null;
	}
	
	@RequestMapping(value = { "/demograpic" })
	public String saveUser(HttpServletRequest req, HttpServletResponse res,
			Map<String, Object> model,
			@ModelAttribute("EmployeeDTO") MTEmployee mtemp) throws IOException, ParseException {
		
	if(mtemp == null || mtemp.getEmpId() == null ) {
		 mtemp = (MTEmployee) req.getSession(false).getAttribute("loginDetails");
	}
		
	MTEmployee mt= new MTEmployee();
	mt.setEmpId(mtemp.getEmpId());
	mt.setCity(mtemp.getCity());
	mt.setCountry(mtemp.getCountry());
	mt.setModifiedBy(mtemp.getEmpId());
	mt.setDateOfBirth(mtemp.getDateOfBirth());
	mt.setGender(mtemp.getGender());
	mt.setfName(mtemp.getfName());
	mt.setlName(mtemp.getlName());
	mt.setBusinessOthers(mtemp.getBusinessOthers());
	mt.setSectorOthers(mtemp.getSectorOthers());
	mt.setSector(mtemp.getSector());
	mt.setModifiedDateTime(new Timestamp(System
			.currentTimeMillis()));
	mt.setBusiness(mtemp.getBusiness());
	Calendar dob = Calendar.getInstance();
	String Date_format="YYYY-MM-DD";
	SimpleDateFormat sdf= new SimpleDateFormat(Date_format);
	/*Date date2=sdf.parse(mtemp.getDateOfBirth());
	dob.setTime(date2);
	mt.setDateOfBirth(mtemp.getDateOfBirth());
	Calendar today=Calendar.getInstance();
	float age=today.get(Calendar.YEAR)-dob.get(Calendar.YEAR);
	mt.setAge(age);*/
	mtemployeeservice.updateEmpSAPDetailsUser(mt);
	
		return "userScreen";
	}

	@RequestMapping(value={"/getEmpdata"})
	public MTResponse getUserData(HttpServletRequest req, HttpServletResponse res) throws IOException, JSONException
	{
		
		List<MTEmployee> mte= mtemployeeservice.get(req.getParameter("emailId"));
		JSONArray arr = new JSONArray();
		PrintWriter pw = res.getWriter();
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");

		for (int i = 0; i < mte.size(); i++) {
			arr.put(new JSONObject(mte.get(i).toString()));
		}

		pw.write(arr.toString());

		pw.flush();
		pw.close();

		return null;
	}

	@RequestMapping(value={"/getBusinessDetails"})
	public MTResponse getBusinessDetails(HttpServletRequest req, HttpServletResponse res) throws IOException, JSONException
	{
		
		List<MTBusiness> mte=mtresponseservice.getBusinessSector(req.getParameter("sect"));
				
				
		JSONArray arr = new JSONArray();
		PrintWriter pw = res.getWriter();
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");

		for (int i = 0; i < mte.size(); i++) {
			arr.put(new JSONObject(mte.get(i).toString()));
		}

		pw.write(arr.toString());

		pw.flush();
		pw.close();

		return null;
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
	
	@RequestMapping(value = { "/thankyouurl" })
	public String thankyou() {
		return "thankyou";
	}
	
	
}
