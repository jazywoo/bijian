package bijian.model.dao.hibernateImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.Chat;
import bijian.model.bean.relationbean.LabelSentence;
import bijian.model.bean.relationbean.LabelUser;
import bijian.model.dao.ILabelUserDao;

public class LabelUserDaoImpl implements ILabelUserDao{
	private HibernateTemplate hibernateTemplate;
	
	public List<LabelUser> getLabelUsersByLabel(final long labelID, final int page,
			final int limit) {
		final String sql="from LabelUser as l " +
						"  where l.label.labelID=:labelID";
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
	public int getLabelUsersSizeByLabel(final long labelID) {
		final String sql="select count(*) from LabelUser as l " +
				        "  where l.label.labelID=:labelID";
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
	public int getLabelUsersSizeByLabel(final long labelID,final Date date1,final Date date2){
		final String sql="select count(*) from LabelUser as l " +
		        		"  where l.label.labelID=:labelID" +
		        		"    and l.createTime between :date1 and :date2";
		Long size=(Long)  this.hibernateTemplate.executeFind(new HibernateCallback() {     
			public Object doInHibernate(Session session)     
			throws HibernateException, SQLException {     
				Query query = session.createQuery(sql); 
				query.setParameter("labelID", labelID)
				.setParameter("date1", date1)
                .setParameter("date2", date2);
				return query.list(); 
			}     
		}).get(0);
		return size.intValue();
	}
    public List<LabelUser> getLabelUsersByLabel(final long labelID,final Date date1,final Date date2,final int page,final int limit){
    	final String sql="from LabelUser as l " +
					   "  where l.label.labelID=:labelID" +
					   "    and l.createTime between :date1 and :date2";
		return this.hibernateTemplate.executeFind(new HibernateCallback() {     
			public Object doInHibernate(Session session)     
			throws HibernateException, SQLException {     
				Query query = session.createQuery(sql); 
				query.setParameter("labelID", labelID)
				.setParameter("date1", date1)
                .setParameter("date2", date2)
				.setFirstResult(page)
				.setMaxResults(limit); 
				return query.list(); 
			}     
		});
    }
	public List<LabelUser> getLabelUsersByUser(final long userID,final int page,final int limit) {
		final String sql="from LabelUser as l " +
						"  where l.user.userID=:userID";
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
	public int getLabelUsersSizeByUser(final long userID) {
		final String sql="select count(*) from LabelUser as l " +
				        "  where l.user.userID=:userID";
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
		LabelUser labelUser=(LabelUser) this.hibernateTemplate.get(LabelUser.class, (Long)id);
		this.hibernateTemplate.delete(labelUser);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(Chat.class, (Long)id);
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
