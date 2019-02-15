package entity;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class User {
	/**
	 * 人员管理
	 */
	private Integer uid;// 用户id
	private String userid;//用户编号
	private String uname;// 用户名
	private String pwd;// 用户密码
	private Integer cid;// 所属乡村id
	private String sex;// 性别
	private String userPhone;// 联系方式
	private Date uaddTime;// 添加时间
	private Integer rid;// 角色id
	private String email;// 电子邮箱
	private String address;// 住址
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getUaddTime() {
		return uaddTime;
	}
	public void setUaddTime(Date uaddTime) {
		this.uaddTime = uaddTime;
	}
	
}
