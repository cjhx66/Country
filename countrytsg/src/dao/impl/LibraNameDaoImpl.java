package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtil;

import dao.LibraNameDao;
import entity.LibraryName;

public class LibraNameDaoImpl implements LibraNameDao{

	@Override
	public boolean editLibrary(LibraryName lib) {
		// TODO Auto-generated method stub
		SessionFactory sf=HibernateUtil.getSesfactory();
		Session s=null;
		Transaction ts=null;
		try {
			s=sf.openSession();
			ts=s.beginTransaction();
			s.update(lib);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			s.close();
		}
		return false;
	}

	@Override
	public LibraryName getLibrary(int cid) {
		// TODO Auto-generated method stub
		String qs="from LibraryName lib where lib.cid="+cid;
		List<LibraryName> list=new ArrayList<LibraryName>();
		try {
			Query q=HibernateUtil.getSesfactory().openSession().createQuery(qs);
			list=q.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public boolean addLibrary(LibraryName lib) {
		// TODO Auto-generated method stub
		SessionFactory sf=HibernateUtil.getSesfactory();
		Session s=null;
		Transaction ts=null;
		try {
			s=sf.openSession();
			ts=s.beginTransaction();
			s.save(lib);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			s.close();
		}
		return false;
	}

}
