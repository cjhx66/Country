package service.impl;

import java.util.List;

import dao.BookTypeDao;
import entity.BookType;
import service.BookTypeService;

public class BookTypeServiceImpl implements BookTypeService{
	private BookTypeDao btDao;

	public BookTypeDao getBtDao() {
		return btDao;
	}

	public void setBtDao(BookTypeDao btDao) {
		this.btDao = btDao;
	}

	@Override
	public boolean addBT(BookType bt) {
		// TODO Auto-generated method stub
		return btDao.addBT(bt);
	}

	@Override
	public boolean delBT(int tid) {
		// TODO Auto-generated method stub
		boolean b=btDao.delBT(tid);
		return b;
	}

	@Override
	public String getBT() {
		// TODO Auto-generated method stub
		List<BookType> bt1=btDao.getBT();
		if (bt1==null) {
			return null;
		}
		StringBuilder str=new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < bt1.size(); i++) {
			str.append("{tid:" + bt1.get(i).getTid() + ",tname:'"
					+ bt1.get(i).getTname()+ "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + bt1.size() + "}";
		return s;
	}

	public BookType fBT(int tid) {
		// TODO Auto-generated method stub
		return btDao.findBT(tid);
	}

	@Override
	public String getBTJson() {
		// TODO Auto-generated method stub
		List<BookType> clist = btDao.getBT();
		if (clist == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (int i = 0; i < clist.size(); i++) {
			str.append("{id:" + clist.get(i).getTid() + ",text:'"
					+ clist.get(i).getTname() + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "]";
		return s;
	}

}
