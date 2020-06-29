package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String mySqlUser = "rot";
	private static final String mySqlPwd = "root";
	private static final String mySqlCS = "jdbc:mysql://localhost:3306/abyssinia";
	
	public enum DBType{
		ORACLE, MYSQL, MSSQL;
	}
	
	public static Connection getConnection(DBType dbType) throws SQLException {
		switch(dbType) {
		case MYSQL:
			return DriverManager.getConnection(mySqlCS, mySqlUser, mySqlPwd);
		default:
			return null;
		}
	}
	
	public static void showErrorMessage(SQLException exception) {
		System.err.println("Error :" + exception.getMessage());
		System.err.println("Error COde :" + exception.getErrorCode());
	}
}
