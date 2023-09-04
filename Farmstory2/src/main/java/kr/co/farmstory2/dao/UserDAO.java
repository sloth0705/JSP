package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.TermsDTO;
import kr.co.farmstory2.dto.UserDTO;

public class UserDAO extends DBHelper {
	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	private UserDAO() {

	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void insertUser(UserDTO dto) {

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

	public TermsDTO selectTerms() {
		TermsDTO dto = null;
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			if (rs.next()) {
				dto = new TermsDTO();
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
			}
			close();
		} catch (Exception e) {
			logger.error("selectTerms error : " + e.getMessage());
		}
		return dto;
	}
}
