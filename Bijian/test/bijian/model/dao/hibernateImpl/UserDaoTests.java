package bijian.model.dao.hibernateImpl;

import java.util.Date;
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
import bijian.model.bean.relationbean.LabelUser;
import bijian.model.dao.ILabelDao;
import bijian.model.dao.ILabelUserDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class UserDaoTests extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	@Resource(name="labelDao",type=LabelDaoImpl.class)
    private ILabelDao labelDao;
	@Resource(name="labelUserDao",type=LabelUserDaoImpl.class)
    private ILabelUserDao labelUserDao;
	
	@Test
	public void insert(){//T entity
		User user=new User();
		user.setUsername("jazywoo");
		user.setNickname("wujianzhi");
		user.setPassword("123456");
		userDao.insert(user);
		System.out.println("testInsert "+user.getUserID());
		Assert.assertNotNull("userID not null", user.getUserID());
	}
	@Test
    public void getByID(){//ID id
		long userID=addUser("jazywoo","wujianzhi","123456");
		User user=(User) userDao.get(userID);
		System.out.println("testGetByID "+user.getUserID());
		Assert.assertNotNull(user);
    }
	@Test
	public void getByUsername(){//String username
		addUser("jazywoo","wujianzhi","123456");
		String username="jazywoo";
		User user=(User) userDao.get(username);
		System.out.println("testGetByUsername "+user.getUserID());
		Assert.assertNotNull(user);
	}
	@Test
    public void update(){//T entity
		long userID=addUser("jazywoo","wujianzhi","123456");
		User user=(User) userDao.get(userID);
		Assert.assertNotNull(user);
		user.setNickname("wujianzhi_update");
		userDao.update(user);
		System.out.println("testUpdate"+user.getUserID());
		Assert.assertEquals("wujianzhi_update", user.getNickname());
    }
	
    @Test
    public void delete(){//ID id
    	long userID=addUser("jazywoo","wujianzhi","123456");
    	User user=(User) userDao.get(userID);
    	Assert.assertNotNull(user);
    	userDao.delete(userID);
    	user=(User) userDao.get(userID);
    	Assert.assertNull("删除user后，为空",user);
    	System.out.println("testDelete ");
    }
    
	@Test
	public void getLike(){//User user,int page,int limit
		addUser("jazywoo","wujianzhi","123456","湖北省","武汉市","黄陂区","摩羯座",22,1,10);
		addUser("jazywoo1","wujianzhi","123456","湖北省","武汉市","洪山区","摩羯座",22,1,10);
		addUser("jazywoo2","wujianzhi","123456","湖北省","武汉市","黄陂区","摩羯座",23,1,10);
		addUser("jazywoo3","wujianzhi","123456","湖北省","武汉市","黄陂区","摩羯座",22,0,10);
		List<User> matchUsers;
		User user=new User();
		int page=0;
		int limit=10;
		try {
			user.setUsername("jazywoo");
			matchUsers=userDao.getLike(user, page,limit);
			Assert.assertNotNull(matchUsers);
			user.setNickname("wujianzhi");
			matchUsers=userDao.getLike(user, page,limit);
			Assert.assertNotNull(matchUsers);
			user.setSex(1);
			matchUsers=userDao.getLike(user, page,limit);
			Assert.assertNotNull(matchUsers);
			user.setAge(22);
			matchUsers=userDao.getLike(user, page,limit);
			Assert.assertNotNull(matchUsers);
			user.setProvince("湖北省");
			matchUsers=userDao.getLike(user, page,limit);
			Assert.assertNotNull(matchUsers);
			user.setCity("武汉市");
			matchUsers=userDao.getLike(user, page,limit);
			Assert.assertNotNull(matchUsers);
			user.setArea("洪山区");
			matchUsers=userDao.getLike(user, page,limit);
			Assert.assertNotNull(matchUsers);
			user.setConstellation("摩羯座");
			matchUsers=userDao.getLike(user, page,limit);
			Assert.assertNotNull(matchUsers);
			for(int i=0;i<matchUsers.size();i++){
				System.out.println("匹配userID "+matchUsers.get(i).getUserID());
			}
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	public void getHotUsers(){//int page,int limit
		addUser("jazywoo","wujianzhi","123456","湖北省","武汉市","黄陂区","摩羯座",22,1,1);
		addUser("jazywoo1","wujianzhi","123456","湖北省","武汉市","洪山区","摩羯座",22,1,2);
		addUser("jazywoo2","wujianzhi","123456","湖北省","武汉市","黄陂区","摩羯座",23,1,4);
		addUser("jazywoo3","wujianzhi","123456","湖北省","武汉市","黄陂区","摩羯座",22,0,10);
		int page=0;
		int limit=10;
		List<User> users=userDao.getHotUsers(page, limit);
		Assert.assertTrue(users.size()>0);
		for(int i=0;i<users.size();i++){
			System.out.println("userID "+users.get(i).getUserID());
		}
	}
	public void getHotUserByLabel(){//long labelID
		long userID=addUser("jazywoo3","wujianzhi","123456","湖北省","武汉市","黄陂区","摩羯座",22,0,10);
		long max=userID;
		long labelID=addLabel("开心");
		addLabelUser(userID,labelID);
		userID=addUser("jazywoo3","wujianzhi","123456","湖北省","武汉市","黄陂区","摩羯座",22,0,4);
		addLabelUser(userID,labelID);
		userID=addUser("jazywoo2","wujianzhi2","123456");
		addLabelUser(userID,labelID);
		userID=addUser("jazywoo3","wujianzhi3","123456");
		addLabelUser(userID,labelID);
		
		User user=userDao.getHotUserByLabel(labelID);
		Assert.assertTrue(user.getUserID()==max);
	}
	public void getActiveUsersByLabel(){//long labelID,int page,int limit
		long userID=addUser("jazywoo3","wujianzhi","123456");
		long max=userID;
		long labelID=addLabel("开心");
		addLabelUser(userID,labelID);
		userID=addUser("jazywoo3","wujianzhi","123456");
		addLabelUser(userID,labelID);
		userID=addUser("jazywoo2","wujianzhi2","123456");
		addLabelUser(userID,labelID);
		userID=addUser("jazywoo3","wujianzhi3","123456");
		addLabelUser(userID,labelID);
		
		int page=0;
		int limit=10;
		List<User> users=userDao.getActiveUsersByLabel(labelID, page, limit);
		Assert.assertTrue(users.size()>2);
	}
	
	
	private long addUser(String username,String nickName,String password){
    	User user=new User();
		user.setUsername(username);
		user.setNickname(nickName);
		user.setPassword(password);
		userDao.insert(user);
		return user.getUserID();
    }
	private long addUser(String username,String nickName,String password,
			String province,String city,String area,String constellation,int age,int sex,int hotValue){
    	User user=new User();
		user.setUsername(username);
		user.setNickname(nickName);
		user.setPassword(password);
		user.setProvince(province);
		user.setCity(city);
		user.setArea(area);
		user.setConstellation(constellation);
		user.setAge(age);
		user.setSex(sex);
		userDao.insert(user);
		return user.getUserID();
    }
	private long addLabel(String content){
		Label label=new Label();
		label.setContent(content);
		labelDao.insert(label);
		return label.getLabelID();
	}
	private long addLabelUser(long userID,long labelID){
		User user=(User) userDao.get(userID);
		Label label=(Label) labelDao.get(labelID);
		LabelUser labelUser=new LabelUser();
		labelUser.setLabel(label);
		labelUser.setUser(user);
		labelUser.setCreateTime(new Date());
		labelUserDao.insert(labelUser);
		return labelUser.getLabelUserID();
	}
    
}
