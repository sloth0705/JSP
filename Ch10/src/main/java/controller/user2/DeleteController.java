package controller.user2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User2Service;

@WebServlet("/user2/delete.do")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = -2583553996529441182L;
	
	private User2Service service = new User2Service();

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		service.deleteUser2(uid);
		resp.sendRedirect("/Ch10/user2/list.do");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}