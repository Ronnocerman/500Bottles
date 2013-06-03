package com._500bottles.tests.manager;

import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;

import com._500bottles.da.external.snooth.exception.InvalidSort;
import com._500bottles.da.external.wine.exception.InvalidCategory;
import com._500bottles.da.external.wine.exception.InvalidOtherParameters;
import com._500bottles.exception.da.DAException;
import com._500bottles.manager.WineQueryManager;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineType;

public class WineQueryManagerTests
{

	/**
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidOtherParameters
	 * @throws InvalidSort
	 * @throws InvalidCategory
	 * @throws DAException
	 */
	public static void main(String[] args) throws InvalidCategory, InvalidSort,
			InvalidOtherParameters, IOException, ParseException, DAException
	{

		WineQuery hello1 = new WineQuery();
		hello1.setMinRating(75);
		hello1.setMaxRating(100);

		WineType red = new WineType("Red Wine");
		WineType white = new WineType("White Wine");
		Vector<WineType> vector = new Vector<WineType>();
		vector.add(red);
		vector.add(white);

		hello1.setType(vector);

		Vector<Wine> wineVector = WineQueryManager.search(hello1).getWines();

		// Vector<Wine> wineVector = WineQueryManager.searchWineCom(hello1);
		for (int i = 0; i < wineVector.size(); i++)
		{
			System.out.println("Name: " + wineVector.get(i).getName()
					+ "\nYear: " + wineVector.get(i).getYear() + "\nID: "
					+ wineVector.get(i).getWinecomId() + "\nAppellation: "
					+ wineVector.get(i).getAppellation().getLocation()
					+ "\nVarietal: "
					+ wineVector.get(i).getVarietal().getGrapeType()
					+ "\nWineType: "
					+ wineVector.get(i).getType().getWineType()
					+ "\nVineyard: "
					+ wineVector.get(i).getVineyard().getName() + "\nRating: "
					+ wineVector.get(i).getRating() + "\n");
		}

	}

}
