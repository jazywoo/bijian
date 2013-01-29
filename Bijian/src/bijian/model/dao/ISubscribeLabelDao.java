package bijian.model.dao;

import java.util.List;

import bijian.model.bean.Label;
import bijian.model.bean.relationbean.SubscribeLabel;

public interface ISubscribeLabelDao extends IBaseDao{
    public int getSubscribeLabelsSizeByUser(long userID);
    public List<SubscribeLabel> getSubscribeLabelsByUser(long userID,int page,int limit);
    public int getSubscribeLabelsSizeByLabel(long labelID);
    public List<SubscribeLabel> getSubscribeLabelsByLabel(long labelID,int page,int limit);
}
