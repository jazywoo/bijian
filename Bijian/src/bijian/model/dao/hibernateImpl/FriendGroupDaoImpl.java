package bijian.model.dao.hibernateImpl;

import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.FriendGroup;
import bijian.model.bean.relationbean.Following;
import bijian.model.dao.IFriendGroupDao;

public class FriendGroupDaoImpl implements IFriendGroupDao{
	private HibernateTemplate hibernateTemplate;
	
	public void delete(Object id) {
		FriendGroup friendGroup=(FriendGroup) this.hibernateTemplate.get(FriendGroup.class, (Long)id);
		this.hibernateTemplate.delete(friendGroup);
	}

	public Object get(Object id) {
		return this.hibernateTemplate.get(FriendGroup.class, (Long)id);
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
