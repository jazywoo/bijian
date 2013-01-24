package bijian.model.bean.relationbean;

import bijian.model.bean.User;

public class UserSystemInfo {
	private long userSystemInfoID;
    private User user;
    private Integer loginState;
	public long getUserSystemInfoID() {
		return userSystemInfoID;
	}
	public void setUserSystemInfoID(long userSystemInfoID) {
		this.userSystemInfoID = userSystemInfoID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getLoginState() {
		return loginState;
	}
	public void setLoginState(Integer loginState) {
		this.loginState = loginState;
	}
	
    
}
