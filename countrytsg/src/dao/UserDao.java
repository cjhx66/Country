package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	public User login(User user);//登录用户
	public boolean addUser(User user);//添加用户
	public boolean delectUser(int uid);//删除用户
	public List<User> getUsers(int cid);//根据cid查找用户
	public List<User> getUser();//查找全部用户
	public User findUser(int uid);//查找单个用户
	public boolean updatepwd(User user);//修改密码
	public boolean editUser(User user);//修改用户
	public List<User> getRids(int rid, int cid);
	public User findName(String userid);
	public boolean fUserid(int cid, String userid);//判断是否有此用户
}
