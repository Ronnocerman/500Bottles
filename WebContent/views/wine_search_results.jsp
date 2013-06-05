<%@ page import="com._500bottles.object.wine.WineQueryResult" %>
<%@ page import="com._500bottles.object.wine.Wine" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.PrintWriter" %>

<%
    WineQueryResult result = (WineQueryResult) request.getAttribute("search_result");

    Iterator<Wine> it = result.getIterator();

    Wine w;
    PrintWriter writer = response.getWriter();

    int max_results = 9, i = 0;

    while(it.hasNext() && i++ < max_results) {
        w = it.next();

        writer.print("<div class=\"wine\">");
        writer.println("<span class=\"name\">" + w.getName() + "</span>");
        writer.println("<div class=\"image\" style=\"background-image: url('" + w.getImage() + "');\"></div>");
        writer.println("</div>");
    }
%>
