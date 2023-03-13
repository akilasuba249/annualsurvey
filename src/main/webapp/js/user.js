/**
 * 
 */
var a;
var b;
window.feedbackAutoCount = [];


$(document).ready(function() {
	
	  $('.comment').on('click', function() { // Added by Vibhushi
		  var $this = $(this);
		 var id = $this.attr('id');
		  var areacode_id = id.replace("Comments", '');
	    if ($this.is(':checked')) {    	
	     //$this.parent('.add-comment').find('.ok-btn').show();
	    } else {
	    	// $this.parent('.add-comment').find('.ok-btn').hide();
	    	$this.parent('.add-comment').find('.txtdiv').addClass('d-show');
	    	 $this.parent('.add-comment').find('.Addcomments').val('');
	    	 $('.dvComments'+areacode_id+'Resp').val('');
	    }
	  }); 
	  
	  $(".skipcomment").click(function(){
			if ($(window).width() > 768) {
	            // $(this).parents('.inner_content_div').next('.inner_content_div').addClass('active');
	        		
	        	var pankaj = $('.inner_content_div');
	        	//var scrollli = $('li'); 
	            if ($(this).parents('li').hasClass('last_click')) {

	                $('html, body').animate({
	                    scrollTop: $(this).parents('.inner_content_div').next(pankaj).offset().top - 310
	                }, 1500);
	               
	            } else {
	                $('html, body').animate({
	                    scrollTop: $(this).parents('li').next('li').offset().top - 310
	                }, 1500);
	               

	            }
			}
		})
	  
	  
		
	debugger;
	//$('#update-profile').hide();
	 $('#update-profile').modal('hide');
	//alert('Inside user');
	var completed=$("#comp").text();
	
	var areaslength = $('.survey_heading_div li').length
	
	
	/*Niki -----------------*/
	var count =$('.center_div_MP .survey-qtn-div .inner_content_div').length;
	//alert('Countzzzzz : '+count);

/*	$('.center_div_MP .survey-qtn-div .inner_content_div').each(function(){// id of ul
		   var get_id = $(this).parents('.inner_content_div').attr('data-li');http://localhost:1080/annualsurvey/urlLogin?username=NoDUsBMbOydl7CrY6kX1xqDhAhJ9lnGeVZ6nAAfVp70%3D
			alert('get_id ----------'+get_id);
			
			var parents_length = $(this).parents('.inner_content_div').find('.ans_complete').length;
			alert('parents_length -----------'+parents_length);
			
			var t_count = $('.survey_heading_div li#' + get_id).find('i').text();
			alert('t_count ---------'+t_count);
		})*/
	
  /* $('.inner_content_div').each(function() {
	   
	   var li = $(this).attr('data-li')//get each li in ul
		console.log('xxxxxxxxx:'+li.text())//get text of each li
	   
   });*/
		
	/*Niki -----------------*/	
	
	$("#comp").text("");
	$.sessionTimeout({
	        keepAliveUrl: '',
          //  logoutUrl: 'j_spring_security_logout',
	        redirUrl: 'beforeLogout?session=N',
	        keepAlive:true,
	        warnAfter: 540000,
	        redirAfter: 3600000,
	        countdownMessage: 'Redirecting in {timer} seconds.',
	        countdownBar: true
	    });

		
	$('.radio-custom1').bind('keypress', function (event) {
	   debugger;
		var regex = new RegExp("^[^<>'\"/;`%]*$");		//does not allow < > ' " / ; ` %
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
	});
	
	$('.Addcomments').bind('keypress', function (event) {
		
	    var regex = new RegExp("^[^<>'\"/;`%]*$");		//does not allow < > ' " / ; ` %
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
	});
	
		
	$('.name').bind('keypress', function (event) {
		
	   // var regex = new RegExp("^[^<>'\"/;`%@!#$%^&*()]*$");		//does not allow < > ' " / ; ` %
	    var regex = new RegExp("^[a-zA-Z]+$");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
	});
	
	
	var dt = new Date();
	dt.setFullYear(new Date().getFullYear()-18);

	$("#datepicker3").datepicker({
		changeYear : true,
		changeMonth : true,
		dateFormat : 'yy-mm-dd',
		maxDate : dt,
		/*endDate : dt,*/
		yearRange : "-100:+0",
		onSelect: function(){
			$("#spandob").text($(this).val())
			$(".deletepopup").modal();
		}
	});
	
	$('#buttonClose').click(function()
	{
		window.location.href="login?logout";
	});
	
	var date = $('#datepicker3').datepicker({
		dateFormat : 'dd/mm/yyyy'
	}).val();
	a = $('#pkEmp').val();
	b = $('#surveyId').val();
	f = $('#Employeeid').val();

	$("#otherSector").hide();
	$("#otherBusiness").hide();
	var currentTime = new Date();
	var e = currentTime.getFullYear();
	$("#year").val(e);

	
	populateUserData(f);
	
	populate(a, b);
	
	//console.log('a:'+a+',b:'+b);
	//checkfilledalready(a, b)

	var quesId;
	$(document).on("click", '.radio-custom', function(e) {
		debugger;
		
		//alert('Inside radio-custom>>>>');
		
		//quesId = $(this).children().attr('name');
		quesId = $(this).attr('name');
		
		//alert('quesId>>>'+quesId);
		
		var i;
		var count = 0;
		for (i = 0; i < feedbackAutoCount.length; ++i) {
			if (feedbackAutoCount[i] === quesId) {
				count++;
			}
		}
		if (count === 0) {
			feedbackAutoCount.push(quesId);
		}

		if (feedbackAutoCount.length == 5) {
			
			saveUserResponseAutoSave();
			feedbackAutoCount = [];
		}
		
		
	});
	$(document).on("focusout", '.radio-custom1', function (e) {
		   debugger;
		var count1=0;
		var i;
		for (i = 0; i < feedbackAutoCount.length; ++i) {
			if (feedbackAutoCount[i] === quesId) {
				count1++;
			}
		}
		if (count1 === 0) {
			feedbackAutoCount.push(quesId);
		}
		if (feedbackAutoCount.length == 4) {
			saveUserResponseAutoSave();
			feedbackAutoCount = [];
		}
	});
	//Added By Rakesh kumar for focus issues on mobile START :20/03/2018 
	function zoomDisable() {
        $('head meta[name=viewport]').remove();
        $('head').prepend('<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0" />');
    }
	
	$("input[type=text],input[type=radio], textarea ").on({
        'touchstart': function() {
        	//$(this).focus();
            zoomDisable();
        }
    });  // pankaj 
	//Added By Rakesh kumar for focus issues on mobile END :20/03/2018
});


function chekDob() {	
	var dob=$('#datepicker3').val();
	
	if(dob!=""){
	var formatdob=moment(dob).format('DD-MM-YYYY');
	$('#spandob').text(formatdob);
	$('#delete').modal('show');
	}else{
		saveUser();
	}
	
}


function saveUser() {	
	//$('#delete').modal('show');
	   debugger;
	if (validateDetails('demograpic')) {
		$.post('demograpic', $('#demograpic').serialize(), function(response) {
		$("#delete").modal('hide');
//		alert("Profile Saved Successfully");
		swal("Profile Saved Successfully",'', "success");
		populateUserData(f);
		populate(a, b);
		//$('#update-profile').show();
	});
}

}
function saveUserBeforeSubmit() {	
	//$('#delete').modal('show');
	   debugger;
	if (validateDetails('demograpic')) {
		$.post('demograpic', $('#demograpic').serialize(), function(response) {
		$("#delete").modal('hide');
//		alert("Profile Saved Successfully");
		//swal("Profile Saved Successfully",'', "success");
		populateUserDataBeforeSubmit(f);
		populate(a, b);
	});
}

}

function populate(c, d) {
	debugger;
	//alert('inside populate');
var t=0;
	$.ajax({
		type : "POST",
		url : "getEmpresponse",
		async : false,
		data : {
			surveyId1 : d,
			pkEmp : c
		},
		success : function(data) {
			debugger;
			var areas = [];
			var areaCounter = 0;
			var answerCounter = 0;
			var answeredCount = [];
			var totalQuestion = [];
			var get_id ;
			
			//alert('Niki here');
			$.each(JSON.parse(data), function(idx, obj) {
				
				$('#operation').val('Y');
				
				$('#responsemap').val(obj.responsemapID);
				
				//alert('responsemap >>>'+$('#responsemap').val());
				
				if(obj.comments!="null" && obj.comments!="")
				{	
				$("#dvComments" + obj.areacode + "text").val(obj.comments);
				$('#Comments' + obj.areacode).prop('checked', true);
				
				$("#dvComments" + obj.areacode).show();
				}
				$(".dvComments" + obj.areacode + "Resp").val(obj.comments);

				$('#responsemap').val(obj.responsemapID);
				
				if (obj.status == 'completed') {
					$('#saveUser').attr('disabled', 'disabled');
					$('#submitUser').attr('disabled', 'disabled');
		          t++;		
				}
				
				if (obj.questionType == 'Rate') {
					debugger;
					//alert('inside rate');
					console.log($('#Resp' + obj.questionBankId + 'rad' + obj.answerNo));
					$('#Resp' + obj.questionBankId + 'rad' + obj.answerNo).prop('checked', true);
					// $('.sneha').prop('checked', true);
					//$('#Resp' + obj.questionBankId + 'rad' + obj.answerNo).addClass('checkedval'); //added by nikita 
					$('#Resp' + obj.questionBankId + 'rad' + obj.answerNo).closest('.main-que').addClass('checkedval'); //added by nikita
				//	$('#Resp' + obj.questionBankId + 'rad' + obj.answerNo).closest('.main-que').addClass('vibhushi'); 
				    $('#Resp' + obj.questionBankId + 'rad' + obj.answerNo).closest('.main-que').addClass('ans_completed'); 
					$('#Resp' + obj.questionBankId + 'rad' + obj.answerNo).closest('.main-que').addClass('ans_complete'); 
				
				} else if (obj.questionType == 'OpenEnded') {
					
				$('#Resp' + obj.questionBankId + 'text').val(obj.answerText);
					$('#Resp' + obj.questionBankId + 'text').prop('checked', true);   //added by nikita
					
					if(obj.answerText.length >0 ){
						$('#Resp' + obj.questionBankId + 'text').closest('.main-que').addClass('checkedval'); //added by nikita
						$('#Resp' + obj.questionBankId + 'text').closest('.main-que').addClass('ans_completed');
						$('#Resp' + obj.questionBankId + 'text').closest('.main-que').addClass('ans_complete');
					}
					
					//$('#Resp' + obj.questionBankId + 'rad' + obj.answerNo).addClass('ans_completed'); //added by nikita
				} else if (obj.questionType == 'Subquestion') {
					var array = (this.subquestionmap).split(",");
					var k = 0;
					$.each(array, function(i) {

						$('#Resp' + array[k] + 'subquest' + array[k + 1]).prop('checked', true);
						//$('#Resp' + array[k] + 'subquest' + array[k + 1]).closest('li').addClass('checkedval');  //added by nikita
						$('#Resp' + array[k] + 'subquest' + array[k + 1]).parents('.sub-que').addClass('sub_ans_complete'); 
						$('#Resp' + array[k] + 'subquest' + array[k + 1]).closest('.main-que').addClass('checkedval');  //added by nikita
						$('#Resp' + array[k] + 'subquest' + array[k + 1]).closest('.main-que').addClass('ans_completed');
						$('#Resp' + array[k] + 'subquest' + array[k + 1]).closest('.main-que').addClass('ans_complete');

						k = k + 1;
						if (array[k] == undefined) {
							return false;
						}
					});
				}
				
				//addClasstoAnswer(obj,areas,areaCounter,answerCounter,answeredCount,totalQuestion,get_id);   //Added by nikita
		
				var position = areas.indexOf(obj.areacode);    //Checking whether area is already present, if present get index				
				if (position == -1){
					areas[areaCounter] =  obj.areacode;
					position = areaCounter;
					answeredCount[position] = 0;
					areaCounter++;
				}
				
				if ( totalQuestion[position] == undefined)
					totalQuestion[position] = 1;
				else
					totalQuestion[position] = totalQuestion[position] + 1;
				
				
				if( obj.questionType == 'Rate' && obj.answerNo > 0 ){   //Question type = Rating
					
					if ( answeredCount[position] == undefined)
					{
					answeredCount[position] = 1;
					get_id = 'li_'+(answeredCount[position]);    
					console.log($('.survey_heading_div li#'+get_id));
					$('.survey_heading_div li#'+get_id).addClass('answered');
					
					}else{
						answeredCount[position] = answeredCount[position] + 1;
						get_id = 'li_'+(answeredCount[position]);    
						console.log($('.survey_heading_div li#'+get_id));
							$('.survey_heading_div li#'+get_id).addClass('answered');
					}
						
				}else if(obj.questionType != null && obj.questionType == 'OpenEnded' && obj.answerText != '' && obj.answerText.length>0){
						if ( answeredCount[position] == undefined)
						{
						answeredCount[position] = 1;
						get_id = 'li_'+(answeredCount[position]);    
						console.log($('.survey_heading_div li#'+get_id));
						$('.survey_heading_div li#'+get_id).addClass('answered');
						}else{
							answeredCount[position] = answeredCount[position] + 1;
							get_id = 'li_'+(answeredCount[position]);    
							console.log($('.survey_heading_div li#'+get_id));
								$('.survey_heading_div li#'+get_id).addClass('answered');	
						}
				}else if(obj.questionType == 'Subquestion'&& obj.subquestionmap.split(',')[1]>0 ){
					if ( answeredCount[position] == undefined)
					{
					answeredCount[position] = 1;
					get_id = 'li_'+(answeredCount[position]);    
					console.log($('.survey_heading_div li#'+get_id));
					$('.survey_heading_div li#'+get_id).addClass('answered');
					}else{
						answeredCount[position] = answeredCount[position] + 1;
						get_id = 'li_'+(answeredCount[position]);    
						console.log($('.survey_heading_div li#'+get_id));
							$('.survey_heading_div li#'+get_id).addClass('answered');	
					}
				}
				
				//Niki end
				
			});
			//console.log('areas');
			//console.log(areas);
			//console.log('answeredCount');
			//console.log(answeredCount);
			//console.log('totalQuestion');
			//console.log(totalQuestion);
			//Auto Scrolling start
			var areaslength1 = $('.survey_heading_div li').length;
			//console.log('Niki >>>Length >>>'+areaslength1);

			
			for (i = 1; i <= areaslength1; ++i) {
				var id = $("#content_"+i);
				if(id.find('.question_ul_inner').find('li:last-child').find('ul').length == 0){
					id.find('.question_ul_inner').find('li').last().addClass('last_click');	
				}
				
				if(id.find('.question-type-3').find('li').find('ul').length != 0){
                    id.find('.question_ul_inner').find('li').addClass('sub-que');   
                }
				
				//id.find('.question-type-3').find('li').last().addClass('last_click');
			}
			
			for (i = 0; i < areas.length; ++i) {
				
				debugger;
				/*alert(answeredCount[i]);
				alert(totalQuestion[i]);*/
				//console.log('Niki here');
				
					get_id = 'li_'+(i+1);
					//console.log('get_id>>>'+get_id);
					var perc_calc2 = (answeredCount[i] / totalQuestion[i]) * 100;
					
					//console.log('perc_calc2 value >>>'+perc_calc2);
					$('.survey_heading_div li#' + get_id).find('.progress .progress-bar').css('width', perc_calc2 + '%');
				
			}
			//Auto Scrolling end
			
		},
		error : function(xhr, ajaxOptions, throwError) {
//			alert("Please Try Again");
			swal('Please Try Again','', 'warning');
		}

	});
	if(t>0)
	{  
		   debugger;
	 		//alert("Survey is already completed");
	       // swal("Survey is already completed", "error");
				swal({
					  title: 'Survey is Completed',
					  type:'success',
					  confirmButtonColor: '#3085d6',
					  confirmButtonText: 'ok'
					}, function(){
					      window.location.href = "j_spring_security_logout";
						//alert('c value>>>'+c);
						window.location.href="beforeLogout?id="+c;
					});
		}
}

// checking if the survey is already filled start

function checkfilledalready(c, d) {
	debugger;
	var operationval = $('#operation').val();
	
	var t=0;
		$.ajax({
			type : "POST",
			url : "getEmpresponse",
			async : false,
			data : {
				surveyId1 : d,
				pkEmp1 : c
			},
			success : function(data) {
			
					debugger;
					var areas = [];
					var areaCounter = 0;
					var answerCounter = 0;
					var answeredCount = [];
					var totalQuestion = [];
					var get_id ;

				$.each(JSON.parse(data), function(idx, obj) {
					$('#operation').val('Y');
					$('#responsemap').val(obj.responsemapID);
					if(obj.comments!="null" && obj.comments!="")
					{	
					$("#dvComments" + obj.areacode + "text").val(obj.comments);
					$('#Comments' + obj.areacode).prop(
							'checked', true);
					$("#dvComments" + obj.areacode).show();
					}
					//$("#dvComments" + obj.areacode + "text").val(obj.comments);
					$(".dvComments" + obj.areacode + "Resp").val(obj.comments);

					$('#responsemap').val(obj.responsemapID);
					if (obj.status == 'completed') {
						$('#saveUser').attr('disabled', 'disabled');
						$('#submitUser').attr('disabled', 'disabled');
			          t++;		
					}
					if (obj.questionType == 'Rate') {
						$('#Resp' + obj.questionBankId + 'rad' + obj.answerNo).prop('checked', true);
						$('#Comments' + obj.areacode).prop('checked', true);
						//$('#Resp' + obj.questionBankId + 'rad' + obj.answerNo).addClass('checkedval');
						$('#Resp' + obj.questionBankId + 'rad' + obj.answerNo).parents('.main-que').addClass('checkedval'); //added by nikita
						$('.progress-bar.checkedval').css("width",100);

					} else if (obj.questionType == 'OpenEnded') {
						$('#Resp' + obj.questionBankId + 'text').val(obj.answerText);
						$('#Resp' + obj.questionBankId + 'text').prop('checked', true);   //added by nikita
						//
						

						if(obj.answerText.length >0 ){
							//$('#Resp' + obj.questionBankId + 'text').addClass('checkedval'); //added by nikita
							$('#Resp' + obj.questionBankId + 'text').parents('.main-que').addClass('checkedval'); //added by nikita
						}
						
						
						//$('#Resp' + obj.questionBankId + 'text').addClass('checkedval'); //added by nikita
					} else if (obj.questionType == 'Subquestion') {
						var array = (this.subquestionmap).split(",");
						var k = 0;
						$.each(array, function(i) {

							$('#Resp' + array[k] + 'subquest' + array[k + 1]).prop('checked', true);
							$('#Resp' + array[k] + 'subquest' + array[k + 1]).addClass('checkedval');  //added by nikita
							k = k + 1;
							if (array[k] == undefined) {
								return false;
							}
						});
					}
					
					//addClasstoAnswer(obj,areas,areaCounter,answerCounter,answeredCount,totalQuestion,get_id);   //Added by nikita
					
					//Niki
					var position = areas.indexOf(obj.areacode);    //Checking whether area is already present, if present get index				
					if (position == -1){
						areas[areaCounter] =  obj.areacode;
						position = areaCounter;
						answeredCount[position] = 0;
						areaCounter++;
					}
					
					if ( totalQuestion[position] == undefined)
						totalQuestion[position] = 1;
					else
						totalQuestion[position] = totalQuestion[position] + 1;
					
					if( obj.questionType == 'Rate' && obj.answerNo > 0 ){        //Question type = Rating
						
						if ( answeredCount[position] == undefined)
						{
						answeredCount[position] = 1;
						get_id = 'li_'+(answeredCount[position]);    
						console.log($('.survey_heading_div li#'+get_id));
						$('.survey_heading_div li#'+get_id).addClass('answered');
						
						}else{
							answeredCount[position] = answeredCount[position] + 1;
							get_id = 'li_'+(answeredCount[position]);    
							console.log($('.survey_heading_div li#'+get_id));
	     					$('.survey_heading_div li#'+get_id).addClass('answered');
						}
							
					}else if(obj.questionType == 'OpenEnded' && obj.answerText != ''){
						if ( answeredCount[position] == undefined)
						{
						answeredCount[position] = 1;
						get_id = 'li_'+(answeredCount[position]);    
						console.log($('.survey_heading_div li#'+get_id));
						$('.survey_heading_div li#'+get_id).addClass('answered');
						}else{
							answeredCount[position] = answeredCount[position] + 1;
							get_id = 'li_'+(answeredCount[position]);    
							console.log($('.survey_heading_div li#'+get_id));
								$('.survey_heading_div li#'+get_id).addClass('answered');	
						}
					}else if(obj.questionType == 'Subquestion'  && obj.subquestionmap.split(',')[1] > 0 ){
						if ( answeredCount[position] == undefined)
						{
						answeredCount[position] = 1;
						get_id = 'li_'+(answeredCount[position]);    
						console.log($('.survey_heading_div li#'+get_id));
						$('.survey_heading_div li#'+get_id).addClass('answered');
						}else{
							answeredCount[position] = answeredCount[position] + 1;
							get_id = 'li_'+(answeredCount[position]);    
							console.log($('.survey_heading_div li#'+get_id));
								$('.survey_heading_div li#'+get_id).addClass('answered');	
						}
					}
					//Niki end
				
				});
				
				console.log('areas');
				console.log(areas);
				
				console.log('answeredCount');
				console.log(answeredCount);
				console.log('totalQuestion');
				console.log(totalQuestion);
				
				
				//setprogressbarwidth(areas,get_id,answeredCount,totalQuestion);  //added by nikita
				
				for (i = 0; i < areas.length; ++i) {
					debugger;
                     				
						get_id = 'li_'+(i+1);
						console.log('get_id>>>'+get_id);
						var perc_calc2 = (answeredCount[i] / totalQuestion[i]) * 100;
						
						console.log('perc_calc2 value >>>'+perc_calc2);
						$('.survey_heading_div li#' + get_id).find('.progress .progress-bar').css('width', perc_calc2 + '%');
					
				}

			},
			error : function(xhr, ajaxOptions, throwError) {
//				alert("Please Try Again");
				swal('Please Try Again','', 'warning');
			}

		});
		if(t>0)
		{   debugger;
 		//alert("Survey is already completed");
       // swal("Survey is already completed", "error");
			swal({
				  title: 'Survey is Completed',
				  type:'success',
				  confirmButtonColor: '#3085d6',
				  confirmButtonText: 'ok'
				}, function(){
				     /* window.location.href = "j_spring_security_logout";*/
					//alert('c value>>>'+c);
					window.location.href="beforeLogout?id="+c;
				});
//			window.location.href = "login";
			}

	}




//end

function populateUserData(g) {

//alert('Inside populateUserData >>>'+g);
	var k = 0;
	$
			.ajax({
				type : "POST",
				url : "getEmpdata",
				async : false,
				data : {
					emailId : g
				},
				success : function(data) {
				
				//alert('Inside populateUserData Success');
                debugger;
					$
							.each(
									data,
									function(idx, obj) {
										populateCountries("country2");
										if (!(obj.fname.length < 1 || obj.fname == "null" || obj.fname=="" )) {
											$("#fname").val(obj.fname);
											/*$("#fname")
													.attr('readonly', 'true');*/
											++k;
										}

										if (!(obj.lname.length < 1 || obj.lname == "null") || obj.lname=="") {
											$("#lname").val(obj.lname);
										/*	$("#lname")
													.attr('readonly', 'true');*/
											++k;
										}

										if (!(obj.email.length < 1 || obj.email == "null" || obj.email=="")) {
											$("#emailId").val(obj.email);
										/*	$("#emailId").attr('readonly',
													'true');*/
											++k;
										}

										if (!(obj.gender.length < 1 || obj.gender == "null")) {
											if (obj.gender == "Male") {
												$("#male")
														.prop('checked', true);
												//$("#male").attr('disabled','true');
												/*$("#female").attr('disabled',
														'true');*/
											} else {
												$("#female").prop('checked',
														true);
												/*$("#male").attr('disabled',
														'true');*/
												//$("#female").attr('readonly','true');
											}
											++k;
										}

										if (!(obj.country.length < 1 || obj.country == "null") || obj.country== "") {

											$("#country2").val(obj.country);
											/*$("#country2").attr('readOnly',
													'true');*/
											
											/*$("#country2 option:not(:selected)").attr('disabled', 'true');
											$("#country2").css(
													"background-color", "#eee");*/
											++k;
										}
										else{
											$("#country2").val("India");
											++k;
										}
										if (!(obj.datofBirth.length < 1 || obj.datofBirth == "null")) {

											$("#datepicker3").val(
													obj.datofBirth);
											/*$("#datepicker3").datepicker(
													'disable');*/
										/*	$("#datepicker3").attr('readOnly',
											'true');*/
											++k;
										}
										
										if (!(obj.sector.length < 1 || obj.sector == "null" || obj.sector == ""|| obj.sector == "0")) {
                                            
											$("#sector").val(obj.sector);
											/*$("#sector option:not(:selected)").attr('disabled', 'true');
											$("#sector").css(
													"background-color", "#eee");
*/
											++k;
										}
										if (!(obj.sectorOthers.length < 1 || obj.sectorOthers == "null")) {
											$("#sectorOthers").val(
													obj.sectorOthers);
											$("#otherSector").show();
											/*$("#sectorOthers").attr('readonly',
													'true');*/

										}
										if (!(obj.business.length < 1 || obj.business == "null" ||  obj.business == "")) {

											$("#business").val(obj.business);
											/*$("#business option:not(:selected)").attr('disabled', 'true');

											$("#business").css(
													"background-color", "#eee");*/
											++k;
										}
										if (!(obj.businessOthers.length < 1 || obj.businessOthers == "null")) {
											$("#businessOthers").val(
													obj.businessOthers);
											$("#otherBusiness").show();
											/*$("#businessOthers").attr(
													'readonly', 'true');*/

										}

										if (k < 8) {
											/*$('#submitUser').attr('disabled',
													'disabled');*/
//											alert("Please complete your profile");
											swal("Please complete your profile \r\n *Once updated details can not be changed.",'','warning');
											
										} else {
											
											$('#saveUser').removeAttr(
													'disabled');
											$('#submitUser').removeAttr(
													'disabled');
											$('#saveuser').attr('disabled',
													'disabled');
											$("#demograpic :input").attr('disabled','disabled');
											$("#demograpic :input").attr('readonly','true');
											
											$("#saveuser").removeAttr(
													"data-toggle");
											$('#saveuser').css('pointer-events','none');
//											alert("Profile is complete.You can start the Survey");
											//swal("Profile is complete.You can start the Survey", '',"success");
											$('#update-profile').modal('show');
										}
									});
				}
			});
}

function saveUserResponse() {//yashref
	showLoader();
	debugger;
	//alert('Inside saveUserResponse responsemap val >>>>>>>>>>'+$('#responsemap').val());
	
	console.log('Saving data');
	console.log($('#responseStart').serialize());
	
	$.post('responseAdd', $('#responseStart').serialize(), function(response) {
//		alert("Successfully Saved Survey Response");
//		swal("Successfully Saved Survey Response", '',"success");
		
		swal({
			  title: 'Successfully Saved Survey Response',
			  type:'success',
			  confirmButtonColor: '#3085d6',
			  confirmButtonText: 'ok'
			});
		hideLoader();
		
		//alert(' Inside saveUserResponse, now populate(a,b) method will be called ...'+a +' ,b:'+b);
		populate(a, b);
		
	});
}

function saveUserResponseAutoSave() {//yashref
debugger;
	$.post('responseAdd', $('#responseStart').serialize(), function(response) {
		populate(a, b);
	});
	
}

function submitUserResponse() {
	debugger;	
	if (validateDetails('demograpic')) {
	if (validateDetails('responseStart')) {
		$.post('responseSubmit', $('#responseStart').serialize(), function(
				response) {
		//	alert("Survey Response Submitted Successfully ");
/*//			swal("Survey Response Submitted Successfully",'', "success");
			swal({
				  title: 'Survey Response Submitted Successfully',
				  type:'success',
				  confirmButtonColor: '#3085d6',
				  confirmButtonText: 'ok'
				},function(){
					
				});*/
			saveUserBeforeSubmit();
			
			populate(a, b);
			//$("#Welcome").modal('show');
		});

	}
}

}

function validateDetails(formid) {
	debugger;
	
	$("#delete").modal('hide');
	var count = 0, currEle = null;
	var status = true;
	$.each($("#" + formid).find(".must"), function() {

		if ($(this).is("input") && $(this).val().trim().length == 0) {
			count++;
			currEle = this;
			$(this).css('border-color', 'red');
			return false;
		} else if ($(this).is("select") && ($(this).val() == "" || $(this).val() == "-1")) {
			count++;
			currEle = this;
			$(this).css('border-color', 'red');
			return false;
		} else if ($(this).is("textarea") && $(this).val().trim().length == 0) {
			count++;
			currEle = this;
			$(this).css('border-color', 'red');
			$(this).focus();
			//alert("focus");
			//window.scrollTo(500, 0);
			status = false;
			return false;
		}
		else{
			$(this).css('border-color', '');
			return true;
		}

	});
	if( $("#otherSector").is(":visible") && $("#otherSector").find("#sectorOthers").val().trim().length == 0){
		count++;
		currEle = this;
		$("#otherSector").find("#sectorOthers").css('border-color', 'red');
		return false;
	}
	if( $("#otherBusiness").is(":visible") && $("#otherBusiness").find("#businessOthers").val().trim().length == 0){
		count++;
		currEle = this;
		$("#otherBusiness").find("#businessOthers").css('border-color', 'red');
		return false;
	}
	//commented by Rakesh kumar End:
	/*if (status) {
		$.each($("#" + formid).find("input:radio"), function() {

			var name = $(this).attr("name");

			if (!$('input:radio[name="' + name + '"]:checked').length||$('input:radio[name="' + name + '"]:checked').val()=="-1") {
				count++;
				$(this).focus();
				window.scrollTo(0, 0);
				//alert("focus");
				return false;
			}

		});

	}*/
	//commented by Rakesh kumar End:
	if (status) {
        $.each($("#" + formid).find("input:radio"), function() {

                        var name = $(this).attr("name");
                        currEle = null;
                        if (!$('input:radio[name="' + name + '"]:checked').length||$('input:radio[name="' + name + '"]:checked').val()=="-1") {
                                        count++;
                                        currEle = this;
                                        var id=this.id;
                                        if (navigator.userAgent.match(/(iPod|iPhone|iPad|Android)/)) {
                                                        $('body').animate({
                                                            scrollTop: $(this).offset().top-120
                                                        },500);
                                                        
                                                        $(this).focus();

                                        }else{
                                                        $(this).focus();
                                                        
                                        }

                                        return false;
                        }

        });
        $.each( $("#" + formid).find(".addCommentDiv"),function(){
    		/*if($(this).find(".addcmntchk").is(":checked") && $(this).find(".Addcomments").val().trim().length == 0){
    			count++;
                currEle = this;
                $(this).find(".Addcomments").css('border-color', 'red');
                $(this).find(".Addcomments").focus();
    			return false;
    		}*/
    	});
       /* $.each( $("#" + formid).find(".sectorOthers1"),function(){
        	//$(this).find(".sectorOthers").val().trim().length == 0
    		if($(this).is("input") && ($(this).val() == "" || $(this).val() == "-1")){
    			count++;
              
                //$(this).find(".sectorOthers1").css('border-color', 'red');
    			$(".sectorOthers1").css('border-color', 'red');
                $(".sectorOthers1").focus();
                currEle = this;
    			return false;
    		}
    	});
        $.each( $("#" + formid).find(".businessOthers1"),function(){
        	//$(this).find(".sectorOthers").val().trim().length == 0
    		if($(this).is("input") && ($(this).val() == "" || $(this).val() == "-1")){
    			count++;
               // 
                //$(this).find(".otherBusiness1").css('border-color', 'red');
    			$(".businessOthers1").css('border-color', 'red');
                $(".businessOthers1").focus();
                currEle = this;
    			return false;
    		}
    	});*/
}

	if (count > 0) {
		//alert("Please fill all Fields");
		swal("Please fill all Fields",'', "warning");
		currEle = this;
		$(this).focus();
		return false;
	}
	return true;

}

function showLoader() {
	$(".loader").show();
}

function hideLoader() {
	$(".loader").hide();
}
$(document).on("click", ".nobtn", function(){
	$("#datepicker3").val("");
});
function populateUserDataBeforeSubmit(g) {
	debugger;
	var k = 0;
	$
			.ajax({
				type : "POST",
				url : "getEmpdata",
				async : false,
				data : {
					emailId : g
				},
				success : function(data) {
debugger;
					$
							.each(
									data,
									function(idx, obj) {
										populateCountries("country2");
										if (!(obj.fname.length < 1 || obj.fname == "null"  )) {
											$("#fname").val(obj.fname);
											/*$("#fname")
													.attr('readonly', 'true');*/
											++k;
										}

										if (!(obj.lname.length < 1 || obj.lname == "null" )) {
											$("#lname").val(obj.lname);
										/*	$("#lname")
													.attr('readonly', 'true');*/
											++k;
										}

										if (!(obj.email.length < 1 || obj.email == "null" )) {
											$("#emailId").val(obj.email);
										/*	$("#emailId").attr('readonly',
													'true');*/
											++k;
										}

										if (!(obj.gender.length < 1 || obj.gender == "null")) {
											if (obj.gender == "Male") {
												$("#male")
														.prop('checked', true);
												//$("#male").attr('disabled','true');
												/*$("#female").attr('disabled',
														'true');*/
											} else {
												$("#female").prop('checked',
														true);
												/*$("#male").attr('disabled',
														'true');*/
												//$("#female").attr('readonly','true');
											}
											++k;
										}

										if (!(obj.country.length < 1 || obj.country == "null" )) {

											$("#country2").val(obj.country);
											/*$("#country2").attr('readOnly',
													'true');*/
											
											/*$("#country2 option:not(:selected)").attr('disabled', 'true');
											$("#country2").css(
													"background-color", "#eee");*/
											++k;
										}
										else{
											$("#country2").val("India");
											++k;
										}
										if (!(obj.datofBirth.length < 1 || obj.datofBirth == "null")) {

											$("#datepicker3").val(
													obj.datofBirth);
											/*$("#datepicker3").datepicker(
													'disable');*/
										/*	$("#datepicker3").attr('readOnly',
											'true');*/
											++k;
										}
										
										if (!(obj.sector.length < 1 || obj.sector == "null" ||  obj.sector == "0")) {
                                            
											$("#sector").val(obj.sector);
											/*$("#sector option:not(:selected)").attr('disabled', 'true');
											$("#sector").css(
													"background-color", "#eee");
*/
											++k;
										}
										if (!(obj.sectorOthers.length < 1 || obj.sectorOthers == "null")) {
											$("#sectorOthers").val(
													obj.sectorOthers);
											$("#otherSector").show();
											/*$("#sectorOthers").attr('readonly',
													'true');*/

										}
										if (!(obj.business.length < 1 || obj.business == "null" )) {

											$("#business").val(obj.business);
											/*$("#business option:not(:selected)").attr('disabled', 'true');

											$("#business").css(
													"background-color", "#eee");
											++k;
										}
										if (!(obj.businessOthers.length < 1 || obj.businessOthers == "null")) {
											$("#businessOthers").val(
													obj.businessOthers);
											$("#otherBusiness").show();
											/*$("#businessOthers").attr(
													'readonly', 'true');*/

										}
										/*if (!(obj.businessOthers.length < 1 || obj.businessOthers == "null")) {
											$("#businessOthers").val(	obj.businessOthers);
										}*/

										if (k < 8) {
											/*$('#submitUser').attr('disabled',
													'disabled');*/
//											alert("Please complete your profile");
											//swal("Please complete your profile",'','warning');
											
										} else {
											
											$('#saveUser').removeAttr(
													'disabled');
											$('#submitUser').removeAttr(
													'disabled');
											$('#saveuser').attr('disabled',
													'disabled');
											$("#demograpic :input").attr('disabled','disabled');
											$("#demograpic :input").attr('readonly','true');
											
											$("#saveuser").removeAttr(
													"data-toggle");
											$('#saveuser').css('pointer-events','none');
//											alert("Profile is complete.You can start the Survey");
											//swal("Profile is complete.You can start the Survey", '',"success");
										}
									});
				}
			});
}


//Added By nikita on 4dec2020 start
function addClasstoAnswer(obj,areas,areaCounter,answerCounter,answeredCount,totalQuestion,get_id){
	
	var position = areas.indexOf(obj.areacode);    //Checking whether area is already present, if present get index				
	if (position == -1){
		areas[areaCounter] =  obj.areacode;
		position = areaCounter;
		answeredCount[position] = 0;
		areaCounter++;
	}
	
	if ( totalQuestion[position] == undefined)
		totalQuestion[position] = 1;
	else
		totalQuestion[position] = totalQuestion[position] + 1;
	
	if( obj.answerNo > 0 ){   //Question type = Rating
		
		if ( answeredCount[position] == undefined)
		{
		answeredCount[position] = 1;
		get_id = 'li_'+(answeredCount[position]);    
		console.log($('.survey_heading_div li#'+get_id));
		$('.survey_heading_div li#'+get_id).addClass('answered');
		
		}else{
			answeredCount[position] = answeredCount[position] + 1;
			get_id = 'li_'+(answeredCount[position]);    
			console.log($('.survey_heading_div li#'+get_id));
				$('.survey_heading_div li#'+get_id).addClass('answered');
		}
			
	}else{
		if(obj.answerNo == 0 && obj.answerText != ''){
			if ( answeredCount[position] == undefined)
			{
			answeredCount[position] = 1;
			get_id = 'li_'+(answeredCount[position]);    
			console.log($('.survey_heading_div li#'+get_id));
			$('.survey_heading_div li#'+get_id).addClass('answered');
			}else{
				answeredCount[position] = answeredCount[position] + 1;
				get_id = 'li_'+(answeredCount[position]);    
				console.log($('.survey_heading_div li#'+get_id));
					$('.survey_heading_div li#'+get_id).addClass('answered');	
			}
		}else{
			
		}
	}
	//Niki end

}

function setprogressbarwidth(areas,get_id,answeredCount,totalQuestion){
	for (i = 0; i < areas.length; ++i) {
		debugger;
	
			get_id = 'li_'+(i+1);
			console.log('get_id>>>'+get_id);
			var perc_calc2 = (answeredCount[i] / totalQuestion[i]) * 100;
			
			console.log('perc_calc2 value >>>'+perc_calc2);
			$('.survey_heading_div li#' + get_id).find('.progress .progress-bar').css('width', perc_calc2 + '%');
		
	}
}
//Added By nikita on 4dec2020 end