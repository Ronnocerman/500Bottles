package com._500bottles.tests.da.internal;

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
	@Test
	public void testInsertUser()
	{
		User user = new User();
		// System.out.println("supsup");
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

		Sex s = Sex.female;

		user.setSex(s);
		user.setHeight(52);
		user.setWeight(110);
		user.setAdmin(0);

		// System.out.println("hay");
		try
		{
			UserDAO.addUser(user);

		} catch (DAException e)
		{
			// e.printStackTrace();
			// fail();
		}

	}

	@Test
	public void testDeleteUser()
	{
		ApplicationUser user = new User(); //
		// System.out.println("supsup");
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

		Sex s = Sex.female;

		user.setSex(s);
		user.setHeight(52);
		user.setWeight(110);
		user.setAdmin(0);

		// System.out.println("hay");
		try
		{
			// System.out.println("NOW ADDING USER");
			user = UserDAO.addUser(user);
		} catch (DAException e)
		{
			e.printStackTrace();
			fail();
		}

		// System.out.println("userFIRST: " + user.getUserId());

		try
		{
			UserDAO.deleteUser(user);
		} catch (DAException e)
		{
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void testgetDeleteUser()
	{
		// ApplicationUser u1 = null;
		// ApplicationUser u2 = null;
		// ApplicationUser u3 = null;

		try
		{
			UserDAO.deleteUser(30);
			UserDAO.deleteUser(31);
			UserDAO.deleteUser(32);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			UserDAO.deleteUser(27);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testEditUser()
	{
		ApplicationUser user = null;

		try
		{
			user = UserDAO.getUser(82);
		} catch (DAException e)
		{
			fail();
		}

		// System.out.println("does it go here?");
		try
		{
			Sex sex = Sex.male;
			user.setSex(sex);
			// System.out.println("THE DATE 1: " + user.getDOB());
			user.setLastName("Ngo");
			Date date = new Date();
			user.setLastLogin(date);
			// System.out.println("THE DATE 2: " + user.getDOB());
		} catch (Exception e)
		{
			fail();
		}
		// System.out.println("THE DATE: 3 " + user.getDOB());
		try
		{
			UserDAO.editUser(user);
			// System.out.println("THE DATE 4: " + user.getDOB());
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO: confirm that the user is actually edited by getting user back
		// and checking with
		// tests

	}

	@Test
	public void testgetUserbyEmail()
	{

		ApplicationUser user = new User();
		// System.out.println("supsup");
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
		user.setEmail("jess@gmail.com");
		user.setFirstName("Rose");
		user.setLastName("Shaw");

		Sex s = Sex.female;

		user.setSex(s);
		user.setHeight(52);
		user.setWeight(110);
		user.setAdmin(0);

		// System.out.println("hay");
		try
		{
			user = UserDAO.addUser(user);

		} catch (DAException e)
		{
			// e.printStackTrace();
			// fail();
		}

		String sexy = new String("jess@gmail.com");
		ApplicationUser user2 = null;
		try
		{
			user2 = UserDAO.getUserByEmail(sexy);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user2.getUserId() != user.getUserId())
		{
			fail();
		}

	}

}