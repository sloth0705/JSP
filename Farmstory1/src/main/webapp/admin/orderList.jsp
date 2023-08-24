<%@page import="farmstory1.dto.OrderDTO"%>
<%@page import="java.util.List"%>
<%@page import="farmstory1.dao.OrderDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	String pg = request.getParameter("pg");

	OrderDAO dao = new OrderDAO();
	
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
	
	total = dao.selectCountOrdersTotal();
	
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
	List<OrderDTO> orders = dao.selectOrders(start);
%>
<script>
$(function(){
    $('.showPopup').click(function(e){
        e.preventDefault();
        const orderNo = e.target.classList[1];
        $('.' + orderNo).show();
    });
    $('.orderPopup .btnClose').click(function(){
        $('.orderPopup').hide();
    });
    
    $('input[name=all]').change(function(){
		const isChecked = $(this).is(':checked');
		
		if (isChecked) {
			$('input[name=chk]').prop('checked', true);
		} else {
			$('input[name=chk]').prop('checked', false);
		}
	});
    
    $('.orderDelete').click(function(e) {
    	e.preventDefault();
    	$('#formCheck').submit();
    });
});
</script>
<main>
    <%@ include file="./_aside.jsp"%>
    <section id="orderList">
        <nav>
            <h3>주문목록</h3>
        </nav>
        <article>
	        <form id="formCheck" action="/Farmstory1/admin/proc/deleteOrders.jsp" method="get">
	            <table>
	                <tr>
	                    <th><input type="checkbox" name="all"/></th>
	                    <th>주문번호</th>
	                    <th>상품명</th>
	                    <th>판매가격</th>
	                    <th>수량</th>
	                    <th>배송비</th>
	                    <th>합계</th>
	                    <th>주문자</th>
	                    <th>주문일</th>
	                    <th>확인</th>
	                </tr>
	                <%for(OrderDTO order : orders) { %>
	                <tr>
	                    <td class="chk"><input type="checkbox" name="chk" value="<%=order.getOrderNo()%>"/></td>
	                    <td class="orderNo"><%=order.getOrderNo() %></td>
	                    <td class="pName"><%=order.getpName() %></td>                            
	                    <td class="orderPrice"><%=order.getOrderPriceWithComma() %>원</td>
	                    <td class="orderCount"><%=order.getOrderCount() %></td>
	                    <td class="orderDelivery"><%=order.getOrderDeliveryWithComma() %>원</td>
	                    <td class="orderTotal"><%=order.getOrderTotalWithComma() %>원</td>
	                    <td class="name"><%=order.getName() %></td>
	                    <td class="orderDate"><%=order.getOrderDate() %></td>
	                    <td><a href="#" class="showPopup <%=order.getOrderNo()%>">[상세확인]</a></td>
	                </tr>
	                <%} %>
	            </table>
            </form>
            <p>
                <a href="#" class="orderDelete">선택삭제</a>                        
            </p>
            <div class="paging">
	            <%if(pageGroupStart > 1){ %>
                <a href="/Farmstory1/admin/orderList.jsp?pg=<%=pageGroupStart - 1 %>" class="prev"><</a>
		        <%} %>
		        <% for (int i = pageGroupStart; i <= pageGroupEnd; i++) { %>
                <a href="/Farmstory1/admin/orderList.jsp?pg=<%=i %>" class="num on"><%=i %></a>
		        <% } %>  
		        <%if(pageGroupEnd < lastPageNum) {%>    
                <a href="/Farmstory1/admin/orderList.jsp?&pg=<%=pageGroupEnd + 1 %>" class="next">></a>
		        <%} %>
            </div>
        </article>
    </section>
</main>
<%for(OrderDTO order : orders) { %>
<div class="orderPopup <%=order.getOrderNo()%>">
	<section>
	    <nav>
	        <h1>상세주문내역</h1>
	        <button class="btnClose">닫기</button>
	    </nav>
	    <article>  
	        <h3>기본정보</h3>
	        <table>
	            <tr>
	                <td rowspan="7" class="thumb"><img src="/Farmstory1/thumb/<%=order.getThumb1() %>" alt="사과 500g"></td>
	                <td>상품번호</td>
	                <td id="orderNo"><%=order.getOrderProduct() %></td>
	            </tr>
	            <tr>
	                <td>상품명</td>
	                <td id="pName"><%=order.getpName() %></td>
	            </tr>
	            <tr>
	                <td>판매가격</td>
	                <td id="orderPrice"><%=order.getOrderPriceWithComma() %>원</td>
	            </tr>
	            <tr>
	                <td>수량</td>
	                <td id="orderCount"><%=order.getOrderCount() %>개</td>
	            </tr>
	            <tr>
	                <td>배송비</td>
	                <td id="orderDelivery"><%=order.getOrderPriceWithComma() %>원</td>
	            </tr>
	            <tr>
	                <td>합계</td>
	                <td id="orderTotal"><%=order.getOrderTotalWithComma() %>원</td>
	            </tr>
	            <tr>
	                <td>주문자</td>
	                <td id="name"><%=order.getName() %></td>
	            </tr>                        
	        </table>
	        <h3>배송지 정보</h3>
	        <table>
	            <tr>
	                <td>받는분</td>
	                <td><%=order.getReceiver() %></td>
	            </tr>
	            <tr>
	                <td>배송지</td>
	                <td><%=order.getZip() + order.getAddr1() + order.getAddr2() %></td>
	            </tr>
	        </table>
	    </article>
	</section>
</div>
<%} %>
<%@ include file="./_footer.jsp"%>