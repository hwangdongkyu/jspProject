package model1;

public class BoardDTO { //BoardDTO는 데이터베이스 필드값과 동일한 객체를 만듬.
	//멤버 변수 선언
	private String num;				//번호
	private String title;			//제목
	private String content;			//내용
	private String id;				//작성자id
	private java.sql.Date postDate;  //작성일
	private String visitcount;		//조회수
	private String name;			// fk를 활용한 memberDTO의 이름
	
	public BoardDTO() {}
	//게터
	public String getNum() {return num;}
	public String getTitle() {return title;}
	public String getContent() {return content;}
	public String getId() {return id;}
	public java.sql.Date getPostDate() {return postDate;}
	public String getVisitcount() {return visitcount;}
	public String getName() {return name;}
	//세터
	public void setNum(String num) {this.num = num;}
	public void setTitle(String title) {this.title = title;}
	public void setContent(String content) {this.content = content;}
	public void setId(String id) {this.id = id;}
	public void setPostDate(java.sql.Date posDate) {this.postDate = posDate;}
	public void setVisitcount(String visitcount) {this.visitcount = visitcount;}
	public void setName(String name) {this.name = name;}
	
	
	
	
	
	
}
