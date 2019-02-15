package service.impl;

import java.util.ArrayList;
import java.util.List;

import service.SysService;
import dao.SysDao;
import entity.App;
import entity.Button;
import entity.Menu;
import entity.User;

public class SysServiceImpl implements SysService {
	private SysDao sysDao = null;

	public SysDao getSysDao() {
		return sysDao;
	}

	public void setSysDao(SysDao sysDao) {
		this.sysDao = sysDao;
	}

	@Override
	public Menu findMenu(Menu menu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public App findApp(App app) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getApps(User user) {
		List<App> appList = sysDao.getApps();
		String toolbarscript = "{Items:[";
		for (int i = 0; i < appList.size(); i++) {
			toolbarscript += "{";
			toolbarscript += "type:'button',";
			toolbarscript += "text:'" + appList.get(i).getAppName() + "',";
			toolbarscript += "icon:'" + appList.get(i).getAppIcon() + "',";
			/*if (user.getRid() == 1) {
				toolbarscript += "disable:true,";
			} else {*/
				toolbarscript += "disable:"
						+ GetAppAuthority(user.getRid(), appList.get(i)
								.getAppId()) + ",";
			/*}*/
			toolbarscript += "click: function () {";
			toolbarscript += "f_according(" + appList.get(i).getAppId() + ")";
			toolbarscript += "}";
			toolbarscript += "},";
		}
		toolbarscript = toolbarscript.substring(0, toolbarscript.length() - 1);
		toolbarscript += "]}";
		return toolbarscript;
	}

	@Override
	public String getMenu(int appid, User logUser) {
		List<Menu> menuList = new ArrayList<Menu>();
		/*if (logUser.getRid() == 1) {
			menuList = sysDao.getMenu(appid);
		}else{*/
			String menuids = sysDao.getMenuIds(logUser.getRid(),appid);
			String []menuid = menuids.split(",");
			List<Integer> menuidList = new ArrayList<Integer>();
			for (int i = 0; i < menuid.length; i++) {
				if (!menuid[i].equals("")) {
					menuidList.add(Integer.parseInt(menuid[i]));
				}
			}
			for (int i = 0; i < menuidList.size(); i++) {
				Menu menu = sysDao.findMenu(menuidList.get(i));
				menuList.add(menu);
			}
		/*}*/
		String dt = "[" + GetTasksString(0, menuList) + "]";
		return dt;
	}

	private String GetTasksString(int id, List<Menu> table) {
		List<Menu> mlist = new ArrayList<Menu>();
		List<Integer> numList = new ArrayList<Integer>();
		int n = 0;
		for (Menu m : table) {
			if (m.getPid() == id) {
				mlist.add(m);
				numList.add(new Integer(n));
			}
			n++;
		}
		if (mlist.size() == 0) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		for (Menu m : mlist) {
			String[] menuInfo = { m.getMenuId() + "", m.getMenuName(),
					m.getPid() + "", m.getAppid() + "", m.getMenuUrl(),
					m.getMenuIcon() };
			String[] menuTitle = { "Menu_id", "Menu_name", "pid", "appid",
					"Menu_url", "Menu_icon" };
			str.append("{");
			for (int i = 0; i < menuInfo.length; i++) {
				if (i != 0) {
					str.append(",");
				}
				str.append(menuTitle[i]);
				str.append(":\'");
				str.append(menuInfo[i]);
				str.append("\'");
			}
			String childString = GetTasksString(Integer.parseInt(menuInfo[0]),
					table);
			if (null != childString) {
				str.append(",\"children\":[");
				str.append(childString);
				str.append("]},");
			} else {
				str.append("},");
			}

		}
		return str.charAt(str.length() - 1) == ',' ? str.substring(0,
				str.length() - 1) : str.toString();
	}

	@Override
	public boolean GetAppAuthority(int rid, int appid) {
		boolean b = false;
		/*if (rid > 1 && appid > 0) { */
		if (rid > 0 && appid > 0) {
				b = GetAppid(rid).contains(appid);
		}
		return b;
	}

	private List<Integer> GetAppid(int rid) {
		if (rid <= 0) {
			return null;
		} else {
			List<Integer> appidList = sysDao.getAppid(rid);
			return appidList;
		}

	}

	@Override
	public String getButtons(User user,int menuId) {
		List<Button>btnList = sysDao.getButtons(menuId);
		String toolbarscript ="{Items:[";
		for (int i = 0; i < btnList.size(); i++) {
			toolbarscript += "{";
			toolbarscript += "type:'button',";
			toolbarscript += "text:'" + btnList.get(i).getBtnName() + "',";
			toolbarscript += "icon:'" + btnList.get(i).getBtnIcon() + "',";
			if (user.getRid() == 1) {
				toolbarscript += "disable:true,";
			} else {
				toolbarscript += "disable:"
						+ GetBtnAuthority(user.getRid(), btnList.get(i)
								.getBtnId()) + ",";
			}
			toolbarscript += "click: function () {";
			toolbarscript += btnList.get(i).getBtnHandler().toString().replace("()", "("+menuId+")");
			toolbarscript += "}";
			toolbarscript += "},";
		}
		toolbarscript = toolbarscript.substring(0, toolbarscript.length() - 1);
		toolbarscript += "]}";
		return toolbarscript;
	}
	public boolean GetBtnAuthority(int rid, int btnid) {
		boolean b = false;
		if (rid > 1 && btnid > 0) {

				b = GetBtnid(rid).contains(btnid);
		}
		return b;
	}

	private List<Integer> GetBtnid(int rid) {
		if (rid <= 0) {
			return null;
		} else {
			String btnIds = sysDao.getButtonIds(rid);
			String []temp=btnIds.split(",") ;
			List<Integer> btnid = new ArrayList<Integer>();
			for (int i = 0; i < temp.length; i++) {
				if (!temp[i].equals("")) {
					btnid.add(Integer.parseInt(temp[i]));
				}
			}
		return btnid;
		}

	}

}
