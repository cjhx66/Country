package service;

import entity.BookType;

public interface BookTypeService {
	public boolean addBT(BookType bt);
	public boolean delBT(int tid);
	public String getBT();
	public BookType fBT(int tid);
	public String getBTJson();

}
