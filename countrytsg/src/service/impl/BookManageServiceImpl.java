package service.impl;

import java.util.List;

import action.BookTypeAction;
import action.CountryAction;
import action.UserAction;

import dao.BookManageDao;
import entity.BookManage;
import service.BookManageService;

public class BookManageServiceImpl implements BookManageService {
	private BookManageDao bmDao;
	private CountryAction ctyAction;
	private UserAction usAction;
	private BookTypeAction btAction;

	public CountryAction getCtyAction() {
		return ctyAction;
	}

	public void setCtyAction(CountryAction ctyAction) {
		this.ctyAction = ctyAction;
	}

	public UserAction getUsAction() {
		return usAction;
	}

	public void setUsAction(UserAction usAction) {
		this.usAction = usAction;
	}

	public BookTypeAction getBtAction() {
		return btAction;
	}

	public void setBtAction(BookTypeAction btAction) {
		this.btAction = btAction;
	}

	public BookManageDao getBmDao() {
		return bmDao;
	}

	public void setBmDao(BookManageDao bmDao) {
		this.bmDao = bmDao;
	}

	@Override
	public boolean addBM(BookManage bm) {
		// TODO Auto-generated method stub
		return bmDao.addBM(bm);
	}

	@Override
	public boolean editBM(BookManage bm) {
		// TODO Auto-generated method stub
		return bmDao.editBM(bm);
	}

	@Override
	public boolean delBM(int bid) {
		// TODO Auto-generated method stub
		return bmDao.delBM(bid);
	}

	// 根据图书id查
	@Override
	public String findBid(int bid) {
		// TODO Auto-generated method stub
		BookManage bm = bmDao.findBid(bid);
		if (bm == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{bid:" + bm.getBid() + ",bname:'" + bm.getBname()
				+ "',tid:" + bm.getTid() + ",tname:'"
				+ btAction.findName(bm.getTid()) + "',ISBN:'" + bm.getISBN()
				+ "',author:'" + bm.getAuthor() + "',press:'" + bm.getPress()
				+ "',price:" + bm.getPrice() + ",num:" + bm.getBnum() + "}");
		String s = str.toString();
		return s;
	}

	// 根据图书类型查
	@Override
	public String findTid(int cid, int tid) {
		// TODO Auto-generated method stub
		List<BookManage> bm = bmDao.findTid(cid, tid);
		if (bm == null) {
			return null;
		} else {
			StringBuilder str = new StringBuilder();
			str.append("{'Rows':");
			str.append("[");
			for (int i = 0; i < bm.size(); i++) {
				str.append("{bname:'" + bm.get(i).getBname() + "',bookid:'"
						+ bm.get(i).getBookid() + "',ISBN:'"
						+ bm.get(i).getISBN() + "',author:'"
						+ bm.get(i).getAuthor() + "',press:'"
						+ bm.get(i).getPress() + "',num:" + bm.get(i).getBnum()
						+ ",price:'" + bm.get(i).getPrice() + "'},");
			}
			String s = str.substring(0, str.length() - 1);
			/* str.append("]"); */
			s = s + "],'total':" + bm.size() + "}";
			return s;
		}

	}

	@Override
	// 根据作者查
	public String findAuthor(int cid, String author) {
		// TODO Auto-generated method stub
		List<BookManage> bm = bmDao.findAuthor(cid, author);
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < bm.size(); i++) {
			str.append("{bid:" + bm.get(i).getBid() + ",cname:'"
					+ ctyAction.findName(bm.get(i).getCid()) + "',bname:'"
					+ bm.get(i).getBname() + "',ISBN:'" + bm.get(i).getISBN()
					+ "',bookid:'" + bm.get(i).getBookid() + "',btype:'"
					+ btAction.findName(bm.get(i).getTid()) + "',author:'"
					+ bm.get(i).getAuthor() + "',press:'"
					+ bm.get(i).getPress() + "',price:'" + bm.get(i).getPrice()
					+ "',num:" + bm.get(i).getBnum() + ",jie:'" + "借书"
					+ "',addtime:'"
					+ bm.get(i).getBaddTime().toString().substring(0, 19)
					+ "',uname:'" + usAction.findName(bm.get(i).getUid())
					+ "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + bm.size() + "}";
		return s;
	}

	// 根据帐户查
	@Override
	public String findCid(int cid) {
		// TODO Auto-generated method stub
		List<BookManage> b = bmDao.findCid(cid);
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < b.size(); i++) {
			str.append("{bid:" + b.get(i).getBid() + ",bname:'"
					+ b.get(i).getBname() + "',ISBN:'" + b.get(i).getISBN()
					+ "',bookid:'" + b.get(i).getBookid() + "',btype:'"
					+ btAction.findName(b.get(i).getTid()) + "',author:'"
					+ b.get(i).getAuthor() + "',press:'" + b.get(i).getPress()
					+ "',price:'" + b.get(i).getPrice() + "',num:"
					+ b.get(i).getBnum() + ",addtime:'"
					+ b.get(i).getBaddTime().toString().substring(0, 19)
					+ "',uname:'" + usAction.findName(b.get(i).getUid())
					+ "',jie:'" + "借书" + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		s = s + "],'total':" + b.size() + "}";
		return s;
	}

	// 查找全部
	@Override
	public String getBM() {
		// TODO Auto-generated method stub
		List<BookManage> b = bmDao.getBM();
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < b.size(); i++) {
			str.append("{bid:" + b.get(i).getBid() + ",cname:'"
					+ ctyAction.findName(b.get(i).getCid()) + "',bookid:'"
					+ b.get(i).getBookid() + "',bname:'" + b.get(i).getBname()
					+ "',ISBN:'" + b.get(i).getISBN() + "',btype:'"
					+ btAction.findName(b.get(i).getTid()) + "',author:'"
					+ b.get(i).getAuthor() + "',press:'" + b.get(i).getPress()
					+ "',price:'" + b.get(i).getPrice() + "',num:"
					+ b.get(i).getBnum() + ",addtime:'"
					+ b.get(i).getBaddTime().toString().substring(0, 19)
					+ "',uname:'" + usAction.findName(b.get(i).getUid())
					+ "',jie:'" + "借书" + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + b.size() + "}";
		return s;
	}

	// 查找单条，用于修改
	@Override
	public BookManage findBM(int bid) {
		// TODO Auto-generated method stub
		BookManage b = bmDao.findBid(bid);
		return b;
	}

	@Override
	// 根据时间查
	public String findTimes(int cid, String date) {
		// TODO Auto-generated method stub
		List<BookManage> b = bmDao.findTimes(cid, date);
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < b.size(); i++) {
			str.append("{bid:" + b.get(i).getBid() + ",bookid:'"
					+ b.get(i).getBookid() + "',cname:'"
					+ ctyAction.findName(b.get(i).getCid()) + "',bname:'"
					+ b.get(i).getBname() + "',ISBN:'" + b.get(i).getISBN()
					+ "',btype:'" + btAction.findName(b.get(i).getTid())
					+ "',author:'" + b.get(i).getAuthor() + "',press:'"
					+ b.get(i).getPress() + "',price:'" + b.get(i).getPrice()
					+ "',num:" + b.get(i).getBnum() + ",addtime:'"
					+ b.get(i).getBaddTime().toString().substring(0, 19)
					+ "',uname:'" + usAction.findName(b.get(i).getUid())
					+ "',jie:'" + "借书" + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + b.size() + "}";
		return s;
	}

	@Override
	// 根据图书名查
	public String findBname(int cid, String bname) {
		// TODO Auto-generated method stub
		List<BookManage> bm = bmDao.findBname(cid, bname);
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < bm.size(); i++) {
			str.append("{bid:" + bm.get(i).getBid() + ",cname:'"
					+ ctyAction.findName(bm.get(i).getCid()) + "',bname:'"
					+ bm.get(i).getBname() + "',ISBN:'" + bm.get(i).getISBN()
					+ "',bookid:'" + bm.get(i).getBookid() + "',btype:'"
					+ btAction.findName(bm.get(i).getTid()) + "',author:'"
					+ bm.get(i).getAuthor() + "',press:'"
					+ bm.get(i).getPress() + "',price:'" + bm.get(i).getPrice()
					+ "',num:" + bm.get(i).getBnum() + ",jie:'" + "借书"
					+ "',addtime:'"
					+ bm.get(i).getBaddTime().toString().substring(0, 19)
					+ "',uname:'" + usAction.findName(bm.get(i).getUid())
					+ "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + bm.size() + "}";
		return s;
	}

	@Override
	// 根据ISBN查
	public String findISBN(int cid, String isbn) {
		// TODO Auto-generated method stub
		List<BookManage> bm = bmDao.findISBM(cid, isbn);
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < bm.size(); i++) {
			str.append("{bid:" + bm.get(i).getBid() + ",cname:'"
					+ ctyAction.findName(bm.get(i).getCid()) + "',bname:'"
					+ bm.get(i).getBname() + "',ISBN:'" + bm.get(i).getISBN()
					+ "',bookid:'" + bm.get(i).getBookid() + "',btype:'"
					+ btAction.findName(bm.get(i).getTid()) + "',author:'"
					+ bm.get(i).getAuthor() + "',press:'"
					+ bm.get(i).getPress() + "',price:'" + bm.get(i).getPrice()
					+ "',num:" + bm.get(i).getBnum() + ",jie:'" + "借书"
					+ "',addtime:'"
					+ bm.get(i).getBaddTime().toString().substring(0, 19)
					+ "',uname:'" + usAction.findName(bm.get(i).getUid())
					+ "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + bm.size() + "}";
		return s;
	}

	@Override
	// 根据出版社查
	public String findPress(int cid, String press) {
		// TODO Auto-generated method stub
		List<BookManage> bm = bmDao.findPress(cid, press);
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < bm.size(); i++) {
			str.append("{bid:" + bm.get(i).getBid() + ",cname:'"
					+ ctyAction.findName(bm.get(i).getCid()) + "',bname:'"
					+ bm.get(i).getBname() + "',ISBN:'" + bm.get(i).getISBN()
					+ "',bookid:'" + bm.get(i).getBookid() + "',btype:'"
					+ btAction.findName(bm.get(i).getTid()) + "',author:'"
					+ bm.get(i).getAuthor() + "',press:'"
					+ bm.get(i).getPress() + "',price:'" + bm.get(i).getPrice()
					+ "',num:" + bm.get(i).getBnum() + ",jie:'" + "借书"
					+ "',addtime:'"
					+ bm.get(i).getBaddTime().toString().substring(0, 19)
					+ "',uname:'" + usAction.findName(bm.get(i).getUid())
					+ "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + bm.size() + "}";
		return s;
	}

	// 查询图书名
	@Override
	public BookManage findName(int id) {
		// TODO Auto-generated method stub
		return bmDao.findBid(id);
	}

	// 判断是否有这本书
	@Override
	public boolean findBn(int cid, String bookid) {
		// TODO Auto-generated method stub
		return bmDao.findBn(cid, bookid);
	}

	// 查找有几本书
	public int findNum(int bid) {
		BookManage bm = bmDao.findBid(bid);
		int num = bm.getBnum();
		if (num == 0) {
			return 0;
		} else {
			return num;
		}
	}

	// 修改图书数量
	@Override
	public boolean editNum(BookManage b) {
		// TODO Auto-generated method stub
		return bmDao.editBM(b);
	}

	// 共有多少本书
	@Override
	public int getBook(int cid) {
		// TODO Auto-generated method stub
		List<BookManage> bm = bmDao.findCid(cid);
		if (bm == null) {
			return 0;
		} else {
			return bm.size();
		}

	}

	// 根据图书编号查找图书id
	@Override
	public int find(int cid, String bookid) {
		// TODO Auto-generated method stub
		BookManage bm = bmDao.findBns(cid, bookid);
		int a = bm.getBid();
		return a;
	}

	// 根据图书名查找图书价格
	public Float findP(int cid, String bookid) {
		// TODO Auto-generated method stub
		BookManage bm = bmDao.findBns(cid, bookid);
		float b = bm.getPrice();
		return b;
	}

	@Override
	public boolean findT(int cid, String tid) {
		// TODO Auto-generated method stub
		return bmDao.findT(cid, tid);
	}

	@Override
	public List<BookManage> getTs() {
		// TODO Auto-generated method stub
		return bmDao.getBM();
	}
}
