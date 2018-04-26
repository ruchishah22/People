package com.person.rest.webservice;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	public Connection getDbConnection() throws Exception {
		Connection connection = null;
		try {
			String connectionURL = "jdbc:mysql://localhost:3306/codezone4";
			Class.forName("org.postgresql.").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "");
			
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