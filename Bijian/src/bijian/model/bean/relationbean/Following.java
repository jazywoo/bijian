package bijian.model.bean.relationbean;

import java.util.Date;

import bijian.model.bean.User;

public class Following {
    private long followingID;
    private User self;
    private User followinger;
    private Date createTime;
    private Integer isValid;
	public long getFollowingID() {
		return followingID;
	}
	public void setFollowingID(long followingID) {
		this.followingID = followingID;
	}
	public User getSelf() {
		return self;
	}
	public void setSelf(User self) {
		this.self = self;
	}
	public User getFollowinger() {
		return followinger;
	}
	public void setFollowinger(User followinger) {
		this.followinger = followinger;
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
