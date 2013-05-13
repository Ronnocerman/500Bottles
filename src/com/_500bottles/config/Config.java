package com._500bottles.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com._500bottles.util.json.JSONException;
import com._500bottles.util.json.JSONObject;
import com._500bottles.util.json.JSONTokener;

public class Config
{
	private static final String internalConfigFileLocation = "./config/config.json";
	private static final String externalConfigFileLocation = "webapps/config.json";
	private static JSONObject json;
	static
	{
		String jsonConfigLocation;
		if (new File(externalConfigFileLocation).exists())
			jsonConfigLocation = externalConfigFileLocation;
		else
			jsonConfigLocation = internalConfigFileLocation;
		try
		{
			JSONTokener tokener = new JSONTokener(new FileInputStream(new File(
					jsonConfigLocation)));
			json = new JSONObject(tokener);
		} catch (JSONException e)
		{
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			System.err.println("CONFIG FILE NOT FOUND: "
					+ internalConfigFileLocation);
		}
	}

	public static String getProperty(String property)
	{
		return json.getString(property);
	}
}
