package controller.user5;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User4Service;

@WebServlet("/user4/delete.do")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = -2583553996529441182L;

	private User4Service service = new User4Service();

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq = req.getParameter("seq");
		service.deleteUser4(seq);
		resp.sendRedirect("/Ch10/user4/list.do");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}