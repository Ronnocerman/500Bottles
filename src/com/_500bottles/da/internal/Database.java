package com._500bottles.da.internal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com._500bottles.config.Config;
import com.mysql.jdbc.Statement;

/**
 * Class to interface directly with database via JDBC drivers. Can conduct
 * queries that both modify and read information from the database.
 */
public class Database
{
	/* ID from last auto-increment insert operation. */
	private static long lastInsertId = 0;

	private static Connection conn = null;

	/**
	 * Conducts a query of the database the does not modify the contents of
	 * the database.
	 * @param q	Query to execute.
	 * @return      ResultSet object of query result.
	 * @throws SQLException
	 */
	public static ResultSet readQuery(String q) throws SQLException
	{
		PreparedStatement p;
		ResultSet r;

		try {
			if (conn == null)
				conn = connect();

			p = conn.prepareStatement(q);
			r = p.executeQuery(q);

		} catch (SQLException e) {
			throw e;
		}

		return r;
	}

	/**
	 * Conducts a query of the database that may modify database records.
	 * @param q	Query to execute.
	 * @return	Numeric result of database query.
	 * @throws SQLException
	 */
	public static int modQuery(String q) throws SQLException
	{
		PreparedStatement p;
		int i;

		try {
			if (conn == null)
				conn = connect();

			p = conn.prepareStatement(q);
			i = p.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
			setLastInsertId(p.getGeneratedKeys());

		} catch (SQLException e) {
			throw e;
		}

		return i;
	}



	/**
	 * Connects to the database and returns the Connection object.
	 * @return	Database Connection object.
	 * @throws SQLException
	 */
	public static Connection connect() throws SQLException
	{
		String dbUsername, dbPassword, connectionUrl;

		Connection connection;

		dbUsername = Config.getProperty("databaseUsername");
		dbPassword = Config.getProperty("databasePassword");
		connectionUrl = getConnectionUrl();

		try {
			connection = DriverManager.getConnection(connectionUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			throw e;
		}

		return connection;
	}

	/**
	 * Disconnects a database connection.
	 * @throws SQLException
	 */
	public static void disconnect() throws SQLException
	{
		if (conn == null) return;

		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * Returns the ID of the last auto-increment insertion operation.
	 * @return	Last auto-increment id.
	 */
	public static long getLastInsertId()
	{
		return lastInsertId;
	}

	/**
	 * Builds the JDBC connection URL using configuration information.
	 * @return	JDBC Connection URL
	 */
	private static String getConnectionUrl()
	{
		String dbPort, dbHost, dbName, connectionUrl;

		dbPort = Config.getProperty("databasePort");
		dbHost = Config.getProperty("databaseHost");
		dbName = Config.getProperty("databaseName");

		connectionUrl = "jdbc:mysql://" + dbHost + ":"
			+ dbPort + "/" + dbName + "?";

		return connectionUrl;
	}

	/**
	 * Sets the static lastInsertId variable for later retrieval.
	 * @param r	ResultSet containing last insert id.
	 * @throws SQLException
	 */
	private static void setLastInsertId(ResultSet r) throws SQLException
	{
		if (!r.next())
			return;

		lastInsertId = r.getLong(1);
	}
}
