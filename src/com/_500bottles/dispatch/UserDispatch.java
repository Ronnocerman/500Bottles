package com._500bottles.dispatch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UserDispatch extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();

		switch (action) {
			case "login":
				out.println("login clicked");
				break;
			case "logout":
				out.println("logout clicked");
				break;
			case "createAccount":
				out.println("createAccount clicked");
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

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}
}
