package dao;

import java.util.List;

import entity.AppAuthority;
import entity.Role;

public interface RoleDao {
	public boolean addRole(Role role);

	public boolean delRole(int rid);

	public boolean editRole(Role role);

	public List<Role> getRole();

	public Role findRole(int rid);
	

	public List<Integer> getAllapp();

	public boolean addApp(AppAuthority authority);

	public void delApp(AppAuthority appAuthority);

	public List<AppAuthority> getApp(int rid);

	public List<Integer> getAllMenu();

	public List<Role> getRoles();//判断获取下拉列表
}
