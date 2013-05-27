package com._500bottles.manager;

import java.util.ArrayList;

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
	private ArrayList<String> wineType = new ArrayList<String>();// set for
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
	private ArrayList<Wine> wineListRated = new ArrayList<Wine>();
	// level one list of wines which account for the preferred wine type
	/*
	 * private ArrayList<Wine> levelOne = new ArrayList<Wine>(); // level two
	 * list of wines which account for the preferred varietal private
	 * ArrayList<Wine> levelTwo = new ArrayList<Wine>(); // level three list of
	 * wines which account for the preferred year private ArrayList<Wine>
	 * levelThree = new ArrayList<Wine>(); // level four list of wines which
	 * account for the preferred vineyard private ArrayList<Wine> levelFour =
	 * new ArrayList<Wine>(); // the final list of suggested wines private
	 * ArrayList<Wine> suggestion = new ArrayList<Wine>();
	 */
	// the list of all rated types
	private ArrayList<String> typeList = new ArrayList<String>();
	// the type rating
	private ArrayList<Integer> typeRating = new ArrayList<Integer>();
	// the amount of that type
	private ArrayList<Integer> typeAmount = new ArrayList<Integer>();
	// the list of the appellation that have been rated
	private ArrayList<String> appellationList = new ArrayList<String>();
	// the list of the appellation rating
	private ArrayList<Integer> appellationRating = new ArrayList<Integer>();
	// the list of the amount of that appellation that have been rated
	private ArrayList<Integer> appellationAmount = new ArrayList<Integer>();
	// the list of the years that have been rated
	private ArrayList<String> yearList = new ArrayList<String>();
	// the list of the years ratings
	private ArrayList<Integer> yearRating = new ArrayList<Integer>();
	// the list of the years amount
	private ArrayList<Integer> yearAmount = new ArrayList<Integer>();
	// the list of varietals that have been rated
	private ArrayList<String> varietalList = new ArrayList<String>();
	// the list of the varietals ratings
	private ArrayList<Integer> varietalRating = new ArrayList<Integer>();
	// the list of the varietals amounts
	private ArrayList<Integer> varietalAmount = new ArrayList<Integer>();
	// the list of the vineyard that have been rated
	private ArrayList<String> vineyardList = new ArrayList<String>();
	// the list of the vineyard ratings
	private ArrayList<Integer> vineyardRating = new ArrayList<Integer>();
	// the list of the vineyard amount
	private ArrayList<Integer> vineyardAmount = new ArrayList<Integer>();
	// all the wines the user can access will be swaped later for something more
	// dynamic
	private ArrayList<Wine> allTheWines = new ArrayList<Wine>();// all the wines
	private ArrayList<Integer> rate = new ArrayList<Integer>();// a list to keep
																// track of the
																// wines weights
																// aka sum
	private ArrayList<Wine> selected = new ArrayList<Wine>();// the list of
																// wines to be
																// returned
	private int userAmount;// the amount that the user wants
	private double sensitiveType;// the weight of the type
	private double sensitiveVarietal;// the weight of the varietal
	private double sensitiveYear;// the weight of the year
	private double sensitiveVineyard;// the weight of the vineyard
	private double sensitiveAppellation;// the weight of the appellation

	// constructor for WineWizardManager
	public WineWizardManager(int userAmount)
	{
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
	public void setWineType(ArrayList<String> wineType)
	{
		this.wineType = wineType;
	}

	// gets the wine types the user wants
	public ArrayList<String> getWineType()
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
	 * ratings while (true) {// this fills the wineList arraylist with all the
	 * wines that have been // rated Wine temp = winer.getWine();// gets wine
	 * from database if (temp.getRating() != 0) {// if the wine rating field has
	 * yet to be entered // then don't put it in the list
	 * wineListRated.add(temp); } } doLevelOne(true);
	 * 
	 * }
	 */
	// gets the rating of a certain
	public int getRating(String s, ArrayList<String> type)
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
		// TODO:get all the wines in one array list
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

	/*
	 * public void doLevelOne(boolean firstTime) { if (wineType != null &&
	 * !firstTime) { return; } if (wineType == null) { if (firstTime) { for (int
	 * d = 0; d < wineListRated.size(); d++) { String s =
	 * wineListRated.get(d).getType().getWineType(); int k =
	 * wineListRated.get(d).getRating(); place(s, k, typeList, typeRating,
	 * typeAmount); } getAverage(typeRating, typeAmount); sort(typeList,
	 * typeRating, typeAmount); } } if (wineTypeParse < typeList.size()) { for
	 * (int d = 0; d < wineListRated.size(); d++) { if (wineType == null) { if
	 * (wineListRated.get(d).getType().getWineType()
	 * .equals(typeList.get(wineTypeParse))) {
	 * levelOne.add(wineListRated.get(d)); } } else if
	 * (wineListRated.get(d).getType().getWineType() .equals(wineType)) {
	 * levelOne.add(wineListRated.get(d)); } } varietalList.clear();
	 * varietalRating.clear(); varietalAmount.clear(); doLevelTwo(true); } else
	 * { return; // there are no options for this user therefore they should
	 * drink an // alcoholic beverage that help them forget that they } }
	 * 
	 * public void doLevelTwo(boolean firstTime) { if (varietal != null &&
	 * !firstTime) { return; } if (varietal == null) { if (firstTime) { for (int
	 * d = 0; d < levelOne.size(); d++) { String s =
	 * levelOne.get(d).getVarietal().getGrapeType(); int i =
	 * levelOne.get(d).getRating(); place(s, i, varietalList, varietalRating,
	 * varietalAmount); } getAverage(varietalRating, varietalAmount);
	 * sort(varietalList, varietalRating, varietalAmount); } } if (varietalParse
	 * < varietalList.size()) { for (int i = 0; i < levelOne.size(); i++) { if
	 * (varietal == null) { if (levelOne.get(i).getVarietal().getGrapeType()
	 * .equals(varietalList.get(varietalParse))) levelTwo.add(levelOne.get(i));
	 * } else if (levelOne.get(i).getVarietal().getGrapeType()
	 * .equals(varietal)) { levelTwo.add(levelOne.get(i)); } } yearList.clear();
	 * yearRating.clear(); yearAmount.clear(); doLevelThree(true); } else {
	 * levelOne.clear(); wineTypeParse++; doLevelOne(false); } }
	 * 
	 * public void doLevelThree(boolean firstTime) { if (year != null &&
	 * !firstTime) { return; } if (year == null) { if (firstTime) { for (int i =
	 * 0; i < levelTwo.size(); i++) { Integer k = new Integer((int)
	 * levelTwo.get(i).getYear()); String d = k.toString(); int j =
	 * levelTwo.get(i).getRating(); place(d, j, yearList, yearRating,
	 * yearAmount); } getAverage(yearRating, yearAmount); sort(yearList,
	 * yearRating, yearAmount); } } if (yearParse < yearList.size()) { for (int
	 * i = 0; i < levelTwo.size(); i++) { Integer w = new Integer((int)
	 * levelTwo.get(i).getYear()); if (year == null) { if
	 * (w.equals(yearList.get(yearParse))) { levelThree.add(levelTwo.get(i)); }
	 * } else if (w.equals(year)) { levelThree.add(levelTwo.get(i)); } }
	 * vineyardList.clear(); vineyardRating.clear(); vineyardAmount.clear();
	 * doLevelFour(true);
	 * 
	 * } else { levelTwo.clear(); varietalParse++; doLevelTwo(false); } }
	 * 
	 * public void doLevelFour(boolean firstTime) { if (vineyard != null &&
	 * !firstTime) { return; } if (vineyard == null) { if (firstTime) { for (int
	 * d = 0; d < levelThree.size(); d++) { String s =
	 * levelThree.get(d).getVineyard().getName(); int i =
	 * levelThree.get(d).getRating(); place(s, i, vineyardList, vineyardRating,
	 * vineyardAmount); } getAverage(vineyardRating, vineyardAmount);
	 * sort(vineyardList, vineyardRating, vineyardAmount); } } if (vineyardParse
	 * < vineyardList.size()) { for (int i = 0; i < levelThree.size(); i++) { if
	 * (vineyard == null) { if (levelThree.get(i).getVineyard().getName()
	 * .equals(vineyardList.get(vineyardParse)))
	 * levelFour.add(levelThree.get(i)); } else if
	 * (levelThree.get(i).getVineyard().getName() .equals(vineyard)) {
	 * levelFour.add(levelThree.get(i)); } } appellationList.clear();
	 * appellationRating.clear(); appellationAmount.clear(); doLevelFive(true);
	 * } else { levelThree.clear(); yearParse++; doLevelThree(false); } }
	 * 
	 * public void doLevelFive(boolean firstTime) { if (firstTime) { for (int d
	 * = 0; d < levelFour.size(); d++) { String s =
	 * levelFour.get(d).getAppellation().getLocation(); int i =
	 * levelFour.get(d).getRating(); place(s, i, appellationList,
	 * appellationRating, appellationAmount); } getAverage(appellationRating,
	 * appellationAmount); sort(appellationList, appellationRating,
	 * appellationAmount); } while (suggestion.isEmpty() && (appellationParse <
	 * appellationList.size())) { selectWine(); if (suggestion.isEmpty())
	 * appellationParse++; } if (suggestion.isEmpty()) { levelFour.clear();
	 * vineyardParse++; doLevelFour(false); } }
	 */
	// the method called by WineWizardManager to select the Wine
	public ArrayList<Wine> selectWine()
	{
		// TODO get all rated wines sorted if none then return a bunch of
		// randoms
		// TODO get all the wines from cellar and/or online if user selects
		// neither
		doAverages();
		getSuggestionAlpha();
		return selected;

	}

	// sorts the attributes and rating based on rating
	public void sort(ArrayList<String> att, ArrayList<Integer> rating,
			ArrayList<Integer> amount)
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
	public void getAverage(ArrayList<Integer> rating, ArrayList<Integer> amount)
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
	public void place(String attribute, int rat, ArrayList<String> att,
			ArrayList<Integer> rating, ArrayList<Integer> amount)
	{
		Integer r = new Integer(rat);
		// if the list doesn't contain the attribute then it places it in there
		if (!att.contains(attribute))
		{
			att.add(attribute);
			rating.add(r);
			Integer one = new Integer(1);
			amount.add(one);
		} else
		{
			int k = typeList.indexOf(attribute);
			Integer d = new Integer(rating.get(k).intValue() + rat);
			rating.set(k, d);
			Integer q = new Integer(amount.get(k).intValue() + 1);
			amount.set(k, q);
		}
	}

	public Wine random()
	{
		int rand = (int) Math.random() * allTheWines.size();
		while (allTheWines.get(rand).getRating() > 2.5)
		{
			rand = (int) Math.random() * allTheWines.size();
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

	public ArrayList<Wine> findWine()
	{
		ArrayList<Wine> returnList = new ArrayList<Wine>();
		int count = 0;
		int atts = 0;
		// TODO Auto-generated method stub
		// TODO get all the wines and put it into allTheWines
		for (int i = 0; i < allTheWines.size(); i++)
		{
			if (wineType != null)
			{
				atts++;
				for (int q = 0; q < wineType.size(); q++)
				{

					if (allTheWines.get(i).getType().getWineType()
							.equals(wineType.get(q)))
					{
						count++;
					}
				}
			}
			// check to see if wines don't have the required varietal the user
			// set
			if (varietal != null)
			{
				atts++;
				if (allTheWines.get(i).getVarietal().getGrapeType()
						.equals(varietal))
				{
					count++;
				}
			}
			// check to see if wines don't have the required year the user set
			if (year != null)
			{
				Integer r = new Integer((int) allTheWines.get(i).getYear());
				String s = r.toString();// converts it to a string
				atts++;
				if (s.equals(year))
				{
					count++;
				}
			}
			// check to see if wines don't have the required vineyard the user
			// set
			if (vineyard != null)
			{
				atts++;
				if (allTheWines.get(i).getVineyard().getName().equals(vineyard))
				{
					count++;
				}
			}
			// check to see if wines don't have the required appellation the
			// user set
			if (appellation != null)
			{
				atts++;
				if (allTheWines.get(i).getAppellation().getLocation()
						.equals(appellation))
				{
					count++;
				}
			}
			if (atts == count)
			{
				returnList.add(allTheWines.get(i));
			}
		}
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
}