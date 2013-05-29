package com._500bottles.object.user;

import java.util.Date;

public abstract class ApplicationUser
{
	protected long userId;
	protected Date registrationDate;
	protected Date lastLogin;
	protected String emailAddress;
	protected char[] password;
	protected double height;
	protected double weight;
	protected Date dateOfBirth;
	protected Sex sex;
	protected String firstName;
	protected String lastName;
	protected boolean admin;

	public void setEmail(String email)
	{
		// TODO Auto-generated method stub
		emailAddress = email;

	}

	public void setPassword(char[] pass)
	{
		// TODO Auto-generated method stub
		password = pass;
	}

	public void setFirstName(String first)
	{
		// TODO Auto-generated method stub
		firstName = first;
	}

	public void setLastName(String last)
	{
		// TODO Auto-generated method stub
		lastName = last;
	}

	public void setDOB(Date Dob)
	{
		// TODO Auto-generated method stub
		dateOfBirth = Dob;
	}

	public void setSex(Sex s)
	{
		// TODO Auto-generated method stub
		sex = s;
	}

	public void setWeight(double fat)
	{
		// TODO Auto-generated method stub
		weight = fat;
	}

	public void setHeight(double tall)
	{
		// TODO Auto-generated method stub
		height = tall;
	}

	public void setRegistrationDate(Date registrationDate)
	{
		this.registrationDate = registrationDate;
	}

	public void setLastLogin(Date lastLogin)
	{
		this.lastLogin = lastLogin;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public String getEmail()
	{
		// TODO Auto-generated method stub
		return emailAddress;
	}

	public char[] getPasswordHash()
	{
		// TODO Auto-generated method stub
		return password;
	}

	public String getName()
	{
		// TODO Auto-generated method stub
		return firstName + " " + lastName;
	}

	public String getFirstName()
	{
		// TODO Auto-generated method stub
		return firstName;
	}

	public String getLastName()
	{
		// TODO Auto-generated method stub
		return lastName;
	}

	public Sex getSex()
	{
		// TODO Auto-generated method stub
		return sex;
	}

	public double getHeight()
	{
		// TODO Auto-generated method stub
		return height;
	}

	public double getWeight()
	{
		// TODO Auto-generated method stub
		return weight;
	}

	public Date getDOB()
	{
		// TODO Auto-generated method stub
		return dateOfBirth;
	}

	public Date getRegistrationDate()
	{
		return registrationDate;
	}

	public Date getLastLogin()
	{
		return lastLogin;
	}

	public long getUserId()
	{
		return userId;
	}

	public boolean isAdmin()
	{
		return admin;
	}

	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}
}
