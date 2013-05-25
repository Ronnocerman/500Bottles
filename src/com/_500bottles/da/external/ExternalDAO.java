package com._500bottles.da.external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/22/13
 * Time: 5:07 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ExternalDAO {

	protected static String doGetRequest(String url)
	{
		BufferedReader reader = null;
		String result = "", line = "";
		URLConnection connection;

		try {
			connection = new URL(url).openConnection();

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((line = reader.readLine()) != null)
				result += line;

		} catch (IOException ioException) {

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioException) {

				}
			}
		}

		return result;
	}

	protected static String doPostRequest(String url)
	{
		// TODO: Implement doPostRequest() method.
		return "";
	}

}
