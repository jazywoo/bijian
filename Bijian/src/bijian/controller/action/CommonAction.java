package bijian.controller.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import bijian.model.bean.Label;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;

import com.opensymphony.xwork2.ActionSupport;


public class CommonAction extends ActionSupport{
	   //搜索输入框的ajax提示
    public String searchAjax(){
//    	String keyword=(String) request.get("keyword");
//    	int page=0;
//    	int limit=4;
//    	
//    	Map jsonMap=new HashMap();
//    	User user=new User();
//    	user.setUsername(keyword);
//    	jsonMap.put("userList",userService.searchUser(user, page, limit));
//    	Sentence sentence=new Sentence();
//    	sentence.setContent(keyword);
//    	jsonMap.put("sentenceList", userService.searchSentence(sentence, page, limit));
//    	Label label=new Label();
//    	label.setContent(keyword);
//    	jsonMap.put("labelList", userService.searchLabel(label, page, limit));
//    	Mood mood=new Mood();
//    	mood.setContent(keyword);
//    	jsonMap.put("moodList", userService.searchMood(mood, page, limit));
//    	
//    	JSONObject jsonObject=JSONObject.fromObject(jsonMap);
//    	resultJson=jsonObject.toString();
    	return SUCCESS;    	
    }
    public String changeLanguage(){
//  	  System.out.println("sds");
//  	  Locale locale=new Locale("en","US");//(这个能根据你传来的值动态改变)
//  	  this.session.put("WW_TRANS_I18N_LOCALE", locale);
  	  return SUCCESS;
    }
}
