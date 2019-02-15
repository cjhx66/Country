package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import utils.HibernateUtil;
import dao.SysDao;
import entity.App;
import entity.AppAuthority;
import entity.Button;
import entity.Menu;

public class SysDaoImpl implements SysDao {

	@Override
	public List<Menu> getMenu(int appid) {
		String queryString = "from Menu menu where menu.appid=" + appid;
		List<Menu> list = new ArrayList<Menu>();
		try {
			Query query = HibernateUtil.getSesfactory().openSession()
					.createQuery(queryString);
			list = query.list();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	public String getMenuIds(int rid, int appid) {
		String queryString = "from AppAuthority appAuthority where appAuthority.rid="
				+ rid + " and appAuthority.appid=" + appid;
		List<AppAuthority> list = new ArrayList<AppAuthority>();
		try {
			Query query = HibernateUtil.getSesfactory().openSession()
					.createQuery(queryString);
			list = query.list();
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0).getMenuids();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu findMenu(int menuId) {
		// TODO Auto-generated method stub
		String queryString = "from Menu menu where menu.menuId='" + menuId
				+ "'";
		List<Menu> list = null;
		try {
			Query query = HibernateUtil.getSesfactory().openSession()
					.createQuery(queryString);
			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<App> getApps() {
		String queryString = "from App app";
		Query query = HibernateUtil.getSesfactory().openSession()
				.createQuery(queryString);
		List<App> appList = query.list();
		return appList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Button> getButtons(int menuId) {
		String queryString = "from Button button where button.menuId='"
				+ menuId + "'";
		List<Button> list = null;
		try {
			Query query = HibernateUtil.getSesfactory().openSession()
					.createQuery(queryString);
			list = query.list();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public List<Integer> getAppid(int rid) {
		List<AppAuthority> list = creatQuery(rid);
		if (list.size() == 0) {
			return null;
		} else {
			List<Integer> appidlist = new ArrayList<Integer>();
			for (int i = 0; i < list.size(); i++) {
				appidlist.add(list.get(i).getAppid());
			}
			return appidlist;
		}

	}

	@Override
	public String getButtonIds(int rid) {
		List<AppAuthority> list = creatQuery(rid);
		if (list.size() == 0) {
			return null;
		} else {
			String buttonIds = "";
			for (int i = 0; i < list.size(); i++) {
				buttonIds += list.get(i).getButtonids();
			}
			return buttonIds;
		}
	}

	@SuppressWarnings("unchecked")
	private List<AppAuthority> creatQuery(int rid) {
		String queryString = "from AppAuthority appAuthority where appAuthority.rid='"
				+ rid + "'";
		List<AppAuthority> list = new ArrayList<AppAuthority>();
		try {
			Query query = HibernateUtil.getSesfactory().openSession()
					.createQuery(queryString);
			list = query.list();
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Menu> getMenu() {
		// TODO Auto-generated method stub
		String queryString = "from Menu menu";
		Query query = HibernateUtil.getSesfactory().openSession()
				.createQuery(queryString);
		List<Menu> List = query.list();
		return List;
	}

}
