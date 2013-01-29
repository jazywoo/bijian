package bijian.model.dao;

import java.util.List;

import bijian.model.bean.Label;

public interface ILabelDao extends IBaseDao {
	   public Label getByContent(String content);//返回内容相同的标签
	   public List<Label> getLike(String content,int page,int limit);
	   public List<Label> getHotLabels(int page,int limit);
}