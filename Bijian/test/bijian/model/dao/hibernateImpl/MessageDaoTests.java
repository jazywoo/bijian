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

import bijian.model.bean.Chat;
import bijian.model.bean.Comment;
import bijian.model.bean.Message;
import bijian.model.bean.User;
import bijian.model.dao.IMessageDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过1
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
	public void insert(){
		long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		User fromUser=(User) userDao.get(fromUserID);
		User toUser=(User) userDao.get(toUserID);
		Assert.assertNotNull(fromUser);
		Assert.assertNotNull(toUser);
		Message message=new Message();
		message.setContent("很高兴今天认识你");
		message.setCreateTime(new Date());
		message.setFromUser(fromUser);
		message.setToUser(toUser);
		messageDao.insert(message);
		Assert.assertNotNull(message.getMessageID());
	}
	@Test 
	public void delete(){
		long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		long messageID=addMessage(fromUserID,toUserID);
		messageDao.delete(messageID);
		Message message=(Message) messageDao.get(messageID);
		Assert.assertNull(message);
	}
	@Test
	public void getMessageListSize(){// userID,messageUserID
		long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		addMessage(fromUserID,toUserID);
		addMessage(fromUserID,toUserID);
		addMessage(fromUserID,toUserID);
		addMessage(fromUserID,toUserID);
		
		int size=messageDao.getMessageListSize(fromUserID, toUserID);
		Assert.assertTrue(size>0);
	}
	@Test
	public void getMessageList(){//userID,messageUserID
		long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		addMessage(fromUserID,toUserID);
		addMessage(fromUserID,toUserID);
		addMessage(fromUserID,toUserID);
		addMessage(fromUserID,toUserID);
		
    	int page=0;
    	int limit=10;
    	List<Message> messages=messageDao.getMessageList(fromUserID, toUserID, page, limit);
    	Assert.assertTrue(0<messages.size());   
    	for(int i=0;i<messages.size();i++){
    		System.out.println("messageID-->"+messages.get(i).getMessageID());
    	}
	}
	
	 private long addMessage(long fromUserID,long toUserID){
		 User fromUser=(User) userDao.get(fromUserID);
		 User toUser=(User) userDao.get(toUserID);
		 Message message=new Message();
		 message.setContent("很高兴今天认识你");
		 message.setCreateTime(new Date());
		 message.setFromUser(fromUser);
		 message.setToUser(toUser);
		 messageDao.insert(message);
		 return message.getMessageID();
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
