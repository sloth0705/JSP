package kr.co.farmstory2.controller.market;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/market/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 8543347364327031519L;
	private ProductService pService = ProductService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pg = req.getParameter("pg");
		String cate = req.getParameter("cate");

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
		int start = (currentPage - 1) * 10;

		// 전체 개시물 갯수 조회
		total = pService.selectCountTotal(cate);

		if (total % 10 == 0) {
			lastPageNum = total / 10;
		} else {
			lastPageNum = total / 10 + 1;
		}

		// 페이지 그룹 계산
		pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);
		pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
		pageGroupEnd = pageGroupCurrent * 10;

		if (pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}

		// 페이지 시작번호 계산
		pageStartNum = total - start;

		// 현재 페이지 게시물 조회
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		if (cate != null && !cate.equals("")) {
			products = pService.selectProducts(start, cate);
		} else {
			products = pService.selectProducts(start);
		}

		req.setAttribute("pg", pg); 
		req.setAttribute("cate", cate); 
		req.setAttribute("total", total); 
		req.setAttribute("pageGroupStart", pageGroupStart);
		req.setAttribute("pageGroupEnd", pageGroupEnd);
		req.setAttribute("pageStartNum", pageStartNum);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("products", products);
		req.getRequestDispatcher("/market/list.jsp").forward(req, resp);
	}
}