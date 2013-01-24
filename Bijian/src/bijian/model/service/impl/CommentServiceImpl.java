package bijian.model.service.impl;

import java.util.List;

import bijian.model.bean.Comment;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.dao.ICommentDao;
import bijian.model.dao.ISentenceDao;
import bijian.model.dao.IUserDao;
import bijian.model.service.ICommentService;

public class CommentServiceImpl implements ICommentService {
	private ISentenceDao sentenceDao;
	private IUserDao userDao;
	private ICommentDao commentDao;

	public void commentSentence(long articleObjectID, Comment comment) {
		Sentence sentence=(Sentence) sentenceDao.get(articleObjectID);
		sentence.getComments().add(comment);
		sentenceDao.update(sentence);
	}

	public void deleteComment(long userID,long articleObjectID, long commentID) {
		Sentence sentence=(Sentence) sentenceDao.get(articleObjectID);
		if(sentence.getAuthor().getUserID()!=userID){
			System.out.println("无权限，句子不属于此人");
			return ;
		}
		commentDao.delete(commentID);
	}

	public List<Comment> getComment(long articleObjectID, int page, int limit) {
		return commentDao.getCommentList(articleObjectID, page, limit);
	}
	

	public ISentenceDao getSentenceDao() {
		return sentenceDao;
	}

	public void setSentenceDao(ISentenceDao sentenceDao) {
		this.sentenceDao = sentenceDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public ICommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(ICommentDao commentDao) {
		this.commentDao = commentDao;
	}

}
