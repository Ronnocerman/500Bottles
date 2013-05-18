package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO
{
	public int insert(String db) throws SQLException, ClassNotFoundException
	{
		String ins = "insert into " + db + " values (default)";
		return Database.modQuery(ins);
	}

	public int delete(String db, String col, String val)
			throws ClassNotFoundException, SQLException
	{
		String del = "delete from " + db + " where " + col + "=" + val;
		return Database.modQuery(del);
	}

	public int update(String db, String newCol, String newVal, String key,
			String val) throws ClassNotFoundException, SQLException
	{
		String update = "update " + db + " set " + newCol + "=" + newVal
				+ " where " + key + "=" + val;
		return Database.modQuery(update);
	}

	public ResultSet select(String col, String db)
			throws ClassNotFoundException, SQLException
	{
		String sel = "select " + col + " from " + db;
		return Database.readQuery(sel);
	}
}
