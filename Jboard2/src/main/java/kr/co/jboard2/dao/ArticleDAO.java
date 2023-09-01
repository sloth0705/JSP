package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;

public class ArticleDAO extends DBHelper {
	private static ArticleDAO instance = new ArticleDAO();

	public static ArticleDAO getInstance() {
		return instance;
	}

	private ArticleDAO() {

	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int insertArticle(ArticleDTO dto) {
		int no = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false); // 트랜젝션 시작
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			psmt.executeUpdate();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
			conn.commit(); // 작업확정
			if (rs.next()) {
				no = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("insertArticle error : " + e.getMessage());
		}
		return no;
	}

	public ArticleDTO selectArticle(String no) {
		ArticleDTO dto = null;
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				// 파일 정보
				FileDTO fileDto = new FileDTO();
				fileDto.setFno(rs.getInt(12));
				fileDto.setAno(rs.getInt(13));
				fileDto.setOriName(rs.getString(14));
				fileDto.setNewName(rs.getString(15));
				fileDto.setDownload(rs.getInt(16));
				fileDto.setRdate(rs.getString(17));
				dto.setFileDto(fileDto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectArticle error : " + e.getMessage());
		}
		return dto;
	}

	public List<ArticleDTO> selectArticles(int start, String search) {
		List<ArticleDTO> articles = new ArrayList<>();
		try {
			if (search == null) {
				psmt = getConnection().prepareStatement(SQL.SELECT_ARTICLES);
				psmt.setInt(1, start);
			} else {
				psmt = getConnection().prepareStatement(SQL.SELECT_ARTICLES_FOR_SEARCH);
				psmt.setString(1, "%" + search + "%");
				psmt.setInt(2, start);
			}
			rs = psmt.executeQuery();
			while (rs.next()) {
				ArticleDTO vo = new ArticleDTO();
				vo.setNo(rs.getInt(1));
				vo.setParent(rs.getInt(2));
				vo.setComment(rs.getInt(3));
				vo.setCate(rs.getString(4));
				vo.setTitle(rs.getString(5));
				vo.setContent(rs.getString(6));
				vo.setFile(rs.getInt(7));
				vo.setHit(rs.getInt(8));
				vo.setWriter(rs.getString(9));
				vo.setRegip(rs.getString(10));
				vo.setRdate(rs.getString(11));
				vo.setNick(rs.getString(12));
				articles.add(vo);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articles;
	}

	public void updateArticle(ArticleDTO dto) {

	}

	public void deleteArticle(String no) {
		try {
			psmt = getConnection().prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("deleteArticle error : " + e.getMessage());
		}
	}

	public int selectCountTotal(String search) {
		int result = 0;
		try {
			if (search == null) {
				psmt = getConnection().prepareStatement(SQL.SELECT_COUNT_TOTAL);
			} else {
				psmt = getConnection().prepareStatement(SQL.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, "%" + search + "%");
			}
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

	public void downloadFile(int fno) {
		try {
			psmt = getConnection().prepareStatement(SQL.DOWNLOAD_FILE);
			psmt.setInt(1, fno);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("downloadFile error : " + e.getMessage());
		}
	}

	public List<ArticleDTO> selectComments(int parent) {
		List<ArticleDTO> comments = new ArrayList<>();
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setInt(1, parent);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ArticleDTO vo = new ArticleDTO();
				vo.setNo(rs.getInt(1));
				vo.setParent(rs.getInt(2));
				vo.setComment(rs.getInt(3));
				vo.setCate(rs.getString(4));
				vo.setTitle(rs.getString(5));
				vo.setContent(rs.getString(6));
				vo.setFile(rs.getInt(7));
				vo.setHit(rs.getInt(8));
				vo.setWriter(rs.getString(9));
				vo.setRegip(rs.getString(10));
				vo.setRdate(rs.getString(11));
				vo.setNick(rs.getString(12));
				comments.add(vo);
			}
		} catch (Exception e) {
			logger.error("selectComments error : " + e.getMessage());
		}
		return comments;
	}

	public int insertComment(ArticleDTO dto) {
		int result = 0;
		try {
			psmt = getConnection().prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertComment error : " + e.getMessage());
		}
		return result;
	}

	public void plusComment(String parent) {
		try {
			psmt = getConnection().prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT);
			psmt.setString(1, parent);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("plusComment error : " + e.getMessage());
		}
	}
}