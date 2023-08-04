<%@page import="java.net.InetAddress"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	String pass1 = request.getParameter("pass1");
	String name = request.getParameter("name");
	String nick = request.getParameter("nick");
	String email = request.getParameter("email");
	String hp = request.getParameter("hp");
	String role = request.getParameter("role");
	String zip = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String regIp = request.getRemoteAddr();
	if(regIp.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
	    InetAddress inetAddress=InetAddress.getLocalHost();
	    regIp=inetAddress.getHostAddress();
	}
	
	try {
		Context initCtx = new InitialContext();
		Context ctx = (Context) initCtx.lookup("java:comp/env"); // JNDI 기본 환경이름
		DataSource ds = (DataSource) ctx.lookup("jdbc/Jboard");
		Connection conn = ds.getConnection();
		PreparedStatement psmt = conn.prepareStatement("INSERT INTO `user` VALUES(?, SHA2(?, 256), ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?)");
		psmt.setString(1, uid);
		psmt.setString(2, pass1);
		psmt.setString(3, name);
		psmt.setString(4, nick);
		psmt.setString(5, email);
		psmt.setString(6, hp);
		psmt.setString(7, role);
		psmt.setString(8, zip);
		psmt.setString(9, addr1);
		psmt.setString(10, addr2);
		psmt.setString(11, regIp);
		psmt.setString(12, null);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
	} catch(Exception e) {
		e.printStackTrace();
	}
	response.sendRedirect("/Jboard1/user/login.jsp");
%>