package membership;

import common.JDBConnect;

public class MemberDAO extends JDBConnect { //JDBC를 연결하여 crud를 담당한다.
	//extends JDBConnect
	
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw); //new객체 생성할떄 jdbc용 매개값을 전달받는다.
	}//MemberDAO객체를 생성하면 오라클에 접속이 완료된다.
	
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO(); //회원객체 생성(값없다)
		String query = "SELECT * FROM member WHERE id=? AND pass=?"; //쿼리문 템플릿
		try {
			//쿼리문 실행
			pstmt = con.prepareStatement(query); //동적 쿼리문 중비
			pstmt.setString(1, uid);    //쿼리문의 첫번째 인파라미터에 값 설정
			pstmt.setString(2, upass);	//쿼리문의 두번째 인파라미터에 값 설정
			rs = pstmt.executeQuery();	//쿼리문 실행
			
			//결과 처리
			if (rs.next()) {
				//쿼리 결과로 얻은 회원정보를 DTO객체에 저장
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		}
		catch (Exception e) {
			System.out.println("getMemberDTO()메서드 오류 MemberDAO를 확인하세요");
			e.printStackTrace();
		}
		
		return dto; //결과 리턴할 객체
	}
	
}
