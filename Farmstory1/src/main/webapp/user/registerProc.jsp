<%@page import="farmstory1.dao.UserDAO"%>
<%@page import="java.net.InetAddress"%>
<%@page import="farmstory1.dto.UserDTO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String pass1 = request.getParameter("pass1");
	String name = request.getParameter("name");
	String nick = request.getParameter("nick");
	String email = request.getParameter("email");
	String hp = request.getParameter("hp");
	String zip = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String regIp = request.getRemoteAddr();
	if(regIp.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
	    InetAddress inetAddress=InetAddress.getLocalHost();
	    regIp=inetAddress.getHostAddress();
	}
	UserDTO vo = new UserDTO();
	vo.setUid(uid);
	vo.setPass(pass1);
	vo.setName(name);
	vo.setNick(nick);
	vo.setEmail(email);
	vo.setHp(hp);
	vo.setZip(zip);
	vo.setAddr1(addr1);
	vo.setAddr2(addr2);
	vo.setRegip(regIp);
	UserDAO.getInstance().insertUser(vo);
	response.sendRedirect("/Farmstory1/user/login.jsp");
%>