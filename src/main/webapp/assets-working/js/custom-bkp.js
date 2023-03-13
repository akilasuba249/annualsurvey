//TOGGLING NESTED ul
$(document).on('click', ".drop-down .selected a", function () {
	$('.options ul').hide();
	$(this).parents('.drop-down').find('.options ul').toggle();
	// $(".drop-down .options ul").toggle();
});

//SELECT OPTIONS AND HIDE OPTION AFTER SELECTION
$(document).on('click', ".drop-down .options ul li a", function () {
	var text = $(this).html();
	$(this).parents('.drop-down').find('.selected a span').html(text);
	// $(".drop-down .selected a span").html(text);
	$(this).parents('.drop-down').find('.options ul').hide();
});

//HIDE OPTIONS IF CLICKED ANYWHERE ELSE ON PAGE
$(document).bind('click', function (e) {
	var $clicked = $(e.target);
	if (!$clicked.parents().hasClass("drop-down"))
		$(".drop-down .options ul").hide(100);
});


//TOGGLING NESTED ul
$(".drop-down .selected a").click(function () {
	$(".drop-down .options ul").toggle();
});

$('.profile-border').click(function () {
	$('.accountbox').slideToggle("slow");
});


$(document).ready(function () {

	var winW = $(window).width();
	var winH = $(window).height();

	if (winW < 996) {

		$(document).on('click', '.survey_heading_div li', function () {

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





   $(document).on('click', ' .survey_heading_div li', function() {

        $(' .survey_heading_div li').removeClass('active');
        $(this).addClass('active');
        var data_goto = '#' + $(this).attr('data-goto');
        $('.scroll_horizontal').mCustomScrollbar('scrollTo', data_goto);


    });
	$('.scroll_horizontal').mCustomScrollbar({
		axis: "x",
		advanced: {
			autoExpandHorizontalScroll: true //optional (remove or set to false for non-dynamic/static elements)
		}

	});


	var winW = $(window).width();
	var winH = $(window).height();


	$(window).scroll(function () {
		var Scroll_posY = $(this).scrollTop();
		$('.survey_heading_div li').each(function (e) {
			if (Scroll_posY >= $('#' + $(this).attr('data-goto')).offset().top - 400) {
				$('.survey_heading_div li').removeClass('active');
				$(this).addClass('active');
			}
			setTimeout(() => {
        $('.scroll_horizontal').mCustomScrollbar("scrollTo", '#' + $('.survey_heading_div li.active').attr('id'), {
          timeout: 500,
  
        });
      }, 200);
		});
		// if (winW < 991) {
		//     var length_next = winW * ($('.left_div_MP .survey_heading_div li.active').prevAll('li').length);
		//     console.log(length_next);
		//     $('.left_div_MP .scroll_vertical').scrollLeft(length_next);
		// }
	});;


	$(document).on('click', ' .survey_heading_div li', function () {

		$(' .survey_heading_div li').removeClass('active');
		$(this).addClass('active');
		var data_goto = '#' + $(this).attr('data-goto');

		$('html, body').animate({
			scrollTop: $(data_goto).offset().top - 300
		}, 1500);
	});


	$('.center_div_MP .inner_content_div').each(function () {
		var q_count = $(this).find('.question_ul_inner li').not('.sub-que').length;
		var get_id = $(this).attr('data-li');
		//$('.survey_heading_div li#' + get_id).find('i').text(q_count);
		$('.survey_heading_div li.' + get_id).find('i').text(q_count);
	});


	var length_v = $('.center_div_MP .inner_content_div .question_ul_inner li.main-que').length;
	$('.cout_span i').text(length_v);


	// $(document).on('click', '.center_div_MP .inner_content_div .question_ul_inner > li label', function() {
	$(document).on('click', '.center_div_MP .inner_content_div .question_ul_inner .switch-field  label', function () {


		if ($(this).parents('.main-que').find('.switch-field li').length - 1 == $(this).parents('.main-que').find('.switch-field li.active').length) {


			$(this).parents('.main-que').addClass('ans_completed');
		}


		$(this).closest('li').addClass('ans_complete');
		var ans_length = $('.main-que.ans_complete, .main-que.ans_completed').length;
		$('.cout_span span').text(ans_length);

		// var perc_calc = (ans_length / length_v) * 100;
		// $('.center_div_MP .overall_div .progress_bar .bar').css('width', perc_calc + '%');


		var get_id = $(this).parents('.inner_content_div').attr('data-li');
		var parents_length = $(this).parents('.inner_content_div').find('.ans_complete').length;
		var t_count = $('.survey_heading_div li#' + get_id).find('i').text();
		$('.survey_heading_div li#' + get_id).find('small span').text(parents_length);

		var perc_calc2 = (parents_length / t_count) * 100;
		$('.survey_heading_div li#' + get_id).find('.progress .progress-bar').css('width', perc_calc2 + '%');


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


	$(document).on('click', '.skip, .ok-btn', function () {


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
		$(this).parents('.add-comment').find('.ok-btn').fadeIn();
		$('.skip').fadeOut();
		$(this).attr('placeholder', 'Add comments');

	});


	$(document).on('click', '.save-survey', function () {
		$(this).hide();
		$(this).next().show();

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