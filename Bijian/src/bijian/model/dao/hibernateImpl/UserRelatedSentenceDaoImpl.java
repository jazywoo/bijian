package bijian.model.dao.hibernateImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.UserRelatedSentence;
import bijian.model.dao.IUserRelatedSentenceDao;

public class UserRelatedSentenceDaoImpl implements IUserRelatedSentenceDao{
    private HibernateTemplate hibernateTemplate;
	 
    public List<UserRelatedSentence> getActiveRelatedSentences(final long userID,
			final int page, final int limit) {
    	final String sql="from UserRelatedSentence u " +
				    "     where u.user.userID=:userID and u.isSentenceActive=1 " +
				    "     order by u.createTime desc ";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(sql);
				query.setParameter("userID", userID)
				.setFirstResult(page)
				.setMaxResults(limit);
				return query.list();
			}
		});
	}

	public int getActiveRelatedSentencesSize(final long userID) {
		final String sql="select count(*) from UserRelatedSentence u " +
			        "     where u.user.userID=:userID and u.isSentenceActive=1 " +
			        "     order by u.createTime desc ";
		Long size=(Long) this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(sql);
				query.setParameter("userID", userID);
				return query.list();
			}
		}).get(0);
		return size.intValue();
	}

	public List<UserRelatedSentence> getRelatedSentences(final long userID, final int page,
			final int limit) {
		final String sql="from UserRelatedSentence u " +
				    "     where u.user.userID=:userID and u.isSentenceActive=0 " +
				    "     order by u.createTime desc ";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(sql);
				query.setParameter("userID", userID)
				.setFirstResult(page)
				.setMaxResults(limit);
				return query.list();
			}
		});
	}

	public int getRelatedSentencesSize(final long userID) {
		final String sql="select count(*) from UserRelatedSentence u " +
			        "     where u.user.userID=:userID and u.isSentenceActive=0 " +
			        "     order by u.createTime desc ";
		Long size=(Long) this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(sql);
				query.setParameter("userID", userID);
				return query.list();
			}
		}).get(0);
		return size.intValue();
	}
    
	public void delete(Object id) {
		UserRelatedSentence relatedObject=(UserRelatedSentence) this.hibernateTemplate.get(UserRelatedSentence.class, (Long)id);
		this.hibernateTemplate.delete(relatedObject);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(UserRelatedSentence.class, (Long)id);
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
