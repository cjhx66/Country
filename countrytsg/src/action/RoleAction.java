package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import entity.Role;

import service.RoleService;
import entity.AppAuthority;

public class RoleAction {
	private RoleService rs;

	public RoleService getRs() {
		return rs;
	}

	public void setRs(RoleService rs) {
		this.rs = rs;
	}

	public String getRole() throws Exception {
		String r = rs.getRole();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(r);
		return null;
	}

	public String addRole() throws Exception {
		String rname = ServletActionContext.getRequest().getParameter("r_name");
		String rdesc = ServletActionContext.getRequest().getParameter("r_desc");

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date addtime = sf.parse(sf.format(new Date()));

		Role re = new Role();
		re.setRname(rname);
		re.setRdesc(rdesc);
		re.setAddTime(addtime);
		boolean b = rs.addRole(re);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
	}

	public String findRole() throws Exception {
		int rid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("rid"));
		String c = rs.findRoles(rid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(c);
		return null;
	}

	public String editRole() throws Exception {
		String rname = ServletActionContext.getRequest().getParameter("r_name");
		String rdesc = ServletActionContext.getRequest().getParameter("r_desc");
		int rid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("rid"));
		Role re = rs.findRole(rid);
		re.setRname(rname);
		re.setRdesc(rdesc);
		boolean b = rs.editRole(re);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
	}

	public String delRole() throws Exception {
		String rid = ServletActionContext.getRequest().getParameter("rid");
		boolean b = rs.delRole(Integer.parseInt(rid));
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
	}

	/*
	 * 查找所有用户，用于在下拉列表显示
	 */
	public String geRoleJson() throws Exception {
		String role = rs.getRoleJSON();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(role);
		return null;

	}

	// 查找帐户名
	public String findName(int id) {
		Role re = rs.findRole(id);
		if (re == null) {
			return "--";
		} else {
			return re.getRname();
		}
	}

	// 获取权限按钮
	public String getAppAuthority() throws Exception {
		String rname = ServletActionContext.getRequest().getParameter("rid");
		int rid = Integer.parseInt(rname);
		String result = rs.getAppAuthority(rid);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(result);
		return null;
	}

	// 修改权限
	public String updataApp() throws Exception {
		int length = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("length"));
		int rid = Integer.parseInt(ServletActionContext.getRequest().getParameter(
				"rid"));
		ArrayList<String> sList = new ArrayList<String>();
		ArrayList<String> nList = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			sList.add(ServletActionContext.getRequest().getParameter(
					"appId" + i));
			nList.add(ServletActionContext.getRequest().getParameter(
					"appName" + i));
		}

		boolean result = false;
		AppAuthority authority = new AppAuthority();
		List<AppAuthority> appList = new ArrayList<AppAuthority>();
		appList = rs.getApp(rid);
		if (appList != null)
			for (int i = 0; i < appList.size(); i++) {
				if (appList.get(i) != null) {
					rs.delApp(appList.get(i));
				}
			}

		List<Boolean> list = new ArrayList<Boolean>();
		for (int i = 0; i < sList.size(); i++) {
			int a = Integer.parseInt(sList.get(i));
			authority.setAppid(a);
			authority.setRid(rid);
			result = rs.addApp(authority);
			list.add(result);
		}
		ServletActionContext.getResponse().getWriter().print(result);

		return null;
	}
}
