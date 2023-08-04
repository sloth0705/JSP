package kr.co.jboard1.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBHelper {
	protected Connection conn;
	protected PreparedStatement psmt;
	protected Statement stmt;
	protected ResultSet rs;

	public Connection getConnection() {
		try {
			Context ctx = (Context) new InitialContext().lookup("java:comp/env"); // JNDI 기본 환경이름
			DataSource ds = (DataSource) ctx.lookup("jdbc/Jboard");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (psmt != null) {
			psmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}