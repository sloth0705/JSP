<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="./_header.jsp" %>
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
			location.href = '/Jboard2/user/register.do';
		});
	});
</script>
<main id="user">
    <section class="terms">
        <table border="1">
            <caption>사이트 이용약관</caption>
            <tr>
                <td>
                    <textarea name="terms" readonly>${terms.terms }</textarea>
                    <label><input type="checkbox" class="terms" name="chk1">&nbsp;동의합니다.</label>
                </td>
            </tr>
        </table>
        <table border="1">
            <caption>개인정보 취급방침</caption>
            <tr>
                <td>
                    <textarea name="privacy" readonly>${terms.privacy }</textarea>
                    <label><input type="checkbox" class="privacy" name="chk2">&nbsp;동의합니다.</label>
                </td>
            </tr>
        </table>
        <div>
            <a href="/Jboard2/user/login.do" class="btn btnCancel">취소</a>
            <a href="/Jboard2/user/register.do" class="btn btnNext">다음</a>
        </div>
    </section>
</main>
<%@ include file="./_footer.jsp" %>