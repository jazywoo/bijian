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

import bijian.model.bean.ArticleObject;
import bijian.model.bean.Comment;
import bijian.model.bean.Diary;
import bijian.model.bean.User;
import bijian.model.dao.ICommentDao;
import bijian.model.dao.IDiaryDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class CommentDaoTests  extends AbstractTransactionalJUnit4SpringContextTests{
	 @Resource(name="diaryDao",type=DiaryDaoImpl.class)
	    private IDiaryDao diaryDao;
		@Resource(name="userDao",type=UserDaoImpl.class)
		private IUserDao userDao;
		@Resource(name="commentDao",type=CommentDaoImpl.class)
		private ICommentDao commentDao;
		
		@Test
		public void insert(){//T entity
			long fromUserID=0;
			long toUserID=1;
			User fromUser=(User) userDao.get(fromUserID);
			User toUser=(User) userDao.get(toUserID);
			Assert.assertNotNull(fromUser);
			Assert.assertNotNull(toUser);
			long aritcleObjectID=0;
			ArticleObject articleObject=(ArticleObject) diaryDao.get(aritcleObjectID);
			Assert.assertNotNull(articleObject);
			
			Comment comment=new Comment();
			comment.setFromUser(fromUser);
			comment.setToUser(toUser);
			comment.setArticleObject(articleObject);
			comment.setContent("不错喔");
			
			commentDao.insert(comment);
			Assert.assertNotNull("CommentID not null", comment.getCommentID());
		}
		@Test
	    public void getByID(){//ID id
			long commentID=addComment(0,1,0);
			Comment comment=(Comment) commentDao.get(commentID);
			Assert.assertNotNull(comment);
	    }
		@Test
	    public void update(){//T entity
			long commentID=addComment(0,1,0);
			Comment comment=(Comment) commentDao.get(commentID);
			Assert.assertNotNull(comment);
			comment.setContent("update");
			comment=(Comment) commentDao.get(commentID);
			Assert.assertEquals("update", comment.getContent());
	    }
	    @Test
	    public void delete(){//ID id
			long commentID=addComment(0,1,0);
			Comment comment=(Comment) commentDao.get(commentID);
			Assert.assertNotNull(comment);
			commentDao.delete(commentID);
			comment=(Comment) commentDao.get(commentID);
			Assert.assertNull(comment);
	    }
	    
	    
	    @Test
	    public void getCommentList(){//final long articleObjectID,final int page,final int limit
	    	addComment(0,1,0);
	    	addComment(0,1,0);
	    	addComment(0,1,0);
	    	addComment(0,1,0);
	    	addComment(0,1,0);
	    	long articleObjectID=0;
	    	int size=commentDao.getCommentListSize(articleObjectID);
	    	Assert.assertTrue(size>0);
	    	
	    }
	    @Test
	    public void getCommentListSize(){//final long articleObjectID
	    	addComment(0,1,0);
	    	addComment(0,1,0);
	    	addComment(0,1,0);
	    	addComment(0,1,0);
	    	addComment(0,1,0);
	    	long articleObjectID=0;
	    	int page=0;
	    	int limit=10;
	    	List<Comment> comments=commentDao.getCommentList(articleObjectID, page, limit);
	    	Assert.assertTrue(0<comments.size());   
	    	for(int i=0;i<comments.size();i++){
	    		System.out.println("CommentID-->"+comments.get(i).getCommentID());
	    	}
	    }
	    
	    private long addComment(long fromUserID,long toUserID,long aritcleObjectID){
			User fromUser=(User) userDao.get(fromUserID);
			User toUser=(User) userDao.get(toUserID);
			ArticleObject articleObject=(ArticleObject) diaryDao.get(aritcleObjectID);
			
			Comment comment=new Comment();
			comment.setFromUser(fromUser);
			comment.setToUser(toUser);
			comment.setArticleObject(articleObject);
			comment.setContent("不错喔");
			commentDao.insert(comment);
			return comment.getCommentID();
	    }
}
