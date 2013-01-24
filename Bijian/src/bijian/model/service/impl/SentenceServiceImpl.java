package bijian.model.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bijian.model.bean.Label;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.ReportSentence;
import bijian.model.bean.relationbean.UserRelatedSentence;
import bijian.model.dao.IAttentionDao;
import bijian.model.dao.ILabelDao;
import bijian.model.dao.IReportSentenceDao;
import bijian.model.dao.ISentenceDao;
import bijian.model.dao.IUserDao;
import bijian.model.dao.IUserRelatedObjectDao;
import bijian.model.service.ISentenceService;

public class SentenceServiceImpl implements ISentenceService{
    private ISentenceDao sentenceDao;
    private IUserDao userDao;
    private IAttentionDao attentionDao;
    private IUserRelatedObjectDao relatedObjectDao;
    private ILabelDao labelDao;
    private IReportSentenceDao reportSentenceDao;
	
	public void addSentence(long userID, Sentence sentence,List<Label> labels) {
		User user=(User) userDao.get(userID);
	    StringBuffer labelsJson=new StringBuffer();
		for(Label l:labels){
			l.setAuthor(user);
			l.setCreateTime(new Date());
			l.setObjectType(0);//标签对象是句子
			sentence.getLabels().add(l);//关联到句子
			labelsJson.append(l.getContent()+"###");
		}
		
		sentence.setAuthor(user);
		sentence.setLabelsJson(labelsJson.toString());
		sentence.setCreateTime(new Date());
		sentence.setIsValid(1);
		sentenceDao.insert(sentence);
	}

	public void deleteSentence(long sentenceID) {
		sentenceDao.delete(sentenceID);
	}

	public void forwardingSentence(long userID, long sentenceID) {
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		User sentenceAuthor=sentence.getAuthor();
		User forwardingUser=(User) userDao.get(userID);
		Sentence forwardingSentence=new Sentence();
		forwardingSentence.setAuthor(forwardingUser);
		forwardingSentence.setContent(sentence.getContent());
		forwardingSentence.setCreateTime(new Date());
		forwardingSentence.setFromPlace(sentenceAuthor.getUsername());
		sentenceDao.insert(forwardingSentence);
	}

	public List<Sentence> getAttentionSentences(long userID, int page, int limit) {
		List<Attention> attentions=attentionDao.get(userID, page, limit);
		List<Long> attentionListID=new ArrayList<Long>();
		for(Attention a:attentions){
			attentionListID.add(a.getAttentioner().getUserID());
		}
		return sentenceDao.getAttention(attentionListID, page, limit);
	}

	public List<Sentence> getHotSentence(int page, int limit) {
		return sentenceDao.getHot(page, limit);
	}

	public List<Sentence> getLatestSentence(int page, int limit) {
		return sentenceDao.getLatest(page, limit);
	}

	public List<Sentence> getMySentences(long userID, int page, int limit) {
		return sentenceDao.getByUserID(userID, page, limit);
	}

	public List<Sentence> getRelatedMeSentences(long userID, int page, int limit) {
		List<UserRelatedSentence> relatedObjects=relatedObjectDao.getRelatedSentences(userID, page, limit);
		List<Long> RelatedListID=new ArrayList<Long>();
		for(UserRelatedSentence r:relatedObjects){
			RelatedListID.add(r.getUser().getUserID());
		}
		return sentenceDao.getRelated(RelatedListID, page, limit);
	}

	public List<Sentence> getSuggestSentences(long userID, int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public void reportSentence(long userID, long sentenceID) {
		User user=(User) userDao.get(userID);
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		ReportSentence reportSentence=new ReportSentence();
		reportSentence.setReporter(user);
		reportSentence.setSentence(sentence);
		reportSentence.setCreateTime(new Date());
		reportSentence.setIsValid(1);
		reportSentenceDao.insert(reportSentence);
		
	}

	public List<Sentence> searchSentence(String keyword, int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public void thinkGood(long sentenceID) {
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		sentence.setGoodNum(sentence.getGoodNum()+1);
		sentenceDao.update(sentence);
	}

	public void updateSentence(long sentenceID, Sentence sentence) {
		Sentence oldSentence=(Sentence) sentenceDao.get(sentenceID);
		if(sentence.getContent()!=null){
			oldSentence.setContent(sentence.getContent());
		}
		if(sentence.getFromPlace()!=null){
			oldSentence.setFromPlace(sentence.getFromPlace());
		}
		sentenceDao.update(oldSentence);
	}

	public ISentenceDao getSentenceDao() {
		return sentenceDao;
	}

	public void setSentenceDao(ISentenceDao sentenceDao) {
		this.sentenceDao = sentenceDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public ILabelDao getLabelDao() {
		return labelDao;
	}

	public void setLabelDao(ILabelDao labelDao) {
		this.labelDao = labelDao;
	}

	public IReportSentenceDao getReportSentenceDao() {
		return reportSentenceDao;
	}

	public void setReportSentenceDao(IReportSentenceDao reportSentenceDao) {
		this.reportSentenceDao = reportSentenceDao;
	}

	public IAttentionDao getAttentionDao() {
		return attentionDao;
	}

	public void setAttentionDao(IAttentionDao attentionDao) {
		this.attentionDao = attentionDao;
	}

	public IUserRelatedObjectDao getRelatedObjectDao() {
		return relatedObjectDao;
	}

	public void setRelatedObjectDao(IUserRelatedObjectDao relatedObjectDao) {
		this.relatedObjectDao = relatedObjectDao;
	}



}
