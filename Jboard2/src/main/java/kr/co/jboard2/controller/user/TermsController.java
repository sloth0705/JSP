package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.dto.TermsDTO;
import kr.co.jboard2.service.TermsService;

@WebServlet("/user/terms.do")
public class TermsController extends HttpServlet{
	private static final long serialVersionUID = 2636729328948502135L;
	private TermsService service = new TermsService();
	
	@Override
	public void init() throws ServletException {

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TermsDTO dto = service.selectTerms();
		req.setAttribute("terms", dto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/terms.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}