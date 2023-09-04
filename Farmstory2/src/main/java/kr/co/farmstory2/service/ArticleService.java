package kr.co.farmstory2.service;

import java.util.List;

import kr.co.farmstory2.dao.ArticleDAO;
import kr.co.farmstory2.dto.ArticleDTO;

public enum ArticleService {
	INSTANCE;

	private ArticleDAO dao = ArticleDAO.getInstance();

	public void insertArticle(ArticleDTO dto) {
		dao.insertArticle(dto);
	}

	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}

	public List<ArticleDTO> selectArticles(int start, String cate) {
		return dao.selectArticles(start, cate);
	}

	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}

	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}

	public int selectCountTotal(String cate) {
		return dao.selectCountTotal(cate);
	}
}