package cn.jmu.po;

public class BorrowConditions {

	private String readertypeID;
	private String borrowtypeID;
	private int borrownum;
	private int borrowtime;
	private int renewtime;
	public String getReadertypeID() {
		return readertypeID;
	}
	public void setReadertypeID(String readertypeID) {
		this.readertypeID = readertypeID;
	}
	public String getBorrowtypeID() {
		return borrowtypeID;
	}
	public void setBorrowtypeID(String borrowtypeID) {
		this.borrowtypeID = borrowtypeID;
	}
	public int getBorrownum() {
		return borrownum;
	}
	public void setBorrownum(int borrownum) {
		this.borrownum = borrownum;
	}
	public int getBorrowtime() {
		return borrowtime;
	}
	public void setBorrowtime(int borrowtime) {
		this.borrowtime = borrowtime;
	}
	public int getRenewtime() {
		return renewtime;
	}
	public void setRenewtime(int renewtime) {
		this.renewtime = renewtime;
	}
	
}
