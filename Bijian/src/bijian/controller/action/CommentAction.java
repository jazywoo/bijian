package bijian.controller.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import bijian.model.bean.Comment;
import bijian.model.service.IUserService;

import com.opensymphony.xwork2.ActionSupport;

public class CommentAction extends ActionSupport implements SessionAware,RequestAware {
    private Map session;
	private Map request;
	
	private IUserService userService;
	private String resultJson;
	//评论一个句子
    public String commentSentence(){
    	 
       return SUCCESS;
    }
    
     //删除评论
     public String deleteComment() throws Exception{

     	return SUCCESS;
     }
     //显示一个句子的评论
     public String showComment(){
    	 int sentenceID=(Integer)request.get("sentenceID");
    	 int page=(Integer)request.get("page");
     	 int limit=(Integer)request.get("limit");
     	// List<Comment> commentList=userService.getSentenceComment(sentenceID, page, limit);
    	 
     	 return SUCCESS;
     }
     
     
     
     
    public void setSession(Map<String, Object>  session) {
 		this.session = session;
 	}

	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}

	public String getResultJson() {
		return resultJson;
	}
}
