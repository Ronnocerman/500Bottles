package com._500bottles.manager;

import java.util.Vector;

import com._500bottles.object.wine.Appellation;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;
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
	private static Vector<Wine> levelOne = new Vector<Wine>();

	// constructor for WineWizardManager
	public WineWizardManager()
	{

	}

	/**
	 * Sets the wineType vector to vector
	 * 
	 * @param vector
	 *            a vector to set wineType to
	 */
	// sets the wine types that the user wants
	private static void setWineType(Vector<WineType> vector)
	{
		wineType = vector;
	}

	/**
	 * @return the WineType vector
	 */
	// gets the wine types the user wants
	private static Vector<WineType> getWineType()
	{
		return wineType;
	}

	/**
	 * sets the varietal vector
	 * 
	 * @param bob
	 *            sets Varietal vector to bob vector
	 */
	// sets the varietal the user wants
	private static void setVarietal(Vector<Varietal> bob)
	{
		varietal = bob;
	}

	/**
	 * gets the Varietal vector
	 * 
	 * @return varietal vector
	 */
	// gets the varietal the user wants
	private static Vector<Varietal> getVarietal()
	{
		return varietal;
	}

	/**
	 * sets the vineyard the user wants
	 * 
	 * @param vector
	 *            sets vineyard to vector
	 */
	// sets the vineyard the user wants
	private static void setVineyard(Vector<Vineyard> vector)
	{
		vineyard = vector;
	}

	/**
	 * gets the vineyard the user wants
	 * 
	 * @return vineyard
	 */
	private static Vector<Vineyard> getVineyard()
	{
		return vineyard;
	}

	/**
	 * sets the appellation the user wants
	 * 
	 * @param vector
	 *            sets appellation to vector
	 */
	// sets the appellation the user wants
	private static void setAppellation(Vector<Appellation> vector)
	{
		appellation = vector;
	}

	/**
	 * returns appellation
	 * 
	 * @return gets appellation
	 */
	// gets the appellation the user wants
	private static Vector<Appellation> getAppellation()
	{
		return appellation;
	}

	/**
	 * the method called by WineWizardManager to select the Wine
	 * 
	 * @param query
	 *            a query that accounts for the traits
	 * 
	 * @return returns a vector of suggested wine
	 */
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
		// gets the list of wines that are rated
		wineListRated = WineManager.searchWine(getRating).getWines();
		// System.out.println("a Varietal "
		// + wineListRated.get(0).getVarietal().getGrapeType());
		// System.out.println("the number of wineListRated "
		// + wineListRated.size());
		if (!wineListRated.isEmpty())// if wineListRated is empty then don't do
										// this
		{
			// If varietal is empty and wineType is empty
			if (!varietal.isEmpty() && wineType.isEmpty())
			{
				int i = 0;
				while (i < wineListRated.size())
				{
					int test = 0;
					for (int d = 0; d < varietal.size(); d++)
					{
						if (varietal.get(d).getId() == wineListRated.get(i)
								.getVarietal().getId())
							test++;
					}
					if (test == 0)
						wineListRated.remove(i);
					else
					{
						i++;
					}
				}
			}
			getLevelOne();// does level one which in turn does level 2
			if (varietal.isEmpty())// if varietal is empty then it puts the top
									// rated varietals in its place
			{
				// vector of varietal to be built
				Vector<Varietal> app = new Vector<Varietal>();
				// loop that builds it based of the varietalList
				for (int i = 0; i < varietalList.size(); i++)

				{
					// varietal to be made gets the varietal grape type and sets
					// it in newApp
					Varietal newApp = new Varietal();
					newApp.setGrapeType(varietalList.get(i));
					// gets the varietal grape type and sets it in newApp
					newApp.setId(varietalID.get(i).longValue());

					app.add(newApp);// adds it to newApp to the search query
									// list
				}
				search.setVarietal(app);// sets the varietal app vector for the
										// search query
			} else
			{
				// sets the varietal if query's varietal was set
				search.setVarietal(getVarietal());
			}
			if (wineType.isEmpty())// if wine type is empty put the suggested
									// wine
			{
				Vector<WineType> app = new Vector<WineType>();
				// vector to put the best wines
				// the for loop that fills the vector app
				for (int i = 0; i < typeList.size(); i++)
				{
					// app filled with the wine types of choice
					WineType newApp = new WineType();
					// app fills it with the typelist
					newApp.setWineType(typeList.get(i));
					app.add(newApp);// puts these new wineTypes into the vector
				}
				search.setType(app);// sets search query with the wineTypes
			} else
			{
				// else sets the vector of the query type and puts it into
				// search if it wasn't null
				search.setType(getWineType());
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
				// sets the vineyard if the query vineyard was set
				search.setVineyard(getVineyard());
			}
			if (!appellation.isEmpty())
			{
				// sets the appellation if the query vineyard was set
				search.setAppellation(getAppellation());
			}
		}
		search.setSize(wineListRated.size());// sets the size of what the user
												// wants
		// searchs for the wines with the traits
		// System.out.println(typeList.size());
		WineQueryResult doug = WineManager.searchWine(search);
		if (doug == null)
			return null;
		Vector<Wine> returnWineVector = doug.getWines();
		return returnWineVector;

	}

	/**
	 * getLevelOne gets the preferred wineType of the user
	 */
	private static void getLevelOne()
	{

		if (wineType.isEmpty())// checks to see if wine type is empty
		{
			for (int i = 0; i < wineListRated.size(); i++)//
			{
				// gets rating time 100 to make sure no numbers were dropped
				// System.out.println("the number of wineListRated "+
				// wineListRated.size());
				// System.out.println("the number it fails at is " + i);
				Integer k = (int) ((int) 100 * wineListRated.get(i).getRating());
				// gets the wineType
				String s = wineListRated.get(i).getType().getWineType();
				// places it in the wineTypelist along with rating
				place(s, k.intValue(), typeList, typeRating, typeAmount);

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
			// traverse wineListRated to find the rated wines with the 3 types
			// that were chosen
			for (int r = 0; r < wineListRated.size(); r++)
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
			// checks to see if the wines that are rated have a wineType that
			// was selected
			for (int r = 0; r < wineListRated.size(); r++)
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

	/**
	 * getLevelTwo gets the preferred varietal
	 */
	private static void getLevelTwo()// checks to make sure
	{
		// if varietal is empty then do this
		if (varietal.isEmpty())
		{
			// gets the highest rated varietal of the list
			for (int i = 0; i < levelOne.size(); i++)
			{
				// gets the wine rating
				Integer k = (int) ((int) 100 * levelOne.get(i).getRating());
				// gets the grapetype
				String s = levelOne.get(i).getVarietal().getGrapeType();
				// sets the id to a class variable to be used in the place
				// function
				theID = levelOne.get(i).getVarietal().getId();
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

	/**
	 * gets the MinYear
	 * 
	 * @param l
	 *            a long variable to set the minYear
	 */

	private static void setMinYear(long l)
	{
		minYear = l;

	}

	/**
	 * sorts the attributes and rating based on rating
	 * 
	 * @param att
	 *            the attribute
	 * 
	 * @param rating
	 *            average rating of the attribute
	 * 
	 * @param amount
	 *            the amount of the things that have that trait
	 */
	// sorts the attributes and rating based on rating
	public static void sort(Vector<String> att, Vector<Integer> rating,
			Vector<Integer> amount)
	{
		// first for loop goes from beginning to end
		for (int i = 0; i < rating.size(); i++)
		{
			for (int j = rating.size() - 1; j > i; j--)// end to other loop
			{
				// if one is less then the other then switch
				if (rating.get(j) > rating.get(i))
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

	/**
	 * gets the average of the a rating with the amount there
	 * 
	 * @param rating
	 *            the sum of all rating of the attributes
	 * 
	 * @param amount
	 *            the amount to divide the ratings by
	 */
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

	/**
	 * places the attribute along with its rating and the amount and if it is a
	 * varieatal adds its ID
	 * 
	 * @param attribute
	 *            the attribute to be put in
	 * 
	 * @param rat
	 *            the rating of that attribute
	 * 
	 * @param att
	 *            the attribute vector
	 * 
	 * @param rating
	 *            the vector of ratings
	 * 
	 * @param amount
	 *            the vector with the amount
	 */
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

	/**
	 * getMaxYear
	 * 
	 * @return maxYear
	 */

	public long getMaxYear()
	{
		return maxYear;
	}

	/**
	 * sets max year
	 * 
	 * @param l
	 *            sets maxYear to l
	 */

	public static void setMaxYear(long l)
	{
		maxYear = l;
	}
}