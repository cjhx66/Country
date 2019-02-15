package entity;

import java.util.Date;



public class Country {
	/**
	 * 帐户管理
	 */
	private int cid;//乡村帐户id
	private String cname;//乡村帐户名
	private String caddress;//乡村帐户地址
	private String cphone;//乡村帐户联系方式
	private Date caddTime;//乡村帐户添加时间
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCaddress() {
		return caddress;
	}
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public Date getCaddTime() {
		return caddTime;
	}
	public void setCaddTime(Date caddTime) {
		this.caddTime = caddTime;
	}


}
