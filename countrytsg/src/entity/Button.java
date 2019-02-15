package entity;

public class Button {
	private int btnId;
	private String btnName;
	private String btnIcon;
	private String btnHandler;
	private int menuId;

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public void setBtnId(int btnId) {
		this.btnId = btnId;
	}

	public void setBtnIcon(String btnIcon) {
		this.btnIcon = btnIcon;
	}

	public void setBtnHandler(String btnHandler) {
		this.btnHandler = btnHandler;
	}

	public int getMenuId() {
		return menuId;
	}

	public String getBtnName() {
		return btnName;
	}

	public int getBtnId() {
		return btnId;
	}

	public String getBtnIcon() {
		return btnIcon;
	}

	public String getBtnHandler() {
		return btnHandler;
	}
}
