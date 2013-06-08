package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;
import static org.apache.commons.lang3.StringEscapeUtils.unescapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;

public class UploadDAO extends DAO
{
	private final static String PHOTOS_TABLE = Config
			.getProperty("photosTableName");

	public static long addPhoto(String photo) throws DAException
	{
		String columns, values;
		columns = "(`photoURI`)";

		values = "('" + escapeXml(photo) + "')";

		try
		{
			insert(PHOTOS_TABLE, columns, values);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed Photo insertion", e);
		}

		return DAO.getLastInsertId();
	}

	public static boolean deletePhoto(String photo)
	{
		int r;
		String where = "photoURI = '" + escapeXml(photo) + "'";
		try
		{
			r = delete(PHOTOS_TABLE, where);
		} catch (SQLException e)
		{
			return false;
		}
		if (r == 0)
			return false;
		return true;
	}

	public static String getPhoto(long uploadId) throws DAException
	{
		ResultSet r;
		String ret;
		try
		{
			// System.out.println(wineId);
			r = select(PHOTOS_TABLE, "*", "uploadId=" + uploadId);
			// System.out.println("after the select in getWine");

		} catch (SQLException e)
		{
			throw new DAException("SQL select exception.", e.getCause());
		}
		try
		{
			ret = unescapeXml(r.getString("photoURI"));
		} catch (SQLException e)
		{
			throw new DAException("URI not received.");
		}
		return ret;
	}

	public static boolean photoExists(String photo)
	{
		ResultSet r;
		String where = "photoURI = '" + escapeXml(photo) + "'";
		try
		{
			r = select(PHOTOS_TABLE, "*", where);
			if (!r.next())
				return false;
		} catch (SQLException e)
		{
			return false;
		}

		return true;
	}

}
