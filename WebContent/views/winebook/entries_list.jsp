<%@ page import="com._500bottles.object.winebook.Entry" %>
<%@ page import="java.util.Vector" %>
<%@ page import="com._500bottles.manager.WinebookManager" %>
<%@ page import="com._500bottles.action.WinebookAction" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%

    Vector<Entry> entries = WinebookAction.getAllEntries();
    Iterator<Entry> it = entries.iterator();

    Entry entry;
    Date dateCreated;
    String day, month, year;

    SimpleDateFormat day_fmt = new SimpleDateFormat("dd");
    SimpleDateFormat month_fmt = new SimpleDateFormat("MMM");
    SimpleDateFormat year_fmt = new SimpleDateFormat("YYY");

    while (it.hasNext()) {
        entry = it.next();
        dateCreated = entry.getDateCreated();

        day = day_fmt.format(dateCreated);
        month = month_fmt.format(dateCreated);
        year = year_fmt.format(dateCreated);

%>

<div class="winebook_entry light_bg with_border">
    <div class="bg"></div>
    <div class="date">
        <span class="day"><%= day %></span>
        <span class="month"><%= month %></span>
        <span class="year"><%= year %></span>
    </div>
    <div class="image"></div>
    <div class="excerpt">
        <p><%= entry.getContent() %></p>
    </div>
</div>

<% } %>
