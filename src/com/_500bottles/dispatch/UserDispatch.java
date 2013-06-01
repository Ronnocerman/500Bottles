package com._500bottles.dispatch;

import com._500bottles.action.UserAction;
import com._500bottles.exception.user.UserAlreadyExistsException;
import com._500bottles.object.user.ApplicationUser;
import com._500bottles.object.user.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UserDispatch extends HttpServlet
{
	private final String EMAIL_FIELD = "email";
	private final String PASSWORD_FIELD = "password";
	private final String FIRSTNAME_FIELD = "firstname";
	private final String LASTNAME_FIELD = "lastname";
	private final String DOB_DAY = "dobDay";
	private final String DOB_MONTH = "dobMonth";
	private final String DOB_YEAR = "dobYear";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();

		switch (action) {
			case "logout":
				out.println("logout clicked");
				break;
			default:
				out.println("error");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();

		String email = request.getParameter(EMAIL_FIELD);
		String password = request.getParameter(PASSWORD_FIELD);
		String firstname = request.getParameter(FIRSTNAME_FIELD);
		String lastname = request.getParameter(LASTNAME_FIELD);
		String dob_day = request.getParameter(DOB_DAY);
		String dob_month = request.getParameter(DOB_MONTH);
		String dob_year = request.getParameter(DOB_YEAR);

		switch (action) {
			case "login":
				out.println("login clicked");
				out.println("email: " + email);
				out.println("password: " + password);
				break;
			case "createAccount":

				out.println("createAccount clicked");
				out.println("email: " + email);
				out.println("password: " + password);
				out.println("firstname: " + firstname);
				out.println("lastname: " + lastname);
				out.println("dob day: " + dob_day);
				out.println("dob month: " + dob_month);
				out.println("dob year: " + dob_year);
				break;
			case "resetPassword":
				out.println("resetPassword clicked");
				break;
			case "deleteAccount":
				out.println("deleteAccount clicked");
				break;
			case "editUserInfo":
				out.println("editUserInfo clicked");
				break;
			default:
				out.println("error");
		}
	}

	private void createAccount(HttpServletRequest request,
				   HttpServletResponse response)
	{
		String email = request.getParameter(EMAIL_FIELD);
		String password = request.getParameter(PASSWORD_FIELD);
		String firstname = request.getParameter(FIRSTNAME_FIELD);
		String lastname = request.getParameter(LASTNAME_FIELD);
		String dob_day = request.getParameter(DOB_DAY);
		String dob_month = request.getParameter(DOB_MONTH);
		String dob_year = request.getParameter(DOB_YEAR);

		ApplicationUser user = new User();

		try {
			PrintWriter out = response.getWriter();
			UserAction.createAccount(user);

		} catch (UserAlreadyExistsException e) {

		} catch (IOException e) {

		}

	}
}
