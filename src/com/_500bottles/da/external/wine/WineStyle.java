package com._500bottles.da.external.wine;

import java.util.HashMap;

import com._500bottles.da.external.wine.exception.InvalidCategory;

public class WineStyle extends Attribute
{
	private static String errormsg = "Invalid Wine Style";

	public WineStyle(String winestyle) throws InvalidCategory
	{
		super(errormsg);
		this.setCategory(winestyle);
	}

	@Override
	protected void initcategoryAttributeMap()
	{
		categoryAttributeMap = new HashMap<String, String>();
		categoryAttributeMap.put("610", "Red - Light & Fruity");
		categoryAttributeMap.put("611", "Red - Smooth & Supple");
		categoryAttributeMap.put("612", "Red - Earthy & Spicy");
		categoryAttributeMap.put("613", "Red - Big & Bold");
		categoryAttributeMap.put("614", "White - Light & Crisp");
		categoryAttributeMap.put("615", "White - Fruity & Smooth");
		categoryAttributeMap.put("616", "White - Rich & Creamy");

	}
}
