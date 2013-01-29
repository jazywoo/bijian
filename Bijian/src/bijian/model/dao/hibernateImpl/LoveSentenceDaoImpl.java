package bijian.model.dao.hibernateImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.Chat;
import bijian.model.bean.Sentence;
import bijian.model.bean.relationbean.LabelSentence;
import bijian.model.bean.relationbean.LoveSentence;
import bijian.model.dao.ILoveSentenceDao;

public class LoveSentenceDaoImpl implements ILoveSentenceDao{
	private HibernateTemplate hibernateTemplate;
	
	public List<LoveSentence> getLoveSentencesBySentence(final long sentenceID,final int page,final int limit) {
		final String sql="from LoveSentence as l " +
						"  where l.sentence.sentenceID=:sentenceID";
		return this.hibernateTemplate.executeFind(new HibernateCallback() {     
			public Object doInHibernate(Session session)     
			throws HibernateException, SQLException {     
				Query query = session.createQuery(sql); 
				query.setParameter("sentenceID", sentenceID)
				.setFirstResult(page)
				.setMaxResults(limit); 
				return query.list(); 
			}     
		});
	}

	public List<LoveSentence> getLoveSentencesByUser(final long userID,final int page,final int limit) {
		final String sql="from LoveSentence as l " +
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

	public int getLoveSentencesSizeBySentence(final long sentenceID) {
		final String sql="select count(*) from LoveSentence as l " +
						"  where l.sentence.sentenceID=:sentenceID";
		Long size=(Long)  this.hibernateTemplate.executeFind(new HibernateCallback() {     
			public Object doInHibernate(Session session)     
			throws HibernateException, SQLException {     
				Query query = session.createQuery(sql); 
				query.setParameter("sentenceID", sentenceID); 
				return query.list(); 
			}     
		}).get(0);
		return size.intValue();
	}

	public int getLoveSentencesSizeByUser(final long userID) {
		final String sql="select count(*) from LoveSentence as l " +
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
		LoveSentence loveSentence=(LoveSentence) this.hibernateTemplate.get(LoveSentence.class, (Long)id);
		this.hibernateTemplate.delete(loveSentence);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(LoveSentence.class, (Long)id);
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
