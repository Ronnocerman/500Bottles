package com._500bottles.da.external.wine;

import java.util.HashMap;

import com._500bottles.da.external.wine.exception.InvalidCategory;

public class WineType extends Attribute
{
	private static String errormsg = "Invalid Wine Type";

	public WineType(String winetype) throws InvalidCategory
	{
		super(errormsg);
		this.setCategory(winetype);
	}

	@Override
	protected void initcategoryAttributeMap()
	{
		categoryAttributeMap = new HashMap<String, String>();
		categoryAttributeMap.put("Red Wine", "124");
		categoryAttributeMap.put("White Wine", "125");
		categoryAttributeMap.put("Rose Wine", "126");
		categoryAttributeMap.put("Champagne & Sparkling", "123");
		categoryAttributeMap.put("Sake", "134");
		categoryAttributeMap.put("Dessert, Sherry & Port", "128");

	}
}
