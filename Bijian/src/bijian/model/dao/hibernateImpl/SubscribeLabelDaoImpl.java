package bijian.model.dao.hibernateImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.Chat;
import bijian.model.bean.Label;
import bijian.model.bean.relationbean.LoveSentence;
import bijian.model.bean.relationbean.SubscribeLabel;
import bijian.model.dao.ISubscribeLabelDao;

public class SubscribeLabelDaoImpl implements ISubscribeLabelDao{
	private HibernateTemplate hibernateTemplate;

	
	public List<SubscribeLabel> getSubscribeLabelsByLabel(final long labelID, final int page,
			final int limit) {
		final String sql="from SubscribeLabel as s " +
			       		"  where s.label.labelID=:labelID";
		return this.hibernateTemplate.executeFind(new HibernateCallback() {     
			public Object doInHibernate(Session session)     
			throws HibernateException, SQLException {     
				Query query = session.createQuery(sql); 
				query.setParameter("labelID", labelID)
				.setFirstResult(page)
				.setMaxResults(limit);     
				return query.list(); 
			}     
		});
	}

	public List<SubscribeLabel> getSubscribeLabelsByUser(final long userID, final int page, final int limit) {
		final String sql="from SubscribeLabel as s " +
		   			"  where s.user.userID=:userID";
		return this.hibernateTemplate.executeFind(new HibernateCallback() {     
			public Object doInHibernate(Session session)     
			throws HibernateException, SQLException {     
				Query query = session.createQuery(sql); 
				query.setParameter("userID", userID)
				.setFirstResult(page)
				.setMaxResults(limit);     
				return query.list(); 
			}     
		});
	}

	public int getSubscribeLabelsSizeByLabel(final long labelID) {
		final String sql="select count(*) from SubscribeLabel as s " +
			       		"  where s.label.labelID=:labelID";
		Long size=(Long)  this.hibernateTemplate.executeFind(new HibernateCallback() {     
			public Object doInHibernate(Session session)     
			throws HibernateException, SQLException {     
				Query query = session.createQuery(sql); 
				query.setParameter("labelID", labelID);
				return query.list(); 
			}     
		}).get(0);
		return size.intValue();
	}

	public int getSubscribeLabelsSizeByUser(final long userID) {
		final String sql="select count(*) from SubscribeLabel as s " +
		   				"  where s.user.userID=:userID";
		Long size=(Long)  this.hibernateTemplate.executeFind(new HibernateCallback() {     
			public Object doInHibernate(Session session)     
			throws HibernateException, SQLException {     
				Query query = session.createQuery(sql); 
				query.setParameter("userID", userID);
				return query.list(); 
			}     
		}).get(0);
		return size.intValue();
	}

	public void delete(Object id) {
		SubscribeLabel subscribeLabel=(SubscribeLabel) this.hibernateTemplate.get(SubscribeLabel.class, (Long)id);
		this.hibernateTemplate.delete(subscribeLabel);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(SubscribeLabel.class, (Long)id);
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
