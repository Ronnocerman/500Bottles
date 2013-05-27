package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.user.ApplicationUser;
import com._500bottles.object.user.Sex;
import com._500bottles.object.user.User;

public class UserDAO
{
	private final static String USER_TABLE = Config
			.getProperty("UserAccountTableName");

	public static ApplicationUser addUser(ApplicationUser user)
			throws DAException
	{
		// TODO instanceof ApplicationUser to check if User or Admin
		// TODO Check if user exists, then throw a DAException with a proper
		// message, if so.
		String columns, values, registrationDate, lastLogin;
		columns = "(`userEmail`, `userPass`,";
		columns += "`registrationDate`, `lastLogin`,";
		columns += "`firstName`, `lastName`, `sex`, `dateOfBirth`";
		columns += "`height`, `weight`)";

		registrationDate = DAO.formatDate(user.getRegistrationDate());
		lastLogin = DAO.formatDate(user.getLastLogin());

		values = "('" + user.getEmail() + "',";
		values += "'" + user.getPasswordHash().toString() + "',";
		values += "'" + registrationDate + "',";
		values += "'" + lastLogin + "',";
		values += "'" + user.getFirstName() + "',";
		values += "'" + user.getLastName() + "',";
		values += "'" + user.getSex() + "')";
		values += "'" + user.getDOB() + "',";
		values += "'" + user.getHeight() + "',";
		values += "'" + user.getWeight() + "',";

		// Check if user exists

		try
		{
			DAO.insert(USER_TABLE, columns, values);
		} catch (SQLException e)
		{
			throw new DAException(e.getMessage(), e);
		}

		user.setUserId(Database.getLastInsertId());

		return user;
	}

	public static void deleteUser(ApplicationUser user) throws DAException
	{
		try
		{
			DAO.delete(USER_TABLE, "WHERE userId=" + user.getUserId());
		} catch (SQLException e)
		{
			throw new DAException(e.getMessage(), e);
		}
	}

	public static void editUser(ApplicationUser user) throws DAException
	{
		long userId = user.getUserId();
		String sql = "";

		sql += "userEmail=" + user.getEmail();
		sql += ",userPass=" + user.getPasswordHash().toString();
		sql += ",registrationDate="
				+ DAO.formatDate(user.getRegistrationDate());
		sql += ",lastLogin=" + DAO.formatDate(user.getLastLogin());
		sql += ",firstName=" + user.getFirstName();
		sql += ",lastName=" + user.getLastName();
		sql += ",sex=" + user.getSex().toString();
		sql += ",dateOfBirth=" + user.getDOB();
		sql += ",height=" + user.getHeight();
		sql += ",weight=" + user.getWeight();

		try
		{
			DAO.update(USER_TABLE, sql, "userId=" + userId);
		} catch (SQLException e)
		{
			throw new DAException("User not found", e);
		}
	}

	public static ApplicationUser getUser(long userId) throws DAException
	{
		ResultSet r;
		ApplicationUser user = null;
		try
		{
			r = DAO.select(USER_TABLE, "*");
		} catch (SQLException e)
		{
			throw new DAException(e.getMessage(), e);
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

	// TODO:Implement this
	public static ApplicationUser getUserByEmail(String email)
	{
		ApplicationUser user = null;
		return user;
	}

	private static ApplicationUser createUser(ResultSet r) throws SQLException
	{
		User user;

		long userId, dateOfBirth;

		String userEmail, firstName, lastName, sex1;

		char[] userPass;

		Date registrationDate, lastLogin;

		Sex sex;

		double height, weight;

		// Return null if there was no entry in the ResultSet.
		if (!r.next())
			return null;

		userId = r.getLong("userId");
		dateOfBirth = r.getLong("dateOfBirth");

		userEmail = r.getString("userEmail");
		firstName = r.getString("firstName");
		lastName = r.getString("lastName");

		userPass = r.getString("userPass").toCharArray();

		registrationDate = r.getDate("registrationDate");
		lastLogin = r.getDate("lastLogin");

		sex1 = r.getString("Sex");
		sex = Sex.valueOf(sex1);

		height = r.getDouble("height");
		weight = r.getDouble("weight");

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

		return user;
	}
}
