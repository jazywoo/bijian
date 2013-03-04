package bijian.controller.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import bijian.model.bean.*;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.Following;
import bijian.model.bean.relationbean.LoveSentence;
import bijian.model.bean.relationbean.SubscribeLabel;
import bijian.model.service.ILabelService;
import bijian.model.service.ISentenceService;
import bijian.model.service.IUserService;

import com.opensymphony.xwork2.ActionSupport;

public class PageAction extends ActionSupport implements SessionAware,RequestAware{
	private Map request;
	private Map session;
	
    private IUserService userService;
    private ISentenceService sentenceService;
    private ILabelService labelService;
    
    private Map resultMap=new HashMap();//向jsp传递的值
    
    //显示首页
    public String displayHomepage(){   
        List<User> hotUserList=userService.getHotUsers(0,5);
        List<Sentence> sentenceList=sentenceService.getHotSentence(0, 10);
        List<Label> hotLabelList=labelService.getHotLabels(0,5);
        
        resultMap.put("homepage_hotUserList", hotUserList);
        resultMap.put("homepage_sentenceList", sentenceList);
        resultMap.put("homepage_hotLabelList", hotLabelList);
    	return SUCCESS;
    }
    //显示登陆用户首页1，推荐的句子
    public String displayUserHomepage(){
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	System.out.println(loginUser.getUsername());
    	List<Sentence> sentences=sentenceService.getSuggestSentences(userID, 0, 10);//推荐句子
    	resultMap.put("userHomePage_type", "suggest");
        resultMap.put("userHomePage_sentences", sentences);  //显示推荐句子
        getUserHomepageMenu();//加上首页右侧目录数据
        getDiscoveryPageData();//加上发现页面中用户的其他数据
    	return SUCCESS;
    }
    //显示登陆用户首页2，用户喜欢过的的句子
    public String displayUserLoveSentence(){
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	List<Sentence> sentences=sentenceService.getLovedSentences(userID, 0, 10);//喜欢的句子
    	resultMap.put("userHomePage_type", "loveSentence");
    	resultMap.put("userHomePage_sentences", sentences);  //显示喜欢的句子
    	 getUserHomepageMenu();//加上首页右侧目录数据
         getDiscoveryPageData();//加上发现页面中用户的其他数据
    	return SUCCESS;
    }
    //显示登陆用户首页3，收藏的所有标签的所有句子
    public String displayUserAllLabelSentence(){
    	long labelID=Long.parseLong(request.get("labelID").toString());
    	User loginUser=(User) session.get("loginUser");
    	
    	long userID=loginUser.getUserID();
    	List<Sentence> sentences=sentenceService.getAllLabelSentences(userID, 0, 10);//标签句子
    	resultMap.put("userHomePage_type", "allLabelSentence");
    	resultMap.put("userHomePage_sentences", sentences);  //显示标签句子
    	getUserHomepageMenu();//加上首页右侧目录数据
        getDiscoveryPageData();//加上发现页面中用户的其他数据
    	return SUCCESS;
    }
    //显示登陆用户首页4，收藏的某一个标签的所有句子
    public String displayUserOneLabelSentence(){
    	long labelID=Long.parseLong(request.get("labelID").toString());
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	List<Sentence> sentences=sentenceService.getOneLabelSentences(labelID, 0, 10);//标签句子
    	resultMap.put("userHomePage_type", "oneLabelSentence");
    	resultMap.put("userHomePage_sentences", sentences);  //显示标签句子
    	getUserHomepageMenu();//加上首页右侧目录数据
        getDiscoveryPageData();//加上发现页面中用户的其他数据
    	return SUCCESS;
    }
    //显示登陆用户首页5，用户关注的博客的句子
    public String displayUserAllAttentionSentence(){
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	List<Sentence> sentences=sentenceService.getAllAttentionSentences(userID, 0, 10);//关注所有的博客的句子
    	resultMap.put("userHomePage_type", "allAttention");
    	resultMap.put("userHomePage_sentences", sentences);  //显示关注的博客的句子
    	getUserHomepageMenu();//加上首页右侧目录数据
        getDiscoveryPageData();//加上发现页面中用户的其他数据
    	return SUCCESS;
    }
    //显示登陆用户首页6，用户关注的博客的句子
    public String displayUserOneAttentionSentence(){
    	long attentionerID=Long.parseLong(request.get("attentionerID").toString());
    	List<Sentence> sentences=sentenceService.getOneAttentionSentences(attentionerID, 0, 10);//关注的博客的句子
    	resultMap.put("userHomePage_type", "oneAttention");
    	resultMap.put("userHomePage_sentences", sentences);  //显示关注的博客的句子
    	getUserHomepageMenu();//加上首页右侧目录数据
        getDiscoveryPageData();//加上发现页面中用户的其他数据
    	return SUCCESS;
    }
    
  
    //显示发现页面
    public String displayDiscoveryPage(){
    	List<Sentence> sentenceList=sentenceService.getHotSentence(0, 10); //发现 好句子
    	List<Label> hotLabelList=labelService.getHotLabels(0,20); // 发现 热的标签
    	List<User> labelHotUserList=new ArrayList<User>(); //发现 热的标签对应的优秀博客
    	List<List<User>> labelActiveUserList=new ArrayList<List<User>>();//发现 热的标签对应的几个活跃博客
    	List<List<Integer>> labelCountList=new ArrayList<List<Integer>>();//热的标签对应一周内使用次数
    	for(Label l:hotLabelList){
    		labelHotUserList.add(userService.getHotUserByLabel(l.getLabelID()));
    		labelActiveUserList.add(userService.getActiveUsersByLabel(l.getLabelID(), 0, 5));
    		List<Integer> countList=new ArrayList<Integer>();//近7天的使用次数
    		Calendar calendar=Calendar.getInstance();
    		calendar.setTime(new Date());
    		countList.add(labelService.getUsedCount(l.getLabelID(), calendar.getTime()));
    		calendar.add(Calendar.DAY_OF_YEAR, -1);
    		countList.add(labelService.getUsedCount(l.getLabelID(), calendar.getTime()));
    		calendar.add(Calendar.DAY_OF_YEAR, -1);
    		countList.add(labelService.getUsedCount(l.getLabelID(), calendar.getTime()));
    		calendar.add(Calendar.DAY_OF_YEAR, -1);
    		countList.add(labelService.getUsedCount(l.getLabelID(), calendar.getTime()));
    		calendar.add(Calendar.DAY_OF_YEAR, -1);
    		countList.add(labelService.getUsedCount(l.getLabelID(), calendar.getTime()));
    		calendar.add(Calendar.DAY_OF_YEAR, -1);
    		countList.add(labelService.getUsedCount(l.getLabelID(), calendar.getTime()));
    		calendar.add(Calendar.DAY_OF_YEAR, -1);
    		countList.add(labelService.getUsedCount(l.getLabelID(), calendar.getTime()));
    		labelCountList.add(countList);
    	}
    	resultMap.put("discoveryPage_sentenceList", sentenceList);
    	resultMap.put("discoveryPage_hotLabelList", hotLabelList);
    	resultMap.put("discoveryPage_labelHotUserList", labelHotUserList);
    	resultMap.put("discoveryPage_labelActiveUserList", labelActiveUserList);
    	resultMap.put("discoveryPage_labelCountList", labelCountList);
    	return SUCCESS;
    }
    
    //显示个人管理页面
    //显示个人设置
    public String displayOwnSetting(){
    	
    	return SUCCESS;
    }
    //显示我的句子
    public String displayOwnSentence(){
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	List<Sentence> sentences=sentenceService.getMySentences(userID, 0, 10);
    	resultMap.put("ownPage_type", "ownSentence");
    	resultMap.put("ownPage_ownSentenceList", sentences);
    	getOwnPageMenu();//加载个人博客数据
    	getDiscoveryPageData();//加上发现页面中用户的其他数据
    	return SUCCESS;
    }
    //显示我的粉丝
    public String displayOwnFollowing(){
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	List<Following> followings=userService.getFollowings(userID, 0, 10);
    	resultMap.put("ownPage_type", "ownFollowing");
    	resultMap.put("ownPage_ownFollowingList", followings);
    	getOwnPageMenu();//加载个人博客数据
    	getDiscoveryPageData();//加上发现页面中用户的其他数据
    	return SUCCESS;
    }
    //显示我的关注
    public String displayOwnAttention(){
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	List<Attention> attentions=userService.getAttentions(userID, 0, 10);
    	resultMap.put("ownPage_type", "ownAttention");
    	resultMap.put("ownPage_ownAttentionList", attentions);
    	getOwnPageMenu();//加载个人博客数据
    	getDiscoveryPageData();//加上发现页面中用户的其他数据
    	return SUCCESS;
    }
    //显示我搜藏标签
    public String displayOwnSubscribeLabel(){
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	List<SubscribeLabel> subscribeLabels=labelService.getSubscribeLabels(userID, 0, 10);
    	resultMap.put("ownPage_type", "subscribeLabel");
    	resultMap.put("ownPage_subscribeLabelList", subscribeLabels);
    	getOwnPageMenu();//加载个人博客数据
    	getDiscoveryPageData();//加上发现页面中用户的其他数据
    	return SUCCESS;
    }
    //显示我喜欢的句子
    public String displayOwnLoveSentence(){
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	List<LoveSentence> loveSentences=sentenceService.getLoveSentences(userID, 0, 10);
    	resultMap.put("ownPage_type", "loveSentence");
    	resultMap.put("ownPage_loveSentenceList", loveSentences);
    	getOwnPageMenu();//加载个人博客数据
    	getDiscoveryPageData();//加上发现页面中用户的其他数据
    	return SUCCESS;
    }
    //显示我的消息
    public String displayOwnNotice(){
    	
    	return SUCCESS;
    }
    //显示我的私信
    public String displayOwnMessage(){
    	
    	return SUCCESS;
    }
    
    
    //显示博客的主页
    public String displayOnePage(){
    	long objectUserID=Long.parseLong(request.get("objectUserID").toString());
    	List<Sentence> sentences=sentenceService.getMySentences(objectUserID, 0, 10);
    	resultMap.put("objectUserID", objectUserID);
    	resultMap.put("sentences", sentences);
        return SUCCESS;
    }
    private void getUserHomepageMenu(){
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	int loveSentenceSize=sentenceService.getLoveSentencesSize(userID);//喜欢的句子数
    	int subscribeLabelSize=labelService.getSubscribeLabelsSize(userID);
    	List<Label> subscribeLabels=labelService.getSubscribedLabels(userID, 0, 5);//收藏的标签  
    	List<User> attentionUsers=userService.getAttentionUsers(userID, 0, 5);
    	resultMap.put("userHomePage_loveSentenceSize", loveSentenceSize);
        resultMap.put("userHomePage_subscribeLabelSize", subscribeLabels);
        resultMap.put("userHomePage_subscribeLabels", subscribeLabels);
        resultMap.put("userHomePage_attentionSize", loginUser.getAttentionNum());
        resultMap.put("userHomePage_attentionUsers", attentionUsers);
    }
    private void getDiscoveryPageData(){
    	List<User> hotUserList=userService.getHotUsers(0,5);//优秀博客
        List<Label> hotLabelList=labelService.getHotLabels(0,5);//热门标签
        resultMap.put("discoveryPage_hotUserList", hotUserList);
        resultMap.put("discoveryPage_hotLabelList", hotLabelList);
    }
    private void getOwnPageMenu(){
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	int ownSentenceSize=loginUser.getSentenceNum();//个人句子数
    	int followingSize=loginUser.getFollowingNum();//粉丝个数
    	int attentionSize=loginUser.getAttentionNum();//关注个数
    	int subscribeLabelsSize=labelService.getSubscribeLabelsSize(userID);//收藏的标签数
    	int loveSentenceSize=sentenceService.getLoveSentencesSize(userID);//喜欢的句子数
    	int messageSize = 0;
    	int noticeSize = 0;
    	List<Sentence> sentences=sentenceService.getMySentences(userID, 0, 10);
        resultMap.put("OwnPage_ownSentenceSize", ownSentenceSize);
        resultMap.put("OwnPage_followingSize", followingSize);
        resultMap.put("OwnPage_attentionSize", attentionSize);
        resultMap.put("OwnPage_subscribeLabelsSize", subscribeLabelsSize);
        resultMap.put("OwnPage_loveSentenceSize", loveSentenceSize);
        resultMap.put("OwnPage_messageSize", messageSize);
        resultMap.put("OwnPage_noticeSize", noticeSize);
        resultMap.put("OwnPage_sentences", sentences);
    }


	public void setRequest(Map request) {
	    this.request=request;
	}
	public void setSession(Map session) {
		this.session=session;
	}
	public Map getRequest() {
		return request;
	}
	public Map getSession() {
		return session;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IUserService getUserService() {
		return userService;
	}
	public ISentenceService getSentenceService() {
		return sentenceService;
	}
	public void setSentenceService(ISentenceService sentenceService) {
		this.sentenceService = sentenceService;
	}
	public ILabelService getLabelService() {
		return labelService;
	}
	public void setLabelService(ILabelService labelService) {
		this.labelService = labelService;
	}
	public Map getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map resultMap) {
		this.resultMap = resultMap;
	}
    
}
