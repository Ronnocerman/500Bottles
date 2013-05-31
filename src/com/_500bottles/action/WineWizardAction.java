package com._500bottles.action;

import java.util.Vector;

import com._500bottles.manager.WineWizardManager;
import com._500bottles.object.wine.Wine;

public class WineWizardAction
{
	private Vector<String> wineType = new Vector<String>();// set for
	// advance
	// search
	// level one
	private boolean fromCellar;// a flag to see if user wants wine from their
								// cellar
	private long userID;
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
	private int count;
	private boolean wasOdd;
	private Vector<Wine> returnWineCellar = new Vector<Wine>();// the list
																// of
	// wines to
	// return
	private Vector<Wine> returnWineApi = new Vector<Wine>();

	// returns wines by parsing a list
	public Wine getWines()
	{
		if (counter < returnWineCellar.size())
		{
			counter++;
			return returnWineCellar.get(counter - 1);
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
		wasOdd = false;
	}

	// gets the wines suggestion based on if user traits have been set
	public void getSuggestion()
	{
		// used to check if from online and Cellar in order to mess with
		// wineAmount to space out between the two
		if (fromCellar && fromOnline)
		{
			// if it is odd then it will drop one from the api selection in the
			// end
			if (wineAmount % 2 == 1)
			{
				wasOdd = true;
				wineAmount += 1;
			}
			wineAmount = wineAmount / 2;
		}
		// sets counter to zero
		counter = 0;
		count = 0;
		// creates new WineWizardManager
		WineWizardManager wizardManager = new WineWizardManager(wineAmount,
				userID);
		// sets the check for if they want it from the cellar in the manager
		// level
		if (fromCellar)
		{
			wizardManager.setFromCellar(true);
		}
		// sets the check for if they want it from the api in the manager level
		if (fromOnline)
		{
			wizardManager.setFromOnline(true);
		}
		// sets the sensitiveAppellation
		if (sensitiveAppellation != -1)
		{
			wizardManager.setSensitiveAppellation(sensitiveAppellation);
		}
		// sets the sensitiveYear in the wineWizardManager
		if (sensitiveYear != -1)
		{
			wizardManager.setSensitiveYear(sensitiveYear);
		}
		// sets the sensitiveVineyard in the wineWizardManager
		if (sensitiveVineyard != -1)
		{
			wizardManager.setSensitiveVineyard(sensitiveVineyard);
		}
		// sets the sensitiveVarietal in the wineWizardManager
		if (sensitiveVarietal != -1)
		{
			wizardManager.setSensitiveVarietal(sensitiveVarietal);
		}
		// sets the sensitiveType in the wineWizardManager
		if (sensitiveType != -1)
		{
			wizardManager.setSensitiveType(sensitiveType);
		}
		// sets the wineType in wineWizardManger
		if (wineType != null)
		{
			wizardManager.setWineType(wineType);
		}
		// sets the appellation in wineWizardManger
		if (appellation != null)
		{
			wizardManager.setAppellation(appellation);
		}
		// sets the year in wineWizardManger
		if (year != null)
		{
			wizardManager.setYear(year);
		}
		// sets the varietal in wineWizardManger
		if (varietal != null)
		{
			wizardManager.setVarietal(varietal);
		}
		// sets the vineyard in wineWizardManger
		if (vineyard != null)
		{
			wizardManager.setAppellation(vineyard);
		}
		// if wine name not null then go into here and find the wines with the
		// traits and the name

		// if wine not set then go into here and get a suggestion based on the
		// rating
		if (fromCellar)
			returnWineCellar = wizardManager.selectWine();
		if (fromOnline)
			setReturnWineApi(wizardManager.getSelectedApi());
		// if odd was set then remove one from the returnWineApi
		if (wasOdd)
		{
			returnWineApi.remove(returnWineApi.size() - 1);
		}

	}

	public Vector<String> getWineType()
	{
		return wineType;
	}

	// sets the wine type based on an array form the dispatch level
	public void setWineType(String[] wineType)
	{
		for (int i = 0; i < wineType.length; i++)
		{
			this.wineType.add(wineType[i]);
		}

	}

	// gets the appellation set at this level
	public String getAppellation()
	{
		return appellation;
	}

	// sets the appellation
	public void setAppellation(String appellation)
	{
		this.appellation = appellation;
	}

	// gets the year
	public String getYear()
	{
		return year;
	}

	// sets the year
	public void setYear(String year)
	{
		this.year = year;
	}

	// gets the varietal
	public String getVarietal()
	{
		return varietal;
	}

	// sets the varietal
	public void setVarietal(String varietal)
	{
		this.varietal = varietal;
	}

	// gets the Vineyard
	public String getVineyard()
	{
		return vineyard;
	}

	// set the Vineyard
	public void setVineyard(String vineyard)
	{
		this.vineyard = vineyard;
	}

	// gets the sensitive type
	public double getSensitiveType()
	{
		return sensitiveType;
	}

	// sets the Sensitive
	public void setSensitiveType(double sensitiveType)
	{
		this.sensitiveType = sensitiveType;
	}

	// get the sensitiveVarietal
	public double getSensitiveVarietal()
	{
		return sensitiveVarietal;
	}

	// set the Sensitive Varietal
	public void setSensitiveVarietal(double sensitiveVarietal)
	{
		this.sensitiveVarietal = sensitiveVarietal;
	}

	// get the sensitive year
	public double getSensitiveYear()
	{
		return sensitiveYear;
	}

	// sets the sensitive year
	public void setSensitiveYear(double sensitiveYear)
	{
		this.sensitiveYear = sensitiveYear;
	}

	// gets the sensitive vineyard
	public double getSensitiveVineyard()
	{
		return sensitiveVineyard;
	}

	// set sensitive Vineyard
	public void setSensitiveVineyard(double sensitiveVineyard)
	{
		this.sensitiveVineyard = sensitiveVineyard;
	}

	// get sensitive appellation
	public double getSensitiveAppellation()
	{
		return sensitiveAppellation;
	}

	// sets the sensitive appellation
	public void setSensitiveAppellation(double sensitiveAppellation)
	{
		this.sensitiveAppellation = sensitiveAppellation;
	}

	// gets the wineAmount
	public int getWineAmount()
	{
		return wineAmount;
	}

	// sets the wine amount
	public void setWineAmount(int wineAmount)
	{
		this.wineAmount = wineAmount;
	}

	// gets the wine name
	public String getWineName()
	{
		return wineName;
	}

	// sets the wine Name
	public void setWineName(String wineName)
	{
		this.wineName = wineName;
	}

	// returns fromCellar boolean
	public boolean isFromCellar()
	{
		return fromCellar;
	}

	// sets the fromCellar
	public void setFromCellar(boolean fromCellar)
	{
		this.fromCellar = fromCellar;
	}

	// gets fromOnline
	public boolean isFromOnline()
	{
		return fromOnline;
	}

	// sets fromOnline
	public void setFromOnline(boolean fromOnline)
	{
		this.fromOnline = fromOnline;
	}

	// TODO mess with these in like the other return
	public Wine getReturnWineApi()
	{
		if (count < returnWineCellar.size())
		{
			count++;
			return returnWineApi.get(count - 1);
		}
		return null;
	}

	// sets the returnWineApi shouldn't be used but here for some reason
	public void setReturnWineApi(Vector<Wine> returnWineApi)
	{
		this.returnWineApi = returnWineApi;
	}

	// gets the userID
	public long getUserID()
	{
		return userID;
	}

	// sets the userID
	public void setUserID(long userID)
	{
		this.userID = userID;
	}
}