package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/commentUpdate.do")
public class CommentUpdateController extends HttpServlet {
	private static final long serialVersionUID = -8668927802629222989L;
	private ArticleService aService = ArticleService.INSTANCE;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String parent = req.getParameter("parent");
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String comment = req.getParameter("comment");

		aService.updateComment(no, comment);
		resp.sendRedirect("/Farmstory2/board/view.do?no=" + parent + "&group=" + group + "&cate=" + cate);
	}
}