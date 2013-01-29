package bijian.model.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bijian.model.bean.Label;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.LabelSentence;
import bijian.model.bean.relationbean.LabelUser;
import bijian.model.bean.relationbean.ReportSentence;
import bijian.model.bean.relationbean.UserRelatedSentence;
import bijian.model.dao.IAttentionDao;
import bijian.model.dao.ILabelDao;
import bijian.model.dao.ILabelSentenceDao;
import bijian.model.dao.ILabelUserDao;
import bijian.model.dao.IReportSentenceDao;
import bijian.model.dao.ISentenceDao;
import bijian.model.dao.IUserDao;
import bijian.model.dao.IUserRelatedSentenceDao;
import bijian.model.service.ISentenceService;

public class SentenceServiceImpl implements ISentenceService{
    private ISentenceDao sentenceDao;
    private IUserDao userDao;
    private IAttentionDao attentionDao;
    private IUserRelatedSentenceDao relatedObjectDao;
    private ILabelDao labelDao;
    private ILabelUserDao labelUserDao;
    private ILabelSentenceDao labelSentenceDao;
    private IReportSentenceDao reportSentenceDao;
    private IUserRelatedSentenceDao userRelatedSentenceDao;
	
	public void addSentence(long userID, Sentence sentence,List<Label> labels) {
		User user=(User) userDao.get(userID);
		sentence.setAuthor(user);
		sentence.setCreateTime(new Date());
		sentence.setIsValid(1);
		sentenceDao.insert(sentence);
		//开始处理标签
		for(Label l:labels){
			LabelSentence labelSentence=new LabelSentence();
			labelSentence.setSentence(sentence);
			Label existLabel=labelDao.getByContent(l.getContent());
			LabelUser labelUser=new LabelUser();
			labelUser.setUser(user);
			labelUser.setCreateTime(new Date());
			if(existLabel==null){//标签不存在
				l.setCreateTime(new Date());
				labelDao.insert(l);
				labelUser.setLabel(l);
				labelSentence.setLabel(l);
			}else{
				labelUser.setLabel(existLabel);
				labelSentence.setLabel(existLabel);
			}
			labelUserDao.insert(labelUser);
			labelSentenceDao.insert(labelSentence);
		}
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
		List<UserRelatedSentence> relatedSentences=userRelatedSentenceDao.getRelatedSentences(userID, page, limit);
		List<Sentence> sentences=new ArrayList<Sentence>();
		for(UserRelatedSentence rs:relatedSentences){
			sentences.add(rs.getSentence());
		}
		return sentences;
	}

	public List<Sentence> getLabelSentences(long labelID,int page,int limit){
		List<LabelSentence> labelSentences=labelSentenceDao.getLabelSentencesByLabel(labelID, page, limit);
		List<Sentence> sentences=new ArrayList<Sentence>();
		for(LabelSentence ls:labelSentences){
			sentences.add(ls.getSentence());
		}
		return sentences;
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

	public IUserRelatedSentenceDao getRelatedObjectDao() {
		return relatedObjectDao;
	}

	public void setRelatedObjectDao(IUserRelatedSentenceDao relatedObjectDao) {
		this.relatedObjectDao = relatedObjectDao;
	}

	public ILabelUserDao getLabelUserDao() {
		return labelUserDao;
	}

	public void setLabelUserDao(ILabelUserDao labelUserDao) {
		this.labelUserDao = labelUserDao;
	}

	public ILabelSentenceDao getLabelSentenceDao() {
		return labelSentenceDao;
	}

	public void setLabelSentenceDao(ILabelSentenceDao labelSentenceDao) {
		this.labelSentenceDao = labelSentenceDao;
	}

	public IUserRelatedSentenceDao getUserRelatedSentenceDao() {
		return userRelatedSentenceDao;
	}

	public void setUserRelatedSentenceDao(
			IUserRelatedSentenceDao userRelatedSentenceDao) {
		this.userRelatedSentenceDao = userRelatedSentenceDao;
	}



}
