 package entity;

import java.util.Date;

public class Compensate {
	/**
	 * 挂失赔偿
	 */
	private int gid;//挂失id
	private String userid;//读者id
	private int cid;//帐户id 
	private int bid;//图书id
	private String bookid;//图书编号
	private Date guaTime;//挂失时间
	private Date peiTime;//赔偿时间 
	private Float sprice;//实赔金额
	private Float yprice;
	
	
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public Float getSprice() {
		return sprice;
	}
	public void setSprice(Float sprice) {
		this.sprice = sprice;
	}
	public Float getYprice() {
		return yprice;
	}
	public void setYprice(Float yprice) {
		this.yprice = yprice;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public Date getGuaTime() {
		return guaTime;
	}
	public void setGuaTime(Date guaTime) {
		this.guaTime = guaTime;
	}
	public Date getPeiTime() {
		return peiTime;
	}
	public void setPeiTime(Date peiTime) {
		this.peiTime = peiTime;
	}
	
}
