<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="spg"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>Home Page</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />

</head>

<body>
<!--header-->
<div class="col-md-4 col-xs-4 col-sm-4">
	<img src="images/logo.png" class="logo" />
</div>
<div class="col-md-4 col-xs-4 col-sm-4">
	<h1>Group HR Annual Survey</h1>
</div>
<div class="col-md-4 col-xs-4 col-sm-4">
	<select class="pull-right opt">
    	<option>Welcome</option>
     </select>
          <h5 class="pull-right wlcm">Sanjay Chavan</h5>
   
</div>
<!--header end-->
<div class="clearfix"></div>
 <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active librd"><a href="index">Home </a></li>
            <li class="librd"><a href="#">Year</a></li>
            <li class="librd"><a href="area">Area</a></li>
             <li class="librd"><a href="employee">Employee</a></li>
 			 <li class="librd"><a href="question">Question</a></li>
              <li class="librd"><a href="#contact">Dashboard</a></li>            
            
          </ul>
        </div><!--/.nav-collapse -->
    </nav>
    <div class="container">
    	<div class="centerdata">
        	<label class="col-md-3 col-xs-3 col-sm-3">Select Year</label>
        	<div class="col-md-3 col-xs-3 col-sm-3">
           <select class="form-control feld">
           		<option>2015</option>
                <option>2016</option>
           </select>      
            </div>
           
           
           <div class="clearfix"></div>
           

   
 
        </div>
    </div>
    
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/html5shiv.min.js"></script>
 </html>
