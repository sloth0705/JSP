<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String age = request.getParameter("age");
	
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	try {
		Context initCtx = new InitialContext();
		Context ctx = (Context) initCtx.lookup("java:comp/env"); // JNDI 기본 환경이름
		DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
		Connection conn = ds.getConnection();
		PreparedStatement psmt = conn.prepareStatement("UPDATE `user2` SET `name` = ?, `hp` = ?, `age` = ? WHERE `uid` = ?");
		psmt.setString(1, name);
		psmt.setString(2, hp);
		psmt.setString(3, age);
		psmt.setString(4, uid);
		psmt.executeUpdate();
		psmt.close();
		conn.close();
	} catch(Exception e) {
		e.printStackTrace();
	}
	response.sendRedirect("/Ch06/user2/list.jsp");
%>