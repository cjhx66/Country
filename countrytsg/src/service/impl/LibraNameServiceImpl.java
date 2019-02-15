package service.impl;

import action.CountryAction;
import action.UserAction;
import dao.LibraNameDao;
import entity.LibraryName;
import service.LibraNameService;

public class LibraNameServiceImpl implements LibraNameService {
	private LibraNameDao lndao;
	private CountryAction ctyAction;
	private UserAction userAction;

	public CountryAction getCtyAction() {
		return ctyAction;
	}

	public void setCtyAction(CountryAction ctyAction) {
		this.ctyAction = ctyAction;
	}

	public UserAction getUserAction() {
		return userAction;
	}

	public void setUserAction(UserAction userAction) {
		this.userAction = userAction;
	}

	public LibraNameDao getLndao() {
		return lndao;
	}

	public void setLndao(LibraNameDao lndao) {
		this.lndao = lndao;
	}

	@Override
	public boolean editLibrary(LibraryName lib) {
		// TODO Auto-generated method stub
		return lndao.editLibrary(lib);
	}

	@Override
	public String getLibrary(int cid) {
		// TODO Auto-generated method stub
		LibraryName libs=lndao.getLibrary(cid);
		if (libs==null) {
			return null;
		}
		StringBuilder str=new StringBuilder();
		str.append("{lname:'"+libs.getLname()
				+"',cname:'"+ctyAction.findName(libs.getCid())
				+"',uname:'"+userAction.findName(libs.getUid())
				+"',colbooks:"+libs.getColbook()+",userNum:"
				+libs.getUserNum()+",area:'"+libs.getArea()+"',lphone:'"
				+libs.getLphone()+"',laddress:'"+libs.getLaddress()+"',intro:'"
				+libs.getIntro()+"'}");
		String s=str.toString();
		return s;
	}
	@Override
	public boolean addLibrary(LibraryName lib) {
		// TODO Auto-generated method stub
		return lndao.addLibrary(lib);
	}

	@Override
	public LibraryName findLib(int cid) {
		// TODO Auto-generated method stub
		return lndao.getLibrary(cid);
	}

}
