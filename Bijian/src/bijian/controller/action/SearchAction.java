package bijian.controller.action;

import java.util.Map;

import bijian.model.bean.Label;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;

import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport {
	private Map request;
	private Map session;
	
//    public String search(){
//    	String keyword=(String) request.get("keyword");
//    	int page=(Integer)request.get("page");
//    	int limit=(Integer)request.get("limit");
//    	
//    	User user=new User();
//    	user.setUsername(keyword);
//    	session.put("userList",userService.searchUser(user, page, limit));
//    	Sentence sentence=new Sentence();
//    	sentence.setContent(keyword);
//    	session.put("sentenceList", userService.searchSentence(sentence, page, limit));
//    	Label label=new Label();
//    	label.setContent(keyword);
//    	session.put("labelList", userService.searchLabel(label, page, limit));
//    	Mood mood=new Mood();
//    	mood.setContent(keyword);
//    	session.put("moodList", userService.searchMood(mood, page, limit));
//    	return SUCCESS;
//    }
}
