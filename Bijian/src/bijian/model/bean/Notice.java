package bijian.model.bean;

import java.util.Date;

public class Notice {
    private long noticeID;
    private User user;
    private String title;
    private String content;
    private Date createTime;
    private Integer readed;
    private Integer isValid;
	public long getNoticeID() {
		return noticeID;
	}
	public void setNoticeID(long noticeID) {
		this.noticeID = noticeID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Integer getReaded() {
		return readed;
	}
	public void setReaded(Integer readed) {
		this.readed = readed;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
    
}
