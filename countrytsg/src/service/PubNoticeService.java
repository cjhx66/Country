package service;


import entity.PubNotice;

public interface PubNoticeService {
	public boolean addPubNotice(PubNotice pn);//添加公告
	public String findPubNotice(int pnid);//查找单条公告
	public String getPubNotice();//查看全部公告
	public boolean delPubNotice(int pnid);

}
