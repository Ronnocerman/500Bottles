package com._500bottles.dispatch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com._500bottles.action.WinebookAction;
import com._500bottles.exception.da.DAException;
import com._500bottles.exception.winebook.EntryDoesExistException;
import com._500bottles.exception.winebook.EntryDoesNotExistException;
import com._500bottles.manager.SessionManager;
import com._500bottles.object.winebook.Entry;
import com._500bottles.object.winebook.Photo;

@SuppressWarnings("serial")
public class WinebookDispatch extends HttpServlet
{
	private final String TITLE_FIELD = "title";
	private final String CONTENT_FIELD = "content";
	private final String ID_FIELD = "id";
	private final String WINEID_FIELD = "wineid";
	private final String PHOTOID_FIELD = "photoId";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		SessionManager.initiateSessionManager(request);

		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();

		switch (action)
		{
		case "addEntry":
			addEntry(request, response);
			break;
		case "getEntry":
			out.println("getEntry clicked");
			break;
		case "removeEntry":
			removeEntry(request, response);
			break;
		case "editContent":
			editEntry(request, response);
			break;
		case "addWine":
			addWine(request, response);
			break;
		case "removeWine":
			removeWine(request, response);
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
		case "getPhotoURI":
			getPhotoURI(request, response);
			break;
		default:
			out.println("error");
		}
	}

	private void addEntry(HttpServletRequest request,
			HttpServletResponse response)
	{
		long user_id = SessionManager.getSessionManager().getLoggedInUser().getUserId();

		String title = request.getParameter(TITLE_FIELD);
		String content = request.getParameter(CONTENT_FIELD);
		String photo_id = request.getParameter(PHOTOID_FIELD);

		Entry ent = new Entry();
		ent.setUserId(user_id);
		ent.setTitle(title);
		ent.setContent(content);

		if (photo_id != null) {

			long photo_id_lng = Long.parseLong(photo_id);

			Photo p = new Photo();
			p.setId(photo_id_lng);
			p.setFilename(WinebookAction.getPhotoURI(photo_id_lng));

			ent.addPhoto(p);
		}

		try {
			WinebookAction.addEntry(ent);
		} catch (EntryDoesExistException e) {

		}
	}

	private void removeEntry(HttpServletRequest request,
			HttpServletResponse response)
	{
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher = context
				.getRequestDispatcher("/messages/js_callback.jsp");

		long eid = Long.parseLong(request.getParameter(ID_FIELD));

		try
		{
			WinebookAction.removeEntry(eid);
		} catch (EntryDoesNotExistException e)
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

	private void editEntry(HttpServletRequest request,
			HttpServletResponse response)
	{
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher = context
				.getRequestDispatcher("/messages/js_callback.jsp");

		long eid = Long.parseLong(request.getParameter(ID_FIELD));

		try
		{
			WinebookAction.editContent(eid);
		} catch (EntryDoesNotExistException e)
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

	private void addWine(HttpServletRequest request,
			HttpServletResponse response)
	{
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher = context
				.getRequestDispatcher("/messages/js_callback.jsp");

		long eid = Long.parseLong(request.getParameter(ID_FIELD));
		long wid = Long.parseLong(request.getParameter(WINEID_FIELD));
		try
		{
			WinebookAction.addWine(eid, wid);
		} catch (DAException e)
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

	private void removeWine(HttpServletRequest request,
			HttpServletResponse response)
	{
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher = context
				.getRequestDispatcher("/messages/js_callback.jsp");

		long eid = Long.parseLong(request.getParameter(ID_FIELD));
		long wid = Long.parseLong(request.getParameter(WINEID_FIELD));
		try
		{
			WinebookAction.removeWine(eid, wid);
		} catch (DAException e)
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

	private void getPhotoURI(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher = context
			.getRequestDispatcher("/messages/message.jsp");

		long photo_id = Long.parseLong(request.getParameter(PHOTOID_FIELD));

		String photo_uri = WinebookAction.getPhotoURI(photo_id);

		request.setAttribute("msg", photo_uri);

		dispatcher.forward(request, response);
	}
}
