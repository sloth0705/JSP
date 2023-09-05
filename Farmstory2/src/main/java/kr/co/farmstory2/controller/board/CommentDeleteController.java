package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/commentDelete.do")
public class CommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = -1279266258141398385L;
	private ArticleService aService = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String parent = req.getParameter("parent");
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");

		aService.deleteComment(no);
		aService.minusComment(parent);

		resp.sendRedirect("/Farmstory2/board/view.do?no=" + parent + "&group=" + group + "&cate=" + cate);
	}
}