<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<c:import url="./_asideCroptalk.jsp"></c:import>
            <!-- 내용 시작 -->
			<section class="list">
			    <h3>글목록</h3>
			    <article>
			        <table>
			            <tr>
			                <th>번호</th>
			                <th>제목</th>
			                <th>글쓴이</th>
			                <th>날짜</th>
			                <th>조회</th>
			            </tr>
			            <c:forEach var="article" items="${articles}" varStatus="status">
		                <tr>
		                    <td>${pageStartNum - status.index}</td>
		                    <td><a href="/Farmstory2/board/view.do?no=&group=${group }&cate=${cate}">${article.title }</a>&nbsp;[${article.hit }]</td>
			                <td>${article.nick}</td>
			                <td>${article.rdate }</td>
			                <td>${article.hit }</td>
		                </tr>
		                </c:forEach>
			        </table>
			    </article>
			    <!-- 페이지 네비게이션 -->
				<div class="paging">
					<c:if test="${pageGroupStart gt 1 }">
					<a href="/Farmstory2/board/list.do?pg=${pageGroupStart - 1 }&group=${group }&cate=${cate}" class="prev">이전</a>
		            </c:if>
		            <c:forEach var="i" begin="${pageGroupStart }" end="${pageGroupEnd }">
		            <a href="/Farmstory2/board/list.do?pg=${i }&group=${group }&cate=${cate}" class="num ${i == currentPage ? 'current' : ''}">${i }</a>
		            </c:forEach>
		            <c:if test="${pageGroupEnd lt lastPageNum }">
			        <a href="/Farmstory2/board/list.do?pg=${pageGroupEnd + 1 }&group=${group }&cate=${cate}" class="next">다음</a>
		            </c:if>
				</div>
				<!-- 글쓰기 버튼 -->
			<a href="/Farmstory2/board/write.do?group=${group }&cate=${cate}" class="btnWrite">글쓰기</a>
			</section>
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp"%>