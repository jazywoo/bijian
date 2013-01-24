package bijian.model.dao.hibernateImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.Comment;
import bijian.model.bean.Label;
import bijian.model.dao.ICommentDao;


public class CommentDaoImpl implements ICommentDao{
	private HibernateTemplate hibernateTemplate;

	public void delete(Object id) {
		Comment comment=(Comment) this.hibernateTemplate.get(Comment.class, (Long)id);
		this.hibernateTemplate.delete(comment);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(Comment.class, (Long)id);
	}

	public void insert(Object entity) {
		this.hibernateTemplate.save(entity);
	}

	public void update(Object entity) {
		this.hibernateTemplate.update(entity);
	}
	
	public int getCommentListSize(final long articleObjectID) {
		final String sql="select count(*) from Comment c " +
			         "   where c.articleObject.articleObjectID=:articleObjectID";
		Long size=(Long) this.hibernateTemplate.executeFind(new HibernateCallback(){
		public Object doInHibernate(Session session){
			Query query=session.createQuery(sql);
			  query.setParameter("articleObjectID", articleObjectID);
			  return query.list();
			}
		
		}).get(0);
		return size.intValue();
	}
	public List<Comment> getCommentList(final long articleObjectID,final int page,final int limit) {
		
		final String sql1="from Comment as c where c.articleObject.articleObjectID=:articleObjectID ";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query=session.createQuery(sql1);
				query.setParameter("articleObjectID", articleObjectID)
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
