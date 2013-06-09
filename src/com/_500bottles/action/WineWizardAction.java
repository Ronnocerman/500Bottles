package com._500bottles.action;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com._500bottles.da.external.snooth.exception.InvalidSort;
import com._500bottles.da.external.wine.exception.InvalidCategory;
import com._500bottles.da.external.wine.exception.InvalidOtherParameters;
import com._500bottles.exception.da.DAException;
import com._500bottles.manager.WineWizardManager;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;

public class WineWizardAction
{
	public static WineQueryResult getSuggestion(WineQuery wine)
			throws DAException, InvalidCategory, InvalidSort,
			InvalidOtherParameters, IOException, ParseException
	{
		if (wine.getType() == null)
		{

		}
		return WineWizardManager.selectWine(wine);
	}
}