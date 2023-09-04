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

	public UserDTO selectUser(String uid, String pass) {
		UserDTO dto = null;
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new UserDTO();
				dto.setUid(rs.getString("uid"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setNick(rs.getString("nick"));
				dto.setEmail(rs.getString("email"));
				dto.setHp(rs.getString("hp"));
				dto.setRole(rs.getString("role"));
				dto.setZip(rs.getString("zip"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setRegip(rs.getString("regip"));
				dto.setRegDate(rs.getString("regDate"));
				dto.setLeaveDate(rs.getString("leaveDate"));
			}
			close();
		} catch (Exception e) {
			logger.error("selectUser error : " + e.getMessage());
		}
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
