package bijian.model.bean.relationbean;

import java.util.Date;

import bijian.model.bean.FriendGroup;
import bijian.model.bean.User;

public class FriendTable {
    private long friendTableID;
    private User self;
    private FriendGroup group;
    private User friend;
    private Date createTime;
    private Integer isValid;
	public long getFriendTableID() {
		return friendTableID;
	}
	public void setFriendTableID(long friendTableID) {
		this.friendTableID = friendTableID;
	}
	public User getSelf() {
		return self;
	}
	public void setSelf(User self) {
		this.self = self;
	}
	public FriendGroup getGroup() {
		return group;
	}
	public void setGroup(FriendGroup group) {
		this.group = group;
	}
	public User getFriend() {
		return friend;
	}
	public void setFriend(User friend) {
		this.friend = friend;
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
