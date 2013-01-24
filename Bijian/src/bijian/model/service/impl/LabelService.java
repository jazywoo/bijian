package bijian.model.service.impl;

import java.util.Date;

import bijian.model.bean.Label;
import bijian.model.bean.User;
import bijian.model.dao.ILabelDao;
import bijian.model.dao.IUserDao;
import bijian.model.service.ILabelService;

public class LabelService implements ILabelService{
    private IUserDao userDao;
    private ILabelDao labelDao;
	
	public void addLabel(long userID, Label label) {
		User user=(User) userDao.get(userID);
		label.setCreateTime(new Date());
		label.setAuthor(user);
		labelDao.insert(label);
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public ILabelDao getLabelDao() {
		return labelDao;
	}

	public void setLabelDao(ILabelDao labelDao) {
		this.labelDao = labelDao;
	}

}
