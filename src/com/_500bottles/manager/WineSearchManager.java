package com._500bottles.manager;
 
import java.util.Vector;
 
import com._500bottles.object.wine.Wine;
 
public class WineSearchManager
{
        private Vector<String> wineType = new Vector<String>();
        private Vector<Wine> allTheWines;
        private String appellation;// set for advanced search level five
        private String year;// set for advanced search level three
        private String varietal;// set for advanced search level two
        private String vineyard;// set for advanced level four
        private String wineName;
        private Vector<Wine> selected = new Vector<Wine>();// the list of
 
        // wines to be
        // returned
        public void findWine()
        {
                int count = 0;
                int atts = 0;
 
                // TODO get all the wines of the specific name and put it into
                // allTheWines
                for (int i = 0; i < allTheWines.size(); i++)
                {
                        if (wineType != null)
                        {
                                atts++;
                                for (int q = 0; q < wineType.size(); q++)
                                {
 
                                        if (allTheWines.get(i).getType().getWineType()
                                                        .equals(wineType.get(q)))
                                        {
                                                count++;
                                        }
                                }
                        }
                        // check to see if wines don't have the required varietal the user
                        // set
                        if (varietal != null)
                        {
                                atts++;
                                if (allTheWines.get(i).getVarietal().getGrapeType()
                                                .equals(varietal))
                                {
                                        count++;
                                }
                        }
                        // check to see if wines don't have the required year the user set
                        if (year != null)
                        {
                                Integer r = new Integer((int) allTheWines.get(i).getYear());
                                String s = r.toString();// converts it to a string
                                atts++;
                                if (s.equals(year))
                                {
                                        count++;
                                }
                        }
                        // check to see if wines don't have the required vineyard the user
                        // set
                        if (vineyard != null)
                        {
                                atts++;
                                if (allTheWines.get(i).getVineyard().getName().equals(vineyard))
                                {
                                        count++;
                                }
                        }
                        // check to see if wines don't have the required appellation the
                        // user set
                        if (appellation != null)
                        {
                                atts++;
                                if (allTheWines.get(i).getAppellation().getLocation()
                                                .equals(appellation))
                                {
                                        count++;
                                }
                        }
                        if (atts == count)
                        {
                                selected.add(allTheWines.get(i));
                        }
                }
        }
 
        public String getWineName()
        {
                return wineName;
        }
 
        public void setWineName(String wineName)
        {
                this.wineName = wineName;
        }
}