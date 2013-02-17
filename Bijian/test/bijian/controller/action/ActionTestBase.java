package bijian.controller.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsSpringTestCase;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import bijian.controller.action.UserAction;

public class ActionTestBase  extends StrutsSpringTestCase {
	
	 /*这个函数相当@Before注解的函数，是调用单元测试后的时候，
	    首先会执行的方法。可以在这里面做一些必要的准备工作*/
	 @Override
	 protected void setUp() throws Exception {
	     super.setUp();
	     SessionFactory sessionFactory = lookupSessionFactory(this.request);
	     Session hibernateSession= getSession(sessionFactory);
	     TransactionSynchronizationManager.bindResource(sessionFactory,new SessionHolder(hibernateSession));
	 }
	 @Override
	 public String getContextLocations() {
	   //返回你项目中spring配置文件所在的目录
	   return "/spring.xml";
	 }
	 private Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
	     Session session = SessionFactoryUtils.getSession(sessionFactory, true);
	     FlushMode flushMode = FlushMode.NEVER;
	     if (flushMode != null) {
	        session.setFlushMode(flushMode);
	     }
	      return session;
	 }
	 private SessionFactory lookupSessionFactory(HttpServletRequest request) {
	     //“sessionFactory”是你spring配置文件（通常是application.xml）中的SessionFactory。
	     //如：org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean
	     return (SessionFactory)this.applicationContext.getBean("sessionFactory");
	 }
	 		
}
