package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;

public class RatingsDAO extends DAO
{
	private final static String RATINGS_TABLE = Config
			.getProperty("ratingsTableName");

	/**
	 * Adds a rating for a wine from a user to the ratings table
	 * 
	 * @param userId
	 *            The ID of the user whose rating is being added
	 * @param wineId
	 *            The ID of the wine for the rating
	 * @param rating
	 *            The rating of the wine for the user
	 * @return the ratingsId primary key in the ratings table
	 * @throws DAException
	 */
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
		} else
		{
			throw new DAException("Rating already exists");
		}
		return getLastInsertId();
	}

	/**
	 * Deletes the rating for the specified user and wine
	 * 
	 * @param userId
	 *            The ID of the user for the rating being deleted
	 * @param wineId
	 *            The ID of the wine for the rating being deleted
	 * @return true if the rating was deleted, false otherwise
	 */
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

	/**
	 * Edits the rating for the specified wine and user
	 * 
	 * @param userId
	 *            The ID of the user to edit the rating for
	 * @param wineId
	 *            The ID of the wine for the rating to be updated
	 * @param rating
	 *            The new updated rating
	 * @throws DAException
	 */
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

	/**
	 * Gets all the ratings for the specified user
	 * 
	 * @param userId
	 *            The ID of the user get all their ratings from
	 * @return Vector of Longs which contains the ratingsIds
	 * @throws DAException
	 */
	public static Vector<Long> getAllRatings(long userId) throws DAException
	{
		ResultSet r;
		Vector<Long> ret = new Vector<Long>();
		String where = "userId = " + userId;
		try
		{
			r = select(RATINGS_TABLE, "*", where);
		} catch (SQLException e)
		{
			throw new DAException("SQL Select exception.", e);
		}
		try
		{
			while (r.next())
			{
				long temp;
				temp = r.getLong("ratingsId");
				ret.add(temp);
			}
		} catch (SQLException e)
		{
			throw new DAException("ResultSet exception problem");
		}
		return ret;
	}

	/**
	 * Gets the rating of a user for a specific wine
	 * 
	 * @param userId
	 *            The ID of the user to get the rating for
	 * @param wineId
	 *            The ID of the wine to get the rating for
	 * @return the rating for the specified wine by the user
	 * @throws DAException
	 */
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
			throw new DAException("SQL select exception.", e);
		}
		try
		{
			if (r.next())
				rating = r.getDouble("rating");
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("ResultSet Exception");
		}
		return rating;
	}

	/**
	 * Helper method that checks if a rating by a user for a wine exists already
	 * 
	 * @param userId
	 *            The ID of the user to check
	 * @param wineId
	 *            The ID of the wine to check
	 * @return true if the rating exists, false otherwise
	 */
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
