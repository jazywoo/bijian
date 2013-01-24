package bijian.model.dao;

import java.util.List;

import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
   
public interface IUserDao extends IBaseDao {  
	public User get(String username);
	public int getLikeSize(User user)throws Exception;//总共多少条
	public List<User> getLike(User user,int page,int limit)throws Exception; //多条件搜索类似
	
	
}  