package com.person.rest.webservice;

import java.sql.Date;

public class People {
	
	private int id;
	private String name;
	private int age;
	private String dob;
	private String email;
	
	public People(String name, int age, String dob, String email) {
		this.name = name;
		this.age = age;
		this.dob = dob;
		this.email = email;
	}

	public People() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
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
