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
		boolean validated = true;
		String name = p.getName();
		int age = p.getAge();
		String dob = p.getDob();
		String email = p.getEmail();
		
		if(name == null || name.trim().length() == 0){
			validated = false;
			throw new Exception("Name can't be empty.");
		}
		if(age<=0) {
			validated = false;
			throw new Exception("Age can't be empty");
		}
		if(dob == null) {
			validated = false;
			throw new Exception("DOB can't be empty");
		}
		if(email == null || email.trim().length() == 0) {
			validated = false;
			throw new Exception("Emails can't be empty");
		}
		return validated;
	}
	
	final String VALIDATE_PEOPLE = "select count(*) from people where id = ?";
	public boolean validateID(int id) throws Exception {
		boolean validated = false;
		  conn=connect.getDbConnection();
	      pst=conn.prepareStatement(VALIDATE_PEOPLE);
	      pst.setInt(1, id);
	      rs = pst.executeQuery();
	      System.out.println("validateID sql: "+ pst);
	      while( rs != null && rs.next()) {
		    	  int count = rs.getInt("count");
		    	  if(count == 0)
		    	  	throw new Exception("The person with id :" + id + " doesn't exist.");
		      else
		    	  	validated = true;
	      }
		return validated;
	}
	
	final String GET_PEOPLE = "select * from people";
	public List<People> getAllPeople() throws Exception{
	    conn=connect.getDbConnection();
        pst=conn.prepareStatement(GET_PEOPLE);
        rs = pst.executeQuery();
        System.out.println("getAllPeople sql: "+ pst);
        while( rs != null && rs.next()) {
        		ppl = new People();
        		ppl.setId(rs.getInt("id"));
       		ppl.setName(rs.getString("name"));		
        		ppl.setAge(rs.getInt("age"));
        		ppl.setDob(rs.getString("dob"));
        		ppl.setEmail(rs.getString("email"));
        		listOfPeople.add(ppl);
        }
            pst.close();
            rs.close();
            return listOfPeople;
	}
								
	final String CREATE_PEOPLE = "insert into people (id, name, age, dob, email) values (DEFAULT,?,?,?,?) returning id";
	public People create(People p)  throws Exception{
		int returnedId=0;
		conn=connect.getDbConnection();
        PreparedStatement pst=conn.prepareStatement(CREATE_PEOPLE);
        pst.setString(1, p.getName());
        pst.setInt(2, p.getAge());
        pst.setString(3, p.getDob());
        pst.setString(4, p.getEmail());
        rs = pst.executeQuery();
        System.out.println("create sql: "+ pst);
        while( rs != null && rs.next()) {	    		
        		returnedId=rs.getInt("id");	    		
	    }        
        ppl = getPeople(returnedId);
        pst.close();
        rs.close();
		return ppl;
	}
	
	final String GET_PEOPLE_ID = "select * from people where id = ?";
	public People getPeople(int id) throws Exception {
		conn=connect.getDbConnection();
        pst=conn.prepareStatement(GET_PEOPLE_ID);
        pst.setInt(1, id); 
        rs = pst.executeQuery();
        System.out.println("getPeople sql: "+ pst);
	        while( rs != null && rs.next()) {
	        		ppl = new People();
	        		ppl.setId(rs.getInt("id"));
	        		ppl.setName(rs.getString("name"));
	        		ppl.setAge(rs.getInt("age"));
	        		ppl.setDob(rs.getString("dob"));
	        		ppl.setEmail(rs.getString("email"));
	        }
        
        pst.close();
        rs.close();
		return ppl;
	}
	
	final String UPDATE_PEOPLE = "update people set name = ?, age = ?, dob = ?, email = ? where id = ? ";
	public People updatePeople(int id, People p)  throws Exception{
		conn=connect.getDbConnection();
        pst=conn.prepareStatement(UPDATE_PEOPLE);
        pst.setString(1, p.getName());
        pst.setInt(2, p.getAge());
        pst.setString(3, p.getDob());
        pst.setString(4, p.getEmail());
        pst.setInt(5, id);
        int record = pst.executeUpdate();
        System.out.println("updatePeople sql: "+ pst);
        if(record == 0) {
        	 	throw new Exception("The record isn't modified");
        } else {
        		pst1 = conn.prepareStatement(GET_PEOPLE_ID);
        		pst1.setInt(1, id);
        		rs1 = pst.executeQuery();
                while( rs1 != null) {
                		ppl = new People();
                		ppl.setId(rs1.getInt("id"));
                		ppl.setName(rs1.getString("name"));
                		ppl.setAge(rs1.getInt("age"));
                		ppl.setDob(rs1.getString("dob"));
                		ppl.setEmail(rs1.getString("email"));
                }
        		
        }
        pst.close();
        rs.close();
        pst1.close();
        rs1.close();
        
		return ppl;
	}
	
	final String DELETE_PEOPLE = "delete from people where id = ?";
	public int removePeople(int id) throws Exception {
		int count = 0;
		conn=connect.getDbConnection();
        pst=conn.prepareStatement(DELETE_PEOPLE);
        pst.setInt(1, id);
        int record = pst.executeUpdate();
        System.out.println("removePeople sql: "+ pst);
        if(record==0){
			throw new Exception("The record isn't modified");
		}else{
			count = 1;
		}
		return count;
	}
	
}
