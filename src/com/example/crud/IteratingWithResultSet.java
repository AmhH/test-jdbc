package com.example.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.DBUtil;
import com.example.DBUtil.DBType;

public class IteratingWithResultSet {

	public static void main(String[] args) throws SQLException {
		
		//Try with recourse : no need to close the connection
		try (	
				Connection conn = DBUtil.getConnection(DBType.MSSQL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Employees");
				){
			
			String format = "%-4s%-20s%-25s%-10f\n";
			while(rs.next()) {
				System.out.format(format, rs.getString("Employee_I"), rs.getString("First_name"), rs.getString("last_name"), rs.getFloat("salary"));
			}
			System.out.println("Total ROws : " + rs.getRow());
		}catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		}
	}
}

