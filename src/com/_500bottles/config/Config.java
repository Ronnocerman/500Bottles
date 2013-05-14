package com._500bottles.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
			json = (JSONObject) JSONValue.parse(new InputStreamReader(
					new FileInputStream(new File(jsonConfigLocation))));
		} catch (FileNotFoundException e)
		{
			System.err.println("CONFIG FILE NOT FOUND: "
					+ internalConfigFileLocation);
		}
	}

	public static String getProperty(String property)
	{
		return json.get(property).toString();
	}
}
