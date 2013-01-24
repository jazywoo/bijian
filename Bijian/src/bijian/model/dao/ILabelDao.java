package bijian.model.dao;

import java.util.List;

import bijian.model.bean.Label;

public interface ILabelDao extends IBaseDao {
	   public List<Label> get(long userID,int page,int limit);
}