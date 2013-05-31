package com._500bottles.da.external.wine;

import java.util.HashMap;

import com._500bottles.da.external.wine.exception.InvalidCategory;

public class Appellation extends Attribute
{
	private static String errormsg = "Invalid Appellation";

	public Appellation(String appellation) throws InvalidCategory
	{
		super(errormsg);
		this.setCategory(appellation);
	}

	@Override
	protected void initCategoryAttributeMap()
	{
		categoryAttributeMap = new HashMap<String, String>();

		categoryAttributeMap.put("Champagne", "2331");
		categoryAttributeMap.put("Chile", "2428");
		categoryAttributeMap.put("Coonawarra", "9060");
		categoryAttributeMap.put("Austria", "607");
		categoryAttributeMap.put("Barossa", "9058");
		categoryAttributeMap.put("Burgundy", "2457");
		categoryAttributeMap.put("Napa Valley", "2398");
		categoryAttributeMap.put("Canada", "1781");
		categoryAttributeMap.put("Beujolais", "2397");
		categoryAttributeMap.put("Carneros", "2454");
		categoryAttributeMap.put("Russian River", "1863");

		categoryAttributeMap.put("Alsace", "2148");
		categoryAttributeMap.put("Argentina", "2089");
		categoryAttributeMap.put("Clare Valley", "9059");
		categoryAttributeMap.put("Columbia Valley", "2414");
		categoryAttributeMap.put("McLaren Vale", "9063");
		categoryAttributeMap.put("Tuscany", "2452");
		categoryAttributeMap.put("Yarra Valley", "9064");
		categoryAttributeMap.put("Greece", "1455");
		categoryAttributeMap.put("Chablis", "10063");
		categoryAttributeMap.put("Hungary", "1726");
		categoryAttributeMap.put("Slovenia", "1727");

		categoryAttributeMap.put("Victoria", "10077");
		categoryAttributeMap.put("Margaret River", "9062");
		categoryAttributeMap.put("Israel", "2097");
		categoryAttributeMap.put("Rheingau", "1837");
		categoryAttributeMap.put("Hokkaido", "2475");
		categoryAttributeMap.put("Sonoma County", "2371");
		categoryAttributeMap.put("Gisborne", "10074");
		categoryAttributeMap.put("Provence", "10064");
		categoryAttributeMap.put("Languedoc-Roussillon", "2374");
		categoryAttributeMap.put("Santa Maria Valley", "2124");
		categoryAttributeMap.put("Loire", "2333");

		categoryAttributeMap.put("Mosel-Saar-Ruwer", "2268");
		categoryAttributeMap.put("New York", "1867");
		categoryAttributeMap.put("Piedmont", "2431");
		categoryAttributeMap.put("Rioja", "2370");
		categoryAttributeMap.put("None", "2346");
		categoryAttributeMap.put("Yakima Valley", "2052");
		categoryAttributeMap.put("Trentino-Alto Adige", "2279");
		categoryAttributeMap.put("Ribera del Duero", "9067");
		categoryAttributeMap.put("Other German", "1650");
		categoryAttributeMap.put("Cote Rotie", "10053");
		categoryAttributeMap.put("Priorat", "9066");

		categoryAttributeMap.put("Navarra", "10075");
		categoryAttributeMap.put("Santa Cruz Mountains", "965");
		categoryAttributeMap.put("Central Coast", "2462");
		categoryAttributeMap.put("North Coast", "2416");
		categoryAttributeMap.put("Sierra Foothills", "2288");
		categoryAttributeMap.put("Other California", "2388");
		categoryAttributeMap.put("Portugal", "2377");

		categoryAttributeMap.put("Friuli-Venezia Giulia", "2310");
		categoryAttributeMap.put("Rias Baixas", "10065");
		categoryAttributeMap.put("Veneto", "2352");
		categoryAttributeMap.put("Southern Italy", "2143");

		categoryAttributeMap.put("Sardinia", "10068");
		categoryAttributeMap.put("Walla Walla Valley", "1956");
		categoryAttributeMap.put("South Australia", "10078");
		categoryAttributeMap.put("South Africa", "2283");
		categoryAttributeMap.put("Switzerland", "943");
		categoryAttributeMap.put("Willamette Valley", "2474");
		categoryAttributeMap.put("Umpqua Valley", "2195");
		categoryAttributeMap.put("Rogue River Valley", "2157");
		categoryAttributeMap.put("Other French", "2312");
		categoryAttributeMap.put("Sicily", "10066");
		categoryAttributeMap.put("Other Italian", "2309");

		categoryAttributeMap.put("Condrieu", "10054");
		categoryAttributeMap.put("Hermitage", "10055");
		categoryAttributeMap.put("Cornas", "10056");
		categoryAttributeMap.put("Chateauneuf-du-Pape", "10057");
		categoryAttributeMap.put("Tavel", "10058");
		categoryAttributeMap.put("Gigondas", "10059");
		categoryAttributeMap.put("Vacqueyras", "10062");
		categoryAttributeMap.put("Cotes du Rhone", "10061");
		categoryAttributeMap.put("Rasteau", "10060");
		categoryAttributeMap.put("Oher Rhone", "2375");
		categoryAttributeMap.put("Hunter Valley", "9061");

		categoryAttributeMap.put("Other Australia", "2405");
		categoryAttributeMap.put("Hawkes Bay", "10069");
		categoryAttributeMap.put("Martinborough", "10070");
		categoryAttributeMap.put("Malborough", "10071");
		categoryAttributeMap.put("Waipara Valley", "10072");
		categoryAttributeMap.put("Central Otago", "10073");
		categoryAttributeMap.put("Other New Zealand", "2273");
		categoryAttributeMap.put("Akita", "2476");
		categoryAttributeMap.put("Tochigi", "2490");
		categoryAttributeMap.put("Niigata", "2492");
		categoryAttributeMap.put("Hiroshima", "2493");

		categoryAttributeMap.put("Tottori", "2495");
		categoryAttributeMap.put("Iwate", "2491");
		categoryAttributeMap.put("Osaka", "2498");
		categoryAttributeMap.put("Shizuoka", "2497");
		categoryAttributeMap.put("Shimane", "2494");
		categoryAttributeMap.put("St. Estephe", "10044");
		categoryAttributeMap.put("Margaux", "10040");
		categoryAttributeMap.put("Pauillac", "10049");
		categoryAttributeMap.put("Medoc", "10041");
		categoryAttributeMap.put("Pessac-Leognan", "10051");
		categoryAttributeMap.put("Sauternes and Barsac", "10046");

		categoryAttributeMap.put("Graves", "10045");
		categoryAttributeMap.put("St-Emilion", "10042");
		categoryAttributeMap.put("Pomerol", "10043");
		categoryAttributeMap.put("Fronsac", "10047");
		categoryAttributeMap.put("Cotes de Castillon", "10048");
		categoryAttributeMap.put("Other Bordeaux", "10052");
		categoryAttributeMap.put("Rueda", "10076");
		categoryAttributeMap.put("Jumilla", "10067");
		categoryAttributeMap.put("Other Spain", "9065");
		categoryAttributeMap.put("Sardinia", "2315");

	}
}
