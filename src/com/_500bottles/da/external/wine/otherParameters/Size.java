package com._500bottles.da.external.wine.otherParameters;

import com._500bottles.da.external.wine.exception.InvalidOtherParameters;

//for entering the size of the amount of products you want back from wine.com
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
