<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="com._500bottles.action.UserAction" %>
<%@ page import="com._500bottles.action.UserAction" %>

<%//User Action Login returns a null rather than a boolean for some reason %>

<%//String email = request.getParameter("email");%>
<%//String password = request.getParameter("password"); %>
<% //ServletContext context = getServletContext(); %>
<%/*
if(UserAction.login(email, password.toCharArray()))
{

	context.setAttribute("loggedin", "true");

} */%>



<%//Load Servlet context global variables %>
<% ServletContext context = getServletContext(); %>
<%//Store the is loggedin %>
<%String loggedin = (String)context.getAttribute("loggedin"); %>

<%//context.setAttribute("loggedin", "true"); %>

<% //Check If Logged In and Act Accordingly %>
<%if(loggedin == null){ %>
	<%
	//If logged in is null this means servlet doesn't remember you, so make let it know you aren't logged in
	loggedin = "false";
	context.setAttribute("loggedin", loggedin);
	%>
<%}else{%>

		<%if(loggedin == "true"){ %>
		<%//If server remembers you and knows you are logged in go to the homepage %>
			<jsp:forward page="homepage.jsp"/>
		<%}%>

	 <%} %>