package cn.jmu.po;

import java.util.Date;

public class Borrow {
	private String userid;
	private String bookID;
	private Date borrowingtime;
	private Date returntime; //应归还时间
	private boolean renewstatus; 
	private boolean returnstatus; 
	private Date time; //实际归还时间
	
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public Date getBorrowingtime() {
		return borrowingtime;
	}
	public void setBorrowingtime(Date borrowingtime) {
		this.borrowingtime = borrowingtime;
	}
	public boolean isRenewstatus() {
		return renewstatus;
	}
	public void setRenewstatus(boolean renewstatus) {
		this.renewstatus = renewstatus;
	}
	public boolean isReturnstatus() {
		return returnstatus;
	}
	public void setReturnstatus(boolean returnstatus) {
		this.returnstatus = returnstatus;
	}
	public Date getReturntime() {
		return returntime;
	}
	public void setReturntime(Date returntime) {
		this.returntime = returntime;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Borrow(String userid, String bookID, Date borrowingtime, Date returntime, boolean renewstatus,
			boolean returnstatus, Date time) {
		super();
		this.userid = userid;
		this.bookID = bookID;
		this.borrowingtime = borrowingtime;
		this.returntime = returntime;
		this.renewstatus = renewstatus;
		this.returnstatus = returnstatus;
		this.time = time;
	}
	
}
