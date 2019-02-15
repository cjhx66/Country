package service;

import entity.Compensate;

public interface CompensateService {
	public boolean addCompen(Compensate cpn);
	public boolean editCompen(Compensate cpn);
	public boolean delCompen(int gid);//判断如果有赔偿金额和赔偿时间则不可删，否则可删
	public String getCompen(int cid);//查找全部
	public Compensate findCompen(int gid);//查找单条，用于修改
	public String findCom(int gid);//根据gid查

}
