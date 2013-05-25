package com._500bottles.tests.da.internal;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import com._500bottles.da.internal.Database;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/18/13
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class DatabaseTests {

	@Test
	public void testConnection()
	{
		try {
			Connection c = Database.connect();
			Database.disconnect(c);
		} catch (SQLException e) {
			fail("SQLException" + e.getCause());
		}
	}
}
