package service;

import entity.LibraryName;

public interface LibraNameService {
	public boolean editLibrary(LibraryName lib);
	public String getLibrary(int cid);//查找单个本馆信息
	public boolean addLibrary(LibraryName lib);//如果cid中没有本馆信息，则添加
	public LibraryName findLib(int cid);
	

}
