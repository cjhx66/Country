package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.UserDao;

import utils.HibernateUtil;

import entity.User;

public class UserDaoImpl implements UserDao {

	public User login(User user) {
		// TODO Auto-generated method stub
		String queryString = "from User u where u.userid='" + user.getUserid()
				+ "'and u.pwd='" + user.getPwd() + "'";
		User user1 = null;
		try {
			Query query = HibernateUtil.getSesfactory().openSession()
					.createQuery(queryString);
			List list = query.list();
			if (list.size() == 0) {
				user1 = null;
			} else {
				user1 = (User) list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user1;

	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.save(user);
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
	public boolean delectUser(int uid) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			Object user = s.get(User.class, uid);
			s.delete(user);
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			s.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	// 根据cid查询
	public List<User> getUsers(int cid) {
		// TODO Auto-generated method stub
		String qs = "from User u where u.cid=" + cid;
		List<User> list = new ArrayList<User>();
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

	@Override
	// 查询单个信息
	public User findUser(int uid) {
		// TODO Auto-generated method stub
		String qs = "from User u where u.uid=" + uid;
		List<User> list = null;
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
	// 修改密码
	public boolean updatepwd(User user) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		String qs = "from User u where u.userid='" + user.getUserid() + "'";
		User dbuser = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			Query q = s.createQuery(qs);
			List list = q.list();
			dbuser = (User) list.get(0);
			dbuser.setPwd(user.getPwd());
			s.update(dbuser);
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
	// 查找全部
	public List<User> getUser() {
		// TODO Auto-generated method stub
		String qs = "from User user";
		List<User> list = null;
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
	public boolean editUser(User user) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSesfactory();
		Session s = null;
		Transaction ts = null;
		try {
			s = sf.openSession();
			ts = s.beginTransaction();
			s.update(user);
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
	public List<User> getRids(int rid,int cid) {
		// TODO Auto-generated method stub
		String qs = "from User u where u.rid=" + rid+" and u.cid="+cid+"";
		List<User> list = null;
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

	@Override
	public User findName(String userid) {
		// TODO Auto-generated method stub
		String qs = "from User u where u.userid='" + userid+"'";
		List<User> list =null;
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
	public boolean fUserid(int cid, String userid) {
		// TODO Auto-generated method stub
		String qs="from User u where u.cid="+cid+" and u.userid='"+userid+"'";
		List<User> list = null;
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