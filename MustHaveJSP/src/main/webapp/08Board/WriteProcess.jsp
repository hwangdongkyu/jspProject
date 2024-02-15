<%@page import="utils.JSFunction"%>
<%@page import="model1.BoardDAO"%>
<%@page import="model1.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//폼값 받기
String title = request.getParameter("title");
String content = request.getParameter("content");
//폼값을 DTO객체에 저장
BoardDTO dto = new BoardDTO(); //dto객체에 생성
dto.setTitle(title);		   //dto객체에 제목값입력
dto.setContent(content);       //dto객체에 내용값입력
dto.setId(session.getAttribute("UserId").toString()); //로그인하여 세선(객체)에있는 id를 저장
//DAO객체를 통해 DB에 DTO저장
BoardDAO dao = new BoardDAO(application); //jdbc연결 객체생성
int iResult = dao.insertWrite(dto);//insertWrite메서드에서 dto값 전달하여 결과를 int로 받는다.

//int iResult = 0;
//for(int i=1; i<100; i++) {
//	dto.setTitle( title + "-" + i); //제목글자에-1~-100까지 붙는다.
//	iResult = dao.insertWrite(dto); //insert쿼리실행
//}


dao.close();
//성공 or 실패
if(iResult ==1) {
	response.sendRedirect("List.jsp");
}else{
	JSFunction.alertBack("글쓰기에 실패하였습니다.", out);
}
%>


