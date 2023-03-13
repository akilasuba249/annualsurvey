var x = 1;
var date=new Date();
var currYear=date.getFullYear();

$(document).ready(function() {
	
	var configflag= false;
	 var empflag=false;
	 var questionflag=false;
	 var surveyflag=false;
	 var trackingflag=false;
	 var reportflag=false;
	 $('#Config').show();
	 $('#Employee').hide();	
	 $('#Question').hide();
	 $('#Tracking').hide();
	 $('#Report').hide();
	 $('#Area').hide();
 
	 $(window).scroll( function() {
			
		 debugger;
		 var scrollposition = $(window).scrollTop() + $(window).height();
		 var scrolconfigbottom = $('#scrolconfig').position().top + $('#scrolconfig').outerHeight(true);
	     
		 
		 if(empflag==false && $(window).scrollTop() + $(window).height() > $(document).height() - 100) {
			 empflag= true;
	    	 $('#Employee').show();	
	    	 alert(" fire Employee");

		   }
		 
		 if (questionflag== false && $(window).scrollTop() + $(window).height() > $(document).height() - 100) { 
		    
		       //callgetquestion();
	    	 questionflag = true;
	    	 $('#Question').show();
	    	 callgetquestion();
		    }
	     
	     
	     if (surveyflag== false && $(window).scrollTop() + $(window).height() > $(document).height() - 100) { 
		    
		       //callgetquestion();
	    	 surveyflag = true;
	    	 $('#Area').show();
	    	 scrollsurvey();
	    	 alert(" fire surveyflag");
		    }
	     
	     
	     if (trackingflag== false && $(window).scrollTop() + $(window).height() > $(document).height() - 100) { 
		    
		       //callgetquestion();
	    	 trackingflag = true;
	    	 $('#Tracking').show();
	    	 scrolltracking();
	    	 alert(" fire trackingflag");
		    }
	     
	    
	     if (reportflag== false && $(window).scrollTop() + $(window).height() > $(document).height() - 100) { 
		    
		       //callgetquestion();
	    	 reportflag = true;
	    	 $('#Report').show();
	    	 scrollreport();
	    	 alert(" fire reportflag");
		    }
		 
/*		 if ( empflag==false && scrollposition >= scrolconfigbottom ) 
		    {
		       //callgetquestion();
	    	 empflag= true;
	    	 $('#Employee').show();	
	    	 alert(" fire Employee");

		    }
	     
	     var Employeebottom = $('#Employee').position().top ;//+ $('#Employee').outerHeight(true);
	     if (questionflag== false && scrollposition >= Employeebottom ) 
		    {
		       //callgetquestion();
	    	 questionflag = true;
	    	 $('#Question').show();
	    	 alert(" fire question");
		    }
	     
	     var Questionbottom = $('#Question').position().top;// + $('#Question').outerHeight(true);
	     if (surveyflag== false && scrollposition >= Questionbottom ) 
		    {
		       //callgetquestion();
	    	 surveyflag = true;
	    	 $('#Area').show();
	    	 alert(" fire surveyflag");
		    }
	     
	     var Areabottom = $('.Area-section').position().top ;//+ $('#Area').outerHeight(true);
	     if (trackingflag== false && scrollposition >= Areabottom ) 
		    {
		       //callgetquestion();
	    	 trackingflag = true;
	    	 $('#Tracking').show();
	    	 alert(" fire trackingflag");
		    }
	     
	     var Trackingbottom = $('#Tracking').position().top;// + $('#Tracking').outerHeight(true) ;
	     if (reportflag== false && scrollposition >= Trackingbottom ) 
		    {
		       //callgetquestion();
	    	 reportflag = true;
	    	 $('#Report').show();
	    	 alert(" fire reportflag");
		    }
*/	     
		}); 

	
$('#getquestion').click(function(){
	alert("in question .js");
	callgetquestion();
});
	function callgetquestion(){
		
		alert("in getquestion .js");
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


});


function insertQuest(){
	var jsonDataToServer='';
	var jsonDataFromServer='';
	jsonDataToServer = $('#addQuestionForm').serialize();
	
	var validObject=$('#addQuestionForm').serializeJSON();
	debugger;
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
		 tr.append('<td class="que4" id="editQ_'+obj.questionId+'"><a  data-target="#editQuest" data-title="Edit" data-toggle="modal" class="edit md-trigger col-md-6 col-sm-6 hand editQuest" id="editQ_'+obj.questionId+'"></a><a data-target="#deleteQuestMod" data-title="Delete" data-toggle="modal" class="delete col-md-6 col-sm-6 md-trigger hand" onClick="setQuestId('+obj.questionId+')"> </a></td>');
			$("#questTable tbody").append(tr);
			
	 
	 });

}


function getQuestByYearArea(){
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