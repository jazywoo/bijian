package bijian.model.dao.hibernateImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.Chat;
import bijian.model.bean.Notice;
import bijian.model.dao.INoticeDao;

public class NoticeDaoImpl implements INoticeDao{
    private HibernateTemplate hibernateTemplate;
    
    public void delete(Object id) {
    	Notice notice=(Notice) this.hibernateTemplate.get(Notice.class, (Long)id);
		this.hibernateTemplate.delete(notice);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(Notice.class, (Long)id);
	}

	public void insert(Object entity) {
		this.hibernateTemplate.save(entity);
	}

	public void update(Object entity) {
		this.hibernateTemplate.update(entity);
	}
	
	public List<Notice> getNoticeList(final long userID,final int page,final int limit) {
		final String sql="from Notice n where n.user.userID=:userID ";
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

	public int getNoticeListSize(final long userID) {
		final String sql="select count(*) from Notice n " +
				     "    where n.user.userID=:userID ";
		Long size=(Long) this.hibernateTemplate.executeFind(new HibernateCallback(){
				public Object doInHibernate(Session session){
				Query query=session.createQuery(sql);
				query.setParameter("userID", userID);
				return query.list();
				}
		
		}).get(0);
		return size.intValue();
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


}
