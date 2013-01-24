package bijian.model.dao.hibernateImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.Label;
import bijian.model.bean.Message;
import bijian.model.dao.IMessageDao;

public class MessageDaoImpl implements IMessageDao{
	private HibernateTemplate hibernateTemplate;

    public void delete(Object id) {
    	Message message=(Message) this.hibernateTemplate.get(Message.class, (Long)id);
		this.hibernateTemplate.delete(message);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(Message.class, (Long)id);
	}

	public void insert(Object entity) {
		this.hibernateTemplate.save(entity);
	}

	public void update(Object entity) {
		this.hibernateTemplate.update(entity);
	}

	public int getMessageListSize(final long fromUserID,final long toUserID){
		final String sql="select count(*) from Message m  " +
				   "     where m.fromUser.userID=:fromUserID and m.toUser.userID=:toUserID" +
			       "     or m.fromUser.userID=:toUserID and m.toUser.userID=:fromUserID" +
			       "     order by m.createTime desc";
		Long size=(Long) this.hibernateTemplate.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(sql);
				query.setParameter("fromUserID", fromUserID)
				.setParameter("toUserID", toUserID);
				return query.list();
			}
		}).get(0);
		return size.intValue();
	}
	
	public List<Message> getMessageList(final long fromUserID,final long toUserID,final int page,final int limit){
		final String sql="from Message m" +
				       "  where m.fromUser.userID=:fromUserID and m.toUser.userID=:toUserID" +
				       "     or m.fromUser.userID=:toUserID and m.toUser.userID=:fromUserID" +
				       "     order by m.createTime desc";
			return this.hibernateTemplate.executeFind(new HibernateCallback(){
				public Object doInHibernate(Session session){
					Query query=session.createQuery(sql);
					query.setParameter("fromUserID", fromUserID)
					.setParameter("toUserID", toUserID)
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
