<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@page import="kr.co.jboard1.dao.UserDAO"%>
<%@page import="kr.co.jboard1.vo.ArticleVO"%>
<%@page import="java.net.InetAddress"%>
<%@page import="kr.co.jboard1.vo.UserVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String writer = request.getParameter("writer");
	String regIp = request.getRemoteAddr();
	if(regIp.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
	    InetAddress inetAddress=InetAddress.getLocalHost();
	    regIp=inetAddress.getHostAddress();
	}
	ArticleVO vo = new ArticleVO();
	vo.setTitle(title);
	vo.setContent(content);
	vo.setWriter(writer);
	vo.setRegip(regIp);
	ArticleDAO dao = new ArticleDAO();
	dao.insertArticle(vo);
	response.sendRedirect("/Jboard1/list.jsp");
%>