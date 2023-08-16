<%@page import="farmstory1.dao.UserDAO"%>
<%@page import="farmstory1.dto.TermsDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	TermsDTO terms = UserDAO.getInstance().termsDTO();
%>
<%@ include file="../_header.jsp" %>
<script>
	$(function() {
		const chk1 = $('input[name=chk1]')[0];
		const chk2 = $('input[name=chk2]')[0];
		$('.btnNext').eq(0).click(function(e) {
			e.preventDefault();
			if (!chk1.checked) {
				alert('사이트 이용약관에 동의해주세요.');
				return;
			}
			if (!chk2.checked) {
				alert('개인정보 취급방침에 동의해주세요.');
				return;
			}
			location.href = '/Farmstory1/user/register.jsp';
		});
	});
</script>
<div id="user">
	<section class="terms">
	    <table>
	        <caption>사이트 이용약관</caption>
	        <tr>
	            <td>
	                <textarea readonly><%=terms.getTerms() %></textarea>
	                <p>
	                    <label><input type="checkbox" name="chk1"/>동의합니다.</label>
	                </p>
	            </td>
	        </tr>
	    </table>
	    <table>
	        <caption>개인정보 취급방침</caption>
	        <tr>
	            <td>
	                <textarea readonly><%=terms.getPrivacy() %></textarea>
	                <p>
	                    <label><input type="checkbox" name="chk2"/>동의합니다.</label>
	                </p>
	            </td>
	        </tr>
	    </table>
	    <div>
	        <a href="/Farmstory1/user/login.jsp" class="btnCancel">취소</a>
	        <a href="#" class="btnNext">다음</a>
	    </div>
	</section>
</div>
<%@ include file="../_footer.jsp" %>