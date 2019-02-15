package dao;

import java.util.List;

import entity.Compensate;

public interface CompensateDao {
	public boolean addCompen(Compensate cpn);
	public boolean editCompen(Compensate cpn);
	public boolean delCompen(int gid);//判断如果有赔偿金额和赔偿时间则不可删，否则可删
	public List<Compensate> getCompen(int cid);
	public Compensate findCompen(int gid);//查找单条，用于修改

}
