package com.person.rest.webservice;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	public Connection getDbConnection() throws Exception {
		Connection connection = null;
		try {
			/*String connectionURL = "jdbc:postgresql://ec2-54-243-54-6.compute-1.amazonaws.com:5432/da4s06a4872dbg?sslmode=require";
			String userName = "hcbpgftammbgxq";
			String password = "ac377244cab942f0b9e3c84ac82f69bb9717fb19cd583814a1c2d662714144d1";*/
			String connectionURL = "jdbc:postgresql://localhost:5432/mytestdb";
			String userName = "rus9029";
			String password = "";
			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, userName, password);
			
		} catch(Exception e) {
			throw e;
		}
		return connection;
	}
	
}

/*ec2-107-20-249-68.compute-1.amazonaws.com
Database
d4cq9j2oda5gio
User
sroqzsesutigzm
Port
5432
Password
e34c1991b5abe40e45971d14b328f8adcecb3751dcb6ec17b556ac6872975be8*/