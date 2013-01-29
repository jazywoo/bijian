package bijian.controller.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import bijian.model.bean.*;
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
    //显示首页
    public String displayHomePage(){   
    	int page=0;
    	int limit=10;
        List<User> hotUserList=userService.getHotUsers();
        List<Sentence> sentenceList=sentenceService.getHotSentence(page, limit);
        List<Label> hotLabelList=labelService.getHotLabels();
        
    	session.put("hotUserList", hotUserList);
    	session.put("sentenceList", sentenceList);
    	session.put("hotLabelList", hotLabelList);
    	return SUCCESS;
    }
    //显示其他人的主页
    public String displayOtherPage(){
    	int userID=(Integer)request.get("userID");
    	int page=(Integer)request.get("page");
    	int limit=(Integer)request.get("limit");
    	User user=userService.getUser(userID);
    	List<User> attentionUserList=userService.getAttentionUsers(userID, page, limit);
    	List<User> followingUserList=userService.getFollowingUsers(userID, page, limit);
    	List<User> visitUserList=userService.getVisitUsers(userID, page, limit);
        List<Sentence> sentenceList=userService.getMySentences(userID, page, limit);
        List<Label> labelList=userService.getLabels(userID, page, limit);
        session.put("showUser",user);
        session.put("attentionUserList", attentionUserList);
        session.put("followingUserList", followingUserList);
        session.put("visitUserList", visitUserList);
        session.put("sentenceList", sentenceList);
        session.put("labelList", labelList);
        return SUCCESS;
    }
    //显示自己的主页
    public String displayOwnPage(){
    	User loginUser=(User) session.get("loginUser");
    	int userID=loginUser.getUserID();
    	int page=(Integer)request.get("page");
    	int limit=(Integer)request.get("limit");
    	List<User> attentionUserList=userService.getAttentionUsers(userID, page, limit);
    	List<User> followingUserList=userService.getFollowingUsers(userID, page, limit);
    	List<User> visitUserList=userService.getVisitUsers(userID, page, limit);
        List<Sentence> SuggestSentenceList=userService.getSuggestSentences(userID, page, limit);
        List<Label> labelList=userService.getLabels(userID, page, limit);
        session.put("attentionUserList", attentionUserList);
        session.put("followingUserList", followingUserList);
        session.put("visitUserList", visitUserList);
        session.put("SuggestSentenceList", SuggestSentenceList);
        session.put("labelList", labelList);
        return SUCCESS;
    }
    //搜索输入框的ajax提示
    public String searchAjax(){
    	String keyword=(String) request.get("keyword");
    	int page=0;
    	int limit=4;
    	
    	Map jsonMap=new HashMap();
    	User user=new User();
    	user.setUsername(keyword);
    	jsonMap.put("userList",userService.searchUser(user, page, limit));
    	Sentence sentence=new Sentence();
    	sentence.setContent(keyword);
    	jsonMap.put("sentenceList", userService.searchSentence(sentence, page, limit));
    	Label label=new Label();
    	label.setContent(keyword);
    	jsonMap.put("labelList", userService.searchLabel(label, page, limit));
    	Mood mood=new Mood();
    	mood.setContent(keyword);
    	jsonMap.put("moodList", userService.searchMood(mood, page, limit));
    	
    	JSONObject jsonObject=JSONObject.fromObject(jsonMap);
    	resultJson=jsonObject.toString();
    	return SUCCESS;    	
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
    
}
