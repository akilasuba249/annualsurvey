<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spg"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


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
<meta name="description" content="">
<meta name="author" content="">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>    
<title>Group Hr Annual Survey</title>
      <meta name="description" content="Here is a precise description of my awesome webpage.">
      <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
      <link id="favicon" rel="icon" type="image/x-icon" href="assets/images/favicon.png">
      <link rel="stylesheet" href="css/all.css">
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;0,800;1,300;1,400;1,600;1,700;1,800&display=swap" rel="stylesheet">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <link rel="stylesheet" type="text/css" href="https://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css">
      <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.css">
      <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick-theme.min.css">
      <link rel="stylesheet" type="text/css" href="https://malihu.github.io/custom-scrollbar/jquery.mCustomScrollbar.min.css">
      <link rel="stylesheet" href="assets/css/style-new.css?ver=3">
      <link rel="stylesheet" href="css/responsive.css?ver=3">

<script src="assets/js/all.js"></script>

<script src="assets/js/popper.min.js"></script>
<script src="assets/js/slick.min.js"></script>
<!-- <script src="assets/js/jquery.mCustomScrollbar.min.js"></script> -->
<!-- <script src="assets/js/jquery.mCustomScrollbar.concat.min.js"></script> -->

      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
       <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.js"></script>
       <script type="text/javascript" src="https://malihu.github.io/custom-scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
    
<script src="assets/js/custom-new.js?ver=3"></script>

<script src="js/classie.js"></script> 
	<script src="js/modalEffects.js"></script>
	<script src="js/user.js"></script>
	
	<script src="js/countries.js"></script>
	<script src="js/sweetalert.js"></script>
	<script src="js/bootstrap-session-timeout.js"></script>
	<script src="js/login/moment.min.js"></script>
	<script src="js/is.js"></script>
	
   </head>
   <body>
    
         <header class="menu-main">
            <div class="row align-center">
               <div class="col-8">
                  <div class="logo">
                     <img src="assets/images/mahindra-rise-logo.png">
                     <span class="logo-border">Group HR Annual Survey</span>
                  </div>
               </div>
               <div class="col-4">
                  <ul>
                     <li class="profile-border">
                        <img src="assets/images/profile.png">
                        <span>
                           <svg id="baseline-keyboard_arrow_down-24px" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                              <path id="Path_1411" data-name="Path 1411" d="M7.41,8.59,12,13.17l4.59-4.58L18,10l-6,6L6,10Z" fill="#fff"/>
                              <path id="Path_1412" data-name="Path 1412" d="M0,0H24V24H0Z" fill="none"/>
                           </svg>
                        </span>
                        <div class="accountbox">
                           <ul class="inner-ul">
                              <li><a href="#">Signout</a></li>
                           </ul>
                        </div>
                     </li>
                  </ul>
               </div>
            </div>
         </header>
    