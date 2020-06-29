package com.example.crud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.DBUtil;
import com.example.DBUtil.DBType;

public class PreparedStatementInsert {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection(DBType.MSSQL);
			String sql = "INSERT INTO newemployee values(?,?,?,?}";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, 50);
			pstmt.setString(2, "email");
			pstmt.setString(2, "name");
			pstmt.setDate(2, new Date(4567890L));
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("Inserted sucessfully");
			}else {
				System.err.println("Something went wrong");
			}
		}catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		}
	}
}
