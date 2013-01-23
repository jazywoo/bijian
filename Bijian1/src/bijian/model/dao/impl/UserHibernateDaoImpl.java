package bijian.model.dao.impl;


import java.util.List;  

import bijian.model.bean.User;
import bijian.model.dao.IUserDao;

/** 
 * 用户Dao 
 * @author pasu 
 * @vesion 1.0, 2008-3-2 
 * 
 */  
public class UserHibernateDaoImpl extends BaseHibernateDaoImpl<User,String> implements IUserDao {  
    // property constants  
    public static final String USER_NAME = "userName";  
      
    /** 
     * 通过名称查找用户 
     * @return TUser  
     */  
    public User findByUserName(String userName)  
    {  
        List<User> userList = super.findByProperty(USER_NAME, userName);  
        if(userList.size() != 0)  
        {  
            return userList.get(0);  
        }  
        else  
        {  
            return null;  
        }  
    }  
}  