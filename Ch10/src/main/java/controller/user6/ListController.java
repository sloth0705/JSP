package controller.user6;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User6DTO;
import service.User6Service;

@WebServlet("/user6/list.do")
public class ListController extends HttpServlet{
	private static final long serialVersionUID = -654976269535839549L;

	private User6Service service = new User6Service();
	
	@Override
	public void init() throws ServletException {

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User6DTO> users = service.selectUser6s();
		// View에서 users 참조하기 위해 request Scope 저장
		req.setAttribute("users", users);
		RequestDispatcher dispatcher =  req.getRequestDispatcher("/user6/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}