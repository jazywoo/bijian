package bijian.model.bean;

import java.util.Date;

public class Label {
   private long labelID;
   private String content;
   private Date createTime;
	public long getLabelID() {
		return labelID;
	}
	public void setLabelID(long labelID) {
		this.labelID = labelID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
		

}
