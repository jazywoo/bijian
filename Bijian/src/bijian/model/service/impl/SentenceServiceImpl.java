package bijian.model.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bijian.model.bean.Label;
import bijian.model.bean.Sentence;
import bijian.model.bean.User;
import bijian.model.bean.relationbean.Attention;
import bijian.model.bean.relationbean.Forwarding;
import bijian.model.bean.relationbean.LabelSentence;
import bijian.model.bean.relationbean.LabelUser;
import bijian.model.bean.relationbean.LoveSentence;
import bijian.model.bean.relationbean.ReportSentence;
import bijian.model.bean.relationbean.SubscribeLabel;
import bijian.model.bean.relationbean.UserRelatedSentence;
import bijian.model.dao.IAttentionDao;
import bijian.model.dao.IForwardingDao;
import bijian.model.dao.ILabelDao;
import bijian.model.dao.ILabelSentenceDao;
import bijian.model.dao.ILabelUserDao;
import bijian.model.dao.ILoveSentenceDao;
import bijian.model.dao.IReportSentenceDao;
import bijian.model.dao.ISentenceDao;
import bijian.model.dao.ISubscribeLabelDao;
import bijian.model.dao.IUserDao;
import bijian.model.dao.IUserRelatedSentenceDao;
import bijian.model.service.ISentenceService;

public class SentenceServiceImpl implements ISentenceService{
    private ISentenceDao sentenceDao;
    private IUserDao userDao;
    private IAttentionDao attentionDao;
    private ILabelDao labelDao;
    private ILabelUserDao labelUserDao;
    private ILabelSentenceDao labelSentenceDao;
    private ISubscribeLabelDao subscribeLabelDao;
    private IReportSentenceDao reportSentenceDao;
    private IUserRelatedSentenceDao userRelatedSentenceDao;
    private ILoveSentenceDao loveSentenceDao;
    private IForwardingDao forwardingDao;
	
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
	public List<Sentence> getAllAttentionSentences(long userID, int page, int limit) {
		List<Attention> attentions=attentionDao.get(userID, page, limit);
		List<Long> attentionListID=new ArrayList<Long>();
		for(Attention a:attentions){
			attentionListID.add(a.getAttentioner().getUserID());
		}
		return sentenceDao.getAttention(attentionListID, page, limit);
	}
	public List<Sentence> getOneAttentionSentences(long attentionerID, int page, int limit) {
		return sentenceDao.getByUser(attentionerID, page, limit);
	}

	public List<Sentence> getHotSentence(int page, int limit) {
		return sentenceDao.getHot(page, limit);
	}

	public List<Sentence> getLatestSentence(int page, int limit) {
		return sentenceDao.getLatest(page, limit);
	}
	public int getMySentencesSize(long userID){
		return sentenceDao.getSizeByUser(userID);
	}
	public Sentence getMyHotestSentence(long userID){
		return sentenceDao.getHotestByUser(userID);
	}
	public List<Sentence> getMySentences(long userID, int page, int limit) {
		return sentenceDao.getByUser(userID, page, limit);
	}

	public List<Sentence> getRelatedMeSentences(long userID, int page, int limit) {
		List<UserRelatedSentence> relatedObjects=userRelatedSentenceDao.getRelatedSentences(userID, page, limit);
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
	public List<Sentence> getAllLabelSentences(long userID,int page,int limit){
		int labelSize=subscribeLabelDao.getSubscribeLabelsSizeByUser(userID);
		List<SubscribeLabel> subscribeLabels=subscribeLabelDao.getSubscribeLabelsByUser(userID, 0, labelSize-1);
		List<Long> labelList=new ArrayList();
		for(SubscribeLabel sl:subscribeLabels){
			labelList.add(sl.getLabel().getLabelID());
		}
		List<LabelSentence> labelSentences=labelSentenceDao.getLabelSentencesByLabelList(labelList, page, limit);
		List<Sentence> sentences=new ArrayList<Sentence>();
		for(LabelSentence ls:labelSentences){
			sentences.add(ls.getSentence());
		}
		return sentences;
	}
	public List<Sentence> getOneLabelSentences(long labelID,int page,int limit){
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
	public int getLoveSentencesSize(long userID){
		return loveSentenceDao.getLoveSentencesSizeByUser(userID);
	}
    public List<Sentence> getLovedSentences(long userID,int page,int limit){
    	List<LoveSentence> loveSentences=loveSentenceDao.getLoveSentencesByUser(userID, page, limit);
    	List<Sentence> sentences=new ArrayList<Sentence>();
    	for(LoveSentence l:loveSentences){
    		sentences.add(l.getSentence());
    	}
    	return sentences;
    }
    public List<LoveSentence> getLoveSentences(long userID,int page,int limit){
    	return loveSentenceDao.getLoveSentencesByUser(userID, page, limit);
    }
    public void cancelLove(long loveSentenceID){
    	LoveSentence loveSentence=(LoveSentence) loveSentenceDao.get(loveSentenceID);
    	loveSentence.setIsValid(0);
    	loveSentenceDao.update(loveSentence);
    }
	public List<Sentence> searchSentence(String keyword, int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public void lovingSentence(long sentenceID,long userID) {
		User user=(User) userDao.get(userID);
		Sentence sentence=(Sentence) sentenceDao.get(sentenceID);
		LoveSentence loveSentence=new LoveSentence();
		loveSentence.setSentence(sentence);
		loveSentence.setUser(user);
		loveSentence.setCreateTime(new Date());
		loveSentence.setIsValid(1);
		loveSentenceDao.insert(loveSentence);
		sentence.setGoodNum(sentence.getGoodNum()+1);
		sentenceDao.update(sentence);
	}
	public List<Forwarding> getForwarding(long sentenceID,int page,int limit){
		return forwardingDao.getForwardingsBySentence(sentenceID, page, limit);
	}
	public List<LoveSentence> getLove(long sentenceID,int page,int limit){
		return loveSentenceDao.getLoveSentencesBySentence(sentenceID, page, limit);
	}
	public void updateSentence(long userID,long sentenceID,Sentence sentence,List<Label> labels) {
		User user=(User) userDao.get(userID);
		Sentence oldSentence=(Sentence) sentenceDao.get(sentenceID);
		if(sentence.getContent()!=null){
			oldSentence.setContent(sentence.getContent());
		}
		if(sentence.getFromPlace()!=null){
			oldSentence.setFromPlace(sentence.getFromPlace());
		}
		sentenceDao.update(oldSentence);
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
		return userRelatedSentenceDao;
	}

	public void setRelatedObjectDao(IUserRelatedSentenceDao userRelatedSentenceDao) {
		this.userRelatedSentenceDao = userRelatedSentenceDao;
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

	public ILoveSentenceDao getLoveSentenceDao() {
		return loveSentenceDao;
	}

	public void setLoveSentenceDao(ILoveSentenceDao loveSentenceDao) {
		this.loveSentenceDao = loveSentenceDao;
	}

	public IForwardingDao getForwardingDao() {
		return forwardingDao;
	}

	public void setForwardingDao(IForwardingDao forwardingDao) {
		this.forwardingDao = forwardingDao;
	}

	public ISubscribeLabelDao getSubscribeLabelDao() {
		return subscribeLabelDao;
	}

	public void setSubscribeLabelDao(ISubscribeLabelDao subscribeLabelDao) {
		this.subscribeLabelDao = subscribeLabelDao;
	}



}
