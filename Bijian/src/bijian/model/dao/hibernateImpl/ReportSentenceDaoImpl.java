package bijian.model.dao.hibernateImpl;

import org.springframework.orm.hibernate3.HibernateTemplate;

import bijian.model.bean.relationbean.ReportSentence;
import bijian.model.dao.IReportSentenceDao;

public class ReportSentenceDaoImpl implements IReportSentenceDao {
    private HibernateTemplate hibernateTemplate;
	
	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}
	public Object get(Object id) {
		// TODO Auto-generated method stub
		return null;
	}
	public void insert(Object entity) {
		// TODO Auto-generated method stub
		
	}
	public void update(Object entity) {
		// TODO Auto-generated method stub
		
	}
    
    
	public void delete(int reportSentenceID) {
		ReportSentence reportSentence=(ReportSentence) this.hibernateTemplate.get(ReportSentence.class, reportSentenceID);
		this.hibernateTemplate.delete(reportSentence);
	}
	public ReportSentence get(int reportSentenceID) {
		return (ReportSentence) this.hibernateTemplate.get(ReportSentence.class, reportSentenceID);
	}
	public void insert(ReportSentence reportSentence) {
		this.hibernateTemplate.save(reportSentence);
	}
	public void update(ReportSentence reportSentence) {
		this.hibernateTemplate.update(reportSentence);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


}
