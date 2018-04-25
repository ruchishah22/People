package com.person.rest.webservice;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	public Connection getDbConnection() throws Exception {
		Connection connection = null;
		try {
			String connectionURL = "jdbc:mysql://localhost:3306/codezone4";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "");
			
		} catch(Exception e) {
			throw e;
		}
		return connection;
	}
	
}
