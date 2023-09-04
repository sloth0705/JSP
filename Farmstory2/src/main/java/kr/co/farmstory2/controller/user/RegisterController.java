package kr.co.farmstory2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.UserDTO;
import kr.co.farmstory2.service.UserService;

@WebServlet("/user/register.do")
public class RegisterController extends HttpServlet{
	private static final long serialVersionUID = -693117371091469119L;
	private UserService service = UserService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/register.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass1");
		String name = req.getParameter("name");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp = req.getParameter("hp");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String regip = req.getLocalAddr();
		
		UserDTO user = new UserDTO();
		user.setUid(uid);
		user.setPass(pass);
		user.setName(name);
		user.setNick(nick);
		user.setEmail(email);
		user.setHp(hp);
		user.setZip(zip);
		user.setAddr1(addr1);
		user.setAddr2(addr2);
		user.setRegip(regip);
		
		service.insertUser(user);
		resp.sendRedirect("/Farmstory2/user/login.do");
	}
}