package service;
import java.util.List;

import entity.User;

public interface UserService {
	public User login(User user);//登录用户
	public boolean addUser(User user);//添加用户
	public boolean delectUser(int uid);//删除用户
	public boolean editUser(User user);//修改用户
	public String getUser();//查找全部用户
	public String getUsers(int cid);//根据cid查找全部用户
	public User fUser(int uid);//根据uid查找单个用户名
	public boolean updatePwd(User user);//修改登录密码
	public String findUser(int uid);
	public int getRids(int rid, int cid);//根据rid查找全部用户
	public List<User> getUs();//用户id的自增设置
	public User fName(String userid);//根据userid查找用户名
	public boolean fUserid(int cid, String userid);//判断是否有此用户
}
