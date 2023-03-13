<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% response.addHeader("X-Frame-Options", "SAMEORIGIN");  %>  
<% response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");  %>  
<% response.addHeader("Pragma", "no-cache");  %>  
<% response.addDateHeader ("Expires", 0); %>  
<% response.addHeader("X-Powered-By",""); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta content="width=device-width,initial-scale=1.0" name="viewport">
<title>Employee Master</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
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
            	   <label class="col-md-1 nopad">Select Year</label>
                 <div class="col-md-2">
                   <select class="form-control feld">
                        <option>2015</option>
                        <option>2016</option>
                   </select> 
                 </div>
                 <label class="col-md-1 nopad">Area</label>
                    <div class="col-md-2">
                   <select class="form-control feld">
                        <option>All</option>
                        <option>2016</option>
                   </select> 
                        
                    </div>                   
                    <div class="col-md-3 col-md-offset-3 nopad">
                        <div type="file" class="addbtn btn btn-default pull-left"><img src="images/upload.png" style="margin-right: 10px;"/>Upload<input type="file"></div>
                        <div class="addbtn btn btn-default pull-right" data-toggle="modal" data-target="#Addemployee"><img src="images/add.png" style="margin-right: 10px;"/>Add Employee</div>
                    </div>
  </div>              
  <div class="clearfix"></div>
  <!--add Employee pop-up-->
  <div id="Addemployee" class="modal fade" role="dialog">
              <div class="modal-dialog">
            
                <!-- Modal content-->
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <img src="images/add.png" style="margin-right: 10px;"/>Add Area
                  </div>
                  <div class="modal-body">
                    <div class="form-group">
                        <label class="col-md-2 nopad">Token ID :</label>
                        <div class="col-md-4">
                        	<input type="text" class="form-control feld"/>
                        </div>
                        <label class="col-md-2 nopad">Year :</label>
                        <div class="col-md-4">
                        	<input type="text" class="form-control feld"/>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="form-group">
                        <label class="col-md-2 nopad">First Name :</label>
                        <div class="col-md-4">
                        	<input type="text" class="form-control feld"/>
                        </div>
                        <label class="col-md-2 nopad">Last Name :</label>
                        <div class="col-md-4">
                        	<input type="text" class="form-control feld"/>
                        </div>
                    </div>  
                    <div class="clearfix"></div>
                    <div class="form-group">  
                        <label class="col-md-2 nopad">Email ID :</label>
                        <div class="col-md-4">
                        	<input type="text" class="form-control feld"/>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <label class="col-md-2 nopad">Select Area:</label>
                    <div class="col-md-9">
                        <div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">MLU</label>
                        <div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">Ascend</label>
                        <div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">GMC</label>
                    <div class="clearfix"></div>   
                        <div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">RC</label>
                        <div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">Rise(GEB)</label>
                        <div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">MPGM</label>
                    <div class="clearfix"></div>    
                        <div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">MCARES(HR)</label>
                        <div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">MPGM(HR)</label>
                        <div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">C&b</label>
                    </div>
                  </div>
                  <div class="modal-footer">
                  <button type="button" class="btn btn-default bg" data-dismiss="modal">Submit</button>
                    <button type="button" class="btn btn-default bg" data-dismiss="modal">Close</button>
                  </div>
                </div>
            
              </div>
            </div>
  <!--add Employee pop-up end-->
  <!--grid data-->
   <div class="container marg30">
       	<div class="table-responsive">
          <table class="table table-condensed table-striped table-bordered table-hover no-margin">
            <thead>
              <tr>
                <th style="width:15%" class="bg">Token ID</th>
                <th style="width:25%" class="hidden-phone bg">Employee Name</th>
                <th style="width:40%" class="hidden-phone bg">Email id</th>
                <th style="width:10%" class="hidden-phone bg">Area</th>
                <th style="width:10%" class="hidden-phone bg">Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <span class="name">2107726</span>
                </td>
                <td class="hidden-phone">Ajay Undale</td>
                <td class="hidden-phone">ajay.undale@mahindra.com</td>
                <td class="hidden-phone"><a href="#" data-container="body" data-toggle="popover" data-placement="bottom" data-content='<div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">MLU</label>
                <div class="col-md-1"><input type="checkbox"></div><label class="col-md-6">MPGM HR</label>
                <div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">RC</label>
                <div class="col-md-1"><input type="checkbox"></div><label class="col-md-6">Ascend</label><div class="col-md-1"><input type="checkbox"></div><label class="col-md-3">MPGM</label>
                <div class="col-md-1"><input type="checkbox"></div><label class="col-md-6">Rise(GEB)</label>' data-container="body"><img src="images/serch.png"/></a></td>
                <td class="hidden-phone"><a href=""><img src="images/edit.png" /></a><a href=""> <img src="images/delete.png" class="mrgleft" /></a></td>
              </tr>
              <tr>
                
                <td>
                  <span class="name">2107726</span>
                </td>
                <td class="hidden-phone">Sarvan Kumar</td>
                <td class="hidden-phone">SarvanKumar@mahindra.com</td>
                <td class="hidden-phone"><img src="images/serch.png" /></td>
                <td class="hidden-phone"><a href=""><img src="images/edit.png" /></a><a href=""> <img src="images/delete.png" class="mrgleft" /></a></td>
              </tr>
              <tr>
                
                <td>
                  <span class="name">2107726</span>
                </td>
                <td class="hidden-phone">Yash Mahale</td>
                <td class="hidden-phone">YashMahale@mahindra.com</td>
                <td class="hidden-phone"><img src="images/serch.png" /></td>
               <td class="hidden-phone"><a href=""><img src="images/edit.png" /></a><a href=""> <img src="images/delete.png" class="mrgleft" /></a></td>
              </tr>
              <tr>
                
                <td>
                  <span class="name">2107726</span>
                </td>
                <td class="hidden-phone">Ajay Undale</td>
                <td class="hidden-phone">ajay.undale@mahindra.com</td>
                <td class="hidden-phone"><img src="images/serch.png" /></td>
                <td class="hidden-phone"><a href=""><img src="images/edit.png" /></a><a href=""> <img src="images/delete.png" class="mrgleft" /></a></td>
              </tr>
              <tr>
                
                <td>
                  <span class="name">2107726</span>
                </td>
                <td class="hidden-phone">Jitesh Talreja</td>
                <td class="hidden-phone">JiteshTalreja@mahindra.com</td>
                <td class="hidden-phone"><img src="images/serch.png" /></td>
               <td class="hidden-phone"><a href=""><img src="images/edit.png" /></a><a href=""> <img src="images/delete.png" class="mrgleft" /></a></td>
              </tr>
           </tbody>
          </table>
        </div>

	</div>
    <!--grid data end-->
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript">
    	$("[data-toggle=popover]").popover({
			html: true, 
			content: function() {
				  return $('#popover-content').html();
				}
		});
    </script>
</body>
<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>-->
 </html>



