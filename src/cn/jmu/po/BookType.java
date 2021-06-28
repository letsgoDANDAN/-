package cn.jmu.po;

public class BookType {
	private String sonID;
	private String fatherID;
	private String categoryname;

	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getSonID() {
		return sonID;
	}
	public void setSonID(String sonID) {
		this.sonID = sonID;
	}
	public String getFatherID() {
		return fatherID;
	}
	public void setFatherID(String fatherID) {
		this.fatherID = fatherID;
	}
	
}
