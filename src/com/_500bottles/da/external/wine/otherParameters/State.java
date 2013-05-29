package com._500bottles.da.external.wine.otherParameters;

import com._500bottles.da.external.wine.exception.InvalidOtherParameters;

public class State extends OtherParameter
{
	String[] stateArray = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE",
			"FL",

			"GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
			"MA", "MI", "MN",

			"MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND",
			"OH", "OK",

			"OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA",
			"WV", "WI",

			"WY" };

	public State(String s) throws InvalidOtherParameters
	{
		boolean found = false;
		for (int i = 0; i < 50; i++)
			if (s == stateArray[i])
			{
				found = true;
				return;
			}
		if (found == false)
			throw new InvalidOtherParameters("Invalid State");
		else
		{
			string = "state=";
			string += s;
		}

	}
}
