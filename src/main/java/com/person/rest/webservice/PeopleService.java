package com.person.rest.webservice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PeopleService {
	DbConnection connect = new DbConnection();
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	PreparedStatement pst1 = null;
	ResultSet rs1 = null;
	People ppl = null;
	List<People> listOfPeople = new ArrayList<People>();
	
	public boolean validate(People p) throws Exception {
		boolean validated = false;
		String name = p.getName();
		int age = p.getAge();
		Date dob = p.getDob();
		String email = p.getEmail();
		
		if(name == null || name.trim().length() == 0)
			throw new Exception("Name can't be empty.");
		if(age<=0)
			throw new Exception("Age can't be empty");
		if(dob == null)
			throw new Exception("DOB can't be empty");
		if(email == null || email.trim().length() == 0)
			throw new Exception("Emails can't be empty");
		return validated;
	}
	
	final String GET_PEOPLE = "select * from people";
	public List<People> getAllPeople() throws Exception{
		conn=connect.getDbConnection();
        pst=conn.prepareStatement(GET_PEOPLE);
        rs = pst.executeQuery();
        while( rs != null) {
        		ppl = new People();
        		ppl.setId(rs.getString("id"));
        		ppl.setName(rs.getString("name"));
        		ppl.setAge(rs.getInt("age"));
        		ppl.setDob(rs.getDate("dob"));
        		ppl.setEmail(rs.getString("email"));
        		listOfPeople.add(ppl);
        }
            pst.close();
            rs.close();
            return listOfPeople;
	}
	
	final String CREATE_PEOPLE = "insert into people (name, age, dob, email) values (?,?,?,?) ";
	public People create()  throws Exception{
		conn=connect.getDbConnection();
        PreparedStatement pst=conn.prepareStatement(CREATE_PEOPLE);
        rs = pst.executeQuery();
        
        
        pst.close();
        rs.close();
		return null;
	}
	
	final String GET_PEOPLE_ID = "select * from people where id = ?";
	public People getPeople(String id) throws Exception {
		conn=connect.getDbConnection();
        pst=conn.prepareStatement(GET_PEOPLE_ID);
        pst.setString(1, id);
        rs = pst.executeQuery();
        while( rs != null) {
        		ppl = new People();
        		ppl.setId(rs.getString("id"));
        		ppl.setName(rs.getString("name"));
        		ppl.setAge(rs.getInt("age"));
        		ppl.setDob(rs.getDate("dob"));
        		ppl.setEmail(rs.getString("email"));
        }
        pst.close();
        rs.close();
		return ppl;
	}
	
	final String UPDATE_PEOPLE = "update people set name = ?, age = ?, dob = ?, email = ? where id = ? ";
	public People updatePeople(String id, People p)  throws Exception{
		conn=connect.getDbConnection();
        pst=conn.prepareStatement(UPDATE_PEOPLE);
        pst.setString(1, p.getName());
        pst.setInt(2, p.getAge());
        pst.setDate(3, p.getDob());
        pst.setString(4, p.getEmail());
        pst.setString(5, id);
        int record = pst.executeUpdate();
        
        if(record == 0) {
        	 	throw new Exception("The record isn't modified");
        } else {
        		pst1 = conn.prepareStatement(GET_PEOPLE_ID);
        		pst1.setString(1, id);
        		rs1 = pst.executeQuery();
                while( rs1 != null) {
                		ppl = new People();
                		ppl.setId(rs1.getString("id"));
                		ppl.setName(rs1.getString("name"));
                		ppl.setAge(rs1.getInt("age"));
                		ppl.setDob(rs1.getDate("dob"));
                		ppl.setEmail(rs1.getString("email"));
                }
        		
        }
        pst.close();
        rs.close();
        pst1.close();
        rs1.close();
        
		return ppl;
	}
	
	final String DELETE_PEOPLE = "delete * from people where id = ?";
	public void removePeople(String id) throws Exception {
		conn=connect.getDbConnection();
        pst=conn.prepareStatement(DELETE_PEOPLE);
        pst.setString(1, id);
        int record = pst.executeUpdate();
        if(record==0){
			throw new Exception("The record isn't modified");
		}else{
			
		}
		
	}
}
