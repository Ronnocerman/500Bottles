<%@ include file="/inc/header.jsp" %>

<section class="wrapper clearfix view animated fadeInUp" id="home" data-anim-in="fadeInLeftBig" data-anim-out="fadeOutLeftBig">

    <%@ include file="/views/homepage/main.jsp" %>

</section>

<script src="js/header.js" type="text/javascript"></script>
<script src="js/homepage.js" type="text/javascript"></script>
<script src="js/gallery.js" type="text/javascript"></script>
<script src="js/chart.js" type="text/javascript"></script>


<% int[] array = CellarAction.getTypeQuantities(); %>
<script>

	var doughnutData = [{
						 value: <%=array[0]%>,
						 color:"#A60000"}, //red 
					
						{
						 value : <%=array[1]%>,
						 color : "#DEDEDE"}, //white 
					
						{
						 value : <%=array[2]%>,
						 color : "#FFB6C1"}, //rosé 
					
						{
						 value : <%=array[3]%>,
						 color : "#372C2C"}]; //other 

	var myDoughnut = new Chart(document.getElementById("canvas").getContext("2d")).Doughnut(doughnutData);
	
	$(function() {
		$(".tooltip_home").hover(function() {
			var tooltip_home = $("> div", this).show();
			var pos = tooltip_home.offset();
			tooltip_home.hide();
			var right = pos.left + tooltip_home.width();
			var pageWidth = $(document).width();
			if (pos.left < 0) {
				tooltip_home.css("marginLeft", "+=" + (-pos.left) + "px");
			}
			else if (right > pageWidth) {
				tooltip_home.css("marginLeft", "-=" + (right - pageWidth));
			}
			tooltip_home.fadeIn();
		}, function() {
			$("> div", this).fadeOut(function() {$(this).css("marginLeft", "");});
		});
	});
</script>
