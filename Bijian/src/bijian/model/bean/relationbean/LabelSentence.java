package bijian.model.bean.relationbean;

import bijian.model.bean.Label;
import bijian.model.bean.Sentence;

public class LabelSentence {
    private long labelSentenceID;
    private Label label;
    private Sentence sentence;
	public long getLabelSentenceID() {
		return labelSentenceID;
	}
	public void setLabelSentenceID(long labelSentenceID) {
		this.labelSentenceID = labelSentenceID;
	}
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public Sentence getSentence() {
		return sentence;
	}
	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}
}
