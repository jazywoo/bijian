package bijian.model.dao.hibernateImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.LabelUser;
import bijian.model.dao.IUserDao;

public class UserDaoImpl implements IUserDao {  
	private HibernateTemplate hibernateTemplate;

	public User get(String username) {
		String sql="from User as u where u.username=?";
		List users=this.hibernateTemplate.find(sql, username);
		if(users!=null&&users.size()>0){
			return (User) users.get(0);
		}
		return null;		
	}

	public List<User> getLike(final User user, final int page, final int limit) throws Exception {
		final StringBuffer sql=new StringBuffer("from User as u where 1=1 ");
		final int[] propertiesFlag=getSql(user,sql);
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(sql.toString());
				int i=0;
				if(propertiesFlag[i++]==1){
					query.setParameter("username", "%"+user.getUsername()+"%");
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("nickname", "%"+user.getNickname()+"%");
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("sex", user.getSex());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("age", user.getAge());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("province", user.getProvince());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("city", user.getCity());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("area", user.getArea());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("constellation", user.getConstellation());
				}
				query.setFetchSize(page).setMaxResults(limit);
				return query.list();
			}
		});
	}

	public int getLikeSize(final User user) throws Exception {
		final StringBuffer sql=new StringBuffer("select count(*) from User as u where 1=1 ");
		final int[] propertiesFlag=getSql(user,sql);
		Long size=(Long)this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				Query query=session.createQuery(sql.toString());
				int i=0;
				if(propertiesFlag[i++]==1){
					query.setParameter("username", user.getUsername());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("nickname", user.getNickname());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("sex", user.getSex());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("age", user.getAge());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("province", user.getProvince());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("city", user.getCity());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("area", user.getArea());
				}
				if(propertiesFlag[i++]==1){
					query.setParameter("constellation", user.getConstellation());
				}
				return query.list();
			}
		}).get(0);
		return size.intValue();
	}
	public List<User> getHotUsers(final int page, final int limit) {
		 final String sql="from User as u" +
				         " order by u.hotValue desc";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query=session.createQuery(sql);
				query.setFirstResult(page)
				.setMaxResults(limit);
				return query.list();				
			}
		});
	}
	public User getHotUserByLabel(final long labelID){
		final String sql="from User as u " +
				       "  where u.hotValue=(select max(temp.hotValue) from User as temp) " +
				       "    and u.userID in (select distinct lu.user.userID from LabelUser as lu " +
				       "                    where lu.label.labelID=:labelID)";
		return (User)this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query=session.createQuery(sql);
				query.setParameter("labelID", labelID);
				return query.list();				
			}
		}).get(0);
	}
	public List<User> getActiveUsersByLabel(final long labelID,final int page,final int limit){
		final String sql="from LabelUser as lu " +
				       "  where lu.label.labelID=:labelID" +
				       "  order by lu.createTime desc";
		List<LabelUser> labelUsers=this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query=session.createQuery(sql);
				query.setParameter("labelID", labelID)
				.setFirstResult(page)
				.setMaxResults(limit);
				return query.list();				
			}
        });
		List<User> users=new ArrayList<User>();
		for(LabelUser lu:labelUsers){
			users.add(lu.getUser());
		}
		return users;
	}
	public void delete(Object id) {
		User user=(User)this.hibernateTemplate.load(User.class, (Long)id);
		this.hibernateTemplate.delete(user);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(User.class, (Long)id);	
	}

	public void insert(Object entity) {
		this.hibernateTemplate.save(entity);
	}

	public void update(Object entity) {
		this.hibernateTemplate.update(entity);
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//根据参数user的信息，查找类似的user
    private int[] getSql(User user,StringBuffer sql) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Field[] fields=user.getClass().getDeclaredFields();
		int length=fields.length;
		int[] propertiesFlag=new int[length];
		for(int i=0;i<length;i++){
			propertiesFlag[i]=0;
		}
		for(int i=0;i<length;i++){
			Field f=fields[i];
			String fieldName=f.getName();
			String methodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);	
            Method m = user.getClass().getMethod(methodName);
			if(fieldName.equals("username")){   
                String value = (String) m.invoke(user);  
                if(value != null){ 
                	propertiesFlag[0]=1;
                	sql.append(" and u.username like :username ");
                    System.out.println("username value:"+value);  
                }  
            }else if(fieldName.equals("nickname")){   
            	String value = (String) m.invoke(user);  
                if(value != null){  
                	propertiesFlag[1]=1;
                	sql.append(" and u.nickname like :nickname ");
                    System.out.println("nickname value:"+value);  
                }  
            }else if(fieldName.equals("sex")){   
                Integer value = (Integer) m.invoke(user);  
                if(value != null){  
                	propertiesFlag[2]=1;
                	sql.append(" and u.sex=:sex ");
                    System.out.println("sex value:"+value);  
                }  
            }else if(fieldName.equals("age")){   
                Integer value = (Integer) m.invoke(user);  
                if(value != null){  
                	propertiesFlag[3]=1;
                	sql.append(" and u.age=:age ");
                    System.out.println("age value:"+value);  
                }  
            }else if(fieldName.equals("province")){   
            	String value = (String) m.invoke(user);  
                if(value != null){  
                	propertiesFlag[4]=1;
                	sql.append(" and u.province=:province ");
                    System.out.println("province value:"+value);  
                }  
            }else if(fieldName.equals("city")){   
            	String value = (String) m.invoke(user);  
                if(value != null){  
                	propertiesFlag[5]=1;
                	sql.append(" and u.city=:city ");
                    System.out.println("city value:"+value);  
                }  
            }else if(fieldName.equals("area")){   
            	String value = (String) m.invoke(user);  
                if(value != null){  
                	propertiesFlag[6]=1;
                	sql.append(" and u.area=:area ");
                    System.out.println("area value:"+value);  
                }  
            }else if(fieldName.equals("constellation")){   
            	String value = (String) m.invoke(user);  
                if(value != null){  
                	propertiesFlag[7]=1;
                	sql.append(" and u.constellation=:constellation ");
                    System.out.println("constellation value:"+value);  
                }  
            }
		}
		System.out.println(sql.toString());
		return propertiesFlag;
    }

	
	
}  