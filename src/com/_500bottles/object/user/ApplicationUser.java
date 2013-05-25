package com._500bottles.object.user;

public abstract class ApplicationUser
{
	protected String emailAddress;
	protected char[] password;
	protected double height;
	protected double weight;
	protected long dateOfBirth;
	protected Sex sex;
	protected String firstName;
	protected String lastName;

	public ApplicationUser()
	{
	}

	abstract void setEmail(String email);

	abstract void setPassword(char[] pass);

	abstract void setFirstName(String First);

	abstract void setLastName(String Last);

	abstract void setDateOfBirth(long Dob);

	abstract void setSex(Sex s);

	abstract void setWeight(double fat);

	abstract void setHeight(double tall);

	abstract String getEmail();

	abstract char[] getPasswordHash();

	abstract String getName();

	abstract String getFirstName();

	abstract String getLastName();

	abstract Sex getSex();

	abstract double getHeight();

	abstract double getWeight();

	abstract long getDOB();

}
