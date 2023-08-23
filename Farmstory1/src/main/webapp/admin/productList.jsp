<%@page import="farmstory1.dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="farmstory1.dao.ProductDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String pg = request.getParameter("pg");
	
	// 페이지 관련 변수 선언
	int currentPage = 1;
	int total = 0;
	int lastPageNum = 0;
	int pageGroupCurrent = 1;
	int pageGroupStart = 1;
	int pageGroupEnd = 0;
	int pageStartNum = 0;
	
	// 현재 페이지 계산
	if (pg != null) {
		currentPage = Integer.parseInt(pg);
	}
	
	// Limit 시작값 개선
	int	start = (currentPage - 1) * 10; 
	
	ProductDAO dao = new ProductDAO();
	total = dao.selectCountProductsTotal("0");
	
	if (total % 10 == 0) {
		lastPageNum = total / 10;	
	} else {
		lastPageNum = total / 10 + 1;
	}
	
	// 페이지 그룹 계산
	pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
	pageGroupEnd = pageGroupCurrent * 10;
	
	if (pageGroupEnd > lastPageNum) {
		pageGroupEnd = lastPageNum;
	}
	
	// 페이지 시작번호 계산
	pageStartNum = total - start;
	
	List<ProductDTO> products =  dao.selectProducts(start, "0");
%>
<%@ include file="./_header.jsp"%>
<main>
    <%@ include file="./_aside.jsp"%>
    <section id="productList">
        <nav>
            <h3>상품목록</h3>
        </nav>
        <article>
            <table>
                <tr>
                    <th><input type="checkbox" name="all"/></th>
                    <th>사진</th>
                    <th>상품번호</th>
                    <th>상품명</th>
                    <th>구분</th>
                    <th>가격</th>
                    <th>재고</th>
                    <th>등록일</th>
                </tr>
                <%for(ProductDTO product : products) { %>
                <tr>
                    <td><input type="checkbox" name=""/></td>
                    <td><img src="/Farmstory1/thumb/<%=product.getThumb1() %>" class="thumb"></td>
                    <td><%=product.getpNo() %></td>
                    <td><%=product.getpName() %></td>
                    <td>
                    	<%
                    		switch(product.getType()) {
                    			case 1: out.print("과일"); break;
                    			case 2: out.print("야채"); break;
                    			case 3: out.print("곡물"); break;
                    		}
                    	%>
                    </td>
                    <td><%=product.getPriceWithComma() %>원</td>
                    <td><%=product.getStock() %></td>
                    <td><%=product.getRdate() %></td>
                </tr>
                <%} %>
            </table>
            <p>
                <a href="#" class="productDelete">선택삭제</a>
                <a href="./productRegister.jsp" class="productRegister">상품등록</a>
            </p>
            <div class="paging">
	            <%if(pageGroupStart > 1){ %>
                <a href="/Farmstory1/admin/productList.jsp?pg=<%=pageGroupStart - 1 %>" class="prev"><</a>
		        <%} %>
		        <% for (int i = pageGroupStart; i <= pageGroupEnd; i++) { %>
                <a href="/Farmstory1/admin/productList.jsp?pg=<%=i %>" class="num on"><%=i %></a>
		        <% } %>  
		        <%if(pageGroupEnd < lastPageNum) {%>    
                <a href="/Farmstory1/admin/productList.jsp?pg=<%=pageGroupEnd + 1 %>" class="next">></a>
		        <%} %>
            </div>
        </article>
    </section>
</main>
<%@ include file="./_footer.jsp"%>