package cn.jmu.po;

public class BookDescribe {
	private String ISBN;
	private String sonID;
	private String publicID;
	private String borrowtypeID;
	private String bookname;
	private double price;
	private String introduce;
	private String picture;
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getPublicID() {
		return publicID;
	}
	public void setPublicID(String publicID) {
		this.publicID = publicID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getSonID() {
		return sonID;
	}
	public void setSonID(String sonID) {
		this.sonID = sonID;
	}
	public String getBorrowtypeID() {
		return borrowtypeID;
	}
	public void setBorrowtypeID(String borrowtypeID) {
		this.borrowtypeID = borrowtypeID;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public BookDescribe(String iSBN, String sonID, String publicID, String borrowtypeID, String bookname, double price,
			String introduce, String picture) {
		super();
		ISBN = iSBN;
		this.sonID = sonID;
		this.publicID = publicID;
		this.borrowtypeID = borrowtypeID;
		this.bookname = bookname;
		this.price = price;
		this.introduce = introduce;
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "BookDescribe [ISBN=" + ISBN + ", sonID=" + sonID + ", publicID=" + publicID + ", borrowtypeID="
				+ borrowtypeID + ", bookname=" + bookname + ", price=" + price + ", introduce=" + introduce
				+ ", picture=" + picture + "]";
	}
	
}
