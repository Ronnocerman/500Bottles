package com._500bottles.da.external.wine.otherParameters;

import com._500bottles.da.external.wine.exception.InvalidOtherParameters;

//for entering the offset 
public class Offset extends OtherParameter
{
	public Offset(int i) throws InvalidOtherParameters
	{
		if (i < 0)
			throw new InvalidOtherParameters("Invalid Offset");

		string = "offset=";
		string += Integer.toString(i);
	}
}
