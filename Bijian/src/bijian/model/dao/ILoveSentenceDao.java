package bijian.model.dao;

import java.util.List;

import bijian.model.bean.relationbean.LoveSentence;


public interface ILoveSentenceDao extends IBaseDao {
	 public int getLoveSentencesSizeByUser(long userID);
	 public List<LoveSentence> getLoveSentencesByUser(long userID,int page,int limit);
	 public int getLoveSentencesSizeBySentence(long sentenceID);
	 public List<LoveSentence> getLoveSentencesBySentence(long sentenceID,int page,int limit);
}
