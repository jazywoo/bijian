package bijian.model.dao;

import java.util.Date;
import java.util.List;

import bijian.model.bean.Sentence;
import bijian.model.bean.User;

public interface ISentenceDao extends IBaseDao {
   //get方法   得到不同条件下的句子
   public Sentence getHotestByUser(long userID);
   public int getSizeByUser(long userID);
   public List<Sentence> getByUser(long userID,int page,int limit) ;//特定用户的句子
   public List<Sentence> getByUser(long userID,Date date1,Date date2,int page,int limit) ;//特定用户时间段的句子
 
   public List<Sentence> getAttention(List<Long> attentionUserList,int page,int limit) ;//某一个用户的所有关注人的最新句子
   public List<Sentence> getHot(int page,int limit);
   public List<Sentence> getLatest(int page,int limit);
   
}
