package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtil;

import dao.PubNoticeDao;
import entity.PubNotice;

public class PubNoticeDaoImpl implements PubNoticeDao {

	@Override
	public boolean addPubNotice(PubNotice pn) {
		// TODO Auto-generated method stub
		SessionFactory sf=HibernateUtil.getSesfactory();
		Session s=null;
		Transaction ts=null;
		try {
			s=sf.openSession();
			ts=s.beginTransaction();
			s.save(pn);
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
	public List<PubNotice> getPubNotice() {
		// TODO Auto-generated method stub
		String qs="from PubNotice pn";
		List<PubNotice> list=null;
		try {
			Query query=HibernateUtil.getSesfactory().openSession().createQuery(qs);
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
	public PubNotice findPn(int pnid) {
		// TODO Auto-generated method stub
		String p="from PubNotice pn where pn.pnid="+pnid;
		Query query=(Query) HibernateUtil.getSesfactory().openSession().createQuery(p);
		List<PubNotice> list=query.list();
		if(list.size()==0)
		{
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public boolean edit(PubNotice pn) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=HibernateUtil.getSesfactory();
		Session session=null;
		Transaction tx=null;
		try {
			session=sessionFactory.openSession();
			tx=session.beginTransaction();
			session.update(pn);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			session.close();
		}
		return false;
	}

	@Override
	public boolean delPubNotice(int pnid) {
		// TODO Auto-generated method stub
		SessionFactory sf=HibernateUtil.getSesfactory();
		Session s=null;
		Transaction ts=null;
		try {
			s=sf.openSession();
			ts=s.beginTransaction();
			Object pn=s.get(PubNotice.class,pnid);
			s.delete(pn);
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

}
