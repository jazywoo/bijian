package bijian.model.service;

import java.util.List;

import bijian.model.bean.Comment;
import bijian.model.bean.Label;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.Following;

public interface IUserService {
    public User login(String username,String password);
    public void register(User user);//对个人信息修改
    public void update(User user);
    public void attentionOne(long myID,long otherID);//社交、关注某人
    public void cancelAttention(long myID,long otherID);//取消关注某人
    
    public User getUser(long userID);//得到某个人的信息
    public int getAttentionUsersSize(long userID);
    public List<User> getAttentionUsers(long userID,int page,int limit);//得到关注的人
    public List<Attention> getAttentions(long userID,int page,int limit);
    public int getFollowingUsersSize(long userID);
    public List<User> getFollowingUsers(long userID,int page,int limit);//得到关注我的人
    public List<Following> getFollowings(long userID,int page,int limit);
    
    public List<User> getVisitUsers(long userID,int page,int limit);//得到最近访问的人
    
    public User getHotUserByLabel(long labelID);
    public List<User> getActiveUsersByLabel(long labelID,int page,int limit);
    
    public List<User> searchUser(User user,int page,int limit);//搜索
    public List<User> getHotUsers();//热门博客
    public List<User> getSentenceForwardingUsers(long sentenceID,int page,int limit);//转发该句子的所有用户
    public List<User> getSentenceLoveUsers(long sentenceID,int page,int limit);//喜欢该句子的所有用户
    
    
}
