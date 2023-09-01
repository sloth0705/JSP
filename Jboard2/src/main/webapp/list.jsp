<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="./_header.jsp" %>
<main id="board">
    <section class="list">                
        <form action="/Jboard2/list.do" method="get">
            <input type="text" name="search" placeholder="제목 키워드 검색">
            <input type="submit" value="검색">
        </form>
        <table>
            <caption>글목록</caption>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>날짜</th>
                <th>조회</th>
            </tr>
            <c:forEach var="article" items="${articles}">
            <tr>
              	<td>${pageStartNum = pageStartNum - 1}</td>
                <td><a href="/Jboard2/view.do?no=${article.no }">${article.title }</a>&nbsp;[${article.comment}]</td>
                <td>${article.nick}</td>
                <td>${article.rdate }</td>
                <td>${article.hit }</td>
            </tr>
            </c:forEach>
        </table>
        <div class="page">
        	<c:if test="${pageGroupStart gt 1 }">
            <a href="/Jboard2/list.do?pg=${pageGroupStart - 1 }" class="prev">이전</a>
            </c:if>
            <c:forEach var="i" begin="${pageGroupStart }" end="${pageGroupEnd }">
            <a href="/Jboard2/list.do?pg=${i }" class="num ${i == currentPage ? "current" : ""}">${i }</a>
            </c:forEach>
            <c:if test="${pageGroupEnd lt lastPageNum }">
            <a href="/Jboard2/list.do?pg=${pageGroupEnd + 1 }" class="next">다음</a>
            </c:if>
        </div>
        <a href="/Jboard2/write.do" class="btn btnWrite">글쓰기</a>
    </section>
</main>
<%@ include file="./_footer.jsp" %>