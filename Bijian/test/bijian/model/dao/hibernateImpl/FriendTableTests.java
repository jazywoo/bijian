package bijian.model.dao.hibernateImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import bijian.model.bean.FriendGroup;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.FriendTable;
import bijian.model.dao.IFriendGroupDao;
import bijian.model.dao.IFriendTableDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class FriendTableTests extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name="friendTableDao",type=FriendTableDaoImpl.class)
    private IFriendTableDao friendTableDao;
	@Resource(name="friendGroupDao",type=FriendGroupDaoImpl.class)
    private IFriendGroupDao friendGroupDao;
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	
	@Test
    public void insert(){//T entity
		long selfID=addUser("jazywoo","wujianzhi","123456");
		long friendID=addUser("jazywoo2","wujianzhi2","123456");
		long friendGroupID=addFriendGroup(selfID);
		User self=(User) userDao.get(selfID);
		User friend=(User) userDao.get(friendID);
		FriendGroup friengGroup=(FriendGroup) friendGroupDao.get(friendGroupID);
		Assert.assertNotNull(self);
		Assert.assertNotNull(friend);
		Assert.assertNotNull(friengGroup);
		FriendTable friendTable=new FriendTable();
		friendTable.setSelf(self);
		friendTable.setFriend(friend);
		friendTable.setGroup(friengGroup);
		friendTableDao.insert(friendTable);
		Assert.assertNotNull(friendTable.getFriendTableID());
    }
	@Test
    public void deleteByUser(){//long selfID,long attentionerID
		long selfID=addUser("jazywoo","wujianzhi","123456");
		long friendID=addUser("jazywoo2","wujianzhi2","123456");
		long friendGroupID=addFriendGroup(selfID);
		addFriendTable(selfID,friendID,friendGroupID);
		
		FriendTable friendTable=friendTableDao.get(selfID, friendID);
		Assert.assertNotNull(friendTable);
		friendTableDao.delete(selfID, friendID);
		friendTable=friendTableDao.get(selfID, friendID);
		Assert.assertNull(friendTable);
    }
    
    @Test
	public void getByUser(){//long selfID,long attentionerID
    	long selfID=addUser("jazywoo","wujianzhi","123456");
		long friendID=addUser("jazywoo2","wujianzhi2","123456");
		long friendGroupID=addFriendGroup(selfID);
		addFriendTable(selfID,friendID,friendGroupID);
		
		FriendTable friendTable=friendTableDao.get(selfID, friendID);
		Assert.assertNotNull(friendTable);
	}
    
    @Test
    public void getFriendListSize(){//long userID
    	long selfID=addUser("jazywoo","wujianzhi","123456");
		long friendID=addUser("jazywoo2","wujianzhi2","123456");
		long friendGroupID=addFriendGroup(selfID);
		addFriendTable(selfID,friendID,friendGroupID);
		friendID=addUser("jazywoo2","wujianzhi2","123456");
		addFriendTable(selfID,friendID,friendGroupID);
		friendID=addUser("jazywoo2","wujianzhi2","123456");
		addFriendTable(selfID,friendID,friendGroupID);
		
    	int size=friendTableDao.getFriendListSize(selfID);
    	Assert.assertTrue(size>0);
    }
    
    @Test
    public void getFriendList(){//long userID,int page,int limit
    	long selfID=addUser("jazywoo","wujianzhi","123456");
		long friendID=addUser("jazywoo2","wujianzhi2","123456");
		long friendGroupID=addFriendGroup(selfID);
		addFriendTable(selfID,friendID,friendGroupID);
		friendID=addUser("jazywoo2","wujianzhi2","123456");
		addFriendTable(selfID,friendID,friendGroupID);
		friendID=addUser("jazywoo2","wujianzhi2","123456");
		addFriendTable(selfID,friendID,friendGroupID);
		
    	int page=0;
    	int limit=10;
    	List<FriendTable> friends=friendTableDao.get(selfID, page, limit);
    	Assert.assertTrue(0<friends.size());   
    	for(int i=0;i<friends.size();i++){
    		System.out.println("attentionID-->"+friends.get(i).getFriendTableID());
    	}
    }
    
    private long addFriendTable(long selfID,long friendID,long friendGroupID){
    	User self=(User) userDao.get(selfID);
		User friend=(User) userDao.get(friendID);
		FriendGroup friengGroup=(FriendGroup) friendGroupDao.get(friendGroupID);
		FriendTable friendTable=new FriendTable();
		friendTable.setSelf(self);
		friendTable.setFriend(friend);
		friendTable.setGroup(friengGroup);
		friendTableDao.insert(friendTable);
		return friendTable.getFriendTableID();
    }
    
    
    private long addUser(String username,String nickName,String password){
    	User user=new User();
		user.setUsername(username);
		user.setNickname(nickName);
		user.setPassword(password);
		userDao.insert(user);
		return user.getUserID();
    }
    private long addFriendGroup(long userID){
    	FriendGroup friendGroup=new FriendGroup();
		User user=(User) userDao.get(userID);
		friendGroup.setAuthor(user);
		friendGroup.setGroupName("同事");
		friendGroupDao.insert(friendGroup);
		return friendGroup.getFriendGroupID();
    }
	
}
