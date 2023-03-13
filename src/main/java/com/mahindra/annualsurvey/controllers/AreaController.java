package com.mahindra.annualsurvey.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.mahindra.annualsurvey.dto.AreaDTO;
import com.mahindra.annualsurvey.dto.AreaYearMappingDTO;
import com.mahindra.annualsurvey.filter.CrossScriptingFilter;
import com.mahindra.annualsurvey.filter.MultipartXSSFilter;
import com.mahindra.annualsurvey.model.MTArea;
import com.mahindra.annualsurvey.services.AreaYearMappingService;
import com.mahindra.annualsurvey.services.MTAreaService;
import com.mahindra.annualsurvey.services.StatusService;

@Controller
public class AreaController {
	
	public static final Logger logger = Logger.getLogger(AreaController.class);
	
	@Autowired
	private MTAreaService areaService;
	
	@Autowired
	private AreaYearMappingService areaYearMapService;
	
	@Autowired
	private MultipartXSSFilter multipart; 
	
@RequestMapping(value={"/addArea"},method=RequestMethod.POST)
public void addArea(HttpServletRequest req,HttpServletResponse res,Map<String, Object> model, @ModelAttribute("AreaDTO")AreaDTO area1) throws IOException{
	logger.info("Into Area Controller to for ADD AREA");
	AreaDTO area = new AreaDTO();
	String areaDesc =req.getParameter("area"); 
	area.setAreaDesc(areaDesc);
	logger.info("Area Desc is "+area.getAreaDesc());
	
	boolean flag=true;		
	
	
	MultipartXSSFilter mult = new MultipartXSSFilter();
	if(areaDesc!=null)
    { 
    	String b = mult.cleanXSS(areaDesc);
    	if(!(areaDesc.equalsIgnoreCase(b)))
    	{
    		flag=false;	
    		System.out.println("Velnerable");
    	}
    	
    }	
	if(flag){
		//if multipart does not have cross site script then this code will get execute
		System.out.println("XXX NOt found");
		areaService.create(area);
		res.getWriter().write("200");
	}	
}
@RequestMapping(value={"/editArea"},method=RequestMethod.POST)
public void editArea(HttpServletRequest req,HttpServletResponse res,Map<String, Object> model) throws IOException{
	logger.info("Into Area Controller to for EDIT AREA");
	AreaDTO area = new AreaDTO();
	String areaCode = req.getParameter("areaCode");
	String areaDesc =req.getParameter("area");
	area.setAreaCode(Long.valueOf(areaCode));
	area.setAreaDesc(areaDesc);
	logger.info("Area Desc is "+area.getAreaDesc());
	
	boolean flag=true;		
	
	
	MultipartXSSFilter mult = new MultipartXSSFilter();
	if(areaDesc!=null)
    { 
    	String b = mult.cleanXSS(areaDesc);
    	if(!(areaDesc.equalsIgnoreCase(b)))
    	{
    		flag=false;	
    		System.out.println("Velnerable");
    	}
    	
    }	
	if(flag){
		//if multipart does not have cross site script then this code will get execute
		System.out.println("XXX NOt found");
		areaService.updateArea(area);
		res.getWriter().write("200");
	}	
}

@RequestMapping(value={"/getAreas"},method=RequestMethod.POST)
public void getAreas(HttpServletRequest req,HttpServletResponse res) throws IOException{
	logger.info("Into Area Controller to for GET AREAS");
	List<AreaDTO> areas = areaService.getArea();
	logger.info("areas are "+areas);
	
	/*Iterator<MTArea> iterator=areas.iterator();
	
	while (iterator.hasNext()) {
		MTArea areaObj = iterator.next();
		logger.info(areaObj.getAreaCode());
		logger.info(areaObj.getAreaDesc());
	}*/
	
	Gson gson = new Gson();
	String areaJson =gson.toJson(areas);
	logger.info("areaJson is "+areaJson);
	//method name filternotice chat() // 361
	res.getWriter().write(areaJson);
	
}
@RequestMapping(value={"/getAreasForYear"},method=RequestMethod.POST)
public void getAreasForYear(HttpServletRequest req,HttpServletResponse res,Map<String, Object> model) throws IOException{
	logger.info("Into Area Controller to for Get areas for a particular year");
	String yearId =req.getParameter("selectedYear");
	logger.info("Selected Year is "+yearId);
	List<AreaDTO> activeAreas= areaService.selectAreas(yearId);
	
	boolean isEmpty = activeAreas.isEmpty();
		Gson gson = new Gson();
		String areaYearJson =gson.toJson(activeAreas);
		logger.info("areaJson is "+areaYearJson);
		res.getWriter().write(areaYearJson);	
	
	
	
	
	
}
/*//not being used
@RequestMapping(value={"/getYears"},method=RequestMethod.POST)
public void getYears(HttpServletRequest req,HttpServletResponse res) throws IOException{
	logger.info("Into Area Controller to for GET AREAS");
	List<Integer> fiscalYear = new ArrayList<>();
	fiscalYear = FiscalDate.getFiscalDate();
	
	Iterator<Integer> iterator=fiscalYear.iterator();
	
	while (iterator.hasNext()) {
			logger.info("Years are "+iterator.next() );
	}
	String fiscalYearString=fiscalYear.toString();
	logger.info("FISCAL YEAR TO STRING IS"+fiscalYearString);
	res.getWriter().write(fiscalYearString);
}*/

@RequestMapping(value={"/mapArea"},method=RequestMethod.POST)
public void mapArea(HttpServletRequest req,HttpServletResponse res) throws IOException{
	logger.info("Into Area Controller to for MAP AREA INSERT/UPDATE");
	AreaYearMappingDTO areaMap = new AreaYearMappingDTO();
	
	logger.info("");
	logger.info("YEAR IS "+req.getParameter("year"));
	logger.info("CODE FOR AREA IS "+req.getParameter("code"));
	logger.info("");
	
	areaMap.setAreaCode(Integer.parseInt(req.getParameter("code")));
	areaMap.setYear(req.getParameter("year"));
	
	areaYearMapService.create(areaMap);
	res.getWriter().write("200");
	
}

@RequestMapping(value={"/deleteArea"},method=RequestMethod.POST)
public void deleteArea(HttpServletRequest req,HttpServletResponse res) throws IOException{
	logger.info("Into Area Controller to for DELETE MAPPED AREA");
	logger.info("Into Area Controller to for MAP AREA INSERT/UPDATE");
	AreaYearMappingDTO areaMap = new AreaYearMappingDTO();
	
	logger.info("");
	logger.info("YEAR IS "+req.getParameter("year"));
	logger.info("CODE FOR AREA IS "+req.getParameter("code"));
	logger.info("");
	
	areaMap.setAreaCode(Integer.parseInt(req.getParameter("code")));
	areaMap.setYear(req.getParameter("year"));
	
	areaYearMapService.delete(areaMap);
	res.getWriter().write("200");
	
}
@RequestMapping(value={"/deleteMTArea"},method=RequestMethod.POST)
public void deleteMTArea(HttpServletRequest req,HttpServletResponse res) throws IOException{
	
	
	String areaCode = req.getParameter("areaCode");
	try {
	areaService.deleteArea(areaCode);
	res.getWriter().write("200");
	}catch(Exception e) {
		res.getWriter().write("Area has been mapped to employee");
	}
}
}
