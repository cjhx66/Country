package service.impl;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import action.UserAction;

import dao.PubNoticeDao;
import entity.PubNotice;
import entity.User;
import service.PubNoticeService;

public class PubNoticeServiceImpl implements PubNoticeService {
	private PubNoticeDao pnDao;
	private UserAction userAction;

	public UserAction getUserAction() {
		return userAction;
	}

	public void setUserAction(UserAction userAction) {
		this.userAction = userAction;
	}

	public PubNoticeDao getPnDao() {
		return pnDao;
	}

	public void setPnDao(PubNoticeDao pnDao) {
		this.pnDao = pnDao;
	}

	@Override
	public boolean addPubNotice(PubNotice pn) {
		// TODO Auto-generated method stub
		return pnDao.addPubNotice(pn);
	}

	@Override
	public String findPubNotice(int pnid) {
		// TODO Auto-generated method stub
		PubNotice pn = pnDao.findPn(pnid);
		if (pn == null) {
			return null;
		}
		StringBuffer str = new StringBuffer();
		str.append("{pnid:" + pn.getPnid() + ",title:'" + pn.getTitle()
				+ "',content:'" + pn.getContent() + "',uname:'"
				+ userAction.findName(pn.getUid()) + "',time:'"
				+ pn.getPntime().toString().substring(0, 19) + "',num:"
				+ pn.getNum() +",active:'" + "查看"+ "'}");

		String s = str.toString();
		/*
		 * 添加查看用户的id
		 */
		ActionContext context = ActionContext.getContext();
		User user = new User();
		user = (User) context.getSession().get("user");
		String uids = "," + user.getUid();
		String[] ids = uids.split(",");
		int add = 0;
		for (int i = 0; i < ids.length; i++) {
			for (int j = 0; j < i; j++) {
				if (ids[j] == "" || ids[j].equals(ids[j + 1])) {
					add = 2;
				} else {
					add = 1;
				}
			}

		}
		pn.setNum(pn.getNum() + 1);
		pnDao.edit(pn);
		return s;
	}

	@Override
	public String getPubNotice() {
		// TODO Auto-generated method stub
		List<PubNotice> pn = pnDao.getPubNotice();
		if (pn == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < pn.size(); i++) {
			str.append("{pnid:" + pn.get(i).getPnid() + ",pntitle:'"
					+ pn.get(i).getTitle() + "',time:'"
					+ pn.get(i).getPntime().toString().substring(0, 19)
					+ "',uname:'" + userAction.findName(pn.get(i).getUid())
					+"',active:'" + "查看"+ "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + pn.size() + "}";
		return s;
	}

	@Override
	public boolean delPubNotice(int pnid) {
		// TODO Auto-generated method stub
		boolean b=pnDao.delPubNotice(pnid);
		return b;
	}

}
