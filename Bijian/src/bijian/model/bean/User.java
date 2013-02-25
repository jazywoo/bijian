package bijian.model.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class User{
   private long userID;
   private String username;
   private String nickname;
   private String password;
   private Integer sex;
   private Integer age;
   private String photo;
   
   private Date createTime; 
   private Integer loginState;
   private Integer hotValue;
   private Integer attentionNum;
   private Integer followingNum;
   private Integer sentenceNum;
   private Integer visitNum;
   
   private Set attentions=new HashSet();
   private Set followings=new HashSet();
   private Set friends=new HashSet();
   
   private Set chats=new HashSet();
   private Set notices=new HashSet();
   private Set messages=new HashSet();
   
   private Set sentences=new HashSet();
   private Set comments=new HashSet();
   
   private Set reportSentences=new HashSet();
   private Set relatedSentences=new HashSet();
   private Set loveSentences=new HashSet();
   
   private Set labelUsers=new HashSet();
   private Set subscribeLabels=new HashSet();
   
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getLoginState() {
		return loginState;
	}
	public void setLoginState(Integer loginState) {
		this.loginState = loginState;
	}
	public Integer getAttentionNum() {
		return attentionNum;
	}
	public void setAttentionNum(Integer attentionNum) {
		this.attentionNum = attentionNum;
	}
	public Integer getFollowingNum() {
		return followingNum;
	}
	public void setFollowingNum(Integer followingNum) {
		this.followingNum = followingNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Set getAttentions() {
		return attentions;
	}
	public void setAttentions(Set attentions) {
		this.attentions = attentions;
	}
	public Set getFollowings() {
		return followings;
	}
	public void setFollowings(Set followings) {
		this.followings = followings;
	}
	public Set getFriends() {
		return friends;
	}
	public void setFriends(Set friends) {
		this.friends = friends;
	}
	public Set getChats() {
		return chats;
	}
	public void setChats(Set chats) {
		this.chats = chats;
	}
	public Set getNotices() {
		return notices;
	}
	public void setNotices(Set notices) {
		this.notices = notices;
	}
	public Set getMessages() {
		return messages;
	}
	public void setMessages(Set messages) {
		this.messages = messages;
	}
	public Set getSentences() {
		return sentences;
	}
	public void setSentences(Set sentences) {
		this.sentences = sentences;
	}
	public Set getComments() {
		return comments;
	}
	public void setComments(Set comments) {
		this.comments = comments;
	}
	public Set getReportSentences() {
		return reportSentences;
	}
	public void setReportSentences(Set reportSentences) {
		this.reportSentences = reportSentences;
	}
	public Set getRelatedSentences() {
		return relatedSentences;
	}
	public void setRelatedSentences(Set relatedSentences) {
		this.relatedSentences = relatedSentences;
	}
	public Set getLoveSentences() {
		return loveSentences;
	}
	public void setLoveSentences(Set loveSentences) {
		this.loveSentences = loveSentences;
	}
	public Set getLabelUsers() {
		return labelUsers;
	}
	public void setLabelUsers(Set labelUsers) {
		this.labelUsers = labelUsers;
	}
	public Set getSubscribeLabels() {
		return subscribeLabels;
	}
	public void setSubscribeLabels(Set subscribeLabels) {
		this.subscribeLabels = subscribeLabels;
	}
	public Integer getHotValue() {
		return hotValue;
	}
	public void setHotValue(Integer hotValue) {
		this.hotValue = hotValue;
	}
	public Integer getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}
	public Integer getSentenceNum() {
		return sentenceNum;
	}
	public void setSentenceNum(Integer sentenceNum) {
		this.sentenceNum = sentenceNum;
	}
   
}
