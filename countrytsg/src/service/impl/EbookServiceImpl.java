package service.impl;

import java.util.List;

import dao.EbookDao;
import entity.Ebook;
import service.EbookService;

public class EbookServiceImpl implements EbookService{
	
	private EbookDao ed;

	public EbookDao getEd() {
		return ed;
	}

	public void setEd(EbookDao ed) {
		this.ed = ed;
	}

	@Override
	public boolean addEbook(Ebook ek) {
		// TODO Auto-generated method stub
		boolean b=ed.addEbook(ek);
		return b;
	}

	@Override
	public boolean delEbook(int eid) {
		// TODO Auto-generated method stub
		return ed.delEbook(eid);
	}

	@Override
	public String getEbook(int cid) {
		// TODO Auto-generated method stub
		List<Ebook> ek=ed.getEbook(cid);
		StringBuilder str=new StringBuilder();
		str.append("{'Rows':");
		str.append("[");
		for (int i = 0; i < ek.size(); i++) {
			str.append("{eid:"+ek.get(i).getEid()
					+",bname:'"+ek.get(i).getBname()
					+"',type:'"+ek.get(i).getType()
					+"',time:'"+ek.get(i).getTime().toString().substring(0,19)
					+"',down:'"+"下载"+"'},");
		}
		String s=str.substring(0,str.length()-1);
		s=s+"],'total':"+ek.size()+"}";
		return s;
	}

	@Override
	public Ebook findEk(int eid) {
		// TODO Auto-generated method stub
		return ed.findEk(eid);
	}

}
