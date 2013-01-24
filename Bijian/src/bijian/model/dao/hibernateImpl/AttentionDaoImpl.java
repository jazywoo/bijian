package bijian.model.dao.hibernateImpl;

import java.sql.SQLException;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.relationbean.Attention;
import bijian.model.dao.IAttentionDao;

public class AttentionDaoImpl implements IAttentionDao{
	private HibernateTemplate hibernateTemplate;
	
	
	public void delete(final long selfID,final long attentionerID){
		final String sql="delete from Attention a " +
					  "   where a.self.userID=:selfID and a.attentioner.userID=:attentionerID ";
		this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
		        Query query=session.createQuery(sql);
		        query.setParameter("selfID", selfID)
		        .setParameter("attentionerID", attentionerID);
		        query.executeUpdate();
		        return null;
			}
		});
	}
	public Attention get(final long selfID,final long attentionerID){
		final String sql="from Attention a " +
				     "   where a.self.userID=:selfID and a.attentioner.userID=:attentionerID ";
		List attentions=this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
			  Query query=session.createQuery(sql);
			  query.setParameter("selfID", selfID)
			  .setParameter("attentionerID", attentionerID);
			  return query.list();
			}
		});
		if(attentions.size()>0){
			return (Attention) attentions.get(0);
		}
		return null;
	}
	public List<Attention> get(final long userID, final int page, final int limit) {
		final String sql="from Attention a " +
				"   where a.self.userID=:userID ";
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

	public int getAttentionListSize(final long userID) {
		final String sql="select count(*) from Attention a " +
				      "   where a.self.userID=:userID";
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
		Attention attention=(Attention) this.hibernateTemplate.get(Attention.class, (Long)id);
		this.hibernateTemplate.delete(attention);
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
