package com.example.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.DBUtil;
import com.example.DBUtil.DBType;

public class PreparedStatementRetrieve {
		
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection(DBType.MSSQL);
			String sql = "select * from employee where salary < ? and department_id = ?";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setDouble(1,  1000);
			pstmt.setInt(2, 50);
			rs = pstmt.executeQuery();
			
			String format = "%-4s%-20s%-25s%-10f\n";
			while(rs.next()) {
				System.out.format(format, rs.getString("Employee_I"), rs.getString("First_name"), rs.getString("last_name"), rs.getFloat("salary"));
			}
			
		}catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		}
	}
	
}
