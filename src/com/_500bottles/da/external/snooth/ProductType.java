package com._500bottles.da.external.snooth;

import com._500bottles.da.external.snooth.exception.InvalidProductType;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/22/13
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProductType {

	private static final String validTypes[] =
		{"wine", "spirits", "table", "dessert",
		"sparkling", "fruit", "cider", "sake"};

	private String type = "";

	/**
	 * Generic parameter-free constructor. Essentially creates a product
	 * type corresponding to all product types.
	 */
	public ProductType() {}

	/**
	 * Generates a product type corresponding to a specific type of
	 * product. Valid products are "wine", "spirits", "table", "dessert",
	 * "sparkling", "fruit", "cider", and "sake".
	 * @param type	Type of product.
	 * @throws InvalidProductType
	 */
	public ProductType(String type) throws InvalidProductType
	{
		if (!validateType(type))
			throw new InvalidProductType();

		this.type = type;
	}

	/**
	 * Returns the API argument string for product.
	 * @return	Product string.
	 */
	public String toString()
	{
		return type;
	}

	public boolean equals(ProductType t)
	{
		if (t.toString() == this.toString())
			return true;
		return false;
	}

	/**
	 * Validates the specified product type string with known valid types.
	 * @param type	Type to validate.
	 * @return	True if valid, else false.
	 */
	private static boolean validateType(String type)
	{
		for (int i = 0; i < validTypes.length; i++) {
			if (type.equals(validTypes[i]))
				return true;
		}

		return false;
	}
}
