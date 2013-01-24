package bijian.model.dao.hibernateImpl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import bijian.model.bean.Notice;
import bijian.model.bean.User;
import bijian.model.dao.INoticeDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class NoticeDaoTests extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource(name="userDao",type=UserDaoImpl.class)
    private IUserDao userDao;
	@Resource(name="noticeDao",type=NoticeDaoImpl.class)
    private INoticeDao noticeDao;
	
	@Test
	public void insert(){
		long userID=0;
		User user=(User) userDao.get(userID);
		Assert.assertNotNull(user);
		Notice notice=new Notice();
		notice.setContent("notice");
		notice.setUser(user);
		
		noticeDao.insert(notice);
		Assert.assertNotNull(notice.getNoticeID());
	}
	@Test
	public void delete(){
		long noticeID=addNotice(0);
		Notice notice=(Notice) noticeDao.get(noticeID);
		Assert.assertNotNull(notice);
		noticeDao.delete(noticeID);
		notice=(Notice) noticeDao.get(noticeID);
		Assert.assertNull(notice);
	}
	@Test
	public void getNoticeListSize(){
		addNotice(0);
		addNotice(0);
		addNotice(0);
		addNotice(0);
		addNotice(0);
		long userID=0;
		int size=noticeDao.getNoticeListSize(userID);
		Assert.assertTrue(size>=5);
	}
	@Test
    public void getNoticeList(){
		addNotice(0);
		addNotice(0);
		addNotice(0);
		addNotice(0);
		addNotice(0);
		long userID=0;
		int page=0;
		int limit=10;
		List<Notice> notices=noticeDao.getNoticeList(userID, page, limit);
		Assert.assertTrue(0<notices.size());   
    	for(int i=0;i<notices.size();i++){
    		System.out.println("NoticeID-->"+notices.get(i).getNoticeID());
    	}
	}
	
	private long addNotice(long userID){
		User user=(User) userDao.get(userID);
		Notice notice=new Notice();
		notice.setContent("notice");
		notice.setUser(user);
		
		noticeDao.insert(notice);
		return notice.getNoticeID();
	}
}
