/**
 * @author Yash Mahale
 */
var empTracking;
var x = 1;
var date=new Date();
var currYear=date.getFullYear();
var year = date.getFullYear();
//var EmployeeArr=[];
$(document)
		.ready(
				function() {
					
					
					
					var scrolconfigbottom; 
					var scrollempbottom;
					var scrollquestionbottom;
					var scrollsurveybottom;
					var scrolltrackingbottom;
					var scrollreportbottom;
					
					var configflag= false;
					 var empflag=false;
					 var questionflag=false;
					 var surveyflag=false;
					 var trackingflag=false;
					 var reportflag=false;
					/* $('#Config').show();
					 $('#Employee').hide();	
					 $('#Question').hide();
					 $('#Tracking').hide();
					 $('#Report').hide();
					 $('#Area').hide();*/
				 
					 $(window).scroll( function() {
							debugger;
						 
						 var scrollposition = $(window).scrollTop() + $(window).height();
						 scrolconfigbottom = $('#scrolconfig').position().top + $('#scrolconfig').outerHeight(true);
					     
						 
						 if(empflag==false && scrollposition >= scrolconfigbottom) {
							 empflag= true;
					    	// $('#Employee').show();	
					    	// alert(" fire Employee");

						   }
						 
						 scrollempbottom = $('#Employee').position().top + $('#Employee').outerHeight(true);
						 
						 if (questionflag== false && scrollposition >= scrollempbottom) { 
						    
						     debugger;
							 callgetquestion();
					    	 questionflag = true;
					    	// $('#Question').show();
					    	 //callgetquestion();
						    }
					     
						 scrollquestionbottom = $('#Question').position().top + $('#Question').outerHeight(true);
					     if (surveyflag== false && scrollposition >= scrollquestionbottom) { 
						    
						     
					    	 surveyflag = true;
					    	// $('#Area').show();
					    	 scrollsurvey();
					    	 //alert(" fire surveyflag");
						    }
					     
					     scrollsurveybottom = $('#Area').position().top + $('#Area').outerHeight(true);
					     if (trackingflag== false && scrollposition >= scrollsurveybottom) { 
						    
						       
					    	 trackingflag = true;
					    	// $('#Tracking').show();
					    	 scrolltracking();
					    	 //alert(" fire trackingflag");
						    }
					     
					     scrolltrackingbottom = $('#Tracking').position().top + $('#Tracking').outerHeight(true);
					     if (reportflag== false && scrollposition >= scrolltrackingbottom) { 
						    
						       
					    	 reportflag = true;
					    	 //$('#Report').show();
					    	 scrollreport();
					    	 //alert(" fire reportflag");
						    }
						 
				     
						}); 
					
					$("#addAreaButton").click(function(e) {
						e.preventDefault();
					//	alert("Emptying area desc");
						$("#areaDesc").val("");
						$("#areaDesc").focus();
					});
					// Start Get Areas
					//$('#getarea').click(function(){
						function area(){
						
						//alert("In getarea in get_add_area .js");
						
						
					var date = new Date();
					var year = date.getFullYear();
				//	alert(year);
					$(".datepicker").datepicker({dateFormat: 'dd-M-y',minDate:0});
				//	$(".datepicker").datepicker({minDate:0});
					//$("select#yearConfig option[value = '"+ year+ "']").attr('selected', 'selected');
					$("#yearConfig").val(year);
					
					
					//harshad commented
							/*$.ajax({
								type : "POST",
								url : "getAreas",
								async : false,
								data : {},
								success : function(response) {
									// alert(" RECIEVED DATA: "+response);
									var areas = jQuery.parseJSON(response);
									$("#mt_areaTable1 tbody").empty();
									$("#mt_areaTable2 tbody").empty();
									var tr;
							//		debugger;
									$
											.each(
													areas,
													function(idx, obj) {
														tr = $('<tr class="tbrder" id = "areatable'
																+ obj.areaCode
																+ '">');
														tr
																.append('<td class="biglable">'
																		+ obj.areaDesc
																		+ '</td>');

													RadioWorking	tr
																.append('<td class="smalllable"> <div class="btn-group" data-toggle="buttons"> \
						 <label class="btn btn-default " onChange="areaCaptureYes(this)";>\
						 <input type="radio" name="active-'
																		+ obj.areaCode
																		+ '" class="areaYes" autocomplete="off" value="on" > \
						 <b><span class="glyphicon glyphicon-ok fwh"></span></b> </label><label class="btn btn-default active" onChange="areaCaptureNo(this)";> \
						 <input type="radio" name="active-'
																		+ obj.areaCode
																		+ '" class="areaNo" autocomplete="off" value="off" >\
						 <b><span class="glyphicon glyphicon-remove fwh"></span></b>\</label></div></td></tr>');Radio Working

														tr.append('<td class="smalllable"><input type="checkbox" id="areaCheck" name="active-'+obj.areaCode+'" onChange="areaCapture(this)" class="checkbox"/></td>');
														
														 * tr.append('<td class="smalllable">
														 * <div class="switch"> \
														 * <input type="radio"
														 * class="switch-input
														 * areaRadio"
														 * name="active'+obj.areaCode+'"
														 * value="off" id="off1" >\
														 * <label for="off1"
														 * class="switch-label
														 * switch-label-off"></label> \
														 * <input type="radio"
														 * class="switch-input
														 * areaRadio"
														 * name="active'+obj.areaCode+'"
														 * value="on" id="on1"
														 * checked>\ <label
														 * for="on1"
														 * class="switch-label
														 * switch-label-on"></label>
														 * <span
														 * class="switch-selection">\
														 * </span> </div></td></tr>');
														 
														if(idx==0){
															$("#mt_areaTable1 tbody")
															.append(tr);	
														}
														else {
															if(idx%2==1){
																$("#mt_areaTable2 tbody")
																.append(tr);
															}else{
																$("#mt_areaTable1 tbody")
																.append(tr);
															}
														}
													});
								},
								error : function(xhr, ajaxOptions, throwError) {
									alert("Please Try Again");
								}
							});*/
							// End get areas
							 
							//Select current year in the dropdown and get active areas for that particular year
							$("select#areaYear option[value = '"+ year+ "']").attr('selected', 'selected');
							getAreasForSelectedYear(year);
							
							
							
							
							
							$("#sendMailTracking").click(function() {
								
								var amountOfRowsTracking = $("#trackingtable  tbody  tr").filter(':has(:checkbox:checked)').length;
							//	alert("amountOfRowsTracking is"+amountOfRowsTracking);
								if(amountOfRowsTracking<1){
									alert("No Respondent selected, atleast one respondent has to be selected");
								}
								else{
									
									var sendReminderMail;
									sendReminderMail = confirm("Are you sure you want to send a reminder mail to the respondents?");
									if (sendReminderMail == false) {
									    alert("No Reminder Mails sent!");
									} else {
									//	alert("You pressed Yes!");
										
			//++++++++++++++++++++++++++++++++SENDING REMINDER MAIL TO THE EMPLOYEES++++++++++++++++++++++++++++++++
										
										var dataForTracking= getEmployees();
									//	alert(dataForTracking);
										
										$.ajax({
											type:"POST",
											url:"mailToEmployees",
											async:true,
											data:{
											dataForTracking: dataForTracking,
											mailType:"trackingReminder",
											year:year
											},
											success:function(dataFromServer){
												alert("Success");
												
											},
											error:function(){
												alert("Please Try Again");
											}
										});
									}
									
								}
								
								
							});
							
							$('#trackingDropDown').change(function() {
															
								scrolltracking();
								//alert("empTracking"+empTracking);
								
								if(empTracking!=null && empTracking !=""){ 
								var selectedStatus = this.value;
								$("#trackingtable tbody").empty();
								if(selectedStatus=="all"){
									populateTrackingTableForNotStarted(empTracking);
									populateTrackingTableForInProgress(empTracking);
									populateTrackingTableForCompleted(empTracking);
								}
								else if(selectedStatus=="notStarted"){
									$("#trackingtable tbody").empty();
									populateTrackingTableForNotStarted(empTracking);
									
								}else if(selectedStatus=="progress"){
									$("#trackingtable tbody").empty();
									populateTrackingTableForInProgress(empTracking);
									
								}
								else if(selectedStatus=="completed"){
									$("#trackingtable tbody").empty();
									populateTrackingTableForCompleted(empTracking);
									
								}
								}else{
									
									norecordfound("#trackingtable tbody");
								}
							});
								

					$("#addAreaSubmit").click(function() {
						// alert("Add area submit is clicked");
						var area = $("#areaDesc").val();
						if(jQuery.trim($("#areaDesc").val()).length>0){
//							alert(area);
							debugger;
							$.ajax({
								type : "POST",
								url : "addArea",
								async : false,
								data : {
									area : area
								},
								success : function(response) {
									if(response=200){
									alert("area added ");
									location.reload();
									}
									else{
										alert(response);
									}
									//setarea();
									/*$("#AddArea").hide();
								//	$("#overlayForArea").hide();
									$("#areaDesc").val("");
									var selectedYear = $('#areaYear').val();
									if (selectedYear == '0') {
										getAreas();
									} else {
										getAreasForSelectedYear(selectedYear);
									}*/

								},
								error : function(xhr, ajaxOptions, throwError) {
									alert("Please Try Again");
								}
							});
							
						}
						else{
							alert("Please enter a valid Area");
						}
					
					});
					//edit area
					$(".editAreaBtn").click(function(){
						var areaCode= $(this).parents(".smalllable").find(".areaCode").val();
						var areaDesc= $(this).parents(".smalllable").find(".areaDesc").val();
						
						$("#EditArea").find(".areaCode").val(areaCode);
						$("#EditArea").find(".areaDesc").val(areaDesc);
						$("#EditArea").modal();
					})
					
					$("#updateAreaSubmit").click(function() {
						// alert("Add area submit is clicked");
						var areaCode = $(this).parents("#EditArea").find(".areaCode").val();
						var area = $(this).parents("#EditArea").find(".areaDesc").val();
						if(jQuery.trim(area).length>0){
//							alert(area);
							debugger;
							$.ajax({
								type : "POST",
								url : "editArea",
								async : false,
								data : {
									area : area,
									areaCode : areaCode
								},
								success : function(response) {
									if(response=200){
									alert("area updated");
									location.reload();
									}
									else{
										alert(response);
									}
									/*//setarea();
								//	$("#overlayForArea").hide();
									$("#areaDesc").val("");
									var selectedYear = $('#areaYear').val();
									if (selectedYear == '0') {
										getAreas();
									} else {
										getAreasForSelectedYear(selectedYear);
									}*/

								},
								error : function(xhr, ajaxOptions, throwError) {
									alert("Please Try Again");
								}
							});
							
						}
						else{
							alert("Please enter a valid Area");
						}
					
					});
					
					//delete area 
					$(".deleteAreaBtn").click(function(){
						var areaCode= $(this).parents(".smalllable").find(".areaCode").val();
						$("#deleteAreaModal").find(".areaCode").val(areaCode);
						$("#deleteAreaModal").modal();
					})
					
					$("#delAreaBtn").click(function(){
						var areaCode = $(this).parents("#deleteAreaModal").find(".areaCode").val();
						
						$.ajax({
							type : "POST",
							url : "deleteMTArea",
							async : false,
							data : {
								areaCode : areaCode
							},
							success : function(response) {
								if(response==200){
								alert("area deleted");
								location.reload();
								}
								else{
									alert(response);
									location.reload();
								}
								/*//setarea();
							//	$("#overlayForArea").hide();
								$("#areaDesc").val("");
								var selectedYear = $('#areaYear').val();
								if (selectedYear == '0') {
									getAreas();
								} else {
									getAreasForSelectedYear(selectedYear);
								}*/

							},
							error : function(xhr, ajaxOptions, throwError) {
								alert("Please Try Again");
							}
						});
					})

					// Year onchange for area start

					$('#areaYear').change(function() {
						var selectedYear = this.value;

						if (selectedYear == '0') {
							getAreas();
						} else {
							getAreasForSelectedYear(selectedYear);
						}
					});
					
			/*	$("#frequency").onChange(function(){
					
				});*/
					
					
					/*$("#areaCheck").click( function(){
						   if( $(this).is(':checked') ) {
							   alert("checked");
						   }
						   else{
							   alert("unchecked");
						   }
						});*/
				//	alert("calling Report Controller");
					
						}	
						
						//Get List of Question start
						
						
							function callgetquestion(){
								
								//alert("in getquestion .js");
								/*$('#Employee').hide();	
								$('#Question').show();
								$('#Config').hide();
								$('#Tracking').hide();
								$('#Report').hide();
								$('#Area').hide();*/
								
								$('#getQuestYears option').filter(function() { return $.trim( $(this).text() ) == currYear; }).attr('selected',true);
								getQuestByYear();
								}
						$( "#questTable tbody " ).on("click",".editQuest" ,function() {
						var questId=$(this).attr("id");
						var questType=  $(this).closest('tr').find('td:eq(2)').text();
						var questDesc=  $(this).closest('tr').find('td:eq(1)').text();
						var questArea=  $(this).closest('tr').find('td:eq(0)').text();
						var questYear=  $(this).closest('tr').find('td:eq(3)').text();
						if(questId.length!=0){
							questId=questId.split("_")[1];
						}
						if(questType=="Rating with Subquestions")
						{
							getSubQuestionPopulated(questId);
							}
						else
						{
							$(".edittext").remove();
							$(".remove_field").remove();
							 $('#subquesteditId').val("");
							}	
						$("form#editQuestForm #editQuestId").val(questId);
						$("#editQuestText").text(questDesc);
						$('form#editQuestForm #editQuestType option').filter(function() { return $.trim( $(this).text() ) == questType; }).attr('selected',true);
						$('form#editQuestForm #editQuestArea option').filter(function() { return $.trim( $(this).text() ) == questArea; }).attr('selected',true);
						$('form#editQuestForm #editQuestYear option').filter(function() { return $.trim( $(this).text() ) == questYear; }).attr('selected',true);
						});

						$( "#getQuestYears" ).on("change", function() {
							var questYear=$( "#getQuestYears" ).text();
							/*if(parseInt(currYear)>parseInt(questYear))
								$("questTable tbody td.que4").attr("disabled","disabled");
								//$("#questTable").children(":input, a").attr("disabled", "disabled");
								//$('td').find(':input').prop("disabled", true);
							*/
							getQuestByYear();
						});



						$( "#getQuestArea" ).on("change", function() {
							getQuestByYearArea();
						});

							/*	$("#submitNewQn").on("click", function() {
									var trackingData = $('#questTable').tableToJSON();  
									alert(trackingData);
									
								 trackingData=$.each(trackingData,function(k, v) {
											       		this.areaDesc = this.Area;
											       		delete this.Area;	
											       		this.questionDesc = this.Question_Description;
											       		delete this.Question_Description;
											        	this.questionType = this.Question_Type;
											       		delete this.Question_Type;
											       		this.year = this.Year;
											       		delete this.Year;
											});	 
								var url="insertNewQuests";
								var jsonDataToServer=trackingData;
								var questionsList=sendDataQuest(jsonDataToServer,url);	
									});
						*/

						var max_fields      = 20; //maximum input boxes allowed
						var wrapper         = $(".input_fields_wrap1"); //Fields wrapper
						var add_button      = $(".edit_field_button"); //Add button ID
						var k=0;
						 //initlal text box count
						$(add_button).click(function(e){ //on add input button click
						    e.preventDefault();
						    if(x < max_fields){ //max input box allowed
						        x++; //text box increment
						        k=x-1;
						        $(wrapper).append('<div class="clearfix"></div><div class="flefttext"> <input type="text" class="form-control feld edittext" name="subquest['+k+'].subQuestDesc"/><button type="button" class="remove_field btn add-more btnad"><span class="glyphicon glyphicon-minus"></span></button></div><div class="clearfix"></div>'); //add input box
						    }
						});

						$(wrapper).on("click",".remove_field", function(e){ //user click on remove text
						    e.preventDefault(); $(this).parent('div').remove(); x--;
						});


						//Get List of Question end
						
						//Get List of Employees with their status for tracking
						
					 	function scrolltracking(){	
					 		debugger;
					 		//alert("");
					 	$.ajax({
							type:"POST",
							url:"getStatuses",
							async:false,
							data:{
							year: year	
							},
							success:function(dataFromServer){
								//alert("Success");
								//alert("Data from server is "+dataFromServer);
								$("#trackingtable tbody").empty();
								empTracking = jQuery.parseJSON(dataFromServer);
								if(empTracking!=null && empTracking !=""){ 
								calculateCount(empTracking);
								populateTrackingTableForNotStarted(empTracking);
								populateTrackingTableForInProgress(empTracking);
								populateTrackingTableForCompleted(empTracking);
								}else{
									
									norecordfound("#trackingtable tbody");
								}
								
							},
							error:function(){
								alert("Please Try Again");
							}
						});
						}
					
//Get List of Surveys Start
					function scrollsurvey(){	
						
						//alert("scrollsurvey");	
						
						$.ajax({
							type:"POST",
							url: "getSurveys",
							async:false,
							data:{
							},
							success: function(response){
								if(response!="no_survey"){
									//alert(response);
									$("#surveyListTable tbody").empty();
									var surveyList = jQuery.parseJSON(response);
									if(surveyList!=null && surveyList !=""){
									$.each(surveyList, function(idx, obj) {
										
										++idx;
										
										tr = $('<tr/>');
										tr.append("<td class='survey1'>"+idx+"</td>");
										tr.append("<td class='survey1'>"+obj.year+"</td>");
										tr.append("<td class='survey2'>"+obj.startDate+"</td>");
										tr.append("<td class='survey2'>"+obj.endDate+"</td>");
										
										$("#surveyListTable tbody").append(tr);
									});
									}else{
										
										norecordfound("#surveyListTable tbody");
										
									}
								}
								
							},
							error:function(){
								alert("Something went wrong, please try again");
							}
								
							});						
						}
//Get List of Surveys End 
						
					//Get Reports for Yes/No, Open Ended and comments
				
				 function scrollreport(){
						//		$("#yesNoExport").click(function() {
						
					//	alert("in report");
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
								if(yesNoData!=null && yesNoData !=""){
							//	alert(yesNoData);
								populateYesNoTable(yesNoData);
								}else{
									
									norecordfound("#yesNoTable tbody");
								}
								
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
								if(commentData!=null && commentData !=""){
								populateCommentsTable(commentData);
								}else{
									
									norecordfound("#commentsTable tbody");
								}
								
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
								if(openEndedData!=null && openEndedData !=""){
								populateopenEndedTable(openEndedData);
								}else{
									norecordfound("#openEndedTable tbody");
								}
								
								
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
					
				}
					
					
				
					area();
					
					$('#getarea').click(function(){
						area();
						// $('#Config').show();
						// $('#Employee').show();	
						/* $('#Question').hide();
						 $('#Area').hide();
						 $('#Tracking').hide();
						 $('#Report').hide();*/
					});	
					
					$('#getemployee').click(function(){
						// $('#Config').show();
						 //$('#Employee').show();	
						/* $('#Question').hide();
						 $('#Area').hide();
						 $('#Tracking').hide();
						 $('#Report').hide();*/
					});
					
					$('#getquestion').click(function(){
						// $('#Config').show();
						 //$('#Employee').show();	
						// $('#Question').show();
						//$(window).scrollTop($('#Question').offset().top);
						if(questionflag==false)
						 callgetquestion();
						
					});
					$('#getservey').click(function(){
						/* $('#Config').show();
						 $('#Employee').show();	
						 $('#Question').show();*/
						// $('#Area').show();
						debugger;
						//$(document).scrollTo('#Question');
						 if(questionflag==false)
							 callgetquestion();
						 if(surveyflag==false)
						 scrollsurvey();
						 $(window).scrollTop($('#Area').offset().top);
					});
					
					
				$('#gettarcking').click(function(){
					/* $('#Config').show();
					 $('#Employee').show();	
					 $('#Question').show();
					 $('#Area').show();*/
					// $('#Tracking').show();
					debugger
					// $(document).scrollTo('#Tracking');
					if(questionflag==false)
						 callgetquestion();
					 if(surveyflag==false)
					 scrollsurvey();
					 if (trackingflag==false)
					scrolltracking();
					// $(window).scrollTop($('#Tracking').offset().top);
					});
				$('#getreport').click(function(){
					//debugger;
					/* $('#Config').show();
					 $('#Employee').show();	
					 $('#Question').show();
					 $('#Area').show();
					 $('#Tracking').show();*/
					//$('#Report').show();
					//$(window).scrollTop($('#Report').offset().top);
					//$(document).scrollTo('#Report');
					if(questionflag==false)
						 callgetquestion();
					 if(surveyflag==false)
					 scrollsurvey();
					 if (trackingflag==false)
					scrolltracking();
					 if(reportflag==false)
					scrollreport();
				});
					//$( "#getarea" ).trigger("click");
				});

//end of document ready


// Get Areas from Area Master Table start
function getAreas() {

	$
			.ajax({
				type : "POST",
				url : "getAreas",
				async : false,
				data : {},
				success : function(response) {
					// alert(" RECIEVED DATA: "+response);
					var areas = jQuery.parseJSON(response);
					//setarea(areas);
					//setarea(areas,"#getQuestArea");
				//	setarea(areas,"#editQuestArea");
					//alert("area"+areas[1].areaCode);
					$("#mt_areaTable1 tbody").empty();
					$("#mt_areaTable2 tbody").empty();
					var tr;
					$
							.each(
									areas,
									function(idx, obj) {
										tr = $('<tr class="tbrder" id = "areatable'
												+ obj.areaCode + '">');
										tr.append('<td class="biglable">'
												+ obj.areaDesc + '</td>');
										/*tr
												.append('<td class="smalllable"> <div class="btn-group" data-toggle="buttons"> \
												<label class="btn btn-default " onChange="areaCaptureYes(this)";>\
												<input type="radio" name="active-'
														+ obj.areaCode
														+ '" class="areaYes" autocomplete="off" value="on" > \
												<b><span class="glyphicon glyphicon-ok fwh"></span></b> </label><label class="btn btn-default active" onChange="areaCaptureNo(this)";> \
												<input type="radio" name="active-'
														+ obj.areaCode
														+ '" class="areaNo" autocomplete="off" value="off" >\
												<b><span class="glyphicon glyphicon-remove fwh"></span></b>\</label></div></td></tr>');*/
												
												tr.append('<td class="smalllable"><input type="checkbox" id="areaCheck" name="active-'+obj.areaCode+'" onChange="areaCapture(this)" class="checkbox"/>' 
														+'<input type="hidden" class="areaCode" value="'+obj.areaCode+'"/>'
														+'<input type="hidden" class="areaDesc" value="'+obj.areaDesc+'"/>'
														+'<button type="button" class="editAreaBtn edit_btn_style">'
														+'<i class="glyphicon glyphicon-pencil edit_icon"></i>'
														+'</button>'
														+'<button type="button" class="deleteAreaBtn delete_btn_style">'
														+'<i class="glyphicon glyphicon-trash delete_icon"></i>'
														+'</button>'
														+'</td>');

									//	$("#mt_areaTable tbody").append(tr);
										
										if(idx==0){
											$("#mt_areaTable1 tbody")
											.append(tr);	
										}
										else {
											if(idx%2==1){
												$("#mt_areaTable2 tbody")
												.append(tr);
											}else{
												$("#mt_areaTable1 tbody")
												.append(tr);
											}
											
										}
									});
				},
				error : function(xhr, ajaxOptions, throwError) {
					alert("Please Try Again");
				}
			});

};
// Get Areas from Area Master Table end

function areaCaptureYes(captured) {

	var code = captured.children[0].name;
	code = code.split("-")[1];
	// alert(code);
	var selectedYear = $('#areaYear').val();
	if (selectedYear != '0') {
		// alert("Perform insert/update task for" + code);

		$.ajax({
			type : "POST",
			url : "mapArea",
			async : false,
			data : {
				year : selectedYear,
				code : code
			},
			success : function(data) {
				// alert(data);
				getAreasForSelectedYear(selectedYear);
			},
			error : function(xhr, ajaxOptions, throwError) {
				alert("Please Try Again");
			}
		});
	}
}

function areaCaptureNo(captured) {

	var code = captured.children[0].name;
	code = code.split("-")[1];
	// alert(code);
	var selectedYear = $('#areaYear').val();
	if (selectedYear != '0') {

		// alert("Perform delete task for" + code);
		$.ajax({
			type : "POST",
			url : "deleteArea",
			async : false,
			data : {
				year : selectedYear,
				code : code
			},
			success : function(data) {
				// alert(data);
				getAreasForSelectedYear(selectedYear);
			},
			error : function(xhr, ajaxOptions, throwError) {
				alert("Please Try Again");
			}
		});
	}
}

function areaCapture(captured) {
	//alert("Into Area Capture"+captured);
	
if(captured.checked){
	
	var code = captured.name;
	code = code.split("-")[1];
	// alert(code);
	var selectedYear = $('#areaYear').val();
	if (selectedYear != '0') {
		// alert("Perform insert/update task for" + code);

		$.ajax({
			type : "POST",
			url : "mapArea",
			async : false,
			data : {
				year : selectedYear,
				code : code
			},
			success : function(data) {
				// alert(data);
				getAreasForSelectedYear(selectedYear);
			},
			error : function(xhr, ajaxOptions, throwError) {
				alert("Please Try Again");
			}
		});
	}
	
	
	
}else{
	var code = captured.name;
	code = code.split("-")[1];
	// alert(code);
	var selectedYear = $('#areaYear').val();
	if (selectedYear != '0') {

		// alert("Perform delete task for" + code);
		$.ajax({
			type : "POST",
			url : "deleteArea",
			async : false,
			data : {
				year : selectedYear,
				code : code
			},
			success : function(data) {
				// alert(data);
				getAreasForSelectedYear(selectedYear);
			},
			error : function(xhr, ajaxOptions, throwError) {
				alert("Please Try Again");
			}
		});
	}
	
}

}
//get updated area list start 

function setarea(data){
	$('.areaload').empty();
	$('.areaload').append($("<option></option>").val("0").html("Select Area"));
	$.each(data, function(idx, obj) {
		
		 $('.areaload').append($("<option></option>").val(obj.areaCode).html(obj.areaDesc));
		
	});
	
	
}

//get updated area list end


// Get Areas for the year that is selected start
function getAreasForSelectedYear(year) {

	// $(".areaRadio").removeAttr("checked");
	getAreas();
	var selectedYear = year; // get the value selected
	// alert("Selected Year is "+selectedYear);
	var svrUrl = "getAreasForYear";
	$.ajax({
		type : "POST",
		url : svrUrl,
		async : false,
		data : {
			selectedYear : selectedYear
		},
		success : function(data) {
			// alert("Areas for "+selectedYear +" is "+data);
			//alert("DATA FOR AREA SELECT IS"+data)
			var areaYearList = jQuery.parseJSON(data);
			//var isObjectEmpty = jQuery.isEmptyObject(areaYearList);//check if the object is empty
			//alert("Object is Empty: "+isObjectEmpty);
			
			//set the areas active in the area table
			//if(!isObjectEmpty){
			setarea(areaYearList);
				$.each(areaYearList, function(idx, obj) {
					// alert(obj.areaCode);
					var id = "areatable" + obj.areaCode;

					var x = $("#" + id + " td:nth-child(2)");
					var text = $("#" + id + " td:nth-child(1)");
					text.addClass("textbold");
					
					var name = "active-" + obj.areaCode;
					
					var y = x.find("input[name=" + name + "]");
				//	var z = x.find("input[name=" + name + "][value='off']");
					y.attr("checked", "true");
					//y.parent("label").addClass("active");
				//	z.parent("label").removeClass("active");
				});
		//	} //end if
			
		},
		error : function(xhr, ajaxOptions, throwError) {
			alert("Please Try Again");
		}
	});
};

function populateTrackingTableForNotStarted(trackingData) {
	var tr;

	$.each(trackingData, function(idx, obj) {
		
		if(obj.status=="notStarted"){
			tr = $('<tr class=brorange data-attribute="'+obj.sapFlag+'"/>');
			tr.append("<td class='trck1'><input class='checkTracking' type='checkbox' checked></td>");
			tr.append("<td class='trck2'>"+obj.empId+"</td>");
			tr.append("<td class='trck3'>"+obj.fName+" "+ obj.lName+"</td>");
			tr.append("<td class='trck4'>"+obj.emailId+"</td>");
			tr.append("<td class='trck5'>Not Started</td>");

			$("#trackingtable tbody").append(tr);
		}
		

	});
	
}
function norecordfound(div){
	var tr;
	
	
	tr = $('<tr class=brorange />');
	tr.append("<td colspan ='5'>No Record Found</td>");
	$(div).append(tr);

	
	
}

function populateTrackingTableForInProgress(trackingData) {
	var tr;
debugger;
	$.each(trackingData, function(idx, obj) {
		
		if(obj.status=="progress"){
			tr = $('<tr class=brorange data-attribute="'+obj.sapFlag+'"/>');
			tr.append("<td class='trck1'><input class='checkTracking' type='checkbox' checked></td>");
			tr.append("<td class='trck2'>"+obj.empId+"</td>");
			tr.append("<td class='trck3'>"+obj.fName+" "+ obj.lName+"</td>");
			tr.append("<td class='trck4'>"+obj.emailId+"</td>");
			tr.append("<td class='trck5'>In Progress</td>");

			$("#trackingtable tbody").append(tr);
		}
		

	});
	
}

function populateTrackingTableForCompleted(trackingData) {
	var tr;

	$.each(trackingData, function(idx, obj) {
		
		if(obj.status=="completed"){
			tr = $('<tr class=brorange data-attribute="'+obj.sapFlag+'"/>');
			tr.append("<td class='trck1'><input class='checkTracking' type='checkbox' disabled></td>");
			tr.append("<td class='trck2'>"+obj.empId+"</td>");
			tr.append("<td class='trck3'>"+obj.fName+" "+ obj.lName+"</td>");
			tr.append("<td class='trck4'>"+obj.emailId+"</td>");
			tr.append("<td class='trck5'>Completed</td>");

			$("#trackingtable tbody").append(tr);
		}

	});
}

function calculateCount(trackingData){
	
	var inProgressCount=0;
	var completedCount=0;
	var notStartedCount=0;
	var totalCount=0;
	$('#trackingCount').empty();
	$.each(trackingData, function(idx, obj) {

				if (obj.status == "progress") {
					inProgressCount++;
					totalCount++;

					} else if (obj.status == "completed") {
						completedCount++;
						totalCount++;

						} else if (obj.status == "notStarted") {
							notStartedCount++;
							totalCount++;
							}

	});
	var progressAlert="TOTAL EMPLOYEES: "+totalCount+",  IN PROGRESS: "+inProgressCount+",  COMPLETED: "+completedCount +",  NOT STARTED: "+notStartedCount;
	//var progressAlert="TOTAL EMPLOYEES "+totalCount+" IN PROGRESS= "+inProgressCount+" COMPLETED= "+completedCount +" NOT STARTED= "+notStartedCount;
//	alert(""+progressAlert);
	
	var div = document.getElementById('trackingCount');
	div.innerHTML = div.innerHTML + progressAlert;

};

function getEmployees(){
	//alert("hi");
	//$("#trackingtable").tableToJSON();
	
	var count=0;
	var temp=0;
	debugger;
	var EmployeeArr=[];
	$('#trackingtable tbody tr').filter(':has(:checkbox:checked)').each(function() { //changed code
		EmployeeArr[temp]=new Array(5);
		EmployeeArr[temp][0]="empId'" +":'"+ $(this).find("td").eq(1).text()+"";
		EmployeeArr[temp][1]=  "fullName'" +":'"+ $(this).find("td").eq(2).text()+"";
		EmployeeArr[temp][2]=  "emailId'" +":'"+ $(this).find("td").eq(3).text()+"";
		EmployeeArr[temp][3]=  "status'" +":'"+ $(this).find("td").eq(4).text()+"";
	//	alert($(this).attr("data-attribute"));
		EmployeeArr[temp][4]=  "sapFlag'" +":'"+ $(this).attr("data-attribute");
		
		
		
temp++;
count++;
	});
	var jsonDataTracking=JSON.stringify(EmployeeArr); 
	return jsonDataTracking;
//	EmployeeArr=[];
	
}



function checkUncheckAll(captured){
	//alert("Hi");
	if (captured.checked){
		$('.checkTracking').each(function(){
			this.checked=true;
		});
	}
	else{
		$('.checkTracking').each(function(){
			this.checked=false;
		});
	}
}

//Question Methods Start
function insertQuest(){
	var jsonDataToServer='';
	var jsonDataFromServer='';
	jsonDataToServer = $('#addQuestionForm').serialize();
	var validObject=$('#addQuestionForm').serializeJSON();
	var yearId=$("#getQuestYears").val();
	var areaId=$("#getQuestArea").val();

	if (validateQuestion(validObject)) 
	{
		var url="questioninsert";
		jsonDataFromServer=sendDataQuest(jsonDataToServer,url);
		
		$("#addQuestionForm").trigger('reset');
		if(yearId.length>0 && areaId!=0)
			getQuestByYearArea();
		else
			if(yearId.length>0)
				getQuestByYear();
     		else
	        	populateQuestions(jsonDataFromServer);
	}
		
}

function setQuestId(id){
	
	$('.delQuestYes').attr('id',id);
}

function deleteQuest(questId){
	var jsonDataFromServer='';
	var url="questiondelete";
	$.ajax({
		type : "POST",
		url : url,
		async : false,
		data : "questionId=" + questId,
		success : function(data) {
			jsonDataFromServer=data;
			//alert(jsonDataFromServer);
			swal("Successfully Deleted");
			$('#deleteQuestMod').modal('hide');
		},
		error : function(xhr, ajaxOptions, throwError) {
			alert("Please Try Again");
		}
		
	});
	var yearId=$("#getQuestYears").val();
	var areaId=$("#getQuestArea").val();
	if(yearId.length>0 && areaId!=0)
		getQuestByYearArea();
	else
		if(yearId.length>0)
			getQuestByYear();
 		else
        	populateQuestions(jsonDataFromServer);
}

function updateQuest(){
	var jsonDataToServer='';
	var jsonDataFromServer='';
	jsonDataToServer = $('#editQuestForm').serialize();
	var validObject=$('#editQuestForm').serializeJSON();
	if (validateQuestion(validObject)) 
	{
		var url="questionupdate";
		var questionsList=sendDataQuest(jsonDataToServer,url);
		var yearId=$("#getQuestYears").val();
		var areaId=$("#getQuestArea").val();
		
		if(yearId.length>0 && areaId!=0)
			getQuestByYearArea();
		else
			if(yearId.length>0)
				getQuestByYear();
     		else
	        	populateQuestions(questionsList);
		
	}
	$("#editQuestForm").trigger('reset');
}

function sendDataQuest(jsonDataToServer,url){
	$("#addQuestion").modal('hide');
	var jsonDataFromServer="";
	$.ajax({
		type : "POST",
		url : url,
		async : false,
		data :  jsonDataToServer,
		success : function(data) {
			if (data != null){
				swal("Successfully Edited");
			}
			jsonDataFromServer=data;
		},
		error : function(xhr, ajaxOptions, throwError) {
			alert("Please Try Again");
		}
		
	});
	return jsonDataFromServer;
}

function getQuestByYear(){
	
	//alert("getQuestByYear in question .js");
	var yearId=$("#getQuestYears").val();
	$("#getQuestArea").val("0");
	var jsonDataToServer='{"yearMap.pkYear":"'+yearId+'"}';
	var url="questionListbyYear";
	//var questionsList=sendDataQuest(jsonDataToServer,url);
	var yeartext=$("#getQuestYears option[value='"+yearId+"']").text();
	$.ajax({
		type : "POST",
		url : url,
		async : false,
		data : "yearMap.pkYear=" + yearId,
		success : function(data) {
			
			jsonDataFromServer=data;
			//alert(jsonDataFromServer);
		},
		error : function(xhr, ajaxOptions, throwError) {
			alert("Please Try Again");
		}
		
	});
	if(jsonDataFromServer.length>0)
	populateQuestions(jsonDataFromServer);
	else{
		
		if(parseInt(yeartext)>parseInt(currYear)){
			
		var x=confirm("NO QUESTIONS HAVE BEEN UPLOADED..DO YOU WISH TO USE THE PREVIOUS YEAR QUESTIONS?");
		 if(x){
			 var yeartext=$("#getQuestYears option[value='"+yearId+"']").text();
				var prevYear=parseInt(yeartext-1);
				var pkYear=$('#getQuestYears option').filter(function() { return $.trim( $(this).text() ) == prevYear; }).val();
			getPrevsQuest(pkYear,yeartext);
		  }
	}else{
		$("#questTable tbody").empty();
	//	alert("No data to display");
		
	}
		}
	
}


function populateQuestions(jsonDataFromServer)
{
	$("#questTable tbody").empty();
	var tr;
	 $.each(jsonDataFromServer,function(idx, obj) {
		 tr = $('<tr/>');
		 tr.append('<td class="que1"><input type="hidden" name="questionList['+idx+'].areaCode" value="'+obj.areaCode+'">'+obj.questionArea +'</td>');
	     tr.append('<td class="que2"><input type="hidden" name="questionList['+idx+'].questionDesc" value="'+obj.questionDesc+'">'+obj.questionDesc +'</td>');
		 tr.append('<td class="que3"><input type="hidden" name="questionList['+idx+'].questionType" value="'+obj.pkQuestType+'">'+obj.questionType +'</td>');
		 tr.append('<td class="que5"><input type="hidden" name="questionList['+idx+'].year" value="'+obj.pkYear+'">'+obj.year+'</td>');
		 tr.append('<td class="que4" id="editQ_'+obj.questionId+'"><a  data-target="#editQuest" data-title="Edit" data-toggle="modal" class="edit editnobg md-trigger col-md-6 col-sm-6 hand editQuest" id="editQ_'+obj.questionId+'"><i class="glyphicon glyphicon-pencil edit_icon a_editbg_icon"></i></a><a data-target="#deleteQuestMod" data-title="Delete" data-toggle="modal" class="delete deletenobg col-md-6 col-sm-6 md-trigger hand" onClick="setQuestId('+obj.questionId+')"><i class="glyphicon glyphicon-trash delete_icon a_deletebg_icon"></i></a></td>');
			$("#questTable tbody").append(tr);
			
	 
	 });

}


function getQuestByYearArea(){
	
	//alert("getQuestByYearArea in guestion js");
	var yearId=$("#getQuestYears").val();
	var areadId=$("#getQuestArea").val();
	if(areadId=="0"){
		getQuestByYear();
	}
	else{
	var url="questionListbyAreaYear";
	$.ajax({
		type : "POST",
		url : url,
		async : false,
		data : {"yearMap.pkYear" : yearId ,
			      "questionArea.areaCode":  areadId },
		success : function(data) {
			jsonDataFromServer=data;
			//alert(jsonDataFromServer);
		},
		error : function(xhr, ajaxOptions, throwError) {
			alert("Please Try Again");
		}
		
	});
	populateQuestions(jsonDataFromServer);
	}
}

	function getPrevsQuest(yearId,yeartext){
		var url="questionListbyYear";
		var jsonDataFromServer='';
		var pkYearId=$("#getQuestYears").val();

		$.ajax({
			type : "POST",
			url : url,
			async : false,
			data : "yearMap.pkYear=" + yearId,
			success : function(data) {
				jsonDataFromServer=data;
				//alert(jsonDataFromServer);
			},
			error : function(xhr, ajaxOptions, throwError) {
				alert("Please Try Again");
			}
		});
		if(jsonDataFromServer.length>0){
			$("#questTable tbody").empty();
			var tr;
			 $.each(jsonDataFromServer,function(idx, obj) {
				 tr = $('<tr/>');
				 tr.append('<td class="que1"><input type="hidden" name="questionList['+idx+'].areaCode" value="'+obj.areaCode+'">'+obj.questionArea +'</td>');
			     tr.append('<td class="que2"><input type="hidden" name="questionList['+idx+'].questionDesc" value="'+obj.questionDesc+'">'+obj.questionDesc +'</td>');
				 tr.append('<td class="que3"><input type="hidden" name="questionList['+idx+'].questionType" value="'+obj.pkQuestType+'">'+obj.questionType +'</td>');
				 tr.append('<td class="que5"><input type="hidden" name="questionList['+idx+'].year" value="'+pkYearId+'">'+yeartext +'</td>');
				 tr.append('<td class="que4"><a  data-target="#editQuest" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand editQuest"></a><a data-target="#deleteQuestMod" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand" onClick="setQuestId('+obj.questionId+')"> </a></td>');
					$("#questTable tbody").append(tr);
					$("#submitNewQn").css({display: "block"});	
				//$("div#submitNewQn").show();
			 });
	}
		else
			alert("No Data found. Kindly upload the questions for the selected year");

}

function clearFields(){
	$("#addQuestionForm").trigger('reset');
}

function addNewQuests()
{

	var url="insertNewQuests";
	var jsonDataFromServer='';
	var jsonDataToServer=$('#questTableForm').serialize();
	$.ajax({
		type : "POST",
		url : url,
		async : false,
		data : jsonDataToServer,
		success : function(data) {
			jsonDataFromServer=data;
			//alert(jsonDataFromServer);
		},
		error : function(xhr, ajaxOptions, throwError) {
			alert("Please Try Again");
		}
	});
	alert("Questions Added");
	$("#submitNewQn").css({display: "none"});
	populateQuestions(jsonDataFromServer);
	return false;	
}


function getSubQuestionPopulated(questId)
{
	
	var url="questionDetailByID";
	var jsonDataFromServer='';


	$.ajax({
		type : "POST",
		url : url,
		async : false,
		data : "questId=" + questId,
		success : function(data) {
			jsonDataFromServer=data;
			
		},
		error : function(xhr, ajaxOptions, throwError) {
			alert("Please Try Again");
		}
	});
x=jsonDataFromServer.length;
	if(jsonDataFromServer.length>0){
		var k=0;
		
		$(".edittext").remove();
		$(".remove_field").remove();
		var wrapper         = $(".input_fields_wrap1");
		
	/*	 $(wrapper).append('<div class="form-group"> <label class="col-sm-4 nopad">Edit Sub Question</label>            <div class="clearfix"></div>'); //add input box
		 $(wrapper).append('<div><div class="col-sm-11"><input type="text" class="form-control feld" id="subquesteditId" name="subquest[0].subQuestDesc"></div></div>'); //add input box
		 $(wrapper).append('<div class="col-sm-1"><button id="edit_field_button" class="edit_field_button btn add-more"><span class="glyphicon glyphicon-plus"></span></button></div></div>');
	*/	 $.each(jsonDataFromServer,function(idx, obj) {
			 if(idx==0)
				 {
				 $('#subquesteditId').val(this.subQuestDesc);
				 }
			 else
			 {
				 k=idx;
				 $(wrapper).append('<div class="clearfix"></div><div class="flefttext"> <input type="text" style="margin-left:0px;" class="form-control feld edittext" name="subquest['+k+'].subQuestDesc" value="'+this.subQuestDesc+'"/><button type="button" class="remove_field btn add-more btnad"><span class="glyphicon glyphicon-minus"></span></button></div><div class="clearfix"></div>'); //add input box

			 }	 
			 			});
}
}
//Question Method End

//Report Methods start

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
	        

//Report Methods End

