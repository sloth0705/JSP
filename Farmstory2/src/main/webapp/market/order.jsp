<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="./_aside.jsp" %>
<script>
	$(function() {
		const pNo = ${product.pNo};
		const price = ${product.price};
		let delivery = ${product.delivery};
		const count = ${count};
		const total = price * count;
		if (total < 30000) {
			total += delivery;
			$('.delivery').text(delivery.toLocaleString() + "원");
		} else {
			$('.delivery').text("무료");
			delivery = 0;
		}
		$('.total').text(total.toLocaleString() + "원");
   		$('input[name=orderProduct]').val(pNo);
   		$('input[name=orderCount]').val(count);
   		$('input[name=orderDelivery]').val(delivery);
   		$('input[name=orderPrice]').val(price);
   		$('input[name=orderTotal]').val(total);
		
		
		$('#btnBuy').click(function(e) {
			e.preventDefault();
			if (confirm('상품을 구매하시겠습니까?')) {
				$('#orderForm').submit();
			}
		});
	});
</script>
        <article class="order">
            <nav>
                <img src="../images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                <p>
                    HOME > 장보기 > <em>장보기</em>
                </p>
            </nav>
            <!-- 내용 시작 -->
            <h3>주문상품 확인</h3>
            <div class="info">
                <img src="/Farmstory2/upload/${product.thumb2 }" alt="썸네일">
                <table>                            
                    <tr>
                        <td>상품명</td>
                        <td>${product.pName }</td>
                    </tr>
                    <tr>
                        <td>상품코드</td>
                        <td>${product.pNo }</td>
                    </tr>
                    <tr>
                        <td>배송비</td>
                        <td class="delivery"></td>
                    </tr>
                    <tr>
                        <td>판매가격</td>
                        <td>${product.getPriceWithComma() }원</td>
                    </tr>
                    <tr>
                        <td>구매수량</td>
                        <td class="count">${count }개</td>
                    </tr>
                    <tr>
                        <td>합계</td>
                        <td class="total"></td>
                    </tr>
                </table>
            </div>
            <h3>주문정보 입력</h3>
            <div class="shipping">
           		<form id="orderForm" action="/Farmstory2/market/order.do" method="post">
           			<input type="hidden" name="orderProduct"/>
           			<input type="hidden" name="orderCount"/>
           			<input type="hidden" name="orderDelivery"/>
           			<input type="hidden" name="orderPrice"/>
           			<input type="hidden" name="orderTotal"/>
           			<input type="hidden" name="orderUser" value="${sessUser.uid }"/>
					<table>
					    <tr>
					        <td>받는분</td>
					        <td><input type="text" name="receiver"></td>
					    </tr>
					    <tr>
					        <td>휴대폰</td>
					        <td><input type="text" name="hp"></td>
					    </tr>
					    <tr>
					        <td>배송주소</td>
					        <td>
					            <input type="text" name="zip" readonly>
					            <button type="button" id="btnZip"class="btnZip" onclick='zipcode()'>우편번호 검색</button>
					            <input type="text" name="addr1" placeholder="기본주소 검색">
					            <input type="text" name="addr2" placeholder="상세주소 입력">
					        </td>
					    </tr>
					    <tr>
					        <td>기타</td>
					        <td>
					            <textarea name="orderEtc"></textarea>
					        </td>
					    </tr>
					</table>
                </form>
            </div>
            <p>
                <a href="#" id="btnBuy"><img src="../images/market_btn_buy.gif" alt="구매하기"></a>
                <a href="/Farmstory2/market/list.do" id="btnShopping"><img src="../images/market_btn_shopping.gif"></a>
            </p>
            <!-- 내용 끝 -->
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp" %>