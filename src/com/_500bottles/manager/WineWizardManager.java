package com._500bottles.manager;

import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;

import com._500bottles.da.external.snooth.exception.InvalidSort;
import com._500bottles.da.external.wine.exception.InvalidCategory;
import com._500bottles.da.external.wine.exception.InvalidOtherParameters;
import com._500bottles.da.internal.VarietalDAO;
import com._500bottles.da.internal.VineyardDAO;
import com._500bottles.da.internal.WineTypeDAO;
import com._500bottles.exception.da.DAException;
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
	private static long wineTypeID;
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
	private static Vector<Integer> typeID = new Vector<Integer>();
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
		/*
		 * try { wineType.get(0).setWineTypeId(
		 * WineTypeDAO.getWineType(wineType.get(0).getWineType())
		 * .getWineTypeId()); } catch (DAException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
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
		/*
		 * try { varietal.get(0).setId(
		 * VarietalDAO.getVarietal(varietal.get(0).getGrapeType()) .getId()); }
		 * catch (DAException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
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
		/*
		 * try { vineyard.get(0).setId(
		 * VineyardDAO.getVineyard(vineyard.get(0).getName()).getId()); } catch
		 * (DAException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
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
	 * @throws ParseException
	 * @throws IOException
	 * @throws InvalidOtherParameters
	 * @throws InvalidSort
	 * @throws InvalidCategory
	 * @throws DAException
	 */
	public static WineQueryResult selectWine(WineQuery query)
			throws DAException, InvalidCategory, InvalidSort,
			InvalidOtherParameters, IOException, ParseException
	{
		if (query.getType().size() > 0)
		{
			if (query.getType().get(0).getWineType().equals("undefined")
					|| query.getType().get(0).getWineType().equals("type"))
			{
				Vector<WineType> blank = new Vector<WineType>();
				query.setType(blank);
				// System.out.println("does it get here");
			}
		}
		if (query.getVarietal().size() > 0)
		{
			if (query.getVarietal().get(0).getGrapeType()
					.equals("grape varietal"))
			{
				Vector<Varietal> blank = new Vector<Varietal>();
				query.setVarietal(blank);
				// System.out.println("dance with me");
			}
		}

		// System.out.println("query.getType().size() " +
		// query.getType().size());
		// query object is the user settings
		WineQuery search = new WineQuery();// the query search for the suggested
											// wine
		setAppellation(query.getAppellation());// sets the appellation to the
												// query vector of the same type
		setVarietal(query.getVarietal());// sets the varietal to the query
											// vector of same type
		setWineType(query.getType());// sets the wine type to the query vector
										// of the same type
		if (query.getType().size() > 0)
		{
			System.out.println("wine type set? "
					+ query.getType().get(0).getWineType());
		}

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
		getRating.setMinRating(0);// sets the min rating to 0
		getRating.setMaxRating(5);// sets the max rating to 5
		getRating.setSize(250);
		// gets the list of wines that are rated
		wineListRated = WineManager.searchWine(getRating).getWines();
		// System.out.println("wineListRated.size()" + wineListRated.size());
		for (int u = 0; u < wineListRated.size(); u++)
		{
			WineType bob = WineTypeDAO.getWineTypeById(wineListRated.get(u)
					.getType().getWineTypeId());
			wineListRated.get(u).getType().setWineType(bob.getWineType());
		}

		if (!vineyard.isEmpty() && !wineListRated.isEmpty())
		{
			int r = 0;
			Vineyard vv = VineyardDAO.getVineyardById(wineListRated.get(r)
					.getVineyard().getId());
			// /System.out
			// .println("reaches the scenario of vineyard not null and wine list rated not 0");
			while (r < wineListRated.size())
			{

				if (vineyard.get(0).getName().equals(vv.getName()))
				{
					r++;
				} else
				{
					wineListRated.remove(r);
				}
			}
		}
		// System.out.println("WineListRated.size() after check: "
		// + wineListRated.size());
		if (!wineListRated.isEmpty())// if wineListRated is empty then don't do
										// this
		{
			boolean doLevelOne = true;
			// If varietal is empty and wineType is empty
			if (varietal.size() >= 1)
			{
				doLevelOne = false;
				// System.out.println("gets into corner case");
				for (int i = 0; i < wineListRated.size(); i++)
				{
					int test = 0;
					for (int d = 0; d < varietal.size(); d++)
					{
						String varietalName = VarietalDAO.getVarietalById(
								wineListRated.get(i).getVarietal().getId())
								.getGrapeType();
						// System.out
						// .println("string varietalName in corner case "
						// + varietalName);
						if (varietal.get(d).getGrapeType().equals(varietalName))
						{
							test++;
							// System.out.println("shouldn't have problems");
						}
					}
					if (test >= 1)
						levelOne.add(wineListRated.get(i));

				}
			}
			if (doLevelOne)
				getLevelOne();// does level one which in turn does level 2
			else
			{
				getLevelTwo();
				wineType.clear();
				if (levelOne.size() >= 1)
					wineType.add(levelOne.get(0).getType());
				else
				{
					Vector<Wine> wi = new Vector<Wine>();
					WineQueryResult returnResult = new WineQueryResult(wi);
					return returnResult;
				}
				// TODO get it to create the wine type value
			}
			if (varietal.size() < 1)// if varietal is empty then it puts the top
									// rated varietals in its place
			{
				// System.out.println("does it get into varietal==null?");
				// vector of varietal to be built
				Vector<Varietal> app = new Vector<Varietal>();
				// varietal to be made gets the varietal grape type and sets
				// it in newApp
				Varietal newApp = new Varietal();
				newApp.setId(varietalID.get(0).longValue());
				newApp.setGrapeType(varietalList.get(0));

				app.add(newApp);// adds it to newApp to the search query
								// list
				if (app.size() >= 1)
				{
					// System.out.println("******************");
					search.setVarietal(app);// sets the varietal app vector for

				} else
				{
					search.setVarietal(null);
				}
				// search query
			} else
			{
				// sets the varietal if query's varietal was set
				Varietal var = VarietalDAO.getVarietal(varietal.get(0)
						.getGrapeType());
				varietal.clear();
				varietal.add(var);
				search.setVarietal(getVarietal());
			}
			if (wineType.size() < 1)// if wine type is empty put the suggested
									// wine
			{
				Vector<WineType> app = new Vector<WineType>();
				// vector to put the best wines
				// the for loop that fills the vector app
				// System.out.println("typeList.size() " + typeList.size());
				for (int i = 0; i < typeList.size(); i++)
				{
					// app filled with the wine types of choice
					WineType newApp = new WineType();
					// app fills it with the typelist
					// System.out.println("typeList.get(i) " + typeList.get(i));
					@SuppressWarnings("unused")
					String type = typeList.get(i);
					newApp.setWineTypeId(wineTypeID);
					// if (newApp == null)
					// System.out.println("newApp is null wtf barque");
					newApp.setWineType(typeList.get(i));

					app.add(newApp);// puts these new wineTypes into the vector
				}
				// System.out.println("wineType app.size() " + app.size());
				if (app.size() >= 1)
				{
					search.setType(app);// sets search query with the wineTypes
					// System.out.println("do you get into this if statement");
				} else
				{
					search.setType(null);
				}
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
			if (query.getMinPrice() != 0)
			{
				search.setMinPrice(query.getMinPrice());
			}
			if (query.getMaxPrice() != 0)
			{
				search.setMaxPrice(query.getMaxPrice());
			}

			if (vineyard.size() < 1)
			{
				// sets the vineyard if the query vineyard was set
				search.setVineyard(getVineyard());
			}
			if (appellation.size() < 1)
			{
				// sets the appellation if the query vineyard was set
				search.setAppellation(getAppellation());
			}
		} else
		{
			search.setAppellation(query.getAppellation());
			search.setVarietal(getVarietal());
			search.setVineyard(getVineyard());
			search.setMinPrice(query.getMinPrice());
			search.setMaxPrice(query.getMaxPrice());
			search.setMinYear(minYear);
			search.setMaxYear(maxYear);
			search.setType(getWineType());
		}
		search.setSize(wineListRated.size());// sets the size of what the user
												// wants
		// searchs for the wines with the traits
		if (search.getType() != null)
		{
			// System.out.println("search wine type size = "
			// + search.getType().size());
			// System.out.println("search Wine Type name: "
			// + search.getType().get(0).getWineType());
			// System.out.println("search Wine Type id: "
			// + search.getType().get(0).getWineTypeId());
		} else
			System.out.println("search wine type is null");
		// System.out.println(" varietalList.size() at this location is"
		// + varietalList.size());
		search.setSize(query.getSize());
		if (search.getVarietal() != null)
		{
			// System.out.println("varietlList.size() " + varietalList.size());
			// System.out.println("search varietal = "
			// + search.getVarietal().size());
			// System.out.println("search varietal name: "
			// + search.getVarietal().get(0).getGrapeType());
			// System.out.println("search varietal id: "
			// + search.getVarietal().get(0).getId());
		}

		WineQueryResult result = WineManager.searchWine(search);
		levelOne.clear();
		wineListRated.clear();
		varietal.clear();
		vineyard.clear();
		wineType.clear();
		appellation.clear();
		typeList.clear();
		typeRating.clear();
		typeAmount.clear();
		typeID.clear();
		varietalList.clear();
		varietalAmount.clear();
		varietalID.clear();
		varietalRating.clear();
		minYear = 0;
		maxYear = 0;
		theID = 0;
		if (result == null)
		{
			Vector<Wine> wines = new Vector<Wine>();
			// System.out.println("result is null");
			result = new WineQueryResult(wines);
		}
		// System.out.println("result.getWines().size() "
		// + result.getWines().size());

		return result;
	}

	/**
	 * getLevelOne gets the preferred wineType of the user
	 */
	private static void getLevelOne()
	{
		// System.out.println("wineType.size() at entry levelOne.size() "
		// + wineType.size());
		if (wineType.size() < 1)// checks to see if wine type is empty
		{
			// System.out.println("First part of getLevelOne");
			// System.out.println("wineListRated inside of getLevelOne"
			// + wineListRated.size());
			for (int i = 0; i < wineListRated.size(); i++)//
			{
				// gets rating time 100 to make sure no numbers were dropped
				// System.out.println("the number of wineListRated "+
				// wineListRated.size());
				// System.out.println("the number it fails at is " + i);
				Integer k = (int) ((int) 100 * wineListRated.get(i).getRating());
				// gets the wineType
				WineType wineT = new WineType();
				String s = "frog dddddddd";
				try
				{
					wineT = WineTypeDAO.getWineTypeById(wineListRated.get(i)
							.getType().getWineTypeId());
					s = wineT.getWineType();
					theID = wineT.getWineTypeId();
					// System.out.println("string s: " + s);
				} catch (DAException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println("this wineType is "
				// + wineListRated.get(i).getType().getWineType());
				// places it in the wineTypelist along with rating
				place(s, k.intValue(), typeList, typeRating, typeAmount);

			}
			getAverage(typeRating, typeAmount);// gets the average of typeRating
												// by using typeAmount
			sort(typeList, typeRating, typeID);// sorts the typeList based
												// on rating
			while (1 < typeList.size())// while the type list is bigger then 3
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
					try
					{
						if (WineTypeDAO
								.getWineTypeById(
										wineListRated.get(r).getType()
												.getWineTypeId()).getWineType()
								.compareTo(typeList.get(y)) == 0)
							test++;
					} catch (DAException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
			// System.out.println("gets into getLevelOne part 2");
			// System.out.println("wineType.get(0).getWineType() at^ "
			// + wineType.get(0).getWineType());
			for (int r = 0; r < wineListRated.size(); r++)
			{
				int test = 0;
				for (int y = 0; y < wineType.size(); y++)
				{
					try
					{
						if (WineTypeDAO
								.getWineTypeById(
										wineListRated.get(r).getType()
												.getWineTypeId()).getWineType()
								.compareTo(wineType.get(y).getWineType()) == 0)
							test++;
					} catch (DAException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (test > 0)
				{

					levelOne.add((wineListRated.get(r)));
				}
			}
		}// calls level 2
			// System.out.println("levelOne.size()" + levelOne.size());
		// System.out.println("levelOne.size() at end of getLevelOne "
		// + levelOne.size());
		getLevelTwo();
	}

	/**
	 * getLevelTwo gets the preferred varietal
	 */
	private static void getLevelTwo()// checks to make sure
	{
		if (!levelOne.isEmpty())
		{
			wineTypeID = levelOne.get(0).getType().getWineTypeId();
		}

		// if varietal is empty then do this
		if (varietal.size() < 1)
		{
			// System.out.println("levelOne.size() levelOne " +
			// levelOne.size());
			// gets the highest rated varietal of the list
			for (int i = 0; i < levelOne.size(); i++)
			{
				// gets the wine rating
				Integer k = (int) ((int) 100 * levelOne.get(i).getRating());
				// gets the grapetype
				String s = levelOne.get(i).getVarietal().getGrapeType();
				// System.out.println("string s " + s);
				// sets the id to a class variable to be used in the place
				// function
				theID = levelOne.get(i).getVarietal().getId();
				try
				{
					s = VarietalDAO.getVarietalById(theID).getGrapeType();
					// System.out.println("varietal string s: " + s);
				} catch (DAException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println("theID: " + theID);
				// System.out.println("theRating: " + k);
				// System.out.println("the varietal " + s);
				place(s, k.intValue(), varietalList, varietalRating,
						varietalAmount);// places the item in the list
			}
			getAverage(varietalRating, varietalAmount);// sets the variteal
														// average
			sort(varietalList, varietalRating, varietalID);// sorts the
															// varietals lists
			// including the id
			// System.out.println("varietallist.get(0) " + varietalID.get(0));
			// System.out.println("******varieatList.get(0) "
			// + varietalList.get(0));
			// System.out.println("varietalID.get()" + varietalID.get(0));
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
		// if (att.equals(varietalList))
		// System.out.println("amount.size()" + amount.size());
		// first for loop goes from beginning to end
		for (int i = 0; i < rating.size(); i++)
		{
			for (int j = rating.size() - 1; j > i; j--)// end to other loop
			{
				// if one is less then the other then switch
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
					// if (att.equals(varietalList))
					// System.out.println("gets into this if state");
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
			// if (att.equals(varietalList))
			// System.out.println("gets into this if state");
			if (att.equals(varietalList))
			{
				varietalID.add((int) theID);
			}
			if (att.equals(typeList))
			{
				typeID.add((int) theID);
				if (theID == 12)
				{
					// System.out.println("********theID****** " + theID);
				}
			}
		} else
		{
			// System.out.println("gets in here******");
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
