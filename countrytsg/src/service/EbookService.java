package service;

import entity.Ebook;

public interface EbookService {
	public boolean addEbook(Ebook ek);//上传资源
	public boolean delEbook(int eid);//删除资源
	public String getEbook(int cid);//查找全部资源
	public Ebook findEk(int eid);//根据id查找文件
	
	

}
