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

import bijian.model.bean.Comment;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.dao.ICommentDao;
import bijian.model.dao.ISentenceDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class CommentDaoTests  extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name="userDao",type=UserDaoImpl.class)
	private IUserDao userDao;
	@Resource(name="commentDao",type=CommentDaoImpl.class)
	private ICommentDao commentDao;
	@Resource(name="sentenceDao",type=SentenceDaoImpl.class)
	private ISentenceDao sentenceDao;
	
	@Test
	public void insert(){//T entity
		long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		User fromUser=(User) userDao.get(fromUserID);
		User toUser=(User) userDao.get(toUserID);
		Assert.assertNotNull(fromUser);
		Assert.assertNotNull(toUser);
		long sentenceID=addSentence(toUserID,0);
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		Assert.assertNotNull(sentence);
		
		Comment comment=new Comment();
		comment.setFromUser(fromUser);
		comment.setToUser(toUser);
		comment.setSentence(sentence);
		comment.setContent("不错喔");
		
		commentDao.insert(comment);
		Assert.assertNotNull("CommentID not null", comment.getCommentID());
	}
	@Test
    public void getByID(){//ID id
		long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		long sentenceID=addSentence(toUserID,0);
		long commentID=addComment(fromUserID,toUserID,sentenceID);
		
		Comment comment=(Comment) commentDao.get(commentID);
		Assert.assertNotNull(comment);
    }
	@Test
    public void update(){//T entity
		long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		long sentenceID=addSentence(toUserID,0);
		long commentID=addComment(fromUserID,toUserID,sentenceID);
		
		Comment comment=(Comment) commentDao.get(commentID);
		Assert.assertNotNull(comment);
		comment.setContent("update");
		comment=(Comment) commentDao.get(commentID);
		Assert.assertEquals("update", comment.getContent());
    }
    @Test
    public void delete(){//ID id
    	long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		long sentenceID=addSentence(toUserID,0);
		long commentID=addComment(fromUserID,toUserID,sentenceID);
		
		Comment comment=(Comment) commentDao.get(commentID);
		Assert.assertNotNull(comment);
		commentDao.delete(commentID);
		comment=(Comment) commentDao.get(commentID);
		Assert.assertNull(comment);
    }
    
    
    @Test
    public void getCommentList(){//final long sentenceID,final int page,final int limit
    	long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		long sentenceID=addSentence(toUserID,0);
		addComment(fromUserID,toUserID,sentenceID);
		addComment(fromUserID,toUserID,sentenceID);
		addComment(fromUserID,toUserID,sentenceID);
		addComment(fromUserID,toUserID,sentenceID);
		addComment(fromUserID,toUserID,sentenceID);
    	int size=commentDao.getCommentListSize(sentenceID);
    	Assert.assertTrue(size>0);
    	
    }
    @Test
    public void getCommentListSize(){//final long sentenceID
    	long fromUserID=addUser("jazywoo","wujianzhi","123456");
		long toUserID=addUser("jazywoo2","wujianzhi2","123456");
		long sentenceID=addSentence(toUserID,0);
		addComment(fromUserID,toUserID,sentenceID);
		addComment(fromUserID,toUserID,sentenceID);
		addComment(fromUserID,toUserID,sentenceID);
		addComment(fromUserID,toUserID,sentenceID);
		addComment(fromUserID,toUserID,sentenceID);
    	int page=0;
    	int limit=10;
    	List<Comment> comments=commentDao.getCommentList(sentenceID, page, limit);
    	Assert.assertTrue(0<comments.size());   
    	for(int i=0;i<comments.size();i++){
    		System.out.println("CommentID-->"+comments.get(i).getCommentID());
    	}
    }
    
    private long addComment(long fromUserID,long toUserID,long sentenceID){
		User fromUser=(User) userDao.get(fromUserID);
		User toUser=(User) userDao.get(toUserID);
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		
		Comment comment=new Comment();
		comment.setFromUser(fromUser);
		comment.setToUser(toUser);
		comment.setSentence(sentence);
		comment.setContent("不错喔");
		commentDao.insert(comment);
		return comment.getCommentID();
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
}
