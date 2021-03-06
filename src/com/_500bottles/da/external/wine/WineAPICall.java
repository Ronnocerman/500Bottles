package com._500bottles.da.external.wine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com._500bottles.object.wine.Appellation;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineType;

public class WineAPICall
{

	public WineAPICall()
	{
	}

	// this takes the url and gets a list of products from wine.com api, then
	// parses the json string that
	// we get back and package it into our wine object
	public Vector<Wine> getProducts(String url) throws IOException,
			ParseException
	{
		Vector<Wine> wineVector = new Vector<Wine>();
		URL apicall = new URL(url);
		URLConnection yc = apicall.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		String inputLine, jsonString = "";
		while ((inputLine = in.readLine()) != null)
		{
			jsonString += inputLine;
			System.out.println(inputLine);
		}
		in.close();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonString);
		JSONObject jsonObject = (JSONObject) obj;
		jsonObject = (JSONObject) jsonObject.get("Products");
		JSONArray jsonArray = (JSONArray) jsonObject.get("List");
		for (int i = 0; i < jsonArray.size(); i++)
		{
			Wine temp = new Wine();
			jsonObject = (JSONObject) jsonArray.get(i);
			String s = (String) jsonObject.get("Name");
			try
			{
				int num = Integer.parseInt(s.substring(s.length() - 4,
						s.length()));
				temp.setYear((long) num);
				temp.setName(s.substring(0, s.length() - 5));
			} catch (NumberFormatException e)
			{

				temp.setName(s);
			}

			temp.setWinecomId((long) jsonObject.get("Id"));

			temp.setPriceMin((double) jsonObject.get("PriceMin"));
			temp.setPriceMax((double) jsonObject.get("PriceMax"));

			JSONObject jsonObject2 = (JSONObject) jsonObject.get("Appellation");
			Appellation appellation = new Appellation(
					(String) jsonObject2.get("Name"));
			temp.setAppellation(appellation);

			JSONArray jsonArray2 = (JSONArray) jsonObject.get("Labels");

			jsonObject2 = (JSONObject) jsonArray2.get(0);
			temp.setImage((String) jsonObject2.get("Url"));

			jsonObject2 = (JSONObject) jsonObject.get("Varietal");
			Varietal varietal = new Varietal((String) jsonObject2.get("Name"));
			temp.setVarietal(varietal);
			JSONObject jsonObject3 = (JSONObject) jsonObject2.get("WineType");
			WineType winetype = new WineType((String) jsonObject3.get("Name"));
			temp.setType(winetype);

			jsonObject2 = (JSONObject) jsonObject.get("Vineyard");
			Vineyard vineyard = new Vineyard((String) jsonObject2.get("Name"));
			temp.setVineyard(vineyard);

			// Divide the wine.com rating by 20 to map it to a 0 to
			// 5 scale for internal use.
			jsonObject2 = (JSONObject) jsonObject.get("Ratings");
			temp.setRating(Long.valueOf(
					(long) jsonObject2.get("HighestScore") / 20).doubleValue());

			wineVector.add(temp);

		}
		return wineVector;
	}
}