package com._500bottles.da.internal;

import static org.apache.commons.lang3.StringEscapeUtils.escapeXml;
import static org.apache.commons.lang3.StringEscapeUtils.unescapeXml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.user.ApplicationUser;
import com._500bottles.object.user.Sex;
import com._500bottles.object.user.User;

public class UserDAO extends DAO
{
	private final static String USER_TABLE = Config
			.getProperty("userAccountTableName");

	public static ApplicationUser addUser(ApplicationUser user)
			throws DAException
	{
		String columns, values, registrationDate, lastLogin, dateOfBirth;
		columns = "(`userEmail`, `userPass`,";
		columns += "`registrationDate`, `lastLogin`,";
		columns += "`firstName`, `lastName`, `sex`, `dateOfBirth`,";
		columns += "`height`, `weight`, `admin`)";

		registrationDate = formatDate(user.getRegistrationDate());
		lastLogin = formatDate(user.getLastLogin());
		dateOfBirth = formatDate(user.getDOB());

		values = "('" + escapeXml(user.getEmail()) + "',";
		values += "'" + escapeXml( user.getPasswordHash() ) + "',";
		values += "'" + registrationDate + "',";
		values += "'" + lastLogin + "',";
		values += "'" + escapeXml(user.getFirstName()) + "',";
		values += "'" + escapeXml(user.getLastName()) + "',";
		values += "'" + escapeXml(user.getSex().toString()) + "',";
		values += "'" + dateOfBirth + "',";
		values += "'" + user.getHeight() + "',";
		values += "'" + user.getWeight() + "',";
		values += "'" + user.isAdmin() + "')";

		// String where = "";
		// where += "userEmail=" + user.getEmail();

		// Check if useremail exists
		try
		{
			// System.out.println("same email: " + user.getEmail());
			ResultSet s = select(USER_TABLE, "*", "`userEmail`='"
					+ escapeXml(user.getEmail()) + "'");
			// System.out.println("after the select");
			// String b = r.getString("userEmail");
			// System.out.println("email received is " + b);
			// System.out.println("after the select");
			if (s.next())
			{
				throw new DAException("User with email:"
						+ unescapeXml(user.getEmail()) + " already exists");
			} else
			{
				insert(USER_TABLE, columns, values);
			}
		} catch (SQLException e1)
		{
			// e1.printStackTrace();
			// System.out.println("USER DOESN'T EXIST YET, RIGHT BEFORE INSERT");
			try
			{
				// System.out.println("does it go in here");
				insert(USER_TABLE, columns, values);
			} catch (SQLException e)
			{
				throw new DAException(e.getMessage(), e);
			}

			return user;
		}
		user.setUserId(Database.getLastInsertId());
		// System.out.println("userIdinAdd: " + user.getUserId());
		return user;
	}

	public static boolean deleteUser(long userId)
	{
		int ret;
		try
		{
			ret = delete(USER_TABLE, "userId=" + userId);
			Database.disconnect();
		} catch (SQLException e)
		{
			return false;
			// throw new DAException(e.getMessage(), e);
		}
		if (ret == 0)
			return false;
		return true;
	}

	/*
	 * public static void deleteUser(ApplicationUser user) throws DAException {
	 * // System.out.println("user: " + user.getUserId());
	 * deleteUser(user.getUserId());
	 * 
	 * }
	 */

	public static void editUser(ApplicationUser user) throws DAException
	{
		long userId = user.getUserId();
		String sql = "";

		sql += "userEmail='" + escapeXml(user.getEmail()) + "'";
		sql += ",userPass='" + escapeXml(user.getPasswordHash().toString())
				+ "'";
		sql += ",registrationDate='"
				+ DAO.formatDate(user.getRegistrationDate()) + "'";
		sql += ",lastLogin='" + DAO.formatDate(user.getLastLogin()) + "'";
		sql += ",firstName='" + escapeXml(user.getFirstName()) + "'";
		sql += ",lastName='" + escapeXml(user.getLastName()) + "'";
		sql += ",sex='" + escapeXml(user.getSex().toString()) + "'";
		sql += ",dateOfBirth='" + DAO.formatDate(user.getDOB()) + "'";
		sql += ",height=" + user.getHeight();
		sql += ",weight=" + user.getWeight();
		sql += ",admin=" + user.isAdmin();

		// System.out.println(sql);
		try
		{
			update(USER_TABLE, sql, "userId=" + userId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException("Failed User update.", e);
		}
	}

	public static ApplicationUser getUser(long userId) throws DAException
	{
		ResultSet r;
		ApplicationUser user = null;

		try
		{
			r = select(USER_TABLE, "*", "userId=" + userId);
			user = createUser(r);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException(e.getMessage(), e);
		}

		return user;
	}

	public static ApplicationUser getUserByEmail(String email)
			throws DAException
	{
		ApplicationUser user = null;
		ResultSet r;
		String where = "";
		where += "userEmail='" + unescapeXml(email) + "'";
		try
		{
			r = select(USER_TABLE, "*", where);
		} catch (SQLException e)
		{
			throw new DAException("User with email: " + email + " not found");
		}
		try
		{
			user = createUser(r);
		} catch (SQLException e)
		{
			throw new DAException(e.getMessage(), e);
		}

		return user;
	}

	private static ApplicationUser createUser(ResultSet r) throws SQLException
	{
		User user;

		long userId;

		String userEmail, firstName, lastName, sex1;

		Date registrationDate, lastLogin, dateOfBirth;

		Sex sex;

		double height, weight;

		int admin;

		// Return null if there was no entry in the ResultSet.
		if (!r.next())
			return null;

		userId = r.getLong("userId");
		dateOfBirth = r.getDate("dateOfBirth");
		userEmail = unescapeXml(r.getString("userEmail"));
		firstName = unescapeXml(r.getString("firstName"));
		lastName = unescapeXml(r.getString("lastName"));

		String userPass = unescapeXml(r.getString("userPass"));

		registrationDate = r.getDate("registrationDate");
		lastLogin = r.getDate("lastLogin");
		sex1 = unescapeXml(r.getString("Sex"));

		sex = Sex.valueOf(sex1);

		height = r.getDouble("height");
		weight = r.getDouble("weight");

		admin = r.getInt("admin");

		user = new User();
		user.setUserId(userId);
		user.setEmail(userEmail);
		user.setPassword(userPass);
		user.setRegistrationDate(registrationDate);
		user.setLastLogin(lastLogin);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setSex(sex);
		user.setDOB(dateOfBirth);
		user.setHeight(height);
		user.setWeight(weight);
		user.setAdmin(admin);

		return user;
	}
}
