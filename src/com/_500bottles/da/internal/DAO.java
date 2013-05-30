package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstract Data Access Object. This object is extended by all DAO objects to
 * provide easy interfaces to insert, delete, update and select rows from the
 * database.
 */
public abstract class DAO
{
	/**
	 * Date format string for storing dates in the database.
	 */
	private static final String sqlDateFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Inserts a record into the specified table.
	 * 
	 * @param table
	 *            The table into which to insert the record.
	 * @param columns
	 *            The columns tuple identifying the columns associated with the
	 *            values.
	 * @param values
	 *            The values to insert into the specified columns.
	 * @return Returns an integer representing the number of rows inserted.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static int insert(String table, String columns, String values)
			throws SQLException
	{
		String sql = "INSERT INTO " + table + " ";
		sql += columns + " ";
		sql += "VALUES " + values + ";";

		// System.out.println(sql);

		return Database.modQuery(sql);
	}

	/**
	 * Returns the ID of the last auto-increment insertion operation.
	 * 
	 * @return Last auto-increment id.
	 */
	public static long getLastInsertId()
	{
		return Database.getLastInsertId();
	}

	/**
	 * Deletes a record from the specified table using the passed WHERE
	 * statement.
	 * 
	 * @param table
	 *            The table to delete the record from.
	 * @param where
	 *            The WHERE clause for the deletion.
	 * @return Returns an integer representing the number of rows deleted.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int delete(String table, String where) throws SQLException
	{
		String sql = "DELETE FROM " + table + " WHERE " + where + ";";

		return Database.modQuery(sql);
	}

	/**
	 * Updates a record in the specified table.
	 * 
	 * @param table
	 *            The table in-which to update the record.
	 * @param set
	 *            The updated information.
	 * @param where
	 *            The WHERE clause for the update statement.
	 * @return Returns an integer representing the number of rows updated.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int update(String table, String set, String where)
			throws SQLException
	{
		String sql = "UPDATE " + table + " SET " + set + " WHERE " + where
				+ ";";

		return Database.modQuery(sql);
	}

	/**
	 * Selects items from the database with the specified table, SELECT
	 * statement and WHERE clause.
	 * 
	 * @param table
	 *            The table in-which to select records.
	 * @param select
	 *            The SELECT statement.
	 * @param where
	 *            The WHERE clause.
	 * @return Returns a ResultSet object for the query.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet select(String table, String select, String where)
			throws SQLException
	{
		String sql = "SELECT " + select + " FROM " + table;
		sql += " WHERE " + where + ";";
		// System.out.println(sql);
		return Database.readQuery(sql);
	}

	public static ResultSet select(String table, String select)
			throws SQLException
	{
		String sql = "SELECT " + select + " FROM " + table + ";";

		return Database.readQuery(sql);
	}

	/**
	 * Formats the specified Date object for storage in the SQL database.
	 * 
	 * @param date
	 *            The Date object to format as a string.
	 * @return The Date formatted as a String object.
	 */
	protected static String formatDate(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(sqlDateFormat);

		return sdf.format(date);
	}

	/**
	 * Parses the specified date string and returns a Date object.
	 * 
	 * @param dateString
	 *            The String object to parse as a date.
	 * @return The Date formatted as a String.
	 */
	protected static Date parseDate(String dateString)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(sqlDateFormat);
		ParsePosition pp = new ParsePosition(0);

		return sdf.parse(dateString, pp);
	}
}
