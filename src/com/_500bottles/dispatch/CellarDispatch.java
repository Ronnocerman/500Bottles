package com._500bottles.dispatch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CellarDispatch extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();

		switch (action) {
			case "setCellarQuantity":
				out.println("setCellarQuantity clicked");
				break;
			case "incCellarQuantity":
				out.println("incCellarQuantity clicked");
				break;
			case "decCellarQuantity":
				out.println("decCellarQuantity clicked");
				break;
			case "setCellarNotes":
				out.println("setCellarNotes clicked");
				break;
			case "getCellarNotes":
				out.println("getCellarNotes clicked");
				break;
			case "clearCellarNotes":
				out.println("clearCellarNotes clicked");
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
