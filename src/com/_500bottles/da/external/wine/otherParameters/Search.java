package com._500bottles.da.external.wine.otherParameters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

//this is a very important class for entering a keyword search
public class Search extends OtherParameter
{
	public Search(String s)
	{
		string = "search=";
		try
		{
			s = URLEncoder.encode(s, "UTF-8");
			System.out.println(s);
		} catch (UnsupportedEncodingException e)
		{
			System.err.println("Encoding exception in Search.java");
		}
		string += s;
	}

	public void addSearch(String s)
	{
		string = string + "+" + s;
	}
}
