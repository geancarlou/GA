package util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
 
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static Session getSessionFactory() {
        return sessionFactory.openSession();
    }
 
    public static void shutdown() {
    	if (getSessionFactory() != null && getSessionFactory().isOpen()){
    		getSessionFactory().close();
    	}
    }
 
}