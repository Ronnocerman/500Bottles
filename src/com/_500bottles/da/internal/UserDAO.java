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

/**
 * Coordinates all User related database access for user adding, deleting, and
 * getting in the database.
 */
public class UserDAO extends DAO
{
	private final static String USER_TABLE = Config
			.getProperty("userAccountTableName");

	/**
	 * Adds the user to the table
	 * 
	 * @param user
	 *            The user to be added to the user table
	 * @return the user which was added
	 * @throws DAException
	 */
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
		values += "'" + escapeXml(user.getPasswordHash()) + "',";
		values += "'" + registrationDate + "',";
		values += "'" + lastLogin + "',";
		values += "'" + escapeXml(user.getFirstName()) + "',";
		values += "'" + escapeXml(user.getLastName()) + "',";
		values += "'" + escapeXml(user.getSex().toString()) + "',";
		values += "'" + dateOfBirth + "',";
		values += "'" + user.getHeight() + "',";
		values += "'" + user.getWeight() + "',";
		values += "'" + user.isAdmin() + "')";

		// Check if useremail exists
		try
		{
			ResultSet s = select(USER_TABLE, "*", "`userEmail`='"
					+ escapeXml(user.getEmail()) + "'");

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
		return user;
	}

	/**
	 * Deletes the user from the table.
	 * 
	 * @param long userId
	 * @return true if item was deleted, false otherwise.
	 */
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

	/**
	 * Edits the user specified
	 * 
	 * @param user
	 *            : the user to edit
	 * 
	 * @return void
	 * @throws DAException
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

	/**
	 * Gets the ApplicationUser object based on the userId
	 * 
	 * @param userId
	 * 
	 * @return the ApplicationUser object of the userId passed in
	 * @throws DAException
	 */
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

	/**
	 * Gets the ApplicationUser object based on the email
	 * 
	 * @param email
	 *            (String)
	 * 
	 * @return the ApplicationUser object of the email string passed in
	 * @throws DAException
	 */
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

	/**
	 * Creates the ApplicationUser object from a result set. This method is
	 * called from the 2 get methods
	 * 
	 * @param r
	 *            result set to construct the ApplicationUser object from
	 * @return ApplicationUser object
	 * @throws SQLException
	 */
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
