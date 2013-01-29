package bijian.model.dao;

import java.util.List;

import bijian.model.bean.Sentence;
import bijian.model.bean.relationbean.LabelSentence;

public interface ILabelSentenceDao extends IBaseDao {
	 public int getLabelSentencesSizeByLabel(long labelID);
     public List<LabelSentence> getLabelSentencesByLabel(long labelID,int page,int limit);
     public int getLabelSentencesSizeBySentence(long sentenceID);
     public List<LabelSentence> getLabelSentencesBySentence(long sentenceID,int page,int limit);
}