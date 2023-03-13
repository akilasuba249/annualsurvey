$(document).ready(function() {
	
	
	
	/*$("#scrolconfig").scroll( function() {
		
		debugger;
	    if($(this).scrollTop() >= ($(this)[0].scrollHeight - $(this).outerHeight())) 
	    {
	        alert("End here");
	        callgetquestion();
	    }
	});*/
	

	
	
	/*$('#areasection').show();
	$('#Employee').hide();
	$('#Question').hide();
	$('#Area').hide();
	$('#Tracking').hide();
	$('#Report').hide();*/
	
	/*$('#getemployee').click(function(){
		
		alert("getemployee");
		
		$.ajax({
			url : "getemployee",
			type : "POST",
			form:$('#uploadFile').serialize(),
			success : function(response) {
				$('#Employee').show();	
				$('#Question').hide();
				$('#areasection').hide();
				$('#Tracking').hide();
				$('#Report').hide();

				alert(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
		
		
		
	});*/
	/*$('#getarea').click(function(){
		$('#Employee').hide();	
		$('#Question').hide();
		$('#Config').show();
		$('#Tracking').hide();
		$('#Report').hide();
	
	});*/
	
/*$('#getquestion').click(function(){
		
		alert("getquestion");
		
		$.ajax({
			url : "getquestion",
			type : "POST",
			//form:$('#questTableForm').serialize(),
			success : function(response) {
				$('#Question').show();	
				$('#Employee').hide();
				$('#areasection').hide();
				$('#Tracking').hide();
				$('#Report').hide();

				//alert(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
		
		
		
	});*/
/*$('#getarea').click(function(){
	
	alert("getarea");
	
	$.ajax({
		url : "getarea",
		type : "POST",
		form:$('#addconfig').serialize(),
		success : function(response) {
			$('#Area').show();	
			//alert(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
	
	
	
	
});
$('#gettracking').click(function(){
	
	alert("gettracking");
	
	$.ajax({
		url : "gettracking",
		type : "POST",
		form:$('#uploadFile').serialize(),
		success : function(response) {
			$('#Tracking').show();	
			//alert(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
	
	
	
	
});
$('#getreport').click(function(){
	
	alert("getreport");
	
	$.ajax({
		url : "getreport",
		type : "POST",
		form:$('#uploadFile').serialize(),
		success : function(response) {
			$('#Report').show();	
			//alert(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
	
	
	
	
});*/
	
});