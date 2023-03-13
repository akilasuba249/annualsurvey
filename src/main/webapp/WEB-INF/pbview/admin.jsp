<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spg"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%

 String sessionData=null;
if(request.getSession(false) == null || request.getSession(false).getAttribute("loginDetails") == null){
	response.sendRedirect("login");
	
}
%> 
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
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">    
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    
<title>Annual Survey</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/scrolling-nav.css" rel="stylesheet">
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="css/jquery-ui.css" />
<link rel="stylesheet" href="css/bootstrap-timepicker.min.css">
<link rel="stylesheet" href="css/sweetalert.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript --> 
<script src="js/bootstrap.min.js"></script> 
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script> 
<!-- Scrolling Nav JavaScript --> 
<!-- <script src="js/ajaxSetup.js"></script> -->  
<script src="js/jquery.easing.min.js"></script> 
<script src="js/scrolling-nav.js"></script> 
<script src="js/classie.js"></script> 
<script src="js/common.js"></script>
<script src="js/jquery.table2excel.js"></script>

<script src="js/commonValidations.js"></script>
<script src="js/jquery.serializejson.min.js"></script>
<script src="js/get_add_area.js"></script>


 <!-- <script src="js/question.js"></script> -->
<script src="js/config.js"></script>

<script src="js/jquery.dataTables.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-timepicker.min.js"></script>
<script src="js/jquery.tabletojson.js"></script>
<script src="js/jquery-ui.js"></script>
 <!-- <script src="js/report.js"></script>  -->
<!-- <script src="js/admin.js"></script>  -->
<script src="js/sweetalert.min.js"></script>
<script src="js/bootstrap-session-timeout.js"></script>

<script>
  $(function() {
    $( "#datepicker" ).datepicker();
    $( "#datepicker2" ).datepicker();
 });
  </script> 
  <style>.col-md-1 input[type=checkbox] {
    margin-top: 11px;
}</style>
</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
	<!--header-->
 <div class="loader"><img src="images/loader.gif"></div>
	<div class="fxtop">
		<div class="col-md-3 col-xs-3 col-sm-3">
			<img src="images/logo.png" class="logo" />
		</div>
		<div class="col-md-6 col-xs-6 col-sm-6">
			<h1>Group HR Annual Survey</h1>
		</div>
		<div class="col-md-3 col-xs-3 col-sm-3">
			<div class="wlcmeTxt">
			<span><span	class="glyphicon glyphicon-user"></span> Welcome </span>
				<a style="cursor:default;margin-right:0px;" class=" dropdown-toggle"
					id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
					<span	id="userInfo"><%=session.getAttribute("username")%></span>	</a>				
				<!-- <span class="logout">
					<a href="beforeLogout" style="color: #333;text-decoration: none;">| <i class="fa fa-power-off"></i></a>
				</span></a> -->
				<%-- <ul class="dropdown-menu dropdown-menu-right whitebg" role="menu"
					aria-labelledby="dropdownMenu1">
					<li role="presentation"><a role="menuitem" class="logoutLink"
						tabindex="-1" href="<c:url value="j_spring_security_logout" />"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul> --%>
			</div>
		</div>

		<!--<select class="pull-right opt">
      <option>Welcome</option>
    </select>
    <h5 class="pull-right wlcm">Snjay Chavan</h5>-->
	</div>
	</div>
	<!--header end-->
	<!-- Navigation -->

	<nav class="navbar navbar-inverse navbar-fixed-top adminnavbar" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li><a class="page-scroll" href="#Home" id="getarea" >Area</a></li>
				<li><a class="page-scroll" href="#Employee" id="getemployee" >Employee</a></li>

				<li><a class="page-scroll" href="#Question" id="getquestion">Question</a></li>
				<li><a class="page-scroll" href="#Area" id="getservey">Create Survey</a></li>
				<li><a class="page-scroll" href="#Tracking" id ="gettarcking">Tracking</a></li>
				<li><a class="page-scroll" href="#Report" id="getreport">Report</a></li>


			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>

<!--
    <section id="intro" class="intro-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>Scrolling Nav</h1>
                    <p><strong>Usage Instructions:</strong> Make sure to include the <code>scrolling-nav.js</code>, <code>jquery.easing.min.js</code>, and <code>scrolling-nav.css</code> files. To make a link smooth scroll to another section on the page, give the link the <code>.page-scroll</code> class and set the link target to a corresponding ID on the page.</p>
                    <a class="btn btn-default page-scroll" href="#about">Click Me to Scroll Down!</a>
                </div>
            </div>
        </div>
    </section>--> 

<!-- Home-section -->
<section id="Home" class="Home-section">
 
      <!--All pop-up start-->
      <div class="col-lg-12"> 
        <!--add area pop-up-->
        <div id="AddArea" class="modal fade" role="dialog">
          <div class="modal-dialog w5"> 
            
            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            <span class="glyphicon glyphicon-plus-sign sadd"></span>Add Area </div>
          <div class="modal-body">
            <form class="form-group">
              <label class="col-md-3">Add Area</label>
              <div class="col-md-6">
                <input type="text" id="areaDesc" class="form-control feld" maxlength="170"/>
              </div>
            </form>
            <div class="clearfix"></div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default bg" data-dismiss="modal" id="addAreaSubmit">Submit</button>
            <button type="button" class="btn btn-default bg" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
    <!--add area pop-up end--> 
    <!-- edit area pop up -->
    <div id="EditArea" class="modal fade" role="dialog">
          <div class="modal-dialog w5"> 
            
            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            <span class="glyphicon glyphicon-plus-sign sadd"></span>Edit Area </div>
          <div class="modal-body">
            <form class="form-group">
              <label class="col-md-3">Area</label>
              <div class="col-md-6">
              	<input type="hidden" class="areaCode"/>
                <input type="text" class="areaDesc" class="form-control feld" maxlength="170"/>
              </div>
            </form>
            <div class="clearfix"></div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default bg" data-dismiss="modal" id="updateAreaSubmit">Update</button>
            <button type="button" class="btn btn-default bg" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
    
    <!-- delete area modal -->
    <div id="deleteAreaModal" class="modal fade" role="dialog">
          <div class="modal-dialog w5"> 
            
            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button></div>
          <div class="modal-body">
              <label class="col-md-9">Are you sure want to delete area ?</label>
              	<input type="hidden" class="areaCode"/>
            <div class="clearfix"></div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default bg" data-dismiss="modal" id="delAreaBtn">Yes</button>
            <button type="button" class="btn btn-default bg" data-dismiss="modal">No</button>
          </div>
        </div>
      </div>
    </div>
    
    <!--add Employee pop-up-->
    <div id="Addemployee" class="modal fade" role="dialog">
      <div class="modal-dialog" style="width: 720px;"> 
        
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <span class="glyphicon glyphicon-plus-sign sadd"></span>Add Employee </div>
          <div class="modal-body">
            <spg:form class="form-horizontal" id="addUpdate" 	modelAttribute="EmployeeDTO">
            <div class="form-group">
              <label class="col-sm-2 nopad">Token ID :</label>
              <div class="col-sm-4">
                <spg:input type="number" class="form-control feld" path="empId"/>
              </div>
              <label class="col-sm-2 nopad">Year :</label>
              <div class="col-sm-4">
                <spg:input type="text" class="form-control feld" path="year"/>
              </div>
            </div>
            <div class="clearfix"></div>
            <div class="form-group">
              <label class="col-sm-2 nopad">First Name :</label>
              <div class="col-sm-4">
                <spg:input type="text" class="form-control feld" pattern="[A-Za-z]" path="fName"/>
              </div>
              <label class="col-sm-2 nopad">Last Name :</label>
              <div class="col-sm-4">
                <spg:input type="text" class="form-control feld" pattern="[A-Za-z]" path="lName"/>
              </div>
            </div>
            <div class="clearfix"></div>
            <div class="form-group">
              <label class="col-sm-2 nopad">Email ID :</label>
              <div class="col-sm-4">
                <spg:input type="text" class="form-control feld" path="emailId"/>
              </div>
            </div>
            <div class="clearfix"></div>
            <label class="col-sm-2 nopad">Select Area:</label>
            <div class="col-sm-9">
              <div class="col-sm-1">
                <spg:checkbox path="Area"  value="MLU"/>
              </div>
              <label class="col-sm-3">MLU</label>
              <div class="col-sm-1">
                <spg:checkbox  path="Area" value="Ascend"/>
              </div>
              <label class="col-sm-3">Ascend</label>
              <div class="col-sm-1">
                <spg:checkbox  path="Area" value="GMC"/>
              </div>
              <label class="col-sm-3">GMC</label>
              <div class="clearfix"></div>
              <div class="col-sm-1">
                <spg:checkbox  path="Area" value="RC"/>
              </div>
              <label class="col-sm-3">RC</label>
              <div class="col-md-1 col-sm-1">
                <spg:checkbox path="Area" value="Rise(GEB)"/>
              </div>
              <label class="col-md-5 col-sm-5">Rise(GEB)</label>
              <div class="col-md-1 col-sm-1">
                <spg:checkbox  path="Area" value="MPGM"/>
              </div>
              <label class="col-md-5 col-sm-5">MPGM</label>
              <div class="clearfix"></div>
              <div class="col-md-1 col-sm-1">
                <spg:checkbox path="Area" value="MCARES(HR)" />
              </div>
              <label class="col-md-5 col-sm-1">MCARES(HR)</label>
              <div class="col-md-1 col-sm-1">
                <spg:checkbox path="Area" value="MPGM(HR)"/>
              </div>
              <label class="col-md-5 col-sm-5">MPGM(HR)</label>
              <div class="col-md-1 col-sm-1">
                <spg:checkbox path="Area" value="C&b"/>
              </div>
              <label class="col-md-5 col-sm-5">C&b</label>
            </div>
          </div>
          <div class="clearfix"></div>
          <div class="modal-footer">
            <button type="button" class="btn addbtn" data-dismiss="modal" onclick="submitEmployee()">Submit</button>
            <button type="button" class="btn addbtn" data-dismiss="modal">Close</button>
          </div>
          </spg:form>	
        </div>
      </div>
    </div>
    <!--add Employee pop-up end--> 
    
    <!-- Add Delet entry popup start -->
    
    <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title custom_align" id="Heading"><span class="glyphicon glyphicon-trash sadd"></span>Delete this entry</h4>
          </div>
          <div class="modal-body">
            <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>
          </div>
          <div class="modal-footer ">
            <button type="button" class="deleterow btn addbtn" ><span class="glyphicon glyphicon-ok-sign"></span>Yes</button>
            <button type="button" class="btn addbtn" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>No</button>
          </div>
        </div>
        <!-- /.modal-content --> 
      </div>
      <!-- /.modal-dialog --> 
    </div>
    <!-- Delete entry popup end --> 
    <!-- Add Delet entry popup start -->
   
    
   
    
    
    <!-- Delete entry popup end --> 
    
    <!-- Question Delete popup start -->
    
     <div class="modal fade" id="deleteQuestMod" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title custom_align" id="Heading"><span class="glyphicon  glyphicon glyphicon-trash sadd"></span>Delete this entry</h4>
                <input type="hidden" id="modDelQuestId">
              </div>
              <div class="modal-body">
                <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Question?</div>
              </div>
              <div class="modal-footer ">
                <button type="button" class="deleterow btn addbtn  delQuestYes" onClick="deleteQuest(this.id)"><span class="glyphicon glyphicon-ok-sign delQuestYes" ></span>Yes</button>
                <button type="button" class="btn addbtn" data-dismiss="modal"><span class="glyphicon glyphicon-remove" id="delQuestNo"></span>No</button>
              </div>
            </div>
            <!-- /.modal-content --> 
          </div>
          <!-- /.modal-dialog --> 
        </div>
        <!--Question Delete popup end --> 
        
        <!-- Edit entry popup start -->
        <div class="modal fade" id="editQuest" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title custom_align" id="Heading"><span class="glyphicon  glyphicon glyphicon-edit sadd"></span>Edit Question</h4>
              </div>
              <div class="modal-body">
              <form id="editQuestForm">
                <div class="form-group">
                <input type="hidden" name="questionId" id="editQuestId">
                </div>
              
                <div class="form-group">
                <label class="col-md-12 nopad" >Question Description :</label>
                  <textArea  style="resize: none;" rows="4" class="form-control" id="editQuestText" type="text" name="questionDesc" maxlength="499"></textArea>
                </div>
                <div class="form-group">
                 <label class="col-md-12 nopad">Select Area :</label>
                   <select class="form-control feld areaload" name="areaCode" id="editQuestArea" >
              									<option value="0">Select Area</option>
												<c:forEach items="${areas}" var="areas">
												<option value="${areas.areaCode}">${areas.areaDesc}</option>
												</c:forEach>
            		 </select>
                </div>
                <div class="clearfix"></div>
               <div class="form-group">
                  <label class="col-md-12 nopad"> Select Question Type :</label>
                
                      <select class="form-control feld" name="questionType"  id="editQuestType" >
              			<option value="0">Select Type</option>
              			<c:if test="${questType ne null && questType ne ''}">
              			<c:forEach items="${questType}" var="areas">
						<option value="${areas.pkQuestType}">${areas.questTypeDesc}</option>
						</c:forEach></c:if>
						</select>
            
                  </div>
                    <div class="clearfix"></div>
                      <div class="form-group">
                 <label class="col-md-12 nopad">Select Year :</label>
                   <select class="form-control feld" name="year" id="editQuestYear" >
              									<c:forEach items="${years}" var="years">
												<option value="${years.pkYear}">${years.year}</option>
												</c:forEach>
            		 </select>
                </div>
                        <div class="input_fields_wrap1">
   					 
   					   <div class="form-group"> <label class="col-sm-4 nopad">Edit Sub Question</label>            <div class="clearfix"></div>
   					   <div><div class="col-sm-11 nopad"><input type="text" class="form-control feld" id="subquesteditId" name="subquest[0].subQuestDesc"></div></div>
   					   <div class="col-sm-1"><button class="edit_field_button btn add-more"><span class="glyphicon glyphicon-plus"></span></button></div></div>
				  </div>
    
                <div class="clearfix"></div>
                
              <div class="modal-footer " style="text-align:center !important;">
                <button type="button" class="md-close btn addbtn btn-lg" data-dismiss="modal"  onClick="updateQuest();"><span class="glyphicon glyphicon-ok-sign" style="top:0;"></span>Update</button>
              </div>
              </form>
            </div>
            <!-- /.modal-content --> 
          </div>
          <!-- /.modal-dialog --> 
        </div>
        </div>
        
        
        <!-- Edit entry popup end --> 
        <!--Add Question pop-up-->
        <div id="addQuestion" class="modal fade" role="dialog" >
          <div class="modal-dialog"> 
            
            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <span class="glyphicon glyphicon-plus-sign sadd"></span>Add Question </div>
              <div class="modal-body">
              <form id="addQuestionForm">
                <div class="form-group">
                <label class="col-sm-4 nopad" ><span class="starRed">*</span>Description :</label>
                  <div class="col-sm-8">
                    <textarea rows="4" style="resize: none;" class="form-control feld" name="questionDesc" maxlength="499"></textarea>
                  </div>
                </div>
                <div class="clearfix"></div>
                <div class="form-group">
                  <label class="col-sm-4 nopad"><span class="starRed">*</span>Area :</label>
                  <div class="col-sm-8">
               	      <select class="form-control feld areaload" name="areaCode" >
              									<option value="0">Select Area</option>
												<c:forEach items="${areas}" var="areas">
												<option value="${areas.areaCode}">${areas.areaDesc}</option>
												</c:forEach>
            		 </select>
                  </div>
                  </div>
                <div class="clearfix"></div>
                
                 <div class="form-group">
                  <label class="col-sm-4 nopad"><span class="starRed">*</span>Question Type :</label>
                  <div class="col-sm-8">
                  <c:if test="${questType ne null && questType ne ''}">
                      <select class="form-control feld" name="questionType">
              			<option value="0">Select Type</option>
              			<c:forEach items="${questType}" var="areas">
						<option value="${areas.pkQuestType}">${areas.questTypeDesc}</option>
						</c:forEach>
						</select></c:if>
            </div>
                  </div>
                <div class="clearfix"></div>
             
              <div class="form-group">
                  <label class="col-sm-4 nopad"><span class="starRed">*</span>Year :</label>
                  <div class="col-sm-6">
                      <select class="form-control feld" name="year">
              			<option value="0">Select Year</option>
              			<c:forEach items="${years}" var="years">
												<option value="${years.pkYear}">${years.year}</option>
												</c:forEach>
            		 </select>
                  </div>
                  </div>
                  <div class="clearfix"></div>
                  <div class="input_fields_wrap">
   					 
   					   <div class="form-group"> <label class="col-sm-4 nopad">Add Sub Question</label>
   					   <div><div class="col-sm-7"><input type="text" class="form-control feld" name="subquest[0].subQuestDesc" id="subquestaddId" maxlength="250"></div></div>
   					   <div class="col-sm-1"><button class="add_field_button btn add-more addquesbtn"><span class="glyphicon glyphicon-plus"></span></button></div></div>
				  </div>
				  
				  
				  
			<div class="clearfix"></div>
             	</form>
              </div>
              
              <div class="modal-footer">
                <button type="button" class="btn addbtn" onClick="insertQuest();">Submit</button>
                <button type="button" class="btn addbtn" data-dismiss="modal">Close</button>
              </div>
            </div>
          <!-- </div>
        </div> -->
        <!--Add Question pop-up end--> 
      </div>
      <!--all pop-up end--> 
    </div>
  </div>
</section>
<!-- Home-section end --> 

	<!-- Create Survey Section -->
<section id="Config" class="Config-section">
  <div class="container" id ="scrolconfig" >
    <div class="xs_col_filtering" id="areasection">
      <div class="col-md-12">
        <h1 class="text-left myh1">Area Section</h1>
      </div>
      <div class="col-sm-12 nopad ">
        <div class="col-sm-12">
          <label class="col-sm-1 nopad pad10">Select Year</label>
          <div class="col-sm-2 nopad2">
           <select class="form-control feld" id="areaYear">
				<option value="0">All Areas</option>
              <c:forEach items="${years}" var="years">
				<option value="${years.year}">${years.year}</option>
				</c:forEach>

            </select>
          </div>
          <div class="col-sm-9 text-right nopad pad5 adbtn"> <a href="#AddArea" class="addbtn btn btn-default tp" data-toggle="modal" data-target="#AddArea" id="addAreaButton"><span class="glyphicon glyphicon-plus-sign sadd"></span>Add Area</a> </div>
          </div>  
          <div class="clearfix"></div>
          
          <!-- area grid start-->
         <div class="col-md-6 col-sm-6"> 
          <table class="table table-condensed table-striped table-hover marg5" id= "mt_areaTable1">
            <tbody>
              <tr class="tbrder">
             <!--    <td class="biglable">1.Mahindra Leadership University</td>
                <td class="smalllable"><div class="btn-group" data-toggle="buttons">
                    <label class="btn btn-default">
                      <input type="radio" class="vsk" name="options" id="areaYes" autocomplete="off" value="on">
                      <b><span class="glyphicon glyphicon-ok fwh"></span></b> </label>
                    <label class="btn btn-default active">
                      <input type="radio" class="vsk2" name="options" id="areaNo" autocomplete="off" value="off" >
                      <b><span class="glyphicon glyphicon-remove fwh"></span></b> </label>
                  </div></td> -->
                  <td class="biglable"><input type="checkbox" /></td>
              </tr>
            </tbody>
          </table>
         </div>
          <!-- area grid end--> 
           <!-- area grid start-->
          <div class="col-md-6 col-sm-6">
          <table class="table table-condensed table-striped table-hover marg5" id= "mt_areaTable2">
            <tbody>
              <tr class="tbrder">
                <td class="biglable">1.Mahindra Leadership University</td>
                <!-- <td class="smalllable"><div class="btn-group" data-toggle="buttons">
                    <label class="btn btn-default">
                      <input type="radio" class="vsk" name="options" id="areaYes" autocomplete="off" value="on">
                      <b><span class="glyphicon glyphicon-ok fwh"></span></b> </label>
                    <label class="btn btn-default active">
                      <input type="radio" class="vsk2" name="options" id="areaNo" autocomplete="off" value="off" >
                      <b><span class="glyphicon glyphicon-remove fwh"></span></b> </label>
                  </div></td> -->
                  <td class="smalllable"><input type="checkbox" id="areaCheck" class="checkbox"/></td>
              </tr>
            </tbody>
          </table>
         </div>
          <!-- area grid end-->  
           
      </div>
    </div>
  </div>
  
  
</section>
<script>
		$(document).ready(function() {

	 $.sessionTimeout({
			        keepAliveUrl: '',
			        logoutUrl: 'beforeLogout?session=N',
			        redirUrl: 'beforeLogout?session=N',
			        keepAlive:true,
			         warnAfter: 900000,
			        redirAfter: 9000001, 
			        countdownMessage: 'Redirecting in {timer} seconds.',
			        countdownBar: true
			    });

			
			
		//	alert('<c:out value="${status}"/>');
			var statusOfUpload = '<c:out value="${status}"/>';
			if(statusOfUpload=="okay"){
				swal({title: "File Uploaded Successfully" },

						  function(){
						      window.location.href = "admin";
						 });
			}else if(statusOfUpload=="error"){
				
			  window.location.href="downloadErrorExcel";

			  swal({title: "Error records downloaded, and the unerrored recored uploaded" },

					  function(){
					      window.location.href = "admin";
					 });

				
				}
			
			
			$("#conf").click(function() {
				//window.alert("insert data  successfully ");
				
				//$('#addconfig')[0].reset();
			});
			
			$('#areaDesc').bind('keypress', function (event) {
			    var regex = new RegExp("^[^<>'\"/;`%]*$");		//does not allow < > ' " / ; ` %
			    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
			    if (!regex.test(key)) {
			       event.preventDefault();
			       return false;
			    }
			});
		});
	</script>
<!-- <script>
$(document).ready(function(){
$( "#addconfig  button[type=submit]" ).click(function()
{
	      submitConfig();
	//	window.alert("insert data  successfully ");
		
		//$('#addconfig')[0].reset();
	});	
});
</script> -->
	<!-- Create Survey Section End -->


<div class="clearfix"></div>
<!-- Employee Section -->
<section id="Employee" class="Employee-section">
  <div class="container">
    <div class="">
     <div class="col-md-12">
        <h1 class="text-left myh1">Employee Section</h1>
      </div>
      <div class="col-lg-12 col-md-12 col-sm-12" style="padding-right: 0px;">
        <spg:form method="POST" action="uploadFile?${_csrf.parameterName}=${_csrf.token}"  id="uploadFile" enctype="multipart/form-data" modelAttribute="FileBean">
        <label class="col-md-1 col-sm-2 nopad">Select Year</label>
        <div class="col-md-2 col-sm-2 year3 nopad">
          <spg:select class="form-control feld" path="year" id="yearEmp" name="year1" onchange="getYearEMP(this)">            
            <c:forEach items="${years}" var="years">
												<spg:option value="${years.year}">${years.year}</spg:option>
												</c:forEach>
            		
          </spg:select>
        </div>
        <label class="col-md-1 col-sm-1 newpad">Area</label>
        <div class="col-md-2 col-sm-2 nopad">
          <select class="form-control feld areaload" id="areaId" onchange="getAreaEMP(this)">
            <option value="All">All</option>
         <%-- 					<c:forEach var="area1" items="${activeAreas}">
         
            <option value="${area1.areaDesc}">${area1.areaDesc}</option>
            </c:forEach> --%>
          </select>
        </div>
	<div class="col-md-4 col-md-offset-2 col-sm-5 tp drpdwndiv"> 
	<a class="addbtn btn btn-default tp dropdown-toggle" id="menu2" data-toggle="dropdown"><span class="glyphicon glyphicon-upload sadd"></span>Upload </a> <a onclick="addEmployee()" href="" class="addbtn btn btn-default tp pull-right" data-toggle="modal"><span class="glyphicon glyphicon-plus-sign sadd"></span>Add Employee</a> 
          <ul class="dropdown-menu dropp" role="menu" aria-labelledby="menu2">
            <li>
              <button type="file" class="btn btn-lg addbtn" >
              <span class="glyphicon glyphicon-upload sadd"></span> Upload
                 <spg:input type="file" name="file" id="file1" path="fileData"/>
                             <spg:input type="hidden" path="name" value="Employee"/>                
              </button>
              <button type="button" class="btn btn-lg addbtn" id="downloadtempemp"><span class="glyphicon glyphicon-download-alt tp"></span> Download Template</button>
            </li>
          </ul>
        </div>
      	 </spg:form>                  
           
        </div>
        
        <div class="clearfix"></div>
         <div class="col-md-12"> 
      
        <!--Employee grid data-->
        <div class="table-responsive marg5">
          <table id="onetable" class="table table-fixedheader table-condensed table-striped table-bordered table-hover no-margin">
            <thead>
              <tr>
                <th class="emp1 bg">Token ID</th>
                <th class="emp2 bg">Employee Name</th>
                <th class="emp3 bg">Email id</th>
                <th class="emp4 bg">Area</th>
                <th class="emp5 bg text-center">Edit</th>
              </tr>
            </thead>
            <tbody>
					<%!int a = 0;%>
					
					<c:if test="${employees ne null && employees ne ''}">
					<c:forEach var="employees" items="${employees}">
						<c:if test="${employees.year=='2015'}">

							<tr>

								<td class="emp1"><c:out value="${employees.empId}" /></td>
								<td class="emp2"><c:out value="${employees.fName}" />
									<c:out value="${employees.lName}" /></td>
								<td class="emp3"><c:out
										value="${employees.emailId}" /></td>
								<td class="emp4 dropdown"><div
										class="serch hand dropdown-toggle" id="menu1"
										data-toggle="dropdown"></div>
<%-- 									<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
										<c:forEach var="newsItem" items="${employees.emp}">

											<li>
												<div class="col-md-1 col-sm-1">
													<input type="checkbox">
												</div> <label class="col-md-3 col-sm-3">${newsItem.areaCode}</label>
											</li>
										</c:forEach>
									</ul>
 --%>									</td>

								<td class="hidden-phone col-md-12"><div
										class="edit md-trigger col-md-6 col-sm-6 hand"
										data-modal="modal-13"></div>
									<div class="delete col-md-6 col-sm-6 md-trigger hand "
										id='<c:out value="${employees.pkEmp}deleteEmployee"/>'
										onClick="delete_employee(${employees.pkEmp});"></div></td>
										</tr>
						</c:if>
						
					</c:forEach></c:if>

					<!--      <td>2107726</td>
                <td class="hidden-phone">Ajay Undale</td>
                <td class="hidden-phone">ajay.undale@mahindra.com</td>
                <td class="hidden-phone dropdown"><div class="serch hand dropdown-toggle" id="menu1" data-toggle="dropdown"></div>
                  <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                    <li>
                      <div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-3 col-sm-3">MLU</label>
                      <div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-6 col-sm-6">MPGM HR</label>
                      <div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-3 col-sm-3">RC</label>
                      <div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-6 col-sm-6">Ascend</label>
                      <div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-3 col-sm-3">MPGM</label>
                      <div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-6 col-sm-6">Rise(GEB)</label>
                    </li>
                  </ul></td>
                <td class="hidden-phone col-md-12"><a  data-target="#Edit" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand"></a>
		 <a  data-target="#delete" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand"> </a></td>
              </tr>
              <tr>
                <td>2107726</td>
                <td class="hidden-phone">Sarvan Kumar</td>
                <td class="hidden-phone">SarvanKumar@mahindra.com</td>
                <td class="hidden-phone"><div class="serch hand"></div></td>
                <td class="hidden-phone col-md-12"><a  data-target="#Edit" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand"></a> <a  data-target="#delete" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand"> </a></td>
              </tr>
              <tr>
                <td><span class="name">2107726</span></td>
                <td class="hidden-phone">Yash Mahale</td>
                <td class="hidden-phone">YashMahale@mahindra.com</td>
                <td class="hidden-phone"><div class="serch hand"></div></td>
                <td class="hidden-phone col-md-12"><a  data-target="#Edit" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand"></a> <a  data-target="#delete" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand"> </a></td>
              </tr>
              <tr>
                <td>2107726</td>
                <td class="hidden-phone">Ajay Undale</td>
                <td class="hidden-phone">ajay.undale@mahindra.com</td>
                <td class="hidden-phone"><div class="serch hand"></div></td>
                <td class="hidden-phone col-md-12"><a  data-target="#Edit" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand"></a> <a  data-target="#delete" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand"> </a></td>
              </tr>
              <tr>
                <td>2107726</td>
                <td class="hidden-phone">Jitesh Talreja</td>
                <td class="hidden-phone">JiteshTalreja@mahindra.com</td>
                <td class="hidden-phone"><div class="serch hand"></div></td>
                <td class="hidden-phone col-md-12"><a  data-target="#Edit" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand"></a> <a  data-target="#delete" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand"> </a></td>
              </tr>
            </tbody>
          </table>
        </div>
        <!--Employee grid data end--> 
        
        </tbody>
        </table>
    </div>
	  <div class="row">
      <div class="col-sm-6"> <label class="col-sm-3 nopad">Total Respondents</label>
       <div class="col-sm-2"><input type="text" id="empcount" class="form-control text-center" disabled=""></input></div></div>
	</div>
      </div>
    </div>
  </div>
</section>
<!-- 
 Section end-->
<div class="clearfix"></div>
<!-- Question Section -->
<section id="Question" class="Question-section">
  <div class="container">
    <div>
      <div class="col-md-12">
        <h1 class="text-left myh1">Question Section</h1>
      </div>
      <div class="col-lg-12 col-sm-12 pright">  
       <spg:form method="POST" action="uploadFile?${_csrf.parameterName}=${_csrf.token}" id="uploadFile1" enctype="multipart/form-data" modelAttribute="FileBean">
        <label class="col-md-1 col-sm-2 nopad">Select Year</label>
        <div class="col-md-2 col-sm-2 nopad">
          <spg:select class="form-control feld " id="getQuestYears" path="year">
              			<c:forEach items="${years}" var="yearsQuest">
												<option value="${yearsQuest.pkYear}">${yearsQuest.year}</option>
												</c:forEach>
          </spg:select>
        </div>
        <label class="col-md-1 col-sm-1 newpad">Area</label>
        <div class="col-md-2 col-sm-2 nopad">
 			<select class="form-control feld areaload" name="areaCode" id="getQuestArea" >
              									<option value="0">Select Area</option>
												<c:forEach items="${areas}" var="areasQuest">
												<option value="${areasQuest.areaCode}">${areasQuest.areaDesc}</option>
										</c:forEach>
        		 </select>
                
        </div>
        <div class="col-md-4 col-sm-5 col-md-offset-2 tp drpdwndiv"> 
        	<a class="addbtn btn btn-default tp dropdown-toggle" id="menu3" data-toggle="dropdown">
        		<span class="glyphicon glyphicon-upload sadd"></span>Upload </a>
          <ul class="dropdown-menu dropp" role="menu" aria-labelledby="menu3">
            <li>
              <button type="file" class="btn btn-lg addbtn" >
              <span class="glyphicon glyphicon-upload sadd"></span> Upload
              <spg:input type="file" id="file2" path="fileData"/>
              <spg:input type="hidden" path="name" value="Question"/>
              </button>
              <button type="button" class="btn btn-lg addbtn"  id="downloadquest" ><span class="glyphicon glyphicon-download-alt tp"></span> Download Template</button>
            </li>
          </ul>
          </a>
          </spg:form>
           <a data-target="#addQuestion" class="addbtn btn btn-default tp pull-right" data-toggle="modal" onClick="clearFields();"><span class="glyphicon glyphicon-plus-sign sadd"></span>Add Question</a> </div>
        </div>
        <div class="clearfix"></div>
 <!--<div class="col-md-3 col-sm-12 col-md-offset-3 nopad"> <a type="file" class="addbtn btn btn-default pull-left hand"> <img src="images/upload.png" style="margin-right: 10px;"/>Upload
          <input type="file">
          </a> <a data-target="#addQuestion" class="addbtn btn btn-default pull-right" data-toggle="modal"><img src="images/add.png" style="margin-right: 10px;"/>Add Question</a> </div>
        <div class="clearfix"></div>
       -->
 <!--Qustion grid data-->
 <div class="col-md-12">
        <div class="table-responsive marg5">
         <spg:form method="post" action="insertNewQuests" commandName="QuestionListDTO" id="questTableForm" >
 
          <table class="table table-fixedheader table-condensed table-striped table-bordered table-hover no-margin"  id="questTable">
            <thead>
              <tr>
                <th class="que1 bg">Area</th>
                <th class="que2 bg">Question Description</th>
                <th class="que3 bg">Question Type</th>
                <th class="que5 bg">Year</th>
                <th colspan="2" class="que4 bg text-center">Edit</th>
              </tr>
            </thead>
            <tbody>
            
            <c:if test ="${questions ne null && questions ne ''}" >
            <c:forEach var="questions" items="${questions}" varStatus="question">
				<tr>
                <td class="que1"><input type="hidden" name="questions[${question.index}].areaCode" value="${questions.questionArea.areaCode}">${questions.questionArea.areaDesc}</td>
                <td class="que2"><input type="hidden" name="questions[${question.index}].questionDesc" value="${questions.questionDesc}">${questions.questionDesc}</td>
                <td class="que3"><input type="hidden" name="questions[${question.index}].questionType" value="${questions.questType.pkQuestType}">${questions.questType.questTypeDesc}</td>
                <td class="que5"><input type="hidden" name="questions[${question.index}].year" value="${questions.yearMap.pkYear}">${questions.yearMap.year}</td>
                 <td class="que4 " id="editQ_${questions.questionId} ">
                 <a  data-target="#editQuest" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand editQuest " id="editQ_${questions.questionId} "></a>
                 <a data-target="#deleteQuestMod" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand" onClick="setQuestId(${questions.questionId})"> </a></td>
               </tr>
       		</c:forEach></c:if>
  <!--             <tr>
                <td>Open ended</td>
                <td class="hidden-phone">I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone">MLU</td>
                <td class="hidden-phone col-md-12"><a  data-target="#Edit" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand"></a> <a  data-target="#delete" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand"> </a></td>
              </tr>
              <tr>
                <td><span class="name">Ratings</span></td>
                <td class="hidden-phone">Rating I see Career opportunities in line my career aspirations.</td>
                <td class="hidden-phone">MLU</td>
                <td class="hidden-phone col-md-12"><a  data-target="#Edit" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand"></a> <a  data-target="#delete" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand"> </a></td>
              </tr>
              <tr>
                <td>Open ended</td>
                <td class="hidden-phone">I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone">MLU</td>
                <td class="hidden-phone col-md-12"><div class="edit md-trigger col-sm-6 col-md-6 hand" data-modal="modal-13"></div>
                  <div class="delete col-md-6 md-trigger col-sm-6 hand" data-modal="modal-8"> </div></td>
              </tr>
              <tr>
                <td>Open ended</td>
                <td class="hidden-phone">I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone">MLU</td>
                <td class="hidden-phone col-md-12"><a  data-target="#Edit" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand"></a> <a  data-target="#delete" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand"> </a></td>
              </tr>
              <tr>
                <td>Open ended</td>
                <td class="hidden-phone">I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone">MLU</td>
                <td class="hidden-phone col-md-12"><a  data-target="#Edit" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand"></a> <a  data-target="#delete" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand"> </a></td>
              </tr>
              <tr>
   -->          </tbody>
           
          </table>
          <div class="pull-right" ><button type="Submit" class="addbtn btn btn-default" id='submitNewQn' value="Submit" style="display: none;"  onClick="return addNewQuests();" >Submit</button></div>
          </spg:form>
         </div>
        <!--Qustion grid data end--> 
      </div>
    </div>
  </div>
</section>
<!-- Question Section end--> 
<!-- Area Section -->
<section id="Area" class="Area-section">
  <div class="container">
    <div class="">
   <div class="col-md-12">
        <h1 class="text-left myh1"> Create Survey Section</h1>
   </div>     
    <div class="col-md-12 col-sm-12">
        <div class=" panel panel-primary">
          <div class="panel-heading">Select Date</div>
          <div class="panel-body">
            <form id="addconfig" class="addconfig" action="configinsert" role="form" method="post" modelAttribute="MTSurveyConfigDTO">
              <div class="form-group">
                <label class="col-md-2 col-xs-2 col-sm-2 nopad">Year</label>
                <div class="col-md-2 col-xs-2 col-sm-2">
                  <!--  <select class="form-control" id="yearConfig" name="year">
			          		<option value="2016">2016</option>
			          		<option value="2017">2017</option>
			          		<option value="2018">2018</option>
			          		<option value="2019">2019</option>
			          		<option value="2020">2020</option>
      </select> -->  <input type="text" class="form-control" id="yearConfig" name="year" readonly> 
                </div>
              </div>
              <div class="clearfix"></div>
              <div class="form-group">
                <label class="col-md-2 col-sm-2 nopad">Start Date</label>
                <div class="col-md-4 col-sm-4">
                  <!-- <div class="controls input-append date form_date" data-date="" data-date-format="dd MM yyyy" 								data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <div class="inner-addon right-addon"> <i class="glyphicon glyphicon-calendar"></i>
                      <input size="16" class="form-control" type="text" value="" name="startDate" readonly>
                    </div>
                    <span class="add-on"><i class="icon-remove"></i></span> <span class="add-on"><i class="icon-th"></i></span> 
                  </div> -->        
                <div class="inner-addon right-addon"> <i class="glyphicon glyphicon-calendar"></i>  
                  <input type="text" class="form-control datepicker" id="datepicker" name="startDate" readonly="true">
                </div> 
                </div>
                <label class="col-md-2 col-sm-2 nopad">End Date</label>
                <div class="col-md-4 col-sm-4">
                  <!-- <div class="controls input-append date form_date" data-date="" data-date-format="dd MM yyyy" 								data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <div class="inner-addon right-addon"> <i class="glyphicon glyphicon-calendar"></i>
                      <input size="16" class="form-control" type="text" value="" id="enddate" name="endDate" readonly>
                    </div>
                    <span class="add-on"><i class="icon-remove"></i></span> <span class="add-on"><i class="icon-th"></i></span>
                   </div> -->
                    <div class="inner-addon right-addon"> <i class="glyphicon glyphicon-calendar"></i>  
                  		<input type="text" class="form-control datepicker" id="datepicker2" name="endDate" readonly="true">
                	</div> 
                </div>
              </div>
              <div class="clearfix"></div>
              <!-- <div class="form-group">
                <label class="col-md-2 col-sm-2 nopad">Start Time</label>
                <div class="col-md-4 col-sm-4">
                  <div class="input-group bootstrap-timepicker">
                    <input id="timepicker1" type="text" class="input-small form-control" name="startTime">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span> </div>
                </div>
                <label class="col-md-2 col-sm-2 nopad">End Time</label>
                <div class="col-md-4 col-sm-4 ">
                  <div class="input-group bootstrap-timepicker">
                    <input id="timepicker2" type="text" class="input-small form-control" name="endTime">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span> </div>
                </div>
              </div> -->
              <div class="clearfix"></div>
              <div class="form-group">
                <label class="col-md-2 col-sm-2 nopad">Frequency  (in days) </label>
                <div class="col-md-4 col-sm-4">
                  <select class="form-control" id="frequency" onchange="showNextDate()" name="frequency">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                  </select>
                </div>
                <label class="col-md-2 col-sm-2 nopad">Next Date</label>
                <div class="col-md-4 col-sm-4">
                  <div class="controls input-append date form_date" data-date="" data-date-format="dd MM yyyy" 								data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <div class="inner-addon right-addon"> <i class="glyphicon glyphicon-calendar"></i>
                   <input size="16" class="form-control" value="" id="nextDate" type="text" disabled>
                     </div>
                    <span class="add-on"><i class="icon-remove"></i></span> <span class="add-on"><i class="icon-th"></i></span> </div>
                </div>
              </div>
              <div class="clearfix"></div>
              <div class="form-group">
              <div class="text-right" style="margin-right:15px;">
                 <button type="button" id="conf" class="addbtn btn btn-default" data-dismiss="modal" onclick="submitConfig()">Submit</button>
              </div>               
              </div>      
            </form>
          </div>
        </div>
        <div class="clearfix"></div>
        
        <div class="table-responsive">
        <h1 class="text-left myh1">Survey List</h1>
          <table class="table table-fixedheader table-condensed table-striped table-bordered table-hover no-margin" id="surveyListTable">
            <thead>
              <tr>
                <th class="survey1 bg">Serial Number</th>
                <th class="survey1 bg">Year</th>
                <th class="survey2 bg">Start Date</th>
                <th class="survey2 bg">End Date</th>
              </tr> 
            </thead>
            <tbody>
  <%--            <c:forEach var="config" items="${config}"> 
              <tr>
                <td class="survey1"><c:out value="${config.sno}" /></td>
                <td class="survey1"><c:out value="${config.year}" /></td>
                <td class="survey2"><c:out value="${config.startDate}" /></td>
                <td class="survey2"><c:out value="${config.endDate}" /></td>    
              </tr> 
             </c:forEach>         
   --%>          </tbody>
          </table>
        </div>
      </div>
     </div>
  </div>
  
</section>
<!-- Area Section end-->
<!-- Tracking Section  start-->
<section id="Tracking" class="Tracking-section">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
       <h1 class="text-left myh1">Tracking Section</h1>
      </div>
       <div id="trackingCount" class="textbold col-md-12">
      </div>
      <div class="col-lg-12 col-sm-12 pright marg10">
        <label class="col-md-1 col-sm-1 nopad">Status</label>
        <div class="col-md-2 col-sm-2 nopad ml">
						<select id="trackingDropDown" class="form-control feld">
							<option value="all" selected="selected">All</option>
							<option value="notStarted">Not Started</option>
							<option value="progress">In Progress</option>
							<option value="completed">Completed</option>
						</select>
					</div>
					<!-- <div class='smalllable'> 
					<input type = 'checkbox' class='checkbox' id='selectTracking' onChange='checkUncheckAll(this)'>
					 </div> -->
	    <div class="col-md-9 col-sm-9 tp text-right"> 
          <button type="button" id="exporttoExcel" class="addbtn btn btn-default tp" style="margin-left: 10px;"><span class="glyphicon glyphicon-download-alt sadd"></span>Export To Excel</button>
        <!--   <button type="button" id="sendMailTracking" class="addbtn btn mr btn-default tp"><span class="glyphicon glyphicon-envelope sadd"></span>Send E-Mail</button> </div> -->
        </div>
        <div class="clearfix"></div>
      <!--grid data-->
      <div class="container marg5">
        <table class="table table-fixedheader table-condensed table-striped table-bordered orngtbl table-hover tallbrd no-margin" id= "trackingtable">
          <thead>
            <tr>
              <th class="bg trck1"><input type = 'checkbox' class='checkbox' id='selectTracking' onChange='checkUncheckAll(this)' checked></th>
              <th class="bg trck2">Token Number</th>
              <th class="bg trck3">Name</th>
              <th class="bg trck4">Email</th>
              <th class="bg trck5">Status</th>
            </tr>
          </thead>
          <tbody>
            <tr class="brorange">
              <td class="trck1"><input type="checkbox" /></td>
              <td class="trck2">C57000XX</td>
              <td class="trck3">John Smith</td>
              <td class="trck4">johnsmith@gmail.com</td>
              <td class="trck5">Completed</td>
            </tr>
            <tr class="brorange">
              <td style="width:5%"><input type="checkbox" /></td>
              <td style="width:20%" class="hidden-phone">C57000XX</td>
              <td style="width:45%"  class="hidden-phone">John Smith</td>
              <td style="width:20%"  class="hidden-phone">johnsmith@gmail.com</td>
              <td style="width:10%">Completed</td>
            </tr>
            <tr class="brorange">
              <td style="width:5%"><input type="checkbox" /></td>
              <td style="width:20%" class="hidden-phone">C57000XX</td>
              <td style="width:45%"  class="hidden-phone">John Smith</td>
              <td style="width:20%"  class="hidden-phone">johnsmith@gmail.com</td>
              <td style="width:10%">Completed</td>
            </tr>
            <tr class="brorange">
              <td style="width:5%"><input type="checkbox" /></td>
              <td style="width:20%" class="hidden-phone">C57000XX</td>
              <td style="width:45%"  class="hidden-phone">John Smith</td>
              <td style="width:20%"  class="hidden-phone">johnsmith@gmail.com</td>
              <td style="width:10%">Completed</td>
            </tr>
          </tbody>
        </table>
      </div>
      
      <!--grid data end--> 
    </div>
  </div>
</section>

<!-- Tracking Section end --> 

<!--Report section start
<section id="Report" class="Report-section">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h1 class="text-left myh1">Report Section</h1>
      </div>
    </div>
  </div>
</section>
Report section end-->
<!--Report section start-->
<section id="Report" class="Report-section">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h1 class="text-left myh1">Report Section</h1>
      </div>
      <div class="col-lg-12 col-md-12 col-sm-12">
      
       <div class="col-lg-12 col-sm-12 pright padleft">
        <h1 class="text-left myh1 col-sm-12 padleft">Yes/No Questions</h1>
         <div class="col-sm-6 padleft">
                 <label class="col-md-2 nopad">Select Year :</label>
                 <div class="col-md-3 col-sm-3 year3 nopad">
                  	  <select class="form-control feld" name="year" id="questionsYear" onchange="populateYes()" >
              									<c:forEach items="${years}" var="years">
												<option value="${years.year}">${years.year}</option>
												</c:forEach>
            		 </select>
            	 </div>
                </div>
	    <div class="tp text-right col-sm-6 padleft"> 	   
          <button type="button" id="yesNoExport" class="addbtn btn btn-default tp" style="margin-left: 10px;"><span class="glyphicon glyphicon-download-alt sadd"></span>Export To Excel</button>
          </div>
        </div>
        <div class="clearfix"></div>
        <!--Employee grid data-->
        <div class="table-responsive mtop">
          <table class="table table-fixedheader table-condensed table-striped table-bordered orngtbl table-hover tallbrd no-margin" id="yesNoTable">
            <thead>
              <tr>
                <th class="bg ysq1" style="text-align: center;">Employee ID</th>
                <th class="hidden-phone bg ysq2" style="text-align: center;">Employee Name</th>
                <th class="hidden-phone bg ysq3" style="text-align: center;">Area</th>
                <th class="hidden-phone bg ysq4" style="text-align: center;">Question</th>
                <th class="hidden-phone bg ysq5" style="text-align: center;"> Answer</th>
                <th class="hidden-phone bg ysq6" style="text-align: center;"> Year</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="hidden-phone  ysq1">2107726</td>
                <td class="hidden-phone  ysq2">Ajay Undale</td>                
                <td class="hidden-phone ysq3">GMC</td>
                <td class="hidden-phone ysq4">I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone ysq5">Yes</td>
                <td class="hidden-phone ysq6">2016</td>
              </tr>
               <tr>
                <td class="hidden-phone  ysq1">2107726</td>
                <td class="hidden-phone  ysq2">Ajay Undale</td>                
                <td class="hidden-phone ysq3">GMC</td>
                <td class="hidden-phone ysq4">I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone ysq5">Yes</td>
                <td class="hidden-phone ysq6">2016</td>
              </tr>
               <tr>
                <td class="hidden-phone  ysq1">2107726</td>
                <td class="hidden-phone  ysq2">Ajay Undale</td>                
                <td class="hidden-phone ysq3">GMC</td>
                <td class="hidden-phone ysq4">I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone ysq5">Yes</td>
                <td class="hidden-phone ysq6">2016</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
     <div class="col-lg-12 col-md-12 col-sm-12">
    <div class="col-lg-12 col-sm-12 pright padleft">
        <h1 class="text-left myh1 col-sm-12 padleft">Comments</h1>
       <div class="col-sm-6 padleft">
                 <label class="col-md-2 nopad">Select Year :</label>
                 <div class="col-md-3 col-sm-3 year3 nopad">
                  	  <select class="form-control feld" name="year" id="CommentsYear" onchange="populateComment()">
              									<c:forEach items="${years}" var="years">
												<option value="${years.year}">${years.year}</option>
												</c:forEach>
            		 </select>
            	 </div>
                </div>
	    <div class="tp text-right col-sm-6 padleft"> 
          <button type="button" id="commentExport" class="addbtn btn btn-default tp" style="margin-left: 10px;"><span class="glyphicon glyphicon-download-alt sadd"></span>Export To Excel</button>
          </div>
        </div>
        <div class="clearfix"></div>
        <!--Employee grid data-->
        <div class="table-responsive mtop">
          <table class="table table-fixedheader table-condensed table-striped table-bordered orngtbl table-hover tallbrd no-margin" id="commentsTable">
            <thead>
              <tr>
                <th class="bg comm1" style="text-align: center;">Employee ID</th>
                <th class="hidden-phone bg comm2" style="text-align: center;">Employee Name</th>
                <th class="hidden-phone bg comm3" style="text-align: center;">Area</th>
                <th class="hidden-phone bg comm4" style="text-align: center;">Comments</th>
                <th class="hidden-phone bg comm5" style="text-align: center;"> Year</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="comm1">2107726</td>
                <td class="hidden-phone comm2">Ajay Undale</td>
                <td class="hidden-phone comm3">GMC</td>
                <td class="hidden-phone comm4">I feel that i am contributing to the overall version of my company.I feel that i am contributing to the overall version of my company.I feel that i am contributing to the overall version of my company.I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone comm5">2016</td>
              </tr>
               <tr>
                <td class="comm1">2107726</td>
                <td class="hidden-phone comm2">Ajay Undale</td>
                <td class="hidden-phone comm3">GMC</td>
                <td class="hidden-phone comm4">I feel that i am contributing to the overall version of my company.I feel that i am contributing to the overall version of my company.I feel that i am contributing to the overall version of my company.I feel that i am contributing to the overall version of my company.I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone comm5">2016</td>
              </tr>
               <tr>
                <td class="comm1">2107726</td>
                <td class="hidden-phone comm2">Ajay Undale</td>
                <td class="hidden-phone comm3">GMC</td>
                <td class="hidden-phone comm4">I feel that i am contributing to the overall version of my company.I feel that i am contributing to the overall version of my company.I feel that i am contributing to the overall version of my company.I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone comm5">2016</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-lg-12 col-md-12 col-sm-12">      
        <div class="col-lg-12 col-sm-12 pright padleft">
         <h1 class="text-left myh1 col-sm-12 padleft">Open Ended Questions</h1>
       <div class="col-sm-6 padleft">
                 <label class="col-md-2 nopad">Select Year :</label>
                 <div class="col-md-3 col-sm-3 year3 nopad">
                  	  <select class="form-control feld" name="year" id="openendedYear" onchange="getOpenEnd();">
              									<c:forEach items="${years}" var="years">
												<option value="${years.year}">${years.year}</option>
												</c:forEach>
            		 </select>
            	 </div>
                </div>
	    <div class="tp text-right col-sm-6 padleft"> 
          <button type="button" id="openEndedExport" class="addbtn btn btn-default tp" style="margin-left: 10px;"><span class="glyphicon glyphicon-download-alt sadd"></span>Export To Excel</button>
          </div>
        </div>
        <div class="clearfix"></div>
        <!--Employee grid data-->
        <div class="table-responsive mtop">
          <table class="table table-fixedheader table-condensed table-striped table-bordered orngtbl table-hover tallbrd no-margin" id="openEndedTable">
            <thead>
              <tr>
                <th class="bg oeq1" style="text-align: center;">Employee ID</th>
                <th class="hidden-phone bg oeq2" style="text-align: center;">Employee Name</th>
                <th class="hidden-phone bg oeq3" style="text-align: center;">Area</th>
                <th class="hidden-phone bg oeq4" style="text-align: center;">Question</th>
                <th class="hidden-phone bg oeq5" style="text-align: center;"> Answer</th>
                <th class="hidden-phone bg oeq6" style="text-align: center;"> Year</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="oeq1">2107726</td>
                <td class="hidden-phone oeq2">Ajay Undale</td>
                <td class="hidden-phone oeq3">GMC</td>
                <td class="hidden-phone oeq4">I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone oeq5">Yes</td>
                <td class="hidden-phone oeq6">2016</td>
              </tr>
                 <tr>
                <td class="oeq1">2107726</td>
                <td class="hidden-phone oeq2">Ajay Undale</td>
                <td class="hidden-phone oeq3">GMC</td>
                <td class="hidden-phone oeq4">I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone oeq5">Yes</td>
                <td class="hidden-phone oeq6">2016</td>
              </tr>
                 <tr>
                <td class="oeq1">2107726</td>
                <td class="hidden-phone oeq2">Ajay Undale</td>
                <td class="hidden-phone oeq3">GMC</td>
                <td class="hidden-phone oeq4">I feel that i am contributing to the overall version of my company.</td>
                <td class="hidden-phone oeq5">Yes</td>
                <td class="hidden-phone oeq6">2016</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</section>
<!--Report section end--> 
<!-- Add Delet entry popup start -->
        
        <div class="modal fade deleteModal" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
          <div class="modal-dialog" style="width: 720px;">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
              </div>
              <div class="modal-body">
                <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>
              </div>
              <div class="modal-footer">
                <button type="button" class="deleterow btn addbtn" ><span class="glyphicon glyphicon-ok-sign"></span>Yes</button>
                <button type="button" class="btn addbtn " data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>No</button>
              </div>
            </div>
            <!-- /.modal-content --> 
          </div>
          <!-- /.modal-dialog --> 
        </div>
        <!-- Delete entry popup end --> 

<!--  upload employee modal -->
<!-- <div class="modal fade deletepopup" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title custom_align" id="Heading"><span class="glyphicon glyphicon-plus-sign sadd"></span>Upload Alert</h4>
          </div>
          <div class="modal-body">
            <div class="alert alert-danger">
            <span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to update details?
            <span></span> You are uploading Employee for year:  
            <span id="uplYear"> </span></div>
          </div>
          <div class="modal-footer ">
            <button type="button" class="deleterow btn uploadbtn"><span class="glyphicon glyphicon-ok-sign"></span>Ok</button>
            <button type="button" class="btn addbtn" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>Cancel</button>
          </div>
        </div>
        /.modal-content 
      </div>
      /.modal-dialog 
    </div> -->
<!-- end upload employee modal -->

<div class="modal fade EditEmployee" id="EditEmployee" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
          <div class="modal-dialog" style="width: 660px;">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title custom_align" id="Heading">Edit Your Detail</h4>
              </div>
              <div class="modal-body">
                <div class="form-group">
                  <input class="form-control " type="text" placeholder="2107726">
                </div>
                <div class="form-group">
                  <input class="form-control " type="text" placeholder="Ajay Undale">
                </div>
                <div class="form-group">
                  <textarea rows="2" class="form-control" placeholder="ajay.undale@mahindra.com"></textarea>
                </div>
                <div class="form-group">
                	<div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-3 col-sm-3">MLU</label>
                      <div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-3 col-sm-3">MPGM HR</label>
                      <div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-3 col-sm-3">RC</label>
                      <div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-3 col-sm-3">Ascend</label>
                      <div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-3 col-sm-3">MPGM</label>
                      <div class="col-md-1 col-sm-1">
                        <input type="checkbox">
                      </div>
                      <label class="col-md-3 col-sm-3">Rise(GEB)</label>
                </div>
              </div>
              <div class="modal-footer" style="text-align:center !important;">
                <button type="button" class="md-close btn addbtn btn-lg" data-dismiss="modal"><span class="glyphicon glyphicon-ok-sign" style="top: 0;"></span> Update</button>
              </div>
            </div>
                

<script>
$(".deleterow").on("click", function(){
var $killrow = $(this).parent('tr');
    $killrow.addClass("danger");
$killrow.fadeOut(2000, function(){
    $(this).remove();
});});
</script>

<script>


    $("#exporttoExcel").on("click", function(){
	$("#trackingtable").table2excel({
		exclude: ".noExl",
		name: "download",
		filename: "Tracking",
		fileext: ".xls",
		exclude_img: true,
		exclude_links: true,
		exclude_inputs: true
	   });
	});

$('#file1').change(function() {
	var file=$('#file1').val();

	if (!(/\.(xlsx|xls|xlsm)$/i).test(file)) {
	//alert('Please upload valid excel file as in Employee Template');
	swal('Please upload valid excel file as in Employee Template');
	
	$(file).val('');
	}
	else{
		$(".loader").show();
		
		$('#uploadFile').submit();
		
	// write your code here to upload file
	}
	
	}); 
	 /* $(".uploadbtn").click(function(){
		$(".loader").show();
		$('#uploadFile').submit();
		$("#file1").val("");
	
	}) */
 
$('#file2').change(function() {
	var file=$('#file2').val();

	if (!(/\.(xlsx|xls|xlsm)$/i).test(file)) {
//	alert('Please upload valid excel file as in Question Template');
	swal('Please upload valid excel file as in Question Template');
	$(file).val('');
	}
	else{
		$(".loader").show();
		

		$('#uploadFile1').submit();
		// write your code here to upload file
	}
	
	});

</script>

<script>
$("#downloadtempemp").on("click", function(){
	
	window.location.href="fileDownload?type=Employee";
});

$("#downloadquest").on("click", function(){
	
	window.location.href="fileDownload?type=Question";
});

 </script>


 
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	$('.form_date').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
</script> 
<script type="text/javascript">
$('#timepicker1').timepicker();
</script> 
<script type="text/javascript">
$('#timepicker2').timepicker();
</script>
<script>
$(document).ready(function() {
	 var modelAttributeValue = '${modelAttribute}';
	 

	 if (modelAttributeValue=="okay"){
		 alert("uploaded successfully");
		 
	 }
	 
    var max_fields      = 20; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID
    var k=0;
    var x = 1; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            k=x-1;
            $(wrapper).append('<div class="clearfix"></div><div class="flefttext addqueminus"><input type="text" class="form-control feld addtext" maxlength="250" name="subquest['+k+'].subQuestDesc"/><button type="button" class="remove_field btn add-more btnad"><span class="glyphicon glyphicon-minus"></span></button></div><div class="clearfix"></div>'); //add input box
        }
    });
    
    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).parent('div').remove(); x--;
    });
});
</script>

<script>
$('#AddArea').on('shown.bs.modal', function () {
    $('#areaDesc').focus();
})
</script>
</body>
</html>
