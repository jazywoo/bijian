package bijian.model.dao.hibernateImpl;

import java.sql.SQLException;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.Chat;
import bijian.model.dao.IChatDao;

public class ChatDaoImpl implements IChatDao{
    private HibernateTemplate hibernateTemplate;
	
	public List<Chat> getChatList(final long userID,final long chatUserID,final int page, final int limit) {
		final String sql="from Chat c" +
				       "  where c.fromUser.userID=:fromUserID and c.toUser.userID=:toUserID" +
				       "     or c.fromUser.userID=:toUserID and c.toUser.userID=:fromUserID" +
				       "  order by c.createTime desc";
		return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session){
				Query query=session.createQuery(sql);
				query.setParameter("fromUserID", userID)
				.setParameter("toUserID", chatUserID)
				.setFirstResult(page)
				.setMaxResults(limit);
				return query.list();
			}
		});
		
	}
	public int getChatListSize(final long userID, final long chatUserID) {
		final String sql="select count(*) from Chat c " +
	                  "   where c.fromUser.userID=:userID and c.toUser.userID=:chatUserID " +
	                  "      or c.fromUser.userID=:chatUserID and c.toUser.userID=:userID ";
		Long size=(Long) this.hibernateTemplate.executeFind(new HibernateCallback(){
		public Object doInHibernate(Session session){
			Query query=session.createQuery(sql);
		  query.setParameter("userID", userID)
		  .setParameter("chatUserID", chatUserID);
		  return query.list();
		}
		
		}).get(0);
		return size.intValue();
	}
	public void delete(Object id) {
		Chat chat=(Chat) this.hibernateTemplate.get(Chat.class, (Long)id);
		this.hibernateTemplate.delete(chat);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(Chat.class, (Long)id);
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
