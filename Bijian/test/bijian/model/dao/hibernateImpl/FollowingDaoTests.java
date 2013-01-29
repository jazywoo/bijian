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
import bijian.model.bean.relationbean.Following;
import bijian.model.dao.IFollowingDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 *　测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class FollowingDaoTests extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name="followingDao",type=FollowingDaoImpl.class)
    private IFollowingDao followingDao;
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	
	
    @Test
    @Rollback(false)
    public void insert(){//T entity
    	long selfID=addUser("jazywoo","wujianzhi","123456");
    	long followingerID=addUser("jazywoo2","wujianzhi2","123456");
    	User self=(User) userDao.get(selfID);
    	User followinger=(User) userDao.get(followingerID);
    	Following following=new Following();
    	following.setSelf(self);
    	following.setFollowinger(followinger);
    	following.setCreateTime(new Date());
    	followingDao.insert(following);
    	Assert.assertNotNull(following.getFollowingID());
    }
    @Test
    public void deleteByUser(){//long selfID,long followingerID
    	long selfID=addUser("jazywoo","wujianzhi","123456");
    	long followingerID=addUser("jazywoo2","wujianzhi2","123456");
    	addFollowing(selfID,followingerID);
    	followingDao.delete(selfID, followingerID);
    	Following following=followingDao.get(selfID, followingerID);
    	Assert.assertNull(following);
    }
    
    @Test
	public void getByUser(){//long selfID,long followingerID
    	long selfID=addUser("jazywoo","wujianzhi","123456");
    	long followingerID=addUser("jazywoo2","wujianzhi2","123456");
    	addFollowing(selfID,followingerID);
    	Following following=followingDao.get(selfID, followingerID);
    	Assert.assertNotNull(following);
	}
    
    @Test
    public void getFollowingListSize(){//long userID
    	long selfID=addUser("jazywoo","wujianzhi","123456");
    	long followingerID=addUser("jazywoo2","wujianzhi2","123456");
    	addFollowing(selfID,followingerID);
    	followingerID=addUser("jazywoo2","wujianzhi2","123456");
    	addFollowing(selfID,followingerID);
    	followingerID=addUser("jazywoo2","wujianzhi2","123456");
    	addFollowing(selfID,followingerID);
    	
    	int followingSize=followingDao.getFollowingListSize(selfID);
    	Assert.assertTrue(0<followingSize);    	
    }
    
    @Test
    public void getByUserID(){//long userID,int page,int limit
    	long selfID=addUser("jazywoo","wujianzhi","123456");
    	long followingerID=addUser("jazywoo2","wujianzhi2","123456");
    	addFollowing(selfID,followingerID);
    	followingerID=addUser("jazywoo2","wujianzhi2","123456");
    	addFollowing(selfID,followingerID);
    	followingerID=addUser("jazywoo2","wujianzhi2","123456");
    	addFollowing(selfID,followingerID);
    	
    	int page=0;
    	int limit=10;
    	List<Following> followings=followingDao.get(selfID, page, limit);
    	Assert.assertTrue(0<followings.size());   
    	for(int i=0;i<followings.size();i++){
    		System.out.println("followingID-->"+followings.get(i).getFollowingID());
    	}
    }
	
    private long addFollowing(long selfID,long followingerID){
    	User self=(User) userDao.get(selfID);
    	User followinger=(User) userDao.get(followingerID);
    	Following following=new Following();
    	following.setSelf(self);
    	following.setFollowinger(followinger);
    	following.setCreateTime(new Date());
    	followingDao.insert(following);
    	return following.getFollowingID();
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
