package com._500bottles.manager;

import java.util.Vector;

import com._500bottles.object.wine.Wine;

public class WineWizardManager
{ // note to self add in more list for different phases
	/*
	 * private int wineTypeParse;// accounts for the location of the wineType
	 * list private int appellationParse;// accounts for the location of the
	 * appellation private int yearParse;// accounts for the location of the
	 * year list private int varietalParse;// accounts for the location of the
	 * varietal list private int vineyardParse;// accounts for the location of
	 * the vineyard list
	 */

	@SuppressWarnings("unused")
	private long userID;
	private Vector<String> wineType = new Vector<String>();// set for
															// advance
															// search
															// level one
	private String appellation;// set for advanced search level five
	private String year;// set for advanced search level three
	private String varietal;// set for advanced search level two
	private String vineyard;// set for advanced level four
	private String wineName;
	private boolean fromCellar;// a flag to see if user wants wine from their
	// cellar
	private boolean fromOnline;// a flag to see if user wants wine from online
	// the list of Rated wines from database
	private Vector<Wine> wineListRated = new Vector<Wine>();
	// level one list of wines which account for the preferred wine type

	// private Vector<Wine> levelOne = new Vector<Wine>(); // level two list
	// ofines
	// which account for the
	// preferred varietal
	// private Vector<Wine> levelTwo = new Vector<Wine>(); // level three list
	// of
	// wines which account
	// for the preferred
	// year
	// private Vector<Wine> levelThree = new Vector<Wine>(); // level four list
	// of
	// wines which
	// account for
	// thepreferred
	// vineyard
	// private Vector<Wine> levelFour = new Vector<Wine>();
	// the final list of suggested wines
	// private Vector<Wine> suggestion = new Vector<Wine>();

	// the list of all rated types
	private Vector<String> typeList = new Vector<String>();
	// the type rating
	private Vector<Integer> typeRating = new Vector<Integer>();
	// the amount of that type
	private Vector<Integer> typeAmount = new Vector<Integer>();
	// the list of the appellation that have been rated
	private Vector<String> appellationList = new Vector<String>();
	// the list of the appellation rating
	private Vector<Integer> appellationRating = new Vector<Integer>();
	// the list of the amount of that appellation that have been rated
	private Vector<Integer> appellationAmount = new Vector<Integer>();
	// the list of the years that have been rated
	private Vector<String> yearList = new Vector<String>();
	// the list of the years ratings
	private Vector<Integer> yearRating = new Vector<Integer>();
	// the list of the years amount
	private Vector<Integer> yearAmount = new Vector<Integer>();
	// the list of varietals that have been rated
	private Vector<String> varietalList = new Vector<String>();
	// the list of the varietals ratings
	private Vector<Integer> varietalRating = new Vector<Integer>();
	// the list of the varietals amounts
	private Vector<Integer> varietalAmount = new Vector<Integer>();
	// the list of the vineyard that have been rated
	private Vector<String> vineyardList = new Vector<String>();
	// the list of the vineyard ratings
	private Vector<Integer> vineyardRating = new Vector<Integer>();
	// the list of the vineyard amount
	private Vector<Integer> vineyardAmount = new Vector<Integer>();
	// all the wines the user can access will be swaped later for something more
	// dynamic
	private Vector<Wine> allTheWines = new Vector<Wine>();// all the wines
	private Vector<Integer> rate = new Vector<Integer>();// a list to keep
															// track of the
															// wines weights
															// aka sum
	private Vector<Wine> selected = new Vector<Wine>();// the list of
														// wines to be
														// returned
	private Vector<Wine> selectedApi = new Vector<Wine>();
	private int userAmount;// the amount that the user wants
	private double sensitiveType;// the weight of the type
	private double sensitiveVarietal;// the weight of the varietal
	private double sensitiveYear;// the weight of the year
	private double sensitiveVineyard;// the weight of the vineyard
	private double sensitiveAppellation;// the weight of the appellation

	// constructor for WineWizardManager
	public WineWizardManager(int userAmount, long userID)
	{
		this.userID = userID;
		fromCellar = false;
		fromOnline = false;
		/*
		 * wineTypeParse = 0; appellationParse = 0; yearParse = 0; varietalParse
		 * = 0; vineyardParse = 0;
		 */
		sensitiveType = 3;// sets the default weight for the type to 2
		sensitiveVarietal = 3;// sets the default weight for the varietal to
								// 1.75
		sensitiveYear = 2;// sets the weight of the year
		sensitiveVineyard = 1;// sets the weight of the vineyard
		sensitiveAppellation = 1;// sets the weight of the appellation
		this.setUserAmount(userAmount);// sets the amount of wines to return
		// the for loop that fills the values with zero
		for (int i = 0; i < userAmount; i++)
		{
			rate.add(0);
			selected.add(null);
		}
	}

	// sets the wine types that the user wants
	public void setWineType(Vector<String> wineType)
	{
		this.wineType = wineType;
	}

	// gets the wine types the user wants
	public Vector<String> getWineType()
	{
		return wineType;
	}

	// sets the varietal the user wants
	public void setVarietal(String varietal)
	{
		this.varietal = varietal;
	}

	// gets the varietal the user wants
	public String getVarietal()
	{
		return varietal;
	}

	// sets the year the user wants
	public void setYear(String year)
	{
		this.year = year;
	}

	// gets the year the user wants
	public String getYear()
	{
		return year;
	}

	// sets the vineyard the user wants
	public void setVineyard(String vineyard)
	{
		this.vineyard = vineyard;
	}

	// gets the vineyard the user wants
	public String getVineyard()
	{
		return vineyard;
	}

	// sets the appellation the user wants
	public void setAppellation(String appellation)
	{
		this.appellation = appellation;
	}

	// gets the appellation the user wants
	public String getAppellation()
	{
		return appellation;
	}

	/*
	 * public Wine getSuggestionBeta() { WineDAO winer = new WineDAO(); // this
	 * while loop is to create an array list full of wine objects with //
	 * ratings while (true) {// this fills the wineList Vector with all the
	 * wines that have been // rated Wine temp = winer.getWine();// gets wine
	 * from database if (temp.getRating() != 0) {// if the wine rating field has
	 * yet to be entered // then don't put it in the list
	 * wineListRated.add(temp); } } doLevelOne(true);
	 * 
	 * }
	 */
	// gets the rating of a certain
	public int getRating(String s, Vector<String> type)
	{
		// account for a list of types that the user has selected
		if (type.equals(wineType))
		{
			return typeList.size();
		}
		for (int i = 0; i < type.size(); i++)
		{
			if (s.equals(type.get(i)))
			{
				return type.size() - i;
			}
		}
		return 0;
	}

	public void getSuggestionAlpha()
	{

		for (int i = 0; i < allTheWines.size(); i++)
		{
			double sum = 0;// the score of the wine
			// score of the wine plus the type score
			sum = sum
					+ sensitiveType
					* getRating(allTheWines.get(i).getType().getWineType(),
							typeList);// create custom weight variables
			// score of the wine plus the varietal score
			sum = sum
					+ sensitiveVarietal
					* getRating(
							allTheWines.get(i).getVarietal().getGrapeType(),
							varietalList);
			// gets the year that the wine was created
			Integer r = new Integer((int) allTheWines.get(i).getYear());
			String s = r.toString();// converts it to a string
			// score of the wine plus the year score
			sum = sum + sensitiveYear * getRating(s, yearList);
			// score of the wine plus vineyard score
			sum = sum
					+ sensitiveVineyard
					* getRating(allTheWines.get(i).getVineyard().getName(),
							vineyardList);
			// score of the wine plusthe appellation score
			sum = sum
					+ sensitiveAppellation
					* getRating(allTheWines.get(i).getAppellation()
							.getLocation(), appellationList);
			// check to see if wines don't have the required type the user set
			if (wineType != null)
			{
				int test = 0;
				for (int q = 0; q < wineType.size(); q++)
				{

					if (allTheWines.get(i).getType().getWineType()
							.equals(wineType.get(q)))
					{
						test = 1;
					}
				}
				if (test != 1)
				{
					sum = 0;
				}
			}
			// check to see if wines don't have the required varietal the user
			// set
			if (varietal != null)
			{
				if (!allTheWines.get(i).getVarietal().getGrapeType()
						.equals(varietal))
				{
					sum = 0;
				}
			}
			// check to see if wines don't have the required year the user set
			if (year != null)
			{
				if (!s.equals(year))
				{
					sum = 0;
				}
			}
			// check to see if wines don't have the required vineyard the user
			// set
			if (vineyard != null)
			{
				if (!allTheWines.get(i).getVineyard().getName()
						.equals(vineyard))
				{
					sum = 0;
				}
			}
			// check to see if wines don't have the required appellation the
			// user set
			if (appellation != null)
			{
				if (!allTheWines.get(i).getAppellation().getLocation()
						.equals(appellation))
				{
					sum = 0;
				}
			}
			// adds and sort the wine to the list possible cutting it off
			// depending if list is to long
			addSort(sum, allTheWines.get(i));
		}
	}

	// adds and sort the wine to the list possible cutting it off depending if
	// list is to long
	public void addSort(double rating, Wine win)
	{
		rate.add((int) rating * 100);
		selected.add(win);
		for (int i = 0; i < rate.size(); i++)
		{
			for (int j = rate.size() - 1; j > i; j--)
			{
				if (rate.get(j) > rate.get(i))
				{
					Integer temp = rate.get(j);
					rate.set(j, rate.get(i));
					rate.set(i, temp);
					Wine tempWine = selected.get(j);
					selected.set(j, selected.get(i));
					selected.set(i, tempWine);
				}

			}
		}
		rate.remove(rate.size() - 1);
		selected.remove(selected.size() - 1);
	}

	public void doAverages()
	{

		for (int d = 0; d < wineListRated.size(); d++)
		{
			// puts in the rating of the wines based on their attributes
			String s = wineListRated.get(d).getType().getWineType();
			int k = (int) (100 * wineListRated.get(d).getRating());
			place(s, k, typeList, typeRating, typeAmount);
			s = wineListRated.get(d).getVarietal().getGrapeType();
			place(s, k, varietalList, varietalRating, varietalAmount);
			Integer r = new Integer((int) wineListRated.get(d).getYear());
			s = r.toString();
			place(s, k, yearList, yearRating, yearAmount);
			s = wineListRated.get(d).getVineyard().getName();
			place(s, k, vineyardList, vineyardRating, vineyardAmount);
			s = wineListRated.get(d).getAppellation().getLocation();
			place(s, k, appellationList, appellationRating, appellationAmount);
		}
		// averages and sorts the attributes
		getAverage(typeRating, typeAmount);
		sort(typeList, typeRating, typeAmount);
		getAverage(varietalRating, varietalAmount);
		sort(varietalList, varietalRating, varietalAmount);
		getAverage(yearRating, yearAmount);
		sort(yearList, yearRating, yearAmount);
		getAverage(vineyardRating, vineyardAmount);
		sort(vineyardList, vineyardRating, vineyardAmount);
		getAverage(appellationRating, appellationAmount);
		sort(appellationList, appellationRating, appellationAmount);
	}

	// the method called by WineWizardManager to select the Wine
	public Vector<Wine> selectWine()
	{
		// TODO get all rated wines sorted if none then return a bunch of
		// randoms
		// TODO get all the wines from cellar and/or online if user selects
		// neither
		if (wineListRated.isEmpty())
		{
			for (int i = 0; i < userAmount; i++)
			{
				Wine dog = random();
				if (!selected.contains(dog))
					selected.add(dog);
			}
		} else
		{
			doAverages();
			// TODO: Grab a few wines from api if specified based on the
			// averages
			if (fromCellar)
				getSuggestionAlpha();
			if (fromOnline)
				getApiWines();
		}
		return selected;
	}

	public void getApiWines()
	{
		// TODO: get the api Wines with the top traits
		if (!wineType.isEmpty())
		{

		}

	}

	// sorts the attributes and rating based on rating
	public void sort(Vector<String> att, Vector<Integer> rating,
			Vector<Integer> amount)
	{
		for (int i = 0; i < rating.size(); i++)
		{
			for (int j = rating.size() - 1; j > i; j--)
			{
				if (rating.get(j) > rating.get(i))
				{
					String tempAtt = att.get(i);
					Integer tempRat = rating.get(i);
					Integer tempAmount = amount.get(i);
					att.set(i, att.get(j));
					rating.set(i, rating.get(j));
					amount.set(i, rating.get(j));
					att.set(j, tempAtt);
					rating.set(j, tempRat);
					amount.set(j, tempAmount);
				}
			}
		}
	}

	// gets the average of an attribute
	public void getAverage(Vector<Integer> rating, Vector<Integer> amount)
	{
		for (int i = 0; i < rating.size(); i++)
		{
			int d = rating.get(i).intValue();
			int q = amount.get(i).intValue();
			Integer avg = new Integer(d / q);
			rating.set(i, avg);
		}

	}

	// places the attribute in the list whether new or already in the list
	public void place(String attribute, int rat, Vector<String> att,
			Vector<Integer> rating, Vector<Integer> amount)
	{
		Integer r = new Integer(rat);
		// if the list doesn't contain the attribute then it places it in there
		if (!att.contains(attribute))
		{
			// System.out.println("does it even work?");
			att.add(attribute);
			rating.add(r);
			Integer one = new Integer(1);
			amount.add(one);
		} else
		{
			// System.out.println("gets in here");
			int k = att.indexOf(attribute);
			// System.out.println(k);
			Integer d = new Integer(rating.get(k).intValue() + rat);
			// System.out.println("gets over here");
			rating.set(k, d);
			Integer q = new Integer(amount.get(k).intValue() + 1);
			amount.set(k, q);

		}
	}

	public Wine random()
	{

		int rand = (int) Math.random() * allTheWines.size();
		if (allTheWines.get(rand).getRating() == 0)
		{
			while (allTheWines.get(rand).getRating() > 2.5)
			{
				rand = (int) Math.random() * allTheWines.size();
				if (allTheWines.get(rand).getRating() == 0)
					break;
			}
		}
		return allTheWines.get(rand);
	}

	// gets the user amount of wines to return per suggestion
	public int getUserAmount()
	{
		return userAmount;
	}

	// set the user amount of wines to return per suggestion
	public void setUserAmount(int userAmount)
	{
		this.userAmount = userAmount;
	}

	// gets the weight of the type
	public double getSensitiveType()
	{
		return sensitiveType;
	}

	// sets the weight of the type
	public void setSensitiveType(double sensitveType)
	{
		this.sensitiveType = sensitveType;
	}

	// sets the weight of the varietal
	public double getSensitiveVarietal()
	{
		return sensitiveVarietal;
	}

	// gets the weight of the varietal
	public void setSensitiveVarietal(double sensitveVarietal)
	{
		this.sensitiveVarietal = sensitveVarietal;
	}

	// gets the weight of the year
	public double getSensitiveYear()
	{
		return sensitiveYear;
	}

	// sets the weight of the year
	public void setSensitiveYear(double sensitveYear)
	{
		this.sensitiveYear = sensitveYear;
	}

	// gets the weight of the vineyard
	public double getSensitiveVineyard()
	{
		return sensitiveVineyard;
	}

	// sets the weight of the vineyard
	public void setSensitiveVineyard(double sensitveVineyard)
	{
		this.sensitiveVineyard = sensitveVineyard;
	}

	// gets the weight of the appellation
	public double getSensitiveAppellation()
	{
		return sensitiveAppellation;
	}

	// sets the weight of the appellation
	public void setSensitiveAppellation(double sensitveAppellation)
	{
		this.sensitiveAppellation = sensitveAppellation;
	}

	public Vector<Wine> findWineApi()
	{
		// TODO do a call to the api with a search with the name specified and
		// the traits

		return null;
	}

	public String getWineName()
	{
		return wineName;
	}

	public void setWineName(String wineName)
	{
		this.wineName = wineName;
	}

	public boolean isFromCellar()
	{
		return fromCellar;
	}

	public void setFromCellar(boolean fromCellar)
	{
		this.fromCellar = fromCellar;
	}

	public boolean isFromOnline()
	{
		return fromOnline;
	}

	public void setFromOnline(boolean fromOnline)
	{
		this.fromOnline = fromOnline;
	}

	public Vector<Wine> getSelectedApi()
	{
		return selectedApi;
	}

	public void setSelectedApi(Vector<Wine> selectedApi)
	{
		this.selectedApi = selectedApi;
	}

	public Vector<Wine> getSelected()
	{
		return selected;
	}

	public void setSelected(Vector<Wine> selected)
	{
		this.selected = selected;
	}
}