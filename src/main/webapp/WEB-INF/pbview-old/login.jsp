<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>
<!--<% response.addHeader("X-Frame-Options", "DENY"); %> -->

<% response.addHeader("X-Frame-Options", "SAMEORIGIN");  %>  
<% response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");  %>  
<% response.addHeader("Pragma", "no-cache");  %>  
<% response.addDateHeader ("Expires", 0); %>  
<% response.addHeader("X-Powered-By",""); %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=EDGE"> 	
<!-- <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"> -->
	
<title>Login</title>
	<link rel="stylesheet" type="text/css" href="css/login/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/login/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/login/material-design-iconic-font.min.css">
	<link rel="stylesheet" type="text/css" href="css/login/animate.css">	
	<link rel="stylesheet" type="text/css" href="css/login/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="css/login/animsition.css">
	<link rel="stylesheet" type="text/css" href="css/login/select2.min.css">
	<link rel="stylesheet" type="text/css" href="css/login/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="css/login/util.css">
	<link rel="stylesheet" type="text/css" href="css/login/main.css">
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Raleway:400,500,700|Roboto:400,500,700" rel="stylesheet">
<!-- 
<script src="js/jquery.js"></script>
<script src="js/login.js"></script>
<script src="js/sweetalert.min.js"></script>
<script src="js/ajaxSetup.js"></script> -->
</head>

<body>

<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
			
			
		
				   <%-- <form id="loginForm" class="form-horizontal login100-form validate-form" role="form"  method="post" action="j_spring_security_check" autocomplete="off"> 
					<div class="text-center logoimgdiv"><img src="images/logowhite.png" /></div>
					<div class="wrap-input100 validate-input" data-validate ="Enter Email-Id">
						<input id="login-username" class="input100 logtext" type="text" name="j_username" value="">
						<span class="focus-input100" data-placeholder="Email Id"></span>
					</div>   --%>
 
					<!-- <div class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
						<input id="login-password" class="input100 logtext" type="password" name="j_password">
						<span class="focus-input100" data-placeholder="Password"></span>
					</div>
					 -->
					  <!-- <div class="wrap-input100 validate-input captchdiv" data-validate="Enter captcha">
						<img id="captcha_id" src="captcha" width="160" height="50">
						<span class="glyphicon glyphicon-refresh" onclick="document.getElementById('captcha_id').src = 'captcha?' + Math.random(); return false"></span>
						<input id="j_captcha" vld="{required:true}" class="input100 logtext" type="text" name="j_captcha">
						<span class="focus-input100" data-placeholder="Enter captcha"></span>
					</div>   -->
					 <div class=" margin margin-center">
       
					        <c:if test="${not empty error}">
								<div class="msg">${error}</div>
							</c:if>
							<%-- <c:if test="${not empty msg}">
								<div class="msg">${msg}</div>
							</c:if> --%>
							 <c:if test="${not empty captchaerror}">
								<div class="msg">${captchaerror}</div>
							</c:if> 
							<!-- session out -->
							<c:if test="${not empty sessionout}">
								<div class="msg font-msg-style">${sessionout}</div>
						        <div class="msg font-msg-style">Use the link provided in the mail to complete the survey.</div>
							</c:if>
							<c:if test="${not empty sesssioninuse}">
								<div class="msg font-msg-style">${sesssioninuse}</div>
							</c:if>
							<!-- gfyuh -->
							 <c:if test="${not empty completed}">
								<div class="msg font-msg-style">You have already submitted the Survey.</div>
							</c:if> 
							<c:if test="${not empty progressStatus}">
								<div class="msg font-msg-style">${progressStatus}</div>
								<div class="msg font-msg-style">Use the link provided in the mail to complete the survey.</div>
							</c:if>
							<!-- <div>Thank you!</div> -->
					  		</div>
					  		<c:if test="${not empty completedStatus}">
								<div class="msg font-msg-style" style="text-align:center;">${completedStatus}</div>
							</c:if>
							
							
							<c:if test="${not empty invalidLink}">
								<div class="msg font-msg-style">${invalidLink}</div>
							</c:if>
							<c:if test="${not empty surveyEnd}">
								<div class="msg font-msg-style">The survey is closed. In case of any queries, please contact Esha Bhatia at bhatia.esha@mahindra.com.</div>
							</c:if>
 							<div class="msg  thankyou-txt ">Thank you!</div>
					  <%-- <div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn loginbutton" id="loginButton">
								Login
							</button>
						</div>
					</div>
					
					      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />          
				</form>   --%> 
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
	
<script src="js/login/jquery-3.2.1.min.js"></script>
	<script src="js/login/animsition.min.js"></script>
	<script src="js/login/popper.js"></script>
	<script src="js/login/bootstrap.min.js"></script>
	<script src="js/login/select2.min.js"></script>
	<script src="js/login/moment.min.js"></script>
	<script src="js/login/daterangepicker.js"></script>
	<script src="js/login/countdowntime.js"></script>
	<script src="js/login/main.js"></script>
	

</body>
</html>
