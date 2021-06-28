package cn.jmu.po;

public class User {
	private String userid;
	private String password;
	private String role;
	private String name;
	private String Email;
	private String phone;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public User(String userid, String password, String role, String name, String email, String phone) {
		super();
		this.userid = userid;
		this.password = password;
		this.role = role;
		this.name = name;
		Email = email;
		this.phone = phone;
	}
	

}
