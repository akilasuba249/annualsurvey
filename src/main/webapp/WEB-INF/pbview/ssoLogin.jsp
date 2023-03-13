<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>

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
<link href="css/style.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.js"></script>
<!-- <script src="quizjs/login.js"></script> -->
<script>
$(document).ready(function() {
    $( "#ssoLogForm" ).submit(function( event ) {
        //alert("SSO Login"); 
        $("#wait").show();
        $("#ssoLogForm").hide();
        debugger;
        return true;
 });
$('#ssoLogForm').submit();

	/* $( "#login" ).onclick(function( event ) {
			alert("SSO Login"); 
			$("#wait").css("display", "block");
			return true; 
			aunthentication();
		}); */
	//$('#ssoLogForm').submit();
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Annualsurvey</title>
</head>
<body>
<%response.setHeader("Access-Control-Allow-Origin","*");
response.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS");
response.setHeader("Access-Control-Allow-Credentials","true");
%>
<div class="loader1"></div>
		<form  accept-charset="UTF-8" role="form" id="ssoLogForm" method="post" action="j_spring_security_check" >
			<div class="col-lg-3">
			  <%-- <input type="text" class="form-control uname hidden" style="border-radius: 0px; display: none"  value="M${tokenId}" name="username" > --%>
			  <input type="text" class="form-control upn hidden" style="border-radius: 0px; display: none"  value="${attribute}" name="j_username" >
			  
			  <input type="text" class="form-control upn hidden" style="border-radius: 0px; display: none"  value="${tokenId}" name="j_tokenid" >
			  
			  <%-- <input type="text" class="form-control emailId hidden" style="border-radius: 0px; display: none"  value="M${emailId}" name="emailId" > --%>
			 <input id="login-password" type="password"  class="form-control upn hidden" style="border-radius: 0px; display: none"  name="j_password" placeholder="Password" value="sso">
			</div>
			<button id="login" class="btn" >Submit</button>
		</form>
		
		<div id="wait" style="position: fixed;top: 0;left: 0;right: 0;bottom: 0;">
		<div style="position: absolute; left: 0;height: 100px;
    width: 100px;
    left: 0;
    bottom: 0;
    right: 0;
    top: 0;
    margin: auto;">
		<img src='images/loader.gif' width="64" height="64" /><br>Loading..</div>
		</div>
		
</body>
</html>