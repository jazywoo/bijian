package bijian.model.dao;

import java.util.List;
import bijian.model.bean.Chat;

public interface IChatDao extends IBaseDao {
	public List<Chat> getChatList(long userID,long chatUserID,int page,int limit);
	public int getChatListSize(long userID,long chatUserID);
}
