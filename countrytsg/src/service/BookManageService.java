package service;

import java.util.List;

import entity.BookManage;

public interface BookManageService {

	public boolean addBM(BookManage bm);

	public boolean editBM(BookManage bm);

	public boolean delBM(int bid);

	public String findBid(int bid);// 根据图书id查

	public String findTid(int cid,int tid);// 根据图书类型查

	public String findBname(int cid,String bname);// 根据图书名查

	public String findAuthor(int cid,String author);// 根据作者查

	public String findCid(int cid);// 根据帐户查

	public String getBM();// 查询全部

	public BookManage findBM(int bid);// 查找单条，用于修改

	public String findTimes(int cid,String date);

	public String findISBN(int cid,String isbn);// 根据isbn查

	public String findPress(int cid,String press);// 根据出版社查

	public BookManage findName(int id);// 查询图书名

	public int findNum(int bid);// 查找有几本书

	public boolean editNum(BookManage b);//修改图书数量

	public int getBook(int cid);//共有多少本书

	public int find(int cid, String bookid);//根据图书名查找图书id和价格
	
	public Float findP(int cid, String bname);//根据图书名查找图书id和价格

	public boolean findBn(int cid, String bookid);

	public boolean findT(int cid, String tid);// 判断这种类型下是否有书

	public List<BookManage> getTs();//图书编号的自增设置

}
