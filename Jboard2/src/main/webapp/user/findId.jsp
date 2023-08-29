<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
	let isAuthOk = false;
	$(function() {
		let flag = true;
		$('.btnAuth').click(function() {
			if (!flag) {
				return;
			}
			const name = $('input[name=name]').val();
			const email = $('input[name=email]').val();
			const jsonData = {
				"name":name,
				"email":email,
				"status":"findId"
			};
			$('#authMail').text('이메일을 확인 중입니다.');
			$.ajax({
				url: '/Jboard2/user/findId.do',
				type: 'POST',
				data: jsonData,
				dataType: 'json',
				success: function(data) {
					if (data.result > 0) {
						$('#authMail').css('color','green').text('이메일을 확인 후 인증코드를 입력하세요.');
						$('input[name=auth]').attr('disabled', false);
						flag = false;
					} else {
						$('#authMail').css('color','red').text('해당하는 아이디가 존재하지 않습니다. 다시 확인해 주십시오.');
						flag = true;
					}
				}
			});	
		});
		
		$('.btnConfirm').click(function() {
			const auth = $('input[name=auth]').val();
			const jsonData = {
				"code":auth
			};
			$.ajax({
				url: '/Jboard2/user/findId.do',
				type: 'POST',
				data: jsonData,
				dataType: 'json',
				success: function(data) {
					if (data.result > 0) {
						$('#authCode').text('인증이 완료되었습니다. 확인 버튼을 눌러주세요');
						isAuthOk = true;
					} else {
						$('#authCode').text('인증번호가 맞지않습니다.');
					}
				}
			});	
		});
		
		$('.btnNext').click(function(e) {
			e.preventDefault();
			if (!isAuthOk) {
				alert('인증을 완료해 주십시오.');
				return false;
			} else {
				const name = $('input[name=name]').val();
				const email = $('input[name=email]').val();
				location.href = '/Jboard2/user/findIdResult.do?name=' + name + '&email=' + email;
			}
		});
	});
</script>
<main id="user">
    <section class="find findId">
        <form action="/Jboard2/user/findId.do" method="post">
            <table>
                <caption>아이디 찾기</caption>
                <tr>
                    <td>이름</td>
                    <td><input type="text" name="name" placeholder="이름 입력"/></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td>
                        <div>
                            <input type="email" name="email" placeholder="이메일 입력"/>
                            <button type="button" class="btnAuth">인증번호 받기</button>
                            <span id="authMail"></span>
                        </div>
                        <div>
                            <input type="text" name="auth" disabled placeholder="인증번호 입력"/>
                            <button type="button" class="btnConfirm">확인</button>
                            <span id="authCode"></span>
                        </div>
                    </td>
                </tr>                        
            </table>                                        
        </form>
        <p>
            회원가입시 이메일 주소와 입력한 이메일 주소가 같아야, 인증번호를 받을 수 있습니다.<br>
            인증번호를 입력 후 확인 버튼을 누르세요.
        </p>
        <div>
            <a href="/Jboard2/user/login.do" class="btn btnCancel">취소</a>
            <a href="#" class="btn btnNext">다음</a>
        </div>
    </section>
</main>
<%@ include file="./_footer.jsp" %>