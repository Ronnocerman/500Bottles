<%@ page import="com._500bottles.object.wine.WineQueryResult" %>
<%@ page import="com._500bottles.object.wine.Wine" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.PrintWriter" %>

<section class="wrapper clearfix subview animated" id="quick_search_results" data-anim-out="fadeOutRightBig" data-anim-in="fadeInRightBig">
    <div id="quick_search_results_wine_container" class="wine_grid_container" data-rows="3">
        <ul class="wine_grid">
<%
    WineQueryResult result = (WineQueryResult) request.getAttribute("search_result");

    Iterator<Wine> it = result.getIterator();

    Wine w;

    while(it.hasNext()) {
        w = it.next();
        String image = w.getImage();
        if (image.length() == 0)
            image = "/img/blank_wine_bottle.png";
        %><li class="wine"><%
        %><div class="back"><%= w.getName() %></div><%
        %><div class="wine_image front" style="background-image: url('<%= image %>');"></div><%
        %></li><%
    }
%>
        </ul>
        <div class="next"></div>
        <div class="prev"></div>
    </div>

    <script type="text/javascript">
        _500bottles.gallery.buildGallery("#quick_search_results_wine_container");
    </script>

</section>