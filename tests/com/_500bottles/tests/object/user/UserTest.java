package com._500bottles.tests.object.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.user.User;

public class UserTest
{
	boolean test = false;
	User user1;
	User user2;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp()
	{
		user1 = new User();
		user2 = new User();
		user2.setEmail("test@ucsd.edu");
		user2.setDOB(new Date(84, 10 - 1, 18));

		String newPassword = "12qw!@QW";

		user2.setPassword(newPassword);
	}

	@After
	public void tearDown()
	{
		user1 = null;
		user2 = null;
	}

	@Test
	public void getEmail()
	{
		assertEquals(user1.getEmail(), null);
		assertEquals(user2.getEmail(), "test@ucsd.edu");
	}

	@Test
	public void getPasswordHash() throws NullPointerException
	{
		assertEquals(new String(user2.getPasswordHash()), "12qw!@QW");

		try
		{
			user1.getPasswordHash();
			fail();
		} catch (NullPointerException e)
		{
			if (test)
			{
				System.out.println("In getPasswordHash(): " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getDOB()
	{
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		date = sdf.format(user2.getDOB());
		assertEquals(user1.getDOB(), null);
		assertEquals(date, "1984-10-18");
	}

	@Test
	public void setEmail()
	{
		user1.setEmail("new@ucsd.edu");
		assertEquals(user1.getEmail(), "new@ucsd.edu");
	}

	@Test
	public void setPassword()
	{
		char[] password;
		String newPassword = "qw12QW!@";

		user1.setPassword(newPassword);
		assertEquals(new String(user1.getPasswordHash()), "qw12QW!@");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void setDOB()
	{
		user1.setDOB(new Date(93, 10 - 1, 18));
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date = sdf.format(user1.getDOB());

		assertEquals(date, "1993-10-18");
	}

	// @Test
	// public void setPasswordWithNoSpecial()
	// {
	// try
	// {
	// user1.setPassword("qw12QW".toCharArray());
	// if (new String(user1.getPasswordHash()) != "qw12QW")
	// fail("Throw exception: No special character");
	// } catch (Exception e)
	// {
	// fail();
	// }
	// }
	//
	// @Test
	// public void setPasswordWithNoNum()
	// {
	// try
	// {
	// user1.setPassword("qwQW!@".toCharArray());
	// if (new String(user1.getPasswordHash()) != "qwQW!@")
	// fail("Throw exception: No number");
	// } catch (Exception e)
	// {
	// fail();
	// }
	// }
	//
	// @Test
	// public void setPasswordWithNoUpper()
	// {
	// try
	// {
	// user1.setPassword("qw12!@".toCharArray());
	// if (new String(user1.getPasswordHash()) != "qw12!@")
	// fail("Throw exception: No uppercase letter");
	// } catch (Exception e)
	// {
	// fail();
	// }
	// }
	//
	// @Test
	// public void setPasswordWithNoLower()
	// {
	// try
	// {
	// user1.setPassword("12QW!@".toCharArray());
	// if (new String(user1.getPasswordHash()) != "12QW!@")
	// fail("Throw exception: No lowercase letter");
	// } catch (Exception e)
	// {
	// fail();
	// }
	// }
}
