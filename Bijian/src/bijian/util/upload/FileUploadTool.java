package bijian.util.upload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class FileUploadTool {
	private String saveDir;
    private int userID;
    private File[] uploadFiles;
    private String[] uploadFileNames;
    private String[] uploadFileContentTypes;
    
    public void beginUpload() throws IOException{
    	for(int i=0;i<uploadFiles.length;i++){
    		String uploadDate=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString();
    		String filename=userID+"_"+uploadDate+"_"+uploadFileNames[i];
    		File destFile=new File(saveDir,filename);
    		FileUtils.copyFile(uploadFiles[i], destFile);
    	}
    }
    
    
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public File[] getUploadFiles() {
		return uploadFiles;
	}
	public void setUploadFiles(File[] uploadFiles) {
		this.uploadFiles = uploadFiles;
	}
	public String[] getUploadFileNames() {
		return uploadFileNames;
	}
	public void setUploadFileNames(String[] uploadFileNames) {
		this.uploadFileNames = uploadFileNames;
	}
	public String[] getUploadFileContentTypes() {
		return uploadFileContentTypes;
	}
	public void setUploadFileContentTypes(String[] uploadFileContentTypes) {
		this.uploadFileContentTypes = uploadFileContentTypes;
	}


	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}


	public String getSaveDir() {
		return saveDir;
	}
    
     
}
