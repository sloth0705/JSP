package kr.co.farmstory2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.FileDTO;

public class FileDAO extends DBHelper {
	private static FileDAO instance = new FileDAO();

	public static FileDAO getInstance() {
		return instance;
	}

	private FileDAO() {

	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void insertFile(FileDTO dto) {
		try {
			psmt = getConnection().prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getaNo());
			psmt.setString(2, dto.getOriName());
			psmt.setString(3, dto.getNewName());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertFile error : " + e.getMessage());
		}
	}

	public FileDTO selectFile(String no) {
		FileDTO dto = null;
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_FILE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new FileDTO();
				dto.setfNo(rs.getInt("fNo"));
				dto.setaNo(rs.getInt("aNo"));
				dto.setOriName(rs.getString("oriName"));
				dto.setNewName(rs.getString("newName"));
				dto.setDownload(rs.getInt("download"));
				dto.setRdate(rs.getString("rdate"));
			}
		} catch (Exception e) {
			logger.error("selectFile error : " + e.getMessage());
		}
		return dto;
	}
	
	public FileDTO selectFileBYfNO(String fNo) {
		FileDTO dto = null;
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_FILE_BY_FNO);
			psmt.setString(1, fNo);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new FileDTO();
				dto.setfNo(rs.getInt("fNo"));
				dto.setaNo(rs.getInt("aNo"));
				dto.setOriName(rs.getString("oriName"));
				dto.setNewName(rs.getString("newName"));
				dto.setDownload(rs.getInt("download"));
				dto.setRdate(rs.getString("rdate"));
			}
		} catch (Exception e) {
			logger.error("selectFile error : " + e.getMessage());
		}
		return dto;
	}
}