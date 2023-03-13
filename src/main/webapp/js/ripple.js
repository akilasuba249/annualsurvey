    ////////////____Input Focus___//////////////////

    $('.formcontrol').focusout(function() {
        $('.formgroup').removeClass('focus');
    });
    $('.formcontrol').focus(function() {
        $(this).closest('.formgroup').addClass('focus');
    });

    /// Input Kepress Filled  Focus
    $('.formcontrol').keyup(function() {
    	if($(this).find('option')!=undefined && $(this).find('option').length>0 && $(this).find("option:selected").text()==""){
    		
    		$(this).closest('.formgroup').removeClass('filled');
    		
    	}else if($(this).val().length > 0){
            $(this).closest('.formgroup').addClass('filled');
        }

        else{
            $(this).closest('.formgroup').removeClass('filled');
        }
    });
    /// Input mousedown Filled  Focus gauri
    $('.formcontrol').mousedown(function() {
    	if($(this).find('option')!=undefined && $(this).find('option').length>0 && $(this).find("option:selected").text()==""){
    		
    		$(this).closest('.formgroup').removeClass('filled');
    		
    	}else if($(this).val().trim().length > 0){
            $(this).closest('.formgroup').addClass('filled');
        }

        else{
            $(this).closest('.formgroup').removeClass('filled');
        }
    });
    //For select drop-down - Ashutosh
    $("select.formcontrol").change(function() {
    	if($(this).find('option')!=undefined && $(this).find('option').length>0 && $(this).find("option:selected").text()==""){
    		
    		$(this).closest('.formgroup').removeClass('filled');
    		
    	}else if($(this).val().trim().length > 0){
            $(this).closest('.formgroup').addClass('filled');
        }

        else{
            $(this).closest('.formgroup').removeClass('filled');
        }
    });
    //For date picker - Ashutosh
    function checkForDate(){
//    	alert("Date Selected ...");
    	alert("checkfordate is called")
    	debugger;
    	$(this).closest('.formgroup').addClass('filled');
    	//$("#toDate").datepicker("option","minDate", selected);
    	 //$("#fromDate").datepicker("option", selected);
    	 /*$(".fandtdate").html($("#fromDate").val()+" to "+$("#toDate").val());*/
    }
    /// Input load Filled  Focus
    $('.formcontrol').load(function() {
    	if($(this).val().length == 0){
            $(this).closest('.formgroup').addClass('filled');
        }

        else{
            $(this).closest('.formgroup').removeClass('filled');
        }
    });
    /// Input Check Filled Focus
    var $formControl = $('.formcontrol');
    var values = {};
    var validate =    $formControl.each(function() {
//    	console.log($(this));
    	if($(this).val()!=null && $(this).val().trim().length > 0){
            $(this).closest('.formgroup').addClass('filled');
           
        }
        else{
            $(this).closest('.formgroup').removeClass('filled');
        }
    });

// Button switching

//$('.caption').click(function(){
//  $(this).closest('.log-form').addClass('open');
//});
//
//
//$('.close').click(function(){
//  $(this).closest('.log-form').removeClass('open');
//});
//Ripple Effect
//$(".btn").click(function(e) {
//
//  // Remove olds ones
//  $(".ripple").remove();
//
//  // Setup
//  var posX = $(this).offset().left,
//      posY = $(this).offset().top,
//      buttonWidth = $(this).width(),
//      buttonHeight = $(this).height();
//
//  // Add the element
//  $(this).prepend("<span class='ripple'></span>");
//
//  // Make it round!
//  if (buttonWidth >= buttonHeight) {
//    buttonHeight = buttonWidth;
//  } else {
//    buttonWidth = buttonHeight;
//  }
//
//  // Get the center of the element
//  var x = e.pageX - posX - buttonWidth / 2;
//  var y = e.pageY - posY - buttonHeight / 2;
//
//  // Add the ripples CSS and start the animation
//  $(".ripple").css({
//    width: buttonWidth,
//    height: buttonHeight,
//    top: y + 'px',
//    left: x + 'px'
//  }).addClass("rippleEffect");
//});