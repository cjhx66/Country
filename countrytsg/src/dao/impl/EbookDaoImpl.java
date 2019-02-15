package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtil;

import dao.EbookDao;
import entity.Ebook;

public class EbookDaoImpl implements EbookDao {

	@Override
	public boolean addEbook(Ebook ek) {
		// TODO Auto-generated method stub
		SessionFactory sf=HibernateUtil.getSesfactory();
		Session s=null;
		Transaction ts=null;
		try {
			s=sf.openSession();
			ts=s.beginTransaction();
			s.save(ek);
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
	public boolean delEbook(int eid) {
		// TODO Auto-generated method stub
		SessionFactory sf=HibernateUtil.getSesfactory();
		Session s=null;
		Transaction ts=null;
		try {
			s=sf.openSession();
			ts=s.beginTransaction();
			Object ek=s.get(Ebook.class, eid);
			s.delete(ek);
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
	public List<Ebook> getEbook(int cid) {
		// TODO Auto-generated method stub
		String qs="from Ebook e where e.cid="+cid;
		List<Ebook> list=null;
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
			return list;
		}
	}

	@Override
	public Ebook findEk(int eid) {
		// TODO Auto-generated method stub
		String qs="from Ebook e where e.eid="+eid;
		Query q=HibernateUtil.getSesfactory().openSession().createQuery(qs);
		List<Ebook> list=q.list();
		if (list.size()==0) {
			return null;
		}else{
			return list.get(0);
		}
	}

}
