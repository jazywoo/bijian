package bijian.test;


import java.util.Date;

import net.sf.json.JSONObject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bijian.model.bean.User;

public class TestMain {
    public static void main(String[] args){
       User user=new User();
       user.setUserID(1);
       user.setUsername("jazuwoo");
     System.out.println(JSONObject.fromObject(user));
    	
    }
}
