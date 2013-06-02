package com._500bottles.tests.da.internal;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com._500bottles.da.internal.Database;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/18/13 Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class DatabaseTests
{

	@Test
	public void testConnection()
	{
		try
		{
			Database.connect();
			Database.disconnect();
		} catch (SQLException e)
		{
			fail("SQLException" + e.getCause());
		}
	}
}
