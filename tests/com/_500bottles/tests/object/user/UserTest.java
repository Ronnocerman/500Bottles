package com._500bottles.tests.object.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com._500bottles.object.user.User;

public class UserTest
{
	User user1;
	User user2;

	@Before
	public void setUp()
	{
		user1 = new User();
		user2 = new User();
		user2.setEmail("test@ucsd.edu");
		user2.setPassword("12qw!@QW".toCharArray());
		user2.setDOB(1984);
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
	public void getPasswordHash()
	{
		assertEquals(user1.getPasswordHash(), null);
		assertEquals(new String(user2.getPasswordHash()), "12qw!@QW");
	}

	@Test
	public void getDOB()
	{
		assertEquals(user1.getDOB(), 0);
		assertEquals(user2.getDOB(), 1984);
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
		user1.setPassword("qw12QW!@".toCharArray());
		assertEquals(new String(user1.getPasswordHash()), "qw12QW!@");
	}

	@Test
	public void setPasswordWithNoSpecial()
	{
		try
		{
			user1.setPassword("qw12QW".toCharArray());
			if (new String(user1.getPasswordHash()) != "qw12QW")
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
			user1.setPassword("qwQW!@".toCharArray());
			if (new String(user1.getPasswordHash()) != "qwQW!@")
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
			user1.setPassword("qw12!@".toCharArray());
			if (new String(user1.getPasswordHash()) != "qw12!@")
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
			user1.setPassword("12QW!@".toCharArray());
			if (new String(user1.getPasswordHash()) != "12QW!@")
				fail("Throw exception: No lowercase letter");
		} catch (Exception e)
		{
			fail();
		}
	}

	@Test
	public void setDOB()
	{
		user1.setDOB(1990);
		assertEquals(user1.getDOB(), 1990);
	}
}
