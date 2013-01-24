package bijian.model.bean.relationbean;

import java.util.Date;

import bijian.model.bean.Label;
import bijian.model.bean.User;

public class SubscribeLabel {
   private long subscribeLabelID;
   private User user;
   private Label label;
   private Date createTime;
   private int isValid;
	public long getSubscribeLabelID() {
		return subscribeLabelID;
	}
	public void setSubscribeLabelID(long subscribeLabelID) {
		this.subscribeLabelID = subscribeLabelID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
}
