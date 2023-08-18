<%@page import="farmstory1.dto.UserDTO"%>
<%@page import="farmstory1.dao.UserDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	String uri = request.getParameter("uri");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String no = request.getParameter("no");
	
	UserDTO user =  UserDAO.getInstance().selectUser(uid, pass);
	if (user != null) {
		session.setAttribute("sessUser", user);
		if (uri.equals("null")) {
			response.sendRedirect("/Farmstory1");
		} else {
			if (no == null) {
				response.sendRedirect("/Farmstory1/" + uri + "group=" + group + "&cate=" + cate);
			} else {
				response.sendRedirect("/Farmstory1/" + uri + "group=" + group + "&cate=" + cate + "&no=" + no);
			}
		}
	} else {
		response.sendRedirect("/Farmstory1/user/login.jsp?success=100");
	}
%>