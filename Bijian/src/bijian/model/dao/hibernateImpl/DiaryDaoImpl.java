package bijian.model.dao.hibernateImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.ArticleObject;
import bijian.model.bean.Comment;
import bijian.model.bean.Diary;
import bijian.model.dao.IDiaryDao;

public class DiaryDaoImpl implements IDiaryDao{
    private HibernateTemplate hibernateTemplate;
	
    public void delete(Object id) {
    	ArticleObject diary=(ArticleObject) this.hibernateTemplate.get(Diary.class, (Long)id);
		this.hibernateTemplate.delete(diary);
	}

	public Object get(Object id) {
		return (ArticleObject)this.hibernateTemplate.get(Diary.class, (Long)id);
	}

	public void insert(Object entity) {
		this.hibernateTemplate.save(entity);
	}

	public void update(Object entity) {
		this.hibernateTemplate.update(entity);
	}
    
	public List<Diary> get(final List<Long> attentionUserList, final int page, final int limit) {
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria=session.createCriteria(Diary.class)
				.add(Restrictions.in("author.userID", attentionUserList))
				.addOrder(Order.desc("createTime"))
				.setFirstResult(page)
				.setMaxResults(limit);
				return criteria.list();
			}
			
		});
	}

	public List<Diary> getByMoodValue(final int value1, final int value2, final int page,final int limit) {
		final String sql="from Diary as d " +
				        " where d.moodValue between :value1 and :value2 " +
				        " order by d.createTime desc";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(sql);
				query.setParameter("value1", value1)
				.setParameter("value2",value2)
				.setFirstResult(page)
				.setMaxResults(limit);
				return query.list();
			}
		});
	}

	public List<Diary> getByMoodValue(final int value1, final int value2, final Date date1,final Date date2, final int page, final int limit) {
		final String sql="from Diary as d " +
				        " where d.moodValue between :value1 and :value2 " +
				        " and d.createTime between :date1 and :date2" +
				        " order by d.createTime desc";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
		
				public Object doInHibernate(Session session)throws HibernateException, SQLException {
					Query query=session.createQuery(sql);
					query.setParameter("value1", value1)
					.setParameter("value2",value2)
					.setParameter("date1", date1)
					.setParameter("date2", date2)
					.setFirstResult(page)
					.setMaxResults(limit);
					return query.list();
				}
			});
	}

	public List<Diary> getByUserAndMood(final long userID, final int value1, final int value2,final int page, final int limit) {
		final String sql="from Diary as d " +
				        " where d.author.userID=:userID " +
				        " and  d.moodValue between :value1 and :value2 " +
				        " order by d.createTime desc";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
		
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query=session.createQuery(sql);
				query.setParameter("userID", userID)
				.setParameter("value1", value1)
				.setParameter("value2",value2)
				.setFirstResult(page)
				.setMaxResults(limit);
				return query.list();
			}
		});
	}

	public List<Diary> getByUserAndMood(final long userID,final int value1,final int value2,
			final Date date1,final Date date2,final int page,final int limit) {
		final String sql="from Diary as d " +
				        " where d.author.userID=:userID " +
				        " and  d.moodValue between :value1 and :value2 " +
				        " and  d.createTime between :date1 and :date2" +
				        " order by d.createTime desc";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query=session.createQuery(sql);
				query.setParameter("userID", userID)
				.setParameter("value1", value1)
				.setParameter("value2",value2)
				.setParameter("date1", date1)
				.setParameter("date2", date2)
				.setFirstResult(page)
				.setMaxResults(limit);
				return query.list();
			}
		});
	}

	public List<Diary> getByUserID(final long userID,final int page,final int limit) {
		final String sql="from Diary as d " +
				        " where d.author.userID=:userID " +
				        " order by d.createTime desc";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query=session.createQuery(sql);
				query.setParameter("userID", userID)
				.setFirstResult(page)
				.setMaxResults(limit);
				return query.list();
			}
		});
	}

	public List<Diary> getByUserID(final long userID,final Date date1,final Date date2,
			final int page,final int limit) {
		final String sql="from Diary as d " +
				        " where d.author.userID=:userID " +
				        " and  d.createTime between :date1 and :date2" +
				        " order by d.createTime desc";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query=session.createQuery(sql);
				query.setParameter("userID", userID)
				.setParameter("date1", date1)
				.setParameter("date2", date2)
				.setFirstResult(page)
				.setMaxResults(limit);
				return query.list();
			}
		});
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


}
