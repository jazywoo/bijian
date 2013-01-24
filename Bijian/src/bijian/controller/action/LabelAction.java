package bijian.controller.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LabelAction extends ActionSupport implements SessionAware {
    private  Map session;
	
	//添加标签
	public String addLabel() throws Exception{
         
     	 return SUCCESS;
     }
	//修改标签
     public String modifyLabel() throws Exception{

    	return SUCCESS;
     }
     //删除标签
     public String deleteLabel() throws Exception{

     	return SUCCESS;
     }
     
     
     
    public void setSession(Map session) {
 		this.session = session;
 	}
}
