package com.example.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.DBUtil;
import com.example.DBUtil.DBType;

public class DBMetaDataDemo {

	public static void main(String[] args) {
		try(Connection conn = DBUtil.getConnection(DBType.MYSQL)){
			DatabaseMetaData dbmd = conn.getMetaData();
			
			System.out.println("Driver Name : " + dbmd.getDriverName());
			System.out.println("Driver Version : " + dbmd.getDriverVersion());
			System.out.println("Loged in User : " + dbmd.getDriverName());
			System.out.println("Database Product Name : " + dbmd.getDatabaseProductName());
			System.out.println("Database Product Version : " + dbmd.getDatabaseProductVersion());
			
			ResultSet rs = dbmd.getTables(null, null, null, new String[] {"TABLE"});
			
			while(rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));
			}
			rs.close();
		} catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		}
	}
}
