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

</head>
<body>

    <h1>Welcome to the AJAX Tester!</h1>
    
    <script type="text/javascript">
        (function() {
            function bind_events()
            {
                $("#winebook_addEntry").on("click", function() {
                    submit_request("/winebook", {
                        "action": "addEntry",
                        "entrycontent": $("#addEntry_content").val()
                    }, "POST");
                });
                $("#winebook_getEntry").on("click", function() {
                    submit_request("/winebook", {
                        "action": "getEntry"
                    });
                });
                $("#winebook_removeEntry").on("click", function() {
                    submit_request("/winebook", {
                        "action": "removeEntry"
                    }, "POST");
                });
                $("#winebook_editContent").on("click", function() {
                    submit_request("/winebook", {
                        "action": "editContent",
                        "editcontent": $(#editEntry_content).val()
                    }, "POST");
                });
                $("#winebook_addWine").on("click", function() {
                    submit_request("/winebook", {
                        "action": "addWine",
                        "addwine": $(#addWine_content).val()
                    }, "POST");
                });
                $("#winebook_removeWine").on("click", function() {
                    submit_request("/winebook", {
                        "action": "removeWine"
                    }, "POST");
                });
                $("#winebook_addPhoto").on("click", function() {
                    submit_request("/winebook", {
                        "action": "addPhoto",
                    }, "POST");
                });
                $("#winebook_removePhoto").on("click", function() {
                    submit_request("/winebook", {
                        "action": "removePhoto"
                    }, "POST");
                });
                $("#winebook_uploadPhoto").on("click", function() {
                    submit_request("/winebook", {
                        "action": "uploadPhoto"
                    }, "POST");
                });
            }

            function submit_request(url, data, type)
            {
                type = type || "GET";

                $.ajax({
                    url: url,
                    data: data,
                    type: type
                }).success(function (data, textStatux, jqXHR) {
                            $("#response_user").text(data);
                        });
            }

            $(window).on("ready", bind_events);
        })();
    </script>

    <h2>User Tests</h2>
    <hr/>
    <table style="width: 100%">
        <tr>
            <td width="50%">
                <label for="addEntry_content">add entry content</label>
                <input id="addEntry_content" type="text" value="" />
                
                <label for="editEntry_content">edit entry content</label>
                <input id="editEntry_content" type="text" value="" />

                <label for="addWine_content">add wine content</label>
                <input id="addWine_content" type="text" value="" />
                
                <button id="winebook_addEntry">Test addEntry</button>
                <button id="winebook_getEntry">Test getEntry</button>
                <button id="winebook_removeEntry">Test removeEntry</button>
                <button id="winebook_editContent">Test editContent</button>
                <button id="winebook_addWine">Test addWine</button>
                <button id="winebook_removeWine">Test removeWine</button>
                <button id="winebook_addPhoto">Test addPhoto</button>
                <button id="winebook_removePhoto">Test removePhoto</button>
                <button id="winebook_uploadPhoto">Test uploadPhoto</button>
            </td>
            <td>
                <textarea id="response_user" style="width: 100%; height: 300px;"></textarea>
            </td>
        </tr>
    </table>

</body>
</html>