<%@page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(session.getAttribute("UserId") == null ) {
	JSFunction.alertLocation("로그인후 이용해주십시오", "../06Session/LoginForm.jsp", out); //1. 알림창단어뜸 2.06세션로그인폼으로 이동
	return;
}
%>
