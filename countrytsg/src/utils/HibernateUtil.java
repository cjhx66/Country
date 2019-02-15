package utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static Configuration configration;
	private static SessionFactory sessionFactory;
	
	static{
		try {
			configration=new Configuration().configure();
			 ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
					 applySettings(configration.getProperties()).buildServiceRegistry();  
			sessionFactory=configration.buildSessionFactory(serviceRegistry);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Configuration getConfigration() {
		return configration;
	}
	
	public static SessionFactory getSesfactory() {
		return sessionFactory;
	}
	
	
}
