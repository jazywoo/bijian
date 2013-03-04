package bijian.controller.action;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.UUID;



import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import com.opensymphony.xwork2.Action;


import bijian.model.bean.Label;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;

public class PageActionTests extends ActionTestBase{
	
    public void testDisplayHomepage() throws Exception{
//    	User user=new User();
//    	user.setUserID(100);
//    	user.setUsername("jazywoo");
//    	this.request.getSession().putValue("loginUser", user);
    	String result=this.executeAction("/pageAction!displayHomepage.action");
    	//Assert.assertEquals(Action.SUCCESS,result);
    	Map resultMap=(Map) findValueAfterExecute("resultMap");
    	List<User> hotUserList=(List<User>) resultMap.get("homepage_hotUserList");
    	List<Sentence> sentenceList=(List<Sentence>) resultMap.get("homepage_sentenceList");
    	List<Label> hotLabelList=(List<Label>) resultMap.get("homepage_hotLabelList");
    	System.out.println(hotUserList.get(0).getNickname());
//    	System.out.println(sentenceList.get(0).getContent());
//    	System.out.println(hotLabelList.get(0).getContent());
    }
    public void testDisplayUserHomepage() throws Exception{
    	User user=new User();
    	user.setUserID(100);
    	user.setUsername("jazywoo");
    	HttpSession session=new MockHttpSession();
//    	String sessionID=UUID.randomUUID().toString();
//    	session.setAttribute(ConstParameter.USER_SESSION, sessionID);
    	session.putValue("loginUser", user);
    	this.request.setSession(session);
    	
    	String result=this.executeAction("/pageAction!displayUserHomepage.action");
    	//Assert.assertEquals(Action.SUCCESS,result);
    	Map resultMap=(Map) findValueAfterExecute("resultMap");
    	String userHomePage_type=(String) resultMap.get("userHomePage_type");
    	Assert.assertEquals("suggest", userHomePage_type);
    }
    public void testDisplayUserLoveSentence(){
    	
    }
	public void testDisplayUserAllLabelSentence(){
		
	}
    public void testDisplayUserOneLabelSentence(){
    	
    }
    public void testDisplayUserAllAttentionSentence(){
		
	}
    public void testDisplayUserOneAttentionSentence(){
    	
    }
    public void testDisplayDiscoveryPage(){
    	
    }
    public void testDisplayOwnSetting(){
		
	}
    public void testDisplayOwnSentence(){
    	
    }
    public void testDisplayOwnFollowing(){
    	
    }
    public void testDisplayOwnAttention(){
		
	}
    public void testDisplayOwnSubscribeLabel(){
    	
    }
    public void testDisplayOwnLoveSentence(){
    	
    }
    public void testDisplayOwnNotice(){
		
	}
    public void testDisplayOwnMessage(){
    	
    }
}
