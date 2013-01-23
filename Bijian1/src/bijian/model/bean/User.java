package bijian.model.bean;

import java.util.ArrayList;

public class User {
   private String username;
   private String nickname;
   private String password;
   private int sex;
   private int age;
   private String province;
   private String area;
   private String constellation;
   private String photo;
   private int loginState;
   private int attentionNum;
   private int followingNum;
   private ArrayList<Sentence> sentenceList;
   private ArrayList<Label> labelList;
   private ArrayList<Mood> moodList;
   private ArrayList<User> attentionList;
   private ArrayList<User> followingList;
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getSex() {
		return sex;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvince() {
		return province;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getArea() {
		return area;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPhoto() {
		return photo;
	}
	public void setLoginState(int loginState) {
		this.loginState = loginState;
	}
	public int getLoginState() {
		return loginState;
	}
	public void setAttentionNum(int attentionNum) {
		this.attentionNum = attentionNum;
	}
	public int getAttentionNum() {
		return attentionNum;
	}
	public void setFollowingNum(int followingNum) {
		this.followingNum = followingNum;
	}
	public int getFollowingNum() {
		return followingNum;
	}
	public void setLabelList(ArrayList<Label> labelList) {
		this.labelList = labelList;
	}
	public ArrayList<Label> getLabelList() {
		return labelList;
	}
	public void setMoodList(ArrayList<Mood> moodList) {
		this.moodList = moodList;
	}
	public ArrayList<Mood> getMoodList() {
		return moodList;
	}
	public void setSentenceList(ArrayList<Sentence> sentenceList) {
		this.sentenceList = sentenceList;
	}
	public ArrayList<Sentence> getSentenceList() {
		return sentenceList;
	}
	public void setAttentionList(ArrayList<User> attentionList) {
		this.attentionList = attentionList;
	}
	public ArrayList<User> getAttentionList() {
		return attentionList;
	}
	public void setFollowingList(ArrayList<User> followingList) {
		this.followingList = followingList;
	}
	public ArrayList<User> getFollowingList() {
		return followingList;
	}
   
   
}
