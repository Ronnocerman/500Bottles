package com._500bottles.da.external.wine.otherParameters;

import com._500bottles.da.external.wine.exception.InvalidOtherParameters;

public class InStock extends OtherParameter
{
	public InStock(boolean b) throws InvalidOtherParameters
	{
		string = "instock=";

		if (b)
			string += "true";
		else
			string += "false";
	}

}
