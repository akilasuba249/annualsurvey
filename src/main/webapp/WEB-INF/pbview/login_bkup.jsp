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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>   
<title>Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/sweetalert.css" />

<script src="js/jquery.js"></script>
<script src="js/login.js"></script>
<script src="js/sweetalert.min.js"></script>
<!-- <script src="js/ajaxSetup.js"></script> -->
</head>

<body class="logincon">

<!-- <div class="navbar navbar-fixed-top mainnav">
      <div class="topdiv">
        <div="col-md-12">
            <div  class="leftlogo">
              <a class="navbar-brand" href="#"><img src="images/logoridge.png"></a>
              <nav class="navbar navbar-default">
                  <div class="navbar-header">
                    <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="#" class="navbar-brand">Brand</a>
                </div>
                
               </nav>
            </div>
        </div>
      </div> -->

<form id="loginForm" class="form-horizontal" role="form"  method="post" action="j_spring_security_check" autocomplete="off"> 
<div class="container">
<div id="login" class="login">

  
    <div class="login-icon-field">
      <img src="images/mahindralogo.png">
    </div>
    <div class="login-form">
      <div class="username-row row">
        <label for="username_input">
        <svg version="1.1" class="user-icon" x="0px" y="0px"
        viewBox="-255 347 100 100" xml:space="preserve" height="36px" width="30px">
          <path class="user-path" d="
          M-203.7,350.3c-6.8,0-12.4,6.2-12.4,13.8c0,4.5,2.4,8.6,5.4,11.1c0,0,2.2,1.6,1.9,3.7c-0.2,1.3-1.7,2.8-2.4,2.8c-0.7,0-6.2,0-6.2,0
          c-6.8,0-12.3,5.6-12.3,12.3v2.9v14.6c0,0.8,0.7,1.5,1.5,1.5h10.5h1h13.1h13.1h1h10.6c0.8,0,1.5-0.7,1.5-1.5v-14.6v-2.9
          c0-6.8-5.6-12.3-12.3-12.3c0,0-5.5,0-6.2,0c-0.8,0-2.3-1.6-2.4-2.8c-0.4-2.1,1.9-3.7,1.9-3.7c2.9-2.5,5.4-6.5,5.4-11.1
          C-191.3,356.5-196.9,350.3-203.7,350.3L-203.7,350.3z"/>
        </svg>
        </label>
        <input id="login-username" type="text" name="j_username"  class="username-input logininput " placeholder="Username" required></input>
      </div>
      <div class="password-row row">
        <label for="password_input">
        <svg version="1.1" class="password-icon" x="0px" y="0px"
        viewBox="-255 347 100 100" height="36px" width="30px">
          <path class="key-path" d="M-191.5,347.8c-11.9,0-21.6,9.7-21.6,21.6c0,4,1.1,7.8,3.1,11.1l-26.5,26.2l-0.9,10h10.6l3.8-5.7l6.1-1.1
          l1.6-6.7l7.1-0.3l0.6-7.2l7.2-6.6c2.8,1.3,5.8,2,9.1,2c11.9,0,21.6-9.7,21.6-21.6C-169.9,357.4-179.6,347.8-191.5,347.8z"/>
        </svg>
        </label>
        <input id="login-password" type="password" name="j_password" class="password-input logininput" class="input" placeholder="Password" required></input>
      </div>
      
      <img id="captcha_id" src="captcha" width="160" height="50"> 
  		<span class="glyphicon glyphicon-refresh" onclick="document.getElementById('captcha_id').src = 'captcha?' + Math.random(); return false"></span>   
  		
      <div class="captcha-row row">
        <label for="username_input">
        <svg version="1.1" class="user-icon" x="0px" y="0px"
        viewBox="-255 347 100 100" xml:space="preserve" height="36px" width="30px">
          <path class="user-path" d="
          M-203.7,350.3c-6.8,0-12.4,6.2-12.4,13.8c0,4.5,2.4,8.6,5.4,11.1c0,0,2.2,1.6,1.9,3.7c-0.2,1.3-1.7,2.8-2.4,2.8c-0.7,0-6.2,0-6.2,0
          c-6.8,0-12.3,5.6-12.3,12.3v2.9v14.6c0,0.8,0.7,1.5,1.5,1.5h10.5h1h13.1h13.1h1h10.6c0.8,0,1.5-0.7,1.5-1.5v-14.6v-2.9
          c0-6.8-5.6-12.3-12.3-12.3c0,0-5.5,0-6.2,0c-0.8,0-2.3-1.6-2.4-2.8c-0.4-2.1,1.9-3.7,1.9-3.7c2.9-2.5,5.4-6.5,5.4-11.1
          C-191.3,356.5-196.9,350.3-203.7,350.3L-203.7,350.3z"/>
        </svg>
        </label>
        <input id="login-passwordj_captcha" type="text" name="j_captcha" class="username-input logininput " vld="{required:true}" placeholder="Enter Captcha" required></input>
      </div>
    </div>
    <div class=" margin">
       
        <c:if test="${not empty error}">
			<div class="msg">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<c:if test="${not empty captchaerror}">
			<div class="msg">${captchaerror}</div>
		</c:if>
		<c:if test="${not empty sessionout}">
			<div class="msg">${sessionout}</div>
		</c:if>
		<c:if test="${not empty sesssioninuse}">
			<div class="msg">${sesssioninuse}</div>
		</c:if>
		
		
  		</div>
    <div class="call-to-action">
      <button id="loginButton" class="loginbutton frmbtn" type="button">Log In</button>
      <!-- <button type="button" class="loginbutton frmbtn">SSO Log In</button> -->
      
    </div>
  </div>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
  
</div>
</form >

<script>
//jQuery & Velocity.js

function slideUpIn() {
  $("#login").velocity("transition.slideUpIn", 1250)
};

function slideLeftIn() {
  $(".row").delay(500).velocity("transition.slideLeftIn", {stagger: 500})    
}

function shake() {
  $(".password-row").velocity("callout.shake");
}

slideUpIn();
slideLeftIn();
$("button").on("click", function () {
  shake();
});
</script>

</body>
</html>
