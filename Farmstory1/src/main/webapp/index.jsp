<%@page import="farmstory1.dao.ArticleDAO"%>
<%@page import="farmstory1.dto.ArticleDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ArticleDAO dao = new ArticleDAO();
	List<ArticleDTO> storys = dao.selectArticlesLimit(5, "story");
	List<ArticleDTO> grows = dao.selectArticlesLimit(5, "grow");
	List<ArticleDTO> schools = dao.selectArticlesLimit(5, "school");
	List<ArticleDTO> notices = dao.selectArticlesLimit(3, "notice");
	List<ArticleDTO> qnas = dao.selectArticlesLimit(3, "qna");
	List<ArticleDTO> faqs = dao.selectArticlesLimit(3, "faq");
%>
<%@ include file="./_header.jsp" %>
<main>
    <div class="slider">
        <ul>
            <li><img src="./images/main_slide_img1.jpg" alt="슬라이더1"></li>
            <li><img src="./images/main_slide_img2.jpg" alt="슬라이더2"></li>
            <li><img src="./images/main_slide_img3.jpg" alt="슬라이더3"></li>
        </ul>
        <img src="./images/main_slide_img_tit.png" alt="사람과 자연을 사랑하는 팜스토리"/>
        <div class="banner">
            <img src="./images/main_banner_txt.png" alt="GRAND OPEN"/>
            <img src="./images/main_banner_tit.png" alt="팜스토리 오픈기념 30% 할인 이벤트"/>
            <img src="./images/main_banner_img.png" alt="과일"/>
        </div>
    </div>
    <div class="quick">
        <a href="#"><img src="./images/main_banner_sub1_tit.png" alt="오늘의 식단"></a>
        <a href="#"><img src="./images/main_banner_sub2_tit.png" alt="나도 요리사"></a>                
    </div>
    <div class="latest">
        <div>
            <a href="#"><img src="./images/main_latest1_tit.png" alt="텃밭 가꾸기"/></a>
            <img src="./images/main_latest1_img.jpg" alt="이미지"/>
            <table>
            	<%for(ArticleDTO grow : grows) { %>
                <tr>
                    <td>></td>
                    <td><a href="/Farmstory1/board/view.jsp?no=<%=grow.getNo()%>&group=Croptalk&cate=grow"><%=grow.getTitle() %></a></td>
                    <td><%=grow.getRdate() %></td>
                </tr>
                <%} %>
            </table>
        </div>
        <div>
            <a href="#"><img src="./images/main_latest2_tit.png" alt="귀농학교"/></a>
            <img src="./images/main_latest2_img.jpg" alt="이미지"/>
            <table>
               <%for(ArticleDTO school : schools) { %>
                <tr>
                    <td>></td>
                    <td><a href="/Farmstory1/board/view.jsp?no=<%=school.getNo()%>&group=Croptalk&cate=school"><%=school.getTitle() %></a></td>
                    <td><%=school.getRdate() %></td>
                </tr>
                <%} %>
            </table>
        </div>
        <div>
            <a href="#"><img src="./images/main_latest3_tit.png" alt="농작물 이야기"/></a>
            <img src="./images/main_latest3_img.jpg" alt="이미지"/>
            <table>
                <%for(ArticleDTO story : storys) { %>
                <tr>
                    <td>></td>
                    <td><a href="/Farmstory1/board/view.jsp?no=<%=story.getNo()%>&group=Croptalk&cate=story"><%=story.getTitle() %></a></td>
                    <td><%=story.getRdate() %></td>
                </tr>
                <%} %>
            </table>
        </div>
    </div>
    <div class="info">
        <div>
            <img src="./images/main_sub2_cs_tit.png" class="tit" alt="고객센터 안내"/>
            <div class="tel">
                <img src="./images/main_sub2_cs_img.png" alt="">
                <img src="./images/main_sub2_cs_txt.png" alt="1666-777">
                <p class="time">
                    평일: AM 09:00 ~ PM 06:00<br>
                    점심: PM 12:00 ~ PM 01:00<br>
                    토, 일요일, 공휴일 휴무
                </p>
            </div>
            <div class="btns">
                <a href="#"><img src="./images/main_sub2_cs_bt1.png" alt="1:1 고객문의"></a>
                <a href="#"><img src="./images/main_sub2_cs_bt2.png" alt="자주묻는질문"></a>
                <a href="#"><img src="./images/main_sub2_cs_bt3.png" alt="배송조회"></a>
            </div>
        </div>
        <div>
            <img src="./images/main_sub2_account_tit.png" class="tit" alt="계좌안내"/>
            <p class="account">
                기업은행 123-456789-01-01-012<br />
                국민은행 01-1234-56789<br />
                우리은행 123-456789-01-01-012<br />
                하나은행 123-456789-01-01<br />
                예 금 주 (주)팜스토리
            </p>
        </div>
        <div>
            <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">공지사항</a></li>
                    <li><a href="#tabs-2">1:1 고객문의</a></li>
                    <li><a href="#tabs-3">자주묻는 질문</a></li>
                </ul>
                <div id="tabs-1">
                    <ul class="txt">
                    	<%for(ArticleDTO notice : notices) { %>
                        <li><a href="/Farmstory1/board/view.jsp?no=<%=notice.getNo()%>&group=Community&cate=notice">· <%=notice.getTitle() %></a></li>
                        <%} %>
                    </ul>
                </div>
                <div id="tabs-2">
                    <ul class="txt">
                        <%for(ArticleDTO qna : qnas) { %>
                        <li><a href="/Farmstory1/board/view.jsp?no=<%=qna.getNo()%>&group=Community&cate=qna">· <%=qna.getTitle() %></a></li>
                        <%} %>
                    </ul>
                </div>
                <div id="tabs-3">
                    <ul class="txt">
                        <%for(ArticleDTO faq : faqs) { %>
                        <li><a href="/Farmstory1/board/view.jsp?no=<%=faq.getNo()%>&group=Community&cate=faq">· <%=faq.getTitle() %></a></li>
                        <%} %>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="./_footer.jsp" %>        