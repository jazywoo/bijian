package bijian.model.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bijian.model.bean.Comment;
import bijian.model.bean.Label;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.Following;
import bijian.model.bean.relationbean.Forwarding;
import bijian.model.bean.relationbean.LoveSentence;
import bijian.model.bean.relationbean.ReportSentence;
import bijian.model.bean.relationbean.UserRelatedSentence;
import bijian.model.dao.IAttentionDao;
import bijian.model.dao.ICommentDao;
import bijian.model.dao.IFollowingDao;
import bijian.model.dao.IForwardingDao;
import bijian.model.dao.ILabelDao;
import bijian.model.dao.ILoveSentenceDao;
import bijian.model.dao.IReportSentenceDao;
import bijian.model.dao.ISentenceDao;
import bijian.model.dao.IUserDao;
import bijian.model.dao.IUserRelatedSentenceDao;
import bijian.model.service.IUserService;

public class UserServiceImpl implements IUserService{
	private IUserDao userDao;
	private IAttentionDao attentionDao;
	private IFollowingDao followingDao;
	private ILoveSentenceDao loveSentenceDao;
	private IForwardingDao forwardingDao;

	public void attentionOne(long myID, long otherID) { //关注
		User self=(User) userDao.get(myID);
		User attentioner=(User) userDao.get(otherID);
		Attention attention=new Attention();
		attention.setSelf(self);
		attention.setAttentioner(attentioner);
		attention.setCreateTime(new Date());
		attention.setIsValid(1);
		attentionDao.insert(attention);
	}
	public int getAttentionUsersSize(long userID){
		return attentionDao.getAttentionListSize(userID);
	}
	public List<User> getAttentionUsers(long userID, int page, int limit) {//得到关注人
		List<Attention> attentions=attentionDao.get(userID, page, limit);
		List<User> users=new ArrayList<User>();
		for(Attention a:attentions){
			users.add(a.getAttentioner());
		}
		return users;
	} 
	public int getFollowingUsersSize(long userID){
		return followingDao.getFollowingListSize(userID);
	}
	public List<User> getFollowingUsers(long userID, int page, int limit) {//得到被关注人
		List<Following> followings=followingDao.get(userID, page, limit);
		List<User> users=new ArrayList<User>();
		for(Following a:followings){
			users.add(a.getFollowinger());
		}
		return users;
	}

	public User getUser(long userID) {
		return (User) userDao.get(userID);
	}

	public List<User> getVisitUsers(long userID, int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<User> getSentenceLoveUsers(long sentenceID, int page, int limit) {
		List<LoveSentence> loveSentences=loveSentenceDao.getLoveSentencesBySentence(sentenceID, page, limit);
		List<User> users=new ArrayList<User>();
		for(LoveSentence l:loveSentences){
			users.add(l.getUser());
		}
		return users;
	}
	public List<User> getSentenceForwardingUsers(long sentenceID, int page, int limit) {
		List<Forwarding> forwardings=forwardingDao.getForwardingsBySentence(sentenceID, page, limit);
		List<User> users=new ArrayList<User>();
		for(Forwarding f:forwardings){
			users.add(f.getUser());
		}
		return users;
	}
	public List<User> getHotUsers() {
		int page=0;
		int limit=5;
		return userDao.getHotUsers(page, limit);
	}
	public User getHotUserByLabel(long labelID){
		return userDao.getHotUserByLabel(labelID);
	}
    public List<User> getActiveUsersByLabel(long labelID,int page,int limit){
    	return userDao.getActiveUsersByLabel(labelID, page, limit);
    }
	public User login(String username, String password) {
		User user=userDao.get(username);
		if(null!=user&&user.getPassword().equals(password)){
			return user;
		}
		return null;
	}

	public void register(User user) {
		userDao.insert(user);
	}

	public List<User> searchUser(User user, int page, int limit) {
		try {
			return userDao.getLike(user, page, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void update(User user) {
		userDao.update(user);
	}

	
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IAttentionDao getAttentionDao() {
		return attentionDao;
	}

	public void setAttentionDao(IAttentionDao attentionDao) {
		this.attentionDao = attentionDao;
	}

	public IFollowingDao getFollowingDao() {
		return followingDao;
	}

	public void setFollowingDao(IFollowingDao followingDao) {
		this.followingDao = followingDao;
	}

	public ILoveSentenceDao getLoveSentenceDao() {
		return loveSentenceDao;
	}

	public void setLoveSentenceDao(ILoveSentenceDao loveSentenceDao) {
		this.loveSentenceDao = loveSentenceDao;
	}

	public IForwardingDao getForwardingDao() {
		return forwardingDao;
	}

	public void setForwardingDao(IForwardingDao forwardingDao) {
		forwardingDao = forwardingDao;
	}



	
}
