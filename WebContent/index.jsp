<%@ page import="com._500bottles.object.wine.Wine" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com._500bottles.action.UserAction" %>


<% 
//Get Context of global objects on servlet
ServletContext context = getServletContext(); 
//Retrieve stored global is loggedin variable
String loggedin = (String)context.getAttribute("loggedin");

%>

<% //Check If Logged In and Act Accordingly %>
<%if(loggedin == null){ %>
	<%
	//If logged in is null this means servlet doesn't remember you, so make let it know you aren't logged in
	loggedin = "false";
	context.setAttribute("loggedin", loggedin);
	%>
<%}else{%>

		
		<%
		//If server remembers you and knows you are logged in go to the homepage
		if(loggedin == "true"){ %>
			<jsp:forward page="homepage.jsp"/>
		<%}else{ 
				//If the servlet remembers you but you arent logged in go ot front page%>
				<jsp:forward page="frontpage.jsp"/>
	 		 <%} %>

	 <%} %>