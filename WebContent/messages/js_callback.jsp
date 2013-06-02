<%
    String callback = (String) request.getAttribute("callback");
%><%= callback + "()" %>