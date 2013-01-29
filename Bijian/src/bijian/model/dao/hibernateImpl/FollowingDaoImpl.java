package bijian.model.dao.hibernateImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.Following;
import bijian.model.dao.IFollowingDao;

public class FollowingDaoImpl implements IFollowingDao{
	private HibernateTemplate hibernateTemplate;
	
	public void delete(final long selfID,final long followingerID){
		final String sql="delete from Following f " +
				  "   where f.self.userID=:selfID and f.followinger.userID=:followingerID ";
		this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
			  Query query=session.createQuery(sql);
			  query.setParameter("selfID", selfID)
			  .setParameter("followingerID", followingerID);
			  query.executeUpdate();
			  return null;
			}
		});
	}
    public Following get(final long selfID,final long followingerID){
    	final String sql="from Following f " +
			     "   where f.self.userID=:selfID and f.followinger.userID=:followingerID ";
    	List followings=this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
			  Query query=session.createQuery(sql);
			  query.setParameter("selfID", selfID)
			  .setParameter("followingerID", followingerID);
			  return query.list();
			}
		});
		if(followings.size()>0){
			return (Following) followings.get(0);
		}
		return null;
    }
	public List<Following> get(final long userID,final int page,final int limit) {
		final String sql="from Following f " +
					  "   where f.self.userID=:userID ";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
		        Query query=session.createQuery(sql);
		        query.setParameter("userID", userID)
		        .setFirstResult(page)
		        .setMaxResults(limit);
		        return query.list();
			}
		});
	}
	
	public int getFollowingListSize(final long userID){
		final String sql="select count(*) from Following f " +
	      			  "   where f.self.userID=:userID";
		Long size=(Long) this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query=session.createQuery(sql);
                query.setParameter("userID", userID);
                return query.list();
			}
			
		}).get(0);
		return size.intValue();
	}

	public void delete(Object id) {
		Following following=(Following) this.hibernateTemplate.get(Following.class, (Long)id);
		this.hibernateTemplate.delete(following);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(Following.class, (Long)id);
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

}
