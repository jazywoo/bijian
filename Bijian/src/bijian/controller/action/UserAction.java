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
import bijian.model.dao.hibernateImpl.UserDaoImpl;
import bijian.model.service.IUserService;
import bijian.util.upload.FileUploadTool;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements SessionAware,RequestAware{	
	private Map session;
	private Map request;
	private IUserService userService;
	
	private User user;//修改用户信息
	private FileUploadTool uploadTool=new FileUploadTool();//上传文件处理类
	
	private String resultJson;
	
	public String getUserInfo(){
		long userID=Long.parseLong(request.get("userID").toString());
		User user=userService.getUser(userID);
		String userJson=JSONObject.fromObject(user).toString();		
		resultJson="{'root':'user',result:"+userJson+"}";
		return SUCCESS;
	}
	//喜欢该句子的所有用户
	public String getSentenceLoveUsers(){
		long sentenceID=Long.parseLong(request.get("sentenceID").toString());
		int page=Integer.parseInt(request.get("page").toString());
    	int limit=Integer.parseInt(request.get("limit").toString());
    	List<User> sentenceLoveUsers=userService.getSentenceLoveUsers(sentenceID, page, limit);
    	String usersJson=JSONObject.fromObject(sentenceLoveUsers).toString();
    	resultJson="{'root':'sentenceLoveUsers',result:"+usersJson+"}";
		return SUCCESS;
	}
	//转发该句子的所有用户
	public String getSentenceForwardingUsers(){
		long sentenceID=Long.parseLong(request.get("sentenceID").toString());
		int page=Integer.parseInt(request.get("page").toString());
    	int limit=Integer.parseInt(request.get("limit").toString());
    	List<User> sentenceForwardingUsers=userService.getSentenceForwardingUsers(sentenceID, page, limit);
    	String usersJson=JSONObject.fromObject(sentenceForwardingUsers).toString();
    	resultJson="{'root':'sentenceForwardingUsers',result:"+usersJson+"}";
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
    	int attentionUserID=(Integer) request.get("attentionUserID");
    	User loginUser=(User) session.get("loginUser");
    	long userID=loginUser.getUserID();
    	this.userService.attentionOne(userID, attentionUserID);
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
	
}
