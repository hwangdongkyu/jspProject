package fileupload;

public class MyFileDTO {
	//멤버 변수
	private String idx;
	private String name;
	private String title;
	private String cate;
	private String ofile;
	private String sfile;
	private String posdate;
	//게터
	public String getIdx() {return idx;}
	public String getName() {return name;}
	public String getTitle() {return title;}
	public String getCate() {return cate;}
	public String getOfile() {return ofile;}
	public String getSfile() {return sfile;}
	public String getPosdate() {return posdate;}
	//세터
	public void setIdx(String idx) {this.idx = idx;}
	public void setName(String name) {this.name = name;}
	public void setTitle(String title) {this.title = title;}
	public void setCate(String cate) {this.cate = cate;}
	public void setOfile(String ofile) {this.ofile = ofile;}
	public void setSfile(String sfile) {this.sfile = sfile;}
	public void setPosdate(String posdate) {this.posdate = posdate;}
	
	
}
