package com.person.rest.webservice;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	public Connection getDbConnection() throws Exception {
		Connection connection = null;
		try {
			String connectionURL = "jdbc:postgresql://ec2-107-21-103-146.compute-1.amazonaws.com:5432/df1jsn0bo6190d?sslmode=require";
			String userName = "bypjdjbxqbkagw";
			String password = "6fb4701411b8292a070b568dbfa52b051682077d4f6b31b81a75938d45ea1e2b";
			/*String connectionURL = "jdbc:postgresql://localhost:5432/mytestdb";
			String userName = "rus9029";
			String password = "";*/
			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, userName, password);
			
		} catch(Exception e) {
			throw e;
		}
		return connection;
	}
	
}


