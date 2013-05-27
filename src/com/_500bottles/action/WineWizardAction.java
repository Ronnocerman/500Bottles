package com._500bottles.action;

import java.util.ArrayList;

import com._500bottles.manager.WineWizardManager;
import com._500bottles.object.wine.Wine;

public class WineWizardAction
{
	private ArrayList<String> wineType = new ArrayList<String>();// set for
	// advance
	// search
	// level one
	private boolean fromCellar;// a flag to see if user wants wine from their
								// cellar
	private boolean fromOnline;// a flag to see if user wants wine from online
	private String wineName;// a string of the wine name the user wants
	private String appellation;// set for advanced search level five
	private String year;// set for advanced search level three
	private String varietal;// set for advanced search level two
	private String vineyard;// set for advanced level four
	private double sensitiveType;// the weight of the type
	private double sensitiveVarietal;// the weight of the varietal
	private double sensitiveYear;// the weight of the year
	private double sensitiveVineyard;// the weight of the vineyard
	private double sensitiveAppellation;// the weight of the appellation
	private int wineAmount;// the amount that the user wants
	private int counter;// counter used to keep track of the current wine to
						// display
	private ArrayList<Wine> returnWine = new ArrayList<Wine>();// the list of
																// wines to
																// return

	// returns wines by parsing a list
	public Wine getWines()
	{
		if (counter < returnWine.size())
		{
			counter++;
			return returnWine.get(counter - 1);
		}
		return null;
	}

	public WineWizardAction()
	{
		sensitiveType = -1;// the weight of the type
		sensitiveVarietal = -1;
		sensitiveYear = -1;
		sensitiveVineyard = -1;
		sensitiveAppellation = -1;
	}

	// gets the wines suggestion based on if user traits have been set
	public void getSuggestion()
	{
		// sets counter to zero
		counter = 0;
		// creates new WineWizardManager
		WineWizardManager wizardManager = new WineWizardManager(wineAmount);
		if (fromCellar)
		{
			wizardManager.setFromCellar(true);
		}
		if (fromOnline)
		{
			wizardManager.setFromOnline(true);
		}
		if (sensitiveAppellation != -1)
		{
			wizardManager.setSensitiveAppellation(sensitiveAppellation);
		}
		if (sensitiveYear != -1)
		{
			wizardManager.setSensitiveYear(sensitiveYear);
		}
		if (sensitiveVineyard != -1)
		{
			wizardManager.setSensitiveVineyard(sensitiveVineyard);
		}
		if (sensitiveVarietal != -1)
		{
			wizardManager.setSensitiveVarietal(sensitiveVarietal);
		}
		if (sensitiveType != -1)
		{
			wizardManager.setSensitiveType(sensitiveType);
		}
		if (wineType != null)
		{
			wizardManager.setWineType(wineType);
		}
		if (appellation != null)
		{
			wizardManager.setAppellation(appellation);
		}
		if (year != null)
		{
			wizardManager.setYear(year);
		}
		if (varietal != null)
		{
			wizardManager.setVarietal(varietal);
		}
		if (vineyard != null)
		{
			wizardManager.setAppellation(vineyard);
		}
		if (wineName != null)
		{
			returnWine = wizardManager.findWine();
		} else
		{
			returnWine = wizardManager.selectWine();
		}

	}

	public ArrayList<String> getWineType()
	{
		return wineType;
	}

	public void setWineType(String[] wineType)
	{
		for (int i = 0; i < wineType.length; i++)
		{
			this.wineType.add(wineType[i]);
		}

	}

	public String getAppellation()
	{
		return appellation;
	}

	public void setAppellation(String appellation)
	{
		this.appellation = appellation;
	}

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getVarietal()
	{
		return varietal;
	}

	public void setVarietal(String varietal)
	{
		this.varietal = varietal;
	}

	public String getVineyard()
	{
		return vineyard;
	}

	public void setVineyard(String vineyard)
	{
		this.vineyard = vineyard;
	}

	public double getSensitiveType()
	{
		return sensitiveType;
	}

	public void setSensitiveType(double sensitiveType)
	{
		this.sensitiveType = sensitiveType;
	}

	public double getSensitiveVarietal()
	{
		return sensitiveVarietal;
	}

	public void setSensitiveVarietal(double sensitiveVarietal)
	{
		this.sensitiveVarietal = sensitiveVarietal;
	}

	public double getSensitiveYear()
	{
		return sensitiveYear;
	}

	public void setSensitiveYear(double sensitiveYear)
	{
		this.sensitiveYear = sensitiveYear;
	}

	public double getSensitiveVineyard()
	{
		return sensitiveVineyard;
	}

	public void setSensitiveVineyard(double sensitiveVineyard)
	{
		this.sensitiveVineyard = sensitiveVineyard;
	}

	public double getSensitiveAppellation()
	{
		return sensitiveAppellation;
	}

	public void setSensitiveAppellation(double sensitiveAppellation)
	{
		this.sensitiveAppellation = sensitiveAppellation;
	}

	public int getWineAmount()
	{
		return wineAmount;
	}

	public void setWineAmount(int wineAmount)
	{
		this.wineAmount = wineAmount;
	}

	public String getWineName()
	{
		return wineName;
	}

	public void setWineName(String wineName)
	{
		this.wineName = wineName;
	}

	public boolean isFromCellar()
	{
		return fromCellar;
	}

	public void setFromCellar(boolean fromCellar)
	{
		this.fromCellar = fromCellar;
	}

	public boolean isFromOnline()
	{
		return fromOnline;
	}

	public void setFromOnline(boolean fromOnline)
	{
		this.fromOnline = fromOnline;
	}
}