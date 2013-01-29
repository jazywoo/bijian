package bijian.model.bean.relationbean;

import java.util.Date;

import bijian.model.bean.Sentence;
import bijian.model.bean.User;

public class Forwarding {
    private long forwardingID;
    private Sentence sentence;
    private User user;
    private Date createTime;
	public long getForwardingID() {
		return forwardingID;
	}
	public void setForwardingID(long forwardingID) {
		this.forwardingID = forwardingID;
	}
	public Sentence getSentence() {
		return sentence;
	}
	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    
}
