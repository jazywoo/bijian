package bijian.model.bean;

import java.util.Date;

public class Comment {
    private int commentID;
    private String content;
    private Date createTime;
    private User author;
    private Sentence sentence;
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public int getCommentID() {
		return commentID;
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
	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}
	public Sentence getSentence() {
		return sentence;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public User getAuthor() {
		return author;
	}
}
