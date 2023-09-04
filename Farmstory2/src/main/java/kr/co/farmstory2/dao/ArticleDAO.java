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

	public void insertArticle(ArticleDTO dto) {

	}

	public ArticleDTO selectArticle(String no) {
		ArticleDTO dto = null;
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
}