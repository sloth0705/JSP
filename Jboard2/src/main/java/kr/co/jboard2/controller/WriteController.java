package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;
import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;

@WebServlet("/write.do")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = -1583953554011146813L;

	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/write.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MultipartRequest mr = aService.uploadFile(req);
		// 폼 데이터 수신
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String oName = mr.getOriginalFileName("file");
		String regip = req.getRemoteAddr();

		ArticleDTO dto = new ArticleDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oName);
		dto.setWriter(writer);
		dto.setRegip(regip);
		int no = aService.insertArticle(dto);
		// 파일명 수정
		if (oName != null && !oName.equals("")) {
			String sName = aService.renameToFile(req, oName);

			// 파일 테이블 Insert
			FileDTO fileDto = new FileDTO();
			fileDto.setAno(no);
			fileDto.setOriName(oName);
			fileDto.setNewName(sName);
			fService.insertFile(fileDto);
		}

		resp.sendRedirect("/Jboard2/list.do");
	}
}