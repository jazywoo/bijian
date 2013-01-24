package bijian.model.bean.relationbean;

import java.util.Date;

import bijian.model.bean.User;

public class Attention {
    private long attentionID;
    private User self;
    private User attentioner;
    private Date createTime;
    private Integer isValid;
	public long getAttentionID() {
		return attentionID;
	}
	public void setAttentionID(long attentionID) {
		this.attentionID = attentionID;
	}
	public User getSelf() {
		return self;
	}
	public void setSelf(User self) {
		this.self = self;
	}
	public User getAttentioner() {
		return attentioner;
	}
	public void setAttentioner(User attentioner) {
		this.attentioner = attentioner;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
}
