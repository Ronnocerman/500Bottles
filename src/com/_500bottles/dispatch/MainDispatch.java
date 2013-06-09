package com._500bottles.dispatch;

import com._500bottles.manager.SessionManager;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Wine;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NSFWServlet
 */
public class MainDispatch extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public MainDispatch()
	{
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			     HttpServletResponse response) throws ServletException, IOException
	{
		SessionManager.initiateSessionManager(request);

		String action = request.getParameter("action");
		String requestedPage = request.getParameter("view");

		HttpSession session = request.getSession();

		if (action != null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/" + requestedPage + ".jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}
}
