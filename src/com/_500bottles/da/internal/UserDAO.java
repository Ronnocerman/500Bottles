package com._500bottles.da.internal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com._500bottles.config.Config;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.user.Sex;
import com._500bottles.object.user.User;

public class UserDAO
{
	private final static String USER_TABLE = Config
			.getProperty("UserAccountTableName");

	public static User addUser(User user) throws Exception
	{
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
		values += "'" + user.getDOB() + "',";
		values += "'" + user.getHeight() + "',";
		values += "'" + user.getWeight() + "',";
		values += "'" + user.getSex() + "')";

		try
		{
			int i = DAO.insert(USER_TABLE, columns, values);
			// TODO: Better exception handling.
		} catch (Exception e)
		{
			throw e;
		}

		user.setUserId(Database.getLastInsertId());

		return user;
	}

	public static void deleteUser(User user) throws SQLException
	{
		DAO.delete(USER_TABLE, "WHERE userId=" + user.getUserId());
	}

	public static void editUser(User user) throws SQLException
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

		DAO.update(USER_TABLE, sql, "userId=" + userId);
	}

	public static User getUser(long userId) throws DAException
	{
		ResultSet r;
		User user = null;

		try
		{
			r = DAO.select(USER_TABLE, "*");
			user = createUser(r);

		} catch (Exception e)
		{
			// TODO: handle query exceptions.
		}

		return user;

	}

	private static User createUser(ResultSet r) throws SQLException
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
