package bijian.model.service;

import java.util.List;

import bijian.model.bean.Comment;

public interface ICommentService {

	//对于已登录的用户------
    public void deleteComment(long commentID);//只能删除自己句子的评论
    public void commentSentence(long sentenceID,long userID,Comment comment);//评论
    
    //公共的操作------
    public int getCommentSize(long sentenceID);//某一句子下的评论
    public List<Comment> getComment(long sentenceID,int page,int limit);//某一句子下的评论
    
    
}
