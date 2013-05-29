package com._500bottles.da.external.snooth;

import com._500bottles.da.external.snooth.exception.InvalidColor;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/22/13
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class Color
{
	private static final String validColors[] =
		{"red", "white", "rose", "amber", "clear"};

	private String color = "";

	/**
	 * Generic parameter-free constructor. Essentially creates a color equal
	 * to all colors and therefore will not narrow a query.
	 */
	public Color() {}

	/**
	 * Generates a product color corresponding to a specific color. Valid
	 * colors are listed in the validColors[] array and are: "red", "white",
	 * "rose", "amber", and "clear".
	 * @param color	Color of product.
	 * @throws com._500bottles.da.external.snooth.exception.InvalidColor
	 */
	public Color(String color) throws InvalidColor
	{
		if (!validateColor(color))
			throw new InvalidColor();

		this.color = color;
	}

	/**
	 * Returns the API argument string for product.
	 * @return	Color string.
	 */
	public String toString()
	{
		return color;
	}

	public boolean equals(Color c)
	{
		if (c == null)
			return false;

		if (this.color.equals(c.toString()))
			return true;

		return false;
	}

	/**
	 * Validates the specified colors string with known valid colors.
	 * @param color	Type to validate.
	 * @return	True if valid, else false.
	 */
	private static boolean validateColor(String color)
	{
		for (int i = 0; i < validColors.length; i++) {
			if (color.equals(validColors[i]))
				return true;
		}

		return false;
	}
}
