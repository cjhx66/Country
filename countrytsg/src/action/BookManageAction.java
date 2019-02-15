package action;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import entity.BookManage;
import entity.User;

import service.BookManageService;
import service.BorBooksService;

public class BookManageAction {
	private BookManageService bmService;
	private User logUser;
	private String bids;

	public String getBids() {
		return bids;
	}

	public void setBids(String bids) {
		this.bids = bids;
	}

	public User getLogUser() {
		return logUser;
	}

	public void setLogUser(User logUser) {
		this.logUser = logUser;
	}

	public BookManageService getBmService() {
		return bmService;
	}

	public void setBmService(BookManageService bmService) {
		this.bmService = bmService;
	}

	// 查询全部
	public String getBM() throws Exception {
		String bm = bmService.getBM();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(bm);
		return null;
	}

	// 根据cid查
	public String getBms() throws Exception {
		ActionContext action = ActionContext.getContext();
		logUser = (User) action.getSession().get("user");
		int cid = logUser.getCid();
		String bm = bmService.findCid(cid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(bm);
		return null;
	}

	// 添加
	public String addBM() throws Exception {
		String bname = ServletActionContext.getRequest().getParameter("bname");
		String ISBN = ServletActionContext.getRequest().getParameter("ISBN");
		String author = ServletActionContext.getRequest()
				.getParameter("author");
		String press = ServletActionContext.getRequest().getParameter("press");
		String price = ServletActionContext.getRequest().getParameter("price");
		String num = ServletActionContext.getRequest().getParameter("num");
		String tname = ServletActionContext.getRequest().getParameter(
				"tname_val");
		int tid = Integer.parseInt(tname);

		ActionContext action = ActionContext.getContext();
		logUser = (User) action.getSession().get("user");

		Date date = new Date();
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = spf.parse(spf.format(date));

		String bookid = getTs(tid);

		BookManage bm = new BookManage();
		bm.setBname(bname);
		bm.setAuthor(author);
		bm.setBaddTime(date);
		bm.setBnum(Integer.parseInt(num));
		bm.setCid(logUser.getCid());
		bm.setUid(logUser.getUid());
		bm.setISBN("ISBN" + ISBN);
		bm.setPress(press);
		bm.setPrice(Float.parseFloat(price));
		bm.setTid(tid);
		bm.setBookid(bookid);
		boolean b = bmService.addBM(bm);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
	}

	// 获取判断图书编号

	public String getTs(int tid) throws Exception {
		ActionContext act = ActionContext.getContext();
		User user = (User) act.getSession().get("user");

		String td = null;
		if (tid < 10) {
			td = "00" + tid;
		} else if (tid > 9 && tid < 100) {
			td = "0" + tid;
		} else {
			td = String.valueOf(tid);
		}

		int cid = user.getCid();
		String cd = null;
		if (cid < 10) {
			cd = "00" + cid;
		} else if (cid > 9 && cid < 100) {
			cd = "0" + cid;
		} else {
			cd = String.valueOf(cid);
		}

		List<BookManage> list = bmService.getTs();
		List<Integer> l = new ArrayList<Integer>();
		String cs1 = "TS" + cd + "LX" + td;
		String cs = null;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				cs = list.get(i).getBookid();
				String abx = cs.substring(10);
				String ax = cs.substring(0, 10);
				int ab = 0;
				if (cs1.equals(ax)) {
					ab = Integer.parseInt(abx.substring(abx.indexOf("0")));
					l.add(ab);
				}

			}

			int b = 0;
			if (l.size() == 0) {
				cs1 = cs1 + "0001";
			} else {
				for (int i = 0; i < l.size(); i++) {
					if (l.get(i) > b) {
						b = l.get(i);
					}
				}
				b++;
				if (b < 10) {
					cs1 = cs1 + "000" + b;
				} else if (b > 9 && b < 100) {
					cs1 = cs1 + "00" + b;
				} else if (b > 99 && b < 1000) {
					cs1 = cs1 + "0" + b;
				} else {
					cs1 = cs1 + b;
				}
			}
			return cs1;
		} else {
			return cs1 + "0001";
		}
	}

	// 查找单条
	public String findBM() throws Exception {
		int bid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("bid"));
		String bs = bmService.findBid(bid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(bs);
		return null;
	}

	// 修改
	public String editBM() throws Exception {
		int bid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("bid"));
		String bname = ServletActionContext.getRequest().getParameter("bname");
		String ISBN = ServletActionContext.getRequest().getParameter("ISBN");
		String author = ServletActionContext.getRequest()
				.getParameter("author");
		String press = ServletActionContext.getRequest().getParameter("press");
		String price = ServletActionContext.getRequest().getParameter("price");
		String num = ServletActionContext.getRequest().getParameter("num");
		int tid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("tname_val"));

		BookManage b = bmService.findBM(bid);
		b.setAuthor(author);
		b.setBname(bname);
		b.setISBN(ISBN);
		b.setBnum(Integer.parseInt(num));
		b.setPress(press);
		b.setPrice(Float.parseFloat(price));
		b.setTid(tid);
		boolean bm = bmService.editBM(b);
		ServletActionContext.getResponse().getWriter().print(bm);
		return null;
	}

	// 删除
	public String delBM() throws Exception {
		int num = 0;
		String[] id = bids.split(",");
		for (int i = 0; i < id.length; i++) {
			if (bmService.delBM(Integer.parseInt(id[i]))) {
				num = 1;
			}
		}
		ServletActionContext.getResponse().getWriter().print(num);
		return null;
	}

	// 根据时间查
	public String findTimes() throws Exception {
		ActionContext action = ActionContext.getContext();
		logUser = (User) action.getSession().get("user");
		int cid = logUser.getCid();
		Date date = new Date();
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
		String t = spf.format(date);
		String time = bmService.findTimes(cid, t);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(time);
		return null;
	}

	// 根据图书名查
	public String findBook() throws Exception {
		String r = ServletActionContext.getRequest().getParameter("shuru");
		r = URLDecoder.decode(r, "UTF-8");
		ActionContext action = ActionContext.getContext();
		logUser = (User) action.getSession().get("user");
		int cid = logUser.getCid();
		String book = bmService.findBname(cid, r);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(book);
		return null;
	}

	// 根据ISBN查
	public String findISBN() throws Exception {
		String shuru = ServletActionContext.getRequest().getParameter("shuru");
		ActionContext action = ActionContext.getContext();
		logUser = (User) action.getSession().get("user");
		int cid = logUser.getCid();
		String isbn = bmService.findISBN(cid, shuru);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(isbn);
		return null;
	}

	// 根据出版社查
	public String findPress() throws Exception {
		String shuru = ServletActionContext.getRequest().getParameter("shuru");
		shuru = URLDecoder.decode(shuru, "UTF-8");
		ActionContext action = ActionContext.getContext();
		logUser = (User) action.getSession().get("user");
		int cid = logUser.getCid();
		String press = bmService.findPress(cid, shuru);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(press);
		return null;
	}

	// 根据作者查
	public String findAuthor() throws Exception {
		String shuru = ServletActionContext.getRequest().getParameter("shuru");
		shuru = URLDecoder.decode(shuru, "UTF-8");
		ActionContext action = ActionContext.getContext();
		logUser = (User) action.getSession().get("user");
		int cid = logUser.getCid();
		String author = bmService.findAuthor(cid, shuru);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(author);
		return null;
	}

	// 根据图书类型查
	public String findTid() throws Exception {
		int tid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("tid"));
		ActionContext action = ActionContext.getContext();
		logUser = (User) action.getSession().get("user");
		int cid = logUser.getCid();
		String t = bmService.findTid(cid, tid);
		if (t != null) {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(t);
			return null;
		} else {
			return null;
		}

	}

	// 查询图书名
	public String findName(int bid) {
		BookManage bt = bmService.findName(bid);
		if (bt == null) {
			return "--";
		} else {
			return bt.getBname();
		}
	}

	// 判断是否有这本书
	public String findBn() throws Exception {
		String bookid = ServletActionContext.getRequest().getParameter("shuru");
		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");

		int cid = user.getCid();
		boolean a = bmService.findBn(cid, bookid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(a);
		return null;
	}

	// 判断这种类型下是否有书
	public String findT() throws Exception {
		String tid = ServletActionContext.getRequest().getParameter("tid");
		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");

		int cid = user.getCid();
		boolean a = bmService.findT(cid, tid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(a);
		return null;
	}

	// 修改单个图书的num

	public int editNum(int bid, int btype) throws IOException {
		// TODO Auto-generated method stub
		BookManage bm = bmService.findBM(bid);
		int num = bm.getBnum();
		if (num == 0) {
			return 0;
		} else {
			if (btype == 1) {
				bm.setBnum(num - 1);
			} else if (btype == 0) {
				bm.setBnum(num + 1);
			} else {
				bm.setBnum(num);
			}
			boolean b = bmService.editNum(bm);
			return num;
		}
	}

	// 查找num
	public String findNum() throws Exception {
		int bid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("bid"));

		int num = bmService.findNum(bid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(num);
		return null;
	}

}
