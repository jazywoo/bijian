package bijian.model.service;

import java.util.List;

import bijian.model.bean.Label;

public interface ILabelService {
    public void addLabel(long userID,Label label);
    public List<Label> getHotLabels();
}
