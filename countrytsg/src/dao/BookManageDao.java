package dao;

import java.util.List;

import entity.BookManage;

public interface BookManageDao {
	public boolean addBM(BookManage bm);

	public boolean editBM(BookManage bm);

	public boolean delBM(int bid);

	public BookManage findBid(int bid);// 根据图书id查

	public List<BookManage> findTid(int cid,int tid);// 根据图书类型查

	public List<BookManage> findBname(int cid,String bname);// 根据图书名查

	public List<BookManage> findAuthor(int cid,String author);// 根据作者查

	public List<BookManage> findCid(int cid);// 根据帐户查

	public List<BookManage> getBM();// 查询全部

	public List<BookManage> findTimes(int cid,String date);//根据时间查
	
	public List<BookManage> findISBM(int cid,String isbn);//根据isbn查
	
	public List<BookManage> findPress(int cid,String press);//根据出版社查

	public BookManage findBns(int cid, String bookid);//根据图书名查找价格和id

	public boolean findBn(int cid, String bookid);

	public boolean findT(int cid, String tid);// 判断这种类型下是否有书

	
}
