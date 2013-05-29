package com._500bottles.da.external.wine.otherParameters;

public class Search extends OtherParameter
{
	public Search(String s)
	{
		string = "search=";
		string += s;
	}

	public void addSearch(String s)
	{
		string = string + "+" + s;
	}
}
