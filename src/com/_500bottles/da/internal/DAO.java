package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public abstract class DAO
{
	/**
	 * Inserts a record into the specified table.
	 * @param table		The table into which to insert the record.
	 * @param columns	The columns tuple identifying the columns
	 *                      associated with the values.
	 * @param values	The values to insert into the specified columns.
	 * @return		Returns an integer representing the number of
	 * 			rows inserted.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int insert(String table, String columns, String values)
		throws SQLException, ClassNotFoundException
	{
		String sql = "INSERT INTO " + table + " ";
		sql += columns + " ";
		sql += "VALUES " + values + ";";

		return Database.modQuery(sql);
	}

	/**
	 * Deletes a record from the specified table using the passed WHERE
	 * statement.
	 * @param table		The table to delete the record from.
	 * @param where		The WHERE clause for the deletion.
	 * @return		Returns an integer representing the number of
	 * 			rows deleted.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int delete(String table, String where)
		throws ClassNotFoundException, SQLException
	{
		String sql = "DELETE FROM " + table + " WHERE " + where + ";";

		return Database.modQuery(sql);
	}

	/**
	 * Updates a record in the specified table.
	 * @param table		The table in-which to update the record.
	 * @param set		The updated information.
	 * @param where		The WHERE clause for the update statement.
	 * @return		Returns an integer representing the number
	 * 			of rows updated.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int update(String table, String set, String where)
		throws ClassNotFoundException, SQLException
	{
		String sql = "UPDATE " + table + " " + set + " " + where + ";";

		return Database.modQuery(sql);
	}

	/**
	 * Selects items from the database with the specified table, SELECT
	 * statement and WHERE clause.
	 * @param table 	The table in-which to select records.
	 * @param select	The SELECT statement.
	 * @param where		The WHERE clause.
	 * @return 		Returns a ResultSet object for the query.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ResultSet select(String table, String select, String where)
			throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT " + select + " FROM " + table;
		sql += " WHERE " + where + ";";

		return Database.readQuery(sql);
	}
}
