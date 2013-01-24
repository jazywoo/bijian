package bijian.model.dao.hibernateImpl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import bijian.model.bean.ArticleObject;
import bijian.model.bean.Comment;
import bijian.model.bean.Message;
import bijian.model.bean.User;
import bijian.model.dao.IMessageDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class MessageDaoTests  extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource(name="userDao",type=UserDaoImpl.class)
	private IUserDao userDao;
	@Resource(name="messageDao",type=MessageDaoImpl.class)
	private IMessageDao messageDao;
	
	@Test
	public void insert(){//T entity
		long fromUserID=0;
		long toUserID=1;
		User fromUser=(User) userDao.get(fromUserID);
		User toUser=(User) userDao.get(toUserID);
		Assert.assertNotNull(fromUser);
		Assert.assertNotNull(toUser);
		
		Message message=new Message();
		message.setFromUser(fromUser);
		message.setToUser(toUser);
		message.setContent("不错喔");
		
		messageDao.insert(message);
		Assert.assertNotNull("MessageID() not null", message.getMessageID());
	}
	@Test
    public void getByID(){//ID id
		long messageID=addMessage(0,1);
		Message message=(Message) messageDao.get(messageID);
		Assert.assertNotNull(message);
    }
	@Test
    public void update(){//T entity
		long messageID=addMessage(0,1);
		Message message=(Message) messageDao.get(messageID);
		Assert.assertNotNull(message);
		message.setContent("update");
		message=(Message) messageDao.get(messageID);
		Assert.assertEquals("update", message.getContent());
    }
    @Test
    public void delete(){//ID id
    	long messageID=addMessage(0,1);
		Message message=(Message) messageDao.get(messageID);
		Assert.assertNotNull(message);
		messageDao.delete(messageID);
		message=(Message) messageDao.get(messageID);
		Assert.assertNull(message);
    }
    
    
    @Test
    public void getCommentList(){//long fromUserID,long toUserID,final int page,final int limit
    	addMessage(0,1);
    	addMessage(0,1);
    	addMessage(0,1);
    	addMessage(0,1);
    	addMessage(0,1);
    	long fromUserID=0;
    	long toUserID=1;
    	int size=messageDao.getMessageListSize(fromUserID, toUserID);
    	Assert.assertTrue(size>0);
    	
    }
    @Test
    public void getCommentListSize(){//long fromUserID,long toUserID
    	addMessage(0,1);
    	addMessage(0,1);
    	addMessage(0,1);
    	addMessage(0,1);
    	addMessage(0,1);
    	long fromUserID=0;
    	long toUserID=1;
    	int page=0;
    	int limit=10;
    	List<Message> messages=messageDao.getMessageList(fromUserID, toUserID, page, limit);
    	Assert.assertTrue(0<messages.size());   
    	for(int i=0;i<messages.size();i++){
    		System.out.println("MessageID-->"+messages.get(i).getMessageID());
    	}
    }
    
    private long addMessage(long fromUserID,long toUserID){
		User fromUser=(User) userDao.get(fromUserID);
		User toUser=(User) userDao.get(toUserID);
		
		Message message=new Message();
		message.setFromUser(fromUser);
		message.setToUser(toUser);
		message.setContent("不错喔");
		
		messageDao.insert(message);
		return message.getMessageID();
    }
}
