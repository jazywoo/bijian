package bijian.model.service;

import java.util.List;

import bijian.model.bean.Label;
import bijian.model.bean.Sentence;

public interface ISentenceService {

	//对已登录的用户--------
    public List<Sentence> getMySentences(long userID,int page,int limit);
    public void addSentence(long userID,Sentence sentence,List<Label> labels);
    public void updateSentence(long sentenceID,Sentence sentence);
    public void deleteSentence(long sentenceID);
    public void forwardingSentence(long userID,long sentenceID);//转发    
    //得到关注的人动态句子
    public List<Sentence> getAttentionSentences(long userID,int page,int limit);
     //得到系统根据我的喜好推荐的动态句子
    public List<Sentence> getSuggestSentences(long userID,int page,int limit);
     //得到与我相关，评论过的动态句子
    public List<Sentence> getRelatedMeSentences(long userID,int page,int limit);
    //得收藏标签的句子
    public List<Sentence> getLabelSentences(long labelID,int page,int limit);
    
    //公共的操作---------
    public List<Sentence> getLatestSentence(int page,int limit);//最新的
    public List<Sentence> getHotSentence(int page,int limit);//最热的
    public void reportSentence(long userID,long sentenceID);//举报
    public void thinkGood(long sentenceID);//赞
    public List<Sentence> searchSentence(String keyword,int page,int limit);//搜索
}
