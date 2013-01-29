package bijian.model.dao.hibernateImpl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import bijian.model.bean.User;
import bijian.model.bean.FriendGroup;
import bijian.model.dao.IFriendGroupDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class FriendGroupDaoTests extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name="friendGroupDao",type=FriendGroupDaoImpl.class)
    private IFriendGroupDao friendGroupDao;
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	
	@Test
    public void insert(){//T entity
		long userID=addUser("jazywoo","wujianzhi","123456");
		FriendGroup friendGroup=new FriendGroup();
		User user=(User) userDao.get(userID);
		friendGroup.setAuthor(user);
		friendGroup.setGroupName("同事");
		friendGroupDao.insert(friendGroup);
		Assert.assertNotNull(friendGroup.getFriendGroupID());
    }
	@Test
    public void update(){//T entity
		long userID=addUser("jazywoo","wujianzhi","123456");
    	long friendGroupID=addFriendGroup(userID);
    	FriendGroup friendGroup=(FriendGroup) friendGroupDao.get(friendGroupID);
    	Assert.assertNotNull(friendGroup);
    	friendGroup.setGroupName("pengyou");
        friendGroup=(FriendGroup) friendGroupDao.get(friendGroupID);
        Assert.assertEquals("pengyou", friendGroup.getGroupName());
    }
	@Test
    public void deleteByID(){//ID id
		long userID=addUser("jazywoo","wujianzhi","123456");
    	long friendGroupID=addFriendGroup(userID);
    	FriendGroup friendGroup=(FriendGroup) friendGroupDao.get(friendGroupID);
    	Assert.assertNotNull(friendGroup); 
    	friendGroupDao.delete(friendGroupID);
    	friendGroup=(FriendGroup) friendGroupDao.get(friendGroupID);
    	Assert.assertNull(friendGroup); 
    }
    @Test
	public void getByID(){//ID id
    	long userID=addUser("jazywoo","wujianzhi","123456");
    	long friendGroupID=addFriendGroup(userID);
    	FriendGroup friendGroup=(FriendGroup) friendGroupDao.get(friendGroupID);
    	Assert.assertNotNull(friendGroup);    	
	}
    
    private long addFriendGroup(long userID){
    	FriendGroup friendGroup=new FriendGroup();
		User user=(User) userDao.get(userID);
		friendGroup.setAuthor(user);
		friendGroup.setGroupName("同事");
		friendGroupDao.insert(friendGroup);
		return friendGroup.getFriendGroupID();
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
