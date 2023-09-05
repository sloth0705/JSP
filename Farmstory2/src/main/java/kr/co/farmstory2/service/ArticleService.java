package kr.co.farmstory2.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dao.ArticleDAO;
import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;

public enum ArticleService {
	INSTANCE;

	private ArticleDAO dao = ArticleDAO.getInstance();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
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
	public void downloadFile(HttpServletRequest req, HttpServletResponse resp, FileDTO fileDto) throws IOException {
		// response 파일 다운로드 헤더 수정
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileDto.getOriName(), "utf-8"));
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");

		// response 파일 스트림 작업
		String path = getFilePath(req);
		File file = new File(path + "/" + fileDto.getNewName());

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());

		while (true) {
			int data = bis.read();
			if (data == -1) {
				break;
			}
			bos.write(data);
		}
		bos.close();
		bis.close();
		
		dao.downloadFile(fileDto.getfNo());
	}
	
	public void insertComment(ArticleDTO dto) {
		dao.insertComment(dto);
	}
	
	public void updateComment(String no, String comment) {
		dao.updateComment(no, comment);
	}
	
	public void deleteComment(String no) {
		dao.deleteComment(no);
	}
	
	public List<ArticleDTO> selectComments(String parent) {
		return dao.selectComments(parent);
	}
	
	public void plusComment(String no) {
		dao.plusComment(no);
	}
	
	public void minusComment(String no) {
		dao.minusComment(no);
	}
}