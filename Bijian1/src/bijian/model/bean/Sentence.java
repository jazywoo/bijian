package bijian.model.bean;

import java.util.ArrayList;
import java.util.Date;

public class Sentence {
    private int sentenceID;
    private String content;
    private Date createTime;
    private User author;
    private int goodNum;
    private int commentNum;
    private int forwardingNum;
    private Mood mood;
    private ArrayList<Comment> commentList;
	public void setSentenceID(int sentenceID) {
		this.sentenceID = sentenceID;
	}
	public int getSentenceID() {
		return sentenceID;
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
	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}
	public int getGoodNum() {
		return goodNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setForwardingNum(int forwardingNum) {
		this.forwardingNum = forwardingNum;
	}
	public int getForwardingNum() {
		return forwardingNum;
	}
	public void setMood(Mood mood) {
		this.mood = mood;
	}
	public Mood getMood() {
		return mood;
	}
	public void setCommentList(ArrayList<Comment> commentList) {
		this.commentList = commentList;
	}
	public ArrayList<Comment> getCommentList() {
		return commentList;
	}
    
}
