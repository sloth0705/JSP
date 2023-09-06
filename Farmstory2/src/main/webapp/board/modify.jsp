<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<c:import url="./_aside${group }.jsp"></c:import>
			<section class="modify">
			    <h3>글수정</h3>
			    <article>
			        <form action="/Farmstory2/board/modify.do" method="post">
			        	<input type="hidden" name="no" value="${article.no }">
			        	<input type="hidden" name="group" value="${group }">
			        	<input type="hidden" name="cate" value="${cate }">
			            <table>
			                <tr>
			                    <td>제목</td>
			                    <td><input type="text" name="title" value="${article.title }"/></td>
			                </tr>
			                <tr>
			                    <td>내용</td>
			                    <td>
			                        <textarea name="content">${article.content }</textarea>                                
			                    </td>
			                </tr>
			                <tr>
			                    <td>첨부파일</td>
					            <td>
					                <a href="/Farmstory2/fileDownload.do?fNo=${file.fNo }">${file.oriName }</a>
					                <span>${file.download }회 다운로드</span>
					            </td>
			                </tr>
			            </table>
			            <div>
			                <a href="#" class="btnCancel">취소</a>
			                <input type="submit"  class="btnWrite" value="수정완료">
			            </div>
			        </form>
			    </article>
			</section>
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp"%>