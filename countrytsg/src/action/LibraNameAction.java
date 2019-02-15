package action;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import entity.LibraryName;
import entity.User;

import service.BookManageService;
import service.LibraNameService;
import service.UserService;

public class LibraNameAction {
	private LibraNameService lnservice;
	private User logUser;
	private UserService us;
	private BookManageService bs;
	
	public BookManageService getBs() {
		return bs;
	}

	public void setBs(BookManageService bs) {
		this.bs = bs;
	}

	public UserService getUs() {
		return us;
	}

	public void setUs(UserService us) {
		this.us = us;
	}

	public User getLogUser() {
		return logUser;
	}

	public void setLogUser(User logUser) {
		this.logUser = logUser;
	}

	public LibraNameService getLnservice() {
		return lnservice;
	}

	public void setLnservice(LibraNameService lnservice) {
		this.lnservice = lnservice;
	}
	public String getLibname() throws Exception{
		ActionContext content=ActionContext.getContext();
		logUser=(User) content.getSession().get("user");
		int cid=logUser.getCid();
		String lib=lnservice.getLibrary(cid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(lib);
		return null;
	}
	
	public String addLibName() throws Exception{
		String lname=ServletActionContext.getRequest().getParameter("lname");
		String lphone=ServletActionContext.getRequest().getParameter("lphone");
		String area=ServletActionContext.getRequest().getParameter("area");
		String laddress=ServletActionContext.getRequest().getParameter("laddress");
		String intro=ServletActionContext.getRequest().getParameter("intro");
		
		ActionContext content=ActionContext.getContext();
		logUser=(User) content.getSession().get("user");
		
		LibraryName lib=new LibraryName();
		lib.setLname(lname);
		lib.setArea(area);
		lib.setIntro(intro);
		lib.setLphone(lphone);
		lib.setLaddress(laddress);
		lib.setUid(logUser.getUid());
		lib.setCid(logUser.getCid());
		
		int num=us.getRids(3,logUser.getCid());
		lib.setUserNum(num);
		
		int book=bs.getBook(logUser.getCid());
		lib.setColbook(book);
		
		boolean b=lnservice.addLibrary(lib);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
		
	}
	
	public String editLibname() throws Exception{
		String lname=ServletActionContext.getRequest().getParameter("lname");
		String lphone=ServletActionContext.getRequest().getParameter("lphone");
		String area=ServletActionContext.getRequest().getParameter("area");
		String laddress=ServletActionContext.getRequest().getParameter("laddress");
		String intro=ServletActionContext.getRequest().getParameter("intro");
		
		ActionContext content=ActionContext.getContext();
		logUser=(User) content.getSession().get("user");
		int cid=logUser.getCid();
		
		LibraryName lib=lnservice.findLib(cid);
		lib.setLname(lname);
		lib.setLphone(lphone);
		lib.setArea(area);
		lib.setLaddress(laddress);
		lib.setIntro(intro);
		boolean b=lnservice.editLibrary(lib);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
		
		
	}
}
