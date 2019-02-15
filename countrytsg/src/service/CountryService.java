package service;

import entity.Country;

public interface CountryService {
	public boolean addCountry(Country cty);
	public boolean editCountry(Country cty);
	public boolean delCountry(int cid);
	public String getCountry();//查找全部
	public String findCountry(int cid);//查找单个帐户
	public Country findCty(int cid);
	public String getCountryJSON();//查找全部帐户，用于下拉列表
	
	
}
