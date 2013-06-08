<%@ page import="com._500bottles.object.wine.WineQueryResult" %>
<%@ page import="com._500bottles.object.wine.Wine" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com._500bottles.manager.SessionManager" %>
<%@ page import="com._500bottles.manager.FavoritesManager" %>

<section class="wrapper clearfix subview animated" id="quick_search_results" data-anim-out="fadeOutRightBig" data-anim-in="fadeInRightBig">
    <div id="quick_search_results_wine_container" class="wine_grid_container" data-rows="3">

    <%
        WineQueryResult result = (WineQueryResult) request.getAttribute("search_result");
        request.setAttribute("grid_wines", result);
    %>

    <%@ include file="wine_grid/wine_grid.jsp" %>

    </div>

    <script type="text/javascript">
        _500bottles.gallery.buildGallery("#quick_search_results_wine_container", true);
    </script>

</section>