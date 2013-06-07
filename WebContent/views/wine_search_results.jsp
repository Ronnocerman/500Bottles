<%@ page import="com._500bottles.object.wine.WineQueryResult" %>
<%@ page import="com._500bottles.object.wine.Wine" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.PrintWriter" %>

<section class="wrapper clearfix subview animated" id="quick_search_results" data-anim-out="fadeOutRightBig" data-anim-in="fadeInRightBig">
    <div class="wine_grid">
<%
    WineQueryResult result = (WineQueryResult) request.getAttribute("search_result");

    Iterator<Wine> it = result.getIterator();

    Wine w;

    System.err.println("wines returned: " + result.getResultsCount());

    while(it.hasNext()) {
        w = it.next();
        System.err.println(w.toString());
        String image = w.getImage();
        if (image.length() == 0)
            image = "/img/blank_wine_bottle.png";
        %><div class="wine"><%
        %><span style="display: none" class="name"><%= w.getName() %></span><%
        %><div class="wine_image" style="background-image: url('<%= image %>');"></div><%
        %></div><%
    }
%>
    </div>
</section>