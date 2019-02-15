package service;

import entity.BorBooks;

public interface BorBooksService {
	public boolean addBorBooks(BorBooks bb);//添加借书
	public boolean editBorBooks(BorBooks bb);//还书，续借，修改状态，还书时间
	public String getBorBooks(int cid,int btype);//根据帐户查询全部借书
	public BorBooks findBorBooks(int jid);//查看单条，用于修改
	public String findType(int cid, int btype);//根据帐户查还书记录
	public String findUid(String userid, int btype);//根据读者编号，状态查找图书
	public int findJid(String userid, int btype);//查询单个读者借了几本书
	public String findUids(String userid);//读者查询借阅信息
	public boolean delBorBooks(String userid); 

}
