$(function(){
	$('#formUser').submit(function(){
	if(!isUidOk){
		alert('아이디를 확인 하십시요.');
		return false; // 폼 전송 취소	
	}
	
	if(!isPassOk){
		alert('비밀번호를 확인 하십시요.');
		return false; // 폼 전송 취소	
	}
	
	if(!isNameOk){
		alert('이름를 확인 하십시요.');
		return false; // 폼 전송 취소	
	}
	
	if(!isNickOk){
		alert('별명을 확인 하십시요.');
		return false; // 폼 전송 취소	
	}
	
	if(!isEmailOk){
		alert('이메일을 확인 하십시요.');
		return false; // 폼 전송 취소	
	}
	
	if(!isHpOk){
		alert('휴대폰을 확인 하십시요.');
		return false; // 폼 전송 취소	
	}
					
	return true; // 폼 전송 시작
});
})