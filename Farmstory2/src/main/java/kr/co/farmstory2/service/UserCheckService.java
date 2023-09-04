package kr.co.farmstory2.service;

import kr.co.farmstory2.dao.UserCheckDAO;

public class UserCheckService {
	private UserCheckDAO dao = new UserCheckDAO();

	public int checkUid(String uid) {
		return dao.checkUid(uid);
	}
	
	public int checkNick(String nick) {
		return dao.checkNick(nick);
	}
	
	public int checkHp(String hp) {
		return dao.checkHp(hp);
	}
}