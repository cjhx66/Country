package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtil;

import dao.BookTypeDao;
import entity.BookType;

public class BookTypeDaoImpl implements BookTypeDao{

	@Override
	public boolean addBT(BookType bt) {
		// TODO Auto-generated method stub
		SessionFactory sf=HibernateUtil.getSesfactory();
		Session s=null;
		Transaction ts=null;
		try {
			s=sf.openSession();
			ts=s.beginTransaction();
			s.save(bt);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			s.close();
		}
		return false;
	}

	@Override
	public boolean delBT(int tid) {
		// TODO Auto-generated method stub
		SessionFactory sf=HibernateUtil.getSesfactory();
		Session s=null;
		Transaction ts=null;
		try {
			s=sf.openSession();
			ts=s.beginTransaction();
			Object bkt = s.get(BookType.class, tid);
			s.delete(bkt);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			s.close();
		}
		return false;
	}

	@Override
	public List<BookType> getBT() {
		// TODO Auto-generated method stub
		String querys="from BookType bt";
		List<BookType> list=null;
		try {
			Query query=HibernateUtil.getSesfactory().openSession().createQuery(querys);
			list=query.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (list.size()==0) {
			return null;
		}else{
			return list;
		}
	}

	@Override
	public BookType findBT(int tid) {
		// TODO Auto-generated method stub
		String qs="from BookType bt where bt.tid="+tid;
		List<BookType> list=new ArrayList<BookType>();
		try {
			Query q=HibernateUtil.getSesfactory().openSession().createQuery(qs);
			list=q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (list.size()==0) {
			return null;
		}else {
			return list.get(0);
		}
	}
}
