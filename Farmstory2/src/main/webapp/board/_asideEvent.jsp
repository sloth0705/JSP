<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sub">
	<div>
		<img src="/Farmstoryf/images/sub_top_tit4.png" alt="CROP TALK">
	</div>
	<section class="event">
		<aside>
			<img src="/Farmstory2/images/sub_aside_cate4_tit.png" alt="농작물이야기" />
			<ul class="lnb">
				<li class="${cate eq 'event' ? 'on' : '' }"><a
					href="/Farmstory2/board/list.jsp?group=Event&cate=event">이벤트</a></li>
			</ul>
		</aside>
		<article>
			<nav>
				<img src="/Farmstory2/images/sub_nav_tit_cate4_${cate }.png"
					alt="이벤트" />
				<p>
					HOME > 이벤트 >
					<c:choose>
						<c:when test="${cate eq 'event' }">
							<em>이벤트</em>
						</c:when>
					</c:choose>
				</p>
			</nav>