package com.example.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.DBUtil;
import com.example.DBUtil.DBType;

public class ResultSetScrolling {

public static void main(String[] args) throws SQLException {
		
		//Try with recourse : no need to close the connection
		try (	
				Connection conn = DBUtil.getConnection(DBType.MSSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("select * from Employees where rumnum <= 10");
				){
			
			String format = "%-4s%-20s%-25s%-10f\n";
			rs.beforeFirst();
			System.out.println("First 10 Rows : ");
			while(rs.next()) {
				System.out.format(format, rs.getString("Employee_I"), rs.getString("First_name"), rs.getString("last_name"), rs.getFloat("salary"));
			}

			rs.afterLast();
			System.out.println("Last 10 Rows : ");
			while(rs.previous()) {
				System.out.format(format, rs.getString("Employee_I"), rs.getString("First_name"), rs.getString("last_name"), rs.getFloat("salary"));
			}
			
			rs.first();
			System.out.println("First Record : ");
			System.out.format(format, rs.getString("Employee_I"), rs.getString("First_name"), rs.getString("last_name"), rs.getFloat("salary"));

			rs.last();
			System.out.println("Last Record : ");
			System.out.format(format, rs.getString("Employee_I"), rs.getString("First_name"), rs.getString("last_name"), rs.getFloat("salary"));

			rs.absolute(4);
			System.out.println("Record at 4th row : ");
			System.out.format(format, rs.getString("Employee_I"), rs.getString("First_name"), rs.getString("last_name"), rs.getFloat("salary"));

			rs.relative(2);
			System.out.println("Record at 6th row : ");
			System.out.format(format, rs.getString("Employee_I"), rs.getString("First_name"), rs.getString("last_name"), rs.getFloat("salary"));

			rs.relative(-4);
			System.out.println("Record at 2nd row : ");
			System.out.format(format, rs.getString("Employee_I"), rs.getString("First_name"), rs.getString("last_name"), rs.getFloat("salary"));

		}catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		}
	}
}
