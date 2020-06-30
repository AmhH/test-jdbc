package com.example.txn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.DBUtil;
import com.example.DBUtil.DBType;

public class TransactionDemo {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection(DBType.MSSQL);
			conn.setAutoCommit(false);
			String withDraw = "UPDATE PSBANK set amount = amount - ? where acno =?";
			pstmt = conn.prepareStatement(withDraw);
		
			pstmt.setDouble(1, 500);
			pstmt.setInt(2, 7);
			pstmt.executeUpdate();
			
			String deposit = "UPDATE PSBANK set amount = amount + ? where acno =?";
			pstmt = conn.prepareStatement(deposit);
		
			pstmt.setDouble(1, 500);
			pstmt.setInt(2, 2);
			pstmt.executeUpdate();
			
			String sql = "select amount from PSBANK where acno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, 7);
			ResultSet rs =  pstmt.executeQuery();
			double banlce = 0;
			if(rs.next()) {
				banlce = rs.getDouble("amount");
			}
			
			if(banlce >= 5000) {
				conn.commit();
				System.out.println("Amount Transferre successfully ...");
			}else {
				conn.rollback();
				System.out.println("Insufficient Funds ..");
			}
			
		}catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		}
	}
}
