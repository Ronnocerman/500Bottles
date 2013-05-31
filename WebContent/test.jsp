<%@ page import="com._500bottles.object.wine.Wine" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Tests</title>

    <style type="text/css">
        button {
            display: block;
        }
    </style>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>

    <script type="text/javascript">

        function bind_events()
        {
            $("#setCellarQuantity").on("click", function() {
                submit_request("/cellar", "setCellarQuantity");
            });
            $("#incCellarQuantity").on("click", function() {
                submit_request("/cellar", "incCellarQuantity");
            });
            $("#decCellarQuantity").on("click", function() {
                submit_request("/cellar", "decCellarQuantity");
            });
            $("#setCellarNotes").on("click", function() {
                submit_request("/cellar", "setCellarNotes");
            });
            $("#getCellarNotes").on("click", function() {
                submit_request("/cellar", "getCellarNotes");
            });
            $("#clearCellarNotes").on("click", function() {
                submit_request("/cellar", "clearCellarNotes");
            });
        }

        function submit_request(url, action)
        {
            $.ajax({
                url: url,
                data: {"action": action}
            }).success(function (data, textStatux, jqXHR) {
                $("#response").text(data);
            });
        }

        $(window).on("ready", bind_events);

    </script>

</head>
<body>

    <h1>Welcome to the AJAX Tester!</h1>

    <table style="width: 100%">
        <tr>
            <td>
                <button id="setCellarQuantity">Test setCellarQuantity</button>
                <button id="incCellarQuantity">Test incCellarQuantity</button>
                <button id="decCellarQuantity">Test decCellarQuantity</button>
                <button id="setCellarNotes">Test setCellarNotes</button>
                <button id="getCellarNotes">Test getCellarNotes</button>
                <button id="clearCellarNotes">Test clearCellarNotes</button>
            </td>
            <td>
                <textarea id="response" style="width: 100%; height: 300px;"></textarea>
            </td>
        </tr>
    </table>

</body>
</html>