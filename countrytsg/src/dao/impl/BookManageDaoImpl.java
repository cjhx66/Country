package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtil;

import dao.BookManageDao;
import entity.BookManage;

public class BookManageDaoImpl implements BookManageDao {

	@Override
	public boolean addBM(BookManage bm) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.save(bm);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			s.close();
		}
		return false;
	}

	@Override
	public boolean editBM(BookManage bm) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.update(bm);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			s.close();
		}
		return false;
	}

	@Override
	public boolean delBM(int bid) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			Object bm = s.get(BookManage.class, bid);
			s.delete(bm);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			s.close();
		}
		return false;
	}

	// 根据图书id查
	@Override
	public BookManage findBid(int bid) {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm where bm.bid=" + bid;
		List<BookManage> list = new ArrayList<BookManage>();
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			list = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	// 根据类型查
	@Override
	public List<BookManage> findTid(int cid, int tid) {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm where bm.tid=" + tid + " and bm.cid="
				+ cid;
		List<BookManage> list = new ArrayList<BookManage>();
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			list = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		} else {
			return list;
		}

	}

	// 根据作者查
	@Override
	public List<BookManage> findAuthor(int cid, String author) {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm where bm.cid=" + cid
				+ " and bm.author like'" + author + "%'";
		List<BookManage> b = new ArrayList<BookManage>();
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			b = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (b.size() == 0) {
			return null;
		} else {
			return b;
		}
	}

	// 根据帐户查
	@Override
	public List<BookManage> findCid(int cid) {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm where bm.cid=" + cid;
		List<BookManage> list = new ArrayList<BookManage>();
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			list = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	// 查找全部
	@Override
	public List<BookManage> getBM() {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm";
		List<BookManage> bks = null;
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			bks = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (bks.size() == 0) {
			return null;
		} else {
			return bks;
		}
	}

	// 根据时间查
	@Override
	public List<BookManage> findTimes(int cid, String date) {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm where bm.cid=" + cid
				+ " and bm.baddTime like'" + date + " %'";
		List<BookManage> list = new ArrayList<BookManage>();
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			list = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	// 根据图书名查
	@Override
	public List<BookManage> findBname(int cid, String bname) {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm where bm.cid=" + cid
				+ " and bm.bname like'%" + bname + "%'";
		List<BookManage> b = new ArrayList<BookManage>();
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			b = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (b.size() == 0) {
			return null;
		} else {
			return b;
		}
	}

	// 根据isbn查
	@Override
	public List<BookManage> findISBM(int cid, String isbn) {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm where bm.cid=" + cid + " and bm.ISBN='"
				+ isbn + "'";
		List<BookManage> b = new ArrayList<BookManage>();
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			b = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (b.size() == 0) {
			return null;
		} else {
			return b;
		}
	}

	// 根据出版社查
	@Override
	public List<BookManage> findPress(int cid, String press) {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm where bm.cid=" + cid
				+ " and bm.press like'" + press + "%'";
		List<BookManage> b = new ArrayList<BookManage>();
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			b = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (b.size() == 0) {
			return null;
		} else {
			return b;
		}
	}

	// 根据帐户和图书名查
	@Override
	public boolean findBn(int cid, String bookid) {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm where bm.cid=" + cid
				+ " and bm.bookid='" + bookid + "'";
		List<BookManage> bks = null;
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			bks = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (bks.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public BookManage findBns(int cid, String bookid) {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm where bm.cid=" + cid
				+ " and bm.bookid='" + bookid + "'";
		List<BookManage> bks = null;
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			bks = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (bks.size() == 0) {
			return null;
		} else {
			return bks.get(0);
		}
	}

	@Override
	public boolean findT(int cid, String tid) {
		// TODO Auto-generated method stub
		String qs = "from BookManage bm where bm.tid=" + tid + " and bm.cid="
				+ cid;
		List<BookManage> list = new ArrayList<BookManage>();
		try {
			Query q = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			list = q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

}
