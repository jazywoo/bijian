package bijian.test;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bijian.model.bean.User;

public class TestMain {
    public static void main(String[] args) throws Exception{
//    	UserActionTest userActionTest=new UserActionTest();
//    	userActionTest.setUp();
//    	userActionTest.test_login();
    	
    	ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
    	SessionFactory sessionFactory=(SessionFactory) context.getBean("sessionFactory");
    	Session session=sessionFactory.openSession();
    	Transaction transaction=session.beginTransaction();
    	
    	User user=new User();
    	user.setUserID(100);
    	user.setUsername("ww");
    	user.setNickname("ww");
    	user.setPassword("1234567");
    	user.setCreateTime(new Date());
    	session.save(user);
    	transaction.commit();
    	session.close();
    	
    	session=sessionFactory.openSession();
    	transaction=session.beginTransaction();
    	session.save(user);
    	transaction.commit();
    	session.close();
    	
    	
    }
    public static void testDao(){
    	DaoTest daoTest=new DaoTest();
    	daoTest.testUserDao();
    }
}
