package bijian.model.dao;

import java.util.List;

import bijian.model.bean.relationbean.Attention;

public interface IAttentionDao extends IBaseDao{
	public void delete(long selfID,long attentionerID);
	public Attention get(long selfID,long attentionerID);
	public int getAttentionListSize(long userID);//总共多少条
    public List<Attention> get(long userID,int page,int limit);
}
