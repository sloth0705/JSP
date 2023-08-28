package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.UserDTO;

public class UserDAO extends DBHelper {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void insertUser(UserDTO dto) {
		try {
			psmt = getConnection().prepareStatement(SQL.INSERT_USER);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getNick());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getZip());
			psmt.setString(8, dto.getAddr1());
			psmt.setString(9, dto.getAddr2());
			psmt.setString(10, dto.getRegip());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertUser error : " + e.getMessage());
		}
	}

	public UserDTO selectUser(String uid) {
		UserDTO dto = null;

		return dto;
	}

	public List<UserDTO> selectUsers() {
		List<UserDTO> users = new ArrayList<UserDTO>();

		return users;
	}

	public void updateUser(UserDTO dto) {

	}

	public void deleteUser(String uid) {

	}
}