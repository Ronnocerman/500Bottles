package com._500bottles.da.external.wine.otherParameters;

import com._500bottles.da.external.wine.exception.InvalidOtherParameters;

//for checking the instock option
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
