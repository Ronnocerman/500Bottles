package com._500bottles.da.external.wine.otherParameters;

import com._500bottles.da.external.wine.exception.InvalidOtherParameters;

public class Size extends OtherParameter
{
	public Size(int i) throws InvalidOtherParameters
	{
		if (i < 0)
			throw new InvalidOtherParameters("Invalid Size");

		string = "size=";
		string += Integer.toString(i);
	}

}
