package action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import entity.Role;
import entity.Rule;
import entity.User;

import service.RuleService;

public class RuleAction {
	private RuleService ruService;
	private User logUser;

	public User getLogUser() {
		return logUser;
	}

	public void setLogUser(User logUser) {
		this.logUser = logUser;
	}

	public RuleService getRuService() {
		return ruService;
	}

	public void setRuService(RuleService ruService) {
		this.ruService = ruService;
	}
	
	public String getRule() throws Exception{
		String rule=ruService.getRule();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(rule);
		return null;
	}
	public String addRule() throws Exception{
		String runame=ServletActionContext.getRequest().getParameter("runame");
		int ruday=Integer.parseInt(ServletActionContext.getRequest().getParameter("ruday"));
		int renew=Integer.parseInt(ServletActionContext.getRequest().getParameter("renew"));
		int runum=Integer.parseInt(ServletActionContext.getRequest().getParameter("runum"));
		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date addtime=sf.parse(sf.format(new Date()));
		
		ActionContext actionContext = ActionContext.getContext();
		logUser =(User) actionContext.getSession().get("user");
		
		Rule ru=new Rule();
		ru.setUserid(logUser.getUserid());
		ru.setRuday(ruday);
		ru.setRenew(renew);
		ru.setRuname(runame);
		ru.setRutime(addtime);
		ru.setRunum(runum);
		
		boolean b=ruService.addRule(ru);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
	}
	
	public String findRule() throws Exception{
		int ruid=Integer.parseInt(ServletActionContext.getRequest().getParameter("ruid"));
		String r=ruService.findRule(ruid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(r);
		return null;
	}
	
	public String editRule() throws Exception{
		String runame=ServletActionContext.getRequest().getParameter("runame");
		int ruday=Integer.parseInt(ServletActionContext.getRequest().getParameter("ruday"));
		int renew=Integer.parseInt(ServletActionContext.getRequest().getParameter("renew"));
		int runum=Integer.parseInt(ServletActionContext.getRequest().getParameter("runum"));
		
		int ruid=Integer.parseInt(ServletActionContext.getRequest().getParameter("ruid"));
		Rule r=ruService.finRule(ruid);
		r.setRuname(runame);
		r.setRuday(ruday);
		r.setRunum(runum);
		r.setRenew(renew);
		boolean b=ruService.editRule(r);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
	}
	
	public String delRule() throws Exception{
		int ruid=Integer.parseInt(ServletActionContext.getRequest().getParameter("ruid"));
		boolean b=ruService.delRule(ruid);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
		
	}
}
