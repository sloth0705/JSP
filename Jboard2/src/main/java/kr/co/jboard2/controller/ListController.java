package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 7787169292569437228L;
	private ArticleService service = ArticleService.INSTANCE;

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		if (sessUser == null) {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		} else {
			String pg = req.getParameter("pg");
			String search = req.getParameter("search");
			
			// 페이지 관련 변수 선언
			int currentPage = 1;
			int total = 0;
			int lastPageNum = 0;
			int pageGroupCurrent = 1;
			int pageGroupStart = 1;
			int pageGroupEnd = 0;
			int pageStartNum = 0;
			
			// 현재 페이지 계산
			if (pg != null) {
				currentPage = Integer.parseInt(pg);
			}
			
			// Limit 시작값 개선
			int	start = (currentPage - 1) * 10; 
			
			
			// 전체 개시물 갯수 조회
			total = service.selectCountTotal(search);
			
			if (total % 10 == 0) {
				lastPageNum = total / 10;	
			} else {
				lastPageNum = total / 10 + 1;
			}
			
			// 페이지 그룹 계산
			pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
			pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
			pageGroupEnd = pageGroupCurrent * 10;
			
			if (pageGroupEnd > lastPageNum) {
				pageGroupEnd = lastPageNum;
			}
			
			// 페이지 시작번호 계산
			pageStartNum = total - start;
			
			// 현재 페이지 게시물 조회
			List<ArticleDTO> articles =  service.selectArticles(start, search);
			req.setAttribute("articles", articles);
			req.setAttribute("pageGroupStart", pageGroupStart);
			req.setAttribute("pageGroupEnd", pageGroupEnd);
			req.setAttribute("pageStartNum", pageStartNum);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("lastPageNum", lastPageNum);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}