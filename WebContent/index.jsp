<%@ page import="com._500bottles.manager.SessionManager" %>
<%@ include file="/inc/head.jsp" %>

<%
    SessionManager sm = SessionManager.getSessionManager();

    if (sm.getLoggedInUser() != null) {
        %><%@ include file="/views/homepage.jsp" %><%
    } else {
        %><%@ include file="/views/frontpage.jsp" %><%
    }
%>

<%@ include file="/inc/footer.jsp" %>
