package dao;

import java.util.List;

import entity.Country;

public interface CountryDao {
	public boolean addCountry(Country cty);
	public boolean editCountry(Country cty);
	public List<Country> getCountry();
	public Country findCountry(int cid);
	public boolean delCountry(int cid);
	public List<Country> getCty(int cid);
}
