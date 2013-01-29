package bijian.model.dao.hibernateImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.Label;
import bijian.model.bean.relationbean.FriendTable;
import bijian.model.dao.ILabelDao;


public class LabelDaoImpl implements ILabelDao{
    private HibernateTemplate hibernateTemplate;

    public void delete(Object id) {
    	Label label=(Label) this.hibernateTemplate.get(Label.class, (Long)id);
		this.hibernateTemplate.delete(label);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(Label.class, (Long)id);
	}

	public void insert(Object entity) {
		this.hibernateTemplate.save(entity);
	}

	public void update(Object entity) {
		this.hibernateTemplate.update(entity);
	}
    

	public List<Label> getLike(final String content,final int page,final int limit) {
		final String sql="from Label as l where l.content like :content";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query=session.createQuery(sql);
				query.setParameter("content", "%"+content+"%")
				.setFirstResult(page)
				.setMaxResults(limit);
				return query.list();				
			}
		});
	}
	 public Label getByContent(final String content){
		 final String sql="from Label as l where l.content=:content";
		return (Label) this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query=session.createQuery(sql);
				query.setParameter("content", content);
				return query.list();				
			}
		}).get(0);
	 }
	 public List<Label> getHotLabels(final int page, final int limit) {
		 final String sql="from Label as l" +
		 		         " order by l.hotValue desc,l.createTime desc";
			return this.hibernateTemplate.executeFind(new HibernateCallback(){
				public Object doInHibernate(Session session){
					Query query=session.createQuery(sql);
					query.setFirstResult(page)
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
