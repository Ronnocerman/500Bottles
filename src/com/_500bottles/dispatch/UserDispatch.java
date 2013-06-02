package com._500bottles.dispatch;

import com._500bottles.action.UserAction;
import com._500bottles.exception.user.UserAlreadyExistsException;
import com._500bottles.exception.user.UserDoesNotExistException;
import com._500bottles.manager.SessionManager;
import com._500bottles.object.user.ApplicationUser;
import com._500bottles.object.user.User;
import com._500bottles.util.Utilities;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UserDispatch extends HttpServlet
{
	private final String SUCCESS_CALLBACK_FIELD = "success";
	private final String FAILED_CALLBACK_FIELD = "failed";
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
		SessionManager.initiateSessionManager(request);

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
		SessionManager.initiateSessionManager(request);

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
				login(request, response);
				break;

			case "createAccount":
				createAccount(request, response);
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

	private void login(HttpServletRequest request,
			   HttpServletResponse response)
	{
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher =
			context.getRequestDispatcher("/messages/js_callback.jsp");

		String email = request.getParameter(EMAIL_FIELD);
		String password = request.getParameter(PASSWORD_FIELD);
		String success = request.getParameter(SUCCESS_CALLBACK_FIELD);
		String failed = request.getParameter(FAILED_CALLBACK_FIELD);

		boolean passwordCorrect = false;

		try {
			String passwordHash = Utilities.hashPassword(password);

			passwordCorrect = UserAction.login(email, passwordHash);

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

		} catch (UserDoesNotExistException e) {

		}

		try {
			if (passwordCorrect) {
				request.setAttribute("callback", success);
			} else {
				request.setAttribute("callback", failed);
			}

			dispatcher.forward(request, response);

		} catch (ServletException | IOException e) {

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
			String passwordHash = Utilities.hashPassword(password);

			// Parse the date of birth.
			Calendar dob = new GregorianCalendar();
			dob.set(Integer.parseInt(dob_year), Integer.parseInt(dob_month), Integer.parseInt(dob_day));

			Date d = dob.getTime();

			user.setEmail(email);
			user.setPassword(passwordHash);
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setDOB(d);
			user.setRegistrationDate(new Date());
			user.setLastLogin(new Date());

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

		}

		try {
			PrintWriter out = response.getWriter();
			UserAction.createAccount(user);

		} catch (UserAlreadyExistsException e) {

		} catch (IOException e) {

		}

	}
}
