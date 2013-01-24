package bijian.controller.action;

import java.util.Locale;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LanguageAction extends ActionSupport implements SessionAware{
	  private Map session;
	
      public String changeLanguage(){
//    	  System.out.println("sds");
//    	  Locale locale=new Locale("en","US");//(这个能根据你传来的值动态改变)
//    	  this.session.put("WW_TRANS_I18N_LOCALE", locale);
    	  return SUCCESS;
      }
      

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}
}
