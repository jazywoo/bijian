package bijian.model.dao;

import java.util.List;

import bijian.model.bean.Notice;

public interface INoticeDao extends IBaseDao {
	public int getNoticeListSize(long userID);
	public List<Notice> getNoticeList(long userID,int page,int limit);
	
}
