package service.impl;

import java.util.Date;
import java.util.List;

import action.BookManageAction;
import action.UserAction;

import dao.CompensateDao;
import entity.Compensate;
import service.CompensateService;

public class CompensateServiceImpl implements CompensateService {
	private CompensateDao cpnDao;
	private UserAction usAction;
	private BookManageAction bmAction;

	public BookManageAction getBmAction() {
		return bmAction;
	}

	public void setBmAction(BookManageAction bmAction) {
		this.bmAction = bmAction;
	}

	public UserAction getUsAction() {
		return usAction;
	}

	public void setUsAction(UserAction usAction) {
		this.usAction = usAction;
	}

	public CompensateDao getCpnDao() {
		return cpnDao;
	}

	public void setCpnDao(CompensateDao cpnDao) {
		this.cpnDao = cpnDao;
	}

	@Override
	public boolean addCompen(Compensate cpn) {
		// TODO Auto-generated method stub
		return cpnDao.addCompen(cpn);
	}

	@Override
	public boolean editCompen(Compensate cpn) {
		// TODO Auto-generated method stub
		return cpnDao.editCompen(cpn);
	}

	@Override
	public boolean delCompen(int gid) {
		// TODO Auto-generated method stub
		boolean cpn = cpnDao.delCompen(gid);
		return cpn;
	}

	@Override
	public String getCompen(int cid) {
		// TODO Auto-generated method stub
		List<Compensate> c = cpnDao.getCompen(cid);
		if (c == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < c.size(); i++) {

			String pei = null;
			Date date = c.get(i).getPeiTime();

			if (date == null) {
				pei = null;
			} else {
				pei = c.get(i).getPeiTime().toString().substring(0, 19);
			}

			str.append("{gid:" + c.get(i).getGid() + ",userid:'"
					+ c.get(i).getUserid() + "',uname:'"
					+ usAction.findName(c.get(i).getUserid()) + "',bname:'"
					+ bmAction.findName(c.get(i).getBid()) + "',gtime:'"
					+ c.get(i).getGuaTime().toString().substring(0, 19)
					+"',bookid:'"+c.get(i).getBookid()
					+ "',yprice:'" + c.get(i).getYprice() + "',ptime:'" + pei
					+ "',sprice:'" + c.get(i).getSprice() + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		s = s + "],'total':" + c.size() + "}";
		return s;
	}

	@Override
	public Compensate findCompen(int gid) {
		// TODO Auto-generated method stub
		Compensate b = cpnDao.findCompen(gid);
		return b;
	}

	@Override
	public String findCom(int gid) {
		// TODO Auto-generated method stub
		Compensate cpn = cpnDao.findCompen(gid);
		if (cpn == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{userid:'" + cpn.getUserid() + "',bname:'"
				+ cpn.getBookid() + "',sprice:'"
				+ cpn.getSprice() + "'}");
		String s = str.toString();
		return s;
	}

}
