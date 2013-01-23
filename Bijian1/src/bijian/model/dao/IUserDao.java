package bijian.model.dao;

import bijian.model.bean.User;
 
/** 
 * 用户Dao 
 *  
 * @author pasu 
 * @see com.rc.video.common.base.IBaseDao 
 *  @vesion 1.0, 2008-3-2 
 */  
public interface IUserDao extends IBaseDao<User,String>  {  
    /** 
     * 通过用户名查找用户 
     *  
     * @param   userName    用户名 
     * @return  TUser       用户对象，如果用户名不存在返回null 
     */  
    public  User findByUserName(String userName);    
}  