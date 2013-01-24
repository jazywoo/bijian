package bijian.model.dao.hibernateImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import bijian.model.bean.ArticleObject;
import bijian.model.bean.Diary;
import bijian.model.bean.User;
import bijian.model.dao.IDiaryDao;
import bijian.model.dao.IUserDao;

/**
 * @author jazywoo
 * 测试通过
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class DiaryDaoTests extends AbstractTransactionalJUnit4SpringContextTests{
    @Resource(name="diaryDao",type=DiaryDaoImpl.class)
    private IDiaryDao diaryDao;
	@Resource(name="userDao",type=UserDaoImpl.class)
	private IUserDao userDao;
	
	@Test
	public void insert(){//T entity
		long userID=0;
		User user=(User) userDao.get(userID);
		Assert.assertNotNull(user);
		Diary diary=new Diary();
		diary.setLabelsJson("{type:'diaryLabels',result:['天气','美好','心情']}");
		diary.setAuthor(user);
		diary.setTitle("又是美好的一天");
		diary.setContent("今天天气很好，非常不错");
		diary.setCreateTime(new Date());
		diary.setMoodValue(10);
		
		diaryDao.insert(diary);
		Assert.assertNotNull("DiaryID not null", diary.getArticleObjectID());
	}
	@Test
    public void getByID(){//ID id
		long diaryID=0;
		Diary diary=(Diary) diaryDao.get(diaryID);
		System.out.println("testGetByID "+diary.getDiaryID());
		Assert.assertNotNull(diary);
    }
	@Test
    public void update(){//T entity
		long diaryID=0;
		Diary diary=(Diary) diaryDao.get(diaryID);
		System.out.println("testGetByID "+diary.getDiaryID());
		Assert.assertNotNull(diary);
		diary.setTitle("update_title");
		diaryDao.update(diary);
		Assert.assertEquals("update_title", diary.getTitle());
    }
    @Test
    public void delete(){//ID id
    	long diaryID=0;
		Diary diary=(Diary) diaryDao.get(diaryID);
		System.out.println("testDelete "+diary.getTitle());
		Assert.assertNotNull(diary);
		diaryDao.delete(diaryID);
		diary=(Diary) diaryDao.get(diaryID);
    	Assert.assertNull("删除user后，为空",diary);
    	System.out.println("testDelete ");
    }
    
     
	 @Test
     public void getByUserID(){//long userID,int page,int limit
		 addDiary(0,10,0);
    	 long userID=0;
    	 int page=0;
    	 int limit=10;
    	 List<Diary> diarys=diaryDao.getByUserID(userID, page, limit);
    	 Assert.assertTrue(diarys.size()>0);
    	 for(int i=0;i<diarys.size();i++){
    		 System.out.println("diaryID--->"+diarys.get(i).getDiaryID());
    	 }
     }
	 
	 @Test
     public void getByUserID2(){//long userID,Date date1,Date date2,int page,int limit
		 addDiary(0,10,0);
		 addDiary(0,10,1);
		 addDiary(0,10,2);
		 addDiary(0,10,3);
		 long userID=0;
    	 int page=0;
    	 int limit=10;
    	 Date date1=new Date(System.currentTimeMillis());
    	 Calendar now = Calendar.getInstance(); 
    	 now.setTime(date1);
    	 now.add(Calendar.DAY_OF_YEAR, 3);
    	 Date date2=now.getTime();
    	 List<Diary> diarys=diaryDao.getByUserID(userID,date1,date2, page, limit);
    	 Assert.assertTrue(diarys.size()>0);
    	 for(int i=0;i<diarys.size();i++){
    		 System.out.println("diaryID--->"+diarys.get(i).getDiaryID());
    	 }
     }
	 
     @Test
     public void getByMoodvalue(){//int value1,int value2,int page,int limit
    	 addDiary(0,1,0);
		 addDiary(0,3,1);
		 addDiary(0,4,2);
		 addDiary(0,6,3);
    	 int page=0;
    	 int limit=10;
    	 int value1=0;
    	 int value2=10;
    	 List<Diary> diarys=diaryDao.getByMoodValue(value1, value2, page, limit);
    	 Assert.assertTrue(diarys.size()>0);
    	 for(int i=0;i<diarys.size();i++){
    		 System.out.println("diaryID--->"+diarys.get(i).getDiaryID());
    	 }
     }
     
     @Test
     public void getByMoodvalue2(){//int value1,int value2,Date date1,Date date2,int page,int limit
    	 addDiary(0,1,0);
		 addDiary(0,3,1);
		 addDiary(0,4,2);
		 addDiary(0,6,3);
    	 int page=0;
    	 int limit=10;
    	 int value1=0;
    	 int value2=10;
    	 Date date1=new Date(System.currentTimeMillis());
    	 Calendar now = Calendar.getInstance(); 
    	 now.setTime(date1);
    	 now.add(Calendar.DAY_OF_YEAR, 3);
    	 Date date2=now.getTime();
    	 List<Diary> diarys=diaryDao.getByMoodValue(value1, value2,date1, date2 , page, limit);
    	 Assert.assertTrue(diarys.size()>0);
    	 for(int i=0;i<diarys.size();i++){
    		 System.out.println("diaryID--->"+diarys.get(i).getDiaryID());
    	 }
     }
     
     @Test
     public void getByUserAndMood(){//long userID,int value1,int value2,int page,int limit
    	 addDiary(0,1,0);
		 addDiary(0,3,1);
		 addDiary(0,4,2);
		 addDiary(0,6,3);
    	 long userID=0;
    	 int page=0;
    	 int limit=10;
    	 int value1=0;
    	 int value2=10;
    	 List<Diary> diarys=diaryDao.getByUserAndMood(userID, value1, value2, page, limit);
    	 Assert.assertTrue(diarys.size()>0);
    	 for(int i=0;i<diarys.size();i++){
    		 System.out.println("diaryID--->"+diarys.get(i).getDiaryID());
    	 }
     }
     
     @Test
     public void getByUserAndMood2(){//long userID,int value1,int value2,Date date1,Date date2,int page,int limit
    	 addDiary(0,1,0);
		 addDiary(0,3,1);
		 addDiary(0,4,2);
		 addDiary(0,6,3);
    	 long userID=0;
    	 int page=0;
    	 int limit=10;
    	 int value1=0;
    	 int value2=10;
    	 Date date1=new Date(System.currentTimeMillis());
    	 Calendar now = Calendar.getInstance(); 
    	 now.setTime(date1);
    	 now.add(Calendar.DAY_OF_YEAR, 3);
    	 Date date2=now.getTime();
    	 List<Diary> diarys=diaryDao.getByUserAndMood(userID, value1, value2,date1, date2 , page, limit);
    	 Assert.assertTrue(diarys.size()>0);
    	 for(int i=0;i<diarys.size();i++){
    		 System.out.println("diaryID--->"+diarys.get(i).getDiaryID());
    	 }
     }
     
     @Test
     public void get(){//List<Long> attentionUserList,int page,int limit
    	 addDiary(0,1,0);
		 addDiary(0,3,1);
		 addDiary(1,4,2);
		 addDiary(1,6,3);
    	 List<Long> attentionUserList=new ArrayList<Long>();
    	 attentionUserList.add(new Long(0));
    	 attentionUserList.add(new Long(1));
    	 int page=0;
    	 int limit=10;
    	 List<Diary> diarys=diaryDao.get(attentionUserList, page, limit);
    	 Assert.assertTrue(diarys.size()>0);
    	 for(int i=0;i<diarys.size();i++){
    		 System.out.println("diaryID--->"+diarys.get(i).getDiaryID());
    	 }
    	 
     }
     //向数据库添加记录，方便测试
     private void addDiary(long userID,int moodValue,int date){//T entity
 		User user=(User) userDao.get(userID);
 		Diary diary=new Diary();
 		diary.setLabelsJson("{type:'diaryLabels',result:['天气','美好','心情']}");
 		diary.setAuthor(user);
 		diary.setTitle("又是美好的一天");
 		diary.setContent("今天天气很好，非常不错");
 		Calendar calendar=Calendar.getInstance();
 		calendar.setTime(new Date());
 		calendar.add(Calendar.DAY_OF_YEAR, date);
 		diary.setCreateTime(calendar.getTime());
 		diary.setMoodValue(moodValue);
 		
 		diaryDao.insert(diary);
 	 }  
}
