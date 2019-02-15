package action;

import org.apache.struts2.ServletActionContext;

import entity.BookType;
import service.BookTypeService;

public class BookTypeAction {
	private BookTypeService btService;

	public BookTypeService getBtService() {
		return btService;
	}

	public void setBtService(BookTypeService btService) {
		this.btService = btService;
	}

	public String getBookType() throws Exception {
		String bt = btService.getBT();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(bt);
		return null;
	}

	public String addBT() throws Exception {
		String tname = ServletActionContext.getRequest().getParameter("tname");
		BookType bt = new BookType();
		bt.setTname(tname);
		boolean b = btService.addBT(bt);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;

	}
	
	public String delBT() throws Exception{
		String tid=ServletActionContext.getRequest().getParameter("tid");
		boolean b=btService.delBT(Integer.parseInt(tid));
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
	}
	
	public String findName(int id){
		BookType bt=btService.fBT(id);
		if (bt==null) {
			return "--";
		}else {
			return bt.getTname();
		}
	}
	/*
	 * 查找所有类型，用于在下拉列表显示
	 */
	public String getBTJson() throws Exception {
		String r = btService.getBTJson();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(r);
		return null;

	}
}
