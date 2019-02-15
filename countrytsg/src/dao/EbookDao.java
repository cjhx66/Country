package dao;

import java.util.List;

import entity.Ebook;

public interface EbookDao {
	public boolean addEbook(Ebook ek);//上传资源
	public boolean delEbook(int eid);//删除资源
	public List<Ebook> getEbook(int cid);//查找全部资源
	public Ebook findEk(int eid);
}
