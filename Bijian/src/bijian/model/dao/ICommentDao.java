package bijian.model.dao;

import java.util.ArrayList;
import java.util.List;

import bijian.model.bean.Comment;

public interface ICommentDao extends IBaseDao {	   
	   public List<Comment> getCommentList(long sentenceID,int page,int limit);
	   public int getCommentListSize(long sentenceID);
}
