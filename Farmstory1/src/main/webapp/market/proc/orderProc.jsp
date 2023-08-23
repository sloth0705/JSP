<%@page import="farmstory1.dao.OrderDAO"%>
<%@page import="farmstory1.dto.OrderDTO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String orderUser = request.getParameter("orderUser");
	String buyer = request.getParameter("buyer");
	String hp = request.getParameter("hp");
	String zip = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String orderEtc = request.getParameter("etc");
	String orderProduct = request.getParameter("orderProduct");
	String orderCount = request.getParameter("orderCount");
	String orderDelivery = request.getParameter("orderDelivery");
	String orderPrice = request.getParameter("orderPrice");
	String orderTotal = request.getParameter("orderTotal");
	
	OrderDTO dto = new OrderDTO();
	dto.setOrderProduct(Integer.parseInt(orderProduct));
	dto.setOrderCount(Integer.parseInt(orderCount));
	dto.setOrderDelivery(Integer.parseInt(orderDelivery));
	dto.setOrderPrice(Integer.parseInt(orderPrice));
	dto.setOrderTotal(Integer.parseInt(orderTotal));
	dto.setReceiver(buyer);
	dto.setHp(hp);
	dto.setZip(zip);
	dto.setAddr1(addr1);
	dto.setAddr2(addr2);
	dto.setOrderEtc(orderEtc);
	dto.setOrderUser(orderUser);
	
	OrderDAO dao = new OrderDAO();
	dao.insertOrder(dto);
	
	response.sendRedirect("/Farmstory1/market/list.jsp");
%>