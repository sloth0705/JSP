<%@page import="java.util.List"%>
<%@page import="farmstory1.dto.ArticleDTO"%>
<%@page import="farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String no = request.getParameter("no");
	
	// 로그인 여부 확인
	if (sessUser == null) {
		String uri = "board/view.jsp?";
		response.sendRedirect("/Farmstory1/user/login.jsp?success=101&uri=" + uri + "&group=" + group + "&cate=" + cate + "&no=" + no);
		return;
	}
	
	ArticleDAO dao = new ArticleDAO();
	ArticleDTO dto =  dao.selectArticle(no);
	List<ArticleDTO> comments = dao.selectComments(no);
	pageContext.include("./_aside" + group + ".jsp");
%>
<script type="text/javascript">
	$(function() {
		$('.btnDelete').click(function() {
			if (!confirm('정말로 삭제 하시겠습니까?')) {
				return false;
			}
		})	
	});
</script>
			<section class="view">
			    <h3>글보기</h3>
			    <table>
			        <tr>
			            <td>제목</td>
			            <td><input type="text" name="title" value="<%=dto.getTitle()%>" readonly/></td>
			        </tr>
			        <tr>
			            <td>첨부파일</td>
			            <td>
			                <a href="#">2020년 상반기 매출자료.xls</a>
			                <span>7회 다운로드</span>
			            </td>
			        </tr>
			        <tr>
			            <td>내용</td>
			            <td>
			                <textarea name="content" readonly><%=dto.getContent() %></textarea>
			            </td>
			        </tr>
			    </table>
			    <div>
			        <a href="/Farmstory1/board/proc/deleteProc.jsp?no=<%=no %>&group=<%=group %>&cate=<%=cate %>" class="btnDelete">삭제</a>
			        <a href="/Farmstory1/board/modify.jsp?no=<%=no %>&group=<%=group %>&cate=<%=cate %>" class="btnModify">수정</a>
			        <a href="#" class="btnList">목록</a>
			    </div>  
			    
			    <!-- 댓글리스트 -->
				 <section class="commentList">
				     <h3>댓글목록</h3>
				     <% for(ArticleDTO comment : comments) { %>
			         <article class="comment">
				         <form action="/Farmstory1/proc/commentUpdate.jsp" method="post">
				         	<input type="hidden" name="no" value="<%=comment.getNo()%>">
				         	<input type="hidden" name="parent" value="<%=no%>">
				         	<input type="hidden" name="prevComment" value="<%=comment.getContent() %>"/>
							<span>
							    <span><%=comment.getNick() %></span>
								<span><%=comment.getRdate() %></span>
							</span>
							<textarea name="comment" readonly><%= comment.getContent() %></textarea>
							<%if (comment.getWriter().equals(sessUser.getUid())) { %>
							<div>
							    <a href="/Farmstory1/proc/commentDelete.jsp?no=<%=comment.getNo() %>&parent=<%=no %>" class="del">삭제</a>
							    <a href="#" class="can">취소</a>
							    <a href="#" class="mod">수정</a>
							</div>
							<%} %>
			             </form>
			         </article>
			         <%} %>
			         <%if (comments.isEmpty()){ %>
			         <p class="empty">
			             등록된 댓글이 없습니다.
			         </p>
			         <%} %>
			 </section>
			 	<!-- 댓글입력폼 -->
			    <section class="commentForm">
			        <h3>댓글쓰기</h3>
			        <form action="/Farmstory1/proc/commentInsert.jsp" method="post">
			        	<input type="hidden" name="group" value="<%=group%>">
			        	<input type="hidden" name="cate" value="<%=cate%>">
			        	<input type="hidden" name="parent" value="<%=no%>">
			        	<input type="hidden" name="writer" value="<%=sessUser.getUid()%>">
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