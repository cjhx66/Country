package entity;

import java.util.Date;

public class Rule {
	/**
	 * 借阅规则
	 */
	private int ruid;//借阅规则id
	private String runame;//借阅名称
	private int ruday;//借阅天数
	private int runum;//数量
	private int renew;//续借次数
	private String userid;//创建者
	private Date rutime;//创建日期
	public int getRuid() {
		return ruid;
	}
	public void setRuid(int ruid) {
		this.ruid = ruid;
	}
	public String getRuname() {
		return runame;
	}
	public void setRuname(String runame) {
		this.runame = runame;
	}
	public int getRuday() {
		return ruday;
	}
	public void setRuday(int ruday) {
		this.ruday = ruday;
	}
	public int getRunum() {
		return runum;
	}
	public void setRunum(int runum) {
		this.runum = runum;
	}
	public int getRenew() {
		return renew;
	}
	public void setRenew(int renew) {
		this.renew = renew;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getRutime() {
		return rutime;
	}
	public void setRutime(Date rutime) {
		this.rutime = rutime;
	}
	

}
