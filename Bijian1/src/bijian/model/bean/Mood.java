package bijian.model.bean;

import java.util.Date;

public class Mood {
   private int moodID;
   private String content;
   private Date createTime;
   private User author;
   private User origin;
public void setMoodID(int moodID) {
	this.moodID = moodID;
}
public int getMoodID() {
	return moodID;
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
public void setAuthor(User author) {
	this.author = author;
}
public User getAuthor() {
	return author;
}
public void setOrigin(User origin) {
	this.origin = origin;
}
public User getOrigin() {
	return origin;
}

   
}
