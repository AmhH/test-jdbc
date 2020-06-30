package com.example.metadata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.DBUtil;
import com.example.DBUtil.DBType;

public class ResultSetMetaDataDemo {

	public static void main(String[] args) {
		
		try(
				Connection conn = DBUtil.getConnection(DBType.MSSQL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Employees");){
			ResultSetMetaData rsmd = rs.getMetaData();
			
			String format = "%-50s%-25s%-20s%-20f\n";
			System.out.format(format, "Column Name", "Column Type", "Is Nullable", "Is AutoIncrement");
			for(int i =0; i < rsmd.getColumnCount(); i++) {
				System.out.format(format, rsmd.getColumnName(i), rsmd.getColumnType(i), rsmd.isNullable(i), rsmd.isAutoIncrement(i));
			}
			
		}catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		}
	}
}
