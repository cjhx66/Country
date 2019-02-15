package dao;

import java.util.List;

import entity.PubNotice;

public interface PubNoticeDao {
	public boolean addPubNotice(PubNotice pn);//添加公告
	public PubNotice findPn(int pnid);
	public List<PubNotice> getPubNotice();//查看全部公告
	public boolean edit(PubNotice pn);
	public boolean delPubNotice(int pnid);

}
