package com.costa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface IConnection {


	String user = "root";
	String password = "";

	default Connection getConnection() throws  ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/administracion", user, password);
			
			if (connection != null) {
				System.out.println("Connection established");
			} else {
				throw new Exception("Connection appear to be null");
			}
			
		} catch (Exception error) {
			System.out.println("Cannot even connect DB");
			System.out.println(error.getMessage());
			error.printStackTrace();
		}
		return connection;
	}

	default void closeConnection() {
		Connection connection = null;
		
		try {
			connection.close();
		} catch (Exception error) {

		}
	}

	
}
