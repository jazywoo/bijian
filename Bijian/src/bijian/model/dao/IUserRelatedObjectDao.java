package bijian.model.dao;

import java.util.List;

import bijian.model.bean.relationbean.UserRelatedSentence;


public interface IUserRelatedObjectDao extends IBaseDao {
	public int getActiveRelatedSentencesSize(long userID);//主要是用户未读的，而不是所有的
	public List<UserRelatedSentence> getRelatedSentences(long userID,int page,int limit);
	public int getActiveRelatedDiariesSize(long userID);//主要是用户未读的，而不是所有的
	public List<UserRelatedSentence> getRelatedDiaries(long userID,int page,int limit);
}
