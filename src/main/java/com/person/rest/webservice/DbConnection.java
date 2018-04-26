package com.person.rest.webservice;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	public Connection getDbConnection() throws Exception {
		Connection connection = null;
		try {
			String connectionURL = "jdbc:postgresql://ec2-107-20-249-68.compute-1.amazonaws.com:5432/d966j9djqou3o3";
			String userName = "opdnggiznnrvbp";
			String password = "e6c6372f4ca1e01bb972db4eb56201f9d4f094f7d4c290ef32053820d298bc6e";
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