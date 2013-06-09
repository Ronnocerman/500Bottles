package com._500bottles.dispatch;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

	private final String WINE_TYPE = "type";
	private final String VARIETAL = "varietal";
	private final String MIN_YEAR = "minYear";
	private final String MAX_YEAR = "maxYear";
	private final String MIN_PRICE = "minPrice";
	private final String MAX_PRICE = "maxPrice";
	private final String VINEYARD = "vineyard";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");

		if (action.equals("getSuggestion"))
			getSuggestion(request, response);

	}

	private void getSuggestion(HttpServletRequest request,
			HttpServletResponse response)
	{
		WineQueryResult result = null;
		ServletContext context = getServletContext();

		RequestDispatcher dispatcher = context
				.getRequestDispatcher("/views/wizard/suggestion_results.jsp");

		String wine_type = request.getParameter(WINE_TYPE);
		String varietal = request.getParameter(VARIETAL);
		String min_year = request.getParameter(MIN_YEAR);
		String max_year = request.getParameter(MAX_YEAR);
		String min_price = request.getParameter(MIN_PRICE);
		String max_price = request.getParameter(MAX_PRICE);
		String vineyard = request.getParameter(VINEYARD);

		try
		{
			wine_type = URLDecoder.decode(wine_type, "UTF-8");
			varietal = URLDecoder.decode(varietal, "UTF-8");
			min_year = URLDecoder.decode(min_year, "UTF-8");
			max_year = URLDecoder.decode(max_year, "UTF-8");
			min_price = URLDecoder.decode(min_price, "UTF-8");
			max_price = URLDecoder.decode(max_price, "UTF-8");
			vineyard = URLDecoder.decode(vineyard, "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			System.err
					.println("WineWizardDispatch.getSuggestion(): UnsupportedEncodingException!");
		}

		WineQuery query = new WineQuery();
		query.setSize(36);

		if (vineyard != "")
		{
			Vector<Vineyard> vine = new Vector<Vineyard>();
			Vineyard yard = new Vineyard();
			yard.setName(vineyard);
			vine.add(yard);
			query.setVineyard(vine);
		} else
		{

		}

		if (wine_type != "")
		{
			Vector<WineType> typeList = new Vector<WineType>();
			WineType type = new WineType();
			type.setWineType(wine_type);
			typeList.add(type);
			query.setType(typeList);
			System.out.println("the wine type is " + wine_type);
		}

		if (varietal != "")
		{
			Vector<Varietal> var = new Vector<Varietal>();
			Varietal variet = new Varietal();
			variet.setGrapeType(varietal);
			var.add(variet);
			query.setVarietal(var);
			System.out.println("the varietal is " + varietal);
		}

		if (min_year != null && max_year != null && min_year != ""
				&& max_year != "")
		{
			query.setMinYear(Integer.parseInt(min_year));
			query.setMaxYear(Integer.parseInt(max_year));
		}

		if (max_price != null && min_price != null && max_price != ""
				&& min_price != "")
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
