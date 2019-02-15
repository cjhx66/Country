package entity;

public class LibraryName {
	/**
	 * 本馆信息
	 */
	private int lnid;
	private String lname;//图书馆名称
	private int cid;//帐户id
	private int uid;//图书管理员
	private int colbook;//藏书量
	private int userNum;//读者人数
	private String area;//图书馆面积
	private String intro;//图书馆简介
	private String lphone;//联系方式
	private String laddress;//所在地址
	public int getLnid() {
		return lnid;
	}
	public void setLnid(int lnid) {
		this.lnid = lnid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getColbook() {
		return colbook;
	}
	public void setColbook(int i) {
		this.colbook = i;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getLphone() {
		return lphone;
	}
	public void setLphone(String lphone) {
		this.lphone = lphone;
	}
	public String getLaddress() {
		return laddress;
	}
	public void setLaddress(String laddress) {
		this.laddress = laddress;
	}
	
}
