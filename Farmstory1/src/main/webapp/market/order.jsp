<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%	
	if (sessUser == null) {
		response.sendRedirect("/Farmstory1/user/login.jsp?success=101");
	}

	request.setCharacterEncoding("UTF-8");
	String thumb2 = request.getParameter("thumb2");
	String pNo = request.getParameter("pNo");
	String pName = request.getParameter("pName");
	String delivery = request.getParameter("delivery");
	String price = request.getParameter("price");
	String count = request.getParameter("cnt");
	String total = request.getParameter("total");
%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src='/Farmstory1/js/zipcode.js'></script>
<script>
	$(function() {
		const regex = /[^0-9]/g;
		const count = '<%=count%>';
		const delivery = '<%=delivery%>';
		const price = '<%=price%>';
		const total = '<%=total%>';
		$('#btnBuy').click(function(e) {
			e.preventDefault();
			$('input[name=orderCount]').val(count.replace(regex, ""));
			$('input[name=orderDelivery]').val(delivery.replace(regex, ""));
			$('input[name=orderPrice]').val(price.replace(regex, ""));
			$('input[name=orderTotal]').val(total.replace(regex, ""));
			$('#formOrder').submit();
		});
		$('#btnShopping').click(function(e) {
			e.preventDefault();
			if (confirm('주문을 취소하시겠습니까?')) {
				location.href = '/Farmstory1/market/list.jsp';
			}
		});
	});
</script>
<div id="sub">
    <div><img src="/Farmstory1/images/sub_top_tit2.png" alt="MARKET"></div>
    <section class="market">
        <aside>
            <img src="/Farmstory1/images/sub_aside_cate2_tit.png" alt="장보기"/>
            <ul class="lnb">
                <li class="on"><a href="/Farmstory1/market/list.jsp">장보기</a></li>
            </ul>
        </aside>
        <article class="order">
            <nav>
                <img src="/Farmstory1/images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                <p>
                    HOME > 장보기 > <em>장보기</em>
                </p>
            </nav>
            <!-- 내용 시작 -->
            <h3>주문상품 확인</h3>
            <div class="info">
                <img src="/Farmstory1/thumb/<%=thumb2 %>" alt="">
                <table>                            
                    <tr>
                        <td>상품명</td>
                        <td><%=pName %></td>
                    </tr>
                    <tr>
                        <td>상품코드</td>
                        <td><%=pNo %></td>
                    </tr>
                    <tr>
                        <td>배송비</td>
                        <td class="delivery"><%=delivery %></td>
                    </tr>
                    <tr>
                        <td>판매가격</td>
                        <td><%=price %></td>
                    </tr>
                    <tr>
                        <td>구매수량</td>
                        <td class="count"><%=count %>개</td>
                    </tr>
                    <tr>
                        <td>합계</td>
                        <td class="total"><%=total %></td>
                    </tr>
                </table>
            </div>
            <h3>주문정보 입력</h3>
            <div class="shipping">
	            <form id="formOrder" action="/Farmstory1/market/proc/orderProc.jsp" method="post">
	            	<input type="hidden" name="orderProduct" value="<%=pNo%>">
	            	<input type="hidden" name="orderCount">
	            	<input type="hidden" name="orderDelivery">
	            	<input type="hidden" name="orderPrice">
	            	<input type="hidden" name="orderTotal">
	            	<input type="hidden" name="orderUser" value="<%=sessUser.getUid()%>">
	                <table>
	                    <tr>
	                        <td>받는분</td>
	                        <td><input type="text" name="buyer" value="<%=sessUser.getUid()%>"></td>
	                    </tr>
	                    <tr>
	                        <td>휴대폰</td>
	                        <td><input type="text" name="hp" value="<%=sessUser.getHp()%>"></td>
	                    </tr>
	                    <tr>
	                        <td>배송주소</td>
	                        <td>
	                            <input type="text" name="zip" readonly value="<%=sessUser.getZip()%>"><button type="button" id="btnZip" onclick='zipcode()'>우편번호 검색</button>
	                            <input type="text" name="addr1" value="<%=sessUser.getAddr1() %>" placeholder="기본주소 검색">
	                            <input type="text" name="addr2" value="<%=sessUser.getAddr2() %>" placeholder="상세주소 입력">
	                        </td>
	                    </tr>
	                    <tr>
	                        <td>기타</td>
	                        <td>
	                            <textarea name="etc"></textarea>
	                        </td>
	                    </tr>
	                </table>
                </form>
            </div>
            <p>
                <a href="#" id="btnBuy"><img src="/Farmstory1/images/market_btn_buy.gif" alt="구매하기"></a>
                <a href="#" id="btnShopping"><img src="/Farmstory1/images/market_btn_shopping.gif"></a>
            </p>
            <!-- 내용 끝 -->
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp" %>