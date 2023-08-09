package kr.co.jboard1.dao;

import kr.co.jboard1.db.DBHelper;
import kr.co.jboard1.db.SQL;
import kr.co.jboard1.dto.UserDTO;

public class UserDAO extends DBHelper {
	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	private UserDAO() {

	}

	public void insertUser(UserDTO vo) {
		try {
			psmt = getConnection().prepareStatement(SQL.INSERT_USER);
			psmt.setString(1, vo.getUid());
			psmt.setString(2, vo.getPass());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getNick());
			psmt.setString(5, vo.getEmail());
			psmt.setString(6, vo.getHp());
			psmt.setString(7, vo.getHp());
			psmt.setString(8, vo.getZip());
			psmt.setString(9, vo.getAddr1());
			psmt.setString(10, vo.getAddr2());
			psmt.setString(11, vo.getRegip());
			psmt.setString(12, null);
			psmt.executeUpdate();

			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public UserDTO selectUser(String uid, String pass) {
		UserDTO user = null;
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			if (rs.next()) {
				user = new UserDTO();
				user.setUid(rs.getString(1));
				user.setPass(rs.getString(2));
				user.setName(rs.getString(3));
				user.setNick(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setHp(rs.getString(6));
				user.setRole(rs.getString(7));
				user.setZip(rs.getString(8));
				user.setAddr1(rs.getString(9));
				user.setAddr2(rs.getString(10));
				user.setRegip(rs.getString(11));
				user.setRegDate(rs.getString(12));
				user.setLeaveDate(rs.getString(13));
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	};

	public int checkUser(String key, String sql) {
		int result = 0;
		try {
			psmt = getConnection().prepareStatement(sql);
			psmt.setString(1, key);
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void selectUsers() {

	};

	public void updateUser() {

	};

	public void deleteUser() {

	};
}