<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com._500bottles.manager.*" %>
<%
String email;
email = request.getParameter("email");



if(SessionManager.login(email,)) {

		out.println(/*getUsername(email)*/ "Welcome back, " + email );
} else {
	out.println("userfail");
}
%>
