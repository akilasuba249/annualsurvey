$(document)
		.ready(
				function() {
					
					$("#getreport").click(function() {
			//		$("#yesNoExport").click(function() {
						
					//	alert("Button Clicked");
					var currentTime = new Date();
					
					var a = currentTime.getFullYear();
					
					
						$.ajax({
							type:"POST",
							url:"getYesNoReport",
							async:false,
							data:{year:a},
							success:function(dataFromServer){
							//	alert("Success");
							//	alert("Data from server is "+dataFromServer);
								$("#yesNoTable tbody").empty();
								var yesNoData = jQuery.parseJSON(dataFromServer);
							//	alert(yesNoData);
								populateYesNoTable(yesNoData);
								
								
							},
							error:function(){
								alert("Please Try Again");
							}
						});
						
			//		});
					
					
			//		$("#commentExport").click(function() {
						
					//	alert("Button Clicked");
						
						$.ajax({
							type:"POST",
							url:"getCommentsReport",
							async:false,
							data:{year:a},
							success:function(dataFromServer){
							//	alert("Success");
							//	alert("Data from server is "+dataFromServer);
								$("#commentsTable tbody").empty();
								var commentData = jQuery.parseJSON(dataFromServer);
						//		alert(commentData);
								populateCommentsTable(commentData);
								
								
							},
							error:function(){
								alert("Please Try Again");
							}
						});
						
			//		});
					
				//	$("#openEndedExport").click(function() {
						
					//	alert("Button Clicked");
						
						$.ajax({
							type:"POST",
							url:"getOpenEndedReport",
							async:false,
							data:{year:a},
							success:function(dataFromServer){
							//	alert("Success");
							//	alert("Data from server is "+dataFromServer);
								$("#openEndedTable tbody").empty();
								var openEndedData = jQuery.parseJSON(dataFromServer);
							//	alert(openEndedData);
								populateopenEndedTable(openEndedData);
								
								
							},
							error:function(){
								alert("Please Try Again");
							}
						});
						
				//	});
						
						$("#yesNoExport").click(function(){
							  $("#yesNoTable").table2excel({
							    // exclude CSS class
							    exclude: ".noExl",
							    name: "YesNoReport",
							    filename: "YesNoReport", //do not include extension
							    fileext: ".xls",
								exclude_img: true,
								exclude_links: true,
								exclude_inputs: true
							  }); 
							});
						
						$("#commentExport").click(function(){
							  $("#commentsTable").table2excel({
							    // exclude CSS class
							    exclude: ".noExl",
							    name: "CommentReport",
							    filename: "CommentReport", //do not include extension
							    fileext: ".xls",
								exclude_img: true,
								exclude_links: true,
								exclude_inputs: true
							  }); 
							});
						
						$("#openEndedExport").click(function(){
							  $("#openEndedTable").table2excel({
							    // exclude CSS class
							    exclude: ".noExl",
							    name: "OpenEndedReport",
							    filename: "OpenEndedReport", //do not include extension
							    fileext: ".xls",
								exclude_img: true,
								exclude_links: true,
								exclude_inputs: true
							  }); 
							});
					
				});
				});

function populateYesNoTable(yesNoData){

var tr;
$.each(yesNoData, function(idx, obj) {
	
		tr = $('<tr/>');
		tr.append("<td class='hidden-phone  ysq1'>"+obj.emplolyeeID+"</td>");
		tr.append("<td class='hidden-phone  ysq2'>"+obj.employeeName+"</td>");
		tr.append("<td class='hidden-phone  ysq3'>"+obj.areaDescription+"</td>");
		tr.append("<td class='hidden-phone  ysq4'>"+obj.question+"</td>");
		if(obj.answer==1){
			tr.append("<td class='hidden-phone  ysq5'>Yes</td>");
		}else if(obj.answer==2){
			tr.append("<td class='hidden-phone  ysq5'>No</td>");
		}
		else{
			tr.append("<td class='hidden-phone  ysq5'>Not Answered</td>");
		}
		tr.append("<td class='hidden-phone  ysq6'>"+obj.year+"</td>");

		$("#yesNoTable tbody").append(tr);
	
});
};


function populateCommentsTable(commentData){

	var tr;
	$.each(commentData, function(idx, obj) {
		
			tr = $('<tr/>');
			tr.append("<td class='hidden-phone  comm1'>"+obj.employeeID+"</td>");
			tr.append("<td class='hidden-phone  comm2'>"+obj.employeeName+"</td>");
			tr.append("<td class='hidden-phone  comm3'>"+obj.areaDescription+"</td>");
			tr.append("<td class='hidden-phone  comm4'>"+obj.comments+"</td>");
			tr.append("<td class='hidden-phone  comm5'>"+obj.year+"</td>");
			
			
			$("#commentsTable tbody").append(tr);
		
	});
	};
	
	function populateopenEndedTable(openEndedData){

		var tr;
		$.each(openEndedData, function(idx, obj) {
			
				tr = $('<tr/>');
				tr.append("<td class='hidden-phone  oeq1'>"+obj.EmployeeID+"</td>");
				tr.append("<td class='hidden-phone  oeq2'>"+obj.Employee_Name+"</td>");
				tr.append("<td class='hidden-phone  oeq3'>"+obj.Area+"</td>");
				tr.append("<td class='hidden-phone  oeq4'>"+obj.Question+"</td>");
				tr.append("<td class='hidden-phone  oeq5'>"+obj.Answer+"</td>");
				tr.append("<td class='hidden-phone  oeq6'>"+obj.Year+"</td>");
				
				
				$("#openEndedTable tbody").append(tr);
			
		});
		};
		function populateYes(){
				var ar = $("#questionsYear").val();
				$.ajax({
					type:"POST",
					url:"getYesNoReport",
					async:false,
					data:{year:ar},
					success:function(dataFromServer){
					//	alert("Success");
					//	alert("Data from server is "+dataFromServer);
						$("#yesNoTable tbody").empty();
						var yesNoData = jQuery.parseJSON(dataFromServer);
					//	alert(yesNoData);
						populateYesNoTable(yesNoData);
						
						
					},
					error:function(){
						alert("Please Try Again");
					}
				
				});
			}
		
		function populateComment()
		{
			var ar=$("#CommentsYear").val();
			$.ajax({
				type:"POST",
				url:"getCommentsReport",
				async:false,
				data:{year:ar},
				success:function(dataFromServer){
				//	alert("Success");
				//	alert("Data from server is "+dataFromServer);
					$("#commentsTable tbody").empty();
					var commentData = jQuery.parseJSON(dataFromServer);
			//		alert(commentData);
					populateCommentsTable(commentData);
					
					
				},
				error:function(){
					alert("Please Try Again");
				}
			});
		}
		
        function getOpenEnd(){
        	var ar=$("#openendedYear").val();
		$.ajax({
			type:"POST",
			url:"getOpenEndedReport",
			async:false,
			data:{year:ar},
			success:function(dataFromServer){
			//	alert("Success");
			//	alert("Data from server is "+dataFromServer);
				$("#openEndedTable tbody").empty();
				var openEndedData = jQuery.parseJSON(dataFromServer);
			//	alert(openEndedData);
				populateopenEndedTable(openEndedData);
				
				
			},
			error:function(){
				alert("Please Try Again");
			}
		});
        }


