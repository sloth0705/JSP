package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ArticleDTO;

public class ArticleDAO extends DBHelper {
	private static ArticleDAO instance = new ArticleDAO();

	public static ArticleDAO getInstance() {
		return instance;
	}

	private ArticleDAO() {

	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int insertArticle(ArticleDTO dto) {
		int result = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getCate());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setInt(4, dto.getFile());
			psmt.setString(5, dto.getWriter());
			psmt.setString(6, dto.getRegip());
			psmt.executeUpdate();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
			conn.commit(); // 작업확정
			if (rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("insertArticle error : " + e.getMessage());
		}
		return result;
	}

	public ArticleDTO selectArticle(String no) {
		ArticleDTO dto = null;
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new ArticleDTO();
				dto.setNo(rs.getInt("no"));
				dto.setParent(rs.getInt("parent"));
				dto.setComment(rs.getInt("comment"));
				dto.setCate(rs.getString("cate"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getInt("file"));
				dto.setHit(rs.getInt("hit"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setNick(rs.getString("nick"));
			}
		} catch (Exception e) {
			logger.error("selectArticle error : " + e.getMessage());
		}
		return dto;
	}

	public List<ArticleDTO> selectArticles(int start, String cate) {
		List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt("no"));
				dto.setParent(rs.getInt("parent"));
				dto.setComment(rs.getInt("comment"));
				dto.setCate(rs.getString("cate"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getInt("file"));
				dto.setHit(rs.getInt("hit"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setNick(rs.getString("nick"));
				articles.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectArticles error : " + e.getMessage());
		}
		return articles;
	}

	public void updateArticle(ArticleDTO dto) {

	}

	public void deleteArticle(String no) {

	}

	public int selectCountTotal(String cate) {
		int result = 0;
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_COUNT_TOTAL);
			psmt.setString(1, cate);
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCountTotal error : " + e.getMessage());
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

	public void insertComment(ArticleDTO dto) {
		try {
			psmt = getConnection().prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertComment error : " + e.getMessage());
		}
	}
	
	public void updateComment(String no, String comment) {
		try {
			psmt = getConnection().prepareStatement(SQL.UPDATE_COMMENT);
			psmt.setString(1, comment);
			psmt.setString(2, no);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("updateComment error : " + e.getMessage());
		}
	}
	
	public void deleteComment(String no) {
		try {
			psmt = getConnection().prepareStatement(SQL.DELETE_COMMENT);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("deleteComment error : " + e.getMessage());
		}
	}

	public List<ArticleDTO> selectComments(String parent) {
		List<ArticleDTO> comments = new ArrayList<ArticleDTO>();
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, parent);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt("no"));
				dto.setParent(rs.getInt("parent"));
				dto.setComment(rs.getInt("comment"));
				dto.setCate(rs.getString("cate"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getInt("file"));
				dto.setHit(rs.getInt("hit"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
				dto.setNick(rs.getString("nick"));
				comments.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectComments error : " + e.getMessage());
		}
		return comments;
	}

	public void plusComment(String no) {
		try {
			psmt = getConnection().prepareStatement(SQL.PLUS_COMMENT);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("plusComment error : " + e.getMessage());
		}
	}
	
	public void minusComment(String no) {
		try {
			psmt = getConnection().prepareStatement(SQL.MINUS_COMMENT);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("plusComment error : " + e.getMessage());
		}
	}
}