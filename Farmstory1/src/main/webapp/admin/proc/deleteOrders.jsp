<%@page import="farmstory1.dao.OrderDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String[] chks = request.getParameterValues("chk");
	
	OrderDAO dao = new OrderDAO();
	for (String orderNo : chks) {
		dao.deleteOrder(orderNo);
	}
	response.sendRedirect("/Farmstory1/admin/orderList.jsp");
%>