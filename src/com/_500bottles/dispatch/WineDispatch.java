package com._500bottles.dispatch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class WineDispatch extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();

		switch (action) {
			case "createCustomWine":
				out.println("createCustomWine clicked");
				break;
			case "editCustomWine":
				out.println("editCustomWine clicked");
				break;
			case "deleteCustomWine":
				out.println("deleteCustomWine clicked");
				break;
			case "setFavorite":
				out.println("setFavorite clicked");
				break;
			case "getFavorite":
				out.println("getFavorite clicked");
				break;
			case "setRating":
				out.println("setRating clicked");
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
