package com._500bottles.dispatch;

import com._500bottles.action.WineAction;
import com._500bottles.manager.WineManager;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class WineDispatch extends HttpServlet
{
	private final String WINE_SEARCH_TEXT_FIELD = "textWineSearch";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();

		switch (action)
		{
			case "wineSearch":
				wineSearch(request, response);
				break;
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

	/**
	 * Performs wine search.
	 * @param request
	 * @param response
	 */
	private void wineSearch(HttpServletRequest request,
				HttpServletResponse response)
	{
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher =
			context.getRequestDispatcher("/views/wine_search_results.jsp");

		String textWineSearch = request.getParameter(WINE_SEARCH_TEXT_FIELD);

		try {
			textWineSearch = URLDecoder.decode(textWineSearch, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.err.println("WineDispatch.wineSearch(): UnsupportedEncodingException!");
		}

		System.err.println(textWineSearch);

		WineQuery query = new WineQuery();
		query.setTextQuery(textWineSearch);
		query.setSize(20);

		WineQueryResult result = WineAction.searchWine(query);
		request.setAttribute("search_result", result);

		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO: catch exception or display error.
		}
	}
}
