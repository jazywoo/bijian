package bijian.controller.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import bijian.model.bean.Comment;
import bijian.model.bean.User;
import bijian.model.service.ICommentService;
import bijian.model.service.IUserService;

import com.opensymphony.xwork2.ActionSupport;

public class CommentAction extends ActionSupport implements SessionAware,RequestAware {
    private Map session;
	private Map request;
	
	private IUserService userService;
	private ICommentService commentService;
	
	private Comment comment;
	
	private String resultJson;
	
	//评论一个句子
    public String commentSentence(){
       User loginUser=(User) session.get("loginUser");
       long userID=loginUser.getUserID(); 
       long sentenceID=Long.parseLong(request.get("sentenceID").toString());
       commentService.commentSentence(sentenceID,userID,comment);
       return SUCCESS;
    }
     //删除评论
     public String deleteComment() throws Exception{
        long commentID=Long.parseLong(request.get("commentID").toString());
        commentService.deleteComment(commentID);
     	return SUCCESS;
     }
     //显示一个句子的评论
     public String showComment(){
    	 long sentenceID=Long.parseLong(request.get("sentenceID").toString());
    	 int page=Integer.parseInt(request.get("page").toString());
     	 int limit=Integer.parseInt(request.get("limit").toString());
     	 List<Comment> comments=commentService.getComment(sentenceID, page, limit);
     	 JSONObject jsonObject =new JSONObject();
     	 jsonObject.put("comments", comments);
     	 resultJson=JSONObject.fromObject(jsonObject).toString();
     	 return SUCCESS;
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

	public ICommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(ICommentService commentService) {
		this.commentService = commentService;
	}
}
