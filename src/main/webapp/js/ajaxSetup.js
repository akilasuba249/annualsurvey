$(document).ready(function() {	
	/** CODE FOR APPLYING THE AJAX SEND REQUEST WITH ANY AJAX CALL
	 *  FOR CSRF TOKEN.
	 */
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$.ajaxSetup({
	    beforeSend: function(xhr) {
	        //xhr.setRequestHeader('X-CSRF-TOKEN', token);
	        xhr.setRequestHeader(header, token);
	    }
	});
	
	

});