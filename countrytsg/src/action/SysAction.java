package action;




import org.apache.struts2.ServletActionContext;

import service.SysService;

import com.opensymphony.xwork2.ActionContext;

import entity.User;

public class SysAction {
	private SysService sysService= null;

	public SysService getSysService() {
		return sysService;
	}

	public void setSysService(SysService sysService) {
		this.sysService = sysService;
	}
	public String getApps() throws Exception{
		User logUser = null;
		ActionContext actionContext = ActionContext.getContext();
		logUser=(User) actionContext.getSession().get("user");
		String toolbarscript = sysService.getApps(logUser);
		
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(toolbarscript);
		return null;
	}
	public String getMenus() throws Exception{
		User logUser = null;
		int appid = Integer.parseInt(ServletActionContext.getRequest().getParameter("appid"));
		ActionContext actionContext = ActionContext.getContext();
		logUser=(User) actionContext.getSession().get("user");
		String menus = sysService.getMenu(appid,logUser);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(menus);
		return null;
	}
	public String getButtons() throws Exception{
		User logUser = null;
		ActionContext actionContext = ActionContext.getContext();
		logUser = (User) actionContext.getSession().get("user");
		int menuId = Integer.parseInt(ServletActionContext.getRequest().getParameter("menuId"));
		String toolbarscript = sysService.getButtons(logUser,menuId);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(toolbarscript);
		return null;
	}
	

}
