package bijian.model.dao.hibernateImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import bijian.model.bean.Label;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.SubscribeLabel;
import bijian.model.dao.ILabelDao;
import bijian.model.dao.ISubscribeLabelDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class SubscribeLabelDaoTests extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	@Resource(name="labelDao",type=LabelDaoImpl.class)
    private ILabelDao labelDao;
    @Resource(name="subscribeLabelDao",type=SubscribeLabelDaoImpl.class)
    private ISubscribeLabelDao subscribeLabelDao;
    
	@Test
	public void insert(){//T entity
		long userID=addUser("jazywoo","wujianzhi","123456");
		long labelID=addLabel("开心");
		long subscribeLabelID=addSubscribeLabel(userID,labelID);
		SubscribeLabel subscribeLabel=(SubscribeLabel) subscribeLabelDao.get(subscribeLabelID);
		Assert.assertNotNull(subscribeLabel);		
	}
	@Test
    public void getByID(){//ID id
		long userID=addUser("jazywoo","wujianzhi","123456");
		long labelID=addLabel("开心");
		long subscribeLabelID=addSubscribeLabel(userID,labelID);
		SubscribeLabel subscribeLabel=(SubscribeLabel) subscribeLabelDao.get(subscribeLabelID);
		Assert.assertNotNull(subscribeLabel);	
	}
	@Test
    public void update(){//T entity
		long userID=addUser("jazywoo","wujianzhi","123456");
		long labelID=addLabel("开心");
		long subscribeLabelID=addSubscribeLabel(userID,labelID);
		SubscribeLabel subscribeLabel=(SubscribeLabel) subscribeLabelDao.get(subscribeLabelID);
		Assert.assertNotNull(subscribeLabel);	
		subscribeLabel.setIsValid(1);
		subscribeLabelDao.update(subscribeLabel);
		subscribeLabel=(SubscribeLabel) subscribeLabelDao.get(subscribeLabelID);
		Assert.assertEquals(subscribeLabel.getIsValid(),1);
	}
	@Test
    public void delete(){//ID id
		long userID=addUser("jazywoo","wujianzhi","123456");
		long labelID=addLabel("开心");
		long subscribeLabelID=addSubscribeLabel(userID,labelID);
		SubscribeLabel subscribeLabel=(SubscribeLabel) subscribeLabelDao.get(subscribeLabelID);
		Assert.assertNotNull(subscribeLabel);
		subscribeLabelDao.delete(subscribeLabelID);
		subscribeLabel=(SubscribeLabel) subscribeLabelDao.get(subscribeLabelID);
		Assert.assertNull(subscribeLabel);
	}
	
    @Test
    public void getSubscribeLabelsByUser(){//long userID,int page,int limit
    	long userID=addUser("jazywoo","wujianzhi","123456");
		long labelID=addLabel("开心");
		addSubscribeLabel(userID,labelID);
		labelID=addLabel("开心1");
		addSubscribeLabel(userID,labelID);
		labelID=addLabel("开心2");
		addSubscribeLabel(userID,labelID);
		
		int page=0;
    	int limit=10;
    	List<SubscribeLabel> subscribeLabels=subscribeLabelDao.getSubscribeLabelsByUser(userID, page, limit);
    	Assert.assertTrue(2<subscribeLabels.size());   
    	for(int i=0;i<subscribeLabels.size();i++){
    		System.out.println("SubscribeLabelID-->"+subscribeLabels.get(i).getSubscribeLabelID());
    	}
    }
    @Test
    public void getSubscribeLabelsByLabel(){//long labelID,int page,int limit
    	long userID=addUser("jazywoo","wujianzhi","123456");
		long labelID=addLabel("开心");
		addSubscribeLabel(userID,labelID);
		userID=addUser("jazywoo2","wujianzhi2","123456");
		addSubscribeLabel(userID,labelID);
		userID=addUser("jazywoo3","wujianzhi3","123456");
		addSubscribeLabel(userID,labelID);
		
    	int page=0;
    	int limit=10;
    	List<SubscribeLabel> subscribeLabels=subscribeLabelDao.getSubscribeLabelsByLabel(labelID, page, limit);
    	Assert.assertTrue(2<subscribeLabels.size());   
    	for(int i=0;i<subscribeLabels.size();i++){
    		System.out.println("SubscribeLabelID-->"+subscribeLabels.get(i).getSubscribeLabelID());
    	}
    }
	
	
	
	private long addSubscribeLabel(long userID,long labelID){
		User user=(User) userDao.get(userID);
		Label label=(Label) labelDao.get(labelID);
		SubscribeLabel subscribeLabel=new SubscribeLabel();
		subscribeLabel.setUser(user);
		subscribeLabel.setLabel(label);
		subscribeLabel.setCreateTime(new Date());
		subscribeLabelDao.insert(subscribeLabel);
		return subscribeLabel.getSubscribeLabelID();		
	}
	
	private long addLabel(String content){
		Label label=new Label();
		label.setContent(content);
		labelDao.insert(label);
		return label.getLabelID();
	}
	private long addUser(String username,String nickName,String password){
    	User user=new User();
		user.setUsername(username);
		user.setNickname(nickName);
		user.setPassword(password);
		userDao.insert(user);
		return user.getUserID();
    }
	
	
	
	
}
