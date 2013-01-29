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
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.UserRelatedSentence;
import bijian.model.dao.ISentenceDao;
import bijian.model.dao.IUserDao;
import bijian.model.dao.IUserRelatedSentenceDao;

/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class UserRelatedSentenceDaoTests extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	@Resource(name="sentenceDao",type=SentenceDaoImpl.class)
    private ISentenceDao sentenceDao;
	@Resource(name="userRelatedSentenceDao",type=UserRelatedSentenceDaoImpl.class)
    private IUserRelatedSentenceDao userRelatedSentenceDao;
	
    @Test
    public void insert(){//T entity
    	long sentenceAuthorID=addUser("jazywoo2","wujianzhi2","123456");
    	long sentenceID=addSentence(sentenceAuthorID,0);
    	long userID=addUser("jazywoo","wujianzhi","123456");
    	User user=(User) userDao.get(userID);
    	
    	Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
    	Assert.assertNotNull(user);
    	Assert.assertNotNull(sentence);
    	
    	UserRelatedSentence relatedSentence=new UserRelatedSentence();
    	relatedSentence.setUser(user);
    	relatedSentence.setSentence(sentence);
    	relatedSentence.setIsSentenceActive(1);
    	
    	userRelatedSentenceDao.insert(relatedSentence);
    	Assert.assertNotNull(relatedSentence.getUserRelatedSentenceID());
    }
    
    @Test
	public void update(){
    	long sentenceAuthorID=addUser("jazywoo2","wujianzhi2","123456");
    	long sentenceID=addSentence(sentenceAuthorID,0);
    	long userID=addUser("jazywoo","wujianzhi","123456");
    	long userRelatedSentenceID=addUserRelatedSentence(userID,sentenceID,1);
    	UserRelatedSentence relatedSentence=(UserRelatedSentence) userRelatedSentenceDao.get(userRelatedSentenceID);
    	relatedSentence.setIsSentenceActive(0);
    	relatedSentence=(UserRelatedSentence) userRelatedSentenceDao.get(userRelatedSentenceID);
    	Assert.assertTrue(0==relatedSentence.getIsSentenceActive());
	}
    @Test
    public void getActiveRelatedSentences(){//long userID,int page,int limit
    	long sentenceAuthorID=addUser("jazywoo2","wujianzhi2","123456");
    	long sentenceID=addSentence(sentenceAuthorID,0);
    	long userID=addUser("jazywoo","wujianzhi","123456");
    	addUserRelatedSentence(userID,sentenceID,1);
    	sentenceAuthorID=addUser("jazywoo3","wujianzhi3","123456");
    	sentenceID=addSentence(sentenceAuthorID,1);
    	addUserRelatedSentence(userID,sentenceID,1);
    	sentenceAuthorID=addUser("jazywoo4","wujianzhi4","123456");
    	sentenceID=addSentence(sentenceAuthorID,0);
    	addUserRelatedSentence(userID,sentenceID,0);
    	
    	int page=0;
    	int limit=10;
    	List<UserRelatedSentence> relatedSentences=userRelatedSentenceDao.getActiveRelatedSentences(userID, page, limit);
    	Assert.assertTrue(2<=relatedSentences.size());   
    	for(int i=0;i<relatedSentences.size();i++){
    		System.out.println("ActiveRelatedSentenceID-->"+relatedSentences.get(i).getUserRelatedSentenceID());
    	}
    }
    @Test
	public void getRelatedSentences(){//long userID,int page,int limit
    	long sentenceAuthorID=addUser("jazywoo2","wujianzhi2","123456");
    	long sentenceID=addSentence(sentenceAuthorID,0);
    	long userID=addUser("jazywoo","wujianzhi","123456");
    	addUserRelatedSentence(userID,sentenceID,1);
    	sentenceAuthorID=addUser("jazywoo3","wujianzhi3","123456");
    	sentenceID=addSentence(sentenceAuthorID,1);
    	addUserRelatedSentence(userID,sentenceID,1);
    	sentenceAuthorID=addUser("jazywoo4","wujianzhi4","123456");
    	sentenceID=addSentence(sentenceAuthorID,0);
    	addUserRelatedSentence(userID,sentenceID,0);
    	
    	int page=0;
    	int limit=10;
    	List<UserRelatedSentence> relatedSentences=userRelatedSentenceDao.getActiveRelatedSentences(userID, page, limit);
    	Assert.assertTrue(1<=relatedSentences.size());   
    	for(int i=0;i<relatedSentences.size();i++){
    		System.out.println("RelatedSentenceID-->"+relatedSentences.get(i).getUserRelatedSentenceID());
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
    private long addUserRelatedSentence(long userID,long sentenceID,int isSentenceActive){
    	User user=(User) userDao.get(userID);
    	Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
    	UserRelatedSentence relatedSentence=new UserRelatedSentence();
    	relatedSentence.setSentence(sentence);
    	relatedSentence.setUser(user);
    	relatedSentence.setIsSentenceActive(isSentenceActive);
    	userRelatedSentenceDao.insert(relatedSentence);
    	return relatedSentence.getUserRelatedSentenceID();
    }
}
