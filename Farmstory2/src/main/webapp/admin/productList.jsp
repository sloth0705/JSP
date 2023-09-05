<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<%@ include file="./_aside.jsp" %>
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
                <c:forEach var="product" items="${products}" varStatus="status">
                <tr>
                    <td><input type="checkbox" name=""/></td>
                    <td><img src="/Farmstory2/upload/${product.thumb1 }" class="thumb" alt="샘플1"></td>
                    <td>${product.pNo }</td>
                    <td>${product.pName }</td>
                    <td>
                    	<c:choose>
                    		<c:when test="${product.type eq 1 }">과일</c:when>
                    		<c:when test="${product.type eq 2 }">야채</c:when>
                    		<c:when test="${product.type eq 3 }">곡류</c:when>
                    	</c:choose>
                    </td>
                    <td>${product.getPriceWithComma() }원</td>
                    <td>${product.getStockWithComma() }</td>
                    <td>${product.rdate }</td>
                </tr>
                </c:forEach>
            </table>
            <p>
                <a href="#" class="productDelete">선택삭제</a>
                <a href="/Farmstory2/admin/productRegister.do" class="productRegister">상품등록</a>
            </p>
            <div class="paging">
				<c:if test="${pageGroupStart gt 1 }">
				<a href="/Farmstory2/admin/productList.do?pg=${pageGroupStart - 1 }" class="prev">이전</a>
	            </c:if>
	            <c:forEach var="i" begin="${pageGroupStart }" end="${pageGroupEnd }">
	            <a href="/Farmstory2/admin/productList.do?pg=${i }" class="num ${i == currentPage ? 'current' : ''}">${i }</a>
	            </c:forEach>
	            <c:if test="${pageGroupEnd lt lastPageNum }">
		        <a href="/Farmstory2/admin/productList.do?pg=${pageGroupEnd + 1 }" class="next">다음</a>
	            </c:if>
			</div>
        </article>
    </section>
</main>
<%@ include file="./_footer.jsp" %>