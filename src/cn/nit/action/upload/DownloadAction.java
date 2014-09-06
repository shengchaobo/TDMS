package cn.nit.action.upload;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class DownloadAction extends ActionSupport {
	
    private static final long serialVersionUID = 9048071785232069492L;
    private FileInputStream inputStream;
    private String inputPath;
    public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	private String downLoadName;
    private String fileNum;

    public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public InputStream getDownloadFile() {
      // TODO Auto-generated method stub
    	
      // 把上传的文件放到指定的路径下     	  
      String path = ServletActionContext.getServletContext().getRealPath(inputPath);
    
      //判断目录是否存
      String dir = path+"\\"+this.getFileNum();
      File dirFile = new File(dir);
      
      if(dirFile.exists()){
      	
      	File[] fileList = dirFile.listFiles();	        	
      	for(File f:fileList){
      		this.setDownLoadName(f.getName());           
            try {
            	 inputStream = new FileInputStream(f);
            } catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
                 e.printStackTrace();
            }
      	}
      }
      return inputStream;
     }

     @Override
     public String execute() {
      // TODO Auto-generated method stub
      return "success";
     }

	public void setDownLoadName(String downLoadName) {
		this.downLoadName = downLoadName;
	}

	public String getDownLoadName() {
		
		try {
			this.downLoadName = URLEncoder.encode(downLoadName, "UTF-8");
			//this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// 中文乱码解决
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downLoadName;
	}
}
