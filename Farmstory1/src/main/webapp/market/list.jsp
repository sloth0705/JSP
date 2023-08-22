<%@page import="farmstory1.dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="farmstory1.dao.ProductDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String pg = request.getParameter("pg");
	type = type == null ? "0" : type;	
	
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
	total = dao.selectCountProductsTotal(type);
	
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
	
	List<ProductDTO> products =  dao.selectProducts(start, type);
%>
<%@ include file="../_header.jsp" %>
<div id="sub">
    <div><img src="/Farmstory1/images/sub_top_tit2.png" alt="MARKET"></div>
    <section class="market">
        <aside>
            <img src="/Farmstory1/images/sub_aside_cate2_tit.png" alt="장보기"/>

            <ul class="lnb">
                <li class="on"><a href="/Farmstory1/market/list.jsp">장보기</a></li>
            </ul>
        </aside>
        <article class="list">
            <nav>
                <img src="/Farmstory1/images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                <p>
                    HOME > 장보기 > <em>장보기</em>
                </p>
            </nav>
            <!-- 내용 시작 -->
            <p class="sort">
                <a href="/Farmstory1/market/list.jsp?type=0" class="<%=type.equals("0") ? "on" : "" %>">전체<%=type.equals("0") ? "(" + total + ")" : "" %> |</a>
                <a href="/Farmstory1/market/list.jsp?type=1" class="<%=type.equals("1") ? "on" : "" %>">과일<%=type.equals("1") ? "(" + total + ")" : "" %> |</a>
                <a href="/Farmstory1/market/list.jsp?type=2" class="<%=type.equals("2") ? "on" : "" %>">야채<%=type.equals("2") ? "(" + total + ")" : "" %> |</a>
                <a href="/Farmstory1/market/list.jsp?type=3" class="<%=type.equals("3") ? "on" : "" %>">곡류<%=type.equals("3") ? "(" + total + ")" : "" %></a>
            </p>
            <table>
            	<%for(ProductDTO product : products) { %>
                <tr>
                    <td>
                        <a href="/Farmstory1/market/view.jsp?pNo=<%=product.getpNo()%>">
                        	<img src="/Farmstory1/thumb/<%=product.getThumb1() %>" class="thumb">
                        </a>
                    </td>
                    <td>
                    	<%
                    		switch(product.getType()) {
                    			case 1: out.print("과일"); break;
                    			case 2: out.print("야채"); break;
                    			case 3: out.print("곡물"); break;
                    		}
                    	%>
                    </td>
                    <td>
	                    <a href="/Farmstory1/market/view.jsp?pNo=<%=product.getpNo()%>">
	                    	<%=product.getpName()%>
	                    </a>
                    </td>
                    <td><strong><%=product.getPriceWithComma()%></strong>원</td>
                </tr>
                <%} %>
            </table>
            <div class="paging">
	            <%if(pageGroupStart > 1){ %>
                <a href="/Farmstory1/market/list.jsp?type=<%=type %>&pg=<%=pageGroupStart - 1 %>" class="prev"><</a>
		        <%} %>
		        <% for (int i = pageGroupStart; i <= pageGroupEnd; i++) { %>
                <a href="/Farmstory1/market/list.jsp?type=<%=type %>&pg=<%=i %>" class="num on"><%=i %></a>
		        <% } %>  
		        <%if(pageGroupEnd < lastPageNum) {%>    
                <a href="/Farmstory1/market/list.jsp?type=<%=type %>&pg=<%=pageGroupEnd + 1 %>" class="next">></a>
		        <%} %>
            </div>
            <!-- 내용 끝 -->
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp" %>