package bijian.model.bean.relationbean;

import java.util.Date;

import bijian.model.bean.Sentence;
import bijian.model.bean.User;

public class UserRelatedSentence {
     private long userRelatedSentenceID;
     private User user;
     private Sentence sentence;
     private Integer isSentenceActive;
     private Date createTime;
	public long getUserRelatedSentenceID() {
		return userRelatedSentenceID;
	}
	public void setUserRelatedSentenceID(long userRelatedSentenceID) {
		this.userRelatedSentenceID = userRelatedSentenceID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Sentence getSentence() {
		return sentence;
	}
	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}
	public Integer getIsSentenceActive() {
		return isSentenceActive;
	}
	public void setIsSentenceActive(Integer isSentenceActive) {
		this.isSentenceActive = isSentenceActive;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
     
}
