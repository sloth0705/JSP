<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<div id="sub">
    <div><img src="/Farmstory2/images/sub_top_tit2.png" alt="MARKET"></div>
    <section class="market">
        <aside>
            <img src="/Farmstory2/images/sub_aside_cate2_tit.png" alt="장보기"/>
            <ul class="lnb">
                <li class="on"><a href="/Farmstory2/market/list.do">장보기</a></li>
            </ul>
        </aside>
        <article class="list">
            <nav>
                <img src="/Farmstory2/images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                <p>
                    HOME > 장보기 > <em>장보기</em>
                </p>
            </nav>
            <!-- 내용 시작 -->
            <p class="sort">
                <a href="#" class="on">전체(10) |</a>
                <a href="#">과일 |</a>
                <a href="#">야채 |</a>
                <a href="#">곡류</a>
            </p>
            <table>
                <tr>
                    <td>
                        <a href="/Farmstory2/market/view.do"><img src="/Farmstory2/images/market_item1.jpg" alt="사과 500g"></a>
                    </td>
                    <td>과일</td>
                    <td><a href="#">사과 500g</a></td>
                    <td><strong>4,000</strong>원</td>
                </tr>
            </table>
            <p class="paging">
                <a href="#"><</a>
                <a href="#" class="on">[1]</a>
                <a href="#">[2]</a>
                <a href="#">[3]</a>
                <a href="#">[4]</a>
                <a href="#">[5]</a>
                <a href="#">></a>
            </p>
            <!-- 내용 끝 -->
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp" %>