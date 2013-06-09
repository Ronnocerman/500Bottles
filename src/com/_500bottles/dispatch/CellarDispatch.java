package com._500bottles.dispatch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com._500bottles.action.CellarAction;
import com._500bottles.exception.cellar.CellarException;
import com._500bottles.manager.SessionManager;
import com._500bottles.object.wine.WineQueryResult;

@SuppressWarnings("serial")
public class CellarDispatch extends HttpServlet
{

	private final String WINEID_FIELD = "wineId";
	private final String QUANTITY_FIELD = "quantity";
	private final String NOTES_FIELD = "notes";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		SessionManager.initiateSessionManager(request);

		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();

		switch (action)
		{
		case "setCellarQuantity":
			setCellarQuantity(request, response);
			break;
		case "incCellarQuantity":
			incCellarQuantity(request, response);
			break;
		case "decCellarQuantity":
			decCellarQuantity(request, response);
			break;
		case "setCellarNotes":
			setCellarNotes(request, response);
			break;
		case "getCellarNotes":
			out.println("getCellarNotes clicked");
			break;
		case "clearCellarNotes":
			clearCellarNotes(request, response);
			break;
		case "getUpdatedCellarGrid":
			getUpdatedCellarGrid(request, response);
			break;
		default:
			out.println("error");
		}
	}

	private void setCellarQuantity(HttpServletRequest request,
			HttpServletResponse response)
	{
		{
			ServletContext context = getServletContext();

			RequestDispatcher dispatcher = context
					.getRequestDispatcher("/messages/js_callback.jsp");

			String id = request.getParameter(WINEID_FIELD);
			String quantity = request.getParameter(QUANTITY_FIELD);

			try
			{
				CellarAction.setCellarQuantity(Long.parseLong(id),
						Integer.parseInt(quantity));
			} catch (CellarException e)
			{
				// TODO
			}

			try
			{
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e)
			{

			}
		}
	}

	private void incCellarQuantity(HttpServletRequest request,
			HttpServletResponse response)
	{
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher = context
			.getRequestDispatcher("/messages/js_callback.jsp");

		String id = request.getParameter(WINEID_FIELD);

		CellarAction.incCellarQuantity(Long.parseLong(id));

		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {

		}
	}

	private void decCellarQuantity(HttpServletRequest request,
			HttpServletResponse response)
	{
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher = context
			.getRequestDispatcher("/messages/js_callback.jsp");

		String id = request.getParameter(WINEID_FIELD);

		try {
			CellarAction.decCellarQuantity(Long.parseLong(id));
		} catch (CellarException e) {
			// TODO
		}

		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {

		}
	}

	private void setCellarNotes(HttpServletRequest request,
			HttpServletResponse response)
	{
		{
			ServletContext context = getServletContext();

			RequestDispatcher dispatcher = context
					.getRequestDispatcher("/messages/js_callback.jsp");

			String id = request.getParameter(WINEID_FIELD);
			String notes = request.getParameter(NOTES_FIELD);
			try
			{
				CellarAction.setCellarNotes(Long.parseLong(id), notes);
			} catch (CellarException e)
			{
				// TODO
			}

			try
			{
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e)
			{

			}
		}
	}

	private void clearCellarNotes(HttpServletRequest request,
			HttpServletResponse response)
	{
		{
			ServletContext context = getServletContext();

			RequestDispatcher dispatcher = context
					.getRequestDispatcher("/messages/js_callback.jsp");

			String id = request.getParameter(WINEID_FIELD);
			try
			{
				CellarAction.clearCellarNotes(Long.parseLong(id));
			} catch (CellarException e)
			{
				// TODO
			}

			try
			{
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e)
			{

			}
		}
	}

	private void getUpdatedCellarGrid(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException
	{
		long user_id = SessionManager.getSessionManager().getLoggedInUser().getUserId();

		try {
			WineQueryResult result = CellarAction.getAllWinesFromCellar(user_id);

			request.setAttribute("grid_wines", result);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/wine_grid/wine_grid.jsp");
			dispatcher.forward(request, response);

		} catch (CellarException e) {
			e.printStackTrace();
		}
	}

}
