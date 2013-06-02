package com._500bottles.object.wine;

import java.util.Vector;

import com._500bottles.object.geolocation.GeoLocation;

/**
 *
 */
public class WineQuery
{
	public final static int DEFAULT_OFFSET = 0;
	public final static int DEFAULT_SIZE = 5;
	public final static Vector<Long> DEFAULT_IDS = new Vector<Long>();
	public final static String DEFAULT_TEXT_QUERY = "";
	public final static String DEFAULT_NAME_CONTAINS = "";
	public final static String DEFAULT_DESC_CONTAINS = "";
	public final static GeoLocation DEFAULT_GEOLOCATION = null;
	public final static int DEFAULT_DISTANCE = -1;
	public final static Vector<WineType> DEFAULT_WINE_TYPE = new Vector<WineType>();
	public final static long DEFAULT_MIN_YEAR = 0;
	public final static long DEFAULT_MAX_YEAR = 0;
	public final static Vector<Appellation> DEFAULT_APPELLATION = new Vector<Appellation>();
	public final static Vector<Varietal> DEFAULT_VARIETAL = new Vector<Varietal>();
	public final static Vector<Vineyard> DEFAULT_VINEYARD = new Vector<Vineyard>();
	public final static double DEFAULT_MIN_RATING = -1.0;
	public final static double DEFAULT_MAX_RATING = -1.0;
	public final static double DEFAULT_MIN_PRICE = 0;
	public final static double DEFAULT_MAX_PRICE = 0;

	private int offset;
	private int size;
	/*  */
	private Vector<Long> ids;

	/*  */
	private String textQuery;

	/*  */
	private String nameContains;

	/*  */
	private String descriptionContains;

	/*  */
	private GeoLocation geoLocation;

	/*  */
	private int distance;

	/*  */
	private Vector<WineType> type;

	/*  */
	private long minYear;

	/*  */
	private long maxYear;

	/*  */
	private Vector<Appellation> appellation;

	/*  */
	private Vector<Varietal> varietal;

	/*  */
	private Vector<Vineyard> vineyard;

	/*  */
	private double minRating;

	/*  */
	private double maxRating;

	/*  */
	private double minPrice;

	/*  */
	private double maxPrice;

	/**
	 * Default constructor.
	 */
	public WineQuery()
	{
		this.setIds(DEFAULT_IDS);
		this.setTextQuery(DEFAULT_TEXT_QUERY);
		this.setNameContains(DEFAULT_NAME_CONTAINS);
		this.setDescriptionContains(DEFAULT_DESC_CONTAINS);
		this.setGeoLocation(DEFAULT_GEOLOCATION);
		this.setDistance(DEFAULT_DISTANCE);
		this.setType(DEFAULT_WINE_TYPE);
		this.setMinYear(DEFAULT_MIN_YEAR);
		this.setMaxYear(DEFAULT_MAX_YEAR);
		this.setAppellation(DEFAULT_APPELLATION);
		this.setVarietal(DEFAULT_VARIETAL);
		this.setVineyard(DEFAULT_VINEYARD);
		this.setMinRating(DEFAULT_MIN_RATING);
		this.setMaxRating(DEFAULT_MAX_RATING);
		this.setSize(DEFAULT_SIZE);
		this.setOffset(DEFAULT_OFFSET);
	}

	/**
	 * Gets the vector of Wine IDs set for this query.
	 * 
	 * @return Returns a Vector of Long id's set for this query. If no id's were
	 *         set an empty vector is returned.
	 */
	public Vector<Long> getIds()
	{
		return ids;
	}

	/**
	 * Sets the Wine ID for this query.
	 * 
	 * @param ids
	 */
	public void setIds(Vector<Long> ids)
	{
		this.ids = ids;
	}

	/**
	 * Sets the text query for this WineQuery.
	 * 
	 * @param textQuery
	 */
	public void setTextQuery(String textQuery)
	{
		this.textQuery = textQuery;
	}

	/**
	 * Gets the text query for this WineQuery. If no text query was specified
	 * then an empty string is returned.
	 * 
	 * @return Text query for this WineQuery, or an empty string.
	 */
	public String getTextQuery()
	{
		return this.textQuery;
	}

	/**
	 * Gets the "Name Contains" search field for this query. If not specified
	 * returns an empty string.
	 * 
	 * @return "Name Contains" field or an empty string.
	 */
	public String getNameContains()
	{
		return nameContains;
	}

	/**
	 * Sets the "Name Contains" field for this query.
	 * 
	 * @param nameContains
	 */
	public void setNameContains(String nameContains)
	{
		this.nameContains = nameContains;
	}

	/**
	 * Gets the "Description Contains" field for this query. If not specified
	 * returns an empty string.
	 * 
	 * @return "Description Contains" field or an empty string.
	 */
	public String getDescriptionContains()
	{
		return descriptionContains;
	}

	/**
	 * Sets the "Description Contains" field for this query.
	 * 
	 * @param descriptionContains
	 */
	public void setDescriptionContains(String descriptionContains)
	{
		this.descriptionContains = descriptionContains;
	}

	/**
	 * Gets the geolocation object for this query. If no geolocation object was
	 * specified, it returns null.
	 * 
	 * @return GeoLocation for this WineQuery or null if not specified.
	 */
	public GeoLocation getGeoLocation()
	{
		return geoLocation;
	}

	/**
	 * Sets the geolocation object for this query.
	 * 
	 * @param geoLocation
	 */
	public void setGeoLocation(GeoLocation geoLocation)
	{
		this.geoLocation = geoLocation;
	}

	/**
	 * Gets the distance set for this query. If no distance was specified it
	 * returns -1.
	 * 
	 * @return The distance set for this WineQuery or -1.
	 */
	public int getDistance()
	{
		return distance;
	}

	/**
	 * Sets the distance for this query.
	 * 
	 * @param distance
	 */
	public void setDistance(int distance)
	{
		this.distance = distance;
	}

	/**
	 * Gets the WineType set for this query or null if not specified.
	 * 
	 * @return WineType for this query or null.
	 */
	public Vector<WineType> getType()
	{
		return type;
	}

	/**
	 * Sets the WineType for this query.
	 * 
	 * @param defaultWineType
	 */
	public void setType(Vector<WineType> defaultWineType)
	{
		this.type = defaultWineType;
	}

	/**
	 * Gets the minimum year for this wine query. If not specified, it will
	 * return null.
	 * 
	 * @return Minimum year for this query or null.
	 */
	public long getMinYear()
	{
		return minYear;
	}

	/**
	 * Sets the minimum year for this query.
	 * 
	 * @param minYear
	 */
	public void setMinYear(long minYear)
	{
		this.minYear = minYear;
	}

	/**
	 * Gets the maximum year for this query. If not specified, it will return
	 * null.
	 * 
	 * @return Maximum year for this query or null.
	 */
	public long getMaxYear()
	{
		return maxYear;
	}

	/**
	 * Sets the maximum year for this query.
	 * 
	 * @param maxYear
	 */
	public void setMaxYear(long maxYear)
	{
		this.maxYear = maxYear;
	}

	/**
	 * Gets the Appellation set for this query or null if not specified.
	 * 
	 * @return Appellation for this query or null.
	 */
	public Vector<Appellation> getAppellation()
	{
		return appellation;
	}

	/**
	 * Sets the Appellation for this query.
	 * 
	 * @param defaultAppellation
	 */
	public void setAppellation(Vector<Appellation> defaultAppellation)
	{
		this.appellation = defaultAppellation;
	}

	/**
	 * Gets the Vector of varietals for this query. If no varietals were
	 * specified, it will return an empty Vector.
	 * 
	 * @return Vector of varietals for this query.
	 */
	public Vector<Varietal> getVarietal()
	{
		return varietal;
	}

	/**
	 * Sets the Varietals for this query.
	 * 
	 * @param varietal
	 */
	public void setVarietal(Vector<Varietal> varietal)
	{
		this.varietal = varietal;
	}

	/**
	 * Gets the Vector of Vineyards for this query. If no vineyards were
	 * specified, it will return an empty vector.
	 * 
	 * @return Vector of vineyards for this query.
	 */
	public Vector<Vineyard> getVineyard()
	{
		return vineyard;
	}

	/**
	 * Sets the Vineyards for this query.
	 * 
	 * @param vineyard
	 */
	public void setVineyard(Vector<Vineyard> vineyard)
	{
		this.vineyard = vineyard;
	}

	/**
	 * Gets the minimum rating for this query. If no minimum rating was
	 * specified it will return -1.
	 * 
	 * @return Minimum rating for this query or -1.
	 */
	public double getMinRating()
	{
		return minRating;
	}

	/**
	 * Sets the minimum rating for this query.
	 * 
	 * @param minRating
	 */
	public void setMinRating(double minRating)
	{
		this.minRating = minRating;
	}

	/**
	 * Gets the maximum rating for this query. If no maximum rating was
	 * specified it will return -1.
	 * 
	 * @return Maximum rating for this query or -1.
	 */
	public double getMaxRating()
	{
		return maxRating;
	}

	/**
	 * Sets the maximum rating for this query.
	 * 
	 * @param maxRating
	 */
	public void setMaxRating(double maxRating)
	{
		this.maxRating = maxRating;
	}

	public double getMinPrice()
	{
		return minPrice;
	}

	public void setMinPrice(double minPrice)
	{
		this.minPrice = minPrice;
	}

	public double getMaxPrice()
	{
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice)
	{
		this.maxPrice = maxPrice;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public int getOffset()
	{
		return offset;
	}

	public void setOffset(int offset)
	{
		this.offset = offset;
	}
}
