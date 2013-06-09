package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;

public class RatingsDAO extends DAO
{
	private final static String RATINGS_TABLE = Config
			.getProperty("ratingsTableName");

	// returns ratingsId
	public static long addRating(long userId, long wineId, double rating)
			throws DAException
	{
		if (!ratingExists(userId, wineId))
		{
			String columns, values;
			columns = "(`userID`, ";
			columns += "`wineID`, ";
			columns += "`rating`)";

			values = "('" + userId + "',";
			values += "'" + wineId + "',";
			values += "'" + rating + "')";
			try
			{
				insert(RATINGS_TABLE, columns, values);
				Database.disconnect();
			} catch (SQLException e)
			{
				throw new DAException("Failed Rating insertion", e);
			}
		}
		return getLastInsertId();
	}

	public static boolean deleteRating(long userId, long wineId)
	{
		try
		{
			delete(RATINGS_TABLE, "wineId=" + wineId + " and userId=" + userId);
			Database.disconnect();
		} catch (SQLException e)
		{
			return false;
			// throw new DAException("Failed Favorites deletion", e);
		}
		return true;
	}

	public static void editRating(long userId, long wineId, double rating)
			throws DAException
	{
		String sql = "";

		sql += "rating=" + rating;

		try
		{
			update(RATINGS_TABLE, sql, "userId=" + userId + " and wineId="
					+ wineId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Rating update", e);
		}
	}

	public static double getRating(long userId, long wineId) throws DAException
	{
		ResultSet r;
		double rating = 0;
		try
		{
			String where = "wineId = " + wineId;
			where += " AND " + "userId = " + userId;
			r = select(RATINGS_TABLE, "*", where);

		} catch (SQLException e)
		{
			// System.out.println("Message: " + e.getMessage());
			throw new DAException("SQL select exception.", e);
		}
		try
		{
			rating = r.getDouble("rating");
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("ResultSet Exception");
		}
		return rating;
	}

	private static boolean ratingExists(long userId, long wineId)
	{
		ResultSet r;
		try
		{
			String where = "wineId = " + wineId;
			where += " AND " + "userId = " + userId;
			r = select(RATINGS_TABLE, "*", where);

		} catch (SQLException e)
		{
			// System.out.println("Message: " + e.getMessage());
			return false;
		}

		try
		{
			if (!r.next())
				return false;
		} catch (SQLException e)
		{
			return false;
		}
		return true;
	}

}
