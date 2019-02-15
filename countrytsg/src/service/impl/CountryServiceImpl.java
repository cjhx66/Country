package service.impl;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import dao.CountryDao;
import dao.UserDao;
import service.CountryService;
import entity.Country;
import entity.Role;
import entity.User;

public class CountryServiceImpl implements CountryService {
	private CountryDao cd = null;
	private UserDao udao=null;

	public UserDao getUdao() {
		return udao;
	}

	public void setUdao(UserDao udao) {
		this.udao = udao;
	}

	public CountryDao getCd() {
		return cd;
	}

	public void setCd(CountryDao cd) {
		this.cd = cd;
	}

	@Override
	public boolean addCountry(Country cty) {
		// TODO Auto-generated method stub
		return cd.addCountry(cty);
	}

	@Override
	public boolean editCountry(Country cty) {
		// TODO Auto-generated method stub
		return cd.editCountry(cty);
	}

	@Override
	public String findCountry(int cid) {
		// TODO Auto-generated method stub
		Country cty = cd.findCountry(cid);
		if (cty == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{cid:" + cty.getCid() 
				+ ",cname:'" + cty.getCname()
				+ "',cphone:'" + cty.getCphone() 
				+ "',caddress:'"+ cty.getCaddress() + "'}");
		String s=str.toString();
		return s;
	}

	@Override
	public String getCountry() {
		// TODO Auto-generated method stub
		List<Country> cl = cd.getCountry();
		if (cl == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < cl.size(); i++) {
			str.append("{CID:" + cl.get(i).getCid() + ",cname:'"
					+ cl.get(i).getCname() + "',cphone:'"
					+ cl.get(i).getCphone() + "',caddress:'"
					+ cl.get(i).getCaddress() + "',caddTime:'"
					+ cl.get(i).getCaddTime() + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "],'total':" + cl.size() + "}";
		return s;
	}
	/*
	 * 查找所有用户，用于在下拉列表显示
	 */
	public String getCountryJSON() {
		ActionContext a=ActionContext.getContext();
		User user=(User) a.getSession().get("user");
		List<Country> clist =null;
		if(user.getRid()==1){
			clist= cd.getCountry();
		}else{
			clist=cd.getCty(user.getCid());
		}
		if (clist == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (int i = 0; i < clist.size(); i++) {
			str.append("{id:" + clist.get(i).getCid() + ",text:'"
					+ clist.get(i).getCname() + "'},");
		}
		String s = str.substring(0, str.length() - 1);
		str.append("]");
		s = s + "]";
		return s;
	}

	@Override
	public Country findCty(int cid) {
		// TODO Auto-generated method stub
		Country c=cd.findCountry(cid);
		return c;
	}

	@Override
	public boolean delCountry(int cid) {
		// TODO Auto-generated method stub
		boolean c=udao.getUsers(cid) != null;
		if(c){
			return false;
		}else{
		boolean b=cd.delCountry(cid);
		return b;
		}
	}

}
