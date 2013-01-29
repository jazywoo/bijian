package bijian.model.dao.hibernateImpl;

import java.util.ArrayList;
import java.util.Calendar;
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

import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.dao.IAttentionDao;
import bijian.model.dao.ISentenceDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class SentenceDaoTests extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name="sentenceDao",type=SentenceDaoImpl.class)
    private ISentenceDao sentenceDao;
	@Resource(name="userDao",type=UserDaoImpl.class)
	private IUserDao userDao;
	@Resource(name="attentionDao",type=AttentionDaoImpl.class)
	private IAttentionDao attentionDao;
	
	@Test
	public void insert(){//T entity
		long userID=addUser("jazywoo","wujianzhi","123456");
		User user=(User) userDao.get(userID);
		Assert.assertNotNull(user);
		Sentence sentence=new Sentence();
		sentence.setContent("you know that");
		sentence.setAuthor(user);
		
		sentenceDao.insert(sentence);
		Assert.assertNotNull("SentenceID not null", sentence.getSentenceID());
	}
	@Test
    public void getByID(){//ID id
		long userID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(userID,0);
		
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		Assert.assertNotNull(sentence);
    }
	@Test
    public void update(){//T entity
		long userID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(userID,0);
		
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		Assert.assertNotNull(sentence);
		sentence.setContent("update_content");
		sentenceDao.update(sentence);
		sentence=(Sentence) sentenceDao.get(sentenceID);
		Assert.assertEquals("update_content", sentence.getContent());
    }
    @Test
    public void delete(){//ID id
    	long userID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(userID,0);
		
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		Assert.assertNotNull(sentence);
		sentenceDao.delete(sentenceID);
		sentence=(Sentence) sentenceDao.get(sentenceID);
    	Assert.assertNull("删除user后，为空",sentence);
    }
    
    
    @Test
    public void getByUserID(){//long userID,int page,int limit
    	long userID=addUser("jazywoo","wujianzhi","123456");
		addSentence(userID,0);
		addSentence(userID,1);
		addSentence(userID,2);
		addSentence(userID,3);
	   	 int page=0;
	   	 int limit=10;
	   	 List<Sentence> sentences=sentenceDao.getByUserID(userID, page, limit);
	   	 Assert.assertTrue(sentences.size()>3);
	   	 for(int i=0;i<sentences.size();i++){
	   		 System.out.println("SentenceID--->"+sentences.get(i).getSentenceID());
	   	 }
    }
	 
	 @Test
    public void getByUserID2(){//long userID,Date date1,Date date2,int page,int limit
		 long userID=addUser("jazywoo","wujianzhi","123456");
		 addSentence(userID,0);
		 addSentence(userID,1);
		 addSentence(userID,2);
		 addSentence(userID,3);
	   	 int page=0;
	   	 int limit=10;
	   	 Date date1=new Date(System.currentTimeMillis());
	   	 Calendar now = Calendar.getInstance(); 
	   	 now.setTime(date1);
	   	 now.add(Calendar.DAY_OF_YEAR, 3);
	   	 Date date2=now.getTime();
	   	 List<Sentence> sentences=sentenceDao.getByUserID(userID, page, limit);
	   	 Assert.assertTrue(sentences.size()>3);
	   	 for(int i=0;i<sentences.size();i++){
	   		 System.out.println("SentenceID--->"+sentences.get(i).getSentenceID());
	   	 }
    }
     @Test
	 public void getAttention(){//List<Long> attentionUserList,int page,int limit
    	 long selfID=addUser("jazywoo","wujianzhi","123456");
     	 long attentionerID1=addUser("jazywoo1","wujianzhi1","123456");
     	 addAttention(selfID,attentionerID1);
     	 long  attentionerID2=addUser("jazywoo1","wujianzhi1","123456");
    	 addAttention(selfID,attentionerID2);
    	 
    	 addSentence(attentionerID1,0);
		 addSentence(attentionerID2,1);
    	 List<Long> attentionUserList=new ArrayList<Long>();
    	 attentionUserList.add(attentionerID1);
    	 attentionUserList.add(attentionerID2);
		 int page=0;
    	 int limit=10;
    	 List<Sentence> sentences=sentenceDao.getAttention(attentionUserList, page, limit);
	   	 Assert.assertTrue(sentences.size()>0);
	   	 for(int i=0;i<sentences.size();i++){
	   		 System.out.println("SentenceID--->"+sentences.get(i).getSentenceID());
	   	 }
	 }
	 
    public long addSentence(long userID,int date){
    	User user=(User) userDao.get(userID);
		Assert.assertNotNull(user);
		Sentence sentence=new Sentence();
		sentence.setContent("you know that");
		Calendar calendar=Calendar.getInstance();
 		calendar.setTime(new Date());
 		calendar.add(Calendar.DAY_OF_YEAR, date);
 		sentence.setCreateTime(calendar.getTime());
		sentence.setAuthor(user);
		sentenceDao.insert(sentence);
		return sentence.getSentenceID();
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
    private long addUser(String username,String nickName,String password){
    	User user=new User();
		user.setUsername(username);
		user.setNickname(nickName);
		user.setPassword(password);
		userDao.insert(user);
		return user.getUserID();
   }
   
}
