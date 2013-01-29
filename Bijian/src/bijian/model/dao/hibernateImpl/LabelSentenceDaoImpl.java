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
import bijian.model.dao.ILabelSentenceDao;

public class LabelSentenceDaoImpl implements ILabelSentenceDao{
    private HibernateTemplate hibernateTemplate;
    
    public int getLabelSentencesSizeByLabel(final long labelID){
    	final String sql="select count(*) from LabelSentence as l " +
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
    public List<LabelSentence> getLabelSentencesByLabel(final long labelID,final int page,final int limit){
    	final String sql="from LabelSentence as l " +
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
    public int getLabelSentencesSizeBySentence(final long sentenceID){
    	final String sql="select count(*) from LabelSentence as l " +
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
    public List<LabelSentence> getLabelSentencesBySentence(final long sentenceID,final int page,final int limit){
    	final String sql="from LabelSentence as l " +
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

	public void delete(Object id) {
		LabelSentence labelSentence=(LabelSentence) this.hibernateTemplate.get(LabelSentence.class, (Long)id);
		this.hibernateTemplate.delete(labelSentence);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(LabelSentence.class, (Long)id);
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
