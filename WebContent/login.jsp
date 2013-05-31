<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="com._500bottles.action.UserAction" %>
<%
String email = request.getParameter("email");
char[] password = request.getParameter("password").toCharArray();

if(UserAction.login("Hello","Hello".toCharArray())) {

		out.println("Welcome back, " + email );
} else {
	out.println("userfail");
}
%>
