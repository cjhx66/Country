package action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import entity.PubNotice;
import entity.User;

import service.PubNoticeService;

public class PubNoticeAction {
	private PubNoticeService pnService;
	private User logUser;
	private String pnids;

	public String getPnids() {
		return pnids;
	}

	public void setPnids(String pnids) {
		this.pnids = pnids;
	}

	public User getLogUser() {
		return logUser;
	}

	public void setLogUser(User logUser) {
		this.logUser = logUser;
	}

	public PubNoticeService getPnService() {
		return pnService;
	}

	public void setPnService(PubNoticeService pnService) {
		this.pnService = pnService;
	}
	public String getPubNotice() throws Exception{
		String pn=pnService.getPubNotice();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(pn);
		return null;	
	}
	public String findPn() throws Exception {
		int pnid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		String pns = pnService.findPubNotice(pnid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(pns);
		return null;
	}
	
	public String addPn() throws Exception {
		String title = ServletActionContext.getRequest().getParameter(
				"title");
		String content = ServletActionContext.getRequest().getParameter(
				"content");
		ActionContext actionContext=ActionContext.getContext();
		logUser = (User) actionContext.getSession().get("user");
		Date date = new Date();
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = spf.parse(spf.format(date));
		PubNotice pn = new PubNotice();
		pn.setTitle(title);
		pn.setContent(content);
		pn.setUid(logUser.getUid());
		pn.setPntime(date);
		boolean b = pnService.addPubNotice(pn);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
	}
	
	public String delPn() throws Exception {
		int num = 0;
		String[] id = pnids.split(",");
		for (int i = 0; i < id.length; i++) {
			int d=Integer.parseInt(id[i]);
			if (pnService.delPubNotice(d)) {
				num = 1;
			}
		}
		ServletActionContext.getResponse().getWriter().print(num);
		return null;
	}
}
