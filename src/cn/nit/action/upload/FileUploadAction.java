package cn.nit.action.upload;

import java.io.File;  
import java.io.IOException;  
import java.io.PrintWriter;
import java.util.List;  

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;  
import org.apache.struts2.ServletActionContext;  
import com.opensymphony.xwork2.ActionSupport;  


public class FileUploadAction extends ActionSupport {  
	
    private static final long serialVersionUID = 1L;  
 
    private String inputPath;
    private File uploadFile;
    private String uploadFileFileName;  
 
    
    public String getInputPath() {  
        return inputPath;  
    }  

	public void setInputPath(String inputPath) {  
	
	   this.inputPath = inputPath; 
	} 
	
    public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
  
/*    public String execute() {  
  
       // 把上传的文件放到指定的路径下  
    	HttpServletRequest request = ServletActionContext.getRequest() ;
  
       String path = ServletActionContext.getServletContext().getRealPath(inputPath);  
  
       // 写到指定的路径中  
  
       File file = new File(path);  
  
       // 如果指定的路径没有就创建  
  
       if (!file.exists()) {  
  
           file.mkdirs();  
       }  
  
       // 把得到的文件的集合通过循环的方式读取并放在指定的路径下  
  
       for (int i = 0; i < upload.size(); i++) {  
           try {  
  
              //list集合通过get(i)的方式来获取索引  
  
              FileUtils.copyFile(upload.get(i), new File(file, uploadFileName.get(i)));  
  
           } catch (IOException e) {  
  
              // TODO Auto-generated catch block  
  
              e.printStackTrace();  
           }  
       }  
  
       return SUCCESS;  
    }  */
    
    public void upload() throws IOException{
    	
		PrintWriter out = null ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try{
			
	        // 把上传的文件放到指定的路径下     	  
	        String path = ServletActionContext.getServletContext().getRealPath(inputPath);
	        
	        // 写到指定的路径中          
	        File savefile = new File(new File(path), this.getUploadFileFileName());
	        
	        if(savefile.exists()){
	        	savefile.delete();
	        }
	        
	        FileUtils.copyFile(uploadFile, savefile);
	        	        
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			out.print("{\"state\":true,\"data\":\"上传成功!!!\"}") ;
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,\"data\":\"上传失败，文件异常!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
        
    }

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

}  