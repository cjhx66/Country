package dao;

import java.util.List;

import entity.BorBooks;

public interface BorBooksDao {
	public boolean addBorBooks(BorBooks bb);//添加借书
	public boolean editBorBooks(BorBooks bb);//还书，续借，修改状态，还书时间
	public List<BorBooks> getBorBooks(int cid,int btype);//根据cid查询全部借书
	public BorBooks findBorBooks(int jid);//查看单条，用于修改
	public List<BorBooks> findType(int cid, int btype);//根据cid查询全部还书
	public List<BorBooks> findUid(String userid, int btype);//根据userid和状态查
	public List<BorBooks> findUids(String userid);//读者查询借阅信息
	public boolean delBorBooks(String userid);
	
	

}
