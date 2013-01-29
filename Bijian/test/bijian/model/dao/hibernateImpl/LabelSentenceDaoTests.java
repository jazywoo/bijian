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

import bijian.model.bean.Label;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.LabelSentence;
import bijian.model.dao.ILabelDao;
import bijian.model.dao.ILabelSentenceDao;
import bijian.model.dao.ISentenceDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class LabelSentenceDaoTests extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	@Resource(name="labelDao",type=LabelDaoImpl.class)
    private ILabelDao labelDao;
	@Resource(name="sentenceDao",type=SentenceDaoImpl.class)
    private ISentenceDao sentenceDao;
	@Resource(name="labelSentenceDao",type=LabelSentenceDaoImpl.class)
    private ILabelSentenceDao labelSentenceDao;
	
	@Test
	public void insert(){//T entity
		long userID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(userID,0);
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		long labelID=addLabel("开心");
		Label label=(Label) labelDao.get(labelID);
		LabelSentence labelSentence=new LabelSentence();
		labelSentence.setLabel(label);
		labelSentence.setSentence(sentence);
		labelSentenceDao.insert(labelSentence);
		Assert.assertNotNull(labelSentence.getLabelSentenceID());
	}
	@Test
    public void getByID(){//ID id
		long UserID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(UserID,0);
		long labelID=addLabel("开心");
		long labelSentenceID=addLabelSentence(sentenceID,labelID);
		
		LabelSentence labelSentence=(LabelSentence) labelSentenceDao.get(labelSentenceID);
		Assert.assertNotNull(labelSentence);
	}
	@Test
    public void update(){//T entity
		long UserID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(UserID,0);
		long labelID=addLabel("开心");
		long labelSentenceID=addLabelSentence(sentenceID,labelID);
		
		LabelSentence labelSentence=(LabelSentence) labelSentenceDao.get(labelSentenceID);
		Assert.assertNotNull(labelSentence);
		labelID=addLabel("开心123");
		Label label=(Label) labelDao.get(labelID);
		labelSentence.setLabel(label);
		labelSentenceDao.update(labelSentence);
		Assert.assertEquals(labelSentence.getLabel().getContent(), "开心123");
	}
	@Test
    public void delete(){//ID id
		long UserID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(UserID,0);
		long labelID=addLabel("开心");
		long labelSentenceID=addLabelSentence(sentenceID,labelID);
		
		LabelSentence labelSentence=(LabelSentence) labelSentenceDao.get(labelSentenceID);
		Assert.assertNotNull(labelSentence);
		labelSentenceDao.delete(labelSentenceID);
		labelSentence=(LabelSentence) labelSentenceDao.get(labelSentenceID);
		Assert.assertNull(labelSentence);
	}
	@Test
	public void getLabelSentencesByLabel(){//long labelID,int page,int limit
		long UserID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(UserID,0);
		long labelID=addLabel("开心");
		addLabelSentence(sentenceID,labelID);
		
		UserID=addUser("jazywoo","wujianzhi","123456");
		sentenceID=addSentence(UserID,0);
		addLabelSentence(sentenceID,labelID);
		
		UserID=addUser("jazywoo","wujianzhi","123456");
		sentenceID=addSentence(UserID,0);
		addLabelSentence(sentenceID,labelID);
		
		int page=0;
		int limit=10;
		List<LabelSentence> labelSentences=labelSentenceDao.getLabelSentencesByLabel(labelID, page, limit);	
		Assert.assertTrue(0<labelSentences.size());   
    	for(int i=0;i<labelSentences.size();i++){
    		System.out.println("LabelSentence-->"+labelSentences.get(i).getLabelSentenceID());
    	}
	}
	@Test
    public void getLabelSentencesBySentence(){//long sentenceID,int page,int limit
		long UserID=addUser("jazywoo","wujianzhi","123456");
		long sentenceID=addSentence(UserID,0);
		long labelID=addLabel("开心");
		addLabelSentence(sentenceID,labelID);
		
	    labelID=addLabel("开心1");
		addLabelSentence(sentenceID,labelID);
		labelID=addLabel("开心2");
		addLabelSentence(sentenceID,labelID);
		labelID=addLabel("开心3");
		addLabelSentence(sentenceID,labelID);
		
		int page=0;
		int limit=10;
		List<LabelSentence> labelSentences=labelSentenceDao.getLabelSentencesBySentence(sentenceID, page, limit);	
		Assert.assertTrue(0<labelSentences.size());   
    	for(int i=0;i<labelSentences.size();i++){
    		System.out.println("LabelSentence-->"+labelSentences.get(i).getLabelSentenceID());
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
    private long addLabel(String content){
		Label label=new Label();
		label.setContent(content);
		labelDao.insert(label);
		return label.getLabelID();
	}
    private long addLabelSentence(long labelID,long sentenceID){
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		Label label=(Label) labelDao.get(labelID);
		LabelSentence labelSentence=new LabelSentence();
		labelSentence.setLabel(label);
		labelSentence.setSentence(sentence);
		labelSentenceDao.insert(labelSentence);
		return labelSentence.getLabelSentenceID();
    }
}
