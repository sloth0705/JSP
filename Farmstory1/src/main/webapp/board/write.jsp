<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	
	// 로그인 여부 확인
	if (sessUser == null) {
		String uri = "board/write.jsp?";
		response.sendRedirect("/Farmstory1/user/login.jsp?success=101&uri=" + uri + "&group=" + group + "&cate=" + cate);
		return;
	}
	
	pageContext.include("./_aside" + group + ".jsp");
%>
			<section class="write">
			    <h3>글쓰기</h3>
			    <article>
			        <form action="/Farmstory1/board/writeProc.jsp" method="post">
			        	<input type="hidden" name="writer" value="<%=sessUser.getUid()%>">
			        	<input type="hidden" name="group" value="<%=group%>">
			        	<input type="hidden" name="cate" value="<%=cate%>">
			            <table>
			                <tr>
			                    <td>제목</td>
			                    <td><input type="text" name="title" placeholder="제목을 입력하세요." required/></td>
			                </tr>
			                <tr>
			                    <td>내용</td>
			                    <td>
			                        <textarea name="content" required></textarea>                                
			                    </td>
			                </tr>
			                <tr>
			                    <td>첨부</td>
			                    <td><input type="file" name="file"/></td>
			                </tr>
			            </table>
			            <div>
			                <a href="#" class="btnCancel">취소</a>
			                <input type="submit"  class="btnWrite" value="작성완료">
			            </div>
			        </form>
			    </article>
			</section>
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp"%>