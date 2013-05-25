package com._500bottles.da.external.snooth;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/23/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class WineDetailsResponse extends Response
{
	private Vector<SnoothWine> wines;

	public WineDetailsResponse(String response)
	{
		super(response);

		wines = new Vector<SnoothWine>();

		JSONArray wines_json;

		if (getNumberOfReturned() <= 0)
			return;

		wines_json = (JSONArray) message.get("wines");
		parse_wines_json(wines_json);
	}

	public Vector<SnoothWine> getWines()
	{
		return wines;
	}

	public Iterator<SnoothWine> getWinesIterator()
	{
		return wines.iterator();
	}

	private void parse_wines_json(JSONArray wines_json)
	{
		Iterator<JSONObject> it = wines_json.iterator();

		while (it.hasNext()) {
			JSONObject item = it.next();
			SnoothWine wine = new SnoothWine();

			wine.setName((String) item.get("name"));
			wine.setCode((String) item.get("code"));
			wine.setRegion((String) item.get("region"));
			wine.setWinery((String) item.get("winery"));
			wine.setWinery_id((String) item.get("winery_id"));
			wine.setVarietal((String) item.get("varietal"));
			wine.setPrice((String) item.get("price"));
			wine.setVintage((String) item.get("vintage"));
			wine.setType((String) item.get("type"));
			wine.setLink((String) item.get("link"));
			wine.setColor((String) item.get("color"));
			wine.setImage((String) item.get("image"));
			wine.setWineMakerNotes((String) item.get("wm_notes"));
			wine.setWineryTastingNotes((String) item.get("winery_tasting_notes"));
			wine.setSugar((String) item.get("sugar"));
			wine.setAlcohol((String) item.get("alcohol"));
			wine.setPh((String) item.get("ph"));
			wine.setAcidity((String) item.get("acidity"));
			wine.setSnoothrank(item.get("snoothrank"));
			wine.setAvailable((Long) item.get("available"));
			wine.setNum_merchants((Long) item.get("num_merchants"));
			wine.setNum_reviews((Long) item.get("num_reviews"));

			wines.add(wine);
		}
	}
}
