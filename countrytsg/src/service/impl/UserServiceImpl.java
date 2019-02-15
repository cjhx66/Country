package service.impl;

import java.util.List;

import action.CountryAction;
import action.RoleAction;

import service.UserService;
import dao.UserDao;
import entity.User;

public class UserServiceImpl implements UserService {
	private UserDao userDao = null;
	private CountryAction ctyAction;
	private RoleAction reAction;

	public CountryAction getCtyAction() {
		return ctyAction;
	}

	public void setCtyAction(CountryAction ctyAction) {
		this.ctyAction = ctyAction;
	}

	public RoleAction getReAction() {
		return reAction;
	}

	public void setReAction(RoleAction reAction) {
		this.reAction = reAction;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		User loginUser = userDao.login(user);
		return loginUser;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	@Override
	public boolean delectUser(int uid) {
		// TODO Auto-generated method stub
		boolean b = userDao.delectUser(uid);
		return b;
	}

	@Override
	public String getUsers(int cid) {
		// TODO Auto-generated method stub
		List<User> user = userDao.getUsers(cid);
		if (user == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < user.size(); i++) {
			str.append("{UID:" + user.get(i).getUid() + ",userid:'"
					+ user.get(i).getUserid() + "',uname:'"
					+ user.get(i).getUname() + "',cname:'"
					+ ctyAction.findName(user.get(i).getCid()) + "',rname:'"
					+ reAction.findName(user.get(i).getRid()) + "',sex:'"
					+ user.get(i).getSex() + "',userPhone:'"
					+ user.get(i).getUserPhone() + "',uaddTime:'"
					+ user.get(i).getUaddTime() + "',email:'"
					+ user.get(i).getEmail() + "',address:'"
					+ user.get(i).getAddress() + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + user.size() + "}";
		return s;
	}

	@Override
	public String findUser(int uid) {
		// TODO Auto-generated method stub
		User user = userDao.findUser(uid);
		if (user == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{uid:" + user.getUid() 
				+ ",uname:'" + user.getUname()
				+ "',userid:'" + user.getUserid() 
				+ "',phone:'"
				+ user.getUserPhone() 
				+ "',email:'" + user.getEmail()
				+ "',sex:'" + user.getSex() 
				+ "',address:'" + user.getAddress()
				+ "',cid:" + user.getCid()
				+ ",rid:" + user.getRid()
				+ ",cname:'" + ctyAction.findName(user.getCid())
				+ "',rname:'"
				+ reAction.findName(user.getRid()) + "'}");
		String s = str.toString();
		return s;
	}

	@Override
	public boolean updatePwd(User user) {
		// TODO Auto-generated method stub
		boolean bl = userDao.updatepwd(user);
		return bl;
	}

	@Override
	public String getUser() {
		List<User> us = userDao.getUser();
		if (us == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < us.size(); i++) {
			str.append("{UID:" + us.get(i).getUid() + ",userid:'"
					+ us.get(i).getUserid() + "',uname:'"
					+ us.get(i).getUname() + "',cname:'"
					+ ctyAction.findName(us.get(i).getCid()) + "',rname:'"
					+ reAction.findName(us.get(i).getRid()) + "',sex:'"
					+ us.get(i).getSex() + "',userPhone:'"
					+ us.get(i).getUserPhone() + "',uaddTime:'"
					+ us.get(i).getUaddTime() + "',email:'"
					+ us.get(i).getEmail() + "',address:'"
					+ us.get(i).getAddress() + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + us.size() + "}";
		return s;
	}

	public String getUserJSON() {
		List<User> clist = userDao.getUser();
		if (clist == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (int i = 0; i < clist.size(); i++) {
			str.append("{id:" + clist.get(i).getUid() + ",text:'"
					+ clist.get(i).getUname() + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "]";
		return s;
	}

	@Override
	public User fUser(int uid) {
		// TODO Auto-generated method stub
		return userDao.findUser(uid);
	}

	@Override
	public boolean editUser(User user) {
		// TODO Auto-generated method stub
		return userDao.editUser(user);
	}

	@Override
	public int getRids(int rid,int cid) {
		// TODO Auto-generated method stub
		List<User> user = userDao.getRids(rid,cid);
		if (user==null) {
			return 0;
		}
		return user.size();
	}

	@Override
	public List<User> getUs() {
		// TODO Auto-generated method stub
		return userDao.getUser();
	}

	@Override
	public User fName(String userid) {
		// TODO Auto-generated method stub
		return userDao.findName(userid);
	}

	@Override
	public boolean fUserid(int cid, String userid) {
		// TODO Auto-generated method stub
		return userDao.fUserid(cid,userid);
	}
}
