package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.dto.UserDTO;

public class UserDAO extends DBHelper{
	public void insertUser(UserDTO dto) {
		
	}
	
	public UserDTO selectUser(String uid) {
		UserDTO dto = null;
		
		return dto;
	}
	
	public List<UserDTO> selectUser() {
		List<UserDTO> users = new ArrayList<UserDTO>();
		
		return users;
	}
	
	public void updateUser(UserDTO dto) {
		
	}
	
	public void deleteUser(String uid) {
		
	}
}