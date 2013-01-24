package bijian.model.bean;

import java.util.Date;


public class Sentence{
    private long sentenceID;
    private User author;
    private String content;
    private String fromPlace;
    private Date createTime;
    private Integer goodNum;
    private Integer commentNum;
    private Integer forwardingNum;
    private Integer isValid;

	public long getSentenceID() {
		return sentenceID;
	}

	public void setSentenceID(long sentenceID) {
		this.sentenceID = sentenceID;
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

	public Integer getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getForwardingNum() {
		return forwardingNum;
	}

	public void setForwardingNum(Integer forwardingNum) {
		this.forwardingNum = forwardingNum;
	}


	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	
    
}
