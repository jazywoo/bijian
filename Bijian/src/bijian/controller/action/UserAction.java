package bijian.controller.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.Following;
import bijian.model.dao.hibernateImpl.UserDaoImpl;
import bijian.model.service.ISentenceService;
import bijian.model.service.IUserService;
import bijian.util.upload.FileUploadTool;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements SessionAware,RequestAware{	
	private Map session;
	private Map request;
	
	private IUserService userService;
	private ISentenceService sentenceService;
	
	private User user;//修改用户信息
	private FileUploadTool uploadTool=new FileUploadTool();//上传文件处理类
	
	private String resultJson;
	
	//得到用户信息
	public String getUserInfo(){
		long userID=Long.parseLong(request.get("userID").toString());
		User user=userService.getUser(userID);
		Sentence hotestSentence=sentenceService.getMyHotestSentence(userID);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("user", user);
		jsonObject.put("followingSize", user.getFollowingNum());
		jsonObject.put("sentencesSize", user.getSentenceNum());
		jsonObject.put("hotestSentence", hotestSentence);
		resultJson=JSONObject.fromObject(jsonObject).toString();	
		return SUCCESS;
	}
	
	//登陆
	public String login(){
		String result;
		System.out.println(user.getUsername());
		User loginUser=this.userService.login(user.getUsername(), user.getPassword());
		if(null!=loginUser){
			this.session.put("loginUser", loginUser);
			result=SUCCESS;
		}else{
			result=INPUT;
		}
		return result;
    }
	//登出
    public String logout(){
    	session.remove("loginUser");
    	return SUCCESS;
    }
    //修改个人信息
    public String modifyPhoto(){
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	String saveDir=ServletActionContext.getServletContext().getRealPath("uploadDir");	
    	uploadTool.setSaveDir(saveDir);
    	uploadTool.setUserID(userID);
    	try {
			uploadTool.beginUpload();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    public String modifyBasicInfomation(){
    	
    	return SUCCESS;
    }
    public String modifyPassword(){
    	String result=SUCCESS;
    	String password=(String) request.get("user.password");
    	String verifyPassword=(String) request.get("user.verifyPassword");
    	if(!password.equals(verifyPassword)){
    		result="notEquals";
    	}else{
    		User loginUser=(User) session.get("loginUser");
    		if(password.equals(loginUser.getPassword())){
    			result="unModify";
    		}else{
    			loginUser.setPassword(password);
    			this.userService.update(loginUser);
    			result=SUCCESS;
    		}
    		
    	}
    	return result;
    }
    //关注某人
    public String attentionOne(){
    	long attentionUserID=Long.parseLong(request.get("attentionUserID").toString());
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	this.userService.attentionOne(userID, attentionUserID);
    	return SUCCESS;
    }
    //取消关注某人
    public String cancelAttention(){
    	long attentionUserID=Long.parseLong(request.get("attentionUserID").toString());
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	this.userService.cancelAttention(userID, attentionUserID);
    	return SUCCESS;
    }
    //得到粉丝
    public String getFollowing(){
    	int page=Integer.parseInt(request.get("page").toString());
    	int limit=Integer.parseInt(request.get("limit").toString());
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	List<Following> followings=userService.getFollowings(userID, page, limit);
    	JSONObject jsonObject=new JSONObject();
      	jsonObject.put("followings", followings);
      	resultJson=JSONObject.fromObject(jsonObject).toString();
      	return SUCCESS;
    }
    //得到我关注的
    public String getAttention(){
    	int page=Integer.parseInt(request.get("page").toString());
    	int limit=Integer.parseInt(request.get("limit").toString());
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	List<Attention> attentions=userService.getAttentions(userID, page, limit);
    	JSONObject jsonObject=new JSONObject();
      	jsonObject.put("attentions", attentions);
      	resultJson=JSONObject.fromObject(jsonObject).toString();
      	return SUCCESS;
    }
    
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public String getResultJson() {
		return resultJson;
	}
	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}
	public ISentenceService getSentenceService() {
		return sentenceService;
	}
	public void setSentenceService(ISentenceService sentenceService) {
		this.sentenceService = sentenceService;
	}
	public FileUploadTool getUploadTool() {
		return uploadTool;
	}
	public void setUploadTool(FileUploadTool uploadTool) {
		this.uploadTool = uploadTool;
	}
	
}
