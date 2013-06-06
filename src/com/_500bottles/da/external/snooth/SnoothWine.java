package com._500bottles.da.external.snooth;

import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineType;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/24/13 Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class SnoothWine
{
	private String name;
	private String wm_notes;
	private String winery_tasting_notes;
	private String color;
	private double sugar;
	private double alcohol;
	private double ph;
	private double acidity;
	private String code;
	private String region;
	private String winery;
	private String winery_id;
	private String varietal;
	private String price;
	private String vintage;
	private String type;
	private String link;
	private String tags;
	private String image;
	private double snoothrank;
	private boolean available;
	private int num_merchants;
	private int num_reviews;

	@Override
	public String toString()
	{
		String s = "";

		s += "Name: " + getName() + "\n";
		s += "Code: " + getCode() + "\n";
		s += "Region: " + getRegion() + "\n";
		s += "Winery: " + getWinery() + "\n";
		s += "Winery ID: " + getWinery_id() + "\n";
		s += "Varietal: " + getVarietal() + "\n";
		s += "Price: " + getPrice() + "\n";
		s += "Vintage: " + getVintage() + "\n";
		s += "Type: " + getType() + "\n";
		s += "Link: " + getLink() + "\n";
		s += "Tags: " + getTags() + "\n";
		s += "Image: " + getImage() + "\n";
		s += "Wine Maker Notes: " + getWineMakerNotes() + "\n";
		s += "Winery Tasting Notes: " + getWineryTastingNotes() + "\n";
		s += "Sugar: " + getSugar() + "\n";
		s += "Alcohol: " + getAlcohol() + "\n";
		s += "pH: " + getPh() + "\n";
		s += "Acidity: " + getAcidity() + "\n";
		s += "Snooth Rank: " + getSnoothrank() + "\n";
		s += "Available: " + isAvailable() + "\n";
		s += "Num Merchants: " + getNum_merchants() + "\n";
		s += "Num Reviews: " + getNum_reviews() + "\n";

		return s;
	}

	public String getName()
	{
		return name;
	}

	public String getWineMakerNotes()
	{
		return wm_notes;
	}

	public void setWineMakerNotes(String wm_notes)
	{
		this.wm_notes = wm_notes;
	}

	public String getWineryTastingNotes()
	{
		return winery_tasting_notes;
	}

	public void setWineryTastingNotes(String winery_tasting_notes)
	{
		this.winery_tasting_notes = winery_tasting_notes;
	}

	public String getColor()
	{
		return color;
	}

	public double getSugar()
	{
		return sugar;
	}

	public void setSugar(String sugar)
	{
		if (sugar.length() == 0)
			this.sugar = -1;
		else
			this.sugar = Double.parseDouble(sugar);
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public double getAlcohol()
	{
		return alcohol;
	}

	public void setAlcohol(String alcohol)
	{
		if (alcohol.length() == 0)
			this.alcohol = -1;
		else
			this.alcohol = Double.parseDouble(alcohol);
	}

	public double getPh()
	{
		return ph;
	}

	public void setPh(String ph)
	{
		if (ph.length() == 0)
			this.ph = -1;
		else
			this.ph = Double.parseDouble(ph);
	}

	public double getAcidity()
	{
		return acidity;
	}

	public void setAcidity(String acidity)
	{
		if (acidity.length() == 0)
			this.acidity = -1;
		else
			this.acidity = Double.parseDouble(acidity);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getRegion()
	{
		return region;
	}

	public void setRegion(String region)
	{
		this.region = region;
	}

	public String getWinery()
	{
		return winery;
	}

	public void setWinery(String winery)
	{
		this.winery = winery;
	}

	public String getWinery_id()
	{
		return winery_id;
	}

	public void setWinery_id(String winery_id)
	{
		this.winery_id = winery_id;
	}

	public String getVarietal()
	{
		return varietal;
	}

	public void setVarietal(String varietal)
	{
		this.varietal = varietal;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	public String getVintage()
	{
		return vintage;
	}

	public void setVintage(String vintage)
	{
		this.vintage = vintage;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getTags()
	{
		return tags;
	}

	public void setTags(String tags)
	{
		this.tags = tags;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public double getSnoothrank()
	{
		return snoothrank;
	}

	public void setSnoothrank(Object snoothrank)
	{
		if (snoothrank instanceof String)
		{
			this.snoothrank = -1;

		} else if (snoothrank instanceof Long)
		{
			this.snoothrank = (new Long((Long) snoothrank)).doubleValue();

		} else
		{
			this.snoothrank = (Double) snoothrank;
		}
	}

	public boolean isAvailable()
	{
		return available;
	}

	public void setAvailable(long available)
	{
		if (available > 0)
			this.available = true;
		else
			this.available = false;
	}

	public int getNum_merchants()
	{
		return num_merchants;
	}

	public void setNum_merchants(long num_merchants)
	{
		this.num_merchants = (int) num_merchants;
	}

	public int getNum_reviews()
	{
		return num_reviews;
	}

	public void setNum_reviews(long num_reviews)
	{
		this.num_reviews = (int) num_reviews;
	}

	public Wine toWineObject()
	{
		Wine w = new Wine();

		w.setName(this.getName());
		w.setDescription(this.getWineMakerNotes());
		w.setSnoothId(this.getCode());
		// w.setGeoLocation();
		WineType type = new WineType(this.getType());
		w.setType(type);

		w.setImage(this.getImage());
		w.setPriceMin(Double.parseDouble(this.getPrice().replace(",", "")));
		w.setPriceMax(Double.parseDouble(this.getPrice().replace(",", "")));
		// TODO THE MOST IMPORTANT SHIT IN THE WORLD TO DO, fix snooth wine get
		// vintage, penis
		w.setYear(Long.valueOf(this.getVintage()));

		// Appellation appellation = new Appellation(this.getAppellation());
		// w.setAppellation();
		Varietal varietal = new Varietal(this.getVarietal());
		w.setVarietal(varietal);

		Vineyard vineyard = new Vineyard(this.getWinery());
		w.setVineyard(vineyard);
		// w.setRating(this.getRating());
		return w;
	}
}
