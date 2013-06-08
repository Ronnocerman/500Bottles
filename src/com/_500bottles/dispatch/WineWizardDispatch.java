package com._500bottles.dispatch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

import com._500bottles.action.WineWizardAction;
import com._500bottles.da.external.snooth.exception.InvalidSort;
import com._500bottles.da.external.wine.exception.InvalidCategory;
import com._500bottles.da.external.wine.exception.InvalidOtherParameters;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;
import com._500bottles.object.wine.WineType;

@SuppressWarnings("serial")
public class WineWizardDispatch extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();
		if (action.equals("suggestion"))
			getSuggestion(request, response);

	}

	private void getSuggestion(HttpServletRequest request,
			HttpServletResponse response)
	{
		// System.out.println("Gets into ");
		// TODO Auto-generated method stub
		WineQueryResult result = null;
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher = context
				.getRequestDispatcher("/views/wizard.jsp");
		String wineType = request.getParameter("type");
		String varietal = request.getParameter("varietal");
		String min_year = request.getParameter("min_year");
		String max_year = request.getParameter("max_year");
		String min_price = request.getParameter("min_price");
		String max_price = request.getParameter("max_price");
		String vineyard = request.getParameter("vineyard");
		WineQuery query = new WineQuery();
		query.setSize(4);
		if (vineyard != null)
		{
			Vector<Vineyard> vine = new Vector<Vineyard>();
			Vineyard yard = new Vineyard();
			yard.setName(vineyard);
			vine.add(yard);
			query.setVineyard(vine);
		}
		if (wineType != null)
		{
			Vector<WineType> typeList = new Vector<WineType>();
			WineType type = new WineType();
			type.setWineType(wineType);
			typeList.add(type);
			query.setType(typeList);
		}
		if (varietal != null)
		{
			Vector<Varietal> var = new Vector<Varietal>();
			Varietal variet = new Varietal();
			variet.setGrapeType(varietal);
			var.add(variet);
			query.setVarietal(var);
		}

		if (min_year != null && max_year != null)
		{
			query.setMinYear(Integer.parseInt(min_year));
			query.setMaxYear(Integer.parseInt(max_year));
		}
		if (max_price != null && min_price != null)
		{
			query.setMinPrice(Integer.parseInt(min_price));
			query.setMaxPrice(Integer.parseInt(max_price));
		}
		try
		{
			result = WineWizardAction.getSuggestion(query);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCategory e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidSort e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidOtherParameters e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("search_result", result);

		try
		{
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e)
		{
			// TODO: catch exception or display error.
		}

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

}
