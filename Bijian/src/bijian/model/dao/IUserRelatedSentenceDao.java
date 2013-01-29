package bijian.model.dao;

import java.util.List;

import bijian.model.bean.relationbean.UserRelatedSentence;


public interface IUserRelatedSentenceDao extends IBaseDao {
	public int getActiveRelatedSentencesSize(long userID);//主要是用户未读的，而不是所有的
	public List<UserRelatedSentence> getActiveRelatedSentences(long userID,int page,int limit);
	public int getRelatedSentencesSize(long userID);
	public List<UserRelatedSentence> getRelatedSentences(long userID,int page,int limit);
}
