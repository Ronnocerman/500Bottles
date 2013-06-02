package com._500bottles.manager;

import java.util.Vector;

import com._500bottles.object.wine.Appellation;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineType;

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
	private static Vector<WineType> wineType = new Vector<WineType>();
	private static Vector<Appellation> appellation = new Vector<Appellation>();// set
	// for
	// advanced
	// search level five
	private static long minYear;// set for advanced search level three
	private static long maxYear;
	private static Vector<Varietal> varietal = new Vector<Varietal>();
	private static Vector<Vineyard> vineyard = new Vector<Vineyard>();// set for
	// advanced
	// level four
	private static long theID;
	private String wineName;
	private boolean fromCellar;// a flag to see if user wants wine from their
	// cellar
	private boolean fromWineAPI;// a flag to see if user wants wine from online
	private boolean fromSnooth;
	// the list of Rated wines from database
	private static Vector<Wine> wineListRated = new Vector<Wine>();
	// level one list of wines which account for the preferred wine type
	// the list of all rated types
	private static Vector<String> typeList = new Vector<String>();
	// the type rating
	private static Vector<Integer> typeRating = new Vector<Integer>();
	// the amount of that type
	private static Vector<Integer> typeAmount = new Vector<Integer>();
	// the list of the appellation that have been rated

	// the list of the amount of that appellation that have been rated
	// the list of varietals that have been rated
	private static Vector<String> varietalList = new Vector<String>();
	// the list of the varietals ratings
	private static Vector<Integer> varietalRating = new Vector<Integer>();
	// the list of the varietals amounts
	private static Vector<Integer> varietalAmount = new Vector<Integer>();
	private static Vector<Integer> varietalID = new Vector<Integer>();
	// the list of the vineyard that have been rated
	// all the wines the user can access will be swaped later for something more
	// dynamic
	private Vector<Integer> rate = new Vector<Integer>();// a list to keep
															// track of the
															// wines weights
															// aka sum
	private Vector<Wine> selected = new Vector<Wine>();// the list of
														// wines to be
														// returned
	private static Vector<Wine> levelOne = new Vector<Wine>();
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
		fromSnooth = false;
		fromWineAPI = false;
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
	private static void setWineType(Vector<WineType> vector)
	{
		wineType = vector;
	}

	// gets the wine types the user wants
	private static Vector<WineType> getWineType()
	{
		return wineType;
	}

	// sets the varietal the user wants
	private static void setVarietal(Vector<Varietal> bob)
	{
		varietal = bob;
	}

	// gets the varietal the user wants
	private static Vector<Varietal> getVarietal()
	{
		return varietal;
	}

	// sets the vineyard the user wants
	private static void setVineyard(Vector<Vineyard> vector)
	{
		vineyard = vector;
	}

	// gets the vineyard the user wants
	private static Vector<Vineyard> getVineyard()
	{
		return vineyard;
	}

	// sets the appellation the user wants
	private static void setAppellation(Vector<Appellation> vector)
	{
		appellation = vector;
	}

	// gets the appellation the user wants
	private static Vector<Appellation> getAppellation()
	{
		return appellation;
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
			/*
			 * s = wineListRated.get(d).getVarietal().getGrapeType(); place(s,
			 * k, varietalList, varietalRating, varietalAmount); Integer r = new
			 * Integer((int) wineListRated.get(d).getYear()); s = r.toString();
			 * place(s, k, yearList, yearRating, yearAmount); s =
			 * wineListRated.get(d).getVineyard().getName(); place(s, k,
			 * vineyardList, vineyardRating, vineyardAmount); s =
			 * wineListRated.get(d).getAppellation().getLocation(); place(s, k,
			 * appellationList, appellationRating, appellationAmount);
			 */
		}
		// averages and sorts the attributes
		getAverage(typeRating, typeAmount);
		sort(typeList, typeRating, typeAmount);
		/*
		 * getAverage(varietalRating, varietalAmount); sort(varietalList,
		 * varietalRating, varietalAmount); getAverage(yearRating, yearAmount);
		 * sort(yearList, yearRating, yearAmount); getAverage(vineyardRating,
		 * vineyardAmount); sort(vineyardList, vineyardRating, vineyardAmount);
		 * getAverage(appellationRating, appellationAmount);
		 * sort(appellationList, appellationRating, appellationAmount);
		 */
	}

	// the method called by WineWizardManager to select the Wine
	public static Vector<Wine> selectWine(WineQuery query)
	{
		// query object is the user settings
		WineQuery search = new WineQuery();// the query search for the suggested
											// wine
		setAppellation(query.getAppellation());// sets the appellation to the
												// query vector of the same type
		setVarietal(query.getVarietal());// sets the varietal to the query
											// vector of same type
		setWineType(query.getType());// sets the wine type to the query vector
										// of the same type
		setVineyard(query.getVineyard());// sets the vineyard to the query
											// vector of the same type
		setMinYear(query.getMinYear());// sets the min year to search
		setMaxYear(query.getMaxYear());// sets the max year to search for

		// TODO get all rated wines sorted if none then return a bunch of
		// randoms
		// TODO get all the wines from cellar and/or online if user selects
		// neither
		WineQuery getRating = new WineQuery();// the query to get all wines with
												// a rating of 1 to 5
		getRating.setMinRating(1);// sets the min rating to 1
		getRating.setMaxRating(5);// sets the max rating to 5
		wineListRated = WineManager.searchWine(getRating).getWines();// gets the
																		// list
																		// of
																		// wines
																		// that
																		// are
																		// rated
		if (!wineListRated.isEmpty())// if wineListRated is empty then don't do
										// this
		{
			getLevelOne();// does level one which in turn does level 2
			if (varietal.isEmpty())// if varietal is empty then it puts the top
									// rated varietals in its place
			{
				Vector<Varietal> app = new Vector<Varietal>();// vector of
																// varietal to
																// be built
				for (int i = 0; i < varietalList.size(); i++)// loop that builds
																// it based of
																// the
																// varietalList
				{
					Varietal newApp = new Varietal();// varietal to be made
					newApp.setGrapeType(varietalList.get(i));// gets the
																// varietal
																// grape type
																// and sets it
																// in newApp
					newApp.setId(varietalID.get(i).longValue());// gets the
																// varietal
																// grape type
																// and sets it
																// in newApp
					app.add(newApp);// adds it to newApp to the search query
									// list
				}
				search.setVarietal(app);// sets the varietal app vector for the
										// search query
			} else
			{
				search.setVarietal(getVarietal());// sets the varietal if
													// query's varietal was set
			}
			if (wineType.isEmpty())// if wine type is empty put the suggested
									// wine
			{
				Vector<WineType> app = new Vector<WineType>();// vector to put
																// the best
																// wines
				for (int i = 0; i < typeList.size(); i++)// the for loop that
															// fills the vector
															// app
				{
					WineType newApp = new WineType();// app filled with the wine
														// types of choice
					newApp.setWineType(typeList.get(i));// app fills it with the
														// typelist
					app.add(newApp);// puts these new wineTypes into the vector
				}
				search.setType(app);// sets search query with the wineTypes
			} else
			{
				search.setType(getWineType());// else sets the vector of the
												// query type and puts it into
												// search if it wasn't null
			}
			if (minYear != 0)
			{
				search.setMinYear(minYear);// sets the min year if it was set
			}
			if (maxYear != 0)
			{
				search.setMaxYear(maxYear);// sets the max year
			}
			if (!vineyard.isEmpty())
			{
				search.setVineyard(getVineyard());// sets the vineyard if the
													// query vineyard was set
			}
			if (!appellation.isEmpty())
			{
				search.setAppellation(getAppellation());// sets the appellation
														// if the query vineyard
														// was set
			}
		}
		search.setSize(query.getSize());// sets the size of what the user wants
		return WineManager.searchWine(search).getWines();// searchs for the
															// wines with the
															// traits
	}

	private static void getLevelOne()
	{

		if (wineType.isEmpty())// checks to see if wine type is empty
		{
			for (int i = 0; i < wineListRated.size(); i++)//
			{
				Integer k = (int) ((int) 100 * wineListRated.get(i).getRating());// gets
																					// rating
																					// time
																					// 100
																					// to
																					// make
																					// sure
																					// no
																					// numbers
																					// were
																					// dropped
				String s = wineListRated.get(i).getType().getWineType();// gets
																		// the
																		// wineType
				place(s, k.intValue(), typeList, typeRating, typeAmount);// places
																			// it
																			// in
																			// the
																			// wineTypelist
																			// along
																			// with
																			// rating
			}
			getAverage(typeRating, typeAmount);// gets the average of typeRating
												// by using typeAmount
			sort(typeList, typeRating, typeAmount);// sorts the typeList based
													// on rating
			while (3 < typeList.size())// while the type list is bigger then 3
										// it decreases it
			{
				typeList.remove(typeList.size() - 1);// removes last element aka
														// least favorite
			}
			for (int r = 0; r < wineListRated.size(); r++)// traverse
															// wineListRated to
															// find the rated
															// wines with the 3
															// types that were
															// chosen
			{
				int test = 0;// checks to see if the wine had at least one type
								// in common
				for (int y = 0; y < typeList.size(); y++)
				{
					if (wineListRated.get(r).getType().getWineType()
							.compareTo(typeList.get(y)) == 0)
						test++;
				}
				if (test > 0)// if wine had at least one in common add it to
								// levelOne
				{
					levelOne.add((wineListRated.get(r)));
				}

			}

		} else
		{
			for (int r = 0; r < wineListRated.size(); r++)// checks to see if
															// the wines that
															// are rated have a
															// wineType that was
															// selected
			{
				int test = 0;
				for (int y = 0; y < wineType.size(); y++)
				{
					if (wineListRated.get(r).getType().getWineType()
							.compareTo(wineType.get(y).getWineType()) == 0)
						test++;
				}
				if (test > 0)
				{
					levelOne.add((wineListRated.get(r)));
				}
			}
		}// calls level 2
		getLevelTwo();
	}

	private static void getLevelTwo()// checks to make sure
	{
		// if varietal is empty then do this
		if (varietal.isEmpty())
		{
			// gets the highest rated varietal of the list
			for (int i = 0; i < levelOne.size(); i++)
			{
				Integer k = (int) ((int) 100 * levelOne.get(i).getRating());// gets
																			// the
																			// wine
																			// rating
				String s = levelOne.get(i).getVarietal().getGrapeType();// gets
																		// the
																		// grapetype
				theID = levelOne.get(i).getVarietal().getId();// sets the id to
																// a class
																// variable to
																// be used in
																// the place
																// function
				place(s, k.intValue(), varietalList, varietalRating,
						varietalAmount);// places the item in the list
			}
			getAverage(varietalRating, varietalAmount);// sets the variteal
														// average
			sort(varietalList, varietalRating, varietalID);// sorts the
															// varietals lists
															// including the id
			while (varietalList.size() > 3)// gets the 3 best varietals only
			{
				varietalList.remove(varietalList.size() - 1);
			}
		}
	}

	private static void setMinYear(long l)
	{
		minYear = l;

	}

	// sorts the attributes and rating based on rating
	public static void sort(Vector<String> att, Vector<Integer> rating,
			Vector<Integer> amount)
	{
		for (int i = 0; i < rating.size(); i++)// first for loop goes from
												// beginning to end
		{
			for (int j = rating.size() - 1; j > i; j--)// end to other loop
			{
				if (rating.get(j) > rating.get(i))// if one is less then the
													// other then switch
				{
					//
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
	public static void getAverage(Vector<Integer> rating, Vector<Integer> amount)
	{
		// gets average rating of each one
		for (int i = 0; i < rating.size(); i++)
		{
			int d = rating.get(i).intValue();
			int q = amount.get(i).intValue();
			Integer avg = new Integer(d / q);
			rating.set(i, avg);
		}

	}

	// places the attribute in the list whether new or already in the list
	public static void place(String attribute, int rat, Vector<String> att,
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
			if (att.equals(varietalList))
			{
				varietalID.add((int) theID);
			}
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

	public boolean isFromSnooth()
	{
		return fromSnooth;
	}

	public void setFromOnline(boolean fromSnooth)
	{
		this.fromSnooth = fromSnooth;
	}

	public boolean isFromWineAPI()
	{
		return fromWineAPI;
	}

	public void setFromWineAPI(boolean fromWineAPI)
	{
		this.fromWineAPI = fromWineAPI;
	}

	public Vector<Wine> getSelected()
	{
		return selected;
	}

	public void setSelected(Vector<Wine> selected)
	{
		this.selected = selected;
	}

	public long getMaxYear()
	{
		return maxYear;
	}

	public static void setMaxYear(long l)
	{
		maxYear = l;
	}
}