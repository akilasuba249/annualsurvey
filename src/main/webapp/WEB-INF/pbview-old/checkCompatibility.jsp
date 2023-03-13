<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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

<head>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/scrolling-nav.css" rel="stylesheet">
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/component.css" />
<link rel="stylesheet" href="css/jquery-ui.css" />

<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<!-- <script src="js/jquery.js"></script> 
 --><script src="js/jquery.min.js"></script> 

<!-- Bootstrap Core JavaScript --> 
<script src="js/bootstrap.min.js"></script> 
<!-- <script type="text/javascript" src="js/jquery.nicescroll.min.js"></script> --> 
<!-- Scrolling Nav JavaScript --> 

<!-- <script src="js/scrolling-nav.js"></script> --> 
<!-- <script src="js/ajaxSetup.js"></script> -->    

<!-- <script src="js/user.js"></script> --><script src="js/jquery-ui.js"></script>
<!-- <script src="js/countries.js"></script> -->

<!-- <script src="js/bootstrap-session-timeout.js"></script> -->
<!-- <script src="js/login/moment.min.js"></script> -->
<script src="js/is.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.border{border:1px solid #cdcdcd;padding-left:0;padding-right:0; box-shadow: inset 1px -2px 6px #77777745;}
.center-align{text-align:center;}
.border-top{border-top:1px solid #cdcdcd;padding: 10px;box-shadow: 0px 4px 8px #3c313133;
    background: #f1f1f1;}
.browser-img{padding:10px;}
.background-navbar-inverse{background:#e31837;border-radius: 0;border:#e31837}
.mahindra-logo-img{width: 130px;max-width: 100%;height: auto;}
.navbar-brand-panel{padding:10px;}
.bs-support {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
  
}
.container-package {
    position: relative;
}
</style>

</head>
<body>
<script>
/* $(document).ready(function() {
	debugger;
	var username=$("#encUsername").text();
	if(is.ie(1)||is.ie(2)||is.ie(3)||is.ie(4)||is.ie(5)||is.ie(6)||is.ie(7)||is.ie(8)||is.ie(9)||is.ie(10))
	{			
		
		$(".bs-support").show();
	}
	else
		{
		 window.location.href="annualLogin?username="+username;
		} 
}); */
</script>
 <span id="encUsername" class="hidden">${user}</span>  
 <span id="msg" class=""></span> 
  <nav class="navbar navbar-inverse background-navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand navbar-brand-panel" href="#"><img src="images/mahindra.png" class="mahindra-logo-img"></a>
    </div>
 
  </div>
</nav>
<div class="">
	<div class="container-package">
		
			<div class="bs-support">
			<div class="text-center"><h4>Your Browser Not Supported</h4>The Portal is fully supported on the following browser numbers.</div>
			<div class="col-xs-10 col-xs-offset-2 col-sm-10 col-sm-offset-2">
			
				<div class="col-xs-2 col-sm-2 border">
					<div class="center-align">
						<div class="browser-img">
							<img src="images/compatible_chrome.gif">
						</div>
						<div class="border-top">45.0</div>
					</div>
				</div>
				<div class="col-xs-2 col-sm-2 border">
					<div class="center-align">
						<div class="browser-img">
							<img src="images/compatible_edge.gif">
						</div>
						<div class="border-top">11.0</div>
					</div>
				</div>
				<div class=" col-xs-2 col-sm-2 border">
					<div class="center-align">
						<div class="browser-img">
							<img src="images/compatible_firefox.gif">
						</div>
							<div class="border-top">57.0</div>
					</div>
				</div>
				<div class="col-xs-2 col-sm-2 border">
					<div class="center-align">
						<div class="browser-img">
							<img src="images/compatible_opera.gif">
						</div>
							<div class="border-top">10.0</div>
					</div>
				</div>
				<div class="col-xs-2 col-sm-2 border">
					<div class="center-align">
						<div class="browser-img">
							<img src="images/compatible_safari.gif">
						</div>
						<div class="border-top">10.0</div>
					</div>
				</div>
				</div>
			</div>
	</div>
</div>
 
 
</body>

</html>