package cn.nit.action.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DelFileAction  extends ActionSupport{
	
	private String inputPath;
	private String fileNum;
	
	public String getInputPath() {
		return inputPath;
	}
	
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public void deleteFile() throws IOException{
				
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8") ;  
		PrintWriter out = response.getWriter();
		try{
			
	        // 把上传的文件放到指定的路径下     	  
	        String path = ServletActionContext.getServletContext().getRealPath(inputPath);
	        
	        //判断目录是否存
	        String dir = path+"\\"+this.getFileNum();
	        File dirFile = new File(dir);

	        if(dirFile.exists()){
	        	
	        	File[] fileList = dirFile.listFiles();	        	
	        	for(File f:fileList){
	        		f.delete();
	        	}
	        	dirFile.delete();
	        }
	        
	        out.print("{\"state\":true,\"data\":\"删除成功\"}") ;
	        
		}catch (Exception e) {
			e.printStackTrace();
			out.print("{\"state\":false,\"data\":\"删除失败\"}") ;
		}				
					        
	}



	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}



	public String getFileNum() {
		return fileNum;
	}

}
