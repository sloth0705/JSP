<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"/>
    <link rel="stylesheet" href="/Farmstory2/css/style.css"/>
    <link rel="stylesheet" href="/Farmstory2/user/css/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src='/Farmstory2/js/zipcode.js'></script>    
    <script>
        $(function(){
            $('.slider > ul').bxSlider({
                slideWidth: 980,
                pager: false,
                controls: false,
                auto: true
            });
            $('#tabs').tabs();
        });
    </script>
</head>
<body>
    <div id="container">
        <header>
            <a href="/Farmstory2/index.jsp" class="logo"><img src="/Farmstory2/images/logo.png" alt="로고"/></a>
            <p>
                <a href="#">HOME |</a>
                <c:choose>
                	<c:when test="${sessUser eq null }">
		                <a href="/Farmstory2/user/login.do">로그인 |</a>
                	</c:when>
                	<c:otherwise>
                		<a href="/Farmstory2/user/logout.do">로그아웃 |</a>
                	</c:otherwise>
                </c:choose>
                <a href="/Farmstory2/user/register.do">회원가입 |</a>
                <c:if test="${sessUser.role eq 'ADMIN' }">
                <a href="/Farmstory2/admin/">관리자 |</a>
                </c:if>
                <a href="#">고객센터</a>
            </p>
            <img src="/Farmstory2/images/head_txt_img.png" alt="3만원 이상 무료배송"/>
            <ul class="gnb">
                <li><a href="/Farmstory2/introduction/hello.do">팜스토리소개</a></li>
                <li><a href="/Farmstory2/market/list.do"><img src="/Farmstory2/images/head_menu_badge.png" alt="30%"/>장보기</a></li>
                <li><a href="/Farmstory2/croptalk/story.do">농작물이야기</a></li>
                <li><a href="/Farmstory2/event/event.do">이벤트</a></li>
                <li><a href="/Farmstory2/community/notice.do">커뮤니티</a></li>
            </ul>
        </header>