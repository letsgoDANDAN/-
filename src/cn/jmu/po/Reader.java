package cn.jmu.po;

public class Reader {
	
	private String userid;
	private String unitID;
	private String readertypeID;
	private String password;
	private String role;
	private String name;
	private String Email;
	private String phone;
	private boolean borrowstatus;
	private int fbooknumber;
	private int cbooknumber;
	private int booknumber;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUnitID() {
		return unitID;
	}
	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}
	public String getReadertypeID() {
		return readertypeID;
	}
	public void setReadertypeID(String readertypeID) {
		this.readertypeID = readertypeID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isBorrowstatus() {
		return borrowstatus;
	}
	public void setBorrowstatus(boolean borrowstatus) {
		this.borrowstatus = borrowstatus;
	}
	public int getFbooknumber() {
		return fbooknumber;
	}
	public void setFbooknumber(int fbooknumber) {
		this.fbooknumber = fbooknumber;
	}
	public int getCbooknumber() {
		return cbooknumber;
	}
	public void setCbooknumber(int cbooknumber) {
		this.cbooknumber = cbooknumber;
	}
	public int getBooknumber() {
		return booknumber;
	}
	public void setBooknumber(int booknumber) {
		this.booknumber = booknumber;
	}
	public Reader(String userid, String unitID, String readertypeID, String password, String role, String name,
			String email, String phone, boolean borrowstatus, int fbooknumber, int cbooknumber, int booknumber) {
		super();
		this.userid = userid;
		this.unitID = unitID;
		this.readertypeID = readertypeID;
		this.password = password;
		this.role = role;
		this.name = name;
		this.Email = email;
		this.phone = phone;
		this.borrowstatus = borrowstatus;
		this.fbooknumber = fbooknumber;
		this.cbooknumber = cbooknumber;
		this.booknumber = booknumber;
	}
	@Override
	public String toString() {
		return "Reader [userid=" + userid + ", unitID=" + unitID + ", readertypeID=" + readertypeID + ", password="
				+ password + ", role=" + role + ", name=" + name + ", Email=" + Email + ", phone=" + phone
				+ ", borrowstatus=" + borrowstatus + ", fbooknumber=" + fbooknumber + ", cbooknumber=" + cbooknumber
				+ ", booknumber=" + booknumber + "]";
	}

}
