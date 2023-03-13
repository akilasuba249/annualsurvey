/**
 * 
 */

var data;
$(document).ready(function() {
	var currentTime = new Date();
	var a = currentTime.getFullYear();
	$("div.year3 select").val(a);
	getYearEMP1(a);
});

function submitEmployee() {

	debugger;
	if (EmailValidation()) {

		var l = 0;
		if (validateDetails('addUpdate')) {
			$('#Addemployee').modal('hide');
			showLoader();
			$.post('empinsert',$('#addUpdate').serialize(),function(response) {

				$('#modal-5').modal('hide');
				oppResponse = response;
				var tableData = "";
				$('#onetable tbody').html('');
				$.each(response,function(i, j) {
					l++;
					var oppData = JSON
					.stringify(response[i]);
					tableData += '<tr><td class="emp1">'
						+ this.empId
						+ ' </td> <td class="emp2">'
						+ this.fname
						+ ' '
						+ this.lname
						+ ' </td><td class="emp3">'
						+ this.email
						+ '</td> ';
					tableData += ' <td class="emp4 dropdown"><div class="serch hand dropdown-toggle" id="menu1" data-toggle="dropdown"></div> <ul class="dropdown-menu" role="menu" aria-labelledby="menu1"> <li id="'
						+ this.pkEmp
						+ 'areacount">';
					var array = (this.industries)
					.split(",");
					var k = 0;
					$
					.each(array,function(i) {
								tableData += '<label class="col-md-11 col-sm-12"'
									+ ' id ='
									+ array[k]
								+ 'areamap>'
								+ array[++k]
								+ '</label> ';
								k = k + 1;
								if (array[k] == undefined) {
									return false;
								}
							});
					tableData += ' </li><td class="emp5 col-md-12"><div class="edit md-trigger col-md-6 col-sm-6 hand" id='
						+ this.pkEmp
						+ 'editEmployee onClick="edit_employee('
						+ this.pkEmp
						+ ');"></div>';
					tableData += '  <div class="delete col-md-6 col-sm-6 md-trigger hand " id='
						+ this.pkEmp
						+ '"deleteEmployee" onClick="delete_employee('
						+ this.pkEmp
						+ ');"></div></td></tr> ';
					$('#onetable tbody').html(
							tableData);

				});
				$('#empcount').val(l);

				hideLoader();
				var oTable = $(
						'div#table-responsive marg30 table:eq(0)')
						.dataTable();
				if (oTable != null) {
					oTable.fnDestroy();
				}

			});

		}

	} else {

		// alert("email")
		swal("Please Enter Valid Email ID");
	}

}

function delete_employee(data1) {

	data = data1;
	var title = 'Delete this entry';
	var content = '<div class="modal-body"><div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div></div>';
	var footer = '<button type="button" id="deleteemp" onClick="callMe()" class="deleterow btn addbtn"><span class="glyphicon glyphicon-ok-sign"></span>Yes</button> <button type="button" class="btn addbtn" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>No</button>';

	$(".deleteModal .modal-header").html(title);
	$(".deleteModal .modal-body").html(content);
	$(".deleteModal .modal-footer").html(footer);

	$('.deleteModal').modal('show');
}

function edit_employee(data1) {
	var ename = $("#" + data1 + "editEmployee").parent().parent().children()
	.eq(1).html();
	var email = $("#" + data1 + "editEmployee").parent().parent().children()
	.eq(2).html();
	var empid = $("#" + data1 + "editEmployee").parent().parent().children()
	.eq(0).html();
	var currentTime1 = new Date();
	var a2 = currentTime1.getFullYear();
	var ar6 = $("#areaId").val();
	var selectedYear = $("#yearEmp").val();
	$('#updateEmp').trigger("reset");

	if (selectedYear < a2) {
		swal("Edit Not Allowed For Previous Year");
		return false;
	}
	var title = 'Edit Employee <button type="button" class="close" data-dismiss="modal">&times;</button>';
	var content = '<div class="modal-body"><form id="updateEmp" action="updateEmp" method="POST"><div class="form-group"><input type="hidden" name="areaAdd" value="'
		+ ar6
		+ '"><input type="hidden" name="year" value="'
		+ selectedYear
		+ '"><input type="hidden" name="pkEmp" value="'
		+ data1
		+ '"><label class="col-md-2 nopad"><span class="starRed">*</span>Token ID </label><br><input class="form-control must" type="text" name="empId" value='
		+ empid
		+ ' placeholder="'
		+ empid
		+ ' " readonly></div><div class="form-group"><label class="col-md-2 nopad"><span class="starRed">*</span>Name </label><br><input class="form-control must" type="text" name="fName" placeholder="'
		+ ename
		+ '" value="'
		+ ename
		+ '" readonly> </div><div class="form-group">';
	content += ' <label class="col-md-2 nopad"><span class="starRed">*</span>Email ID </label><br><input class="form-control must" type="text" placeholder="'
		+ email
		+ ' " value="'
		+ email
		+ '" name="emailId" id="emailId1" S/></div><div class="form-group"><label class="col-md-2 nopad"><span class="starRed">*</span>Select Area</label><div class="clearfix"></div>';

	$
	.ajax({
		type : "POST",
		url : "getAreasForYear",
		async : false,
		data : {
			selectedYear : selectedYear
		},
		success : function(data) {
			var areaYearList = jQuery.parseJSON(data);
			$
			.each(
					areaYearList,
					function(idx, obj) {

						content += '<div class="col-md-1 col-sm-1 nopad nopadding_style"><input type="checkbox" id="'
							+ obj.areaCode
							+ 'areamap1" name="Area['
							+ idx
							+ '].areaCode" value="'
							+ obj.areaDesc
							+ '"></div><label class="col-md-5 col-sm-5 nopad" >'
							+ obj.areaDesc
							+ '</label>'
							+ '<input type="hidden" name ="Area['
							+ idx
							+ '].areaCode1" value="'
							+ obj.areaCode + '">'

							;
					});

		},
		error : function(xhr, ajaxOptions, throwError) {
			swal("Please Try Again");
		}

	});
	content += '</div></div><div class="clearfix"></div>';
	var footer = ' <button type="button" class="md-close btn addbtn btn-lg"  onClick="callUpdate()"><span class="glyphicon glyphicon-ok-sign"></span>Update</button></form> ';

	$(".EditEmployee .modal-header").html(title);
	$(".EditEmployee .modal-body").html(content);
	$(".EditEmployee .modal-footer").html(footer);
	$('.EditEmployee').modal('show');

	var lenth = $("#" + data1 + "areacount").children().length;
	for (var i = 0; i < lenth; i++) {

		var dat2 = $("#" + data1 + "editEmployee").parent().parent().children()
		.eq(3).children().eq(1).children().eq(0).children().get(i).id;
		$('#' + dat2 + '1').prop('checked', true);
	}

}

function callUpdate() {

	if (EmailValidation2()) {
		if (validateDetails('updateEmp')) {
			var l = 0;
			$('#EditEmployee').modal('hide');
			showLoader();

			$
			.post(
					'updateEmp',
					$('#updateEmp').serialize(),
					function(response) {
						oppResponse = response;
						var tableData = "";
						$('#onetable tbody').html('');
						var k = 0;
						$
						.each(
								response,
								function(i, j) {
									l++;
									var oppData = JSON
									.stringify(response[i]);
									tableData += '<tr><td class="emp1">'
										+ this.empId
										+ ' </td> <td class="emp2">'
										+ this.fname
										+ ' '
										+ this.lname
										+ ' </td><td class="emp3">'
										+ this.email
										+ '</td> ';
									tableData += ' <td class="emp4 dropdown"><div class="serch hand dropdown-toggle" id="menu1" data-toggle="dropdown"></div> <ul class="dropdown-menu" role="menu" aria-labelledby="menu1"><li id="'
										+ this.pkEmp
										+ 'areacount">';
									var array = (this.industries)
									.split(",");
									var k = 0;
									$
									.each(
											array,
											function(i) {
												tableData += '<label class="col-md-11 col-sm-12"'
													+ ' id ='
													+ array[k]
												+ 'areamap>'
												+ array[++k]
												+ '</label> ';
												k = k + 1;
												if (array[k] == undefined) {
													return false;
												}
											});
									tableData += '</li> <td class="emp5 col-md-12"><div class="edit md-trigger col-md-6 col-sm-6 hand"  id='
										+ this.pkEmp
										+ 'editEmployee onClick="edit_employee('
										+ this.pkEmp
										+ ');"></div>';

									tableData += '  <div class="delete col-md-6 col-sm-6 md-trigger hand " id='
										+ this.pkEmp
										+ '"deleteEmployee" onClick="delete_employee('
										+ this.pkEmp
										+ ');"></div></td></tr> ';
									$('#onetable tbody').html(
											tableData);

								});

						var oTable = $(
						'div#table-responsive marg30 table:eq(0)')
						.dataTable();
						if (oTable != null) {
							oTable.fnDestroy();
						}
						$('#empcount').val(l);

						hideLoader();

					});
		}

	} else {
		swal("Please enter valid Email ID");
	}

}

var oppResponse = null;
function getYearEMP(sel) {
	var value = sel.value;
	var ar = $("#areaId").val();
	var l = 0;
	showLoader();
	$
	.post(
			'getEmployeeArea',
			{
				isActive : 'N',
				year : value,
				area : ar
			},
			function(response) {
				hideLoader();
				oppResponse = response;
				var tableData = "";
				$('#onetable tbody').html('');
				$
				.each(
						response,
						function(i, j) {
							l++;
							var oppData = JSON
							.stringify(response[i]);
							tableData += '<tr><td class="emp1">'
								+ this.empId
								+ ' </td> <td class="emp2">'
								+ this.fname
								+ ' '
								+ this.lname
								+ ' </td><td class="emp3">'
								+ this.email + '</td> ';
							tableData += ' <td class="emp4 dropdown"><div class="serch hand dropdown-toggle" id="menu1" data-toggle="dropdown"></div> <ul class="dropdown-menu" role="menu" aria-labelledby="menu1"><li id="'
								+ this.pkEmp
								+ 'areacount">';
							var array = (this.industries)
							.split(",");
							var k = 0;
							$
							.each(
									array,
									function(i) {
										tableData += '<label class="col-md-11 col-sm-12"'
											+ ' id ='
											+ array[k]
										+ 'areamap>'
										+ array[++k]
										+ '</label> ';
										k = k + 1;
										if (array[k] == undefined) {
											return false;
										}
									});
							tableData += ' </li><td class="emp5 col-md-12"><div class="edit md-trigger col-md-6 col-sm-6 hand" id='
								+ this.pkEmp
								+ 'editEmployee onClick="edit_employee('
								+ this.pkEmp + ');"></div>';
							tableData += '  <div class="delete col-md-6 col-sm-6 md-trigger hand " id='
								+ this.pkEmp
								+ '"deleteEmployee" onClick="delete_employee('
								+ this.pkEmp
								+ ');"></div></td></tr> ';
							$('#onetable tbody')
							.html(tableData);

						});

				var oTable = $(
				'div#table-responsive marg30 table:eq(0)')
				.dataTable();
				if (oTable != null) {
					oTable.fnDestroy();
				}
				$('#empcount').val(l);

			});

}

function getYearEMP1(val) {
	var value = val;
	showLoader();
	var l = 0;
	$
	.post(
			'getEmployee',
			{
				isActive : 'N',
				year : value
			},
			function(response) {
				oppResponse = response;
				var tableData = "";
				$('#onetable tbody').html('');
				$
				.each(
						response,
						function(i, j) {
							l++;
							var oppData = JSON
							.stringify(response[i]);
							tableData += '<tr><td class="emp1 ">'
								+ this.empId
								+ ' </td> <td class="emp2 ">'
								+ this.fname
								+ ' '
								+ this.lname
								+ ' </td><td class="emp3">'
								+ this.email + ' </td> ';
							tableData += ' <td class="emp4 dropdown"><div class="serch hand dropdown-toggle" id="menu1" data-toggle="dropdown"></div> <ul class="dropdown-menu" role="menu" aria-labelledby="menu1"> <li id="'
								+ this.pkEmp
								+ 'areacount">';
							var array = (this.industries)
							.split(",");
							var k = 0;
							$
							.each(
									array,
									function(i) {
										tableData += '<label class="col-md-11 col-sm-12"'
											+ ' id ='
											+ array[k]
										+ 'areamap>'
										+ array[++k]
										+ '</label> ';
										k = k + 1;
										if (array[k] == undefined) {
											return false;
										}
									});
							tableData += '</li> <td class="emp5 col-md-12"><button class="edit editnobg md-trigger col-md-6 col-sm-6 hand"  id='
								+ this.pkEmp
								+ 'editEmployee onClick="edit_employee('
								+ this.pkEmp + ');"><i class="glyphicon glyphicon-pencil edit_icon"></i></button>';

							tableData += '  <button class="delete col-md-6 col-sm-6 md-trigger hand deletenobg" id='
								+ this.pkEmp
								+ '"deleteEmployee" onClick="delete_employee('
								+ this.pkEmp
								+ ');"><i class="glyphicon glyphicon-trash delete_icon"></i></button></td></tr> ';
							$('#onetable tbody')
							.html(tableData);

						});
				$('#empcount').val(l);

				var oTable = $(
				'div#table-responsive marg30 table:eq(0)')
				.dataTable();
				if (oTable != null) {
					oTable.fnDestroy();
				}
				hideLoader();

			});
}

function getAreaEMP(sel) {
	debugger;
	var value = sel.value;

	var ar = $("#yearEmp").val();
	var l = 0;
	showLoader();
	$
	.post(
			'getEmployeeArea',
			{

				area : value,
				year : ar
			},
			function(response) {

				hideLoader();
				oppResponse = response;
				var tableData = "";
				$('#onetable tbody').html('');
				$
				.each(
						response,
						function(i, j) {
							l++;
							var oppData = JSON
							.stringify(response[i]);
							tableData += '<tr><td class="emp1">'
								+ this.empId
								+ ' </td> <td class="emp2">'
								+ this.fname
								+ ' '
								+ this.lname
								+ ' </td><td class="emp3">'
								+ this.email + '</td> ';
							tableData += ' <td class="emp4 dropdown"><div class="serch hand dropdown-toggle" id="menu1" data-toggle="dropdown"></div> <ul class="dropdown-menu" role="menu" aria-labelledby="menu1"> <li id="'
								+ this.pkEmp
								+ 'areacount">';
							var array = (this.industries)
							.split(",");
							var k = 0;
							$
							.each(
									array,
									function(i) {
										tableData += '<label class="col-md-11 col-sm-12"'
											+ ' id ='
											+ array[k]
										+ 'areamap>'
										+ array[++k]
										+ '</label> ';
										k = k + 1;
										if (array[k] == undefined) {
											return false;
										}
									});
							tableData += '</li> <td class="emp5 col-md-12"><div class="edit md-trigger col-md-6 col-sm-6 hand"id='
								+ this.pkEmp
								+ 'editEmployee onClick="edit_employee('
								+ this.pkEmp + ');"></div>';
							tableData += '  <div class="delete col-md-6 col-sm-6 md-trigger hand " id='
								+ this.pkEmp
								+ '"deleteEmployee" onClick="delete_employee('
								+ this.pkEmp
								+ ');"></div></td></tr> ';

							$('#onetable tbody')
							.html(tableData);

						});

				var oTable = $(
				'div#table-responsive marg30 table:eq(0)')
				.dataTable();
				if (oTable != null) {
					oTable.fnDestroy();
				}
				$('#empcount').val(l);

			});

}

function callMe() {

	var ar6 = $("#areaId").val();
	var ar4 = $("#yearEmp").val();
	var l = 0;
	showLoader();

	$
	.ajax({
		data : "pkEmp=" + data + "&year=" + ar4 + "&areaAdd=" + ar6,
		url : "empdelete",
		type : "POST",
		success : function(response) {
			swal("Deleted successfully ");
			$('.deleteModal').modal('hide');
			oppResponse = response;
			var tableData = "";
			$('#onetable tbody').html('');
			$
			.each(
					response,
					function(i, j) {
						l++;
						// alert(i+"&&"+j);
						var oppData = JSON
						.stringify(response[i]);
						tableData += '<tr><td class="emp1">'
							+ this.empId
							+ ' </td> <td class="emp2">'
							+ this.fname + ' ' + this.lname
							+ ' </td><td class="emp3">'
							+ this.email + '</td> ';
						tableData += ' <td class="emp4 dropdown"><div class="serch hand dropdown-toggle" id="menu1" data-toggle="dropdown"></div> <ul class="dropdown-menu" role="menu" aria-labelledby="menu1"><li id="'
							+ this.pkEmp + 'areacount">';
						var array = (this.industries)
						.split(",");
						var k = 0;
						$
						.each(
								array,
								function(i) {
									tableData += '<label class="col-md-11 col-sm-12"'
										+ ' id ='
										+ array[k]
									+ 'areamap>'
									+ array[++k]
									+ '</label> ';
									k = k + 1;
									if (array[k] == undefined) {
										return false;
									}
								});
						tableData += ' </li><td class="emp5 col-md-12"><div class="edit md-trigger col-md-6 col-sm-6 hand" id='
							+ this.pkEmp
							+ 'editEmployee onClick="edit_employee('
							+ this.pkEmp + ');"></div>';
						tableData += '  <div class="delete col-md-6 col-sm-6 md-trigger hand " id='
							+ this.pkEmp
							+ '"deleteEmployee" onClick="delete_employee('
							+ this.pkEmp
							+ ');"></div></td></tr> ';

						$('#onetable tbody').html(tableData);

					});
			hideLoader();

			$('#empcount').val(l);

			var oTable = $('div#table-responsive marg30 table:eq(0)')
			.dataTable();
			if (oTable != null) {
				oTable.fnDestroy();
			}
		},
		error : function(e) {
			swal('Error: ' + e);
		}
	});

}

function validateDetails(formid) {
	var count = 0, currEle = null;
	$.each($("#" + formid).find(".must"), function() {

		if ($(this).is("input") && $(this).val().trim().length == 0) {
			count++;
			currEle = this;
			return false;
		} else if ($(this).is("select") && $('select').val() == "") {
			count++;
			currEle = this;
			return false;
		}

	});

	if ($('#' + formid + ' input:checked').length < 1) {
		swal("Please select atleast one Area");
		return false;
	}
	if (count > 0) {
		// swal("Fields Marked with * are Mandatory");
		swal("Please select all the mandatory fields");
		currEle.focus();
		return false;
	}

	return true;

}

function validateEmail(mailele) {
	// var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	/*var mailformat = /^([a-zA-Z+-])+\@(([a-zA-Z])+\.)+([a-zA-Z])+$/; // added
	// to
	// avoid
	// bugs
	var mail = $(mailele).val();
	var mismatch = 0;
	if (mail.length == 0 || mail.match(mailformat)) {
		return true;
	} else {
		// alert("Please Enter Valid Email Id");
		swal("Please Enter Valid Email Id");
		$(mailele).focus();
		return false;
	}*/
return true;
}

function EmailValidation() {

	// alert("Into Email Validation");
	// var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	/*var mailformat = /^([a-z])+\@(([a-z])+\.)+([a-z])+$/; // new
	// regex
	// pattern
	// added
	// to
	// avoid
	// bugs
	var mail = $('#emailIDAdd').val();
	var mismatch = 0;
	if (mail.length == 0 || mail.match(mailformat)) {
		return true;
	} else {
		// alert("Please Enter Valid Email Id");
		// swal("Please Enter Valid Email Id");
		$('#emailIDAdd').focus();
		return false;
	}*/
return true;
}

function EmailValidation2() {

	// alert("Into Email Validation 2");
	// var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	/*var mailformat = /^([a-zA-Z])+\@(([a-zA-Z])+\.)+([a-zA-Z])+$/; // new
	// regex
	// pattern
	// added
	// to
	// avoid
	// bugs
	var mail = $('#emailId1').val();
	var mismatch = 0;
	if (mail.length == 0 || mail.match(mailformat)) {
		return true;
	} else {
		// alert("Please Enter Valid Email Id");
		// swal("Please Enter Valid Email Id");
		$('#emailId1').focus();
		return false;
	}
*/
	return true;
}

function addEmployee() {
	$('#addUpdate').trigger("reset");
	var ar5 = $("#yearEmp").val();
	var ar6 = $("#areaId").val();
	var title = '<span class="glyphicon glyphicon-plus-sign sadd"></span> Add Employee <button type="button" class="close" data-dismiss="modal">&times;</button>';
	var content = '<div class="modal-body"><form id="addUpdate" action="addUpdate" method="POST"><div class="form-group"><label class="col-sm-2 nopad" ><span class="starRed">*</span>Token ID :</label><div class="col-sm-4"><input type="text" class="form-control feld must" maxlength="15" name="empId" ></div>'
		+ '<label class="col-sm-2 nopad">Year :</label><div class="col-sm-4"><input type="hidden" name="areaAdd" value="'
		+ ar6
		+ '"><input type="text" class="form-control feld " name="year" value="'
		+ ar5
		+ '" readonly/></div></div> '
		+ '<div class="clearfix"></div><div class="form-group"><label class="col-sm-2 nopad"><span class="starRed">*</span>First Name :</label><div class="col-sm-4"><input type="text" class="form-control feld must" name="fName" maxlength="15" /></div> '
		+ '<label class="col-sm-2 nopad"><span class="starRed">*</span>Last Name :</label><div class="col-sm-4"><input type="text" class="form-control feld must" name="lName"  maxlength="15"/></div></div> '
		+ '<div class="clearfix"></div><div class="form-group"><label class="col-sm-2 nopad"><span class="starRed">*</span>Email ID :</label><div class="col-sm-4"><input type="text" class="form-control feld must" name="emailId" id="emailIDAdd" /></div></div> '
		+ ' <div class="clearfix"></div><div class="form-group"><label class="col-sm-2 nopad"><span class="starRed">*</span>Select Area:</label><div class="clearfix"></div>';

	$
	.ajax({
		type : "POST",
		url : "getAreasForYear",
		async : false,
		data : {
			selectedYear : ar5
		},
		success : function(data) {
			var areaYearList = jQuery.parseJSON(data);
			$
			.each(
					areaYearList,
					function(idx, obj) {

						content += '<div class="col-md-1 col-sm-1 nopad nopadding_style"><input type="checkbox" id="'
							+ obj.areaDesc
							+ '" name="Area['
							+ idx
							+ '].areaCode" value="'
							+ obj.areaDesc
							+ '"></div><label class="col-md-5 col-sm-5 nopad" >'
							+ obj.areaDesc
							+ '</label>'
							+ '<input type="hidden" name ="Area['
							+ idx
							+ '].areaCode1" value="'
							+ obj.areaCode + '">';
					});

		},
		error : function(xhr, ajaxOptions, throwError) {
			//		alert("Please Try Again");
			swal("Please Try Again");
		}

	});
	content += '</div></div>';
	var footer = '<button type="button" class="btn addbtn" onclick="submitEmployee()">Submit</button><button type="button" class="btn addbtn" data-dismiss="modal">Close</button></form> ';

	$("#Addemployee .modal-header").html(title);
	$("#Addemployee .modal-body").html(content);
	$("#Addemployee .modal-footer").html(footer);
	$('#Addemployee').modal('show');

}

/*function submitConfig()
 {
 var nextDate = new Date();
 alert(nextDate);
 var numberOfDaysToAdd = $('#frequency').val();
 nextDate.setDate(nextDate.getDate() + parseInt(numberOfDaysToAdd));
 var endDate = new Date($('#enddate').val());
 if(nextDate <= endDate)
 {

 $("#disabledInput").val(nextDate);
 alert("data insert successfully");
 $.post('configinsert',$('#addconfig').serialize(),function(response)
 {

 });

 window.location.reload();
 }
 else
 {
 $("#disabledInput").val("endDate is expired");
 }
 }*/
function clearInputs(data) {
	$("#addconfig :input").each(function() {
		$(this).val('');
	});
}

function showLoader() {
	$(".loader").show();
}

function hideLoader() {
	$(".loader").hide();
}