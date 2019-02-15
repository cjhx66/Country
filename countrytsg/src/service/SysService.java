package service;



import entity.App;
import entity.Menu;
import entity.User;


public interface SysService {
	// 获取Menu
	public Menu findMenu(Menu menu);
	// 获取App
	public String getApps(User user);
	
	public App findApp(App app);
	
	public String getMenu(int appid, User logUser);
	
	public boolean GetAppAuthority(int uid,int appid);

	public String getButtons(User logUser ,int menuId);
	

}
