package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtil;

import dao.RuleDao;
import entity.Rule;

public class RuleDaoImpl implements RuleDao {

	@Override
	public boolean addRule(Rule rule) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.save(rule);
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
	public boolean editRule(Rule rule) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.update(rule);
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
	public boolean delRule(int ruid) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			Object rule=s.get(Rule.class, ruid);
			s.delete(rule);
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
	public List<Rule> getRule() {
		// TODO Auto-generated method stub
		String qs = "from Rule ru";
		List<Rule> list = null;
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
	public Rule findRule(int ruid) {
		// TODO Auto-generated method stub
		String qs = "from Rule r where r.ruid=" + ruid;
		List<Rule> list = new ArrayList<Rule>();
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
}
