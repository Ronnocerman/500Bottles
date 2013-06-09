<%@ page import="com._500bottles.object.wine.WineQueryResult" %>
<%@ page import="com._500bottles.action.CellarAction" %>
<%@ page import="com._500bottles.manager.SessionManager" %>

<section id="home_subview" class="subview animated" data-anim-out="fadeOutLeftBig" data-anim-in="fadeInLeftBig">
    <!-- Your Cellar Wines -->
    <h2>Your Cellar</h2>

    <div id="cellar_preview_wine_grid" class="wine_grid_container" data-rows="1">

        <%
            long user_id = SessionManager.getSessionManager().getLoggedInUser().getUserId();

            WineQueryResult result = CellarAction.getAllWinesFromCellar(user_id);

            request.setAttribute("grid_wines", result);
        %>

        <jsp:include page="/views/wine_grid/wine_grid.jsp" flush="true" />

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
<% int[] arr = CellarAction.getTypeQuantities(); %>
    <div class="column_33" id="cellar_summary" style="z-index: 100">
        <div class="content">
            <div class="light_bg shadow with_border">
                <div class="bg"></div>

                <h4>What's in your cellar</h4>
                <canvas id="canvas" height="190" width="200"></canvas>
                <div id="chartSide">
                    <span class="tooltip_home"><a class="red">red</a><div class="tooltip_homeContent"><%=arr[0]%> bottles (<%=(int)(100*((float)arr[0]/(arr[0]+arr[1]+arr[2]+arr[3])))%>%)</div></span> |
                    <span class="tooltip_home"><a class="white">white</a><div class="tooltip_homeContent"><%=arr[1]%> bottles (<%=(int)(100*((float)arr[1]/(arr[0]+arr[1]+arr[2]+arr[3])))%>%)</div></span> |
                    <span class="tooltip_home"><a class="rose">ros&eacute;</a><div class="tooltip_homeContent"><%=arr[2]%> bottles (<%=(int)(100*((float)arr[2]/(arr[0]+arr[1]+arr[2]+arr[3])))%>%)</div></span> |
                    <span class="tooltip_home"><a class="other">other</a><div class="tooltip_homeContent"><%=arr[3]%> bottles (<%=(int)(100*((float)arr[3]/(arr[0]+arr[1]+arr[2]+arr[3])))%>%)</div></span>
                </div>

            </div>
        </div>
    </div>

    <div class="clearfix"></div>

    <div id="suggested_wine_grid" class="wine_grid_container" data-rows="1">
        <h2>Suggested Wines</h2>
        <ul class="wine_grid">
            <li class="wine">

                <div class="front"><img src="img/mockup/label_1.jpeg" /></div>
                <div class="back">back test</div>

            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_2.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_3.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_4.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_5.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_6.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_1.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">

                <div class="front"><img src="img/mockup/label_1.jpeg" /></div>
                <div class="back">back test</div>

            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_2.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_3.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_4.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_5.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_6.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_1.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">

                <div class="front"><img src="img/mockup/label_1.jpeg" /></div>
                <div class="back">back test</div>

            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_2.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_3.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_4.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_5.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_6.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_1.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">

                <div class="front"><img src="img/mockup/label_1.jpeg" /></div>
                <div class="back">back test</div>

            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_2.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_3.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_4.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_5.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_6.jpeg" /></div>
                <div class="back">back test</div>
            </li>
            <li class="wine">
                <div class="front"><img src="img/mockup/label_1.jpeg" /></div>
                <div class="back">back test</div>
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
