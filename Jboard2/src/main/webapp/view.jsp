<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="./_header.jsp" %>
<script>
	$(function() {
		$('#btnComment').click(function(e) {
			e.preventDefault();
			const content = $('#formComment > textarea[name=content]').val();
			const parent = $('#formComment > input[name=parent]').val();
			const writer = $('#formComment > input[name=writer]').val();
			const jsonData = {
					"content" : content,
					"parent" : parent,
					"writer" : writer
			}
			
			$.ajax({
				url: '/Jboard2/insertComment.do',
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data) {
					if (data.result > 0) {
						alert('댓글이 등록되었습니다.');
					} else {
						alert('댓글이 등록이 실패했습니다.');
					}
				}
			});
		});
	});
</script>
<main id="board">
    <section class="view">
        <table>
            <caption>글보기</caption>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" value="${article.title }" readonly/></td>
            </tr>
            <c:if test="${article.file gt 0}">
	            <tr>
	                <th>파일</th>
	                <td><a href="/Jboard2/fileDownload.do?fno=${article.fileDto.fno }">${article.fileDto.oriName }</a>&nbsp;<span>${article.fileDto.download }</span>회 다운로드</td>
	            </tr>
            </c:if>
            <tr>
                <th>내용</th>
                <td>
                    <textarea name="content" readonly>${article.content }</textarea>
                </td>
            </tr>                    
        </table>
        <div>
            <a href="/Jboard2/delete.do?no=${article.no }" class="btn btnRemove">삭제</a>
            <a href="./modify.jsp" class="btn btnModify">수정</a>
            <a href="./list.jsp" class="btn btnList">목록</a>
        </div>
        <!-- 댓글목록 -->
		<section class="commentList">
		    <h3>댓글목록</h3>                   
	        <c:forEach var="comment" items="${comments}">
		    <article>
		        <span class="nick">${comment.nick }</span>
		        <span class="date">${comment.rdate }</span>
		        <p class="content">${comment.content }</p>                        
		        <div>
		            <a href="#" class="remove">삭제</a>
		            <a href="#" class="modify">수정</a>
		        </div>
		    </article>
		    </c:forEach>
		    <c:if test="${comments.size() == 0 }">
		    	<p class="empty">등록된 댓글이 없습니다.</p>
		    </c:if>
		</section>
		<!-- 댓글쓰기 -->
        <section class="commentForm">
            <h3>댓글쓰기</h3>
            <form id="formComment" action="#" method="post">
            	<input type="hidden" name="parent" value="${article.no }">
            	<input type="hidden" name="writer" value="${sessionScope.sessUser.uid }">
                <textarea name="content"></textarea>
                <div>
                    <a href="#" class="btn btnCancel">취소</a>
                    <input type="submit" id="btnComment" value="작성완료" class="btn btnComplete"/>
                </div>
            </form>
        </section>
    </section>
</main>
<%@ include file="./_footer.jsp" %>