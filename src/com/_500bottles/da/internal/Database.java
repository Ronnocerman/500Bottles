package com._500bottles.da.internal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database
{
	private static String dbHost = "jdbc:mysql://localhost/database?";
	private static String dbUser = "root";
	private static String dbPass = "admin";
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	public static ResultSet readQuery(String q) throws SQLException,
			ClassNotFoundException
	{
		try
		{
			Connection conn = connect();
			preparedStatement = conn.prepareStatement(q);
			resultSet = preparedStatement.executeQuery(q);
			disconnect(conn);
			return resultSet;
		} catch (SQLException e)
		{
			throw e;
		}
	}

	public static int modQuery(String q) throws SQLException,
			ClassNotFoundException
	{
		int i;
		try
		{
			Connection conn = connect();
			preparedStatement = conn.prepareStatement(q);
			i = preparedStatement.executeUpdate(q);
			disconnect(conn);
			return i;
		} catch (SQLException e)
		{
			throw e;
		}
	}

	public static Connection connect() throws ClassNotFoundException,
			SQLException
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection(dbHost, dbUser,
					dbPass);
			return connect;
		} catch (SQLException e)
		{
			throw e;
		} catch (ClassNotFoundException e)
		{
			throw e;
		}
	}

	public static void disconnect(Connection conn) throws SQLException
	{
		try
		{
			if (preparedStatement != null)
			{
				preparedStatement.close();
			}
			if (conn != null)
			{
				conn.close();
			}
		} catch (SQLException e)
		{
			throw e;
		}
	}

}
