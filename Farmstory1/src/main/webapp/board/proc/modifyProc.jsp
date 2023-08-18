<%@page import="farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	ArticleDAO dao = new ArticleDAO();
	dao.updateArticle(no, title, content);
	
	response.sendRedirect("/Farmstory1/board/view.jsp?no=" + no + "&group=" + group + "&cate=" + cate);
%>