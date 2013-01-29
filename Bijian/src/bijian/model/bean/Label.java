package bijian.model.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Label {
   private long labelID;
   private String content;
   private Date createTime;
   private Integer hotValue;
   
   private Set labelSentences=new HashSet();
   private Set labelUsers=new HashSet();
   private Set subscribeLabels=new HashSet();
   
	public long getLabelID() {
		return labelID;
	}
	public void setLabelID(long labelID) {
		this.labelID = labelID;
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
	public Set getLabelSentences() {
		return labelSentences;
	}
	public void setLabelSentences(Set labelSentences) {
		this.labelSentences = labelSentences;
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
		

}
