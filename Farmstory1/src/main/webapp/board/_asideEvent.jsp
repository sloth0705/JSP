<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cate = request.getParameter("cate");
%>
<div id="sub">
    <div><img src="/Farmstory1/images/sub_top_tit4.png" alt="CROP TALK"></div>
    <section class="event">
        <aside>
            <img src="/Farmstory1/images/sub_aside_cate4_tit.png" alt="농작물이야기"/>
            <ul class="lnb">
                <li class="<%=cate.equals("event") ? "on" : ""%>"><a href="/Farmstory1/board/list.jsp?group=Event&cate=event">이벤트</a></li>
            </ul>
        </aside>
        <article>
            <nav>
                <img src="/Farmstory1/images/sub_nav_tit_cate4_<%=cate %>.png" alt="이벤트"/>
                <p>
                    HOME > 이벤트 >
                    <%if(cate.equals("event")){ %> 
                    <em>이벤트</em>
                    <%} %>
                </p>
            </nav>