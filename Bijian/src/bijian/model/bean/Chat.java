package bijian.model.bean;

import java.util.Date;

public class Chat {
    private long chatID;
    private User fromUser;
    private User toUser;
    private String content;
    private Date createTime;
    private Integer isValid;
    
	public long getChatID() {
		return chatID;
	}
	public void setChatID(long chatID) {
		this.chatID = chatID;
	}
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
