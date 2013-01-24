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

import bijian.model.bean.Diary;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.dao.IDiaryDao;
import bijian.model.dao.ISentenceDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class SentenceDaoTests extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name="sentenceDao",type=SentenceDaoImpl.class)
    private ISentenceDao sentenceDao;
	@Resource(name="userDao",type=UserDaoImpl.class)
	private IUserDao userDao;
	
	@Test
	public void insert(){//T entity
		long userID=0;
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
		long sentenceID=addSentence(0,0);
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		Assert.assertNotNull(sentence);
    }
	@Test
    public void update(){//T entity
		long sentenceID=addSentence(0,0);
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		Assert.assertNotNull(sentence);
		sentence.setContent("update_content");
		sentenceDao.update(sentence);
		sentence=(Sentence) sentenceDao.get(sentenceID);
		Assert.assertEquals("update_content", sentence.getContent());
    }
    @Test
    public void delete(){//ID id
    	long sentenceID=addSentence(0,0);
    	System.out.println("9999999999999   ----"+sentenceID);
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		Assert.assertNotNull(sentence);
		sentenceDao.delete(sentenceID);
		sentence=(Sentence) sentenceDao.get(sentenceID);
    	Assert.assertNull("删除user后，为空",sentence);
    }
    
    
    @Test
    public void getByUserID(){//long userID,int page,int limit
    	 addSentence(0,0);
    	 addSentence(0,1);
    	 addSentence(0,2);
	   	 long userID=0;
	   	 int page=0;
	   	 int limit=10;
	   	 List<Sentence> sentences=sentenceDao.getByUserID(userID, page, limit);
	   	 Assert.assertTrue(sentences.size()>0);
	   	 for(int i=0;i<sentences.size();i++){
	   		 System.out.println("SentenceID--->"+sentences.get(i).getSentenceID());
	   	 }
    }
	 
	 @Test
    public void getByUserID2(){//long userID,Date date1,Date date2,int page,int limit
		 addSentence(0,0);
    	 addSentence(0,1);
    	 addSentence(0,2);
    	 addSentence(0,3);
		 long userID=0;
	   	 int page=0;
	   	 int limit=10;
	   	 Date date1=new Date(System.currentTimeMillis());
	   	 Calendar now = Calendar.getInstance(); 
	   	 now.setTime(date1);
	   	 now.add(Calendar.DAY_OF_YEAR, 3);
	   	 Date date2=now.getTime();
	   	 List<Sentence> sentences=sentenceDao.getByUserID(userID, page, limit);
	   	 Assert.assertTrue(sentences.size()>0);
	   	 for(int i=0;i<sentences.size();i++){
	   		 System.out.println("SentenceID--->"+sentences.get(i).getSentenceID());
	   	 }
    }
	 
	 @Test
     public void get(){//List<Long> attentionUserList,int page,int limit
		 addSentence(0,0);
    	 addSentence(0,1);
    	 addSentence(1,2);
    	 addSentence(1,3);
    	 List<Long> attentionUserList=new ArrayList<Long>();
    	 attentionUserList.add(new Long(0));
    	 attentionUserList.add(new Long(1));
    	 int page=0;
    	 int limit=10;
    	 List<Sentence> sentences=sentenceDao.get(attentionUserList, page, limit);
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
    
}
