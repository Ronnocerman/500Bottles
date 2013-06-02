package com._500bottles.object.user;

import java.util.Date;

public abstract class ApplicationUser
{
	protected long userId;
	protected Date registrationDate;
	protected Date lastLogin;
	protected String emailAddress;
	protected String passwordHash;
	protected double height;
	protected double weight;
	protected Date dateOfBirth;
	protected Sex sex;
	protected String firstName;
	protected String lastName;
	protected int admin;

	public ApplicationUser setEmail(String emailAddress)
	{
		this.emailAddress = emailAddress;
		return this;
	}

	public ApplicationUser setPassword(String passwordHash)
	{
		this.passwordHash = passwordHash;
		return this;
	}

	public ApplicationUser setFirstName(String firstName)
	{
		this.firstName = firstName;
		return this;
	}

	public ApplicationUser setLastName(String lastName)
	{
		this.lastName = lastName;
		return this;
	}

	public ApplicationUser setDOB(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	public ApplicationUser setSex(Sex sex)
	{
		this.sex = sex;
		return this;
	}

	public ApplicationUser setWeight(double weight)
	{
		this.weight = weight;
		return this;
	}

	public ApplicationUser setHeight(double height)
	{
		this.height = height;
		return this;
	}

	public ApplicationUser setRegistrationDate(Date registrationDate)
	{
		this.registrationDate = registrationDate;
		return this;
	}

	public ApplicationUser setLastLogin(Date lastLogin)
	{
		this.lastLogin = lastLogin;
		return this;
	}

	public ApplicationUser setUserId(long userId)
	{
		this.userId = userId;
		return this;
	}

	public String getEmail()
	{
		return emailAddress;
	}

	public String getPasswordHash()
	{
		return passwordHash;
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

	public ApplicationUser setAdmin(int admin)
	{
		this.admin = admin;
		return this;
	}
}
