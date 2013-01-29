package bijian.model.dao.hibernateImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.dao.IAttentionDao;
import bijian.model.dao.IUserDao;


/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class AttentionDaoTests extends AbstractTransactionalJUnit4SpringContextTests{
    @Resource(name="attentionDao",type=AttentionDaoImpl.class)
    private IAttentionDao attentionDao;
    @Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
    
    @Test
    public void insert(){//T entity
    	long selfID=addUser("jazywoo","wujianzhi","123456");
    	long attentionerID=addUser("jazywoo2","wujianzhi2","123456");
    	User self=(User) userDao.get(selfID);
    	User attentioner=(User) userDao.get(attentionerID);
    	Attention attention=new Attention();
    	attention.setSelf(self);
    	attention.setAttentioner(attentioner);
    	attention.setCreateTime(new Date());
    	attentionDao.insert(attention);
    	Assert.assertNotNull(attention.getAttentionID());
    }
    @Test
    public void deleteByUser(){//long selfID,long attentionerID
    	long selfID=addUser("jazywoo","wujianzhi","123456");
    	long attentionerID=addUser("jazywoo2","wujianzhi2","123456");
    	addAttention(selfID,attentionerID);
    	
    	Attention attention=attentionDao.get(selfID, attentionerID);
    	Assert.assertNotNull(attention);
    	attentionDao.delete(selfID, attentionerID);
    	attention=attentionDao.get(selfID, attentionerID);
    	Assert.assertNull(attention);
    }
    
    @Test
	public void getByUser(){//long selfID,long attentionerID
    	long selfID=addUser("jazywoo","wujianzhi","123456");
    	long attentionerID=addUser("jazywoo2","wujianzhi2","123456");
    	addAttention(selfID,attentionerID);
    	
    	Attention attention=attentionDao.get(selfID, attentionerID);
    	Assert.assertNotNull(attention);
	}
    
    @Test
    public void getAttentionListSize(){//long userID
    	long selfID=addUser("jazywoo","wujianzhi","123456");
    	long attentionerID=addUser("jazywoo2","wujianzhi2","123456");
    	addAttention(selfID,attentionerID);
    	attentionerID=addUser("jazywoo2","wujianzhi2","123456");
    	addAttention(selfID,attentionerID);
    	attentionerID=addUser("jazywoo2","wujianzhi2","123456");
    	addAttention(selfID,attentionerID);
    	attentionerID=addUser("jazywoo2","wujianzhi2","123456");
    	addAttention(selfID,attentionerID);
    	
    	int attentionSize=attentionDao.getAttentionListSize(selfID);
    	Assert.assertTrue(0<attentionSize);    	
    }
    
    @Test
    public void getAttentionList(){//long userID,int page,int limit
    	long selfID=addUser("jazywoo","wujianzhi","123456");
    	long attentionerID=addUser("jazywoo2","wujianzhi2","123456");
    	addAttention(selfID,attentionerID);
    	attentionerID=addUser("jazywoo2","wujianzhi2","123456");
    	addAttention(selfID,attentionerID);
    	attentionerID=addUser("jazywoo2","wujianzhi2","123456");
    	addAttention(selfID,attentionerID);
    	attentionerID=addUser("jazywoo2","wujianzhi2","123456");
    	addAttention(selfID,attentionerID);
    	
    	int page=0;
    	int limit=10;
    	List<Attention> attentions=attentionDao.get(selfID, page, limit);
    	Assert.assertTrue(0<attentions.size());   
    	for(int i=0;i<attentions.size();i++){
    		System.out.println("attentionID-->"+attentions.get(i).getAttentionID());
    	}
    }
    
    private long addUser(String username,String nickName,String password){
    	User user=new User();
		user.setUsername(username);
		user.setNickname(nickName);
		user.setPassword(password);
		userDao.insert(user);
		return user.getUserID();
    }
    private long addAttention(long selfID,long attentionerID){
    	User self=(User) userDao.get(selfID);
    	User attentioner=(User) userDao.get(attentionerID);
    	Attention attention=new Attention();
    	attention.setSelf(self);
    	attention.setAttentioner(attentioner);
    	attention.setCreateTime(new Date());
    	attentionDao.insert(attention);
    	return attention.getAttentionID();
    }
    
}
