package cn.jmu.po;

public class Ticket {

	private String fineid;
	private String finetypeID;
	private String userid;
	private double totalfineprice;
	private String finedetail;
	private boolean settlestatus;
	public String getFineid() {
		return fineid;
	}
	public void setFineid(String fineid) {
		this.fineid = fineid;
	}
	public String getFinetypeid() {
		return finetypeID;
	}
	public void setFinetypeid(String finetypeid) {
		this.finetypeID = finetypeid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getTotalfineprice() {
		return totalfineprice;
	}
	public void setTotalfineprice(double totalfineprice) {
		this.totalfineprice = totalfineprice;
	}
	public String getFinedetail() {
		return finedetail;
	}
	public void setFinedetail(String finedetail) {
		this.finedetail = finedetail;
	}
	public boolean isSettlestatus() {
		return settlestatus;
	}
	public void setSettlestatus(boolean settlestatus) {
		this.settlestatus = settlestatus;
	}
	public Ticket(String fineid, String finetypeid, String userid, double totalfineprice, String finedetail,
			boolean settlestatus) {
		super();
		this.fineid = fineid;
		this.finetypeID = finetypeid;
		this.userid = userid;
		this.totalfineprice = totalfineprice;
		this.finedetail = finedetail;
		this.settlestatus = settlestatus;
	}
}
