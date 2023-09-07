<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<c:import url="./_aside${group }.jsp"></c:import>
<script type="text/javascript">
	$(function() {
		$('.btnDelete').click(function() {
			if (!confirm('정말로 삭제 하시겠습니까?')) {
				return false;
			}
		})	
	});
	$(function() {
		// 댓글 내용 전역변수
		let comment = '';
		// 댓글 수정
		$('.mod').click(function(e){
			e.preventDefault();
			
			const txt = $(this).text();
			
			if(txt == '수정'){
				$(this).parent().prev().addClass('modi');
				$(this).parent().prev().attr('readonly', false);
				$(this).parent().prev().focus();
				$(this).text('수정완료');
				$(this).prev().show();
			}else{
				// 수정완료 클릭
				// 수정 데이터 전송
				if (!confirm('정말로 수정하시겠습니까?')) {
					// 수정모드 해제 
					$(this).parent().prev().removeClass('modi');
					$(this).parent().prev().attr('readonly', true);
					$(this).text('수정');
					$(this).prev().hide();
					const prevComment = $(this).parent().parent().children()[4].value;
					$(this).parent().parent().children()[6].value = prevComment;
					return false;
				}
				$(this).closest('form').submit();
			}
		});
		
		$('.can').click(function() {
			const mod = $(this).parent().children()[2];
			mod.parentElement.previousElementSibling.classList.remove('modi')
			mod.parentElement.previousElementSibling.setAttribute('readonly', true)
			mod.innerText = '수정';
			$(this).hide();
			const prevComment = $(this).parent().parent().children()[4].value;
			$(this).parent().parent().children()[6].value = prevComment;
			return false;
		});
		
		$('.del').click(function() {
			return confirm('정말 삭제 하시겠습니까?');
		});
		
		// 댓글쓰기 취소
		// Javascript 방식
		const commentContent = document.querySelector('form > textarea[name=content]');
		const btnCancel = document.querySelector('.btnCancel');
		btnCancel.onclick = function(e){
			e.preventDefault();
			commentContent.value = '';
		}
		
		// jQuery 방식
		$('.btnCancel').click(function(e){
			e.preventDefault();
			$('form > textarea[name=content]').val('');
		});
		
		// 원글 삭제
		const btnDelete = document.getElementsByClassName('btnDelete')[0];
		btnDelete.onclick = function(){
			if(confirm('정말 삭제 하시겠습니까?')){
				return true;
			}else{
				return false;					
			}
		}
		
		$('.btnCancel').click(function() {
			location.reload();
		});
	});
</script>
			<section class="view">
			    <h3>글보기</h3>
			    <table>
			        <tr>
			            <td>제목</td>
			            <td><input type="text" name="title" value="${article.title }" readonly/></td>
			        </tr>
			        <tr>
			            <td>첨부파일</td>
			            <td>
			                <a href="/Farmstory2/fileDownload.do?fNo=${file.fNo }">${file.oriName }</a>
			                <span>${file.download }회 다운로드</span>
			            </td>
			        </tr>
			        <tr>
			            <td>내용</td>
			            <td>
			                <textarea name="content" readonly>${article.content }</textarea>
			            </td>
			        </tr>
			    </table>
			    <div>
			    	<c:if test="${sessUser.uid eq article.writer }">
			        <a href="/Farmstory2/board/proc/deleteProc.jsp?no=${no }&group=${group }&cate=${cate}" class="btnDelete">삭제</a>
			        <a href="/Farmstory2/board/modify.do?no=${no }&group=${group }&cate=${cate}" class="btnModify">수정</a>
			        </c:if>
			        <a href="/Farmstory2/board/list.do?group=${group }&cate=${cate}" class="btnList">목록</a>
			    </div>  
			    <!-- 댓글리스트 -->
				 <section class="commentList">
				     <h3>댓글목록</h3>
				     <c:forEach var="comment" items="${comments}" varStatus="status">
			         <article class="comment">
				         <form action="/Farmstory2/board/commentUpdate.do" method="post">
				         	<input type="hidden" name="no" value="${comment.no }">
				         	<input type="hidden" name="parent" value="${no }">
				         	<input type="hidden" name="group" value="${group }"/>
				         	<input type="hidden" name="cate" value="${cate }"/>
				         	<input type="hidden" name="prevComment" value="${comment.content }">
							<span>
							    <span>${comment.nick }</span>
								<span>${comment.rdate }</span>
							</span>
							<textarea name="comment" readonly>${comment.content }</textarea>
							<c:if test="${comment.writer eq sessUser.uid }">
							<div>
							    <a href="/Farmstory2/board/commentDelete.do?no=${comment.no }&parent=${no }&group=${group }&cate=${cate}" class="del">삭제</a>
							    <a href="#" class="can">취소</a>
							    <a href="#" class="mod">수정</a>
							</div>
							</c:if>
			             </form>
			         </article>
			         </c:forEach>
			         <c:if test="${comments.size() eq 0 }">
			         <p class="empty">
			             등록된 댓글이 없습니다.
			         </p>
			         </c:if>
		 	</section>
			 	<!-- 댓글입력폼 -->
			    <section class="commentForm">
			        <h3>댓글쓰기</h3>
			        <form action="/Farmstory2/board/commentInsert.do" method="post">
			        	<input type="hidden" name="group" value="${group }">
			        	<input type="hidden" name="cate" value="${cate }">
			        	<input type="hidden" name="parent" value="${no }">
			        	<input type="hidden" name="writer" value="${sessUser.uid }">
			            <textarea name="comment"></textarea>
			            <div>
			                <a href="#" class="btnCancel">취소</a>
			                <input type="submit" class="btnWrite" value="작성완료"/>
			            </div>
			        </form>
			    </section>
			</section>
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp"%>