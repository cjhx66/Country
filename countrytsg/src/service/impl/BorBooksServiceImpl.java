package service.impl;

import java.util.Date;
import java.util.List;

import action.BookManageAction;
import action.UserAction;

import dao.BorBooksDao;
import entity.BorBooks;
import service.BorBooksService;

public class BorBooksServiceImpl implements BorBooksService {
	private BorBooksDao bbd;
	private BookManageAction bmAction;
	private UserAction usAction;

	public UserAction getUsAction() {
		return usAction;
	}

	public void setUsAction(UserAction usAction) {
		this.usAction = usAction;
	}

	public BookManageAction getBmAction() {
		return bmAction;
	}

	public void setBmAction(BookManageAction bmAction) {
		this.bmAction = bmAction;
	}

	public BorBooksDao getBbd() {
		return bbd;
	}

	public void setBbd(BorBooksDao bbd) {
		this.bbd = bbd;
	}

	@Override
	// 借书
	public boolean addBorBooks(BorBooks bb) {
		// TODO Auto-generated method stub
		return bbd.addBorBooks(bb);
	}

	@Override
	// 用于修改，还书，续借
	public boolean editBorBooks(BorBooks bb) {
		// TODO Auto-generated method stub
		return bbd.editBorBooks(bb);
	}

	@Override
	// 查找单条，用于修改
	public BorBooks findBorBooks(int jid) {
		// TODO Auto-generated method stub
		BorBooks b = bbd.findBorBooks(jid);
		return b;
	}

	@Override
	// 根据读者查找
	public String findUid(String userid, int btype) {
		// TODO Auto-generated method stub
		List<BorBooks> bbs = bbd.findUid(userid, btype);
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < bbs.size(); i++) {

			int bid = bbs.get(i).getBid();
			String bname = bmAction.findName(bid);

			userid = bbs.get(i).getUserid();
			String uname = usAction.findName(userid);

			str.append("{jid:" + bbs.get(i).getJid() + ",bname:'" + bname
					+ "',uname:'" + uname + "',jieTime:'"
					+ bbs.get(i).getJianTime().toString().substring(0, 10)
					+ "',yingTime:'"
					+ bbs.get(i).getYingTime().toString().substring(0, 10)
					+ "',active:'" + "还书" + "',xu:'" + "续借" + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		s = s + "],'total':" + bbs.size() + "}";
		return s;

	}

	@Override
	// 根据帐户id,借书查找
	public String getBorBooks(int cid, int btype) {
		// TODO Auto-generated method stub
		List<BorBooks> bbs = bbd.getBorBooks(cid, btype);
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < bbs.size(); i++) {

			int bid = bbs.get(i).getBid();
			String bname = bmAction.findName(bid);

			String userid = bbs.get(i).getUserid();
			String uname = usAction.findName(userid);

			str.append("{jid:" + bbs.get(i).getJid() + ",bname:'" + bname
					+ "',userid:'" + bbs.get(i).getUserid() + "',uname:'"
					+ uname + "',jieTime:'"
					+ bbs.get(i).getJianTime().toString().substring(0, 10)
					+ "',yingTime:'"
					+ bbs.get(i).getYingTime().toString().substring(0, 10)
					+ "',active:'" + "还书" + "',xu:'" + "续借" + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		s = s + "],'total':" + bbs.size() + "}";
		return s;
	}

	// 还书
	@Override
	public String findType(int cid, int btype) {
		// TODO Auto-generated method stub
		List<BorBooks> bbs = bbd.findType(cid, btype);
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < bbs.size(); i++) {
			int bid = bbs.get(i).getBid();
			String bname = bmAction.findName(bid);
			String userid = bbs.get(i).getUserid();
			String uname = usAction.findName(userid);
			str.append("{jid:" + bbs.get(i).getJid() + ",bname:'" + bname
					+ "',userid:'" + bbs.get(i).getUserid() + "',uname:'"
					+ uname + "',jieTime:'"
					+ bbs.get(i).getJianTime().toString().substring(0, 10)
					+ "',yingTime:'"
					+ bbs.get(i).getYingTime().toString().substring(0, 10)
					+ "',huanTime:'"
					+ bbs.get(i).getHuanTime().toString().substring(0, 10)
					+ "'},");
		}
		String s = str.substring(0, str.length() - 1);
		s = s + "],'total':" + bbs.size() + "}";
		return s;
	}

	// 查询单个读者借了几本书
	@Override
	public int findJid(String userid, int btype) {
		// TODO Auto-generated method stub
		List<BorBooks> bs = bbd.findUid(userid, btype);
		if (bs == null) {
			return 0;
		}
		return bs.size();
	}

	// 读者查询借阅信息
	@Override
	public String findUids(String userid) {
		// TODO Auto-generated method stub
		List<BorBooks> bs = bbd.findUids(userid);
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < bs.size(); i++) {
			String t = null;
			int btype=bs.get(i).getBtype();
			if (btype == 1) {
				t = "借书";
			} else if (btype == 2) {
				t = "续借";
			} else {
				t = "已还";
			}
			int bid = bs.get(i).getBid();
			String bname = bmAction.findName(bid);

			String uid = bs.get(i).getUserid();
			String uname = usAction.findName(uid);

			String huan=null;
			Date date = bs.get(i).getHuanTime();
			
			if (date==null) {
				huan=null;;
			}else{
				huan=bs.get(i).getHuanTime().toString().substring(0, 10);
			}
			str.append("{userid:'" + bs.get(i).getUserid() + "',uname:'"
					+ uname + "',bname:'" + bname + "',t:'" + t + "',jieTime:'"
					+ bs.get(i).getJianTime().toString().substring(0, 10)
					+ "',yingTime:'"
					+ bs.get(i).getYingTime().toString().substring(0, 10)
					+ "',huanTime:'" + huan + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		s = s + "],'total':" + bs.size() + "}";
		return s;
	}

	@Override
	public boolean delBorBooks(String userid) {
		// TODO Auto-generated method stub
		return bbd.delBorBooks(userid);
	}

}
