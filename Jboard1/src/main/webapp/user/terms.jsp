<%@page import="kr.co.jboard1.dto.TermsDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
TermsDTO terms = new TermsDTO();
	try {
		Context initCtx = new InitialContext();
		Context ctx = (Context) initCtx.lookup("java:comp/env"); // JNDI 기본 환경이름
		DataSource ds = (DataSource) ctx.lookup("jdbc/Jboard");
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `terms`");
		if (rs.next()) {
	terms.setTerms(rs.getString(1));
	terms.setPrivacy(rs.getNString(2));
		}
		rs.close();
		stmt.close();
		conn.close();
	} catch(Exception e) {
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jboard::terms</title>
    <link rel="stylesheet" href="../css/style.css">  
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>  
    <script>
    	$(function() {
   			const chk1 = $('input[name=chk1]')[0];
   			const chk2 = $('input[name=chk2]')[0];
    		$('.btnNext').eq(0).click(function(e) {
    			e.preventDefault();
    			if (!chk1.checked) {
    				alert('사이트 이용약관에 동의해주세요.');
    				return;
    			}
    			if (!chk2.checked) {
    				alert('개인정보 취급방침에 동의해주세요.');
    				return;
    			}
    			location.href = '/Jboard1/user/register.jsp';
    		});
    	});
    </script>
</head>
<body>
    <div id="container">
        <header>
            <h3>Board System v1.0</h3>
        </header>
        <main>
            <section class="terms">
                <table>
                    <caption>사이트 이용약관</caption>
                    <tr>
                        <td>
                            <textarea readonly><%=terms.getTerms()%></textarea>
                            <p>
                                <label><input type="checkbox" name="chk1"/>동의합니다.</label>
                            </p>
                        </td>
                    </tr>
                </table>
                <table>
                    <caption>개인정보 취급방침</caption>
                    <tr>
                        <td>
                            <textarea readonly><%=terms.getPrivacy()%></textarea>
                            <p>
                                <label><input type="checkbox" name="chk2"/>동의합니다.</label>
                            </p>
                        </td>
                    </tr>
                </table>
                <div>
                    <a href="#" class="btnCancel">취소</a>
                    <a href="#" class="btnNext">다음</a>
                </div>
            </section>
        </main>
        <footer>
            <p>ⓒcopyright 신진성.com</p>
        </footer>
    </div>
</body>
</html>