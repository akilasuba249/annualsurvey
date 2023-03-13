/**
 * 
 */
package com.mahindra.annualsurvey.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.mahindra.annualsurvey.dto.CommentsDTO;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.dto.OpenEndedDTO;
import com.mahindra.annualsurvey.dto.YesNoDTO;
import com.mahindra.annualsurvey.services.ReportService;

/**
 * @author Yash Mahale
 *
 */
@Controller
public class ReportController {
	public static final Logger logger = Logger.getLogger(ReportController.class);
	@Autowired
	private ReportService reportService;
	
	
	@RequestMapping(value={"/getYesNoReport"},method=RequestMethod.POST)
	public void getYesNoReport(HttpServletRequest req, HttpServletResponse res ) throws IOException {
		String year=req.getParameter("year");
		logger.info("Into getYesNoReport in Report Controller for getting Yes No Report");
		List<YesNoDTO> yesNoList = reportService.getyesNoReport(year);
		Gson gson = new Gson();
		String yesNoString = gson.toJson(yesNoList);
		res.getWriter().write(yesNoString);
 		
	}
	
	@RequestMapping(value={"/getCommentsReport"},method=RequestMethod.POST)
	public void getCommentsReport(HttpServletRequest req, HttpServletResponse res ) throws IOException {
		logger.info("Into getCommentsReport in Report Controller for getting Yes No Report");
		String year=req.getParameter("year");
		List<CommentsDTO> commentList = reportService.getCommentReport(year);
		Gson gson = new Gson();
		String commentString = gson.toJson(commentList);
		res.getWriter().write(commentString);
 		
	}
	
	@RequestMapping(value={"/getOpenEndedReport"},method=RequestMethod.POST)
	public void getOpenEndedReport(HttpServletRequest req, HttpServletResponse res ) throws IOException {
		logger.info("Into getOpenEndedReport in Report Controller for getting Yes No Report");
		String year=req.getParameter("year");
		
		List<OpenEndedDTO> openEndedList = reportService.getOpenEndedReport(year);
		Gson gson = new Gson();
		String openEndedString = gson.toJson(openEndedList);
		res.getWriter().write(openEndedString);
 		
	}
	
	//download report

}
