package action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import entity.Compensate;
import entity.User;

import service.BookManageService;
import service.CompensateService;

public class CompensateAction {
	private CompensateService cpnService;
	private BookManageService bs;
	private BookManageAction bmAction;

	public BookManageAction getBmAction() {
		return bmAction;
	}

	public void setBmAction(BookManageAction bmAction) {
		this.bmAction = bmAction;
	}

	public BookManageService getBs() {
		return bs;
	}

	public void setBs(BookManageService bs) {
		this.bs = bs;
	}

	public CompensateService getCpnService() {
		return cpnService;
	}

	public void setCpnService(CompensateService cpnService) {
		this.cpnService = cpnService;
	}

	public String getCompensate() throws Exception {
		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");
		int cid = user.getCid();
		String s = cpnService.getCompen(cid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(s);
		return null;
	}

	public String delCompen() throws Exception {
		int gid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("gid"));

		Compensate cpn = cpnService.findCompen(gid);

		Date p = cpn.getPeiTime();
		Float s = cpn.getSprice();
		if (p != null && s != null) {
			return null;
		} else {
			boolean b = cpnService.delCompen(gid);
			ServletActionContext.getResponse().getWriter().print(b);
			return null;
		}
	}

	public String addCompen() throws Exception {
		String userid = ServletActionContext.getRequest()
				.getParameter("userid");
		String bname = ServletActionContext.getRequest().getParameter("bname");

		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");
		int cid = user.getCid();

		String price = ServletActionContext.getRequest().getParameter("sprice");
		Date gtime = new Date();
		Date ptime = null;
		Float sprice = null;
		if (price != "") {
			ptime = new Date();
			sprice = Float.parseFloat(price);
		} else {
			ptime = null;
			sprice = null;
		}

		Compensate cp = new Compensate();

		int a = bs.find(cid, bname);
		cp.setBid(a);

		cp.setCid(cid);
		cp.setBookid(bname);
		cp.setGuaTime(gtime);
		cp.setPeiTime(ptime);
		cp.setSprice(sprice);
		cp.setUserid(userid);

		float b = bs.findP(cid, bname);
		cp.setYprice(b);

		int btype = 1;
		int num = bmAction.editNum(a, btype);
		if (num != 0) {
			boolean c = cpnService.addCompen(cp);
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(c);
		} else {
			return null;
		}
		return null;
	}

	public String findCompen() throws Exception {
		int gid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("gid"));
		String c = cpnService.findCom(gid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(c);
		return null;

	}

	public String editCompen() throws Exception {
		int gid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("gid"));
		String userid = ServletActionContext.getRequest()
				.getParameter("userid");
		String bname = ServletActionContext.getRequest().getParameter("bname");
		String sprice = ServletActionContext.getRequest()
				.getParameter("sprice");
		Float s = Float.parseFloat(sprice);
		
		Date ptime=null;
		
		if (sprice != "") {
			ptime = new Date();
		} else {
			ptime = null;
			sprice = null;
		}
		
		Compensate c = cpnService.findCompen(gid);

		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");
		int cid = user.getCid();

		int a = bs.find(cid, bname);
		c.setBid(a);
		c.setUserid(userid);
		c.setSprice(s);
		c.setPeiTime(ptime);
		boolean b=cpnService.editCompen(c);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;

	}
}
