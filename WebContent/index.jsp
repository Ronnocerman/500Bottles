<%@ page import="com._500bottles.manager.SessionManager" %>
<%@ include file="/inc/head.jsp" %>

<%
    SessionManager sm = SessionManager.getSessionManager();

    if (sm.getLoggedInUser() != null) {

        System.err.println("user is logged in! " + sm.getLoggedInUser().getEmail());

        %><%@ include file="/views/homepage.jsp" %><%
    } else {

        System.err.println("user is not logged in");

        %><%@ include file="/views/frontpage.jsp" %><%
}
%>

<%@ include file="/inc/footer.jsp" %>
