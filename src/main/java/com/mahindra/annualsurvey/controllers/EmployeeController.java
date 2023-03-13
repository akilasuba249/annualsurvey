package com.mahindra.annualsurvey.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.services.MTEmployeeService;

@Controller
public class EmployeeController {
	
	@InitBinder
		public void initBinder(WebDataBinder binder) {
		    binder.setAutoGrowCollectionLimit(1024);
		}
	
	public static final Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	MTEmployeeService mtemployeeservice;

	@RequestMapping(value = { "employee" })
	public ModelAndView getEmployee(HttpServletRequest req,	HttpServletResponse res) {
		ModelAndView view = new ModelAndView();
		view.setViewName("employee");
		return view;
	}

	@RequestMapping(value = "/empinsert", method = RequestMethod.POST)
	public void insert(HttpServletRequest request,HttpServletResponse response,Map<String, Object> model,@ModelAttribute("EmployeeDTO") EmployeeDTO emp) throws IOException,
			JSONException {

		
		
		List<MTEmployee> mtemp = mtemployeeservice.get(emp.getEmpId());

		Iterator<MTEmployee> iter = mtemp.iterator();

		if (iter.hasNext()) {
			MTEmployee mt = iter.next();
			emp.setPkEmp(mt.getPkEmp());
			emp.setfName(mt.getfName());
			emp.setlName(mt.getlName());
			mtemployeeservice.updateEmp(emp);

		} else {
			mtemployeeservice.create(emp);
			//mtemployeeservice.sapEmployeeIntCreate(emp.getEmpId()); // removed by ajay sir for update employee issue edited by ajay sir
		}
		List<MTEmployee> opportunities = mtemployeeservice.getEmployeeListYearArea(request.getParameter("areaAdd"), emp.getYear());

		JSONArray arr = new JSONArray();
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");		
		for (int i = 0; i < opportunities.size(); i++) {
			arr.put(new JSONObject(opportunities.get(i).toString()));
		}

		pw.write(arr.toString());

		pw.flush();
		pw.close();
	}

	@RequestMapping(value = "/empupdate", method = RequestMethod.POST)
	public void update(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			@ModelAttribute("EmployeeDTO") EmployeeDTO emp) throws IOException,
			JSONException {

		mtemployeeservice.update(emp); // update employee
		List<MTEmployee> opportunities = mtemployeeservice.getEmployeeList();

		JSONArray arr = new JSONArray();
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		for (int i = 0; i < opportunities.size(); i++) {
			arr.put(new JSONObject(opportunities.get(i).toString()));
		}

		pw.write(arr.toString());

		pw.flush();
		pw.close();

	}

	@RequestMapping(value = "/updateEmp", method = RequestMethod.POST)
	public void empupdate(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			@ModelAttribute("EmployeeDTO") EmployeeDTO emp) throws IOException,
			JSONException {

		mtemployeeservice.updateEmp(emp);
		logger.info("Its year" + request.getParameter("year"));
		List<MTEmployee> opportunities = mtemployeeservice.getEmployeeListYearArea(request.getParameter("areaAdd"), request.getParameter("year"));
		
		JSONArray arr = new JSONArray();
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		for (int i = 0; i < opportunities.size(); i++) {
			arr.put(new JSONObject(opportunities.get(i).toString()));
		}

		pw.write(arr.toString());

		pw.flush();
		pw.close();

	}

	@RequestMapping(value = "getEmployee")
	public void getEmployeeList(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, JSONException {

		List<MTEmployee> opportunities = mtemployeeservice
				.getEmployeeListYear(req.getParameter("2020"));
         
		JSONArray arr = new JSONArray();
		PrintWriter pw = resp.getWriter();
		resp.setContentType("application/json");
		//resp.setCharacterEncoding("utf-8");
		  
		for (int i = 0; i < opportunities.size(); i++) {
			arr.put(new JSONObject(opportunities.get(i).toString()));
		}
		pw.write(arr.toString());

		logger.info(arr.toString());
    	logger.info("Test Changes"+arr.toString());
    	
		pw.flush();
		pw.close();

	}

	@RequestMapping(value = "getEmployeeArea")
	public void getEmplpyeeArea(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, JSONException {

		List<MTEmployee> opportunities = mtemployeeservice
				.getEmployeeListYearArea(req.getParameter("area"),
						req.getParameter("year"));

		JSONArray arr = new JSONArray();
		PrintWriter pw = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		
		for (int i = 0; i < opportunities.size(); i++) {
			arr.put(new JSONObject(opportunities.get(i).toString()));
		}

		pw.write(arr.toString());

		pw.flush();
		pw.close();

	}

	@RequestMapping(value = "/empdelete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model,
			@ModelAttribute("MTEmployee") MTEmployee emp) throws IOException,
			JSONException {
        logger.info("done delete operation");
		mtemployeeservice.deletebyId(emp.getPkEmp(),emp.getYear());
		List<MTEmployee> opportunities =mtemployeeservice
		.getEmployeeListYearArea(request.getParameter("areaAdd"),emp.getYear());
		JSONArray arr = new JSONArray();
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		 
		for (int i = 0; i < opportunities.size(); i++) {
			arr.put(new JSONObject(opportunities.get(i).toString()));
		}

		pw.write(arr.toString());

		pw.flush();
		pw.close();

	}

}
