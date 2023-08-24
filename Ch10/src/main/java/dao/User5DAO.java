package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.User5DTO;

public class User5DAO {
	private final String HOST = "jdbc:mysql://13.209.17.103:3306/userdb";
	private final String USER = "user";
	private final String PASS = "a1s2d3F$";

	public void insertUser5(User5DTO dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `user5` VALUES(?, ?, ?, ?, ?, ?, ?)");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getBirth());
			psmt.setInt(4, dto.getGender());
			psmt.setInt(5, dto.getAge());
			psmt.setString(6, dto.getAddr());
			psmt.setString(7, dto.getHp());
			psmt.executeUpdate();

			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User5DTO selectUser5(String uid) {
		User5DTO dto = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `user5` WHERE `uid` = ?");
			psmt.setString(1, uid);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new User5DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setBirth(rs.getString(3));
				dto.setGender(rs.getInt(4));
				dto.setAge(rs.getInt(5));
				dto.setAddr(rs.getString(6));
				dto.setHp(rs.getString(7));
			}
			rs.close();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public List<User5DTO> selectUser5s() {
		List<User5DTO> users = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `user5`");
			while (rs.next()) {
				User5DTO dto = new User5DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setBirth(rs.getString(3));
				dto.setGender(rs.getInt(4));
				dto.setAge(rs.getInt(5));
				dto.setAddr(rs.getString(6));
				dto.setHp(rs.getString(7));
				users.add(dto);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public void updateUser5(User5DTO dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("UPDATE `user5` SET `name` = ?, `birth` = ?, `gender` = ?, `age` = ?, `addr` = ?, `hp` = ? WHERE `uid` = ?");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getBirth());
			psmt.setInt(3, dto.getGender());
			psmt.setInt(4, dto.getAge());
			psmt.setString(5, dto.getAddr());
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getUid());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteUser5(String uid) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `user5` WHERE `uid` = ?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}