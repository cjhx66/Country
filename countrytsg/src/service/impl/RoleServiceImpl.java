package service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.validator.constraints.Length;

import com.opensymphony.xwork2.ActionContext;

import dao.RoleDao;
import dao.SysDao;
import entity.App;
import entity.AppAuthority;
import entity.Menu;
import entity.Role;
import entity.User;
import service.RoleService;

public class RoleServiceImpl implements RoleService {
	private RoleDao rd;
	private SysDao sysDao;

	public SysDao getSysDao() {
		return sysDao;
	}

	public void setSysDao(SysDao sysDao) {
		this.sysDao = sysDao;
	}

	public RoleDao getRd() {
		return rd;
	}

	public void setRd(RoleDao rd) {
		this.rd = rd;
	}

	@Override
	public boolean addRole(Role role) {
		// TODO Auto-generated method stub
		return rd.addRole(role);
	}

	@Override
	public boolean delRole(int rid) {
		// TODO Auto-generated method stub
		boolean b = rd.delRole(rid);
		return b;
	}

	@Override
	public boolean editRole(Role role) {
		// TODO Auto-generated method stub
		return rd.editRole(role);
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		List<Role> rl = rd.getRole();
		if (rl == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < rl.size(); i++) {
			str.append("{RID:" + rl.get(i).getRid() + ",rname:'"
					+ rl.get(i).getRname() + "',rdesc:'" + rl.get(i).getRdesc()
					+ "',addTime:'" + rl.get(i).getAddTime() + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + rl.size() + "}";
		return s;
	}

	public String getRoleJSON() {
		ActionContext a=ActionContext.getContext();
		User user=(User) a.getSession().get("user");
		List<Role> clist=null;
		if(user.getRid()!=1){
			clist=rd.getRoles();
		}else{
			clist = rd.getRole();
		}
		
		if (clist == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (int i = 0; i < clist.size(); i++) {
			str.append("{id:" + clist.get(i).getRid() + ",text:'"
					+ clist.get(i).getRname() + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "]";
		return s;
	}

	@Override
	public Role findRole(int rid) {
		// TODO Auto-generated method stub
		Role r = rd.findRole(rid);
		return r;
	}

	@Override
	public String findRoles(int rid) {
		// TODO Auto-generated method stub
		Role re = rd.findRole(rid);
		if (re == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{RID:" + re.getRid() + ",rname:'" + re.getRname()
				+ "',rdesc:'" + re.getRdesc() + "',addTime:'" + re.getAddTime()
				+ "'}");
		String s = str.toString();
		return s;
	}

	@Override
	public String getAppAuthority(int rid) {
		// TODO Auto-generated method stub
		List<Integer> allAppid = rd.getAllapp();

		List<Boolean> booleans = new ArrayList<Boolean>();

		List<Integer> appidList = GetAppid(rid);

		List<App> appList = sysDao.getApps();

		boolean b = false;
		if (appidList == null)
			for (int i = 0; i < allAppid.size(); i++) {
				b = false;
				booleans.add(b);
			}
		else {
			for (int i = 0; i < allAppid.size(); i++) {
				if (appidList.contains(allAppid.get(i))) {
					b = true;
					booleans.add(b);
				} else {
					b = false;
					booleans.add(b);
				}
			}
		}
	
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < booleans.size(); i++) {
			sb.append("{boolean:'" + booleans.get(i) + "',appId:'"
					+ appList.get(i).getAppId() + "',appName:'"
					+ appList.get(i).getAppName().trim() + "'},");

		}
		String s = sb.substring(0, sb.length() - 1);
		s = s + "]";
		return s;
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
	public List<AppAuthority> getApp(int rid) {
		// TODO Auto-generated method stub
		List<AppAuthority> appList = new ArrayList<AppAuthority>();
		return appList = rd.getApp(rid);
	}

	@Override
	public void delApp(AppAuthority authority) {
		rd.delApp(authority);
	}

	@Override
	public boolean addApp(AppAuthority authority) {
		// TODO Auto-generated method stub
		return rd.addApp(authority);
	}

}
