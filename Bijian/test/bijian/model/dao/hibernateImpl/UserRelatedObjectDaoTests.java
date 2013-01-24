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
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.UserRelatedSentence;
import bijian.model.dao.IDiaryDao;
import bijian.model.dao.IUserDao;
import bijian.model.dao.IUserRelatedObjectDao;

/**
 * @author jazywoo
 * 测试通过
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class UserRelatedObjectDaoTests extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	@Resource(name="diaryDao",type=DiaryDaoImpl.class)
    private IDiaryDao diaryDao;
	@Resource(name="userRelatedObjectDao",type=UserRelatedObjectDaoImpl.class)
    private IUserRelatedObjectDao userRelatedObjectDao;
	
    @Test
    public void insert(){//T entity
    	long userID=0;
    	long objectID=0;
    	User user=(User) userDao.get(userID);
    	ArticleObject articleObject=(ArticleObject) diaryDao.get(objectID);
    	Assert.assertNotNull(user);
    	Assert.assertNotNull(articleObject);
    	
    	UserRelatedSentence relatedObject=new UserRelatedSentence();
    	relatedObject.setUser(user);
    	relatedObject.setObject(articleObject);
    	relatedObject.setIsObjectActive(1);
    	relatedObject.setObjectType(1);
    	
    	userRelatedObjectDao.insert(relatedObject);
    	Assert.assertNotNull(relatedObject.getUserRelatedObjectID());
    }
    
    @Test
	public void update(){//
    	long userRelatedObjectID=addUserRelatedObject(0,0,0,1);
    	UserRelatedSentence relatedObject=(UserRelatedSentence) userRelatedObjectDao.get(userRelatedObjectID);
    	relatedObject.setIsObjectActive(0);
    	relatedObject=(UserRelatedSentence) userRelatedObjectDao.get(userRelatedObjectID);
    	Assert.assertTrue(0==relatedObject.getIsObjectActive());
	}
    
    @Test
    public void getActiveRelatedSentencesSize(){//long userID
    	addUserRelatedObject(0,0,0,1);
    	addUserRelatedObject(0,0,0,1);
    	addUserRelatedObject(0,0,0,1);
    	addUserRelatedObject(0,0,0,1);
    	addUserRelatedObject(0,0,0,1);
    	long userID=0;
    	int size=userRelatedObjectDao.getActiveRelatedSentencesSize(userID);
    	Assert.assertTrue(size>=5);
    }
    @Test
	public void getRelatedSentences(){//long userID,int page,int limit
		addUserRelatedObject(0,0,0,1);
    	addUserRelatedObject(0,0,0,1);
    	addUserRelatedObject(0,0,0,1);
    	addUserRelatedObject(0,0,0,1);
    	addUserRelatedObject(0,0,0,1);
    	long userID=0;
    	int page=0;
    	int limit=10;
    	List<UserRelatedSentence> relatedObjects=userRelatedObjectDao.getRelatedSentences(userID, page, limit);
    	Assert.assertTrue(0<relatedObjects.size());   
    	for(int i=0;i<relatedObjects.size();i++){
    		System.out.println("RelatedObjectID-->"+relatedObjects.get(i).getUserRelatedObjectID());
    	}
	}
    @Test
	public void getActiveRelatedDiariesSize(){//long userID
		addUserRelatedObject(0,0,1,1);
    	addUserRelatedObject(0,0,1,1);
    	addUserRelatedObject(0,0,1,1);
    	addUserRelatedObject(0,0,1,1);
    	addUserRelatedObject(0,0,1,1);
    	long userID=0;
    	int size=userRelatedObjectDao.getActiveRelatedDiariesSize(userID);
    	Assert.assertTrue(size>=5);
	}
    @Test
	public void getRelatedDiaries(){//long userID,int page,int limit
		addUserRelatedObject(0,0,1,1);
    	addUserRelatedObject(0,0,1,1);
    	addUserRelatedObject(0,0,1,1);
    	addUserRelatedObject(0,0,1,1);
    	addUserRelatedObject(0,0,1,1);
    	long userID=0;
    	int page=0;
    	int limit=10;
    	List<UserRelatedSentence> relatedObjects=userRelatedObjectDao.getRelatedDiaries(userID, page, limit);
    	Assert.assertTrue(0<relatedObjects.size());   
    	for(int i=0;i<relatedObjects.size();i++){
    		System.out.println("RelatedObjectID-->"+relatedObjects.get(i).getUserRelatedObjectID());
    	}
	}
    
    private long addUserRelatedObject(long userID,long objectID,int objectType,int isObjectActive){
    	User user=(User) userDao.get(userID);
    	ArticleObject articleObject=(ArticleObject) diaryDao.get(objectID);
    	Assert.assertNotNull(user);
    	Assert.assertNotNull(articleObject);
    	
    	UserRelatedSentence relatedObject=new UserRelatedSentence();
    	relatedObject.setUser(user);
    	relatedObject.setObject(articleObject);
    	relatedObject.setIsObjectActive(isObjectActive);
    	relatedObject.setObjectType(objectType);
    	
    	userRelatedObjectDao.insert(relatedObject);
    	return relatedObject.getUserRelatedObjectID();
    }
}
