<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>

<section id="view_login" class="view animated no_display no_header no_bg">

    <a class="back_arrow animated" id="back_to_front">Back</a>

    <form id="login_form" class="animated no_display">
        <div class="person_icon">
            <input id="email" type="text" value="email" class="square_bottom" />
        </div>
        <div class="password_icon">
            <input id="password" type="password" class="square_top" />
            <input id="faux_password" type="text" value="password" class="square_top" />
        </div>
        <input id="login_submit" type="button" value="log in"/>

        <div class="forgot_signup">
            <a class="box_arrow">forgot password</a>
        </div>


        <div class="bg"></div>
    </form>

    <form id="create_account_form" class="animated no_display">
        <div class="form_group">
            <input id="create_account_firstname" type="text" value="first name" class="square_bottom" />
            <input id="create_account_lastname" type="text" value="last name" class="square_top" />
        </div>

        <div class="form_group">
            <div class="person_icon">
                <input id="create_account_email" type="text" value="email" class="square_bottom" />
            </div>
            <div class="password_icon">
                <input id="create_account_pwd" type="password" class="square_top" />
                <input id="create_account_faux_pwd" type="text" value="password" class="square_top square_bottom" />
            </div>
            <div class="password_icon">
                <input id="create_account_conf_pwd" type="password" class="square_top" />
                <input id="create_account_faux_conf_pwd" type="text" value="confirm password" class="square_top" />
            </div>
        </div>
        <div class="birthday">
            <select id="bday_month">
                <option value="1">January</option>
                <option value="2">February</option>
                <option value="3">March</option>
                <option value="4">April</option>
                <option value="5">May</option>
                <option value="6">June</option>
                <option value="7">July</option>
                <option value="8">August</option>
                <option value="9">September</option>
                <option value="10">October</option>
                <option value="11">November</option>
                <option value="12">December</option>
            </select>
            <select id="bday_day">
            <%
                for (int i = 1; i < 32; i++) {
                    %>
                    <option value="<%= i %>"><%= i %></option>
                    <%
                }
            %>
            </select>
            <select id="bday_year">
            <%

                Calendar c = new GregorianCalendar();

                int end_year = c.get(Calendar.YEAR);
                int start_year = end_year - 120;

                for (int i = end_year - 20; i >= start_year ; i--) {
                    %>
                    <option value="<%= i %>"><%= i %></option>
                    <%
                }
            %>
            </select>
        </div>

        <input id="create_account_submit" type="button" value="sign up"/>
        <div class="bg"></div>
    </form>
</section>
