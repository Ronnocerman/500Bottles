package com._500bottles.da.external.wine;

import java.util.HashMap;

import com._500bottles.da.external.wine.exception.InvalidCategory;

public class ProductAttribute extends Attribute
{
	private static String errormsg = "Invalid Product Attribute";

	public ProductAttribute(String productAttribute) throws InvalidCategory
	{
		super(errormsg);
		this.setCategory(productAttribute);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initcategoryAttributeMap()
	{
		categoryAttributeMap = new HashMap<String, String>();
		categoryAttributeMap.put("Half Bottle Wines", "42");
		categoryAttributeMap.put("Great Bottles to Give", "15419");
		categoryAttributeMap.put("Green Wines", "38");
		categoryAttributeMap.put("Screw Cap Wines", "44");
		categoryAttributeMap.put("Kosher Wines", "43");
		categoryAttributeMap.put("Collectible Wines", "36");
		categoryAttributeMap.put("Boutique Wines", "506");
		categoryAttributeMap.put("Watch the Video", "15424");
		categoryAttributeMap.put("300ml", "25");
		categoryAttributeMap.put("375ml", "26");
		categoryAttributeMap.put("500ml", "27");
		categoryAttributeMap.put("720ml", "28");
		categoryAttributeMap.put("750ml", "29");
		categoryAttributeMap.put("1.5L", "30");
		categoryAttributeMap.put("3L", "33");
		categoryAttributeMap.put("6L", "34");
	}

}
