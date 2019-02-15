package dao;

import java.util.List;

import entity.BookType;

public interface BookTypeDao {
	public boolean addBT(BookType bt);
	public boolean delBT(int tid);
	public List<BookType> getBT();
	BookType findBT(int tid);

}
