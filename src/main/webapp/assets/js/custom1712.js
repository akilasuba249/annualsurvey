//TOGGLING NESTED ul
$(document).on('click', ".drop-down .selected a", function() {

    $(this).parents('.drop-down').find('.options ul').toggle();

});

//SELECT OPTIONS AND HIDE OPTION AFTER SELECTION
$(document).on('click', ".drop-down .options ul li a", function() {
    var text = $(this).html();
    $(this).parents('.drop-down').find('.selected a span').html(text);
    // $(".drop-down .selected a span").html(text);
    $(this).parents('.drop-down').find('.options ul').hide();
});

//HIDE OPTIONS IF CLICKED ANYWHERE ELSE ON PAGE
$(document).bind('click', function(e) {
    var $clicked = $(e.target);
    if (!$clicked.parents().hasClass("drop-down"))
        $(".drop-down .options ul").hide(100);
});

$(document).on('click touchstart', function(e) {
    var container = $(".profile-border");
    if (!container.is(e.target) && container.has(e.target).length === 0) {
        // $('.accountbox').css('display','none');
        $('.accountbox').slideUp("slow");

    }
});

//TOGGLING NESTED ul


$('.profile-border').click(function() {
    $('.accountbox').slideToggle("slow");
});


$(document).ready(function() {
	
	//Modified by nikita start
	var ans_length = $('#responseStart .checkedval').length
	$('.cout_span span').text(ans_length);
	
	var length_v = $('.center_div_MP .inner_content_div .question_ul_inner li.main-que').length;
	$('.cout_span i').text(length_v);
	//Modified by nikita end
	
    function alignModal() {
        var modalDialog = $(this).find(".modal-dialog");
        // Applying the top margin on modal dialog to align it vertically center
        //modalDialog.css("margin-top", Math.max(0, ($(window).height() - modalDialog.height()) / 2));
    }
    // Align modal when it is displayed
    $(".modal").on("shown.bs.modal", alignModal);

    $(window).on("resize", function() {

        $(".modal:visible").each(alignModal);
    });

    var winW = $(window).width();
    var winH = $(window).height();

    if (winW < 996) {

        $(document).on('click', '.survey_heading_div li', function() {

            if ($(this).parents().hasClass('open_li')) {
                $('.survey_heading_div').removeClass('open_li').find('li').slideUp();

            } else {
                $(this).parent().addClass('open_li').find('li').slideDown();

            }
        });
    }


    // $(document).on('click', '.survey-top-heading li.active', function() {


    //  $(this).siblings().slideUp();

    //   });

    //     $(document).on('click', ' .survey_heading_div li', function() {

    //         $(' .survey_heading_div li').removeClass('active');
    //         $(this).addClass('active');
    //         var data_goto = '#' + $(this).attr('data-goto');
    //         $('.scroll_horizontal').mCustomScrollbar('scrollTo', data_goto);


    //     });
    $('.scroll_horizontal').mCustomScrollbar({
        axis: "x",
        advanced: {
            autoExpandHorizontalScroll: true //optional (remove or set to false for non-dynamic/static elements)
        }

    });

    var winW = $(window).width();
    var winH = $(window).height();

    var left_offset = $('#left_pad_mar').offset().left + 30;
    var left_offset_mobile = $('#left_pad_mar').offset().left + 0;
    if (($(window).width() < 767)) {
        $('.survey_heading_div li#li_1').css('margin-left', left_offset_mobile);
    } else {
        $('.survey_heading_div li#li_1').css('margin-left', left_offset);
    }
    $(window).scroll(function() {
        var Scroll_posY = $(this).scrollTop();
        $('.inner_content_div').each(function() {
            if (Scroll_posY >= $('#' + $(this).attr('id')).offset().top - 400) {
                if ($('.inner_content_div').hasClass('inner_ans_completed')) {
                    $('.inner_content_div').addClass('active');
                } else {
                    $('.inner_content_div').removeClass('active');
                }
                $(this).addClass('active');
            } else {

            }
        });
        
        // Header tabs
        $('.survey_heading_div li').each(function(e) {
            if (winW > 1025) {
                if (Scroll_posY >= $('#' + $(this).attr('data-goto')).offset().top - 400) {
                    $('.survey_heading_div li').removeClass('active');
                    $(this).addClass('active');
                }

            } else {
                if (Scroll_posY >= $('#' + $(this).attr('data-goto')).offset().top - 200) {
                    $('.survey_heading_div li').removeClass('active');
                    $(this).addClass('active');
                }
            }
            setTimeout(() => {
                var scroll__To = (($('.survey_heading_div li.active').prev('li').attr('data-index')) * 220);
                if ($('.survey_heading_div li#li_1').hasClass('active')) {
                    $('.scroll_horizontal').mCustomScrollbar("scrollTo", 0, {
                        timeout: 500,

                    });
                } else {
                    $('.scroll_horizontal').mCustomScrollbar("scrollTo", scroll__To, {
                        timeout: 500,

                    });
                }

            }, 200);
        });
        // if (winW < 991) {
        //     var length_next = winW * ($('.left_div_MP .survey_heading_div li.active').prevAll('li').length);
        //     console.log(length_next);
        //     $('.left_div_MP .scroll_vertical').scrollLeft(length_next);
        // }
    });;


    $(document).on('click', '.survey_heading_div li', function() {

        $('.survey_heading_div li').removeClass('active');
        $(this).addClass('active');
        var data_goto = '#' + $(this).attr('data-goto');

        if (winW > 768) {
            $('html, body').animate({
                scrollTop: $(data_goto).offset().top - 350
            }, 1500);
        } else {
            $('html, body').animate({
                scrollTop: $(data_goto).offset().top - 150
            }, 1500);
        }
    });


    $('.center_div_MP .inner_content_div').each(function() {
        var q_count = $(this).find('.question_ul_inner li').not('.sub-que').length;
        var get_id = $(this).attr('data-li');
        $('.survey_heading_div li#' + get_id).find('i').text(q_count);
        var ans_length = $('.main-que.ans_complete, .main-que .checkedval').length;       //added by nikita
    });

	/*var length_v = $('.center_div_MP .inner_content_div .question_ul_inner li.main-que').length;
	$('.cout_span i').text(length_v+'NNN');*/
	//$('.cout_span i').text(length_v);

    //Modified by nikita start
	// $(document).on('click', '.center_div_MP .inner_content_div .question_ul_inner > li label', function() {
	$(document).on('click', '.center_div_MP .inner_content_div .question_ul_inner .switch-field  label', function () {

		if ($(this).parents('.main-que').find('.switch-field li').length - 1 == $(this).parents('.main-que').find('.switch-field li.active').length) {
			$(this).parents('.main-que').addClass('ans_completed');
			$('.inner_content_div').addClass('inner_ans_completed');
		}

		$(this).closest('li').addClass('ans_complete');
		//$(this).closest('li').addClass('checkedval');
		$(this).closest('.main-que').addClass('checkedval');
		///var ans_length = $('.main-que.ans_complete, .main-que.ans_completed').length;
	    ans_length = $('#responseStart .checkedval').length;
	 	$('.cout_span span').text(ans_length);
	 	
		var get_id = $(this).parents('.inner_content_div').attr('data-li');http://localhost:1080/annualsurvey/urlLogin?username=NoDUsBMbOydl7CrY6kX1xqDhAhJ9lnGeVZ6nAAfVp70%3D
		
		var parents_length = $(this).parents('.inner_content_div').find('.ans_complete').length;
		
		var t_count = $('.survey_heading_div li#' + get_id).find('i').text();
		//$(this).parents().find('.inner_content_div').addClass('groupno'+t_count);
		$(this).parents('.inner_content_div').addClass('allanswered'+t_count);   //added by nikita
		
		//$('.survey_heading_div li#' + get_id).find('small span').text(parents_length);

		//var perc_calc2 = (parents_length / t_count) * 100;
		var perc_calc2 = (parents_length / t_count) * 100;
		$('.survey_heading_div li#' + get_id).find('.progress .progress-bar').css('width', perc_calc2 + '%');
		
		$('.survey_heading_div li#' + get_id).find('.progress .progressbarwidth').val(perc_calc2);
		
		//Modified by nikita end
		
		var inner_content_div = $(".inner_content_div");
		$(".inner_content_div li").removeClass('last_click');
		$(".inner_content_div li:last-child").addClass('last_click');
		if ($(this).parents('li').hasClass('last_click')) {

			$('html, body').animate({
				scrollTop: $(this).parents('.inner_content_div').next($(inner_content_div)).offset().top - 550
			}, 1500);
			$(this).parents('.inner_content_div').next('.inner_content_div').find('.question_ul_inner>li:first-child').addClass('active');

		} else {
			$('html, body').animate({
				scrollTop: $(this).parents('li').next('li').offset().top - 330
				
			}, 1500);
			$(this).parents('li').next('li').addClass('active');
			
		}
	});

	//Modified by nikita start
	$(document).on('click', '.skip, .ok-btn', function () {
		debugger;
		$(this).closest('li').removeClass('checkedval');
		var l = $(this).parent().find('textarea').val().trim().length;
		console.log($(this).parent().find('textarea').val());
		
		//alert('lentghkkkkkkkkkkkk'+l);
		if(l >0){
			$(this).closest('li').addClass('checkedval');
		}
		ans_length = $('#responseStart .checkedval').length;
		
		$('.cout_span span').text(ans_length);
		//Modified by nikita end
		
		if ($(this).parents('li').hasClass('last_click')) {
			$('html, body').animate({
				scrollTop: $(this).parents('.inner_content_div').next('.inner_content_div').offset().top - 330
			}, 1500);
			$(this).parents('.inner_content_div').next('.inner_content_div').find('.question_ul_inner>li:first-child').addClass('active');

		} else {
			$('html, body').animate({
				scrollTop: $(this).parents('li').next('li').offset().top - 330
			}, 1500);
			$(this).parents('li').next('li').addClass('active');

		}

	});

	$(document).on('focus', '.add-comment textarea', function () {
		//$(this).parents('.add-comment').find('.ok-btn').fadeIn();
		//$('.skip').fadeOut();
		$(this).attr('placeholder', 'Add comments');

	});


	$(document).on('click', '.save-survey', function () {
		//$(this).hide();
		//$(this).next().show();

	});


	var readURL = function (input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function (e) {
				$(".profile-pic").attr("src", e.target.result);
			};

			reader.readAsDataURL(input.files[0]);
		}
	};

	$(".file-upload").on("change", function () {
		readURL(this);
	});

	$(".upload-button").on("click", function () {
		$(".file-upload").click();
	});
});


$(function () {
	$('#datepicker1').datepicker({
		dateFormat: 'dd/mm/yy'
	});
});


$(".show-password").on('click', function () {


	if ($('.input-psswd').attr('psswd-shown') == 'false') {
		$('.input-psswd').removeAttr('type');
		$('.input-psswd').attr('type', 'text');
		$('.input-psswd').removeAttr('psswd-shown');
		$('.input-psswd').attr('psswd-shown', 'true');
		$(this).addClass('hide-password');
	} else {

		$('.input-psswd').removeAttr('type');
		$('.input-psswd').attr('type', 'password');

		$('.input-psswd').removeAttr('psswd-shown');
		$('.input-psswd').attr('psswd-shown', 'false');
		$(this).removeClass('hide-password');

	}
});

// $('.scroll_horizontal').mCustomScrollbar({
//            axis: "x"
//        });

$(document).ready(function () {
	$(document).on('click', '.survey-top-heading li', function () {
		$('.survey-top-heading li').removeClass('active');
		$(this).addClass('active');

	});
});

//Added by nikita
/*function showcount(ans_length) {
	//var checkvalcount = ans_length
    alert('Answered clicked'+ans_length);
	//ans_length =ans_length+1;
	//$('.cout_span i').text(checkvalcount);
	$('.cout_span span').text(ans_length);

}
*/