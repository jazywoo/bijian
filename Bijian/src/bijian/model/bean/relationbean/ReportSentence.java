package bijian.model.bean.relationbean;

import java.util.Date;

import bijian.model.bean.Sentence;
import bijian.model.bean.User;

public class ReportSentence {
    private long reportSentenceID;
    private User reporter;
    private Sentence sentence;
    private Date createTime;
    private Integer isValid;
    
	public long getReportSentenceID() {
		return reportSentenceID;
	}
	public void setReportSentenceID(long reportSentenceID) {
		this.reportSentenceID = reportSentenceID;
	}
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
	public Sentence getSentence() {
		return sentence;
	}
	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
}
