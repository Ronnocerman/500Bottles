<%@ page import="com._500bottles.object.wine.WineQueryResult" %>
<%@ page import="com._500bottles.object.wine.Wine" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.io.PrintWriter" %>

<%
    WineQueryResult result = (WineQueryResult) request.getAttribute("search_result");

//    Iterator<Wine> it = result.getIterator();
//
//    Wine w;
//    PrintWriter writer = response.getWriter();
//
//    while(it.hasNext()) {
//        w = it.next();
//        writer.println(w.getName());
//    }
%>
