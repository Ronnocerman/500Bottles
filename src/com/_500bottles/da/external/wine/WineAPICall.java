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

import com._500bottles.object.wine.Wine;

public class WineAPICall
{

	public WineAPICall()
	{
	}

	public Vector<Wine> getProducts(String url) throws IOException,
			ParseException
	{
		Vector<Wine> wineVector;
		URL apicall = new URL("");
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
			jsonObject = (JSONObject) jsonArray.get(i);

		}

		System.out.println(cat.toJSONString());
		return null;
	}
}