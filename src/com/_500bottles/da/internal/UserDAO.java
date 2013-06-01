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
		values += "'" + escapeXml(user.getPasswordHash().toString()) + "',";
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

	public static void deleteUser(long userId) throws DAException
	{
		try
		{
			delete(USER_TABLE, "userId=" + userId);
			Database.disconnect();
		} catch (SQLException e)
		{
			throw new DAException(e.getMessage(), e);
		}
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

		System.out.println(sql);
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
			// System.out.println("AYYYYYYYY");
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

		char[] userPass0;

		Date registrationDate, lastLogin, dateOfBirth;

		Sex sex;

		double height, weight;

		int admin;
		boolean real = r.next();
		System.out.println(real);
		// Return null if there was no entry in the ResultSet.
		if (!real)
			return null;

		userId = r.getLong("userId");
		dateOfBirth = r.getDate("dateOfBirth");
		// System.out.println(dateOfBirth);
		// System.out.println("got userId");

		// System.out.println("WHAT AdsafdsafdsafsadBOUT ffdsaHERE?");
		userEmail = unescapeXml(r.getString("userEmail"));
		firstName = unescapeXml(r.getString("firstName"));
		lastName = unescapeXml(r.getString("lastName"));
		// System.out.println("eheheh");
		userPass0 = unescapeXml(r.getString("userPass")).toCharArray();
		byte[] userPass = new byte[userPass0.length];
		for (int i = 0; i < userPass0.length; i++)
		{
			userPass[i] = (byte) userPass0[i];

		}
		// System.out.println("after password");

		registrationDate = r.getDate("registrationDate");
		lastLogin = r.getDate("lastLogin");
		// System.out.println("right before sex?");
		sex1 = unescapeXml(r.getString("Sex"));
		// System.out.println("right after unescape sex");
		sex = Sex.valueOf(sex1);
		// System.out.println("WHAT ABOUT fdsaHERE?");
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
		// System.out.println("dOB = " + user.getDOB());
		user.setHeight(height);
		user.setWeight(weight);
		user.setAdmin(admin);
		// System.out.println("WHAT ABOUT HERE?");
		return user;
	}
}
