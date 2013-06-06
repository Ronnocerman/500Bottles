<%@ include file="/inc/header.jsp" %>

<section class="wrapper clearfix view animated fadeInUp" id="home" data-anim-in="fadeInLeftBig" data-anim-out="fadeOutLeftBig">

    <%@ include file="/views/homepage/main.jsp" %>

</section>

<script src="js/header.js" type="text/javascript"></script>
<script src="js/homepage.js" type="text/javascript"></script>
<script src="js/gallery.js" type="text/javascript"></script>
<script src="js/chart.js" type="text/javascript"></script>

<script>
	var doughnutData = [{
						 value: 130,
						 color:"#A60000"},
					
						{
						 value : 80,
						 color : "#DEDEDE"},
					
						{
						 value : 100,
						 color : "#FFB6C1"},
					
						{
						 value : 40,
						 color : "#372C2C"}];

	var myDoughnut = new Chart(document.getElementById("canvas").getContext("2d")).Doughnut(doughnutData);
	
	$(function() {
		$(".tooltip").hover(function() {
			var tooltip = $("> div", this).show();
			var pos = tooltip.offset();
			tooltip.hide();
			var right = pos.left + tooltip.width();
			var pageWidth = $(document).width();
			if (pos.left < 0) {
				tooltip.css("marginLeft", "+=" + (-pos.left) + "px");
			}
			else if (right > pageWidth) {
				tooltip.css("marginLeft", "-=" + (right - pageWidth));
			}
			tooltip.fadeIn();
		}, function() {
			$("> div", this).fadeOut(function() {$(this).css("marginLeft", "");});
		});
	});
</script>
