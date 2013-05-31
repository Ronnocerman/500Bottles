package com._500bottles.dispatch;

import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Wine;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NSFWServlet
 */
public class MainDispatch extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public MainDispatch()
	{
		super();
		// TODO Auto-generated constructor stub
		System.err.println("Starting servlet...");
	}

	@Override
	protected void doGet(HttpServletRequest request,
			     HttpServletResponse response) throws ServletException, IOException
	{
		Wine testWine = new Wine();
		testWine.setName("Test Wine Name");

		Varietal v = new Varietal();
		v.setGrapeType("Fred");

		testWine.setVarietal(v);

		//HttpSession session = request.getSession();
		//session.setAttribute("testWine", testWine);

		request.setAttribute("testWine", testWine);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			      HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

}
