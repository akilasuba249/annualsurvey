<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->



<!-- <script src="assets/js/jquery.mCustomScrollbar.min.js"></script> -->
<!-- <script src="assets/js/jquery.mCustomScrollbar.concat.min.js"></script> -->

      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
       <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.js"></script>
       <script type="text/javascript" src="https://malihu.github.io/custom-scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
      <script src="assets/js/custom.js?ver=3"></script>
   </body>
</html>


<script>
/* function sticky_relocate() {
    var window_top = $(window).scrollTop();
    var div_top = $('#sticky-anchor').offset().top;
    if (window_top > div_top) {
        $('#sticky').addClass('stick');
        $('#sticky-anchor').height($('#sticky').outerHeight());
    } else {
        $('#sticky').removeClass('stick');
        $('#sticky-anchor').height(0);
    }
} */


/* function sticky_relocate2() {
	//debugger;
    var window_top = $(window).scrollTop();
    if($('#sticky-anchor2').offset() != undefined){
    var div_top = $('#sticky-anchor2').offset().top;
    var div_bottom = $('#sticky-anchor3').offset().top;
    
    if (window_top > div_top) {
        $('#stickoption').addClass('stick1');
        $('#sticky-anchor2').height($('#stickoption').outerHeight()-400);
    } 
    if(window_top < div_top){
    	 $('#stickoption').removeClass('stick1');
         $('#sticky-anchor2').height(0);
    }
    if (window_top > div_bottom) {
        $('#stickoption').removeClass('stick1');
        $('#sticky-anchor2').height(0);
    }
   }
} */



/* $(function() {
    $(window).scroll(sticky_relocate);
    sticky_relocate();
}); */
    /* if (navigator.userAgent.match(/(iPod|iPhone|iPad|Android)/)) {
        $(window).scroll(sticky_relocate2);
        sticky_relocate2();
    } */
    
    /*---------Comment for checkbox issue----------*/



/*---------Comment for checkbox issue----------*/

/* var dir = 1;
var MIN_TOP = 200;
var MAX_TOP = 350;

function autoscroll() {
    var window_top = $(window).scrollTop() + dir;
    if (window_top >= MAX_TOP) {
        window_top = MAX_TOP;
        dir = -1;
    } else if (window_top <= MIN_TOP) {
        window_top = MIN_TOP;
        dir = 1;
    }
    $(window).scrollTop(window_top);
    window.setTimeout(autoscroll, 100);
} *//*---------Comment for checkbox issue----------*/

</script>
   <script type="text/javascript">
        $(function () {
            $(".comment").click(function () {
            	//alert("hii");
            	//debugger;
                if ($(this).is(":checked")) {
                    $("#dv"+this.id+"").show();                   
                } else {
                    $("#dv"+this.id+"").hide();
                }
            });
        });
    </script>
    <script type="text/javascript">
      $(".comment2").mouseleave(function(){
          var a=$('#'+this.id+'text').val();
         $('.'+this.id+'Resp').val(a);             
      });
     
</script>


 <script type="text/javascript">
      $("#sector").change(function(){
          var sect=$('#sector').val();
          if(sect=="10")
          {
        	  $("#otherSector").show();
        	  $("#otherBusiness").show();
              }
          else
              {
        	  $("#otherSector").hide();
        	  $("#otherBusiness").hide();
               }
         /*  if(sect=="6")
              {
        	  $("#otherBusiness").show();             
              } */
          $
			.post(
					'getBusinessDetails',
					{
						sect : sect
					},
					function(response) {
						$("#business").empty();
						$
						.each(
								response,
								function(idx, obj) {
                               
									$("#business").append('<option value="'+obj.businessId+'">'+obj.businessName+'</option>');
								});
                        									
					});
		});          

      $("#business").change(function(){
          var bus=$('#business').val();          
    	  if(bus=="4" || bus=="8"||bus=="33"||bus=="27"||bus=="38"||bus=="28"||bus=="29"||bus=="25")
        	  {//27,38,28,29,25
        	  $("#otherBusiness").show();              
        	  }
    	  else
        	  {
    		  $("#otherBusiness").hide();              
        	  
        	  }
      });
     
</script>

   </body>
</html>