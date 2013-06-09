package com._500bottles.da.external.wine;

import java.util.HashMap;

import com._500bottles.da.external.wine.exception.InvalidCategory;

//check if specified region is a valid region accepted by wine.com API
public class Region extends Attribute
{
	private static String errormsg = "Invalid Region";

	public Region(String region) throws InvalidCategory
	{
		super(errormsg);
		this.setCategory(region);
	}

	@Override
	protected void initCategoryAttributeMap()
	{
		categoryAttributeMap = new HashMap<String, String>();
		categoryAttributeMap.put("California", "101");
		categoryAttributeMap.put("Washington", "104");
		categoryAttributeMap.put("Oregon", "103");
		categoryAttributeMap.put("Other US", "111");
		categoryAttributeMap.put("Australia", "108");
		categoryAttributeMap.put("New Zealand", "114");
		categoryAttributeMap.put("France - Bordeaux", "10038");
		categoryAttributeMap.put("France - Rhone", "10039");
		categoryAttributeMap.put("France - Other categoryAttributeMap", "102");
		categoryAttributeMap.put("Italy", "105");
		categoryAttributeMap.put("Spain", "109");
		categoryAttributeMap.put("Portugal", "107");
		categoryAttributeMap.put("Israel", "119");
		categoryAttributeMap.put("Germany", "106");
		categoryAttributeMap.put("Greece", "115");
		categoryAttributeMap.put("Other European", "118");
		categoryAttributeMap.put("South America", "112");
		categoryAttributeMap.put("South Africa", "113");
		categoryAttributeMap.put("Japan", "122");
		categoryAttributeMap.put("Canada", "121");

	}
}
