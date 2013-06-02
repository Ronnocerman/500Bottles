package com._500bottles.tests.util;

import com._500bottles.util.Utilities;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UtilitiesTests {

	@Test
	public void passwordHash()
	{
		String password = "12345";
		String hashedPassword;

		try {
			System.err.println("Hashing Password");
			hashedPassword = Utilities.hashPassword(password);

			System.err.println(hashedPassword);

			assertEquals(hashedPassword, Utilities.hashPassword(password));
		} catch (Exception e) {

		}
	}

}
