package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtil;

import dao.CountryDao;
import entity.Country;

public class CountryDaoImpl implements CountryDao {

	@Override
	public boolean addCountry(Country cty) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.save(cty);
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			s.close();
		}
		return false;
	}

	@Override
	public boolean editCountry(Country cty) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSesfactory();
		Session session = null;
		Transaction ts = null;
		try {
			session = sessionFactory.openSession();
			ts = session.beginTransaction();
			session.update(cty);
			ts.commit();
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public Country findCountry(int cid) {
		// TODO Auto-generated method stub
		String qs = "from Country cty where cty.cid=" + cid;
		List<Country> list = new ArrayList<Country>();
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

	@Override
	public List<Country> getCountry() {
		// TODO Auto-generated method stub
		String qs = "from Country cty";
		List<Country> list = null;
		try {
			Query query = HibernateUtil.getSesfactory().openSession()
					.createQuery(qs);
			list = query.list();
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

	@Override
	public boolean delCountry(int cid) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSesfactory();
		Session session = null;
		Transaction ts = null;
		try {
			session = sessionFactory.openSession();
			ts = session.beginTransaction();
			Object cty = session.get(Country.class, cid);
			session.delete(cty);
			ts.commit();
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public List<Country> getCty(int cid) {
		// TODO Auto-generated method stub
		String qs = "from Country cty where cty.cid=" + cid;
		List<Country> list = new ArrayList<Country>();
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

}
