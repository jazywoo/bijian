package bijian.model.bean;

import java.util.Date;

public class Message {
    private int messageID;
    private String content;
    private Date createTime;
    private User from;
    private User to;
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	public int getMessageID() {
		return messageID;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getFrom() {
		return from;
	}
	public void setTo(User to) {
		this.to = to;
	}
	public User getTo() {
		return to;
	}
}
