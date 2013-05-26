
$(document).ready(function() {
	$('#login').submit(function() {
		var email = $('#email').val();

		$.ajax({
			type: 		"post",
			url: 		"login.jsp",
			data: 		"email=" + email,
			success:	function(name) {

				$('#reglog').hide();

				if( name == "userfail" )
				{
					
					$("#reglog").html("div id=\"reglog\" class=\"minor\" >" + "Username not found </div>")
				.fadeIn("slow");

				}else
				$("#reglog").html("<div id=\"reglog\" class=\"minor\" >"  + name + "</div>")
				.fadeIn("slow");
			}
			
		});

	return false;
	});
});
