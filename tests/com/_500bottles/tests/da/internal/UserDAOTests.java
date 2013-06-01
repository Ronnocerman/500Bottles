package com._500bottles.tests.da.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.internal.UserDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.user.ApplicationUser;
import com._500bottles.object.user.Sex;
import com._500bottles.object.user.User;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/18/13 Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class UserDAOTests
{
	boolean test = false;
	User nullUser;

	@SuppressWarnings("deprecation")
	@Test
	public void addUser() throws DAException
	{
		User user = new User();
		Date date = new Date(90, 11, 25);
		Date regDate = new Date();
		Date lastLogin = new Date();
		char[] pw = new String("password").toCharArray();

		byte[] password = new byte[pw.length];
		for (int i = 0; i < pw.length; i++)
		{
			password[i] = (byte) pw[i];

		}

		user.setRegistrationDate(regDate);
		user.setLastLogin(lastLogin);
		user.setDOB(date);
		user.setPassword(password);
		user.setEmail("elisa@gmail.com");
		user.setFirstName("Rose");
		user.setLastName("Shaw");
		user.setSex(Sex.female);
		user.setHeight(52);
		user.setWeight(110);
		user.setAdmin(0);

		try
		{
			UserDAO.addUser(user);

		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void addUserWithSameEmail() throws DAException
	{
		User user = new User();

		user.setEmail("elisa@gmail.com");

		try
		{
			UserDAO.addUser(user);
			fail();

		} catch (DAException e)
		{
			if (test)
				fail(e.getMessage());
		}
	}

	@Test
	public void addNullUser() throws NullPointerException, DAException
	{
		try
		{
			UserDAO.addUser(nullUser);
		} catch (NullPointerException e)
		{
			if (test)
				fail(e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void deleteUser() throws DAException
	{
		ApplicationUser user = new User();
		Date date = new Date(90, 11, 25);
		Date regDate = new Date();
		Date lastLogin = new Date();
		char[] password0 = new String("password").toCharArray();

		byte[] password = new byte[password0.length];
		for (int i = 0; i < password0.length; i++)
		{
			password[i] = (byte) password0[i];
		}

		user.setRegistrationDate(regDate);
		user.setLastLogin(lastLogin);
		user.setDOB(date);
		user.setPassword(password);
		user.setEmail("fjdksal@gmail.com");
		user.setFirstName("Chris");
		user.setLastName("THIS SHIT BETTER NOT BE IN THE DB");
		user.setSex(Sex.female);
		user.setHeight(52);
		user.setWeight(110);
		user.setAdmin(0);

		try
		{
			user = UserDAO.addUser(user);
			assertTrue(UserDAO.deleteUser(user.getUserId()));
		} catch (DAException e)
		{
			if (test)
				fail(e.getMessage());
		}
	}

	@Test
	public void deleteNullUser() throws NullPointerException, DAException
	{
		try
		{
			UserDAO.deleteUser(nullUser.getUserId());
		} catch (NullPointerException e)
		{
			if (test)
			{
				fail(e.getMessage());
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void editUser() throws DAException
	{
		ApplicationUser getUser;

		User user = new User();
		Date date = new Date(84, 10, 18);
		Date regDate = new Date();
		Date lastLogin = new Date(112, 1, 1);
		char[] pw = new String("12qw!@QW").toCharArray();

		byte[] password = new byte[pw.length];
		for (int i = 0; i < pw.length; i++)
		{
			password[i] = (byte) pw[i];

		}

		user.setRegistrationDate(regDate);
		user.setLastLogin(lastLogin);
		user.setDOB(date);
		user.setPassword(password);
		user.setEmail("ysc001@ucsd.edu");
		user.setFirstName("Yun Sung");
		user.setLastName("Choi");
		user.setSex(Sex.male);
		user.setHeight(55);
		user.setWeight(140);
		user.setAdmin(0);

		try
		{
			UserDAO.addUser(user);
			getUser = UserDAO.getUser(user.getUserId());

			char[] pwd = new String("new12QW!@").toCharArray();

			byte[] newPassword = new byte[pw.length];
			for (int i = 0; i < pwd.length; i++)
			{
				newPassword[i] = (byte) pw[i];

			}

			getUser.setLastLogin(new Date());
			getUser.setDOB(new Date(92, 9, 2));
			getUser.setPassword(newPassword);
			getUser.setEmail("new@ucsd.edu");
			getUser.setFirstName("Y");
			getUser.setLastName("C");
			getUser.setSex(Sex.female);
			getUser.setHeight(72);
			getUser.setWeight(200);

			UserDAO.editUser(getUser);

			getUser = UserDAO.getUser(getUser.getUserId());
			assertEquals(getUser.getEmail(), "new@ucsd.edu");

		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void editNullUser() throws NullPointerException, DAException
	{
		try
		{
			UserDAO.editUser(nullUser);
		} catch (NullPointerException e)
		{
			if (test)
			{
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void editUserWithIdZero() throws DAException
	{
		ApplicationUser user0 = new User();
		user0.setUserId(0);

		try
		{
			UserDAO.editUser(user0);
			fail();
		} catch (DAException e)
		{
			if (test)
			{
				fail(e.getMessage());
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getUserById() throws DAException
	{
		ApplicationUser getUser;

		User user = new User();
		Date date = new Date(60, 1, 8);
		Date regDate = new Date();
		Date lastLogin = new Date(113, 3, 3);
		char[] pw = new String("12qw!@QW123").toCharArray();

		byte[] password = new byte[pw.length];
		for (int i = 0; i < pw.length; i++)
		{
			password[i] = (byte) pw[i];

		}

		user.setRegistrationDate(regDate);
		user.setLastLogin(lastLogin);
		user.setDOB(date);
		user.setPassword(password);
		user.setEmail("old@ucsd.edu");
		user.setFirstName("Some");
		user.setLastName("Body");
		user.setSex(Sex.male);
		user.setHeight(65);
		user.setWeight(100);
		user.setAdmin(0);

		try
		{
			UserDAO.addUser(user);
			getUser = UserDAO.getUser(user.getUserId());

			assertEquals(getUser.getEmail(), "old@ucsd.edu");

		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getUserByIdWithNullUser() throws NullPointerException,
			DAException
	{
		try
		{
			UserDAO.getUser(nullUser.getUserId());
		} catch (NullPointerException e)
		{
			if (test)
			{
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getUserByIdWithIdZero() throws DAException
	{
		ApplicationUser user0 = new User();
		user0.setUserId(0);

		try
		{
			UserDAO.getUser(user0.getUserId());
			fail();
		} catch (DAException e)
		{
			if (test)
			{
				fail(e.getMessage());
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getUserByEmail() throws DAException
	{
		ApplicationUser user = new User();
		Date date = new Date(95, 11, 25);
		Date regDate = new Date();
		Date lastLogin = new Date();
		char[] pw = new String("password95").toCharArray();

		byte[] password = new byte[pw.length];
		for (int i = 0; i < pw.length; i++)
		{
			password[i] = (byte) pw[i];
		}

		user.setRegistrationDate(regDate);
		user.setLastLogin(lastLogin);
		user.setDOB(date);
		user.setPassword(password);
		user.setEmail("jess@gmail.com");
		user.setFirstName("Jane");
		user.setLastName("D");

		Sex s = Sex.female;

		user.setSex(s);
		user.setHeight(45);
		user.setWeight(90);
		user.setAdmin(0);

		try
		{
			UserDAO.addUser(user);

			ApplicationUser getUser = UserDAO.getUserByEmail(user.getEmail());

			assertEquals(getUser.getFirstName(), "Jane");

		} catch (DAException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void getUserByEmailWithNullUser() throws NullPointerException,
			DAException
	{
		try
		{
			UserDAO.getUserByEmail(nullUser.getEmail());
		} catch (NullPointerException e)
		{
			if (test)
			{
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getUserByEmailWithIdZero() throws DAException
	{
		ApplicationUser user0 = new User();
		user0.setUserId(0);

		try
		{
			UserDAO.getUserByEmail(user0.getEmail());
			fail();
		} catch (DAException e)
		{
			if (test)
			{
				fail(e.getMessage());
			}
		}
	}
}