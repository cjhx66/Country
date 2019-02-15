package action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import entity.Country;
import service.CountryService;

public class CountryAction {
	private CountryService cs;

	public CountryService getCs() {
		return cs;
	}

	public void setCs(CountryService cs) {
		this.cs = cs;
	}
	
	public String getCountry() throws Exception{
		String cty=cs.getCountry();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(cty);
		return null;	
	}
	public String addCountry() throws Exception{
		String cname=ServletActionContext.getRequest().getParameter("c_name");
		String cphone=ServletActionContext.getRequest().getParameter("c_phone");
		String caddress=ServletActionContext.getRequest().getParameter("c_address");
		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date addtime=sf.parse(sf.format(new Date()));
		
		Country cty=new Country();
		cty.setCname(cname);
		cty.setCphone(cphone);
		cty.setCaddress(caddress);
		cty.setCaddTime(addtime);
		boolean b=cs.addCountry(cty);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
	}
	public String findCountry() throws Exception{
		int cid=Integer.parseInt(ServletActionContext.getRequest().getParameter("cid"));
		String c=cs.findCountry(cid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(c);
		return null;
	}
	public String editCountry() throws Exception{
		String cname=ServletActionContext.getRequest().getParameter("c_name");
		String cphone=ServletActionContext.getRequest().getParameter("c_phone");
		String caddress=ServletActionContext.getRequest().getParameter("c_address");
		int id=Integer.parseInt(ServletActionContext.getRequest().getParameter("cid"));
		Country cty=cs.findCty(id);
		cty.setCname(cname);
		cty.setCaddress(caddress);
		cty.setCphone(cphone);
		boolean b=cs.editCountry(cty);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;	
	}
	public String delCountry() throws Exception{
		String cid=ServletActionContext.getRequest().getParameter("cid");
		boolean b=cs.delCountry(Integer.parseInt(cid));
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
	}
	//查找全部,用于下拉列表
	public String getCountryJSON() throws Exception{
		String cty=cs.getCountryJSON();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(cty);
		return null;	
	}
	//查找帐户名
	public String findName(int id){
		Country d=cs.findCty(id);
		if (d==null) {
			return "--";
		} else {
			return d.getCname();
		}
	}
}
