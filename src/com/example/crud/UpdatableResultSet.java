package com.example.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.DBUtil;
import com.example.DBUtil.DBType;

public class UpdatableResultSet {

public static void main(String[] args) throws SQLException {
		
		//Try with recourse : no need to close the connection
		try (
				Connection conn = DBUtil.getConnection(DBType.MSSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = stmt.executeQuery("select * from Employees");
				){
			rs.absolute(6);
			rs.updateString("Department", "IT");
			rs.updateRow();
			
			System.out.println("Record updated successfully");
			
			rs.moveToInsertRow();
			rs.updateInt("employeeId", 23);
			rs.updateString("name", "Manew");
			rs.insertRow();
			System.out.println("Resord has been Inserted");

		}catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		}
	}
}
