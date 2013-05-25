package com._500bottles.object.user;

public class Admin extends ApplicationUser
{

	@Override
	public void setEmail(String email)
	{
		// TODO Auto-generated method stub
		emailAddress = email;

	}

	@Override
	public void setPassword(char[] pass)
	{
		// TODO Auto-generated method stub
		password = pass;
	}

	@Override
	public void setFirstName(String first)
	{
		// TODO Auto-generated method stub
		firstName = first;
	}

	@Override
	public void setLastName(String last)
	{
		// TODO Auto-generated method stub
		lastName = last;
	}

	@Override
	public void setDateOfBirth(long Dob)
	{
		// TODO Auto-generated method stub
		dateOfBirth = Dob;
	}

	@Override
	public void setSex(Sex s)
	{
		// TODO Auto-generated method stub
		sex = s;
	}

	@Override
	public void setWeight(double fat)
	{
		// TODO Auto-generated method stub
		weight = fat;
	}

	@Override
	public void setHeight(double tall)
	{
		// TODO Auto-generated method stub
		height = tall;
	}

	@Override
	public String getEmail()
	{
		// TODO Auto-generated method stub
		return emailAddress;
	}

	@Override
	public char[] getPasswordHash()
	{
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return firstName + " " + lastName;
	}

	@Override
	public String getFirstName()
	{
		// TODO Auto-generated method stub
		return firstName;
	}

	@Override
	public String getLastName()
	{
		// TODO Auto-generated method stub
		return lastName;
	}

	@Override
	public Sex getSex()
	{
		// TODO Auto-generated method stub
		return sex;
	}

	@Override
	public double getHeight()
	{
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public double getWeight()
	{
		// TODO Auto-generated method stub
		return weight;
	}

	@Override
	public long getDOB()
	{
		// TODO Auto-generated method stub
		return dateOfBirth;
	}

}
