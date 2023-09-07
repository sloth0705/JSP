<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ include file="./_aside.jsp" %>
        <article class="list">
            <nav>
                <img src="/Farmstory2/images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                <p>
                    HOME > 장보기 > <em>장보기</em>
                </p>
            </nav>
            <!-- 내용 시작 -->
            <p class="sort">
                <a href="/Farmstory2/market/list.do" class="${cate eq null or cate eq '' ? 'on'  : '' }">전체${cate eq null or cate eq '' ? ('(' += total += ')')  : '' } |</a>
                <a href="/Farmstory2/market/list.do?cate=1" class="${cate eq 1 ? 'on'  : '' }">과일${cate eq 1 ? ('(' += total += ')')  : '' } |</a>
                <a href="/Farmstory2/market/list.do?cate=2" class="${cate eq 2 ? 'on'  : '' }">야채${cate eq 2 ? ('(' += total += ')')  : '' } |</a>
                <a href="/Farmstory2/market/list.do?cate=3" class="${cate eq 3 ? 'on'  : '' }">곡류${cate eq 3 ? ('(' += total += ')')  : '' }</a>
            </p>
            <table>
            	<c:forEach var="product" items="${products}" varStatus="status">
                <tr>
                    <td>
                        <a href="/Farmstory2/market/view.do?pNo=${product.pNo }"><img src="/Farmstory2/upload/${product.thumb1 }" alt="썸네일" class="thumb"></a>
                    </td>
                    <td>
                    <c:choose>
                   		<c:when test="${product.type eq 1 }">과일</c:when>
                   		<c:when test="${product.type eq 2 }">야채</c:when>
                   		<c:when test="${product.type eq 3 }">곡류</c:when>
                   	</c:choose>
                    </td>
                    <td><a href="#">${product.pName }</a></td>
                    <td><strong>${product.getPriceWithComma() }</strong>원</td>
                </tr>
                </c:forEach>
            </table>
            <div class="paging">
				<c:if test="${pageGroupStart gt 1 }">
				<a href="/Farmstory2/market/list.do?pg=${pageGroupStart - 1 }" class="prev">이전</a>
	            </c:if>
	            <c:forEach var="i" begin="${pageGroupStart }" end="${pageGroupEnd }">
	            <a href="/Farmstory2/market/list.do?pg=${i }" class="num ${i == currentPage ? 'current' : ''}">${i }</a>
	            </c:forEach>
	            <c:if test="${pageGroupEnd lt lastPageNum }">
		        <a href="/Farmstory2/market/list.do?pg=${pageGroupEnd + 1 }" class="next">다음</a>
	            </c:if>
			</div>
            <!-- 내용 끝 -->
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp" %>