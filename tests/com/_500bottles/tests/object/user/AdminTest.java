package com._500bottles.tests.object.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.user.Admin;

public class AdminTest
{
	Admin admin1;
	Admin admin2;

	@Before
	public void setUp()
	{
		admin1 = new Admin();
		admin2 = new Admin();
		admin2.setEmail("test@ucsd.edu");
		admin2.setPassword("12qw!@QW".toCharArray());
		admin2.setDOB(1984);
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
	public void getPasswordHash()
	{
		assertEquals(admin1.getPasswordHash(), null);
		assertEquals(new String(admin2.getPasswordHash()), "12qw!@QW");
	}

	@Test
	public void getDOB()
	{
		assertEquals(admin1.getDOB(), 0);
		assertEquals(admin2.getDOB(), 1984);
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
		admin1.setPassword("qw12QW!@".toCharArray());
		assertEquals(new String(admin1.getPasswordHash()), "qw12QW!@");
	}

	@Test
	public void setPasswordWithNoSpecial()
	{
		try
		{
			admin1.setPassword("qw12QW".toCharArray());
			if (new String(admin1.getPasswordHash()) != "qw12QW")
				fail("Throw exception: No special character");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setPasswordWithNoNum()
	{
		try
		{
			admin1.setPassword("qwQW!@".toCharArray());
			if (new String(admin1.getPasswordHash()) != "qwQW!@")
				fail("Throw exception: No number");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setPasswordWithNoUpper()
	{
		try
		{
			admin1.setPassword("qw12!@".toCharArray());
			if (new String(admin1.getPasswordHash()) != "qw12!@")
				fail("Throw exception: No uppercase letter");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setPasswordWithNoLower()
	{
		try
		{
			admin1.setPassword("12QW!@".toCharArray());
			if (new String(admin1.getPasswordHash()) != "12QW!@")
				fail("Throw exception: No lowercase letter");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setDOB()
	{
		admin1.setDOB(1990);
		assertEquals(admin1.getDOB(), 1990);
	}
}
