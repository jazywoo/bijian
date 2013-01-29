package bijian.model.dao;

import java.util.Date;
import java.util.List;

import bijian.model.bean.relationbean.LabelUser;

public interface ILabelUserDao extends IBaseDao{
	 public int getLabelUsersSizeByUser(long userID);
     public List<LabelUser> getLabelUsersByUser(long userID,int page,int limit);   
     public int getLabelUsersSizeByLabel(long labelID);
     public List<LabelUser> getLabelUsersByLabel(long labelID,int page,int limit);  
     public int getLabelUsersSizeByLabel(long labelID,Date date1,Date date2);
     public List<LabelUser> getLabelUsersByLabel(long labelID,Date date1,Date date2,int page,int limit);  
}
