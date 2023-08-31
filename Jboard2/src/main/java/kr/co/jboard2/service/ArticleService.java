package kr.co.jboard2.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.dto.ArticleDTO;

public enum ArticleService {
	INSTANCE;

	private ArticleDAO dao = ArticleDAO.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}

	public ArticleDTO selectArticle(int no) {
		return dao.selectArticle(no);
	}

	public List<ArticleDTO> selectArticles() {
		return dao.selectArticles();
	}

	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}

	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}

	// 파일 업로드 경로 구하기
	public String getFilePath(HttpServletRequest req) {
		ServletContext ctx = req.getServletContext();
		return ctx.getRealPath("/upload");
	}

	// 파일명 수정
	public String renameToFile(HttpServletRequest req, String oName) {
		String path = getFilePath(req);
		int idx = oName.lastIndexOf(".");
		String ext = oName.substring(idx);
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;

		File f1 = new File(path + "/" + oName);
		File f2 = new File(path + "/" + sName);

		// 파일명 수정
		f1.renameTo(f2);
		return sName;
	}

	// 파일 업로드
	public MultipartRequest uploadFile(HttpServletRequest req) {
		String path = getFilePath(req);
		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;
		MultipartRequest mr = null;
		// 파일 업로드
		try {
			mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			logger.error("uploadFile error : " + e.getMessage());
		}
		return mr;
	}

	// 파일 다운로드
	public void downloadFile() {

	}
}