package com.example.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.DBUtil;
import com.example.DBUtil.DBType;

public class ExecuteStaticStatements {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection(DBType.MSSQL);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from address");
			rs.last();
			
			System.out.println("Total ROws : " + rs.getRow());
		}catch (SQLException e) {

		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
}
