package dao;

import java.util.List;

import entity.App;
import entity.Button;
import entity.Menu;

public interface SysDao {
	// 获取Menu
	public String getMenuIds(int rid, int appid);

	public List<Menu> getMenu(int appid);

	public Menu findMenu(int menuId);

	// 获取App
	public List<App> getApps();

	public List<Integer> getAppid(int uid);

	// 获取button
	public List<Button> getButtons(int menuId);

	public String getButtonIds(int rid);

	public List<Menu> getMenu();

}
