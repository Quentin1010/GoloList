package com.qbeuvelet.gololist.model;

/**
 * @author Abhishek Vadnerkar
 */

public class Person {
	private String name;
	private String phoneNumber;
	
	public Person(String name, String phoneNumber)
	{
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
}
