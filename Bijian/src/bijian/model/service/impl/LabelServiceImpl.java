package bijian.model.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bijian.model.bean.Label;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.LabelUser;
import bijian.model.dao.ILabelDao;
import bijian.model.dao.ILabelUserDao;
import bijian.model.dao.IUserDao;
import bijian.model.service.ILabelService;

public class LabelServiceImpl implements ILabelService{
    private IUserDao userDao;
    private ILabelDao labelDao;
    private ILabelUserDao labelUserDao;
	
	public void addLabel(long userID, Label label) {
		User user=(User) userDao.get(userID);
		Label existLabel=labelDao.getByContent(label.getContent());
		LabelUser labelUser=new LabelUser();
		labelUser.setUser(user);
		labelUser.setCreateTime(new Date());
		if(existLabel==null){//标签不存在
			label.setCreateTime(new Date());
			labelDao.insert(label);
			labelUser.setLabel(label);
		}else{
			labelUser.setLabel(existLabel);
		}
		labelUserDao.insert(labelUser);
	}
	public List<Label> getHotLabels(int page,int limit) {
		return labelDao.getHotLabels(page, limit);
	}
	public int getUsedCount(long labelID,Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date2=calendar.getTime();
		return labelUserDao.getLabelUsersSizeByLabel(labelID, date, date2);
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

	public ILabelUserDao getLabelUserDao() {
		return labelUserDao;
	}

	public void setLabelUserDao(ILabelUserDao labelUserDao) {
		this.labelUserDao = labelUserDao;
	}

	

}