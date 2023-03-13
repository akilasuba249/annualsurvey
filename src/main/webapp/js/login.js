$(document).ready(function() {
			
	$("#loginButton").on('click',function(event){
//		event.preventDefault();
		
		var emailId=$('#login-username').val();
		var password=$('#login-password').val();
		  
		if(emailId == "" || $.trim(emailId) == ""){
//			$('#login_username').focus();
		//	alert("Please enter username");
			swal("Please enter username");
		}else if(password == "" || $.trim(password) == ""){
//			$('#login_password').focus();
		//	alert("Please enter password");
			swal("Please enter password");
		}else{
			$("#loginForm").submit();
		}
	/*	else
		{
			$.post("authentication",$('#loginForm').serialize(),function(response){

				if(response.indexOf("200") != "-1"){
				       window.location.href=response.split("--")[1];
				        
				        }else{
				alert(response);
				            }
			});
			
			
			$.ajax({
			    url: "j_spring_security_check",
			    type: "POST",
			    data: $("#loginForm").serialize(),
			    beforeSend: function (xhr) {
			        xhr.setRequestHeader("X-Ajax-call", "true");
			    },
			    success: function(result) {
			    	debugger;
			       alert(result);
			    }
			});
			}*/
		
	});
	
	
	
	
	
	
	
	
	
	
				});