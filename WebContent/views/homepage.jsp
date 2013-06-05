<%@ include file="/inc/header.jsp" %>

<section class="wrapper clearfix view animated fadeInUp" id="home" data-anim-in="fadeInLeftBig" data-anim-out="fadeOutLeftBig">

    <!-- Your Celler Wines -->
    <div id="cellar_preview_wine_grid" class="wine_grid_container" data-rows="1">
        <h2>Your Cellar</h2>
        <ul class="wine_grid">
            <li class="wine">
                <img src="img/mockup/label_1.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_2.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_3.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_4.png" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_5.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_6.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_4.png" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_5.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_6.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_1.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_2.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_3.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_4.png" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_5.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_6.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_4.png" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_5.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_6.jpeg" />
            </li>
        </ul>
        <div class="next"></div>
        <div class="prev"></div>
    </div>

    <div class="column_66">
        <div class="content" >
            <div class="light_bg with_border shadow " style="height: 270px">
                <div class="bg"></div>
                <h3>Search for wine</h3>
                <form id="wine_search">
                    <input id="wine_search_text" type="text" />
                    <input id="wine_search_submit" type="button" value="search" />
                </form>
                <div class="align_right advanced_link">
                    <a class="light_link box_arrow" href="#">advanced search options</a>
                </div>
            </div>
        </div>
    </div>

    <div class="column_33" style="z-index: 100">
        <div class="content">
            <div class="light_bg shadow with_border">
                <div class="bg"></div>

                <h3>What's in your cellar</h3>
                <div id="canvas" height="200" width="200"></div>

            </div>
        </div>
    </div>

    <div class="clearfix"></div>

    <div id="suggested_wine_grid" class="wine_grid_container" data-rows="1">
        <h2>Suggested Wines</h2>
        <ul class="wine_grid">
            <li class="wine">
                <img src="img/mockup/label_1.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_2.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_3.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_4.png" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_5.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_6.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_4.png" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_5.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_6.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_1.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_2.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_3.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_4.png" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_5.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_6.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_4.png" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_5.jpeg" />
            </li>
            <li class="wine">
                <img src="img/mockup/label_6.jpeg" />
            </li>
        </ul>
        <div class="next"></div>
        <div class="prev"></div>
    </div>

    <div class="clearfix"></div>

    <script type="text/javascript">
        _500bottles.gallery.buildGallery("#cellar_preview_wine_grid");
        _500bottles.gallery.buildGallery("#suggested_wine_grid");
    </script>

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
</script>
