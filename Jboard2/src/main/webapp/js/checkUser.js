// 폼 데이터 검증결과 상태변수
let isUidOk = false;
let isPassOk = false;
let isNameOk = false;
let isNickOk = false;
let isEmailOk = false;
let isHpOk = false;

// 데이터 검증에 사용하는 정규표현식
let reUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
let rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
let reName  = /^[가-힣]{2,10}$/ 
let reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
let reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

let flag = true;

// 사용자 개인정보 중복체크
$(function() {
	// 이메일 인증코드
	let receivedcode = 0;
	let time = 0;
	
	// 비밀번호 검사
	$('input[name=pass2]').focusout(function() {
		const pass1 = $('input[name=pass1]').val();
		const pass2 = $('input[name=pass2]').val();
		if (pass1 == pass2) {
			if (pass1.match(rePass)) {
				$('.resultPass').css('color', 'green').text('사용 가능한 비밀번호 입니다.');
				isPassOk = true;
			} else {
				$('.resultPass').css('color', 'red').text('비밀번호는 숫자, 영문, 특수문자 조합 5자리 이상이어야 합니다.');
				isPassOk = false;
			}
		} else {
			$('.resultPass').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
			isPassOk = false;
		}
	});
	
	// 이름 검사
	$('input[name=name]').focusout(function() {
		const name = $(this).val();
		if (!name.match(reName)) {
			$('.resultName').css('color', 'red').text('유효하지 않은 이름입니다.');
			isNameOk = false;
		} else {
			$('.resultName').css('color', 'red').text('');
			isNameOk = true;
		}
	});
	
	$('#btnCheckUid').click(function(e) {
		const uid = $('input[name=uid]').val();
		if (!uid.match(reUid)) {
			$('.uidResult').css('color', 'red').text('유효한 아이디가 아닙니다.');
			isUidOk = false;
			return;
		}
		if (uid == '') {
			$('.uidResult').css('color', 'red').text('아이디를 입력해주세요.');
			return;
		}
		const jsonData = {
				"type": "uid",
				"uid" : uid
		};
		$.ajax({
			url : '/Jboard2/user/userCheck.do',
			type : 'GET',
			data : jsonData,
			dataType : 'json',
			success : function(data){
				if (data.result > 0) {
					$('.uidResult').css('color', 'red').text('이미 사용중인 아이디입니다.');
					isUidOk = false;
				} else {
					$('.uidResult').css('color', 'green').text('사용가능한 아이디입니다.');
					isUidOk = true;
				}
			}
		});
	});
	
	// 닉네임 중복체크
	$('#btnCheckNick').click(function(){
		// 입력 데이터 가져오기
		const nick = $('input[name=nick]').val();
		if (!nick.match(reNick)) {
			$('.nickResult').css('color', 'red').text('유효한 닉네임이 아닙니다.');
			isNickOk = false;
			return;
		} 
		// JSON 생성
		const jsonData = {
				"type":"nick",
				"nick" : nick
		};
		// 데이터 전송
		$.ajax({
			url : '/Jboard2/user/userCheck.do',
			type : 'GET',
			data : jsonData,
			dataType : 'json',
			success : function(data){
				if (data.result > 0) {
					$('.nickResult').css('color', 'red').text('이미 사용중인 별명입니다.');
					isNickOk = false;
				} else {
					$('.nickResult').css('color', 'green').text('사용가능한 별명입니다.');
					isNickOk = true;
				}
			}
		});
	});
	// 휴대폰 중복체크
	document.getElementsByName('hp')[0].addEventListener('focusout', function() {
		const hp = $(this).val();
		const jsonData = {
				"type": "hp",
				"hp" : hp
		};
		$.ajax({
			url : '/Jboard2/user/userCheck.do',
			type : 'GET',
			data : jsonData,
			dataType : 'json',
			success : function(data){
				if (data.result > 0) {
					$('.resultHp').css('color', 'red').text('이미 사용중인 전화번호 입니다.');
					isHpOk = false;
				} else {
					$('.resultHp').css('color', 'green').text('사용가능한 전화번호 입니다.');
					isHpOk = true;
				}
			}
		});
	});
	
	// 이메일 인증
	$('#btnEmailCode').click(function(){
		if (!flag) {
			return false;
		}
		flag = false;
		time = 30;
		
		const email = $('input[name=email]').val();
		const jsonData = {'email' : email};
		$('.codeEmail').text('인증코드 전송 중 입니다. 잠시만 기다려주세요.');
		$.ajax({
			url: '/Jboard2/user/authEmail.do',
			type: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data) {
				console.log(data.code);
				receivedcode = data.code;
				$('.auth').show();
				$('.resultEmail').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
				$('.codeEmail').text(time + '초 후 재전송 가능합니다.');
			 	let interval = setInterval(function() {
				   	time -= 1;
					$('.codeEmail').text(time + '초 후 재전송 가능합니다.');
					if (time == 0) {
						flag = true;
						$('.codeEmail').text('재전송 가능합니다.');
						clearInterval(interval);
					}   
				}, 1000);	
			}
		});
	});
	$('#btnEmailAuth').click(function(){
		const code = $('input[name=auth]').val();
		if (code == receivedcode) {
			$('.resultEmail').css('color', 'green').text('이메일이 인증 되었습니다.');
			isEmailOk = true;
			
		} else {
			$('.resultEmail').css('color', red).text('인증에 실패했습니다');
			isEmailOk = false;
		}
	});
});