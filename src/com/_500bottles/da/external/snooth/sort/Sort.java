package com._500bottles.da.external.snooth.sort;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/22/13 Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Sort
{
	protected String sort;

	protected void setSort(String sort)
	{
		this.sort = sort;
	}

	/**
	 * Returns the API argument string for product.
	 * 
	 * @return Color string.
	 */
	@Override
	public String toString()
	{
		return sort;
	}

	public boolean equals(Sort s)
	{
		if (this.toString().equals(s.toString()))
			return true;

		return false;
	}
}
