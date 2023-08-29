<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<main id="user">
    <section class="find findIdResult">
        <form action="#">
            <table>
                <caption>아이디 찾기 결과</caption>
                <tr>
                    <td>이름</td>
                    <td>${userResult.name }</td>
                </tr>
                <tr>
                    <td>아이디</td>
                    <td>${userResult.uid }</td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td>${userResult.email }</td>
                </tr>
                <tr>
                    <td>가입일</td>
                    <td>${userResult.regDate }</td>
                </tr>
            </table>                                        
        </form>
        <p>
            고객님의 정보와 일치하는 아이디 입니다.
        </p>
        <div>
            <a href="/Jboard2/user/login.do" class="btn btnCancel">로그인</a>
            <a href="/Jboard2/user/findPass.do" class="btn btnNext">비밀번호 찾기</a>
        </div>
    </section>
</main>
<%@ include file="./_footer.jsp" %>