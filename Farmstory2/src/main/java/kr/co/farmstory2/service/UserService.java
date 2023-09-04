package kr.co.farmstory2.service;

import java.util.List;

import kr.co.farmstory2.dao.UserDAO;
import kr.co.farmstory2.dto.TermsDTO;
import kr.co.farmstory2.dto.UserDTO;

public enum UserService {
	INSTANCE;

	private UserDAO dao = UserDAO.getInstance();

	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}

	public UserDTO selectUser(String uid) {
		return dao.selectUser(uid);
	}

	public List<UserDTO> selectUsers() {
		return dao.selectUsers();
	}

	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}

	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}

	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
}