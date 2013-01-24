package bijian.model.dao;

import java.util.List;

import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.FriendTable;

public interface IFriendTableDao extends IBaseDao {
    public void delete(long selfID,long friendID);
	public FriendTable get(long selfID,long friendID);
	public int getFriendListSize(long userID);//总共多少条
    public List<FriendTable> get(long userID,int page,int limit);
}
