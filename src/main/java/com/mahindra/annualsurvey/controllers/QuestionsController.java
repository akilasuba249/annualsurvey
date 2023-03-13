package com.mahindra.annualsurvey.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.dto.QuestionDTO;
import com.mahindra.annualsurvey.dto.QuestionListDTO;
import com.mahindra.annualsurvey.model.MTQuestion;
import com.mahindra.annualsurvey.model.MTSubQuestion;
import com.mahindra.annualsurvey.services.MTQuestionService;
import com.mahindra.annualsurvey.utils.FileBean;

@Controller
public class QuestionsController {
	static final Logger logger = Logger.getLogger(QuestionsController.class);
	private static List<QuestionDTO> questionList;
	@Autowired
	MTQuestionService mtquestionservice;
	
	/* Author-->Pooja --Question Starts */
	@RequestMapping(value = { "/question" })
	public ModelAndView getQuestions(HttpServletRequest req, HttpServletResponse res, Map<String, Object> model,
			@ModelAttribute("EmployeeDTO") EmployeeDTO emp, @ModelAttribute("question") MTQuestion question,
			@ModelAttribute("FileBean") FileBean uploadItem) {
		logger.info("Get All Questions");
		ModelAndView view = new ModelAndView();
		model.put("questions", mtquestionservice.getQuestions());
		view.setViewName("admin");
		return view;
	}

	@RequestMapping(value = "/questioninsert", method = RequestMethod.POST)
	public void insert(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model,
			@ModelAttribute("QuestionDTO") QuestionDTO question) throws IOException {
		logger.info("Insert Questions");
		if(question.getQuestionDesc()!=null)
			question.setQuestionDesc(question.getQuestionDesc().trim());
		
		mtquestionservice.create(question);
		List<MTQuestion> questList = mtquestionservice.getQuestions();
		JSONArray arr = new JSONArray();
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		for (int i = 0; i < questList.size(); i++) {
			try {
				arr.put(new JSONObject(questList.get(i).toString()));
				logger.info("Question Id " + questList.get(i).getQuestionId());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pw.write(arr.toString());
		pw.flush();
		pw.close();
	}

	@RequestMapping(value = "/questionupdate", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model,
			@ModelAttribute("QuestionDTO") QuestionDTO question) throws IOException {
		logger.info("Update Questions");
		mtquestionservice.update(question);
		List<MTQuestion> questList = mtquestionservice.getQuestions();
		JSONArray arr = new JSONArray();
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		for (int i = 0; i < questList.size(); i++) {
			try {
				arr.put(new JSONObject(questList.get(i).toString()));
				logger.info("Question Id " + questList.get(i).getQuestionId());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pw.write(arr.toString());
		pw.flush();
		pw.close();
	}

	@RequestMapping(value = "/questiondelete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model,
			@ModelAttribute("QuestionDTO") QuestionDTO question) throws IOException {
		logger.info("Delete Questions");
		long qId = question.getQuestionId();
	//	mtquestionservice.deletebyId(qId);
		boolean isQuestUpdated=	mtquestionservice.updateQuestStatus(qId);
		List<MTQuestion> questList = mtquestionservice.getQuestions();
		JSONArray arr = new JSONArray();
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		for (int i = 0; i < questList.size(); i++) {
			try {
				arr.put(new JSONObject(questList.get(i).toString()));
				logger.info("Question Id " + questList.get(i).getQuestionId());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pw.write(arr.toString());
		pw.flush();
		pw.close();
	}

	
	@RequestMapping(value = { "/questionListbyYear" })
	public void getQuestionsByYear(HttpServletRequest req, HttpServletResponse res, Map<String, Object> model,
			@ModelAttribute("EmployeeDTO") EmployeeDTO emp, @ModelAttribute("question") MTQuestion question,
			@ModelAttribute("FileBean") FileBean uploadItem)throws IOException {
		logger.info("Get Questions By Year"+question.getYearMap().getPkYear());
		

		List<MTQuestion> questList = mtquestionservice.getQuestionByYear(question.getYearMap().getPkYear());
		JSONArray arr = new JSONArray();
		PrintWriter pw = res.getWriter();
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		
		for (int i = 0; i < questList.size(); i++) {
			try {
				logger.info(questList.get(i).toString());
				arr.put(new JSONObject(questList.get(i).toString()));
				} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		res.setCharacterEncoding("utf-8");
		pw.write(arr.toString());
		pw.flush();
		pw.close();

		
	}



	@RequestMapping(value = { "/questionListbyAreaYear" })
	public void getQuestionsByAreaYear(HttpServletRequest req, HttpServletResponse res, Map<String, Object> model,
			@ModelAttribute("EmployeeDTO") EmployeeDTO emp, @ModelAttribute("question") MTQuestion question,
			@ModelAttribute("FileBean") FileBean uploadItem)throws IOException {
		logger.info("Get Questions By Year"+question.getYearMap().getPkYear());
		logger.info("Get Questions By Area"+question.getQuestionArea().getAreaCode());

		List<MTQuestion> questList = mtquestionservice.getQuestionByAreaYear(question.getYearMap().getPkYear(),question.getQuestionArea().getAreaCode());
		JSONArray arr = new JSONArray();
		PrintWriter pw = res.getWriter();
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		for (int i = 0; i < questList.size(); i++) {
			try {
				arr.put(new JSONObject(questList.get(i).toString()));
				logger.info("Question Id " + questList.get(i).getQuestionId());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pw.write(arr.toString());
		pw.flush();
		pw.close();

		
	}
	
	@RequestMapping(value = { "/questionDetailByID" })
	public void getQuestionsByID(HttpServletRequest req, HttpServletResponse res, Map<String, Object> model
			)throws IOException {
		logger.info(req.getParameterMap());
        long questId=Long.parseLong(req.getParameter("questId"));
		List<MTSubQuestion> questList = mtquestionservice.getSubQuestions(questId);
		JSONArray arr = new JSONArray();
		PrintWriter pw = res.getWriter();
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		for (int i = 0; i < questList.size(); i++) {
			try {
				arr.put(new JSONObject(questList.get(i).toString()));
				logger.info("Question Id " + questList.get(i).getSubQuestDesc());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pw.write(arr.toString());
		logger.info("Hello"+arr.toString());
		pw.flush();
		pw.close();

		
	}

	
	
	

	@RequestMapping(value = "/insertNewQuests", method = RequestMethod.POST)
	public void insertNewQuests(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("QuestionDTO") QuestionDTO question,@ModelAttribute("QuestionListDTO") QuestionListDTO questionList) throws IOException {
		logger.info("Insert Questions 123");
		    logger.info("List"+questionList.toString());
	        logger.info("Full list"+questionList.getQuestionList());
	        List<QuestionDTO> questions = questionList.getQuestionList();
	        int pkYear=0; 
	        if(null != questions && questions.size() > 0) {
	            QuestionsController.questionList = questions;
	            for (QuestionDTO quests : questions) {
	            	logger.info(quests.getQuestionDesc());
	            	logger.info(quests.getAreaCode());
	            	logger.info(quests.getYear());
	            	logger.info(quests.getQuestionType());
	             mtquestionservice.create(quests);
	            	pkYear=quests.getYear();
	            }
	            
	            List<MTQuestion> questList = mtquestionservice.getQuestionByYear(pkYear);
	    		JSONArray arr = new JSONArray();
	    		PrintWriter pw = response.getWriter();
	    		response.setContentType("application/json");
	    		response.setCharacterEncoding("utf-8");
	    		for (int i = 0; i < questList.size(); i++) {
	    			try {
	    				arr.put(new JSONObject(questList.get(i).toString()));
	    				logger.info("Question Id " + questList.get(i).getQuestionId());
	    			} catch (JSONException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		}
	    		pw.write(arr.toString());
	    		pw.flush();
	    		pw.close();

        	
	        }
	
			}

}
