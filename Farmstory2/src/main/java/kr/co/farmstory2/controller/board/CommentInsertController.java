package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/commentInsert.do")
public class CommentInsertController extends HttpServlet{
	private static final long serialVersionUID = 8484051335166755139L;
	private ArticleService aService = ArticleService.INSTANCE;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String parent = req.getParameter("parent");
		String writer = req.getParameter("writer");
		String content = req.getParameter("comment");
		String regip = req.getRemoteAddr();
		
		ArticleDTO comment = new ArticleDTO();
		comment.setCate(cate);
		comment.setParent(parent);
		comment.setWriter(writer);
		comment.setContent(content);
		comment.setRegip(regip);
		aService.insertComment(comment);
		aService.plusComment(parent);
		resp.sendRedirect("/Farmstory2/board/view.do?no=" + parent + "&group=" + group + "&cate=" + cate);
	}
}