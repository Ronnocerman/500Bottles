package com._500bottles.da.external.wine;

import java.util.HashMap;

import com._500bottles.da.external.wine.exception.InvalidCategory;

public class Publication extends Attribute
{
	private static String errormsg = "Invalid Publication";

	public Publication(String publication) throws InvalidCategory
	{
		super(errormsg);
		this.setCategory(publication);

	}

	@Override
	protected void initCategoryAttributeMap()
	{
		categoryAttributeMap = new HashMap<String, String>();
		categoryAttributeMap.put("Wine Spectator", "202");
		categoryAttributeMap.put("The Wine Advocate", "201");
		categoryAttributeMap.put("James Halliday", "207");
		categoryAttributeMap.put("Connoisseur' Guide", "205");
		categoryAttributeMap.put("International Wine Cellar", "203");
		categoryAttributeMap.put("The Wine News", "206");
		categoryAttributeMap.put("Wine & Spirits", "204");
		categoryAttributeMap.put("Burghound.com", "15425");
		categoryAttributeMap.put("Decanter", "219");
		categoryAttributeMap.put("James Suckling", "208");
		categoryAttributeMap.put("PinotReport", "15426");
		categoryAttributeMap.put("Tasting Panel", "218");
		categoryAttributeMap.put("Wine Enthusiast", "200");

	}
}
