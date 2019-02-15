package entity;

public class AppAuthority {
	Integer aid;
	Integer rid;
	Integer appid;
	String menuids;
	String buttonids;

	public String getMenuids() {
		return menuids;
	}

	public void setMenuids(String menuids) {
		this.menuids = menuids;
	}

	public String getButtonids() {
		return buttonids;
	}

	public void setButtonids(String buttonids) {
		this.buttonids = buttonids;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getAid() {
		return aid;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

}
