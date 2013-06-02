package com._500bottles.da.external.wine;

import java.util.HashMap;

import com._500bottles.da.external.wine.exception.InvalidCategory;

public class VarietalArray extends Attribute
{
	private static String errormsg = "Invalid Varietal";

	public VarietalArray(String varietal) throws InvalidCategory
	{
		super(errormsg);
		this.setCategory(varietal);
	}

	protected void initCategoryAttributeMap()
	{
		categoryAttributeMap = new HashMap<String, String>();
		categoryAttributeMap.put("Cabernet Sauvignon", "139");
		categoryAttributeMap.put("Merlot", "138");
		categoryAttributeMap.put("Cabernet Franc", "197");
		categoryAttributeMap.put("Malbec", "10079");
		categoryAttributeMap.put("Bordeaux Blends", "144");
		categoryAttributeMap.put("Syrah/Shiraz", "146");
		categoryAttributeMap.put("Grenache", "10080");
		categoryAttributeMap.put("Mourvedre", "10083");
		categoryAttributeMap.put("Rhone Blends", "10082");
		categoryAttributeMap.put("Pinot Noir", "143");
		categoryAttributeMap.put("Gamay", "150");
		categoryAttributeMap.put("Zinfandel", "141");
		categoryAttributeMap.put("Petite Sirah", "176");
		categoryAttributeMap.put("Tempranillo", "169");
		categoryAttributeMap.put("Chianti", "152");
		categoryAttributeMap.put("Nebbiolo", "170");
		categoryAttributeMap.put("Dolcetto", "183");
		categoryAttributeMap.put("Nero d'Avola", "10086");
		categoryAttributeMap.put("Primitivo", "10084");
		categoryAttributeMap.put("Barbera", "172");

		categoryAttributeMap.put("Sangiovese", "163");
		categoryAttributeMap.put("Carmenere", "10081");
		categoryAttributeMap.put("Pinotage", "10085");
		categoryAttributeMap.put("Other Red Wine", "145");
		categoryAttributeMap.put("Vintage", "181");
		categoryAttributeMap.put("Non-Vintage", "182");
		categoryAttributeMap.put("Rose", "147");
		categoryAttributeMap.put("Port", "155");
		categoryAttributeMap.put("Madeira", "154");
		categoryAttributeMap.put("Sherry", "157");
		categoryAttributeMap.put("Vermouth", "156");
		categoryAttributeMap.put("Other Dessert", "160");
		categoryAttributeMap.put("Chardonnay", "140");
		categoryAttributeMap.put("Pinot Gris/Grigio", "194");
		categoryAttributeMap.put("Sauvignon Blanc", "151");
		categoryAttributeMap.put("Semillon", "177");
		categoryAttributeMap.put("Pinot Blanc", "168");
		categoryAttributeMap.put("Viognier", "162");
		categoryAttributeMap.put("Riesliing", "153");
		categoryAttributeMap.put("Gewurztraminer", "166");

		categoryAttributeMap.put("Gruner Veltliner", "10087");
		categoryAttributeMap.put("Chenin Blanc", "165");
		categoryAttributeMap.put("Muscat", "173");
		categoryAttributeMap.put("Other White Wine", "148");
		categoryAttributeMap.put("Junmai", "198");
		categoryAttributeMap.put("Junmai-Ginjo", "199");
		categoryAttributeMap.put("Junmai-Daiginjo", "127");
		categoryAttributeMap.put("Albarino", "136");
		categoryAttributeMap.put("Torrontes", "209");

	}
}
