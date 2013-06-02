package com._500bottles.dispatch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class WinebookDispatch extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();

		switch (action)
		{
		case "addEntry":
			out.println("addEntry clicked");
			break;
		case "getEntry":
			out.println("getEntry clicked");
			break;
		case "removeEntry":
			out.println("removeEntry clicked");
			break;
		case "editContent":
			out.println("editContent clicked");
			break;
		case "addWine":
			out.println("addWine clicked");
			break;
		case "removeWine":
			out.println("removeWine clicked");
			break;
		case "addPhoto":
			out.println("addPhoto clicked");
			break;
		case "removePhoto":
			out.println("removePhoto clicked");
			break;
		case "uploadPhoto":
			out.println("uploadPhoto clicked");
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
