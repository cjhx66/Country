package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtil;

import dao.BorBooksDao;
import entity.BookType;
import entity.BorBooks;

public class BorBooksDaoImpl implements BorBooksDao {

	// 添加借书
	@Override
	public boolean addBorBooks(BorBooks bb) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.save(bb);
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

	// 还书，续借，修改状态，还书时间
	@Override
	public boolean editBorBooks(BorBooks bb) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.update(bb);
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

	// 根据cid查询全部借书
	@Override
	public List<BorBooks> getBorBooks(int cid, int btype) {
		// TODO Auto-generated method stub
		String qs = "from BorBooks bb where bb.cid=" + cid + " and btype!="
				+ btype + "";
		List<BorBooks> list = null;
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
		}
		return list;
	}

	// 查看单条，用于修改
	@Override
	public BorBooks findBorBooks(int jid) {
		// TODO Auto-generated method stub
		String qs = "from BorBooks bb where bb.jid=" + jid;
		List<BorBooks> list = new ArrayList<BorBooks>();
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

	// 根据cid查询全部还书
	@Override
	public List<BorBooks> findType(int cid, int btype) {
		// TODO Auto-generated method stub
		String qs = "from BorBooks bb where bb.cid=" + cid + " and btype="
				+ btype + "";
		List<BorBooks> list = null;
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
		}
		return list;
	}

	// 根据userid和状态查
	@Override
	public List<BorBooks> findUid(String userid, int btype) {
		// TODO Auto-generated method stub
		String qs = "from BorBooks bb where bb.userid='" + userid
				+ "' and btype!=" + btype + "";
		List<BorBooks> list = null;
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
		}
		return list;
	}

	// 读者查询借阅信息
	@SuppressWarnings("unchecked")
	@Override
	public List<BorBooks> findUids(String userid) {
		// TODO Auto-generated method stub
		String qs = "from BorBooks bb where bb.userid='" + userid + "'";
		List<BorBooks> list = null;
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
		}
		return list;
	}
	
	@Override
	public boolean delBorBooks(String userid) {
		// TODO Auto-generated method stub
		List<BorBooks> bbs=findUids(userid);
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			Object bs=null;
			for (int i = 0; i < bbs.size(); i++) {
				bs=bbs.get(i);
				s.delete(bs);
			}
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
}
