package cn.jmu.po;

public class Book {
	private String bookID;
	private String ISBN;
	private String branchID;
	private int readerstatus;
	private String details;
	
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getBranchID() {
		return branchID;
	}
	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}
	public int getReaderstatus() {
		return readerstatus;
	}
	public void setReaderstatus(int i) {
		this.readerstatus = i;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Book(String bookID, String iSBN, String branchID, int readerstatus, String details) {
		super();
		this.bookID = bookID;
		ISBN = iSBN;
		this.branchID = branchID;
		this.readerstatus = readerstatus;
		this.details = details;
	}
}
