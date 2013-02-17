package bijian.model.service;

import java.util.Date;
import java.util.List;

import bijian.model.bean.Label;
import bijian.model.bean.relationbean.SubscribeLabel;

public interface ILabelService {
	
    public void addLabel(long userID,Label label);//新建标签
    public void subscribeLabel(long userID,long labelID);//收藏标签
    public void cancelSubscribeLabel(long subscribeLabelID);//取消收藏标签
    public int getSubscribeLabelsSize(long userID);
    public List<Label> getSubscribedLabels(long userID,int page,int limit);//得到收藏的标签
    public List<SubscribeLabel> getSubscribeLabels(long userID,int page,int limit);
    
    public List<Label> getHotLabels(int page,int limit);
    public int getUsedCount(long labelID,Date date);//得到一天内，该标签使用次数
}
