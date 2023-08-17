package farmstory1.dao;

import java.util.ArrayList;
import java.util.List;

import farmstory1.db.DBHelper;
import farmstory1.db.SQL;
import farmstory1.dto.ArticleDTO;

public class ArticleDAO extends DBHelper {
	public void insertArticle(ArticleDTO dto) {
		try {
			psmt = getConnection().prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getCate());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			psmt.executeUpdate();

			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	public List<ArticleDTO> selectArticles(int start, String cate) {
		List<ArticleDTO> articles = new ArrayList<>();
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
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
			e.printStackTrace();
		}
		return result;
	}
}