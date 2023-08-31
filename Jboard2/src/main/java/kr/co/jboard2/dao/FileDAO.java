package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.FileDTO;

public class FileDAO extends DBHelper {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void insertFile(FileDTO dto) {
		try {
			psmt = getConnection().prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOriName());
			psmt.setString(3, dto.getNewName());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertFile error : " + e.getMessage());
		}
	}

	public FileDTO selectFile(int fno) {
		FileDTO dto = null;
		return dto;
	}

	public List<FileDTO> selectFiles() {
		List<FileDTO> files = new ArrayList<>();
		return files;
	}

	public void updateFile(FileDTO dto) {

	}

	public void deleteFile(int fno) {

	}
}