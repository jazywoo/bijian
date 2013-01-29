package bijian.model.dao.hibernateImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.relationbean.Forwarding;
import bijian.model.dao.IForwardingDao;

public class ForwardingDaoImpl implements IForwardingDao{
    private HibernateTemplate hibernateTemplate;
	
	public List<Forwarding> getForwardingsBySentence(final long sentenceID, final int page,
			final int limit) {
		final String sql="from Forwarding f " +
				      "   where f.sentence.sentenceID=:userID ";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
			  Query query=session.createQuery(sql);
			  query.setParameter("sentenceID", sentenceID)
			  .setFirstResult(page)
			  .setMaxResults(limit);
			  return query.list();
			}
		});
	}

	public int getForwardingsSizeBySentence(final long sentenceID) {
		final String sql="select count(*) from Forwarding f " +
				  	  "   where f.sentence.sentenceID=:sentenceID";
		Long size=(Long) this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query=session.createQuery(sql);
			  query.setParameter("sentenceID", sentenceID);
			  return query.list();
			}
		}).get(0);
		return size.intValue();
	}

	public void delete(Object id) {
		Forwarding forwarding=this.hibernateTemplate.get(Forwarding.class, (Long)id);
		this.hibernateTemplate.delete(forwarding);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(Forwarding.class, (Long)id);
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
