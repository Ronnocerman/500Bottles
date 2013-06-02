<%@ page import="com._500bottles.object.wine.Wine" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Tests</title>

    <style type="text/css">
        button, input {
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
                $("#user_login").on("click", function() {
                    submit_request("/user", {
                        "action": "login",
                        "email": $("#email").val(),
                        "password": $("#password").val()
                    }, "POST");
                });
                $("#user_logout").on("click", function() {
                    submit_request("/user", {
                        "action": "logout"
                    });
                });
                $("#user_createAccount").on("click", function() {
                    submit_request("/user", {
                        "action": "createAccount",
                        "firstname": $("#first_name").val(),
                        "lastname": $("#last_name").val(),
                        "email": $("#email").val(),
                        "password": $("#password").val(),
                        "dobDay": $("#dob_day").val(),
                        "dobMonth": $("#dob_month").val(),
                        "dobYear": $("#dob_year").val()
                    }, "POST");
                });
                $("#user_resetPassword").on("click", function() {
                    submit_request("/user", {
                        "action": "resetPassword"
                    }, "POST");
                });
                $("#user_deleteAccount").on("click", function() {
                    submit_request("/user", {
                        "action": "deleteAccount"
                    }, "POST");
                });
                $("#user_editUserInfo").on("click", function() {
                    submit_request("/user", {
                        "action": "editUserInfo"
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
                }).success(function (data, textStatus, jqXHR) {
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
                <label for="first_name">first name</label>
                <input id="first_name" type="text" value="" />
                <label for="last_name">last name</label>
                <input id="last_name" type="text" value="" />

                <label for="email">email</label>
                <input id="email" type="text" value="" />
                <label for="password">password</label>
                <input id="password" type="text" value="" />

                <label for="dob_day">dob day</label>
                <input id="dob_day" type="text" value="" />

                <label for="dob_month">dob month</label>
                <input id="dob_month" type="text" value="" />

                <label for="dob_year">dob year</label>
                <input id="dob_year" type="text" value="" />

                <button id="user_login">Test login</button>
                <button id="user_logout">Test logout</button>
                <button id="user_createAccount">Test createAccount</button>
                <button id="user_resetPassword">Test resetPassword</button>
                <button id="user_deleteAccount">Test deleteAccount</button>
                <button id="user_editUserInfo">Test editUserInfo</button>
            </td>
            <td>
                <textarea id="response_user" style="width: 100%; height: 300px;"></textarea>
            </td>
        </tr>
    </table>

    <script type="text/javascript">
        (function() {
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
        })();

    </script>

    <h2>Cellar Tests</h2>
    <hr/>
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