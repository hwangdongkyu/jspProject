<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<table border="1" width="90%">
		<tr>
			<td align="center">
			<!-- 로그인여부에따른 메뉴 변화 -->
			<%if (session.getAttribute("UserId") == null) { %>
				<a href="../06Session/LoginForm.jsp">로그인</a>
			<% } else { %>
				<a href="../06Session/Logout.jsp">로그아웃</a>
			<% } %>
			<!-- 8장과 9장의 회원제 계시판 프로젝트에서 사용할 링크 -->
			&nbsp;&nbsp;&nbsp;
			<a href="../08Board/List.jsp">게시판(페이징X)</a>
			&nbsp;&nbsp;&nbsp;
			<a href="../09PaginBoard/List.jsp">게시판(페이징o)</a>
		</tr>
	</table>















<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Link.jsp</title>
</head>
<body>

</body>
</html>