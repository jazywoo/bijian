package bijian.model.bean.relationbean;

import java.util.Date;

import bijian.model.bean.Sentence;
import bijian.model.bean.User;

public class LoveSentence {
    private long loveSentenceID;
    private User user;
    private Sentence sentence;
    private Date createTime;
    private int isValid;
	public long getLoveSentenceID() {
		return loveSentenceID;
	}
	public void setLoveSentenceID(long loveSentenceID) {
		this.loveSentenceID = loveSentenceID;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
}
