package com._500bottles.object.user;

import java.util.Date;

public abstract class ApplicationUser
{
	protected long userId;
	protected Date registrationDate;
	protected Date lastLogin;
	protected String emailAddress;
	protected byte[] passwordHash;
	protected double height;
	protected double weight;
	protected Date dateOfBirth;
	protected Sex sex;
	protected String firstName;
	protected String lastName;
	protected int admin;

	public void setEmail(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public void setPassword(byte[] passwordHash)
	{
		this.passwordHash = passwordHash;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public void setDOB(Date Dob)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public void setSex(Sex sex)
	{
		this.sex = sex;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	public void setHeight(double height)
	{
		this.height = height;
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
		return emailAddress;
	}

	public byte[] getPasswordHash()
	{
		return passwordHash.clone();
	}

	public String getName()
	{
		return firstName + " " + lastName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public Sex getSex()
	{
		return sex;
	}

	public double getHeight()
	{
		return height;
	}

	public double getWeight()
	{
		return weight;
	}

	public Date getDOB()
	{
		return dateOfBirth;
	}

	public Date getRegistrationDate()
	{
		return (Date) registrationDate.clone();
	}

	public Date getLastLogin()
	{
		return (Date) lastLogin.clone();
	}

	public long getUserId()
	{
		return userId;
	}

	public int isAdmin()
	{
		return admin;
	}

	public void setAdmin(int admin)
	{
		this.admin = admin;
	}
}
