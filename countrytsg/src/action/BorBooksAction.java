package action;

import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import entity.BorBooks;
import entity.User;

import service.BorBooksService;

public class BorBooksAction {
	private BorBooksService borbs;
	private BookManageAction bmAction;

	public BookManageAction getBmAction() {
		return bmAction;
	}

	public void setBmAction(BookManageAction bmAction) {
		this.bmAction = bmAction;
	}

	public BorBooksService getBorbs() {
		return borbs;
	}

	public void setBorbs(BorBooksService borbs) {
		this.borbs = borbs;
	}

	// 查找帐户id下的全部借书
	public String getBorBook() throws Exception {
		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");
		int cid = user.getCid();
		int btype = 0;
		String bb = borbs.getBorBooks(cid, btype);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(bb);
		return null;
	}

	// 查找单个读者的全部借书
	public String findUid() throws Exception {
		String userid = ServletActionContext.getRequest().getParameter("a");
		int btype = 0;
		String u = borbs.findUid(userid, btype);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(u);
		return null;
	}

	// 读者查询借阅信息
	public String findUids() throws Exception {
		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");
		String userid = user.getUserid();
		String u = borbs.findUids(userid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(u);
		return null;
	}

	// 查找单个读者的借书数目
	public String findJid() throws Exception {
		String userid = ServletActionContext.getRequest()
				.getParameter("userid");
		int btype = 0;
		int u = borbs.findJid(userid, btype);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(u);
		return null;
	}

	// 查找帐户id下的归还记录
	public String findGh() throws Exception {
		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");
		int cid = user.getCid();
		int btype = 0;
		String b = borbs.findType(cid, btype);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(b);
		return null;

	}

	public String editBorBook() throws Exception {
		int jid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("jid"));

		Date huan = new Date();

		BorBooks b = borbs.findBorBooks(jid);

		b.setBtype(0);
		b.setHuanTime(huan);
		boolean bs = borbs.editBorBooks(b);
		int bid = b.getBid();
		int num = bmAction.editNum(bid, 0);
		ServletActionContext.getResponse().getWriter().print(bs);
		return null;
	}

	public String editXu() throws Exception {
		int jid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("xid"));
		Calendar cal = Calendar.getInstance();
		// 下面的就是把当前日期加一个月
		cal.add(Calendar.MONTH, 1);

		BorBooks b = borbs.findBorBooks(jid);
		if (b.getBtype() == 2) {
			return null;
		} else {
			b.setBtype(2);
			b.setHuanTime(null);
			b.setYingTime(cal.getTime());
		}

		int bid = b.getBid();
		int num = bmAction.editNum(bid, 2);

		boolean bs = borbs.editBorBooks(b);

		ServletActionContext.getResponse().getWriter().print(bs);
		return null;
	}

	public String addBor() throws Exception {
		String userid = ServletActionContext.getRequest()
				.getParameter("userid");
		int bid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("bid"));

		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		// 下面的就是把当前日期加一个月
		cal.add(Calendar.MONTH, 1);

		BorBooks bb = new BorBooks();
		bb.setBid(bid);
		bb.setBtype(1);
		bb.setCid(user.getCid());
		bb.setYingTime(cal.getTime());
		bb.setJianTime(date);
		bb.setUserid(userid);

		int btype = 1;
		int num = bmAction.editNum(bid, btype);

		boolean b = borbs.addBorBooks(bb);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(b);
		return null;

	}
}
