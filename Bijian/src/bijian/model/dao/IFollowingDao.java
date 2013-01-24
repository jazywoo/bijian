package bijian.model.dao;

import java.util.List;

import bijian.model.bean.User;
import bijian.model.bean.relationbean.Following;

public interface IFollowingDao extends IBaseDao {
	 public void delete(long selfID,long followingerID);
	 public Following get(long selfID,long followingerID);
	 public int getFollowingListSize(final long userID);
	 public List<Following> get(long userID,int page,int limit);
}
