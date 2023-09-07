package kr.co.farmstory2.controller.market;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/market/view.do")
public class ViewController extends HttpServlet{
	private static final long serialVersionUID = -3295565614586754536L;
	private ProductService pService = ProductService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pNo = req.getParameter("pNo");
		ProductDTO product = pService.selectProduct(pNo);
		req.setAttribute("product", product);
		req.getRequestDispatcher("/market/view.jsp").forward(req, resp);
	}
}
