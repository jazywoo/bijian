package bijian.util.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;

public class FileUploadListener implements ProgressListener{
    private HttpSession session;
    
    public FileUploadListener(HttpServletRequest request){
    	super();
    	this.session=request.getSession();
    }
	public void update(long uploadByte, long fileSizeByte, int fileItemIndex) {
		if(fileSizeByte==-1){
			System.out.println("上传完成");
		}else{
			FileUploadState state=(FileUploadState) session.getAttribute("uploadState");
			state.setUploadByte(uploadByte);
			state.setFileSizeByte(fileSizeByte);
			state.setFileItemIndex(fileItemIndex);
			session.setAttribute("uploadState", state);
		}
	}

}
