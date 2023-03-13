package com.mahindra.annualsurvey.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpServerConnection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahindra.annualsurvey.dao.MTEmployeeDAO;
import com.mahindra.annualsurvey.dao.MTResponseDAO;
import com.mahindra.annualsurvey.dto.AreaDTO;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTQuestion;
import com.mahindra.annualsurvey.model.MTQuestionType;
import com.mahindra.annualsurvey.model.MTResponse;
import com.mahindra.annualsurvey.model.MTUser;
import com.mahindra.annualsurvey.model.MTYear;
import com.mahindra.annualsurvey.services.MTAreaService;
import com.mahindra.annualsurvey.services.MTEmployeeService;
import com.mahindra.annualsurvey.services.MTQuestionService;
import com.mahindra.annualsurvey.services.MTQuestionTypeService;
import com.mahindra.annualsurvey.services.MTYearService;
import com.mahindra.annualsurvey.springsecurityconfig.CustomSuccessHandler;
import com.mahindra.annualsurvey.springsecurityconfig.CustomeAuthProvider;
import com.mahindra.annualsurvey.utils.FileBean;
import com.mahindra.annualsurvey.utils.MailScheduler;
import com.mahindra.annualsurvey.utils.AES;

@Controller
public class HomeController {

	public static final Logger logger = Logger.getLogger(HomeController.class);
	
	
	@Autowired
	MTEmployeeService employeeservice;
	@Autowired
	MTEmployeeDAO mtEmployeeDao;

	/* Author-->Pooja */
	@Autowired
	MTQuestionService mtquestionservice;

	@Autowired
	MTAreaService mtareaservice;

	@Autowired
	MTQuestionTypeService mtquesttype;

	@Autowired
	MTYearService mtYearService;
	
	@Autowired
	CustomSuccessHandler loginHandler;
	
	@Autowired
	MTResponseDAO mtResponseDAO;
	
	@Autowired
	MailScheduler mailSchedular;
	
	@RequestMapping(value = { "admin" })
	public String home(HttpServletRequest req, HttpServletResponse res,
			Map<String, Object> model, Model model1,
			@ModelAttribute("EmployeeDTO") EmployeeDTO emp,
			@ModelAttribute("FileBean") FileBean uploadItem,
			@ModelAttribute("questions") MTQuestion question,
			@ModelAttribute("questType") MTQuestionType questionType,
			@ModelAttribute("MTYear") MTYear years) {
		ModelAndView view = new ModelAndView();
		view.setViewName("admin");
	//	model.put("questions", mtquestionservice.getQuestions());
		model.put("questions","");
	//	List<AreaDTO> areaDtoList=mtareaservice.getArea();
	//	model.put("questType", "");
		//model.put("areas", areaDtoList);
		model.put("questType", mtquesttype.getQuestionType());
		//model.put("employees", employeeservice.getEmployeeList());
		logger.info(mtareaservice.getArea());
	//	model.put("area1", areaDtoList);
		model.put("years", mtYearService.getYears());
	//	model.put("config", mtscConfig.getCreateSurvey());
		String statusOfUpload=req.getParameter("result");
		List<AreaDTO> activeAreas= mtareaservice.selectAreas("4");
		model.put("activeAreas", activeAreas);
		logger.info("Result is "+statusOfUpload);
		model1.addAttribute("status",statusOfUpload);
		logger.info("Into Index");
		return "admin";
	}

	@RequestMapping(value = { "/", "login" })     
	public ModelAndView error(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView view = new ModelAndView();
		HttpSession session= req.getSession();
		view.setViewName("login");
		//session.invalidate();
		logger.info("Into Login");
		System.out.println("LOGIN PAGE: ");
		return view;
	}

	/*@RequestMapping(value = "authentication", method = RequestMethod.POST)
	public String home(HttpServletRequest request,HttpServletResponse response, Map<String, Object> model,
			@ModelAttribute("mtemployee") MTEmployee user) throws IOException,LoginException {
		
		boolean authResult = false;
		String empId = request.getParameter("empId");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		session.setAttribute("empId", empId);
		session.setAttribute("password", password);
	//	session.setMaxInactiveInterval(2*60);
		PrintWriter pw = response.getWriter();

		ActiveDirectoryAuthentication authentication = new ActiveDirectoryAuthentication(
				"corp.mahindra.com");

//		authResult = authentication.authenticate(empId, password);
		//logger.info("Done"+authResult);
		//if (true) {
		if (authResult) {		
			List<MTEmployee> mtUser = employeeservice.checkLogin(user);
			try {
				if (user == null) {
					pw.write("Please Check DB Connection");
				} else if (mtUser.size() == 0) {
					pw.write("200--login");
				} else {
					request.getSession(false).setAttribute("loginDetails",
							mtUser.get(0));
					session.setAttribute("username", mtUser.get(0).getfName());

					if (mtUser.get(0).getUsertype().equals("user")) {
						pw.write("200--user");

					}
					
				}

			} catch (Exception e) {
				pw.write("Area Not Assigned for This Year");

			}

		} else {
			List<MTEmployee> mtUser = employeeservice.checkLoginExternal(user);
			try {
				if (user == null) {
					pw.write("Please Check DB Connection");
				} else if (mtUser.size() == 0) {
					pw.write("200--login");
				} else {
					request.getSession(false).setAttribute("loginDetails",mtUser.get(0));
					session.setAttribute("username", mtUser.get(0).getfName());

					if (mtUser.get(0).getUsertype().equals("admin")) {
						pw.write("200--admin");

					} 
					else if(mtUser.get(0).getUsertype().equals("user"))
					{
						pw.write("200--user");
					}
					else{
						pw.write("Invalid Credetials");
					}
				}

			} catch (Exception e) {
				pw.write("Area Not Assigned for This Year");

			}
//		}                         //active directory
		pw.flush();
		pw.close();

		return "login";

	}
	
	@RequestMapping(value = "authentication", method = RequestMethod.POST)
	public String home(@ModelAttribute("mtemployee") MTEmployee user,HttpServletRequest request,HttpSession session,Model model ){
		session.setAttribute("empId", user.getEmpId());
		session.setAttribute("password",user.getPassword());
		
		MTEmployee mtUser = employeeservice.checkLoginExternal(user);
		
		if(mtUser==null){
			model.addAttribute("msg", "Please Check DB Connection");
		}else{
			session.setAttribute("loginDetails",mtUser.getfName());
			session.setAttribute("username", mtUser.getfName());
		}
		
		return "login";
		
	}*/
	
	
	
	@RequestMapping(value={"/","/login"}, method=RequestMethod.GET)     
	public ModelAndView loginpage(@RequestParam(value="error",required=false)String error,@RequestParam(value="logout",required=false)String logout,
			@RequestParam(value="captchaerror",required=false)String captchaerror,@RequestParam(value="sessionout",required=false)String sessionout,
			@RequestParam(value="sesssioninuse",required=false)String sesssioninuse,@RequestParam(value="surveyEnd",required=false)String surveyEnd,@RequestParam(value="completed",required=false)String completed,HttpServletRequest request, HttpServletResponse response){
		
		System.out.println("LOGIN USER Trying to login");
		ModelAndView model = new ModelAndView("login");  
		
		if (error != null) { 
			/*model.addObject("error", "Invalid username and password!"); */
			model.addObject("error", "Invalid username!"); 
		}
		if (logout != null) {
			//session.invalidate();
			
			model.addObject("msg", "You've been logged out successfully.");		
			/*if(mtResponseDAO.findOne(condition))*/
		}
		
		if (captchaerror != null) {
			model.addObject("captchaerror", "Invalid Captcha!!!");
		}
		
		if (sessionout != null) {
			model.addObject("sessionout", "Session has expired!");
		}
		if (sesssioninuse != null) {
			model.addObject("sesssioninuse", "maximum one login allowed!!!");
		}
		if (completed != null) {
		if (completed.equals("yes")) {
			model.addObject("completedStatus", "You have completed the Survey.");
		}
		if (completed.equals("no")) {
			model.addObject("progressStatus", "Your response has been recorded.");
		}
		}
		if(logout==null && completed==null && sessionout==null && sesssioninuse==null)
		{
			model.addObject("invalidLink", "Please use the link provided in your mail to fill the survey!");
		}
		if (surveyEnd!= null) {
			model.addObject("surveyEnd", "Survey End!");
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			return new ModelAndView("forward:/"+loginHandler.determineTargetUrl(auth));
		}
		
		
		return model;
	}
	
	/*@RequestMapping(value="/j_spring_security_logout", method ={RequestMethod.GET,RequestMethod.POST} )
    public String logoutPage (HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes,Map map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            logger.info("logout ok");
        }
        redirectAttributes.addFlashAttribute("msg", "You've been logged out successfully.");   
        redirectAttributes.addFlashAttribute("savedmsg", "Your Data has been saved successfully,login again to complete the survey");
        redirectAttributes.addFlashAttribute("completemsg", "You have completed the Survey.Thank you");
        return "redirect:/login?status=yes";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }*/
	
	@RequestMapping(value="/beforeLogout", method ={RequestMethod.GET,RequestMethod.POST} )
    public String logout (HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes,Map map,@RequestParam(value="username",required=false) String username,@RequestParam(value="id",required=false) String id,@RequestParam(value="session",required=false) String sessionout) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            logger.info("logout ok");
        }
        if(sessionout!=null)
		{
			  if(sessionout.equals("N"))
			  {
				  return "redirect:/login?sessionout";
			  }
		}
		
		if(username!=null)
		{
				MTEmployee e=mtEmployeeDao.findOne("emailId='"+username+"'");
			MTResponse mtResponse=mtResponseDAO.findOne("mtEmployee.pkEmp='"+e.getPkEmp()+"'");
			if(mtResponse!=null)
			{
				if(mtResponse.getStatus().equals("completed"))
					return "redirect:/login?completed=yes";
				else
					return "redirect:/login?completed=no";
			}
			
		}
		else if(id!=null)
		{
			MTEmployee e=mtEmployeeDao.findOne("pkEmp='"+id+"'");
			
			MTResponse mtResponse=mtResponseDAO.findOne("mtEmployee.pkEmp='"+e.getPkEmp()+"'");
			if(mtResponse!=null)
			{
				if(mtResponse.getStatus().equals("completed"))
					return "redirect:/login?completed=yes";
				else
					return "redirect:/login?completed=no";
			}
			
		}
      /*  redirectAttributes.addFlashAttribute("msg", "You've been logged out successfully.");   
        redirectAttributes.addFlashAttribute("savedmsg", "Your Data has been saved successfully,login again to complete the survey");
        redirectAttributes.addFlashAttribute("completemsg", "You have completed the Survey.Thank you");*/
        
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }
	
	
	
	@RequestMapping(value = "/loginRedirect", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String loginRedirect(HttpServletRequest request,
			Map<String, Object> model, @ModelAttribute("MTUser") MTUser user) {
		logger.info("logout");
		request.getSession().setAttribute("username",
				request.getParameter("username"));

		return request.getParameter("redirect");
	}
	
	/** Session Invalidate when Browser will close implicitly or Explicitly**/
	@RequestMapping(value={"/invalidateSession"},method=RequestMethod.POST)
	public void invalidateSession(HttpServletRequest req, HttpServletResponse res,HttpSession session) throws IOException {
		
		System.out.println("Session invalidate on Browser close.");  
		session.invalidate();
		res.getWriter().write("invalidated"); 
 		
	}  
	
	@RequestMapping(value="/sendMail",method=RequestMethod.GET)
	@ResponseBody
	public String sendMail() {
		try {
			mailSchedular.surveyCreate();
			System.out.println("Success");
			return "Success";
		}catch(Exception e) {
			e.printStackTrace();
			return "Fails";
		}
	}
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value="/sendRMail",method=RequestMethod.GET)
	@ResponseBody
	public String sendRemainderMail() {
		try {
			mailSchedular.surveyReminder();
			System.out.println("Success");
			return "Success";
		}catch(Exception e) {
			e.printStackTrace();
			return "Fails";
		}
	}
}
