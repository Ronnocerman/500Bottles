package com._500bottles.action;

import java.util.Vector;

import com._500bottles.manager.WineWizardManager;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;

public class WineWizardAction
{
	public Vector<Wine> getSuggestion(WineQuery wine)
	{
		return WineWizardManager.selectWine(wine);
	}
}