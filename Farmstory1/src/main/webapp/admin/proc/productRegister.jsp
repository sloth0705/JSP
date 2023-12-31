<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.io.File"%>
<%@page import="java.util.UUID"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="farmstory1.dto.ProductDTO"%>
<%@page import="farmstory1.dao.ProductDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	
	String path = application.getRealPath("/thumb");
	int maxSize = 1024 * 1024 * 10;
	
	// 파일 폼 데이터 수신
	MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	
	String productName = mr.getParameter("productName");
	String type = mr.getParameter("type");
	String price = mr.getParameter("price");
	String delivery = mr.getParameter("delivery");
	String stock = mr.getParameter("stock");
	String thumb1 = mr.getOriginalFileName("thumb1");
	String thumb2 = mr.getOriginalFileName("thumb2");
	String thumb3 = mr.getOriginalFileName("thumb3");
	String seller = mr.getParameter("seller");
	String etc = mr.getParameter("etc");
	
	ProductDTO dto = new ProductDTO(path);
	dto.setpName(productName);
	dto.setType(type);
	dto.setPrice(price);
	dto.setDelivery(delivery);
	dto.setStock(stock);
	dto.setThumb1ForRename(thumb1);
	dto.setThumb2ForRename(thumb2);
	dto.setThumb3ForRename(thumb3);
	dto.setSeller(seller);
	dto.setEtc(etc);
	
	ProductDAO dao = new ProductDAO();
	dao.insertProduct(dto);
	
	response.sendRedirect("/Farmstory1/admin/productRegister.jsp");
%>