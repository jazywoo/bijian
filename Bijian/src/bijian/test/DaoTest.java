package bijian.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import bijian.model.bean.relationbean.*;
import bijian.model.bean.*;
import bijian.model.dao.*;
import bijian.model.dao.hibernateImpl.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(
   transactionManager="txManager",defaultRollback=false	
)
public class DaoTest{
	  @Resource
      private IAttentionDao attentionDao;
	  @Resource
      private IChatDao chatDao;
	  @Resource
      private ICommentDao commentDao;
	  @Resource
      private IDiaryDao diaryDao;
	  @Resource
      private IFollowingDao followingDao;
	  @Resource
      private IFriendGroupDao friendGroupDao;
	  @Resource
      private IFriendTableDao friendTableDao;
	  @Resource
      private ILabelDao labelDao;
	  @Resource
      private IMessageDao messageDao;
	  @Resource
      private INoticeDao noticeDao;
	  @Resource
      private IReportSentenceDao reportSentenceDao;
	  @Resource
      private ISentenceDao sentenceDao;
	  @Resource
      private IUserDao userDao;
	  @Resource
      private IUserRelatedObjectDao userRelatedObjectDao;
      public void testAttentionDao(){
    	  Attention attention=new Attention();
    	  attention.setAttentionID(1);
    	  attention.setCreateTime(new Date());
    	  attention.setIsValid(1);
      }
      public void testChatDao(){
    	  
      }
      public void testCommentDao(){
    	  
      }
      public void testDiaryDao(){
    	  
      }
      public void testFollowingDao(){
    	  
      }
      public void testFriendGroupDao(){
    	  
      }
      public void testFriendTableDao(){
    	  
      }
      public void testLabelDao(){
    	  
      }
      public void testMessageDao(){
    	  
      }
      public void testNoticeDao(){
    	  
      }
      public void testReportSentenceDao(){
    	  
      }
      public void testSentenceDao(){
    	  
      }
      @Test
      @Rollback(false)
      public void testUserDao(){
    	  User user=new User();
    	  user.setUsername("jj");
    	  user.setNickname("sf");
    	  user.setPassword("dsd");
    	  user.setCreateTime(new Date());
    	  userDao.insert(user);
    	  long userID=1;
    	  User user1=(User) userDao.get(userID);
    	  System.out.println("----------------"+user.getNickname());
      }
      public void testUserRelatedObjectDao(){
    	  
      }
      
      
}
