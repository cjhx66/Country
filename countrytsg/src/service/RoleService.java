package service;

import java.util.List;

import entity.AppAuthority;
import entity.Role;

public interface RoleService {
	public boolean addRole(Role role);

	public boolean delRole(int rid);

	public boolean editRole(Role role);

	public String getRole();

	public Role findRole(int rid);

	public String findRoles(int rid);

	public String getRoleJSON();// 用于下拉列表的显示

	public String getAppAuthority(int rid);// 权限设置

	public List<AppAuthority> getApp(int rid);

	public void delApp(AppAuthority appAuthority);

	public boolean addApp(AppAuthority authority);

}
