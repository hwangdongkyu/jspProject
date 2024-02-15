package utils;

import javax.servlet.jsp.JspWriter;

public class JSFunction {
	// jsp 파일에서 alert 창을 띠우려면 <% 스크립 틀릿 %> 이후에 작성을 해야 된다.
	// 자바 코드가 이어지는 부분에서는 코드가 복잡 해진다.
	
	// 메시지 알림창을 띄운 후 명시한 URL로 이동합니다.
	public static void alertLocation(String msg, String url , JspWriter out ) {
		  try {
	            String script = ""  // 삽입할 자바스크립트 코드
	                          + "<script>"
	                          + "    alert('" + msg + "');"
	                          + "    location.href='" + url + "';"
	                          + "</script>";
	            out.println(script);  // 자바스크립트 코드를 out 내장 객체로 출력(삽입)
	        }
	        catch (Exception e) {}
	} //alertLocation() 메서드 종료
	
	
    // 메시지 알림창을 띄운 후 이전 페이지로 돌아갑니다.
    public static void alertBack(String msg, JspWriter out) {
        try {
            String script = ""
                          + "<script>"
                          + "    alert('" + msg + "');"
                          + "    history.back();"
                          + "</script>";
            out.println(script);
        }
        catch (Exception e) {}
    } //alertBack() 메서드 종료
	
	
	
	
	
}
