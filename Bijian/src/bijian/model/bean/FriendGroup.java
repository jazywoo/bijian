package bijian.model.bean;

import java.util.Date;

public class FriendGroup {
    private long friendGroupID;
    private User author;
    private String groupName;
    private Date createTime;
	public long getFriendGroupID() {
		return friendGroupID;
	}
	public void setFriendGroupID(long friendGroupID) {
		this.friendGroupID = friendGroupID;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
    
}
