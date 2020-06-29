package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMySqlConnection {

	static String username = "root";
	static String password = "root";
	static String dbUrl = "jdbc:mysql://localhost:3306/abyssinia";
	
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl, username, password);
			System.out.println("Connection Stablished");
		} catch (SQLException e) {
			System.err.println(e.getMessage());;
		}
		finally {
			conn.close();
		}
	}
}
