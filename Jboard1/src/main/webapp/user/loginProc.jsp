<%@page import="kr.co.jboard1.dao.UserDAO"%>
<%@page import="kr.co.jboard1.dto.UserDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	UserDTO user = UserDAO.getInstance().selectUser(uid, pass);
	if (user != null) {
		// 사용자 세션 저장
		session.setAttribute("sessUser", user);
		response.sendRedirect("/Jboard1/list.jsp");
	} else {
		response.sendRedirect("/Jboard1/user/login.jsp?success=100");
	}
%>