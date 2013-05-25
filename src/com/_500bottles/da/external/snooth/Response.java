package com._500bottles.da.external.snooth;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/23/13
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Response
{
	private long results;
	private long returned;
	private long status;
	private String error_message;

	private static JSONParser parser = new JSONParser();

	protected JSONObject message;

	public Response(String response)
	{
		try {
			message = (JSONObject) parser.parse(response);
			JSONObject meta = (JSONObject) message.get("meta");

			results = (Long) meta.get("results");
			returned = (Long) meta.get("returned");
			status = (Long) meta.get("results");
			error_message = (String) meta.get("errmsg");

		} catch (ParseException pe) {
			// TODO: Handle parse exception by returning error.
		}
	}

	public long getNumberOfResults()
	{
		return results;
	}

	public long getNumberOfReturned()
	{
		return returned;
	}

	public long getStatus()
	{
		return status;
	}

	public String getErrorMessage()
	{
		return error_message;
	}

}
