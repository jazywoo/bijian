package bijian.util.upload;

public class FileUploadState {
    private long uploadByte;
    private long fileSizeByte;
    private int fileItemIndex;
    
	public long getUploadByte() {
		return uploadByte;
	}
	public void setUploadByte(long uploadByte) {
		this.uploadByte = uploadByte;
	}
	public long getFileSizeByte() {
		return fileSizeByte;
	}
	public void setFileSizeByte(long fileSizeByte) {
		this.fileSizeByte = fileSizeByte;
	}
	public int getFileItemIndex() {
		return fileItemIndex;
	}
	public void setFileItemIndex(int fileItemIndex) {
		this.fileItemIndex = fileItemIndex;
	}
}
