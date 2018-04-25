package com.person.rest.webservice;

import java.sql.Date;

public class People {
	
	private String id;
	private String name;
	private int age;
	private Date dob;
	private String email;
	
	public People(String name, int age, Date dob, String email) {
		this.name = name;
		this.age = age;
		this.dob = dob;
		this.email = email;
	}

	public People() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", dob=" + dob + ", email=" + email + "]";
	}
	
	

}
