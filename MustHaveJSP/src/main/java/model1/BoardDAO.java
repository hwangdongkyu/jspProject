package model1;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.servlet.ServletContext;
import common.JDBConnect;

public class BoardDAO extends JDBConnect {
    public BoardDAO(ServletContext application) {
        super(application);
    }

    // 검색 조건에 맞는 게시물의 개수를 반환합니다.
    public int selectCount(Map<String, Object> map) {
        int totalCount = 0; // 결과(게시물 수)를 담을 변수

        // 게시물 수를 얻어오는 쿼리문 작성
        String query = "SELECT COUNT(*) FROM board";
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%'";
        }

        try {
            stmt = con.createStatement();   // 쿼리문 생성
            rs = stmt.executeQuery(query);  // 쿼리 실행
            rs.next();  // 커서를 첫 번째 행으로 이동
            totalCount = rs.getInt(1);  // 첫 번째 칼럼 값을 가져옴
        }
        catch (Exception e) {
            System.out.println("게시물 수를 구하는 중 예외 발생");
            e.printStackTrace();
        }

        return totalCount; 
    }
    
    // 검색 조건에 맞는 게시물 목록을 반환합니다.
    public List<BoardDTO> selectList(Map<String, Object> map) { 
        List<BoardDTO> bbs = new Vector<BoardDTO>();  // 결과(게시물 목록)를 담을 변수

        String query = "SELECT * FROM board "; 
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        query += " ORDER BY num DESC "; 

        try {
            stmt = con.createStatement();   // 쿼리문 생성
            rs = stmt.executeQuery(query);  // 쿼리 실행

            while (rs.next()) {  // 결과를 순화하며...
                // 한 행(게시물 하나)의 내용을 DTO에 저장
                BoardDTO dto = new BoardDTO(); 

                dto.setNum(rs.getString("num"));          // 일련번호
                dto.setTitle(rs.getString("title"));      // 제목
                dto.setContent(rs.getString("content"));  // 내용
                dto.setPostDate(rs.getDate("postdate"));  // 작성일
                dto.setId(rs.getString("id"));            // 작성자 아이디
                dto.setVisitcount(rs.getString("visitcount"));  // 조회수

                bbs.add(dto);  // 결과 목록에 저장
            }
        } 
        catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }

        return bbs;
    }
    public List<BoardDTO> selectListPage(Map<String, Object> map) { 
        List<BoardDTO> bbs = new Vector<BoardDTO>();  // 결과(게시물 목록)를 담을 변수

        String query = "SELECT * FROM ("
        		+ "	SELECT tb.*, rownum rNum FROM ("
        		+ "		SELECT * FROM board " ;
        
        
        
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        query += " ORDER BY num DESC )Tb)WHERE rNum BETWEEN ? and ? " ; //내림차순 정렬
        		
        
        

        try {
            pstmt = con.prepareStatement(query);   // 쿼리문 생성
            pstmt.setString(1, map.get("start").toString());  // 쿼리 실행
            pstmt.setString(2, map.get("end").toString());  // 쿼리 실행
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {  // 결과를 순화하며...
                // 한 행(게시물 하나)의 내용을 DTO에 저장
                BoardDTO dto = new BoardDTO(); 

                dto.setNum(rs.getString("num"));          // 일련번호
                dto.setTitle(rs.getString("title"));      // 제목
                dto.setContent(rs.getString("content"));  // 내용
                dto.setPostDate(rs.getDate("postdate"));  // 작성일
                dto.setId(rs.getString("id"));            // 작성자 아이디
                dto.setVisitcount(rs.getString("visitcount"));  // 조회수

                bbs.add(dto);  // 결과 목록에 저장
            }
        } 
        catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }

        return bbs;
    }
    public int insertWrite(BoardDTO dto) {
    	int result = 0;
    	
    	try {
    		//insert쿼리문 작성
    		String query = "INSERT INTO BOARD ( "
    					 + "num,title,content,id,visitcount) " 
    					 + " VALUES ( "
    					 + "seq_board_num.NEXTVAL, ?, ?, ?, 0)"; 
    		
    		pstmt = con.prepareStatement(query); 
    		pstmt.setString(1, dto.getTitle()); //제목
    		pstmt.setString(2, dto.getContent()); //내용
    		pstmt.setString(3, dto.getId()); //저장된id
    		result = pstmt.executeUpdate(); //
    	}
    	catch (Exception e) {
			System.out.println("게시물 입력중 예외발생(insertWrite메서드를 확인하세요)");
			e.printStackTrace();
		}
    	return result;
    }
    
    //게시물을 클릭했을때 조회수 증가하는 메서드
    public void updateVisitCount(String num) {
    	String query = "UPDATE board SET "
    				 + " Visitcount=visitcount+1 "
    				 + " WHERE num=?";
    	
    	try {
    		pstmt = con.prepareStatement(query); 
    		pstmt.setString(1, num); //인파라미터를 일렬번호로 설정
    		pstmt.executeQuery();	 
    		
    		
    		
    	}
    	catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
    }
    
    //자세히 보기(번호를 받아서 dto로 리턴)
    public BoardDTO selectView(String num) {
    	BoardDTO dto = new BoardDTO();
    	
    	//쿼리문 준비
    	String query = "SELECT B.*, M.name FROM member M INNER JOIN board B ON M.id=B.id WHERE num=?";
    	
    	try {
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, num);  //인파라미터를 일렬번호로 설정
    		rs = pstmt.executeQuery();//쿼리 실행후 결과가 표로나옴
    		
    		//결과 처리
    		if(rs.next()) {
    			dto.setNum(rs.getString("num"));
    			dto.setTitle(rs.getString("title"));
    			dto.setContent(rs.getString("content"));
    			dto.setPostDate(rs.getDate("postdate"));
    			dto.setId(rs.getString("id"));
    			dto.setVisitcount(rs.getString("visitcount"));
    			dto.setName(rs.getString("name")); //dto객체의값 저장
    		}		
    	}
    	catch (Exception e) {
			System.out.println("게시물 상세보기중 예외발생(dao의 selectView()메서드를 확인하세요");
			e.printStackTrace();
		}
    	return dto;

    }
    
    //업데이트용 메서드(dto를받아 int로 리턴)
    public int updateEdit(BoardDTO dto) {
    	int result = 0;
    	
    	try {
    		String query = "UPDATE board SET title=?, content=? WHERE num=?"; //쿼리문 완성
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, dto.getTitle());
    		pstmt.setString(2, dto.getContent());
    		pstmt.setString(3, dto.getNum());
    		
    		result = pstmt.executeUpdate(); //쿼리문 실행하여 결과를 int로 받음
    	}
    	catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생(updateEdit메서드를 확인하세요)");
			e.printStackTrace();
		}
    	return result; //결과 반환	
    }
    
    public int deletePost(BoardDTO dto) {
    	int result = 0;
    	try {
    		String query = "DELETE FROM board WHERE num=?";
    		
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, dto.getNum());
    		
    		result = pstmt.executeUpdate();
    	}
    	catch (Exception e) {
			System.out.println("게시물 삭제중 예외발생");
			e.printStackTrace();
		}
    	return result;
    	
    }
    
}