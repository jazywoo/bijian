package bijian.model.service;

import java.util.Date;
import java.util.List;

import bijian.model.bean.Label;

public interface ILabelService {
    public void addLabel(long userID,Label label);
    public List<Label> getHotLabels(int page,int limit);
    public int getUsedCount(long labelID,Date date);//得到一天内，该标签使用次数
}
