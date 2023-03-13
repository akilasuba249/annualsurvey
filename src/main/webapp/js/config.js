function submitConfig()
{

	//************************Some of the code for configuration screen is in get_add_area.js in doc.ready************************
	
	//****************************validation to check if all the values are filled****************************
	
	if($("#datepicker").val()==null||$("#datepicker").val()==""||$("#datepicker2").val()==null||$("#datepicker2").val()==""||$("#frequency").val()==null){
		swal("Please fill all the details!");
	}else{
		
		var startDate = $("#datepicker").datepicker("getDate");
		var endDate = $("#datepicker2").datepicker("getDate");
		
		
				var startDateDate = startDate.getDate();
				var startDateMonth = startDate.getMonth();
				startDateMonth = startDateMonth+1;
				var startDateYear = startDate.getFullYear();
				
			//	alert("StartDate month is"+startDateMonth);
				
				var startDateSend = startDateDate+"/"+startDateMonth+ "/" +startDateYear;
				
				var endDateDate = endDate.getDate();
				var endDateMonth = endDate.getMonth();
				endDateMonth=endDateMonth+1;
				var endDateYear = endDate.getFullYear();
				
				var endDateSend = endDateDate+"/"+endDateMonth+ "/" +endDateYear;
		
		var frequencySend = $("#frequency").val();
		
//****************************startdate should be lesser than enddate validation****************************
		
		if(startDate>=endDate){
			swal("Start Date has to be lesser than End Date");
		}
		else{
			
			var createSurvey;
			createSurvey = confirm("Are you sure you want to create the survey? You shall not be able to undo it");
			if (createSurvey == false) {
			    swal("No survey created");
			} else {
				//alert("You pressed Yes!");
			    
			    //++++++++++++++++++++++++++++++++++++++++++++++++++++CREATE SURVEY STARTS++++++++++++++++++++++++++++++++++++++++++++++++++++
				
				var frequency = $("#frequency").val();
				frequency = parseInt(frequency);
				var now = new Date();
				startDate= new Date(startDate);
				
				now.setDate(startDate.getDate()+frequency);
				
				
				var m_names = new Array("Jan", "Feb", "Mar", 
						"Apr", "May", "Jun", "Jul", "Aug", "Sep", 
						"Oct", "Nov", "Dec");
						
						var curr_date = now.getDate();
						var curr_month = startDate.getMonth();
						var curr_year = startDate.getFullYear();
						var nextdateDisplay= curr_date + "-" + m_names[curr_month] 
						+ "-" + curr_year;
						
						
					//	alert("Next Date Display is "+nextdateDisplay);
						
						$("#nextDate").val(nextdateDisplay);
						curr_month_send = curr_month+1;
						var nextDateSend = curr_date+"/"+curr_month_send+"/"+curr_year;
						
						var yearSend = $('#yearConfig').val();
				
//						var nextDateMail = $("#nextDate").datepicker("getDate");
						var nextDateMail = new Date(nextdateDisplay);
						
					//	alert("Next Date Mail"+nextDateMail);
						
						if((nextDateMail>endDate)){
							swal("Reminder Date is greater than End Date, Please input appropriate values");
						}
						else{
							$.ajax({
								type:"POST",
								url: "configInsert",
								async:true,
								data:{
								frequency:frequencySend,
								startdate:startDateSend,
								enddate:endDateSend,
								nextdate:nextDateSend,
								year:yearSend,
								},
								
								success: function(response){
									if(response=="exists"){
										swal("Survey already exists for "+yearSend);
									}
									else if(response=="success"){
										swal("Survey has been successfully created!");
										getAreasAfterSubmit();
									}
								},
								error:function(){
									swal("Something went wrong, please try again");
								}
									
								});
						}
						
					
			    
			    
			}

			
		}
	}
	
  }


function showNextDate(){
	
	
	var frequencySelected = $("#frequency").val();
	frequencySelected=parseInt(frequencySelected);
	if($("#datepicker").val()!=""&&$("#datepicker2").val()!=""){
		
		var startDate = $("#datepicker").datepicker("getDate");
		var endDate = $("#datepicker2").datepicker("getDate");
		
	//	alert("Startdate"+startDate);
		
		if(startDate<endDate){
			var now = new Date();
			startDate= new Date(startDate);
			
			now.setDate(startDate.getDate()+frequencySelected);
			
			var m_names = new Array("Jan", "Feb", "Mar", 
					"Apr", "May", "Jun", "Jul", "Aug", "Sep", 
					"Oct", "Nov", "Dec");
					
					var curr_date = now.getDate();
					var curr_month = startDate.getMonth();
					
					var curr_year = startDate.getFullYear();
					var nextdateDisplay= curr_date + "-" + m_names[curr_month] 
					+ "-" + curr_year;
					$("#nextDate").val(nextdateDisplay);
			
		}
	}
	
}

function getAreasAfterSubmit(){
	
	
	//Get List of Surveys Start
	
	$.ajax({
		type:"POST",
		url: "getSurveys",
		async:false,
		data:{
		},
		success: function(response){
			if(response!="no_survey"){
				//alert(response);
				
				var surveyList = jQuery.parseJSON(response);
				$("#surveyListTable tbody").empty();
				$.each(surveyList, function(idx, obj) {
					
					++idx;
					
					tr = $('<tr/>');
					tr.append("<td class='survey1'>"+idx+"</td>");
					tr.append("<td class='survey1'>"+obj.year+"</td>");
					tr.append("<td class='survey2'>"+obj.startDate+"</td>");
					tr.append("<td class='survey2'>"+obj.endDate+"</td>");
					
					$("#surveyListTable tbody").append(tr);
				});
			}
			
		},
		error:function(){
			swal("Something went wrong, please try again");
		}
			
		});						
	
//Get List of Surveys End
	
	
	
}