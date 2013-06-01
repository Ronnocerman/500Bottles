package com._500bottles.da.external.wine;

import java.util.HashMap;

import com._500bottles.da.external.wine.exception.InvalidCategory;

public class VintageArray extends Attribute
{
	private static String errormsg = "Invalid Attribute";

	public VintageArray(String vintage) throws InvalidCategory
	{
		super(errormsg);
		this.setCategory(vintage);
	}

	// TODO
	@Override
	protected void initcategoryAttributeMap()
	{

		categoryAttributeMap = new HashMap<String, String>();

		categoryAttributeMap.put("Non-Vintage", "37");
		categoryAttributeMap.put("1875", "301");
		categoryAttributeMap.put("1910", "303");
		categoryAttributeMap.put("1912", "305");
		categoryAttributeMap.put("1921", "306");
		categoryAttributeMap.put("1922", "307");
		categoryAttributeMap.put("1933", "308");
		categoryAttributeMap.put("1934", "309");
		categoryAttributeMap.put("1937", "310");
		categoryAttributeMap.put("1940", "311");
		categoryAttributeMap.put("1941", "312");

		categoryAttributeMap.put("1944", "314");
		categoryAttributeMap.put("1945", "315");
		categoryAttributeMap.put("1948", "317");
		categoryAttributeMap.put("1950", "319");
		categoryAttributeMap.put("1953", "321");
		categoryAttributeMap.put("1954", "322");
		categoryAttributeMap.put("1955", "323");
		categoryAttributeMap.put("1958", "324");
		categoryAttributeMap.put("1959", "325");
		categoryAttributeMap.put("1961", "327");
		categoryAttributeMap.put("1962", "328");

		categoryAttributeMap.put("1963", "329");
		categoryAttributeMap.put("1964", "330");
		categoryAttributeMap.put("1966", "331");
		categoryAttributeMap.put("1967", "332");
		categoryAttributeMap.put("1968", "333");
		categoryAttributeMap.put("1970", "335");
		categoryAttributeMap.put("1971", "336");
		categoryAttributeMap.put("1973", "338");
		categoryAttributeMap.put("1974", "339");
		categoryAttributeMap.put("1975", "340");
		categoryAttributeMap.put("1976", "341");

		categoryAttributeMap.put("1977", "342");
		categoryAttributeMap.put("1978", "343");
		categoryAttributeMap.put("1979", "344");
		categoryAttributeMap.put("1980", "345");
		categoryAttributeMap.put("1981", "346");
		categoryAttributeMap.put("1982", "347");
		categoryAttributeMap.put("1983", "348");
		categoryAttributeMap.put("1984", "349");
		categoryAttributeMap.put("1985", "350");
		categoryAttributeMap.put("1986", "351");
		categoryAttributeMap.put("1987", "352");

		categoryAttributeMap.put("1988", "353");
		categoryAttributeMap.put("1989", "354");
		categoryAttributeMap.put("1990", "355");
		categoryAttributeMap.put("1991", "356");
		categoryAttributeMap.put("1992", "357");
		categoryAttributeMap.put("1993", "358");
		categoryAttributeMap.put("1994", "359");
		categoryAttributeMap.put("1995", "360");
		categoryAttributeMap.put("1996", "361");
		categoryAttributeMap.put("1997", "362");
		categoryAttributeMap.put("1998", "363");

		categoryAttributeMap.put("1999", "364");
		categoryAttributeMap.put("2000", "365");
		categoryAttributeMap.put("2001", "366");
		categoryAttributeMap.put("2002", "367");
		categoryAttributeMap.put("2003", "368");
		categoryAttributeMap.put("2004", "369");
		categoryAttributeMap.put("2005", "370");
		categoryAttributeMap.put("2006", "371");
		categoryAttributeMap.put("2007", "372");
		categoryAttributeMap.put("2008", "373");
		categoryAttributeMap.put("2009", "374");

		categoryAttributeMap.put("2010", "375");
		categoryAttributeMap.put("2011", "376");
		categoryAttributeMap.put("2012", "377");

	}
}
