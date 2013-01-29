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
public class LabelUserDaoTests extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	@Resource(name="labelDao",type=LabelDaoImpl.class)
    private ILabelDao labelDao;
	@Resource(name="labelUserDao",type=LabelUserDaoImpl.class)
    private ILabelUserDao labelUserDao;
	
	@Test
	public void insert(){//T entity
		long userID=addUser("jazywoo","wujianzhi","123456");
		long labelID=addLabel("开心");
		User user=(User) userDao.get(userID);
		Label label=(Label) labelDao.get(labelID);
		Assert.assertNotNull(user);
		Assert.assertNotNull(label);
		LabelUser labelUser=new LabelUser();
		labelUser.setLabel(label);
		labelUser.setUser(user);
		labelUser.setCreateTime(new Date());
		labelUserDao.insert(labelUser);
	}
	@Test
	public void getLabelUsersByUser(){//long userID,int page,int limit
		long userID=addUser("jazywoo","wujianzhi","123456");
		long labelID=addLabel("开心");
		addLabelUser(userID,labelID);
		labelID=addLabel("开心");
		addLabelUser(userID,labelID);
		labelID=addLabel("开心");
		addLabelUser(userID,labelID);
		
		int page=0;
		int limit=10;
		List<LabelUser> labelUsers=labelUserDao.getLabelUsersByUser(userID, page, limit);
		Assert.assertTrue(labelUsers.size()>=3);
		for(LabelUser lu:labelUsers){
			System.out.println("labelUserID->"+lu.getLabelUserID());
		}
	}
	@Test
    public void getLabelUsersByLabel(){//long labelID,int page,int limit
		long userID=addUser("jazywoo","wujianzhi","123456");
		long labelID=addLabel("开心");
		addLabelUser(userID,labelID);
		userID=addUser("jazywoo1","wujianzhi1","123456");
		addLabelUser(userID,labelID);
		userID=addUser("jazywoo2","wujianzhi2","123456");
		addLabelUser(userID,labelID);
		userID=addUser("jazywoo3","wujianzhi3","123456");
		addLabelUser(userID,labelID);
		int page=0;
		int limit=10;
		List<LabelUser> labelUsers=labelUserDao.getLabelUsersByLabel(labelID, page, limit);
		Assert.assertTrue(labelUsers.size()>=3);
		for(LabelUser lu:labelUsers){
			System.out.println("labelUserID->"+lu.getLabelUserID());
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
