package com._500bottles.da.external.snooth;

import com._500bottles.config.Config;
import com._500bottles.da.external.snooth.exception.InvalidWineSearch;
import com._500bottles.da.external.snooth.sort.Sort;
import com._500bottles.object.wine.WineQuery;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/22/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class WineSearch
{
	private final static String BASE_API_URL =
		"http://api.snooth.com/wines/?";

	private final static String API_KEY = Config.getProperty("snoothAPIKey");

	private final static String CHARSET = "UTF-8";

	private final static int DEFAULT_FIRST_RESULT = 1;

	private final static int DEFAULT_NUM_RESULTS = 10;

	private final static boolean DEFAULT_AVAIL = false;

	private final static ProductType DEFAULT_PRODUCT_TYPE = null;

	private final static Color DEFAULT_COLOR = null;

	private final static int DEFAULT_STORE_ID = 0;

	private final static Country DEFAULT_COUNTRY = null;

	private final static int DEFAULT_ZIPCODE = 0;

	private final static double DEFAULT_LATITUDE = 0;

	private final static double DEFAULT_LONGITUDE = 0;

	private final static Sort DEFAULT_SORT = null;

	private final static double DEFAULT_MIN_PRICE = 0;

	private final static double DEFAULT_MAX_PRICE = 0;

	private final static double DEFAULT_MIN_RATING = 0;

	private final static double DEFAULT_MAX_RATING = 0;

	private final static Language DEFAULT_LANGUAGE = null;

	/* Search query. */
	private String q;

	/* First result to return. Used for paging. */
	private int f;

	/* Number of results to return, must be 1 - 100 */
	private int n;

	/* Availability -- set to 0 for all wines, and 1 for only wines
	 * in-stock.
	 */
	private int a;

	/* Product types to return. */
	private ProductType t;

	/* Product colors. */
	private Color color;

	/* Store ID. Limits results to products from available stores. */
	private int m;

	/* Country -- limits results to wines available in this country. */
	private Country c;

	/* Zipcode -- when combined with certain countries, it limits the
	 * results to local results.
	 */
	private int z;

	/* Latitude. When combined with a longitude value, will return local
	 * results. If a Country parameter is included, this parameter will
	 * be ignored.
	 */
	private double lat;

	/* Longitude (see Latitude). */
	private double lng;

	/* Sort for search. */
	private Sort sort;

	/* Minimum product price. */
	private double mp;

	/* Maximum product price. */
	private double xp;

	/* Minimum SnoothRank. Possible values are any rational number
	 * in the range [0 - 5]
	 */
	private double mr;

	/* Maximum SnoothRank (see Minimum SnoothRank). */
	private double xr;

	/* Language. Only return wines with content in the specified language.
	 * Possible values: en, el, fr, de, it, pt, es. (Beta)
	 */
	private Language lang;

	public WineSearch(String query) throws InvalidWineSearch
	{
		if (!(query.length() > 0))
			throw new InvalidWineSearch();

		this.setQuery(query);
		this.setFirstResult(DEFAULT_FIRST_RESULT);
		this.setNumberOfResults(DEFAULT_NUM_RESULTS);
		this.setAvailable(DEFAULT_AVAIL);
		this.setProductType(DEFAULT_PRODUCT_TYPE);
		this.setColor(DEFAULT_COLOR);
		this.setStoreId(DEFAULT_STORE_ID);
		this.setCountry(DEFAULT_COUNTRY);
		this.setZipCode(DEFAULT_ZIPCODE);
		this.setLatitude(DEFAULT_LATITUDE);
		this.setLongitude(DEFAULT_LONGITUDE);
		this.setSort(DEFAULT_SORT);
		this.setMinPrice(DEFAULT_MIN_PRICE);
		this.setMaxPrice(DEFAULT_MAX_PRICE);
		this.setMinRating(DEFAULT_MIN_RATING);
		this.setMaxRating(DEFAULT_MAX_RATING);
		this.setLanguage(DEFAULT_LANGUAGE);
	}

	public WineSearch(WineQuery query) throws InvalidWineSearch
	{
		this.setQuery(query.getTextQuery());
		// TODO: Match the rest of the queries.
	}

	public String toString()
	{
		String url = BASE_API_URL;

		url += "akey=" + API_KEY;

		try {
			url += "&q=" + this.getEncodedQuery();
		} catch (UnsupportedEncodingException e) {
			// TODO: do something here to catch encoding exception.
		}

		if (this.getFirstResult() != DEFAULT_FIRST_RESULT)
			url += "&f=" + this.getFirstResult();

		if (this.getNumberOfResults() != DEFAULT_NUM_RESULTS)
			url += "&n=" + this.getNumberOfResults();

		if (this.getAvailable() != DEFAULT_AVAIL) {
			if (this.getAvailable())
				url += "&a=1";
		}


		if (this.getProductType() != DEFAULT_PRODUCT_TYPE)
			url += "&t=" + this.getProductType().toString();

		if (this.getColor() != DEFAULT_COLOR)
			url += "&color=" + this.getColor().toString();

		if (this.getStoreId() != DEFAULT_STORE_ID)
			url += "&m=" + this.getStoreId();

		if (this.getCountry() != DEFAULT_COUNTRY)
			url += "&c=" + this.getCountry().toString();

		if (this.getZipCode() != DEFAULT_ZIPCODE)
			url += "&z=" + this.getZipCode();

		if (this.getLatitude() != DEFAULT_LATITUDE)
			url += "&lat=" + this.getLatitude();

		if (this.getLongitude() != DEFAULT_LONGITUDE)
			url += "&lng=" + this.getLongitude();

		if (this.getSort() != DEFAULT_SORT)
			url += "&s=" + this.getSort().toString();

		if (this.getMinPrice() != DEFAULT_MIN_PRICE)
			url += "&mp=" + this.getMinPrice();

		if (this.getMaxPrice() != DEFAULT_MAX_PRICE)
			url += "&xp=" + this.getMaxPrice();

		if (this.getMinRating() != DEFAULT_MIN_RATING)
			url += "&mr=" + this.getMinRating();

		if (this.getMaxRating() != DEFAULT_MAX_RATING)
			url += "&xr=" + this.getMaxRating();

		if (this.getLanguage() != DEFAULT_LANGUAGE)
			url += "&lang=" + this.getLanguage().toString();

		return url;
	}

	public Language getLanguage()
	{
		return lang;
	}

	public void setLanguage(Language lang)
	{
		this.lang = lang;
	}

	public String getQuery()
	{
		return q;
	}

	public String getEncodedQuery() throws UnsupportedEncodingException
	{
		return URLEncoder.encode(q, CHARSET);
	}

	public void setQuery(String q)
	{
		this.q = q;
	}

	public int getFirstResult()
	{
		return f;
	}

	public void setFirstResult(int f)
	{
		this.f = f;
	}

	public int getNumberOfResults()
	{
		return n;
	}

	public void setNumberOfResults(int n)
	{
		this.n = n;
	}

	public boolean getAvailable()
	{
		if (a > 0)
			return true;
		return false;
	}

	public void setAvailable(boolean a)
	{
		if (a)
			this.a = 1;
		else
			this.a = 0;
	}

	public ProductType getProductType()
	{
		return t;
	}

	public void setProductType(ProductType t)
	{
		this.t = t;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public int getStoreId()
	{
		return m;
	}

	public void setStoreId(int m)
	{
		this.m = m;
	}

	public Country getCountry()
	{
		return c;
	}

	public void setCountry(Country c)
	{
		this.c = c;
	}

	public int getZipCode()
	{
		return z;
	}

	public void setZipCode(int z)
	{
		this.z = z;
	}

	public double getLatitude()
	{
		return lat;
	}

	public void setLatitude(double lat)
	{
		this.lat = lat;
	}

	public double getLongitude()
	{
		return lng;
	}

	public void setLongitude(double lng)
	{
		this.lng = lng;
	}

	public Sort getSort()
	{
		return sort;
	}

	public void setSort(Sort sort)
	{
		this.sort = sort;
	}

	public double getMinPrice()
	{
		return mp;
	}

	public void setMinPrice(double mp)
	{
		this.mp = mp;
	}

	public double getMaxPrice()
	{
		return xp;
	}

	public void setMaxPrice(double xp)
	{
		this.xp = xp;
	}

	public double getMinRating()
	{
		return mr;
	}

	public void setMinRating(double mr)
	{
		this.mr = mr;
	}

	public double getMaxRating()
	{
		return xr;
	}

	public void setMaxRating(double xr)
	{
		this.xr = xr;
	}
}
