package bijian.controller.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.dao.hibernateImpl.SentenceDaoImpl;
import bijian.model.dao.hibernateImpl.UserDaoImpl;
import bijian.model.service.ICommonService;
import bijian.model.service.IUserService;

import com.opensymphony.xwork2.ActionSupport;

public class SentenceAction extends ActionSupport implements SessionAware,RequestAware {
	private Map session;
	private Map request;
	private IUserService userService;
	private ICommonService commonService;
	
	private String resultJson;
	//发表句子
//	public String publishSentence() throws Exception{
//		 User loginUser=(User) session.get("loginUser");
//     	 int userID=loginUser.getUserID();
//     	 int moodID=(Integer)request.get("moodID");
//     	 String content=(String) request.get("content");
//         userService.addSentence(userID,moodID,content);
//     	 return SUCCESS;
//     }
//	//修改句子
//     public String modifySentence() throws Exception{
//    	String content=(String) request.get("content");
//    	int sentenceID=(Integer)request.get("sentenceID");
//    	userService.updateSentence(sentenceID,content);
//    	return SUCCESS;
//     }
//     //删除句子
//     public String deleteSentence() throws Exception{
//    	int sentenceID=(Integer)request.get("sentenceID");
//    	userService.deleteSentence(sentenceID);
//     	return SUCCESS;
//     }
//     //赞
//     public String gooding() throws Exception{
//    	int sentenceID=(Integer)request.get("sentenceID");
//    	userService.thinkGood(sentenceID);
// 	    return SUCCESS;
//     }
//     //转发
//     public String forwarding() throws Exception{
//    	User loginUser=(User) session.get("loginUser");
//     	int userID=loginUser.getUserID();
//     	int sentenceID=(Integer)request.get("sentenceID");
//	    userService.forwardingSentence(userID, sentenceID);
//      	return SUCCESS;
//     }
//     //举报
//     public String report() throws Exception{
//    	User loginUser=(User) session.get("loginUser");
//      	int userID=loginUser.getUserID();
//      	int sentenceID=(Integer)request.get("sentenceID");
//	    userService.reportSentence(userID, sentenceID);
//      	return SUCCESS;
//     }
//     //给句子帖上自己的心情
//     public String addMyMood() throws Exception{
//    	User loginUser=(User) session.get("loginUser");
//       	int userID=loginUser.getUserID();
//       	int sentenceID=(Integer)request.get("sentenceID");
//       	int moodID=(Integer)request.get("moodID");
//	    userService.giveMyMood(sentenceID, userID, moodID);
//      	return SUCCESS;
//     }
//     //得到更多的最新句子
//     public String getLatestSentences(){
//    	int page=(Integer)request.get("page");
//     	int limit=(Integer)request.get("limit");
//     	List<Sentence> sentenceList=commonService.getLatestSentences(page, limit);
//     	return SUCCESS;
//     }
//     //得到系统推荐的句子
//     public String getSuggestSentences(){
//		int page=(Integer)request.get("page");
//     	int limit=(Integer)request.get("limit");
//     	User loginUser=(User) session.get("loginUser");
//    	int userID=loginUser.getUserID();
//     	List<Sentence> sentenceList=userService.getSuggestSentences(userID, page, limit);
//     	return SUCCESS;
//     }
//     //得到关注人的的句子
//     public String getAttentionSentences(){
//    	int page=(Integer)request.get("page");
//      	int limit=(Integer)request.get("limit");
//      	User loginUser=(User) session.get("loginUser");
//     	int userID=loginUser.getUserID();
//      	List<Sentence> sentenceList=userService.getAttentionSentences(userID, page, limit);
//      	
//      	return SUCCESS;
//     }
//     //得到与我相关的句子
//     public String getRelatedSentences(){
//    	int page=(Integer)request.get("page");
//       	int limit=(Integer)request.get("limit");
//       	User loginUser=(User) session.get("loginUser");
//      	int userID=loginUser.getUserID();
//       	List<Sentence> sentenceList=userService.getRelatedMeSentences(userID, page, limit);
//       	
//       	return SUCCESS;
//     }
//     //得到用户自己的句子
//     public String getOwnSentences(){
//    	int page=(Integer)request.get("page");
//    	int limit=(Integer)request.get("limit");
//    	User loginUser=(User) session.get("loginUser");
//       	int userID=loginUser.getUserID();
//        List<Sentence> sentenceList=userService.getMySentences(userID, page, limit);
//        	
//        return SUCCESS;
//     }
//     
//	public void setSession(Map<String, Object> session) {
//		this.session = session;
//	}
//	public void setRequest(Map<String, Object> request) {
//		this.request=request;
//	}
//	public String getResultJson() {
//		return resultJson;
//	}
//	public void setResultJson(String resultJson) {
//		this.resultJson = resultJson;
//	}
//	public IUserService getUserService() {
//		return userService;
//	}
//	public void setUserService(IUserService userService) {
//		this.userService = userService;
//	}
//	public ICommonService getCommonService() {
//		return commonService;
//	}
//	public void setCommonService(ICommonService commonService) {
//		this.commonService = commonService;
//	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
}
