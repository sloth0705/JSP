<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@page import="java.net.InetAddress"%>
<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	int parent = Integer.parseInt(request.getParameter("parent"));
	String content = request.getParameter("comment");
	String writer = request.getParameter("writer");
	String regip = request.getRemoteAddr();
	if(regip.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
	    InetAddress inetAddress=InetAddress.getLocalHost();
	    regip=inetAddress.getHostAddress();
	}
	ArticleDTO dto = new ArticleDTO();
	dto.setParent(parent);
	dto.setContent(content);
	dto.setWriter(writer);
	dto.setRegip(regip);
	
	ArticleDAO dao = new ArticleDAO();
	// 댓글 작성
	dao.insertComment(dto);
	// 댓글 수 증가
	dao.updateArticleForComment(parent);
	response.sendRedirect("/Jboard1/view.jsp?no=" + parent);
%>