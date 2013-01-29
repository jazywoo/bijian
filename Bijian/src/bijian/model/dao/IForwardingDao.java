package bijian.model.dao;

import java.util.List;

import bijian.model.bean.relationbean.Forwarding;

public interface IForwardingDao extends IBaseDao {
   public int getForwardingsSizeBySentence(long sentenceID);
   public List<Forwarding> getForwardingsBySentence(long sentenceID,int page,int limit);
}
