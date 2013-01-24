package bijian.model.dao;

import java.util.Date;
import java.util.List;

import bijian.model.bean.Diary;

public interface IDiaryDao extends IBaseDao {
	   public List<Diary> getByUserID(long userID,int page,int limit) ;//特定用户的日志
	   public List<Diary> getByUserID(long userID,Date date1,Date date2,int page,int limit) ;//特定用户时间段的日志
	   public List<Diary> getByMoodValue(int value1,int value2,int page,int limit) ;//特定心情的日志
	   public List<Diary> getByMoodValue(int value1,int value2,Date date1,Date date2,int page,int limit) ;//特定心情时间段的日志
	   public List<Diary> getByUserAndMood(long userID,int value1,int value2,int page,int limit) ;//特定用户特定心情的日志
	   public List<Diary> getByUserAndMood(long userID,int value1,int value2,Date date1,Date date2,int page,int limit) ;//特定用户特定心情时间段的日志
	   
	   public List<Diary> get(List<Long> attentionUserList,int page,int limit) ;//某一个用户的所有关注人的最新日志
	   
}
