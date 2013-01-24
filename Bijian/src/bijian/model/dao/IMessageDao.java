package bijian.model.dao;

import java.util.List;

import bijian.model.bean.Message;

public interface IMessageDao extends IBaseDao {
	public List<Message> getMessageList(long fromUserID,long toUserID,int page,int limit);
	public int getMessageListSize(long fromUserID,long toUserID);
}
