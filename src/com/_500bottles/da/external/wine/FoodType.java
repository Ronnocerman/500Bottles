package com._500bottles.da.external.wine;

import java.util.HashMap;

import com._500bottles.da.external.wine.exception.InvalidCategory;

public class FoodType extends Attribute
{
	private static String errormsg = "Invalid Food Type";

	public FoodType(String foodType) throws InvalidCategory
	{
		super(errormsg);
		this.setCategory(foodType);
	}

	@Override
	protected void initcategoryAttributeMap()
	{
		categoryAttributeMap = new HashMap<String, String>();
		categoryAttributeMap.put("Meat", "3008");
		categoryAttributeMap.put("Cheese", "3009");
		categoryAttributeMap.put("Desert", "3010");
		categoryAttributeMap.put("Pasta & Grains", "3012");
		categoryAttributeMap.put("Poultry", "3013");
		categoryAttributeMap.put("Seafood", "3014");

	}
}
