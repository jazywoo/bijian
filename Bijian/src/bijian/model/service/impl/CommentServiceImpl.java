package bijian.model.service.impl;

import java.util.Date;
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

	public void commentSentence(long sentenceID,long userID,Comment comment) {
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		User user=(User) userDao.get(userID);
		Comment newComment=new Comment();
		newComment.setContent(comment.getContent());
		newComment.setFromUser(user);
		newComment.setSentence(sentence);
		newComment.setToUser(sentence.getAuthor());
		newComment.setCreateTime(new Date());
		newComment.setIsValid(1);
		commentDao.insert(newComment);
	}

	public void deleteComment(long commentID) {
		commentDao.delete(commentID);
	}

	public int getCommentSize(long sentenceID){
		return commentDao.getCommentListSize(sentenceID);
	}
	public List<Comment> getComment(long sentenceID, int page, int limit) {
		return commentDao.getCommentList(sentenceID, page, limit);
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
