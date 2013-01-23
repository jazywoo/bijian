package bijian.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static final SessionFactory sessionFactory=new Configuration().buildSessionFactory();;
    public static final ThreadLocal session=new ThreadLocal();
    public static Session currentSession(){
    	Session s=(Session)session.get();
    	if(s==null){
    		s=sessionFactory.openSession();
    		session.set(s);
    	}
    	return s;
    }
    public static void closeSession(){
    	Session s=(Session)session.get();
    	if(s!=null){
    		s.close();
    	}
    	session.set(null);
    }
}
