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

public class CustomWineDAO extends WineDAO
{
	private final static String CUSTOMWINE_TABLE = Config
			.getProperty("customWineTableName");

	public static CustomWine addCustomWine(CustomWine wine) throws DAException
	{

		// if (!customWineExists(wine))
		// {
		// Varietal varietal = new Varietal();
		// Vineyard vineyard = new Vineyard();
		// WineType wineType = new WineType();
		//
		// /*
		// * if (!varietalExists(wine.getVarietal()))
		// * addVarietal(wine.getVarietal()); if
		// * (!vineyardExists(wine.getVineyard()))
		// * addVineyard(wine.getVineyard()); if
		// * (!wineTypeExists(wine.getType())) addWineType(wine.getType());
		// */
		// try
		// {
		// varietal = getVarietal(wine.getVarietal().getGrapeType());
		//
		// vineyard = getVineyard(wine.getVineyard().getName());
		//
		// wineType = getWineType(wine.getType().getWineType());
		// } catch (DAException e)
		// {
		// }
		//
		// // If vineyard, varietal, winetype dont exist, then add a new one
		// if (varietal == null)
		// addVarietal(wine.getVarietal());
		// if (vineyard == null)
		// addVineyard(wine.getVineyard());
		// if (wineType == null)
		// addWineType(wine.getType());
		//
		// String columns, values;
		// long userId;
		//
		// userId = SessionManager.getSessionManager().getLoggedInUser()
		// .getUserId();
		//
		// columns = "(`wineId`, `userId`,";
		// columns += " `varietalId`, `vineyardId`,";
		// columns += " `wineTypeId`, `wineName`,";
		// columns += " `vintage`, `description`)";
		//
		// values = "("; // TODO: figure out hwo to get wineId
		// values += "'" + userId + "',";
		// if (varietal == null)
		// values += "'" + wine.getVarietal().getId() + "',";
		// else
		// values += "'" + varietal.getId() + "',";
		// if (vineyard == null)
		// values += "'" + wine.getVineyard().getId() + "',";
		// else
		// values += "'" + vineyard.getId() + "',";
		// if (wineType == null)
		// values += "'" + wine.getType().getWineTypeId() + "',";
		// else
		// values += "'" + wineType.getWineTypeId() + "',";
		// values += "'" + "',";
		// }
		if (!wineExists(wine))
		{
			CustomWine ret = new CustomWine();
			Wine temp = new Wine();
			temp = addWine(wine);

			// long wineId, userId, wineTypeId, vineyardId, varietalId, vintage;
			// String name, description, imageURL;

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

			columns = "(`wineId`, `userId`,";
			columns += " `varietalId`, `vineyardId`,";
			columns += " `wineTypeId`, `wineName`,";
			columns += " `vintage`, `description`, `imageURL`)";

			try
			{
				varietal = getVarietal(wine.getVarietal().getGrapeType());

				vineyard = getVineyard(wine.getVineyard().getName());

				wineType = getWineType(wine.getType().getWineType());
			} catch (DAException e)
			{
			}

			// If vineyard, varietal, winetype dont exist, then add a new one
			if (varietal == null)
				addVarietal(wine.getVarietal());
			if (vineyard == null)
				addVineyard(wine.getVineyard());
			if (wineType == null)
				addWineType(wine.getType());

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
			// throw new DAException("Failed Wine deletion", e.getCause());
		}
		if (ret == 0)
			return false;
		return true;
	}

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

		if (getVineyard(wine.getVineyard().getName()) != null)
			addVineyard(wine.getVineyard());
		// System.out.println(wine.getVineyard().getName());
		// System.out.println(wine.getVarietal().getGrapeType());
		if (getVarietal(wine.getVarietal().getGrapeType()) != null)
			addVarietal(wine.getVarietal());
		if (getWineType(wine.getType().getWineType()) != null)
			addWineType(wine.getType());
	}

	public static CustomWine getCustomWine(long customId) throws DAException
	{
		// System.out.println("getWine wineId: " + wineId);
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
		// System.out.println("wineId: " + wineId);

		description = unescapeXml(r.getString("description"));
		year = r.getLong("vintage");

		wineTypeId = r.getLong("wineTypeId");
		try
		{
			type = getWineTypeById(wineTypeId);

		} catch (DAException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * try { wineType = getWineTypeById(wineTypeId).getWineType(); } catch
		 * (DAException e) { throw new SQLException(e.getMessage()); } if
		 * (wineType != null) type.setWineType(wineType);
		 */

		varId = r.getLong("varietalId");

		try
		{
			varietal = getVarietalById(varId);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * try { grapeType = getVarietalById(varId).getGrapeType(); } catch
		 * (DAException e) { throw new SQLException(e.getMessage()); } if
		 * (grapeType != null) varietal.setGrapeType(grapeType);
		 */
		vineId = r.getLong("vineyardId");

		try
		{
			vineyard = getVineyardNameById(vineId);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * try { vineyardName = getVineyardNameById(vineId).getName(); } catch
		 * (DAException e) { throw new SQLException(e.getMessage()); } if
		 * (vineyardName != null) vineyard.setName(vineyardName);
		 */

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

		// System.out.println("does it go right here");
		return wine;
	}

	private static boolean customWineExists(CustomWine wine)
	{
		return true;
	}

}