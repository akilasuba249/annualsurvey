<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
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
<title>Area Master</title>
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
    <div class="container marg30">
    	<div class="centerdata">
        	<label class="col-md-3 col-xs-3 col-sm-3  nopad">Select Year</label>
        	<div class="col-md-3 col-xs-3 col-sm-3">
           <select class="form-control feld">
           		<option>2015</option>
                <option>2016</option>
           </select>      
            </div>
            <div class="col-md-3 col-md-offset-3 nopad">
            	<div class="addbtn btn btn-default pull-right" data-toggle="modal" data-target="#AddArea"><img src="images/add.png" style="margin-right: 10px;"/>Add Area</div>
            </div>
            <div class="clearfix"></div>
             <!--add area pop-up-->
            <div id="AddArea" class="modal fade" role="dialog">
              <div class="modal-dialog w5">
            
                <!-- Modal content-->
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <img src="images/add.png" style="margin-right: 10px;"/>Add Area
                  </div>
                  <div class="modal-body">
                    <form class="form-group">
                        <label class="col-md-3">Select Area</label>
                        <div class="col-md-6">
                        	<input type="text" class="form-control feld"/>
                        </div>
                    </form>
                    <div class="clearfix"></div>
                  </div>
                  <div class="modal-footer">
                  <button type="button" class="btn btn-default bg" data-dismiss="modal">Submit</button>
                    <button type="button" class="btn btn-default bg" data-dismiss="modal">Close</button>
                  </div>
                </div>
            
              </div>
            </div>
           <!--add area pop-up end-->
 <!--Panel-->          
   <div class="panel-group" style="margin-top:20px;">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">1.Mahindra Leadership University
          <div class="switch pull-right">
            <input id="cmn-toggle-1" class="cmn-toggle cmn-toggle-round" type="checkbox">
            <label for="cmn-toggle-1"></label>
          </div>

          
        </h4>
      </div>
      
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">2. RC
          <div class="switch pull-right">
            <input id="cmn-toggle-2" class="cmn-toggle cmn-toggle-round" type="checkbox">
            <label for="cmn-toggle-2"></label>
          </div>
        </h4>
      </div>
     </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">3.GRMP-Participants
          <div class="switch pull-right">
            <input id="cmn-toggle-0" class="cmn-toggle cmn-toggle-round" type="checkbox">
            <label for="cmn-toggle-0"></label>
          </div>
        </h4>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">4.GPMD- HR Heads/CEOs/Mentors
          <div class="switch pull-right">
            <input id="cmn-toggle-3" class="cmn-toggle cmn-toggle-round" type="checkbox">
            <label for="cmn-toggle-3"></label>
          </div>
        </h4>
      </div>
    </div>
    
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">5.Ascend
          <div class="switch pull-right">
            <input id="cmn-toggle-4" class="cmn-toggle cmn-toggle-round" type="checkbox">
            <label for="cmn-toggle-4"></label>
          </div>
        </h4>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">6.CFO+Track Program
          <div class="switch pull-right">
            <input id="cmn-toggle-5" class="cmn-toggle cmn-toggle-round" type="checkbox">
            <label for="cmn-toggle-5"></label>
          </div>
        </h4>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">7.MLU Academies
          <div class="switch pull-right">
            <input id="cmn-toggle-6" class="cmn-toggle cmn-toggle-round" type="checkbox">
            <label for="cmn-toggle-6"></label>
          </div>
        </h4>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">8.Rise(GEB)
          <div class="switch pull-right">
            <input id="cmn-toggle-8" class="cmn-toggle cmn-toggle-round" type="checkbox">
            <label for="cmn-toggle-8"></label>
          </div>
        </h4>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">9.Rise(TWC)
          <div class="switch pull-right">
            <input id="cmn-toggle-9" class="cmn-toggle cmn-toggle-round" type="checkbox">
            <label for="cmn-toggle-9"></label>
          </div>
        </h4>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">10.Mcares(Workmen)
          <div class="switch pull-right">
            <input id="cmn-toggle-10" class="cmn-toggle cmn-toggle-round" type="checkbox">
            <label for="cmn-toggle-10"></label>
          </div>
        </h4>
      </div>
    </div>
  </div>
   
 
        </div>
    </div>
    
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/html5shiv.min.js"></script>
 </html>



