<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			location.href = '/Farmstory2/user/register.do';
		});
	});
</script>
<div id="user">
	<section class="terms">
	    <table>
	        <caption>사이트 이용약관</caption>
	        <tr>
	            <td>
	                <textarea readonly>${terms.terms }</textarea>
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
	                <textarea readonly>${terms.privacy }</textarea>
	                <p>
	                    <label><input type="checkbox" name="chk2"/>동의합니다.</label>
	                </p>
	            </td>
	        </tr>
	    </table>
	    <div>
	        <a href="/Farmstory2/user/login.do" class="btnCancel">취소</a>
	        <a href="/Farmstory2/user/register.do" class="btnNext">다음</a>
	    </div>
	</section>
</div>
<%@ include file="../_footer.jsp" %>