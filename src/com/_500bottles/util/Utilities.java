package com._500bottles.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 6/1/13
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utilities {

	/**
	 * Hashes the given password and returns the result.
	 * @param in
	 * @return The password's hash.
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String hashPassword(String in) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		String salt = "$N5J";
		String pepper = "%35";

		in += pepper;

		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(in.getBytes(), 0, in.length());

		return salt + new BigInteger(1, m.digest()).toString(16);
	}
}
