package bijian.model.bean.relationbean;

import java.util.Date;

import bijian.model.bean.Label;
import bijian.model.bean.User;

public class LabelUser {
    private long labelUserID;
    private User user;
    private Label label;
    private Date createTime;
	public long getLabelUserID() {
		return labelUserID;
	}
	public void setLabelUserID(long labelUserID) {
		this.labelUserID = labelUserID;
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
    
}
