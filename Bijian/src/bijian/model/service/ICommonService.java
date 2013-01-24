package bijian.model.service;

import java.util.List;

import bijian.model.bean.Label;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;

public interface ICommonService {
	
	//系统首页的推荐，给出近期最火最活跃的信息展示
    public List<Sentence> getLatestSentences(int page,int limit);
    public List<Label> getHotLabels(int page,int limit);
    public List<User> getActiveUser(int page,int limit);
    
    //系统首页的推荐，根据大众普遍的心情走势给出的信息展示
    public List<Sentence> getSuggestSentences(int page,int limit);
    public List<Label> getSuggestLabels(int page,int limit);
    public List<User> getSuggestPeople(int page,int limit);
}
