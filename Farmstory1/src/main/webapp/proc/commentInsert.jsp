<%@page import="java.net.InetAddress"%>
<%@page import="farmstory1.dto.ArticleDTO"%>
<%@page import="farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String parent = request.getParameter("parent");
	String writer = request.getParameter("writer");
	String content = request.getParameter("comment");
	String regip = request.getRemoteAddr();
	if(regip.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
	    InetAddress inetAddress=InetAddress.getLocalHost();
	    regip=inetAddress.getHostAddress();
	}
	
	ArticleDTO dto = new ArticleDTO();
	dto.setParent(Integer.parseInt(parent));
	dto.setWriter(writer);
	dto.setContent(content);
	dto.setRegip(regip);
	
	
	ArticleDAO dao = new ArticleDAO();
	dao.insertComment(dto);
	
	response.sendRedirect("/Farmstory1/board/view.jsp?group=" + group + "&cate=" + cate + "&no=" + parent);
%>