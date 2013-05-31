package com._500bottles.tests.object.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.user.Admin;

public class AdminTest
{
	boolean test = false;
	Admin admin1;
	Admin admin2;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp()
	{
		admin1 = new Admin();
		admin2 = new Admin();
		admin2.setEmail("test@ucsd.edu");
		admin2.setDOB(new Date(113, 4, 29));

		char[] password;
		password = "12qw!@QW".toCharArray();
		byte[] newPassword = new byte[password.length];
		for (int i = 0; i < password.length; i++)
		{
			newPassword[i] = (byte) password[i];

		}

		admin2.setPassword(newPassword);
	}

	@After
	public void tearDown()
	{
		admin1 = null;
		admin2 = null;
	}

	@Test
	public void getEmail()
	{
		assertEquals(admin1.getEmail(), null);
		assertEquals(admin2.getEmail(), "test@ucsd.edu");
	}

	@Test
	public void getPasswordHash() throws NullPointerException
	{
		assertEquals(new String(admin2.getPasswordHash()), "12qw!@QW");

		try
		{
			assertEquals(admin1.getPasswordHash(), null);
			fail();
		} catch (NullPointerException e)
		{
			if (test)
			{
				System.out.println("In getPasswordHash() " + e.getMessage());
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void getDOB()
	{
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		date = sdf.format(admin2.getDOB());
		assertEquals(admin1.getDOB(), null);
		assertEquals(date, "2013-05-29");
	}

	@Test
	public void setEmail()
	{
		admin1.setEmail("new@ucsd.edu");
		assertEquals(admin1.getEmail(), "new@ucsd.edu");
	}

	@Test
	public void setPassword()
	{
		char[] password;
		password = "qw12QW!@".toCharArray();
		byte[] newPassword = new byte[password.length];
		for (int i = 0; i < password.length; i++)
		{
			newPassword[i] = (byte) password[i];

		}

		admin1.setPassword(newPassword);
		assertEquals(new String(admin1.getPasswordHash()), "qw12QW!@");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void setDOB()
	{
		admin1.setDOB(new Date(84, 9, 18));
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		date = sdf.format(admin1.getDOB());
		assertEquals(date, "1984-10-18");
	}
	//
	// @Test
	// public void setPasswordWithNoSpecial()
	// {
	// try
	// {
	// admin1.setPassword("qw12QW".toCharArray());
	// if (new String(admin1.getPasswordHash()) != "qw12QW")
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
	// admin1.setPassword("qwQW!@".toCharArray());
	// if (new String(admin1.getPasswordHash()) != "qwQW!@")
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
	// admin1.setPassword("qw12!@".toCharArray());
	// if (new String(admin1.getPasswordHash()) != "qw12!@")
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
	// admin1.setPassword("12QW!@".toCharArray());
	// if (new String(admin1.getPasswordHash()) != "12QW!@")
	// fail("Throw exception: No lowercase letter");
	// } catch (Exception e)
	// {
	// fail();
	// }
	// }
}
