package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/insertComment.do")
public class InsertCommentController extends HttpServlet{
	private static final long serialVersionUID = -6478467559682653565L;
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parent = req.getParameter("parent");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		String regip = req.getRemoteAddr();
		ArticleDTO dto = new ArticleDTO();
		dto.setParent(parent);
		dto.setWriter(writer);
		dto.setContent(content);
		dto.setRegip(regip);
		service.insertComment(dto);
		service.plusComment(parent);
		resp.sendRedirect("/Jboard2/view.do?no=" + parent);
	}
}