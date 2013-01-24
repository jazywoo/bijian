package bijian.controller.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import bijian.model.bean.*;
import bijian.model.service.ICommonService;
import bijian.model.service.IUserService;

import com.opensymphony.xwork2.ActionSupport;

public class PageAction extends ActionSupport implements SessionAware,RequestAware{
	private Map request;
	private Map session;
    private ICommonService commonService;
    private IUserService userService;
    
    private String resultJson;
    
//    //显示首页
//    public String displayHomePage(){   
//    	String kind=(String) request.get("pageKind");
//    	int page=0;
//    	int limit=10;
//        List<User> userList=new ArrayList<User>();
//        List<Sentence> sentenceList=new ArrayList<Sentence>();
//        List<Label> labelList=new ArrayList<Label>();
//        List<Mood> moodList=new ArrayList<Mood>();
//    	if(kind.equals("suggest")){
//    		userList=commonService.getSuggestPeople(page,limit);
//    		sentenceList=commonService.getSuggestSentences(page,limit);
//    		labelList=commonService.getSuggestLabels(page,limit);
//    		moodList=commonService.getSuggestMoods(page,limit);
//    		session.put("pageType", "suggest");
//    	}else{
//    		userList=commonService.getActiveUser(page,limit);
//    		sentenceList=commonService.getLatestSentences(page,limit);
//    		labelList=commonService.getHotLabels(page,limit);
//    		moodList=commonService.getPopularMoods(page,limit);
//    		session.put("pageType","latest");
//    	}
//    	session.put("userList", userList);
//    	session.put("sentenceList", sentenceList);
//    	session.put("labelList", labelList);
//    	session.put("moodList", moodList);
//    	return SUCCESS;
//    }
//    //显示其他人的主页
//    public String displayOtherPage(){
//    	int userID=(Integer)request.get("userID");
//    	int page=(Integer)request.get("page");
//    	int limit=(Integer)request.get("limit");
//    	User user=userService.getUser(userID);
//    	List<User> attentionUserList=userService.getAttentionUsers(userID, page, limit);
//    	List<User> followingUserList=userService.getFollowingUsers(userID, page, limit);
//    	List<User> visitUserList=userService.getVisitUsers(userID, page, limit);
//        List<Sentence> sentenceList=userService.getMySentences(userID, page, limit);
//        List<Label> labelList=userService.getLabels(userID, page, limit);
//        session.put("showUser",user);
//        session.put("attentionUserList", attentionUserList);
//        session.put("followingUserList", followingUserList);
//        session.put("visitUserList", visitUserList);
//        session.put("sentenceList", sentenceList);
//        session.put("labelList", labelList);
//        return SUCCESS;
//    }
//    //显示自己的主页
//    public String displayOwnPage(){
//    	User loginUser=(User) session.get("loginUser");
//    	int userID=loginUser.getUserID();
//    	int page=(Integer)request.get("page");
//    	int limit=(Integer)request.get("limit");
//    	List<User> attentionUserList=userService.getAttentionUsers(userID, page, limit);
//    	List<User> followingUserList=userService.getFollowingUsers(userID, page, limit);
//    	List<User> visitUserList=userService.getVisitUsers(userID, page, limit);
//        List<Sentence> SuggestSentenceList=userService.getSuggestSentences(userID, page, limit);
//        List<Label> labelList=userService.getLabels(userID, page, limit);
//        session.put("attentionUserList", attentionUserList);
//        session.put("followingUserList", followingUserList);
//        session.put("visitUserList", visitUserList);
//        session.put("SuggestSentenceList", SuggestSentenceList);
//        session.put("labelList", labelList);
//        return SUCCESS;
//    }
//    //搜索输入框的ajax提示
//    public String searchAjax(){
//    	String keyword=(String) request.get("keyword");
//    	int page=0;
//    	int limit=4;
//    	
//    	Map jsonMap=new HashMap();
//    	User user=new User();
//    	user.setUsername(keyword);
//    	jsonMap.put("userList",userService.searchUser(user, page, limit));
//    	Sentence sentence=new Sentence();
//    	sentence.setContent(keyword);
//    	jsonMap.put("sentenceList", userService.searchSentence(sentence, page, limit));
//    	Label label=new Label();
//    	label.setContent(keyword);
//    	jsonMap.put("labelList", userService.searchLabel(label, page, limit));
//    	Mood mood=new Mood();
//    	mood.setContent(keyword);
//    	jsonMap.put("moodList", userService.searchMood(mood, page, limit));
//    	
//    	JSONObject jsonObject=JSONObject.fromObject(jsonMap);
//    	resultJson=jsonObject.toString();
//    	return SUCCESS;    	
//    }
//    
//    
    
    
	public ICommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
	}


	public void setRequest(Map<String, Object> request) {
	    this.request=request;
	}
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getResultJson() {
		return resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}
    
}
