<%@page import="kr.co.jboard1.db.SQL"%>
<%@page import="kr.co.jboard1.dao.UserDAO"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String uid = request.getParameter("uid");
	int result = UserDAO.getInstance().checkUser(uid, SQL.SELECT_COUNT_UID);
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	String jsonData = json.toString();
	out.print(jsonData);
%>