package com.example.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.PooledConnection;

public class ConnectionPoolingDemo {

	public static void main(String[] args) {
		OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataDource();
		
		ds.setDriverType("thin");
		ds.setServerName("localhost");
		ds.setPortNumber("1521");
		ds.setServiceName("xe");
		ds.setUser("hr");
		ds.setPassword("hr");
		
		PooledConnection pconn  = ds.getPooledConnection();
		
		Connection conn = pconn.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from address");
		
		
	}
}
