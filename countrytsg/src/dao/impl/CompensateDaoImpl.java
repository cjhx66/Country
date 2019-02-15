package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtil;

import dao.CompensateDao;
import entity.Compensate;

public class CompensateDaoImpl implements CompensateDao {

	@Override
	public boolean addCompen(Compensate cpn) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.save(cpn);
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
	public boolean editCompen(Compensate cpn) {
		// TODO Auto-generated method stub
		SessionFactory sf=HibernateUtil.getSesfactory();
		Session s=null;
		Transaction ts=null;
		try {
			s=sf.openSession();
			ts=s.beginTransaction();
			s.update(cpn);
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
	public boolean delCompen(int gid) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			Object cpn = s.get(Compensate.class, gid);
			s.delete(cpn);
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
	public List<Compensate> getCompen(int cid) {
		// TODO Auto-generated method stub
		String qs = "from Compensate c where c.cid=" + cid;
		List<Compensate> list = null;
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
	public Compensate findCompen(int gid) {
		// TODO Auto-generated method stub
		String qs = "from Compensate cpn where cpn.gid=" + gid;
		List<Compensate> list = new ArrayList<Compensate>();
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
		return list.get(0);
	}

}
