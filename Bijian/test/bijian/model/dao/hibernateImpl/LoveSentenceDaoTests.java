package bijian.model.dao.hibernateImpl;

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
import bijian.model.bean.relationbean.LabelSentence;
import bijian.model.bean.relationbean.LoveSentence;
import bijian.model.dao.ILoveSentenceDao;
import bijian.model.dao.ISentenceDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class LoveSentenceDaoTests extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	@Resource(name="sentenceDao",type=SentenceDaoImpl.class)
    private ISentenceDao sentenceDao;
	@Resource(name="loveSentenceDao",type=LoveSentenceDaoImpl.class)
    private ILoveSentenceDao loveSentenceDao;
	
	@Test
	public void insert(){//T entity
		long userID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(userID,0);
		User user=(User) userDao.get(userID);
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		Assert.assertNotNull(user);
		Assert.assertNotNull(sentence);
		LoveSentence loveSentence=new LoveSentence();
		loveSentence.setUser(user);
		loveSentence.setSentence(sentence);
		loveSentence.setCreateTime(new Date());
		loveSentenceDao.insert(loveSentence);	
		Assert.assertNotNull(loveSentence.getLoveSentenceID());
	}
	@Test
    public void getByID(){//ID id
		long userID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(userID,0);
		long loveSentenceID=addLoveSentence(userID,sentenceID);
		LoveSentence loveSentence=(LoveSentence) loveSentenceDao.get(loveSentenceID);
		Assert.assertNotNull(loveSentence);
	}
	@Test
    public void update(){//T entity
		long userID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(userID,0);
		long loveSentenceID=addLoveSentence(userID,sentenceID);
		LoveSentence loveSentence=(LoveSentence) loveSentenceDao.get(loveSentenceID);
		Assert.assertNotNull(loveSentence);
		loveSentence.setIsValid(1);
		loveSentenceDao.update(loveSentence);
		loveSentence=(LoveSentence) loveSentenceDao.get(loveSentenceID);
		Assert.assertEquals(loveSentence.getIsValid(), 1);
	}
	@Test
    public void delete(){//ID id
		long userID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(userID,0);
		long loveSentenceID=addLoveSentence(userID,sentenceID);
		LoveSentence loveSentence=(LoveSentence) loveSentenceDao.get(loveSentenceID);
		Assert.assertNotNull(loveSentence);
		loveSentenceDao.delete(loveSentenceID);
		loveSentence=(LoveSentence) loveSentenceDao.get(loveSentenceID);
		Assert.assertNull(loveSentence);
	}
	@Test
	public void getLoveSentencesByUser() {//final long userID,final int page,final int limit
		long userID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(userID,0);
		addLoveSentence(userID,sentenceID);
		sentenceID=addSentence(userID,1);
		addLoveSentence(userID,sentenceID);
		sentenceID=addSentence(userID,2);
		addLoveSentence(userID,sentenceID);
		
		int page=0;
		int limit=10;
		List<LoveSentence> loveSentences=loveSentenceDao.getLoveSentencesByUser(userID, page, limit);	
		Assert.assertTrue(0<loveSentences.size());   
    	for(int i=0;i<loveSentences.size();i++){
    		System.out.println("loveSentences-->"+loveSentences.get(i).getLoveSentenceID());
    	}
	}
	@Test
    public void getLoveSentencesBySentence() {//final long sentenceID,final int page,final int limit
		long userID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(userID,0);
		addLoveSentence(userID,sentenceID);
		userID=addUser("jazywoo1","wujianzhi","123456");
		addLoveSentence(userID,sentenceID);
		userID=addUser("jazywoo2","wujianzhi","123456");
		addLoveSentence(userID,sentenceID);
		userID=addUser("jazywoo3","wujianzhi","123456");
		addLoveSentence(userID,sentenceID);
		
		int page=0;
		int limit=10;
		List<LoveSentence> loveSentences=loveSentenceDao.getLoveSentencesBySentence(sentenceID, page, limit);	
		Assert.assertTrue(0<loveSentences.size());   
    	for(int i=0;i<loveSentences.size();i++){
    		System.out.println("loveSentences-->"+loveSentences.get(i).getLoveSentenceID());
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
	private long addSentence(long userID,int date){
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
	private long addLoveSentence(long userID,long sentenceID){
		User user=(User) userDao.get(userID);
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		LoveSentence loveSentence=new LoveSentence();
		loveSentence.setUser(user);
		loveSentence.setSentence(sentence);
		loveSentence.setCreateTime(new Date());
		loveSentenceDao.insert(loveSentence);	
		return loveSentence.getLoveSentenceID();
	}
	
	
	
}
