package bijian.controller.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.SessionAware;

import bijian.model.bean.User;
import bijian.model.bean.relationbean.SubscribeLabel;
import bijian.model.service.ILabelService;

import com.opensymphony.xwork2.ActionSupport;

public class LabelAction extends ActionSupport implements SessionAware {
	private  Map request;
    private  Map session;
    
    private ILabelService labelService;
    
    private String resultJson;
     
     //得到我搜藏的标签
     public String getSubscribeLabel(){
        int page=Integer.parseInt(request.get("page").toString());
     	int limit=Integer.parseInt(request.get("limit").toString());
     	User loginUser=(User) session.get("loginUser");
     	long userID=loginUser.getUserID();
     	List<SubscribeLabel> subscribeLabels=labelService.getSubscribeLabels(userID, page, limit);
     	JSONObject jsonObject=new JSONObject();
     	jsonObject.put("subscribeLabels", subscribeLabels);
     	resultJson=JSONObject.fromObject(jsonObject).toString();
     	return SUCCESS;
     }
     //取消搜藏的标签
     public String cancelSubscribeLabel(){
        long subscribeLabelID=Long.parseLong(request.get("subscribeLabelID").toString());
        labelService.cancelSubscribeLabel(subscribeLabelID);
     	return SUCCESS;
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
	public ILabelService getLabelService() {
		return labelService;
	}
	public void setLabelService(ILabelService labelService) {
		this.labelService = labelService;
	}
	public String getResultJson() {
		return resultJson;
	}
	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}
	public Map getSession() {
		return session;
	}
}
