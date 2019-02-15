package entity;

import java.util.Date;

public class BorBooks {
	/**
	 * 借阅管理
	 */
		private int jid;//借书id
		private String userid;//读者id
		private int bid;//图书id
		private int cid;
		private int btype;//状态(0 还书,1 借书,2续借)
		private Date jianTime;//借书时间
		private Date huanTime;//归还时间
		private Date yingTime;//应还时间
		
	
		public int getCid() {
			return cid;
		}
		public void setCid(int cid) {
			this.cid = cid;
		}
		public Date getYingTime() {
			return yingTime;
		}
		public void setYingTime(Date yingTime) {
			this.yingTime = yingTime;
		}
		public int getJid() {
			return jid;
		}
		public void setJid(int jid) {
			this.jid = jid;
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
		
		public int getBtype() {
			return btype;
		}
		public void setBtype(int btype) {
			this.btype = btype;
		}
		public Date getJianTime() {
			return jianTime;
		}
		public void setJianTime(Date jianTime) {
			this.jianTime = jianTime;
		}
		public Date getHuanTime() {
			return huanTime;
		}
		public void setHuanTime(Date huanTime) {
			this.huanTime = huanTime;
		}

}
