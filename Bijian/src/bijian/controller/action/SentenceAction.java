package bijian.controller.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import bijian.model.bean.Label;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Forwarding;
import bijian.model.bean.relationbean.LoveSentence;
import bijian.model.dao.hibernateImpl.SentenceDaoImpl;
import bijian.model.dao.hibernateImpl.UserDaoImpl;
import bijian.model.service.ISentenceService;
import bijian.model.service.IUserService;

import com.opensymphony.xwork2.ActionSupport;

public class SentenceAction extends ActionSupport implements SessionAware,RequestAware {
	private Map session;
	private Map request;
	
	private IUserService userService;
	private ISentenceService sentenceService;
	
	private Sentence sentence;
	private List<Label> sentenceLabels;
	
	private String resultJson;
	//发表句子
	public String publishSentence(){
		User loginUser=(User) session.get("loginUser");
     	long userID=loginUser.getUserID();
		sentenceService.addSentence(userID, sentence, sentenceLabels);
     	return SUCCESS;
     }
	//修改句子
     public String modifySentence() throws Exception{
    	User loginUser=(User) session.get("loginUser");
      	long userID=loginUser.getUserID();
    	long sentenceID=Long.parseLong(request.get("sentenceID").toString());
    	sentenceService.updateSentence(userID,sentenceID,sentence,sentenceLabels);
    	return SUCCESS;
     }
     //删除句子
     public String deleteSentence() throws Exception{
    	long sentenceID=Long.parseLong(request.get("sentenceID").toString());
    	sentenceService.deleteSentence(sentenceID);
     	return SUCCESS;
     }
     
     //转发
     public String forwarding() throws Exception{
    	User loginUser=(User) session.get("loginUser");
     	long userID=loginUser.getUserID();
     	long sentenceID=Long.parseLong(request.get("sentenceID").toString());
	    sentenceService.forwardingSentence(userID, sentenceID);
      	return SUCCESS;
     }
    //得到转发句子
     public String getForwarding(){
     	long sentenceID=Long.parseLong(request.get("sentenceID").toString());
     	int page=Integer.parseInt(request.get("page").toString());
    	int limit=Integer.parseInt(request.get("limit").toString());
     	List<Forwarding> forwardings=sentenceService.getForwarding(sentenceID, page, limit);
     	JSONObject jsonObject=new JSONObject();
     	jsonObject.put("forwarding", forwardings);
     	resultJson=JSONObject.fromObject(jsonObject).toString();
     	return SUCCESS;
     }
     //赞,喜欢
     public String loving() throws Exception{
    	User loginUser=(User) session.get("loginUser");
      	long userID=loginUser.getUserID();
    	long sentenceID=Long.parseLong(request.get("sentenceID").toString());
    	sentenceService.lovingSentence(sentenceID,userID);
 	    return SUCCESS;
     }
     //得到喜欢句子的人
     public String getLove(){
     	long sentenceID=Long.parseLong(request.get("sentenceID").toString());
     	int page=Integer.parseInt(request.get("page").toString());
    	int limit=Integer.parseInt(request.get("limit").toString());
     	List<LoveSentence> loveSentences=sentenceService.getLove(sentenceID, page, limit);
     	JSONObject jsonObject=new JSONObject();
     	jsonObject.put("loveSentences", loveSentences);
     	resultJson=JSONObject.fromObject(jsonObject).toString();
     	return SUCCESS;
     }
     //举报
     public String report() throws Exception{
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	long sentenceID=(Integer)request.get("sentenceID");
	    sentenceService.reportSentence(userID, sentenceID);
      	return SUCCESS;
     }
     //得到更多的最新句子
     public String getLatestSentences(){
    	int page=Integer.parseInt(request.get("page").toString());
     	int limit=Integer.parseInt(request.get("limit").toString());
     	List<Sentence> sentences=sentenceService.getLatestSentence(page, limit);
     	JSONObject jsonObject=new JSONObject();
     	jsonObject.put("latestSentences", sentences);
     	resultJson=JSONObject.fromObject(jsonObject).toString();
     	return SUCCESS;
     }
     //得到系统推荐的句子
     public String getSuggestSentences(){
    	User loginUser=(User) session.get("loginUser");
     	long userID=loginUser.getUserID();
    	int page=Integer.parseInt(request.get("page").toString());
      	int limit=Integer.parseInt(request.get("limit").toString());
      	List<Sentence> sentences=sentenceService.getSuggestSentences(userID, page, limit);
      	JSONObject jsonObject=new JSONObject();
      	jsonObject.put("suggestSentences", sentences);
      	resultJson=JSONObject.fromObject(jsonObject).toString();
      	return SUCCESS;
     }   
     //得到所有关注人的的句子
     public String getAllAttentionSentences(){
    	int page=Integer.parseInt(request.get("page").toString());
       	int limit=Integer.parseInt(request.get("limit").toString());
      	User loginUser=(User) session.get("loginUser");
     	long userID=loginUser.getUserID();
      	List<Sentence> sentences=sentenceService.getOneAttentionSentences(userID, page, limit);
      	JSONObject jsonObject=new JSONObject();
      	jsonObject.put("attentionSentences", sentences);
      	resultJson=JSONObject.fromObject(jsonObject).toString();
      	return SUCCESS;
     }
     //得到某一个关注人的的句子
     public String getOneAttentionSentences(){
    	int page=Integer.parseInt(request.get("page").toString());
       	int limit=Integer.parseInt(request.get("limit").toString());
       	long attentionerID=Long.parseLong(request.get("attentionerID").toString());
      	List<Sentence> sentences=sentenceService.getOneAttentionSentences(attentionerID, page, limit);
      	JSONObject jsonObject=new JSONObject();
      	jsonObject.put("attentionSentences", sentences);
      	resultJson=JSONObject.fromObject(jsonObject).toString();
      	return SUCCESS;
     }
     //得到我喜欢的句子
     public String getLoveSentences(){
    	User loginUser=(User) session.get("loginUser");
     	long userID=loginUser.getUserID();
    	int page=Integer.parseInt(request.get("page").toString());
      	int limit=Integer.parseInt(request.get("limit").toString());
      	List<Sentence> sentences=sentenceService.getLovedSentences(userID, page, limit);
      	JSONObject jsonObject=new JSONObject();
      	jsonObject.put("lovedSentences", sentences);
      	resultJson=JSONObject.fromObject(jsonObject).toString();
      	return SUCCESS;
     }
     //取消喜欢句子
     public String cancelLove(){
    	 long loveSentenceID=Long.parseLong(request.get("loveSentenceID").toString());
    	 sentenceService.cancelLove(loveSentenceID);
    	 return SUCCESS;
     }
     //得到与我相关的句子
     public String getRelatedSentences(){
    	int page=(Integer)request.get("page");
       	int limit=(Integer)request.get("limit");
       	User loginUser=(User) session.get("loginUser");
      	long userID=loginUser.getUserID();
       	List<Sentence> sentences=sentenceService.getRelatedMeSentences(userID, page, limit);
       	JSONObject jsonObject=new JSONObject();
      	jsonObject.put("relatedSentences", sentences);
      	resultJson=JSONObject.fromObject(jsonObject).toString();
      	return SUCCESS;
     }
     //得到用户所有收藏标签下的句子
     public String getAllLabelSentences(){
    	User loginUser=(User) session.get("loginUser");
      	long userID=loginUser.getUserID();
     	int page=Integer.parseInt(request.get("page").toString());
       	int limit=Integer.parseInt(request.get("limit").toString());
    	List<Sentence> sentences=sentenceService.getAllLabelSentences(userID, page, limit);
    	JSONObject jsonObject=new JSONObject();
      	jsonObject.put("labelSentences", sentences);
      	resultJson=JSONObject.fromObject(jsonObject).toString();
      	return SUCCESS;
      }
     //得到某一个标签下的句子
     public String getOneLabelSentences(){
    	User loginUser=(User) session.get("loginUser");
      	long userID=loginUser.getUserID();
      	long labelID=Long.parseLong(request.get("labelID").toString());
     	int page=Integer.parseInt(request.get("page").toString());
       	int limit=Integer.parseInt(request.get("limit").toString());
    	List<Sentence> sentences=sentenceService.getOneLabelSentences(labelID, page, limit);
    	JSONObject jsonObject=new JSONObject();
      	jsonObject.put("labelSentences", sentences);
      	resultJson=JSONObject.fromObject(jsonObject).toString();
      	return SUCCESS;
      }
     //得到用户自己的句子
     public String getOwnSentences(){
    	int page=Integer.parseInt(request.get("page").toString());
    	int limit=Integer.parseInt(request.get("limit").toString());
    	User loginUser=(User) session.get("loginUser");
       	long userID=loginUser.getUserID();
        List<Sentence> sentences=sentenceService.getMySentences(userID, page, limit);
        JSONObject jsonObject=new JSONObject();
      	jsonObject.put("mySentences", sentences);
      	resultJson=JSONObject.fromObject(jsonObject).toString();
      	return SUCCESS;
     }
     
     
     
     
	public String getResultJson() {
		return resultJson;
	}
	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	public Map getRequest() {
		return request;
	}
	public void setRequest(Map request) {
		this.request = request;
	}
	public ISentenceService getSentenceService() {
		return sentenceService;
	}
	public void setSentenceService(ISentenceService sentenceService) {
		this.sentenceService = sentenceService;
	}
}
