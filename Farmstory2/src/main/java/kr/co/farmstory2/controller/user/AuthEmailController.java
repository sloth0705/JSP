package kr.co.farmstory2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.farmstory2.service.UserService;

@WebServlet("/user/authEmail.do")
public class AuthEmailController extends HttpServlet {
	private static final long serialVersionUID = 9094836002104883300L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.INSTANCE;

	@Override
	public void init() throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		int code = service.sendCodeByEmail(email);
		logger.info("code : " + code);
		JsonObject json = new JsonObject();
		json.addProperty("code", code);

		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		int result = service.checkCode(code);
		JsonObject json = new JsonObject();
		json.addProperty("result", result);

		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
}