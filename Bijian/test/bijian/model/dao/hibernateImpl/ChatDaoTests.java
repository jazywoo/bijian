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
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.dao.IChatDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class ChatDaoTests extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name="chatDao",type=ChatDaoImpl.class)
	private IChatDao chatDao;
	@Resource(name="userDao",type=UserDaoImpl.class)
	private IUserDao userDao;
	
	
	@Test
	public void insert(){
		long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		User fromUser=(User) userDao.get(fromUserID);
		User toUser=(User) userDao.get(toUserID);
		Assert.assertNotNull(fromUser);
		Assert.assertNotNull(toUser);
		Chat chat=new Chat();
		chat.setContent("很高兴今天认识你");
		chat.setCreateTime(new Date());
		chat.setFromUser(fromUser);
		chat.setToUser(toUser);
		chatDao.insert(chat);
		Assert.assertNotNull(chat.getChatID());
	}
	@Test 
	public void delete(){
		long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		long chatID=addChat(fromUserID,toUserID);
		chatDao.delete(chatID);
		Chat chat=(Chat) chatDao.get(chatID);
		Assert.assertNull(chat);
	}
	@Test
	public void getChatListSize(){// userID,chatUserID
		long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		addChat(fromUserID,toUserID);
		addChat(fromUserID,toUserID);
		addChat(fromUserID,toUserID);
		addChat(fromUserID,toUserID);
		
		int size=chatDao.getChatListSize(fromUserID, toUserID);
		Assert.assertTrue(size>0);
	}
	@Test
	public void getChatList(){//userID,chatUserID
		long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		addChat(fromUserID,toUserID);
		addChat(fromUserID,toUserID);
		addChat(fromUserID,toUserID);
		addChat(fromUserID,toUserID);
		
    	int page=0;
    	int limit=10;
    	List<Chat> chats=chatDao.getChatList(fromUserID, toUserID, page, limit);
    	Assert.assertTrue(0<chats.size());   
    	for(int i=0;i<chats.size();i++){
    		System.out.println("chatID-->"+chats.get(i).getChatID());
    	}
	}
	
	 private long addChat(long fromUserID,long toUserID){
		 User fromUser=(User) userDao.get(fromUserID);
		 User toUser=(User) userDao.get(toUserID);
		 Chat chat=new Chat();
		 chat.setContent("很高兴今天认识你");
		 chat.setCreateTime(new Date());
		 chat.setFromUser(fromUser);
		 chat.setToUser(toUser);
		 chatDao.insert(chat);
		 return chat.getChatID();
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
