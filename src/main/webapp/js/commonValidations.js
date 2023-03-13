function validateQuestion(validObject){
	if(validObject.questionDesc==''||validObject.questionDesc.replace(/\s+$/,'').length==0) {
//		alert("Please enter valid Description in the question");	
		debugger;
		swal("Please enter valid Description in the question");	
		return false;
	} else if(validObject.areaCode=='0'||validObject.areaCode==0){
	//	alert("Please enter valid area");	
			swal("Please enter valid area");	
		return false;
	} else if(validObject.questionType=='0'||validObject.questionType==0){
//		alert("Please enter valid questionType");	
		swal("Please enter valid questionType");	
		return false;
	} else if(validObject.year=='0'||validObject.year==0){
		swal("Please Select Year");	
		return false;
		debugger;
	} else if(validObject.questionType=='4' && ( $('#subquesteditId').val().length<1 && $('#subquestaddId').val().length<1)){
//		alert("Please enter Sub Questions");	
		swal("Please enter Sub Questions");	
		return false;
	}
    else if(validObject.questionType !='4' && $('#subquesteditId').val().length>0) {
//	alert("Sub Questions Not Allowed for This Question Type");	
	swal("Sub Questions Not Allowed for This Question Type");	
	return false;
    }
	else{
		return true;
	}
	console.log(validObject);
}