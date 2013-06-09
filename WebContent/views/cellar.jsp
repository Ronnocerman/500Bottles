<%@ page import="com._500bottles.object.wine.WineQueryResult" %>
<%@ page import="com._500bottles.action.CellarAction" %>
<%@ page import="com._500bottles.manager.SessionManager" %>

<section class="wrapper clearfix view animated no_display fadeInUp" id="cellar">

    <%--<section class="column_100 clearfix">--%>
        <%--<div class="content">--%>
            <%--<h1>Cellar</h1>--%>
        <%--</div>--%>
    <%--</section>--%>

    <div id="cellar_wine_grid" class="wine_grid_container" data-rows="3">
        <%
            long user_id = SessionManager.getSessionManager().getLoggedInUser().getUserId();

            WineQueryResult result = CellarAction.getAllWinesFromCellar(user_id);

            request.setAttribute("grid_wines", result);
        %>

        <jsp:include page="/views/wine_grid/wine_grid.jsp" flush="true" />
        <div class="next"></div>
        <div class="prev"></div>
    </div>

    <script type="text/javascript">
        _500bottles.gallery.buildGallery("#cellar_wine_grid");
    </script>
</section>