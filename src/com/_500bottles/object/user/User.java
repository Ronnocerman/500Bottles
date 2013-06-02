package com._500bottles.object.user;

import java.util.Date;

public class User extends ApplicationUser
{
	public User()
	{
		this.setUserId(0);
		this.setRegistrationDate(new Date());
		this.setLastLogin(new Date());
		this.setEmail("");
		this.setPassword("");
		this.setHeight(0);
		this.setWeight(0);
		this.setDOB(new Date());
		this.setSex(Sex.male);
		this.setFirstName("");
		this.setLastName("");
	}
}
