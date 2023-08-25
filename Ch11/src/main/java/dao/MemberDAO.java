package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.MemberDTO;

public class MemberDAO {
	public static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	private MemberDAO() {

	}
	
	// 로거생성
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void insertMember(MemberDTO dto) {
		try {
			logger.info("MemberDAO insertMember()..." + dto);
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)ctx.lookup("jdbc/Userdb");
			Connection conn = ds.getConnection();
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `member` VALUES(?, ?, ?, ?, ?, NOW())");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setString(4, dto.getPos());
			psmt.setInt(5, dto.getDep());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			logger.error("MemberDAO insertMember() ERROR" + e.getMessage());
		}
	}

	public MemberDTO selectMember(String uid) {
		MemberDTO dto = null;

		return dto;
	}

	public List<MemberDTO> selectMembers() {
		List<MemberDTO> members = new ArrayList<MemberDTO>();

		return members;
	}

	public void updateMember(MemberDTO dto) {

	}

	public void deleteMember(String uid) {

	}
}