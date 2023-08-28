package kr.co.jboard2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;

public class UserCheckDAO extends DBHelper {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int checkUid(String uid) {
		int result = 0;
		try {
			psmt = getConnection().prepareStatement(SQL.CHECK_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("checkUid error : " + e.getMessage());
		}
		return result;
	}
	
	public int checkNick(String nick) {
		int result = 0;
		try {
			psmt = getConnection().prepareStatement(SQL.CHECK_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("checkUid error : " + e.getMessage());
		}
		return result;
	}
	
	public int checkHp(String hp) {
		int result = 0;
		try {
			psmt = getConnection().prepareStatement(SQL.CHECK_HP);
			psmt.setString(1, hp);
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("checkUid error : " + e.getMessage());
		}
		return result;
	}
}