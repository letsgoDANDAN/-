package cn.jmu.po;

public class Writes {
	private String ISBN;
	private String authorID;
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getAuthorID() {
		return authorID;
	}
	public void setAuthorID(String authorID) {
		this.authorID = authorID;
	}
	public Writes(String iSBN, String authorID) {
		super();
		ISBN = iSBN;
		this.authorID = authorID;
	}
	@Override
	public String toString() {
		return "Writes [ISBN=" + ISBN + ", authorID=" + authorID + "]";
	}
	
}
