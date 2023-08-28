package kr.co.jboard2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.co.jboard2.service.UserCheckService;

@WebServlet("/user/userCheck.do")
public class UserCheckController extends HttpServlet {
	private static final long serialVersionUID = 6276554598629021076L;
	private UserCheckService service = new UserCheckService();

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		int result = 0;
		if (type.equals("uid")) {
			String uid = req.getParameter("uid");
			result = service.checkUid(uid);
		} else if (type.equals("nick")) {
			String nick = req.getParameter("nick");
			result = service.checkNick(nick);
		} else if (type.equals("hp")) {
			String hp = req.getParameter("hp");
			result = service.checkHp(hp);
		}
		// JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		// JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
}