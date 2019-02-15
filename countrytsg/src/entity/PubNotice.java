package entity;

import java.util.Date;

public class PubNotice {
	/**
	 * 系统公告
	 */
	private int pnid;//公告id
	private String title;//公告名
	private String content;//公告内容
	private Date pntime;//公告时间
	private int uid;//发布人
	private int num;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPnid() {
		return pnid;
	}
	public void setPnid(int pnid) {
		this.pnid = pnid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getPntime() {
		return pntime;
	}
	public void setPntime(Date pntime) {
		this.pntime = pntime;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
	
}
