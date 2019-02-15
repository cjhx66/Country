package entity;

public class App {
	private int appId;
	private String appName;
	private String appIcon;

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}

	public App(String appName, String appIcon) {
		this.appIcon = appIcon;
		this.appName = appName;
	}

	public App() {
		
	}
}
