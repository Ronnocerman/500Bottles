package com._500bottles.dispatch;

import com._500bottles.action.FavoritesAction;
import com._500bottles.action.WineAction;
import com._500bottles.manager.SessionManager;
import com._500bottles.object.user.ApplicationUser;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@SuppressWarnings("serial")
public class FavoritesDispatch extends HttpServlet
{
	private final String WINE_ID_FIELD = "wineId";

	@Override
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException
	{
		SessionManager.initiateSessionManager(request);

		String action = request.getParameter("action");

		switch (action)
		{
			case "setFavorite":
				setFavorite(request);
				break;
			case "clearFavorite":
				clearFavorite(request);
				break;
			case "getFavorite":

				break;
			default:
				System.err.println("Favorites Dispatch Error!");
		}
	}

	/**
	 * Sets a wine as a favorite.
	 * @param request
	 */
	private void setFavorite(HttpServletRequest request)
	{
		SessionManager sm = SessionManager.getSessionManager();
		ApplicationUser user = sm.getLoggedInUser();
		long user_id = user.getUserId();

		String wine_id = request.getParameter(WINE_ID_FIELD);
		long wine_id_lng = Long.parseLong(wine_id);

		FavoritesAction.setFavorite(wine_id_lng, user_id);
	}

	private void clearFavorite(HttpServletRequest request)
	{
		SessionManager sm = SessionManager.getSessionManager();
		ApplicationUser user = sm.getLoggedInUser();
		long user_id = user.getUserId();

		String wine_id = request.getParameter(WINE_ID_FIELD);
		long wine_id_lng = Long.parseLong(wine_id);

		FavoritesAction.clearFavorite(wine_id_lng, user_id);
	}
}
