package bijian.controller.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Test;

import com.opensymphony.xwork2.Action;


import bijian.model.bean.User;

public class PageActionTests extends ActionTestBase{
    public void testDisplayHomepage() throws Exception{
//    	User user=new User();
//    	user.setUserID(100);
//    	user.setUsername("jazywoo");
//    	this.request.getSession().putValue("loginUser", user);
    	String result=this.executeAction("/pageAction!displayHomepage.action");
    	//Assert.assertEquals(Action.SUCCESS,result);
    	List<User> hotUserList=(List<User>) findValueAfterExecute("homepage_hotUserList");
    	System.out.println(hotUserList.get(0).getUsername());
    }
}
