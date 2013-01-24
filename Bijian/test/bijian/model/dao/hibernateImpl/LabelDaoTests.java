package bijian.model.dao.hibernateImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import bijian.model.bean.Label;
import bijian.model.bean.User;
import bijian.model.dao.ILabelDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class LabelDaoTests extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	@Resource(name="labelDao",type=LabelDaoImpl.class)
    private ILabelDao labelDao;
	
	@Test
	public void insert(){//T entity
		long userID=0;
		User user=(User) userDao.get(userID);
		Assert.assertNotNull("userID not null", user.getUserID());
		Label label=new Label();
		label.setContent("开心");
		label.setAuthor(user);
		labelDao.insert(label);
		Assert.assertNotNull("labelID not null", label.getLabelID());
	}
    
	@Test
	public void get(){//int userID,int page,int limit
		addLabel(0);
		addLabel(0);
		addLabel(0);
		addLabel(0);
		long userID=0;
		int page=0;
		int limit=10;
		
		List<Label> labels=labelDao.get(userID, page, limit);
		Assert.assertTrue(labels.size()>0);
	   	for(int i=0;i<labels.size();i++){
	   		System.out.println("labelID--->"+labels.get(i).getLabelID());
	   	}
	}
    
	private void addLabel(long userID){
		User user=(User) userDao.get(userID);
		Label label=new Label();
		label.setContent("开心");
		label.setAuthor(user);
		labelDao.insert(label);
	}
}
