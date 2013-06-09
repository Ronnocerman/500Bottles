package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;
import static org.apache.commons.lang3.StringEscapeUtils.unescapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.manager.SessionManager;
import com._500bottles.object.wine.CustomWine;
import com._500bottles.object.wine.Varietal;
import com._500bottles.object.wine.Vineyard;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineType;

/**
 * Coordinates all custom wine related database access for custom wine adding,
 * deleting, and getting in the database.
 */
public class CustomWineDAO extends WineDAO
{
	private final static String CUSTOMWINE_TABLE = Config
			.getProperty("customWineTableName");

	/**
	 * Adds custom wine to custom wine table and wine table
	 * 
	 * @param wine
	 *            The custom wine to be added to the database
	 * @return The added custom wine
	 * @throws DAException
	 */
	public static CustomWine addCustomWine(CustomWine wine) throws DAException
	{
		// Check if wine exists in wine table already
		if (!wineExists(wine))
		{
			CustomWine ret = new CustomWine();
			Wine temp = new Wine();
			temp = addWine(wine);

			// Set the values for the custom wine
			ret.setId(temp.getId());
			ret.setVarietal(temp.getVarietal());
			ret.setVineyard(temp.getVineyard());
			ret.setType(temp.getType());
			ret.setYear(temp.getYear());
			ret.setName(temp.getName());
			ret.setDescription(temp.getDescription());
			ret.setImage(temp.getImage());
			ret.setUserId(SessionManager.getSessionManager().getLoggedInUser()
					.getUserId());

			String columns, values;

			Varietal varietal = new Varietal();
			Vineyard vineyard = new Vineyard();
			WineType wineType = new WineType();

			// Construct SQL query
			columns = "(`wineId`, `userId`,";
			columns += " `varietalId`, `vineyardId`,";
			columns += " `wineTypeId`, `wineName`,";
			columns += " `vintage`, `description`, `imageURL`)";

			try
			{
				varietal = VarietalDAO.getVarietal(wine.getVarietal()
						.getGrapeType());

				vineyard = VineyardDAO
						.getVineyard(wine.getVineyard().getName());

				wineType = WineTypeDAO
						.getWineType(wine.getType().getWineType());
			} catch (DAException e)
			{
			}

			// If vineyard, varietal, winetype dont exist, then add a new one
			if (varietal == null)
				VarietalDAO.addVarietal(wine.getVarietal());
			if (vineyard == null)
				VineyardDAO.addVineyard(wine.getVineyard());
			if (wineType == null)
				WineTypeDAO.addWineType(wine.getType());

			values = "('" + ret.getId() + "',";
			values += "'" + ret.getUserId() + "',";
			if (varietal == null)
				values += "'" + ret.getVarietal().getId() + "',";
			else
				values += "'" + varietal.getId() + "',";
			if (vineyard == null)
				values += "'" + ret.getVineyard().getId() + "',";
			else
				values += "'" + vineyard.getId() + "',";
			if (wineType == null)
				values += "'" + ret.getType().getWineTypeId() + "',";
			else
				values += "'" + wineType.getWineTypeId() + "',";
			values += "'" + escapeXml(ret.getName()) + "',";
			values += "'" + ret.getYear() + "',";
			values += "'" + escapeXml(ret.getDescription()) + "',";
			values += "'" + escapeXml(ret.getImage()) + "')";

			try
			{
				insert(CUSTOMWINE_TABLE, columns, values);
				Database.disconnect();
			} catch (SQLException e)
			{
				throw new DAException("Failed Custom Wine insertion", e);
			}

			ret.setCustomId(getLastInsertId());

			return ret;
		} else
		{
			throw new DAException("Wine already exists.");
		}
	}

	/**
	 * Deletes custom wine from wines table and custom wines table
	 * 
	 * @param wine
	 *            The wine to be deleted
	 * @return true if wine was deleted, false otherwise.
	 */
	public static boolean deleteCustomWine(CustomWine wine)
	{
		deleteWine(wine);
		int ret;
		try
		{
			ret = delete(CUSTOMWINE_TABLE, "customId=" + wine.getCustomId());
			Database.disconnect();
		} catch (SQLException e)
		{
			return false;
		}
		if (ret == 0)
			return false;
		return true;
	}

	/**
	 * Edits the custom wine specified
	 * 
	 * @param wine
	 *            The edited wine to be updated in the table
	 * @throws DAException
	 */
	public static void editCustomWine(CustomWine wine) throws DAException
	{
		editWine(wine);

		long customId = wine.getCustomId(); // returns the wineId

		String sql = "";
		sql += "wineId=" + wine.getId();
		sql += ",vineyardId=" + wine.getVineyard().getId();
		sql += ",varietalId=" + wine.getVarietal().getId();
		sql += ",wineName='" + escapeXml(wine.getName()) + "'";
		sql += ",wineTypeId=" + wine.getType().getWineTypeId();
		sql += ",vintage=" + wine.getYear();
		sql += ",description='" + escapeXml(wine.getDescription()) + "'";
		sql += ",imageUrl='" + escapeXml(wine.getImage()) + "'";

		try
		{
			update(CUSTOMWINE_TABLE, sql, "customId=" + customId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Wine update", e);
		}

		// Add to vineyard, varietal, winetype tables if they don't exist there
		// yet
		if (VineyardDAO.getVineyard(wine.getVineyard().getName()) != null)
			VineyardDAO.addVineyard(wine.getVineyard());

		if (VarietalDAO.getVarietal(wine.getVarietal().getGrapeType()) != null)
			VarietalDAO.addVarietal(wine.getVarietal());
		if (WineTypeDAO.getWineType(wine.getType().getWineType()) != null)
			WineTypeDAO.addWineType(wine.getType());
	}

	/**
	 * Gets the custom wine from the custom wine ID
	 * 
	 * @param customId
	 *            ID of custom wine to get.
	 * @return the custom wine with the given ID
	 * @throws DAException
	 */
	public static CustomWine getCustomWine(long customId) throws DAException
	{
		ResultSet r;
		CustomWine wine;

		try
		{
			// System.out.println(wineId);
			r = select(CUSTOMWINE_TABLE, "*", "customId=" + customId);
			// System.out.println("after the select in getWine");

		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e.getCause());
		}
		try
		{
			wine = createCustomWine(r);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Wine creation error.");
		}
		return wine;
	}

	/**
	 * Helper method which creates the custom wine to be returned
	 * 
	 * @param r
	 *            ResultSet to construct the custom wine from
	 * @return custom wine created from the ResultSet
	 * @throws SQLException
	 */
	private static CustomWine createCustomWine(ResultSet r) throws SQLException
	{
		CustomWine wine;

		long wineId, year, varId, vineId, wineTypeId, userId, customId;

		String name, description, imageUrl;

		Varietal varietal = new Varietal();
		Vineyard vineyard = new Vineyard();
		WineType type = new WineType();

		if (!r.next())
			return null;

		name = unescapeXml(r.getString("wineName"));
		wineId = r.getLong("wineId");

		description = unescapeXml(r.getString("description"));
		year = r.getLong("vintage");

		wineTypeId = r.getLong("wineTypeId");
		try
		{
			type = WineTypeDAO.getWineTypeById(wineTypeId);

		} catch (DAException e1)
		{
			e1.printStackTrace();
		}

		varId = r.getLong("varietalId");

		try
		{
			varietal = VarietalDAO.getVarietalById(varId);
		} catch (DAException e)
		{
			e.printStackTrace();
		}

		vineId = r.getLong("vineyardId");

		try
		{
			vineyard = VineyardDAO.getVineyardById(vineId);
		} catch (DAException e)
		{
			e.printStackTrace();
		}

		description = unescapeXml(r.getString("description"));
		imageUrl = unescapeXml(r.getString("imageUrl"));

		userId = r.getLong("userId");
		customId = r.getLong("customId");

		// System.out.println(" everything");

		wine = new CustomWine();

		// Set all properties of Wine
		wine.setName(name);
		wine.setId(wineId);
		wine.setCustomId(customId);
		wine.setUserId(userId);

		wine.setDescription(description);
		wine.setYear(year);
		wine.setType(type);

		wine.setVarietal(varietal);
		wine.setVineyard(vineyard);

		wine.setImage(imageUrl);

		return wine;
	}
}