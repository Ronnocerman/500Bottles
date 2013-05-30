package com._500bottles.tests.da.internal;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.internal.UserDAO;
import com._500bottles.exception.da.DAException;
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
		System.out.println("supsup");
		Date date = new Date(90, 11, 25);
		Date regDate = new Date();
		Date lastLogin = new Date();
		char[] password = new String("password").toCharArray();

		user.setRegistrationDate(regDate);
		user.setLastLogin(lastLogin);
		user.setDOB(date);
		user.setPassword(password);
		user.setEmail("rosexshaw@gmail.com");
		user.setFirstName("Rose");
		user.setHeight(52);
		System.out.println("hay");
		try
		{
			UserDAO.addUser(user);
		} catch (DAException e)
		{
			e.printStackTrace();
			fail();
		}

	}

}