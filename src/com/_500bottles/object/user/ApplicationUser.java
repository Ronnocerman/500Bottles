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

	/**
	 * Sets this user's email address and returns the new user
	 * 
	 * @param emailAddress
	 *            The user's email
	 * 
	 * @return The modified user
	 */
	public ApplicationUser setEmail(String emailAddress)
	{
		this.emailAddress = emailAddress;
		return this;
	}

	/**
	 * Sets this user's password and returns the new user
	 * 
	 * @param passwordHash
	 *            The user's hashed password
	 * 
	 * @return The modified user
	 */
	public ApplicationUser setPassword(String passwordHash)
	{
		this.passwordHash = passwordHash;
		return this;
	}

	/**
	 * Sets this user's first name and returns the new user
	 * 
	 * @param firstName
	 *            The first name to be set
	 * 
	 * @return The modified user
	 */
	public ApplicationUser setFirstName(String firstName)
	{
		this.firstName = firstName;
		return this;
	}

	/**
	 * Sets this user's last name and returns the new user
	 * 
	 * @param lastName
	 *            The last name to be set
	 * 
	 * @return The modified user
	 */
	public ApplicationUser setLastName(String lastName)
	{
		this.lastName = lastName;
		return this;
	}

	/**
	 * Sets the user's date of birth and returns the new user
	 * 
	 * @param dateOfBirth
	 *            The user's date of birth
	 * 
	 * @return The modified user
	 */
	public ApplicationUser setDOB(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	/**
	 * Sets the user's sex and returns the new user
	 * 
	 * @param sex
	 *            The user's sex
	 * 
	 * @return The modified user
	 */
	public ApplicationUser setSex(Sex sex)
	{
		this.sex = sex;
		return this;
	}

	/**
	 * Sets the user's weight and returns the new user
	 * 
	 * @param weight
	 *            The user's weight
	 * 
	 * @return The modified user
	 */
	public ApplicationUser setWeight(double weight)
	{
		this.weight = weight;
		return this;
	}

	/**
	 * Sets the user's height and returns the new user
	 * 
	 * @param height
	 *            The user's height
	 * 
	 * @return The modified user
	 */
	public ApplicationUser setHeight(double height)
	{
		this.height = height;
		return this;
	}

	/**
	 * Sets the user's registration date and returns the new user
	 * 
	 * @param registrationDate
	 *            The date the user registered
	 * 
	 * @return The modified user
	 */
	public ApplicationUser setRegistrationDate(Date registrationDate)
	{
		this.registrationDate = registrationDate;
		return this;
	}

	/**
	 * Sets the user's last login date and returns the new user
	 * 
	 * @param lastLogin
	 *            The date of the last time the user logged it
	 * 
	 * @return the modified user
	 */
	public ApplicationUser setLastLogin(Date lastLogin)
	{
		this.lastLogin = lastLogin;
		return this;
	}

	/**
	 * Sets the user's ID and returns the new user
	 * 
	 * @param userId
	 *            The user's ID
	 * 
	 * @return The modified user
	 */
	public ApplicationUser setUserId(long userId)
	{
		this.userId = userId;
		return this;
	}

	/**
	 * Returns the user's email
	 * 
	 * @return The user's email
	 */
	public String getEmail()
	{
		return emailAddress;
	}

	/**
	 * Returns the user's password hash
	 * 
	 * @return The user's password hash
	 */
	public String getPasswordHash()
	{
		return passwordHash;
	}

	/**
	 * Returns the user's full name, their first name followed by their last
	 * name
	 * 
	 * @return The user's full name
	 */
	public String getName()
	{
		return firstName + " " + lastName;
	}

	/**
	 * Returns the user's first name
	 * 
	 * @return The user's first name
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Returns the user's last name
	 * 
	 * @return The user's last name
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Returns the user's sex
	 * 
	 * @return The user's sex
	 */
	public Sex getSex()
	{
		return sex;
	}

	/**
	 * Return the user's height
	 * 
	 * @return The user's height
	 */
	public double getHeight()
	{
		return height;
	}

	/**
	 * Returns the user's weight
	 * 
	 * @return The user's weight
	 */
	public double getWeight()
	{
		return weight;
	}

	/**
	 * Returns this user's date of birth
	 * 
	 * @return This user's date of birth
	 */
	public Date getDOB()
	{
		return dateOfBirth;
	}

	/**
	 * Returns the date of the user's registration
	 * 
	 * @return The user's registration date
	 */
	public Date getRegistrationDate()
	{
		return (Date) registrationDate.clone();
	}

	/**
	 * Returns the date of the user's late login
	 * 
	 * @return The user's last login date
	 */
	public Date getLastLogin()
	{
		return (Date) lastLogin.clone();
	}

	/**
	 * Returns the user's id
	 * 
	 * @return The user's id
	 */
	public long getUserId()
	{
		return userId;
	}

	/**
	 * Returns the user's administrator level
	 * 
	 * @return the user's administrator level
	 */
	public int isAdmin()
	{
		return admin;
	}

	/**
	 * Set this user's admin level and returns the new user
	 * 
	 * @param admin
	 *            The admin level to be set
	 * 
	 * @return The modified user
	 */
	public ApplicationUser setAdmin(int admin)
	{
		this.admin = admin;
		return this;
	}
}
