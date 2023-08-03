// 사용자 개인정보 중복체크
$(function() {
	$('#btnCheckUid').click(function(e) {
		const uid = $('input[name=uid]').val();
		if (!uid.match(reUid)) {
			$('.resultId').css('color', 'red').text('유효한 아이디가 아닙니다.');
			isUidOk = false;
			return;
		}
		if (uid == '') {
			$('.resultId').css('color', 'red').text('아이디를 입력해주세요.');
			return;
		}
		const jsonData = {
				"uid" : uid
		};
		$.ajax({
			url : '/Jboard1/user/checkUid.jsp',
			type : 'GET',
			data : jsonData,
			dataType : 'json',
			success : function(data){
				if (data.result > 0) {
					$('.resultId').css('color', 'red').text('이미 사용중인 아이디입니다.');
					isUidOk = false;
				} else {
					$('.resultId').css('color', 'green').text('사용가능한 아이디입니다.');
					isUidOk = true;
				}
			}
		});
	});
	
	// 닉네임 중복체크
	$('input[name=nick]').focusout(function(){
		
		
		// 입력 데이터 가져오기
		const nick = $(this).val();
		
		if (!nick.match(reNick)) {
			$('.resultNick').css('color', 'red').text('유효한 닉네임이 아닙니다.');
			isNickOk = false;
			return;
		} 
		
		// JSON 생성
		const jsonData = {
				"nick" : nick
		};
		
		// 데이터 전송
		$.get('/Jboard1/user/checkNick.jsp', jsonData, function(data) {
			if (data.result >= 1) {
				$('.resultNick:eq(0)').css('color', 'red').text('이미 사용중인 별명 입니다.');
				isNickOk = false;
			} else {
				$('.resultNick:eq(0)').css('color', 'green').text('사용 가능한 별명 입니다.');
				isNickOk = true;
			}
		});
	});
	
	// 이메일 중복체크
	const email = document.getElementsByName('email')[0];
	email.onfocusout = function() {
		// 입력 데이터 가져오기
		const mail = this.value;
		
		if (!mail.match(reEmail)) {
			$('#resultEmail').css('color', 'red').text('유효한 이메일이 아닙니다.');
			isEmailOk = false;
			return;
		} 
		
		console.log(mail);
		
		const xhr = new XMLHttpRequest();
		xhr.open('GET', '/Jboard1/user/checkEmail.jsp?email=' + mail);
		xhr.send();
		
		xhr.onreadystatechange = function() {
			if (xhr.readyState == XMLHttpRequest.DONE) {
				if (xhr.status == 200) {
					const data = JSON.parse(xhr.response);
					console.log(data);
					const resultEmail = document.getElementById('resultEmail');
					if (data.result >= 1) {
						resultEmail.style.color = 'red';
						resultEmail.innerText = '이미 사용중인 이메일 입니다.';
						isEmailOk = false;
    				} else {
    					resultEmail.style.color = 'green';
						resultEmail.innerText = '사용 가능한 이메일 입니다.';
						isEmailOk = true;
    				}
				}
			}
		};
	};
	
	// 휴대폰 중복체크
	document.getElementsByName('hp')[0].addEventListener('focusout', function() {
		url = '/Jboard1/user/checkHp.jsp?hp=' + this.value;
		if (!this.value.match(reHp)) {
			$('#resultHp').css('color', 'red').text('유효한 번호가 아닙니다.');
			isHpOk = false;
			return;
		} 
		fetch(url)
		.then(response => response.json())
		.then(data => {
			console.log(data);
			const resultHp = document.getElementById('resultHp');
			if (data.result >= 1) {
				resultHp.style.color = 'red';
				resultHp.innerText = '이미 사용중인 전화번호 입니다.';
				isHpOk = false;
			} else {
				resultHp.style.color = 'green';
				resultHp.innerText = '사용 가능한 전화번호 입니다.';
				isHpOk = true;
			}
		});
	});
});