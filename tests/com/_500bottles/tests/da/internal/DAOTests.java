package com._500bottles.tests.da.internal;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import com._500bottles.da.internal.WinebookDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/18/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class DAOTests {

	@Test
	public void testDate()
	{

		Date d = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String df = sdf.format(d);

		System.out.println(df);

	}

	@Test
	public void getLastAutoIncrementId()
	{

	}

}
