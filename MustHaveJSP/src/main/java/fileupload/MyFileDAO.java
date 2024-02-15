package fileupload;

import java.util.List;
import java.util.Vector;

import common.DBConnPool;

public class MyFileDAO extends DBConnPool{
	//새로운 게시물을 입력합니다.
	public int insertFile(MyFileDTO dto) {
		int applyResult = 0;
		try {
			String query = "INSERT INTO myfile("
					+"idx, name, title, cate, ofile, sfile)"
					+"VALUES("
					+"seq_board_num.nextval, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getCate());
			pstmt.setString(4, dto.getOfile());
			pstmt.setString(5, dto.getSfile());
			
			applyResult = pstmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("INSERT중 예외발생");
			e.printStackTrace();
		}
		return applyResult;
	}
	
	//파일 목록을 반환합니다.
	public List<MyFileDTO> myFileList() {
		List<MyFileDTO> fileList = new Vector<MyFileDTO>();
		
		//쿼리문 작성
		String query = "SELECT * FROM myfile ORDER by idx DESC";
		try {
			pstmt = con.prepareStatement(query); //쿼리준비
			rs = pstmt.executeQuery(); //쿼리 실행
			
			while(rs.next()) { //목록안의 파일수만큼 반복
				//DTO에 저장
				MyFileDTO dto = new MyFileDTO();
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setOfile(rs.getString(5));
				dto.setSfile(rs.getString(6));
				dto.setPosdate(rs.getString(7));
				
				fileList.add(dto); //목록에 추가
			}
		}
		catch (Exception e) {
			System.out.println("SELECT시 예외발생");
			e.printStackTrace();
		}
		return fileList; //목록 변환
	}

}
