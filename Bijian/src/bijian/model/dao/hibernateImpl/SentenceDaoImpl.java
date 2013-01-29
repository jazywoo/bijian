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

import bijian.model.bean.Sentence;
import bijian.model.dao.ISentenceDao;

public class SentenceDaoImpl implements ISentenceDao{
    private HibernateTemplate hibernateTemplate;

    public void delete(Object id) {
		Sentence sentence=(Sentence) this.hibernateTemplate.get(Sentence.class, (Long)id);
		this.hibernateTemplate.delete(sentence);
	}
	public Object get(Object id) {
		return this.hibernateTemplate.get(Sentence.class, (Long)id);
	}
	public void insert(Object entity) {
		this.hibernateTemplate.save(entity);
	}
	public void update(Object entity) {
		this.hibernateTemplate.update(entity);
	}
	
    public List<Sentence> getAttention(final List<Long> attentionUserList, final int page,final int limit) {
    	return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Criteria criteria=session.createCriteria(Sentence.class)
				.add(Restrictions.in("author.userID", attentionUserList))
				.addOrder(Order.desc("createTime"))
				.setFirstResult(page)                   
				.setMaxResults(limit);
				return criteria.list();
			}
		});
	}
    public List<Sentence> getHot(final int page,final int limit){
    	final String sql="from Sentence as s" +
				        " order by s.goodNum desc,s.creatTime desc ";
		return this.hibernateTemplate.executeFind(new HibernateCallback() {     
			 public Object doInHibernate(Session session)     
				 throws HibernateException, SQLException {     
					  Query query = session.createQuery(sql.toString()); 
					  query.setFirstResult(page)     
					  .setMaxResults(limit);     
					  return query.list();        
				}     
		});
    }
    public List<Sentence> getLatest(final int page,final int limit){
    	final String sql="from Sentence as s" +
		                 " order by s.creatTime desc ";
		return this.hibernateTemplate.executeFind(new HibernateCallback() {     
		 public Object doInHibernate(Session session)     
		 throws HibernateException, SQLException {     
			  Query query = session.createQuery(sql.toString()); 
			  query.setFirstResult(page)     
			  .setMaxResults(limit);     
			  return query.list();        
			}     
		});
    }
	public List<Sentence> getByUserID(final long userID, final int page, final int limit) {
		final String sql="from Sentence as s" +
		               "  where s.author.userID=:userID";
		return this.hibernateTemplate.executeFind(new HibernateCallback() {     
		    public Object doInHibernate(Session session)     
		      throws HibernateException, SQLException {     
			     Query query = session.createQuery(sql.toString()); 
			     query.setParameter("userID", userID)
			     .setFirstResult(page)     
			     .setMaxResults(limit);     
			     return query.list();        
		    }     
		  });
	}
	public List<Sentence> getByUserID(final long userID, final Date date1, final Date date2,final int page, final int limit) {
		final String sql="from Sentence as s " +
		               "  where s.author.userID=:userID and s.createTime between :date1 and :date2";
		return this.hibernateTemplate.executeFind(new HibernateCallback() {     
		    public Object doInHibernate(Session session)     
		      throws HibernateException, SQLException {     
			     Query query = session.createQuery(sql); 
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
