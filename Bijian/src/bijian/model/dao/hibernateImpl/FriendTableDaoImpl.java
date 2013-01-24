package bijian.model.dao.hibernateImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.FriendGroup;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.FriendTable;
import bijian.model.dao.IFriendTableDao;

public class FriendTableDaoImpl implements IFriendTableDao{
	private HibernateTemplate hibernateTemplate;

	public void delete(final long selfID, final long friendID) {
		final String sql="delete from FriendTable f " +
				   "      where f.self.userID=:selfID and f.friend.userID=:friendID " +
				   "         or f.self.userID=:friendID and f.friend.userID=:selfID ";
	    this.hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(sql);
				query.setParameter("selfID", selfID)
				.setParameter("friendID", friendID);
				query.executeUpdate();
				return null;
			}
		});
	}

	public FriendTable get(final long selfID, final long friendID) {
		final String sql="from FriendTable f " +
		   "      where f.self.userID=:selfID and f.friend.userID=:friendID " +
		   "         or f.self.userID=:friendID and f.friend.userID=:selfID ";
		List friends=this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
			  Query query=session.createQuery(sql);
			  query.setParameter("selfID", selfID)
			  .setParameter("friendID", friendID);
			  return query.list();
			}
		});
		if(friends.size()>0){
			return (FriendTable) friends.get(0);
		}
		return null;
	}

	public List<FriendTable> get(final long userID, final int page, final int limit) {
		final String sql="from FriendTable f " +
		   "      where f.self.userID=:userID or f.friend.userID=:userID";
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

	public int getFriendListSize(final long userID) {
		final String sql="select count(*) from FriendTable f " +
		   "      where f.self.userID=:userID or f.friend.userID=:userID";
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
		FriendTable friend=(FriendTable) this.hibernateTemplate.get(FriendTable.class, (Long)id);
		this.hibernateTemplate.delete(friend);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(Attention.class, (Long)id);
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
