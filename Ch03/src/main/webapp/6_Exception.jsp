<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="./error/404.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>exception</title>
		<!--
			날짜 : 2023/07/26
			이름 : 신진성
			내용 : JSP exception 실습하기
			
			exception
			 - JSP 예외가 발생했을 때 예외를 처리하기 위한 내장객체
			 - 간접적으로 exception 객체를 통해 에러코드별 에러 페이지 작성
		-->
	</head>
	<body>
		<h3>exception 내장객체</h3>
		<a href="./sample.jsp">404에러</a>
		<a href="./proc/exception.jsp">500에러</a>
	</body>
</html>