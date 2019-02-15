package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateSessionFactory;
import utils.HibernateUtil;
import entity.App;
import entity.AppAuthority;
import entity.Menu;
import dao.RoleDao;
import entity.Role;

public class RoleDaoImpl implements RoleDao {

	@Override
	public boolean addRole(Role role) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.save(role);
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
	public boolean delRole(int rid) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			Object role = s.get(Role.class, rid);
			s.delete(role);
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			s.close();
		}
		return true;
	}

	@Override
	public boolean editRole(Role role) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.update(role);
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			s.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRole() {
		// TODO Auto-generated method stub
		String querys = "from Role role";
		List<Role> list = null;
		try {
			Query query = HibernateUtil.getSesfactory().openSession()
					.createQuery(querys);
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
	public Role findRole(int rid) {
		// TODO Auto-generated method stub
		String qs = "from Role role where role.rid=" + rid;
		List<Role> list = new ArrayList<Role>();
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
	public List<Integer> getAllapp() {
		// TODO Auto-generated method stub

		String queryString = "from App";
		List<App> list = new ArrayList<App>();
		Query query = HibernateSessionFactory.getSession().createQuery(
				queryString);
		list = query.list();
		if (list.size() == 0) {
			return null;
		} else {
			List<Integer> appidlist = new ArrayList<Integer>();
			for (int i = 0; i < list.size(); i++) {
				appidlist.add(list.get(i).getAppId());
			}
			return appidlist;
		}
	}

	@Override
	public boolean addApp(AppAuthority authority) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = null;
		Transaction ts = null;
		try {
			session = sessionFactory.openSession();
			ts = session.beginTransaction();
			session.save(authority);
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
	public void delApp(AppAuthority appAuthority) {
		// TODO Auto-generated method stub
		SessionFactory sf = null;
		Session session = null;
		Transaction tx = null;
		try {
			sf = HibernateSessionFactory.getSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			session.delete(appAuthority);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	@Override
	public List<AppAuthority> getApp(int rid) {
		// TODO Auto-generated method stub
		String queryString = "from AppAuthority appauthority where appauthority.rid="
				+ rid;

		List<AppAuthority> list = new ArrayList<AppAuthority>();
		try {
			Query query = HibernateSessionFactory.getSession().createQuery(
					queryString);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public List<Integer> getAllMenu() {
		// TODO Auto-generated method stub
		String queryString = "from Menu";
		List<Menu> list = new ArrayList<Menu>();
		Query query = HibernateSessionFactory.getSession().createQuery(
				queryString);
		list = query.list();
		if (list.size() == 0) {
			return null;
		} else {
			List<Integer> menuidlist = new ArrayList<Integer>();
			for (int i = 0; i < list.size(); i++) {
				menuidlist.add(list.get(i).getMenuId());
			}
			return menuidlist;
		}
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		String querys = "from Role role where role.rid!=" + 1;
		List<Role> list = null;
		try {
			Query query = HibernateUtil.getSesfactory().openSession()
					.createQuery(querys);
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
}
